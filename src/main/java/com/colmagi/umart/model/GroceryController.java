package com.colmagi.umart.model;

import com.colmagi.umart.data.AbstractGroceryObject;
import com.colmagi.umart.data.GroceryUpdateObject;
import com.colmagi.umart.enums.Category;
import com.colmagi.umart.products.GroceryItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/grocery")
public class GroceryController {

    private final GroceryService groceryService;

    @Autowired
    public GroceryController(GroceryService groceryService) {
        this.groceryService = groceryService;
    }

    //GET request at api/v1/grocery, gets every item in the database
    @GetMapping
    public String getItems() {
        return groceryService.getGroceries().toString();
    }

    //GET request at api/v1/grocery, gets every item in the database that contains a given string in its name
    @GetMapping("/search/{input}")
    public String getItemsWithInput(@PathVariable String input) {
        return groceryService.findItems(input).toString();
    }

    //GET request at api/v1/grocery/id/{id}, gets a specific item by ID.
    @GetMapping("/id/{id}")
    public GroceryItem getItemById(@PathVariable String name) {
        return groceryService.getGroceryItemByName(name);
    }

    //GET request at api/v1/grocery/category/{id}, gets a specific item by ID.
    @GetMapping("/category/{cat}")
    public List<GroceryItem> getItemByCategory(@PathVariable Category cat) {
        return groceryService.getItemsByCategory(cat);
    }

    //POST request to add a new grocery item
    @PostMapping
    public void registerNewGrocery(@RequestBody AbstractGroceryObject item) {
        groceryService.addNewItem(item);
    }

    @PostMapping("/update")
    public void updateQuantity(@RequestBody GroceryUpdateObject item) {
        groceryService.changeQuantity(item);
    }

    //DELETE request, deletes a grocery item by ID.
    @DeleteMapping(path = "{groceryId}")
    public void deleteGrocery(@PathVariable("groceryId") int groceryId) {
        groceryService.deleteGrocery(groceryId);
    }
}
