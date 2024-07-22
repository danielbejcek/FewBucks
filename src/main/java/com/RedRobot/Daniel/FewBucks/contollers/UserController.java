package com.RedRobot.Daniel.FewBucks.contollers;

import com.RedRobot.Daniel.FewBucks.entities.Users;
import com.RedRobot.Daniel.FewBucks.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/auth")
public class UserController {

    @Autowired
    UsersService usersService;

    @GetMapping("/users")
    public List<Users> getUsers(){
        return usersService.getUsers();
    }
    @GetMapping("/search")
    public Optional<Users> searchUser(@RequestParam String search){
        return usersService.findUser(search);
    }

//    Implement methods for adding items to basket, listing the basket, removing items from a basket.

}
