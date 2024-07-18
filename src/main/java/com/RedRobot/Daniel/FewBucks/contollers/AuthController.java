package com.RedRobot.Daniel.FewBucks.contollers;

import com.RedRobot.Daniel.FewBucks.entities.Users;
import com.RedRobot.Daniel.FewBucks.repositories.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/public")
public class AuthController {

    @Autowired
    private UsersRepo usersRepo;

    @PostMapping("/register")
    public Users registerUser(@RequestBody Users user){
        return usersRepo.save(user);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        if (usersRepo.existsById(id)) {
            usersRepo.deleteById(id);
            return "User with ID " + id + " deleted successfully.";
        } else {
            return "User with ID " + id + " not found.";
        }

    }
    //    Implement methods that register new user as well as assigning the role to the new user. Eventually adding the option to log in as a user.
}
