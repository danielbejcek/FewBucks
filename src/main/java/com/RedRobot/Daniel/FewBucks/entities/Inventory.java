package com.RedRobot.Daniel.FewBucks.entities;

import jakarta.persistence.*;



@Entity
@Table(name = "Inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @Column(name = "itemName", nullable = false, unique = true)
    private String itemName;

    @Column(name = "itemPrice", nullable = false)
    private double itemPrice;

    @Column(name = "itemAmount")
    private int itemAmount;

    @Column(name = "itemInStock")
    private boolean itemInStock;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice(){
        return itemPrice;
    }
    public void setItemPrice(double itemPrice){
        this.itemPrice = itemPrice;
    }

    public int getItemAmount() {
        return itemAmount;
    }

    public void setItemAmount(int itemAmount) {
        this.itemAmount = itemAmount;
    }

    public boolean getItemInStock(){
        return itemInStock;
    }
    public void setItemInStock(boolean itemInStock){
        this.itemInStock = itemInStock;
    }
}