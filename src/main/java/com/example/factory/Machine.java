package com.example.factory;

import java.util.ArrayList;

import com.example.products.Product;

public class Machine {

	private ArrayList<Routine> routines;
	private Routine routineInProgress;
	private long start;

	public Machine() {
		routines = new ArrayList<Routine>();
	}

	public class Routine {
		private Product product;
		// time in millesec
		private long executionTime;
		private int amount;
		private Machine machine;

		public Routine(Product product, long executionTime, int amount, Machine machine) {
			this.setProduct(product);
			this.setExecutionTime(executionTime);
			this.setAmount(amount);
			this.setMachine(machine);
		}

		public Product getProduct() {
			return product;
		}

		public void setProduct(Product product) {
			this.product = product;
		}

		public long getExecutionTime() {
			return executionTime;
		}

		public void setExecutionTime(long executionTime) {
			this.executionTime = executionTime;
		}

		public int getAmount() {
			return amount;
		}

		public void setAmount(int amount) {
			this.amount = amount;
		}

		public Machine getMachine() {
			return machine;
		}

		public void setMachine(Machine machine) {
			this.machine = machine;
		}

		

	}

	// adds routine by argument for routine constructor
	public void addRoutine(Product product, long executionTime, int ammount) {
		routines.add(new Routine(product, executionTime, ammount, this));
	}

	// removes routine from ArrayList by index
	// index must exist or nothing will happen
	public void removeRoutine(int index) {
		if (index < routines.size() && index >= 0)
			routines.remove(index);
	}

	// starts a routine (select a routine and create a timestamp)
	// index must exist or nothing will happen
	// not that this method will now overwrite other "start", either waint for
	// it to finnish or use "abort()"
	public void start(int index) {
		if (!isRunning())
			if (index < routines.size() && index >= 0) {
				routineInProgress = routines.get(index);
				start = System.currentTimeMillis();
			}
	}

	private boolean isRunning() {
		try{
		if (start != -1 && System.currentTimeMillis() - start > routineInProgress.getExecutionTime())
			return true;
		else
			return false;
		}
		catch(NullPointerException e){
			return false;
		}

	}

	// returns ammount of current routine
	public Routine getRoutine() {
		if (hasNext()) {
			Routine returnRoutine = routineInProgress;
			routineInProgress = null;
			return  returnRoutine;
		} else
			return null;

	}

	public boolean hasNext() {
		if (!isRunning() && routineInProgress != null)
			return true;
		else
			return false;
	}
}
