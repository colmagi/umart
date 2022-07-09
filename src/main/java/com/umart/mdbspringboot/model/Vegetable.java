package com.umart.mdbspringboot.model;

import com.umart.mdbspringboot.data.Attribute;
import com.umart.mdbspringboot.data.Category;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("groceryitems")
public class Vegetable extends GroceryItem {

    private final double weight;

    // Used for dumping data, avoid instantiating using this constructor
    public Vegetable(int id, String name, Category cat, int quantity, double price, double weight) {
        super(id, name, cat, quantity, price);
        this.weight = weight;
    }

    public Vegetable(AbstractGroceryObject ago) {
        super(ago);
        // Get the weight from meta and parse double
        this.weight = Double.parseDouble(ago.getMeta().get(Attribute.WEIGHT));
    }

    public double getWeight() {
        return weight;
    }
}
