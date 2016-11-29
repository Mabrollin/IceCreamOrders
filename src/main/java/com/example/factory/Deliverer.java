package com.example.factory;

public class Deliverer {

	public Deliverer() {
	}

	public long getEstimatedTime(Object destination) {
		// mock 20 sec
		return 1000*20;
	}

}
