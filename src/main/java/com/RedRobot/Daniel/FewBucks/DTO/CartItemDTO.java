package com.RedRobot.Daniel.FewBucks.DTO;

public class CartItemDTO {
    private long id;

    private long itemId;

    private String itemName;

    private int quantity;

    private double itemPrice;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getItemid() {
        return itemId;
    }

    public void setItemid(long itemid) {
        this.itemId = itemid;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }
}
