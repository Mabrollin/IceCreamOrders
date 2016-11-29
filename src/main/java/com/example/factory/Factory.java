package com.example.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.example.factory.Machine.Routine;
import com.example.order.IceCreamOrder;
import com.example.products.Product;
import com.example.products.ProductList;
import com.example.products.ProductSpec;

//Singleton
//Simulates the Icecream factory and calculates/handles orders
public class Factory {
	private static OrderHandler instance = null;

	private Map<String, Machine> machines = new HashMap<String, Machine>();
	private ArrayList<IceCreamOrder> activeOrders = new ArrayList<IceCreamOrder>();
	private Map<IceCreamOrder, ArrayList<Routine>> orderRoutinesDependencies = new  HashMap<IceCreamOrder, ArrayList<Routine>>();
	private Map<Product, Integer> madeProducts = new HashMap<Product, Integer>();
	private Map<Product, Integer> inProgressProducts = new HashMap<Product, Integer>();

	protected Factory() {
		initMachines();
	}

	

	public static OrderHandler getInstance() {
		// create instance if not created
		if (instance == null)
			instance = new OrderHandler();

		return instance;
	}

	private void initMachines() {
		Machine newMachine = new Machine();
		newMachine.addRoutine(ProductList.products.get("Strawberry Cone"), 900000, 60);
		newMachine.addRoutine(ProductList.products.get("Lemon Cone"), 900000, 60);
		newMachine.addRoutine(ProductList.products.get("Mint Cone"), 900000, 60);
		addMachine("Fruit Cone Machine", newMachine);

		newMachine = new Machine();
		newMachine.addRoutine(ProductList.products.get("Chocolate Cone"), 1200000, 60);
		newMachine.addRoutine(ProductList.products.get("Vanilla Cone"), 1200000, 60);
		addMachine("Spice Cone Machine", newMachine);

		newMachine = new Machine();
		newMachine.addRoutine(ProductList.products.get("Cola Popsicle"), 600000, 100);
		newMachine.addRoutine(ProductList.products.get("Pear Popsicle"), 600000, 100);
		newMachine.addRoutine(ProductList.products.get("Raspberry Popsicle"), 600000, 100);
		newMachine.addRoutine(ProductList.products.get("Orange Popsicle"), 600000, 100);
		addMachine("Popsicle Machine", newMachine);

	}

	private void addMachine(String name, Machine newMachine) {
		machines.put(name, newMachine);

	}

	public void addOrder(IceCreamOrder iceCreamOrder) {
		activeOrders.add(iceCreamOrder);
		orderRoutinesDependencies.put(iceCreamOrder, new ArrayList<Routine>());
		handleOrder(iceCreamOrder);
		
	

	}

	private void handleOrder(IceCreamOrder iceCreamOrder) {
		for(ProductSpec productSpec : iceCreamOrder.getProducts()){
			if(madeProducts.containsKey(productSpec.getProduct())){
				
			}
		}
		
	}

	public long getEstimatedTime(ArrayList<ProductSpec> arrayList) {
		if(!activeOrders.contains(arrayList))
			return 0;
		else{
			return calcEstimatedTime(arrayList);
		}
		

	}

	private long calcEstimatedTime(IceCreamOrder iceCreamOrder) {
		if(!orderRoutinesDependencies.containsKey(iceCreamOrder))
			//should not be possible
			return 0;
		else{
			HashMap<Machine, Long> machineTimeQueue = new HashMap<Machine, Long>();
			for(Routine routine : orderRoutinesDependencies.get(iceCreamOrder)){
				if(machineTimeQueue.containsKey(routine.getMachine())){
					machineTimeQueue.put(routine.getMachine(), machineTimeQueue.get(routine.getMachine()) + routine.getExecutionTime());
				}
				else{
					machineTimeQueue.put(routine.getMachine(), routine.getExecutionTime());
				}
			}
			long longestTime = 0;
			for(Long time : machineTimeQueue.values()){
				if(time > longestTime)
					longestTime = time;
			}
			return longestTime;
		}
		
	}

	public void run() {
		try {
			while (true) {
				// to reduce weight
				Thread.sleep(100);
				fetchFromMachines();
				
			}
		} catch (InterruptedException e) {
				System.out.println("Closing thread: " + threadName);
		}

	}

	private void fetchFromMachines() {
		for (Machine machine : machines.values()){
			if(machine.hasNext()){
				Routine routine = machine.getRoutine();
				Product madeProduct = routine.getProduct(); 
				Integer createdAmount = routine.getAmount();
				if(madeProducts.containsKey(madeProduct)){
					madeProducts.put(madeProduct, madeProducts.get(madeProduct) + createdAmount);
					inProgressProducts.put(madeProduct, inProgressProducts.get(madeProduct) - createdAmount);
				}
				else{
					madeProducts.put(madeProduct, createdAmount);
					inProgressProducts.put(madeProduct, inProgressProducts.get(madeProduct) - createdAmount);
				}
			}
		}
		
	}

	public void addToProduction(ArrayList<ProductSpec> products) {
		// TODO Auto-generated method stub
		
	}

	public void addToProduction(ArrayList<ProductSpec> products) {
		// TODO Auto-generated method stub
		
	}



	public boolean hasProducts(ArrayList<ProductSpec> products) {
		// TODO Auto-generated method stub
		return false;
	}



	public void fetch(ArrayList<ProductSpec> products) {
		// TODO Auto-generated method stub
		
	}

}
