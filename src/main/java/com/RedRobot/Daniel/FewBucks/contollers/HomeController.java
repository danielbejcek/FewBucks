package com.RedRobot.Daniel.FewBucks.contollers;

import com.RedRobot.Daniel.FewBucks.entities.Inventory;
import com.RedRobot.Daniel.FewBucks.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
public class HomeController {
    @Autowired
    InventoryService inventoryService;

    @GetMapping("")
    public String homePage(){
        return "Welcome to FewBucks!";
    }

//    @GetMapping("api/public/listProducts")
//    public List<Inventory> getInventory(){
//        return inventoryService.getInventory();
//
//    }
    @PostMapping("/addItem")
    public Inventory addItem(@RequestBody Inventory item){
        return inventoryService.addItem(item);
    }

    /*
    Optional: A container that can hold a value or be empty.
    ResponseEntity: A Spring class that represents an HTTP response, with a status code and body.
     */
    @GetMapping("api/public/listProducts/{id}")
    public ResponseEntity<Object> getItemId(@PathVariable Long id) {
        Optional<Inventory> inventoryItem = inventoryService.getItemById(id);

        if (inventoryItem.isPresent()) {
            return ResponseEntity.ok(inventoryItem.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found");
        }
    }
//    @GetMapping("api/public/listProducts/")
//    public List<Inventory> searchInventory(@RequestParam String search){
//        return inventoryService.searchInventory(search);
//    }

    /*
    Method that allows to search for an item within the inventory with parameter passed in a query like so:
    "api/public/listProducts?search=*item*".
    Whenever the query parameter remains unfilled, whole inventory is returned.
    */
    @GetMapping("api/public/listProducts")
    public List<Inventory> searchInventory(@RequestParam(required = false) String search) {
        if (search != null && !search.isEmpty()) {
            return inventoryService.searchInventory(search);
        } else {
            return inventoryService.getInventory();
        }
    }
}


