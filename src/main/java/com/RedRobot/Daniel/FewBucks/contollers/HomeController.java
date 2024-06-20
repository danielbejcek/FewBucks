package com.RedRobot.Daniel.FewBucks.contollers;

import com.RedRobot.Daniel.FewBucks.entities.Inventory;
import com.RedRobot.Daniel.FewBucks.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {
    @Autowired
    InventoryService inventoryService;

    @GetMapping("")
    public String helloWorld(){
        return "Howdy";
    }

    @GetMapping("/getInventory")
    public List<Inventory> getInventory(){
        return inventoryService.getInventory();

    }


}
