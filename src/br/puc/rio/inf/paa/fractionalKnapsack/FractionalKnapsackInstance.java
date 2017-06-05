package br.puc.rio.inf.paa.fractionalKnapsack;

import java.util.Arrays;

public class FractionalKnapsackInstance {

	public double capacity;
	public Item[] items;

	public FractionalKnapsackInstance(double capacity, Item[] items) {

		this.capacity = capacity;
		this.items = items;
	}

	@Override
	public String toString() {
		return "FractionalKnapsack [capacity=" + capacity + ", items=" + Arrays.toString(items) + "]";
	}

}
