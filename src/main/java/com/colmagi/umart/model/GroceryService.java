package com.colmagi.umart.model;

import com.colmagi.umart.data.AbstractGroceryObject;
import com.colmagi.umart.data.GroceryUpdateObject;
import com.colmagi.umart.products.Clothes;
import com.colmagi.umart.products.GroceryItem;
import com.colmagi.umart.products.Vegetable;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.colmagi.umart.enums.Category;
import com.colmagi.umart.repository.ItemRepository;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@EnableMongoRepositories
public class GroceryService implements CommandLineRunner {

    private final ItemRepository groceryItemRepo;
    private final MongoTemplate groceryTemplate;
    private GroceryDocumentCallbackHandler groceryHandler;

    @Autowired
    public GroceryService(ItemRepository groceryItemRepo, MongoTemplate groceryTemplate, GroceryDocumentCallbackHandler groceryHandler) {
        this.groceryItemRepo = groceryItemRepo;
        this.groceryTemplate = groceryTemplate;
        this.groceryHandler = groceryHandler;
    }

    //Finds every grocery item in the list
    public JsonArray findItems(String input) {
        groceryHandler = new GroceryDocumentCallbackHandler();
        JsonArray allGroceries = new JsonArray();
        String match = "^" + input + ".*$";
        Pattern pattern = Pattern.compile(match, Pattern.CASE_INSENSITIVE);
        System.out.println(match);
        Query query1 = new Query(Criteria.where("name").regex(pattern));
        groceryTemplate.executeQuery(query1, "groceryitems", groceryHandler);
        return groceryHandler.getAllGroceries();
        //return groceryItemRepo.findItemsByRegexpName(input);
    }
    //Finds every grocery item in the list
    public JsonArray getGroceries() {
        JsonArray allGroceries = new JsonArray();
        for (Document doc : groceryTemplate.getCollection("groceryitems").find()) {
            JsonObject groceryItem = new JsonObject();
            for (Map.Entry<String, Object> entry : doc.entrySet()) {
                groceryItem.addProperty(entry.getKey(), entry.getValue().toString());
            }
            allGroceries.add(groceryItem);
        }
        return allGroceries;
    }


    //Adds a new item to the grocery list
    public void addNewItem(AbstractGroceryObject item) {
            GroceryItem exactItem;
            switch(item.getCategory()) {
                case VEGETABLE:
                    exactItem = new Vegetable(item);
                    break;
                case CLOTHES:
                    exactItem = new Clothes(item);
                    break;
                default:
                    System.out.println(item.getName() + " did not fall into a valid category! Skipping...");
                    return;
            }
            groceryItemRepo.save(exactItem);
            System.out.println(item);
    }

    //Deletes an item from the grocery list
    public void deleteGrocery(int groceryId) {
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
    public void changeQuantity(GroceryUpdateObject item) {
        Query query1 = new Query(Criteria.where("_id").is(item.getId()));
        Update update1 = new Update();
        update1.set("quantity", item.getQuantity());
        groceryTemplate.updateFirst(query1, update1, "groceryitems");
    }

    //Changes the price of a given grocery item
    public void changePrice(String name, double newPrice) {
        Query query1 = new Query(Criteria.where("id").is(name));
        Update update1 = new Update();
        update1.set("price", newPrice);
        groceryTemplate.updateFirst(query1, update1, GroceryItem.class);
    }

    // 3. Get name and quantity of all items of a particular category
    public List<GroceryItem> getItemsByCategory(Category category) {
        System.out.println("Getting items for the category " + category);
        List<GroceryItem> list = groceryItemRepo.findAll(category.name());
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
            if(temp.getSex().equals("M")) {
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

    public void updateCategory(Category category) {

        // Change to this new value
        Category newCategory = Category.FOOD;

        // Find all the items with the category snacks
        List<GroceryItem> list = groceryItemRepo.findAll(category.name());

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
    public void deleteGroceryItem(int id) {
        groceryItemRepo.deleteById(id);
        System.out.println("Item with id " + id + " deleted...");
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
