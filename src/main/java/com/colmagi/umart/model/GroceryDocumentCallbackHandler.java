package com.colmagi.umart.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mongodb.MongoException;
import org.bson.Document;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.DocumentCallbackHandler;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class GroceryDocumentCallbackHandler implements DocumentCallbackHandler {

    JsonArray allGroceries;

    public GroceryDocumentCallbackHandler() {
        allGroceries = new JsonArray();
    }

    @Override
    public void processDocument(Document source) throws MongoException, DataAccessException {
        JsonObject groceryItem = new JsonObject();
        for (Map.Entry<String, Object> entry : source.entrySet()) {
            groceryItem.addProperty(entry.getKey(), entry.getValue().toString());
        }
        allGroceries.add(groceryItem);
    }
//        if (source.get("weight") == null) {
//            Clothes cloth = new Clothes(
//                    (Integer) source.get("id"),
//                    (String) source.get("name"),
//                    (Category) source.get("category"),
//                    (Integer) source.get("quantity"),
//                    (Double) source.get("price"),
//                    (String) source.get("size"),
//                    (String) source.get("sex")
//            );
//
//        } else {
//            Vegetable veggie = new Vegetable(
//                    (Integer) source.get("id"),
//                    (String) source.get("name"),
//                    (Category) source.get("category"),
//                    (Integer) source.get("quantity"),
//                    (Double) source.get("price"),
//                    (Double) source.get("weight")
//            );
//        }

    public JsonArray getAllGroceries() {
        return allGroceries;
    }
}