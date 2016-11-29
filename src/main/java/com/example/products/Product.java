package com.example.products;

public class Product {

	public Product(String name,String description, int price) {
		this.name = name;
		this.description = description;
		this.price = price;
		
	}

	private String name;
	private String description;
	private int price;


	public String getDescription() {
		return description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean equals(Object other){
		if(!(other instanceof Product))
			return false;
		return name.equals(((Product)other).name);
		
	}
}