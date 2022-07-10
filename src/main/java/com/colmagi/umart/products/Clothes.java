package com.colmagi.umart.products;

import com.colmagi.umart.enums.Attribute;
import com.colmagi.umart.enums.Category;
import com.colmagi.umart.data.AbstractGroceryObject;
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
