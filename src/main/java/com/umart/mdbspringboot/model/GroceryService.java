package com.umart.mdbspringboot.model;

import com.umart.mdbspringboot.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@EnableMongoRepositories
public class GroceryService implements CommandLineRunner {

    private final ItemRepository groceryItemRepo;
    private final MongoTemplate groceryTemplate;

    @Autowired
    public GroceryService(ItemRepository groceryItemRepo, MongoTemplate groceryTemplate) {
        this.groceryItemRepo = groceryItemRepo;
        this.groceryTemplate = groceryTemplate;
    }
    //Finds every grocery item in the list
    public List<GroceryItem> getGroceries() {
        return groceryItemRepo.findAll();
    }

    //Adds a new item to the grocery list
    public void addNewItem(GroceryItem item) {
        Optional<GroceryItem> optionalGroceryItem = Optional.ofNullable(groceryItemRepo.findItemByName(item.getName()));
        if (optionalGroceryItem.isPresent()) {
            changeQuantity(item.getName(), item.getQuantity());
            //throw new IllegalStateException("this item already exists!");
        }
        else {
            groceryItemRepo.save(item);
        }
        System.out.println(item);
    }

    //Deletes an item from the grocery list
    public void deleteGrocery(String groceryId) {
        boolean exists = groceryItemRepo.existsById(groceryId);
        if (!exists) {
            throw new IllegalStateException("grocery with id " + groceryId + "does not exist.");
        }
        groceryItemRepo.deleteById(groceryId);
    }

    //Finds a grocery item by ID
    public GroceryItem getGroceryItemByName(String name) {
        System.out.println("Getting item by name: " + name);
        Optional<GroceryItem> opt = Optional.ofNullable(groceryItemRepo.findItemByName(name));
        if (opt.isPresent()) {
            return opt.get();
        }
        else {
            throw new IllegalStateException("Item not found.");
        }
    }

    //Changes the quantity of a given grocery item
    public void changeQuantity(String name, int newQuantity) {
        Query query1 = new Query(Criteria.where("id").is(name));
        Update update1 = new Update();
        update1.set("quantity", newQuantity);
        groceryTemplate.updateFirst(query1, update1, GroceryItem.class);
    }

    //Changes the price of a given grocery item
    public void changePrice(String name, double newPrice) {
        Query query1 = new Query(Criteria.where("id").is(name));
        Update update1 = new Update();
        update1.set("price", newPrice);
        groceryTemplate.updateFirst(query1, update1, GroceryItem.class);
    }

    // 3. Get name and quantity of all items of a particular category
    public List<GroceryItem> getItemsByCategory(String category) {
        System.out.println("Getting items for the category " + category);
        List<GroceryItem> list = groceryItemRepo.findAll(category);
        return list;
    }

    // 4. Get count of documents in the collection
    public void findCountOfGroceryItems() {
        long count = groceryItemRepo.count();
        System.out.println("Number of documents in the collection: " + count);
    }

    public String getItemDetails(GroceryItem item) {
        System.out.println(
                "Item Name: " + item.getName() + ", \nQuantity: " + item.getQuantity() +
                        ", \nItem Category: " + item.getCategory() + ", \nPrice: " + item.getPrice() + ",");
        if (item instanceof Vegetable) {
            Vegetable temp = (Vegetable) item;
            System.out.println("Item Weight: " + temp.getWeight());
        }
        else if (item instanceof Clothes) {
            Clothes temp = (Clothes) item;
            System.out.println("Item Size: " + temp.getSize() + ", ");
            if(temp.getSex() == true) {
                System.out.println("Item Sex: Male");
            }
            else {
                System.out.println("Item Sex: Female");
            }
        }
        else {
            //do nothing...
        }
        return "";
    }

    public void updateCategoryName(String category) {

        // Change to this new value
        String newCategory = "munchies";

        // Find all the items with the category snacks
        List<GroceryItem> list = groceryItemRepo.findAll(category);

        list.forEach(item -> {
            // Update the category in each document
            item.setCategory(newCategory);
        });

        // Save all the items in database
        List<GroceryItem> itemsUpdated = groceryItemRepo.saveAll(list);

        if(itemsUpdated != null)
            System.out.println("Successfully updated " + itemsUpdated.size() + " items.");
    }

    // DELETE
    public void deleteGroceryItem(String id) {
        groceryItemRepo.deleteById(id);
        System.out.println("Item with id " + id + " deleted...");
    }

    @Override
    public void run(String... args) throws Exception {

    }
}