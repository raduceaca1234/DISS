package com.ubb.faculty_of_psychology.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.*;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.ubb.faculty_of_psychology.configuration.UserPrincipal;
import com.ubb.faculty_of_psychology.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import static java.util.Collections.emptyList;
@Service
public class JwtTokenService {
    private static final long EXPIRATIONTIME = 120000;
    private static final String SIGNINGKEY = "signingKey";
    private static final String BEARER_PREFIX = "Bearer";
    public static final Duration JWT_TOKEN_SESSION_LENGTH = Duration.of(7, ChronoUnit.HOURS);
    private static final String SECRET = "Vndzk9QZhDbIBKOX1lg0ur70nMNhjlzj";
    private final Algorithm algorithm;
    public JwtTokenService(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    public String generateToken(User user) {
        Algorithm algorithm = Algorithm.HMAC512(SECRET);
        try {
            return JWT.create()
                    .withIssuer("Faculty-Of-Psychology")
                    .withExpiresAt(Date.from(Instant.now().plus(JWT_TOKEN_SESSION_LENGTH)))
                    .withClaim("id", user.getId())
                    .withClaim("email", user.getEmail())
                    .sign(algorithm);
        } catch(JWTCreationException exception) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "An error happened while authenticating"
            );
        }
    }

    public UserPrincipal parseToken(String token) throws Exception {
        JWTVerifier build = JWT.require(algorithm)
                .withClaimPresence("id")
                .withClaimPresence("email")
                .withClaimPresence("admin")
                .build();
        try {
            DecodedJWT verify = build.verify(token);
            Long id = verify.getClaim("id").asLong();
            String email = verify.getClaim("email").asString();
            return new UserPrincipal(id, email);
        } catch(JWTDecodeException | SignatureVerificationException | TokenExpiredException | AlgorithmMismatchException e) {
            throw new Exception();
        }
    }

    public static void addJWTToken(HttpServletResponse response, String username) {
        String JwtToken = Jwts.builder().setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SIGNINGKEY)
                .compact();
        response.addHeader("Authorization", BEARER_PREFIX + " " + JwtToken);
        response.addHeader("Access-Control-Expose-Headers", "Authorization");
    }

    public static Authentication getAuthentication(HttpServletRequest request) throws NoSuchAlgorithmException {
        String token = request.getHeader("Authorization");
        SecretKey secretKey = KeyGenerator.getInstance("AES").generateKey();
        if (token != null) {
            String user = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();

            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, emptyList());
            } else {
                throw new RuntimeException("Authentication failed");
            }
        }
        return null;
    }
}
