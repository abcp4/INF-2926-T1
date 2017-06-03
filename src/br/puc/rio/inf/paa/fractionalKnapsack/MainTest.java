package br.puc.rio.inf.paa.fractionalKnapsack;

import java.util.Map;


public class MainTest {
	
	public static void main(String[] args) {
		
		
		Item[] items = new Item[5];
		
		for (int i = 0; i < items.length; i++) {
			items[i] = new Item(i, i+1, 12, 0.0);
		}
		
		
		for (int i = 0; i < items.length; i++) {
		System.out.println(items[i].value);
		}
		
		
		FractionalKnapsack knapsack = new FractionalKnapsack(15.0, items);
		
		FractionalKnapsackNlogN knapsackN = new FractionalKnapsackNlogN();
	
		Map<Item, Double> map = knapsackN.knapsack(knapsack);
	
		map.entrySet().forEach( entry-> {
			System.out.println(entry.getKey().id + " " + entry.getValue());
		});
	
	}

}
