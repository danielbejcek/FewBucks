package com.RedRobot.Daniel.FewBucks.contollers;


import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {

    @GetMapping("")
    public String homePage(){
        return "Welcome to FewBucks!";
    }
}


