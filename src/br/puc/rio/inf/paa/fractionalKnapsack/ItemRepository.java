package br.puc.rio.inf.paa.fractionalKnapsack;

import java.util.Arrays;

public class ItemRepository {
	
	public Item[] items;

	public ItemRepository(Item[] items) {
		
		this.items = items;
	}

	@Override
	public String toString() {
		return "FractionalKnapsack [items=" + Arrays.toString(items) + "]";
	}

}
