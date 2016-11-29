package com.example.products;

import java.util.HashMap;

public final class ProductList {

	public static final HashMap<String, Product> products;
	static{
		products = new HashMap<String, Product>();
		products.put("Strawberry Cone", new CountlineIceCream("Raspberry Cone", "soft ice cream cone with raspberry flavor", 16, "strawberry"));
		products.put("Lemon Cone", new CountlineIceCream("Lemon Cone", "soft ice cream cone with raspberry flavor", 16, "lemon"));
		products.put("Chocolate cone", new CountlineIceCream("Chocolate cone", "soft ice cream cone with raspberry flavor", 18, "chocolate"));
		products.put("Vanilla Cone", new CountlineIceCream("Vanilla Cone", "soft ice cream cone with raspberry flavor", 18, "vanilla"));
		products.put("Mint Cone", new CountlineIceCream("Mint Cone", "soft ice cream cone with raspberry flavor", 18, "mint"));
		products.put("Cola Popsicle", new CountlineIceCream("Cola Popsicle", "popsicle with cola flavor", 8, "cola"));
		products.put("Pear Popsicle", new CountlineIceCream("Pear Popsicle", "popsicle with pear flavor", 6, "pear"));
		products.put("Raspberry Popsicle", new CountlineIceCream("Raspberry Popsicle", "popsicle with raspberry flavor", 6, "raspberry"));
		products.put("Orange Popsicle", new CountlineIceCream("Orange Popsicle", "popsicle with orange flavor", 8, "orange"));
		
	}
	
	
	

}
