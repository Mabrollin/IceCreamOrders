package com.example.order;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.example.factory.OrderHandler;
import com.example.products.ProductSpec;


public class IceCreamOrder {

    private ArrayList<ProductSpec> productSpecs = new ArrayList<ProductSpec>();

    private Long id;

    public ArrayList<ProductSpec> getProducts() {
        return productSpecs;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
    
    public long getEstimatedTime(){
    	return OrderHandler.getInstance().getEstimatedTime(this);
    	//return new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime().getTime() - timeStamp.getTime());
    	
    }

    public String username;

	private Date timeStamp;

    public IceCreamOrder(String name) {
        this.username = name;
        timeStamp =  Calendar.getInstance().getTime();
        OrderHandler.getInstance().addOrder(this);
    }

	public void addProductSpec(ProductSpec newProductSpec) {
		for (ProductSpec spec : productSpecs){
			if(spec.equals(newProductSpec)){
				spec.addAmount(newProductSpec.getAmount());
				return;
			}
		}
		productSpecs.add(newProductSpec);
		
	}
	public String toString(){
		String returnString = username;
		returnString += "<br />";
		returnString += "------------------------";
		returnString += "<br />";
		returnString += getEstimatedTime();
		returnString += "<br />";
		returnString += "------------------------";
		returnString += "<br />";
		for(ProductSpec spec : productSpecs){
			returnString += spec.toString() + "<br />";
		}
		return returnString;
	}

	public Object getDestination() {
		// TODO Auto-generated method stub
		return null;
	}

}