package com.example.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.example.factory.Machine.Routine;
import com.example.order.IceCreamOrder;

//Singleton
//handles orders, merely a middle step between orders and the factory
public class OrderHandler implements Runnable {
	private Thread thread;
	private final String threadName = "factory thread";
	private static OrderHandler instance = null;

	private Map<IceCreamOrder, orderStatus> activeOrders = new HashMap<IceCreamOrder, orderStatus>();
	private Factory factory;
	private Deliverer deliverer;

	protected OrderHandler() {
		factory = new Factory();
		deliverer = new Deliverer();
		initThread();
	}

	private void initThread() {
		System.out.println("Starting " + threadName);
		if (thread == null) {
			thread = new Thread(this, threadName);
			thread.start();
		}
	}

	public static OrderHandler getInstance() {
		// create instance if not created
		if (instance == null)
			instance = new OrderHandler();

		return instance;
	}

	// adds order
	public void addOrder(IceCreamOrder iceCreamOrder) {
		activeOrders.put(iceCreamOrder, orderStatus.HANDLING);
		// no implemented "handling"

		addToProduction(iceCreamOrder, factory);
		activeOrders.put(iceCreamOrder, orderStatus.MAKING);

	}

	// adds the productspec to the factory's production
	private void addToProduction(IceCreamOrder iceCreamOrder, Factory factory) {
		factory.addToProduction(iceCreamOrder.getProducts());

	}

	// returns the Estimated time for the order to be completed
	public long getEstimatedTime(IceCreamOrder iceCreamOrder) {
		if (!activeOrders.containsKey(iceCreamOrder))
			return 0;
		else {
			long time = 0;
			switch (activeOrders.get(iceCreamOrder)) {
			case HANDLING:
				time += this.getEstimatedHandlingTime(iceCreamOrder);
			case MAKING:
				time += factory.getEstimatedTime(iceCreamOrder.getProducts());
			case DELIVERING:
				time += deliverer.getEstimatedTime(iceCreamOrder);
			default:
				break;

			}
			return time;

		}

	}

	//Estimated the time of handling (inspect, review, errorcheck etc)
	private long getEstimatedHandlingTime(IceCreamOrder iceCreamOrder) {
		// Mocking since this functionallity is not implemented
		return 0;
	}

	//Method from runnable interface. Runs on seperate Thread
	//The purpose is to run the factory and delivery in the background
	public void run() {
		try {
			while (true) {
				// to reduce weight
				Thread.sleep(100);
				factory.run();
				fetchFromFactory(factory);

			}
		} catch (InterruptedException e) {
			System.out.println("Closing thread: " + threadName);
		}

	}

	private void fetchFromFactory(Factory factory) {
		for (IceCreamOrder order : activeOrders.keySet()) {
			if (factory.hasProducts(order.getProducts())) {
				factory.fetch(order.getProducts());
			}
		}

	}

}
