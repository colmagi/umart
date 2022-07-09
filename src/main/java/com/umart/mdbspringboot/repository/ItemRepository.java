package com.umart.mdbspringboot.repository;

import com.umart.mdbspringboot.data.Category;
import com.umart.mdbspringboot.model.GroceryItem;
import com.umart.mdbspringboot.model.AbstractGroceryObject;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
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
