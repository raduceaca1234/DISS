package com.ubb.faculty_of_psychology.controller;

import com.ubb.faculty_of_psychology.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/hello")
    public String hello(){
        return "Hello";
    }

//
//    @GetMapping("/admin")
//    public String adminPage(Principal principal, Model model) {
//        String email = principal.getName();
//        model.addAttribute("currentUser", userService.findByEmail(email));
//        model.addAttribute("allUsers", userService.getAllUsers());
//        return "adminPage";
//    }
//
//    @GetMapping("/delete/{user_id}")
//    public String deleteUser(@PathVariable("user_id") Long user_id) {
//        User user = userService.findUserById(user_id);
//        userService.deleteUser(user);
//        return "redirect:/admin";
//    }
//
//    @GetMapping("/admin/new/{user_id}")
//    public String giveAdminPrivilege(@PathVariable("user_id") Long user_id) {
//        userService.giveAdminPrivilege(user_id);
//        return "redirect:/admin";
//
//    }
}
