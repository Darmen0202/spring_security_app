package com.example.springsecurity.contollers;

import com.example.springsecurity.security.PersonDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class MainController {

    @GetMapping("mainPage")
    public String mainPage() {
        return "index";
    }

    @GetMapping("admin")
    public String adminPage() {
        return "admin/adminPage";
    }

    @GetMapping("showUserProfile")
    public String showUserProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        System.out.println(personDetails.getPerson());

        return "users/profile";
    }
}
