package com.colmagi.umart.data;

public class GroceryUpdateObject {

    private int id;
    private int quantity;

    public GroceryUpdateObject(int id, int quantity){
        this.id = id;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }
}
