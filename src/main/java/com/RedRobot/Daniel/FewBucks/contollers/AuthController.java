package com.RedRobot.Daniel.FewBucks.contollers;

import com.RedRobot.Daniel.FewBucks.entities.Users;
import com.RedRobot.Daniel.FewBucks.repositories.UsersRepo;
import com.RedRobot.Daniel.FewBucks.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/public")
public class AuthController {

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private UsersService usersService;

    /*The registerUser method receives the request body and passes the Users object to the UsersService.
    * The entire registration process is synchronous. The response is only sent back to the client after all processing is complete.*/
    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody Users user) {
        try {
            Users newUser = usersService.registerUser(user);
            return ResponseEntity.ok(newUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
