package com.example.products;

import com.example.order.IceCreamOrder;


public class ProductSpec {


	ProductSpec() { // jpa only
	}

	public ProductSpec(Product product, Long amount) {

		
		this.setProduct(product);
		this.setAmount(amount);
	}
	private Long amount;
	private Product product;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public String toString(){
		return product.getName() + " - " + amount + "x";
	}
	public boolean equals(Object other){
		return product.equals(((ProductSpec)other).getProduct());
		
	}

	public void addAmount(Long amount) {
		this.amount += amount;
		
	}

}