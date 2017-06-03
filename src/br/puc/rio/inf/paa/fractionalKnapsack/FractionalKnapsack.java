package br.puc.rio.inf.paa.fractionalKnapsack;

import java.util.Arrays;
import java.util.List;

public class FractionalKnapsack {

	public double capacity;
	 public Item[] items;

	
	public FractionalKnapsack(double capacity, Item[] items) {

		this.capacity = capacity;
		this.items = items;
	}


	@Override
	public String toString() {
		return "FractionalKnapsack [capacity=" + capacity + ", items=" + Arrays.toString(items) + "]";
	}
	
	

}
