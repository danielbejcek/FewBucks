package com.RedRobot.Daniel.FewBucks.model;

import jakarta.persistence.*;
import lombok.Data;

//@Data
@Entity
@Table(name = "Inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itemID")
    private Long id;

    @Column(name = "itemName")
    private String itemName;

    @Column(name = "itemAmount")
    private int itemAmount;

    public Inventory(long id, String itemName, int itemAmount) {
        this.id = id;
        this.itemName = itemName;
        this.itemAmount = itemAmount;
    }
}