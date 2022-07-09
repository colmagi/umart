package com.umart.mdbspringboot.model;

import com.umart.mdbspringboot.data.Attribute;
import com.umart.mdbspringboot.data.Category;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("groceryitems")
public class Clothes extends GroceryItem {
    public String size;
    public String sex;

    public Clothes(int id, String name, Category cat, int quantity, double price, String size, String sex) {
        super(id, name, cat, quantity, price);
        this.size = size;
        this.sex = sex;
    }

    public Clothes(AbstractGroceryObject ago) {
        super(ago);
        // Get the weight from meta and parse double
        this.size = ago.getMeta().get(Attribute.SIZE);
        this.sex = ago.getMeta().get(Attribute.SEX);
    }

    public String getSize() {
        return size;
    }

    public String getSex() {
        return sex;
    }

}
