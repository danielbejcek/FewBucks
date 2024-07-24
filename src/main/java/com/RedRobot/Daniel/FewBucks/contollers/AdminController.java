package com.RedRobot.Daniel.FewBucks.contollers;

import com.RedRobot.Daniel.FewBucks.entities.Inventory;
import com.RedRobot.Daniel.FewBucks.entities.Users;
import com.RedRobot.Daniel.FewBucks.repositories.UsersRepo;
import com.RedRobot.Daniel.FewBucks.services.InventoryService;
import com.RedRobot.Daniel.FewBucks.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/admin")
public class AdminController {

    @Autowired
    UsersService usersService;

    @Autowired
    UsersRepo usersRepo;

    @Autowired
    InventoryService inventoryService;


    /*Url is not able to accept post request. This needs to be done using an external application like Postman*/
    @PostMapping("/addItem")
    public Inventory addItem(@RequestBody Inventory item){
        return inventoryService.addItem(item);
    }

    @GetMapping("/users")
    public List<Users> getUsers(){
        return usersService.getUsers();
    }

    /*Deleting a USER from the USERS table*/
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        if (usersRepo.existsById(id)) {
            usersRepo.deleteById(id);
            return "User with ID " + id + " deleted successfully.";
        } else {
            return "User with ID " + id + " not found.";
        }

    }

    @GetMapping("/search")
    public Optional<Users> searchUser(@RequestParam String search){
        return usersService.findUser(search);
    }
}
