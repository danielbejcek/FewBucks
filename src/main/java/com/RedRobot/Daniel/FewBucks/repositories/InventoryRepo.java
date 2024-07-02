package com.RedRobot.Daniel.FewBucks.repositories;

import com.RedRobot.Daniel.FewBucks.entities.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepo extends JpaRepository<Inventory,Long> {
    /* Containing: Partial match, similar to SQL's LIKE with wildcards.
    Allows to search for items without explicitly stating the full name of the item.*/
    List<Inventory> findByItemNameContaining(String query);
}
