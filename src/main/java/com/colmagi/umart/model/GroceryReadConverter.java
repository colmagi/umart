package com.colmagi.umart.model;

import com.colmagi.umart.enums.Category;
import com.colmagi.umart.products.Clothes;
import com.colmagi.umart.products.GroceryItem;
import com.colmagi.umart.products.Vegetable;
import org.bson.Document;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Component;

@Component
@ReadingConverter
public class GroceryReadConverter implements Converter<Document, GroceryItem> {
    @Override
    public GroceryItem convert(Document source) {
        if(source.get("weight") == null) {
            Clothes cloth = new Clothes(
                    (Integer) source.get("id"),
                    (String) source.get("name"),
                    (Category) source.get("category"),
                    (Integer) source.get("quantity"),
                    (Double) source.get("price"),
                    (String) source.get("size"),
                    (String) source.get("sex")
            );
            return cloth;
        }
        else {
            Vegetable veggie = new Vegetable (
                    (Integer) source.get("id"),
                    (String) source.get("name"),
                    (Category) source.get("category"),
                    (Integer) source.get("quantity"),
                    (Double) source.get("price"),
                    (Double) source.get("weight")
            );
            return veggie;
        }
    }
}
