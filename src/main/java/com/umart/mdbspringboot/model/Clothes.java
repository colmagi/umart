package com.umart.mdbspringboot.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("groceryitems")
public class Clothes extends GroceryItem {
    public String size;
    public boolean sex;

    public Clothes(String id, String name, int quantity, String category, double price, String size, boolean sex) {
        super(id, name, quantity, category, price);
        this.size = size;
        this.sex = sex;
    }

    public String getSize() {
        return size;
    }

    public boolean getSex() {
        return sex;
    }

}
