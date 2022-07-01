package com.umart.mdbspringboot.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("groceryitems")
public class GroceryItem {
    @Id
    private String id;

    private String name;
    private int quantity;
    private String category;
    private double price;

    public GroceryItem(String id, String name, int quantity, String category, double price) {
        super();
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.category = category;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String newCategory) {
        category = newCategory;
    }

    public double getPrice() {
        return price;
    }
}

