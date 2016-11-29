package com.example.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.order.IceCreamOrder;
import com.example.products.ProductList;
import com.example.products.ProductSpec;

@RestController
@RequestMapping("/order/{userId}")
class ProductRestController {

	private Map<String, IceCreamOrder> orders = new HashMap<String, IceCreamOrder>();

	@RequestMapping(value = "/add/{productName}/{amount}", method = RequestMethod.GET)
	String add(@PathVariable String userId, @PathVariable String productName, @PathVariable Long amount) {
		if(!this.validateProduct(productName)){
			System.out.println("No product '" + productName + "'");
			return "No product '" + productName + "'";
			}
		if(!this.validateAmount(amount)){
			System.out.println("Not a number '" + amount + "'");
			return "Not a number '" + amount + "'";
			}
		
		this.validateUser(userId);
		ProductSpec newProductSpec = new ProductSpec(ProductList.products.get(productName), amount);
		orders.get(userId).addProductSpec(newProductSpec);
		return "added " + amount + " " + productName + " to " + userId + "'s order.";

	}

	private boolean validateAmount(Long amount) {
		// TODO Auto-generated method stub
		return true;
	}

	@RequestMapping(value = "/time", method = RequestMethod.GET)
	String time(@PathVariable String userId) {
		this.validateUser(userId);
		return "" + this.orders.get(userId).getEstimatedTime();
	}

	@RequestMapping(method = RequestMethod.GET)
	String readProducts(@PathVariable String userId) {
		this.validateUser(userId);
		return this.orders.get(userId).toString();
	}


	private void validateUser(String userId) {
		if(!orders.containsKey(userId))
			orders.put(userId, new IceCreamOrder(userId));
			
	}

	private boolean validateAmount(String amount) {

		if(amount.isEmpty()) 
			return false;
	    for(int i = 0; i < amount.length(); i++) {
	        if(Character.digit(amount.charAt(i),10) < 0) return false;
	    }
	    return true;
	}

	private boolean validateProduct(String productName) {
		return ProductList.products.containsKey(productName);

	}
}

@ResponseStatus(HttpStatus.NOT_FOUND)
class UserNotFoundException extends RuntimeException {

	public UserNotFoundException(String userId) {
		super("could not find user '" + userId + "'.");
	}
}