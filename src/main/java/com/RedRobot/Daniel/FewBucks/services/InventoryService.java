package com.RedRobot.Daniel.FewBucks.services;

import com.RedRobot.Daniel.FewBucks.entities.Inventory;
import com.RedRobot.Daniel.FewBucks.repositories.InventoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepo inventoryRepo;


    public List<Inventory> getInventory(){
        return inventoryRepo.findAll();
    }

    public Inventory addItem(Inventory item){
       return inventoryRepo.save(item);
    }


}
