package com.umart.mdbspringboot.model;

import com.umart.mdbspringboot.data.Attribute;
import com.umart.mdbspringboot.data.Category;

import java.util.HashMap;
import java.util.Map;

/**
 * This object is used to instantiate a
 * {@link GroceryItem}
 */
public class AbstractGroceryObject {
    private final int id;
    private final String name;
    private int quantity;
    private Category category;
    private final double price;
    private final Map<Attribute, String> meta;

    public AbstractGroceryObject(int id, String name, Category cat, int quantity, double price, String data) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.category = cat;
        this.price = price;

        // Deserialize metadata
        // Data is stored in the format attribute_name:value,attribute_name2:value2
        meta = new HashMap<>();
        String[] splitData = data.split(",");
        for (String pair : splitData) {
            String[] dataSet = pair.split(":");
            // Get the attribute
            try {
                Attribute attr = Attribute.valueOf(dataSet[0].toUpperCase());
                meta.put(attr, dataSet[1]);
            } catch (IllegalArgumentException ignored) {
                // If the attribute could not be found, continue to next pair
                System.out.println(pair + " does not have a locatable attribute!");
            }
        }
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

    public Map<Attribute, String> getMeta() {
        return meta;
    }

}
