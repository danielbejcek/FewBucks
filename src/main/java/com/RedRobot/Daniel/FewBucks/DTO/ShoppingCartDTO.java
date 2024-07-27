package com.RedRobot.Daniel.FewBucks.DTO;

import java.util.List;

public class ShoppingCartDTO {
    private long id;

    private long userId;

    private List<CartItemDTO> items;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public List<CartItemDTO> getItems() {
        return items;
    }

    public void setItems(List<CartItemDTO> items) {
        this.items = items;
    }


}
