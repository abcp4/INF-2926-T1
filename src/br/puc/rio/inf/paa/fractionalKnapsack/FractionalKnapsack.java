package br.puc.rio.inf.paa.fractionalKnapsack;

import java.util.ArrayList;
import java.util.List;

public class FractionalKnapsack {
	
	public double capacity;
	public List<Item> selectedItems;
	
	
	
	public FractionalKnapsack(double capacity){
		this.capacity = capacity;
		this.selectedItems = new ArrayList<>();
	}

}
