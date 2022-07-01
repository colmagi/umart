package com.umart.mdbspringboot.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("groceryitems")
public class Vegetable extends GroceryItem {

    public double weight;

    public Vegetable(String id, String name, int quantity, String category, double price, double weight) {
        super(id, name, quantity, category, price);
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }
}
