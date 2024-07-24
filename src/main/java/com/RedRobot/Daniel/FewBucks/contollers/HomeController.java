package com.RedRobot.Daniel.FewBucks.contollers;


import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
public class HomeController {

    @GetMapping("/")
    public String homePage(){

        /*SecurityContextHolder holds the SecurityContext, which contains the authentication information of the currently authenticated user. */
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        /*Ensure the authentication object is not null and the user is authenticated.*/
        if (auth != null && auth.isAuthenticated()){
            /*The principal object represents the authenticated user.*/
            Object principal = auth.getPrincipal();
            String username;
            Collection role;

            if (principal instanceof UserDetails){
                /*Casts principal to treat it as object of UserDetails and calls getUsername() to get the authenticated user's username.*/
                username = ((UserDetails) principal).getUsername();
                role = ((UserDetails) principal).getAuthorities();
                return "Welcome to FewBucks "+ role.toString().toLowerCase() +" " +username + "!";
            }
        }
        return "Welcome to FewBucks!";
    }
}


