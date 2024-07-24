package com.RedRobot.Daniel.FewBucks.contollers;

import com.RedRobot.Daniel.FewBucks.entities.Inventory;
import com.RedRobot.Daniel.FewBucks.repositories.InventoryRepo;
import com.RedRobot.Daniel.FewBucks.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/public")
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    @GetMapping("/listProducts/{id}")
    public ResponseEntity<Object> getItemId(@PathVariable Long id) {
        Optional<Inventory> inventoryItem = inventoryService.getItemById(id);

        if (inventoryItem.isPresent()) {
            return ResponseEntity.ok(inventoryItem.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found");
        }
    }

    /*
    Method that allows to search for an item within the inventory with parameter passed in a query like so:
    "api/public/listProducts?search=*item*".
    Whenever the query parameter remains unfilled, the whole inventory is returned.
    */
    @GetMapping("/listProducts")
    public List<Inventory> searchInventory(@RequestParam(required = false) String search) {
        if (search != null && !search.isEmpty()) {
            return inventoryService.searchInventory(search);
        } else {
            return inventoryService.getInventory();
        }
    }


}

