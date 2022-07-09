package com.umart.mdbspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableMongoRepositories
@RestController
public class MdbSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(MdbSpringBootApplication.class, args);
	}

}





	//CREATE
//	void createGroceryItems() {
//		System.out.println("Data creation started...");
//		groceryItemRepo.save(new GroceryItem("Whole Wheat Biscuit", "Whole Wheat Biscuit", 5, "snacks"));
//		groceryItemRepo.save(new GroceryItem("Kodo Millet", "XYZ Kodo Millet healthy", 2, "millets"));
//		groceryItemRepo.save(new GroceryItem("Dried Red Chilli", "Dried Whole Red Chilli", 2, "spices"));
//		groceryItemRepo.save(new GroceryItem("Pearl Millet", "Healthy Pearl Millet", 1, "millets"));
//		groceryItemRepo.save(new GroceryItem("Cheese Crackers", "Bonny Cheese Crackers Plain", 6, "snacks"));
//		System.out.println("Data creation complete...");
//	}
/*
	// READ
	// 1. Show all the data
	public void showAllGroceryItems() {
		groceryItemRepo.findAll().forEach(item -> System.out.println(getItemDetails(item)));
	}

	// 2. Get item by name
	public void getGroceryItemByName(String name) {
		System.out.println("Getting item by name: " + name);
		Optional<GroceryItem> opt = Optional.ofNullable(groceryItemRepo.findItemByName(name));
		if (opt.isPresent()) {
			System.out.println(getItemDetails(opt.get()));
		}
		else {
			System.out.println("Item not found.");
		}
	}

	public void changeQuantity(String name, int newQuantity) {
		Optional<GroceryItem> opt = Optional.ofNullable(groceryItemRepo.findItemByName(name));
		if (opt.isPresent()) {
			Query query1 = new Query(Criteria.where("id").is(name));
			Update update1 = new Update();
			update1.set("quantity", newQuantity);
			groceryTemplate.updateFirst(query1, update1, GroceryItem.class);
		}
		else {
			System.out.println("Item not found.");
		}
	}

	// 3. Get name and quantity of all items of a particular category
	public void getItemsByCategory(String category) {
		System.out.println("Getting items for the category " + category);
		List<GroceryItem> list = groceryItemRepo.findAll(category);
		if (list.isEmpty()) {
			System.out.println("Nothing found for this category.");
		}
		else {
			list.forEach(item -> getItemDetails(item));
		}
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
	public void run(String... args) {
		boolean cont = true;
		while(cont) {
			System.out.println("Please indicate the action you would like to take.");
			Scanner scan = new Scanner(System.in);
			int input = scan.nextInt();
			scan.nextLine();
			switch(input) {
				case 1:
					showAllGroceryItems();
					break;
				case 2:
					System.out.println("Please type the name of the item you wish to get.");
					String query = scan.nextLine();
					getGroceryItemByName(query);
					break;
				case 3:
					System.out.println("Please type the category you wish to get.");
					query = scan.nextLine();
					getItemsByCategory(query);
					break;
				case 4:
					System.out.println("Please input the name of the item you wish to add.");
					String name = scan.nextLine();
					System.out.println("Please input the quantity of the item you wish to add.");
					int quantity = scan.nextInt();
					scan.nextLine();
					System.out.println("Please input the category of the item you wish to add. 1 for Vegetable, 2 for Clothes");
					int cat = scan.nextInt();
					scan.nextLine();
					System.out.println("Please input the price of the item you wish to add.");
					double price = scan.nextDouble();
					scan.nextLine();
					if (cat == 1) {
						System.out.println("Please input the weight of the item you wish to add.");
						double weight = scan.nextDouble();
						groceryItemRepo.save(new Vegetable(name, name, quantity, "Vegetable", price, weight));
					}
					else if (cat == 2) {
						System.out.println("Please input the size of the item you wish to add.");
						String size = scan.nextLine();
						groceryItemRepo.save(new Clothes(name, name, quantity, "Clothes", price, size, true));
					}
					else {
						System.out.println("invalid category.");
						break;
					}
					break;
				case 5:
					System.out.println("Please input the name of the item you wish to delete.");
					name = scan.nextLine();
					deleteGroceryItem(name);
					break;
				case 6:
					findCountOfGroceryItems();
					break;
				case 7:
					System.out.println("Deleting all....");
					groceryItemRepo.deleteAll();
					break;
				case 8:
					System.out.println("Please type the item you wish to update.");
					query = scan.nextLine();
					System.out.println("Please input the new quantity.");
					int newQuantity = scan.nextInt();
					scan.nextLine();
					changeQuantity(query, newQuantity);
					break;
				default:
					cont = false;
					break;
			}
		}
		System.exit(0);
//		System.out.println("-------------CREATE GROCERY ITEMS-------------------------------\n");
//
//		createGroceryItems();
//
//		System.out.println("\n----------------SHOW ALL GROCERY ITEMS---------------------------\n");
//
//		showAllGroceryItems();
//
//		System.out.println("\n--------------GET ITEM BY NAME-----------------------------------\n");
//
//		getGroceryItemByName("Whole Wheat Biscuit");
//
//		System.out.println("\n-----------GET ITEMS BY CATEGORY---------------------------------\n");
//
//		getItemsByCategory("millets");
//
//		System.out.println("\n-----------UPDATE CATEGORY NAME OF SNACKS CATEGORY----------------\n");
//
//		updateCategoryName("snacks");
//
//		System.out.println("\n----------DELETE A GROCERY ITEM----------------------------------\n");
//
//		deleteGroceryItem("Kodo Millet");
//
//		System.out.println("\n------------FINAL COUNT OF GROCERY ITEMS-------------------------\n");
//
//		findCountOfGroceryItems();
//
//		System.out.println("\n-------------------THANK YOU---------------------------");

	}
}

 */
