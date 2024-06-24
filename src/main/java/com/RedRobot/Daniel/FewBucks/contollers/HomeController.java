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
    public String helloWorld(){
        return "Welcome to FewBucks!";
    }

    @GetMapping("listProducts")
    public List<Inventory> getInventory(){
        return inventoryService.getInventory();

    }
    @PostMapping("addItem")
    public Inventory addItem(@RequestBody Inventory item){
        return inventoryService.addItem(item);
    }

    /*
    Optional: A container that can hold a value or be empty.
    map: Applies a function to the contained value if it exists, transforming it.
    orElseGet: Provides an alternative value if the Optional is empty.
    ResponseEntity: A Spring class that represents an HTTP response, with a status code and body.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> getItemId(@PathVariable Long id){
        Optional<Inventory> inventoryItem = inventoryService.getItemById(id);
        return inventoryItem.<ResponseEntity<Object>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Item not found"));

    }



}
