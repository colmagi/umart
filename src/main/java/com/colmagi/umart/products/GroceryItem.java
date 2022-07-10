package com.colmagi.umart.products;

import com.colmagi.umart.enums.Category;
import com.colmagi.umart.data.AbstractGroceryObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("groceryitems")
public class GroceryItem {
    @Id
    private final int id;

    private final String name;
    private int quantity;
    private Category category;
    private final double price;

    public GroceryItem(AbstractGroceryObject ago) {
        this.id = ago.getId();
        this.name = ago.getName();
        this.quantity = ago.getQuantity();
        this.category = ago.getCategory();
        this.price = ago.getPrice();
    }

    // Overloaded constructor for manual instantiation
    // When possible, instantiate using an instantiated AbstractGroceryObject
    public GroceryItem(int id, String name, Category cat, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.category = cat;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int newQuantity) {
        quantity = newQuantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category newCategory) {
        category = newCategory;
    }

    public double getPrice() {
        return price;
    }
}

