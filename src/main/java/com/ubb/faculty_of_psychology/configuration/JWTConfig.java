package com.ubb.faculty_of_psychology.configuration;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.util.Pair;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECGenParameterSpec;
@Configuration
public class JWTConfig {

    @Bean
    public Algorithm algorithm() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        Pair<ECPrivateKey, ECPublicKey> pair = generateECDSAKeys();
        return Algorithm.ECDSA512(pair.getSecond(), pair.getFirst());
    }

    private static Pair<ECPrivateKey, ECPublicKey> generateECDSAKeys() throws NoSuchAlgorithmException,
            InvalidAlgorithmParameterException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
        keyPairGenerator.initialize(new ECGenParameterSpec("secp256r1"));
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        return Pair.of((ECPrivateKey) keyPair.getPrivate(), (ECPublicKey) keyPair.getPublic());
    }
}