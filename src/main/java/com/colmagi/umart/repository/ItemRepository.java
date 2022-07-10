package com.colmagi.umart.repository;

import com.colmagi.umart.products.GroceryItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ItemRepository extends MongoRepository<GroceryItem, Integer> {

    @Query("{name:'?0'}")
    GroceryItem findItemByName(String name);

    @Query("{ 'name' : { $regex: ?0 } }")
    List<GroceryItem> findItemsByRegexpName(String regexp);

    // A Category is an Enum, but for the sake of searching, use Category#name()
    @Query(value="{category:'?0'}")
    List<GroceryItem> findAll(String categoryName);

    public long count();

}
