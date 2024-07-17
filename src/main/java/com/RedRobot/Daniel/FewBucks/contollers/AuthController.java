package com.RedRobot.Daniel.FewBucks.contollers;

import com.RedRobot.Daniel.FewBucks.entities.Users;
import com.RedRobot.Daniel.FewBucks.repositories.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/public")
public class AuthController {

    @Autowired
    private UsersRepo usersRepo;

    @PostMapping("/register")
    public Users registerUser(@RequestBody Users user){
        return usersRepo.save(user);
    }
//    Implement methods that register new user as well as assigning the role to the new user. Eventually adding the option to log in as a user.
}
