package com.RedRobot.Daniel.FewBucks.services;

import com.RedRobot.Daniel.FewBucks.entities.Inventory;
import com.RedRobot.Daniel.FewBucks.repositories.InventoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepo inventoryRepo;


    public List<Inventory> getInventory(){
        return inventoryRepo.findAll();
    }


    public Inventory addItem(Inventory item){
        item.setItemInStock(item.getItemAmount() > 0);
        return inventoryRepo.save(item);
    }

    public Optional<Inventory> getItemById(Long id){
        return inventoryRepo.findById(id);
    }

    public List<Inventory> searchInventory(String search){
        return inventoryRepo.findByItemNameContainingIgnoreCase(search);
    }


}
