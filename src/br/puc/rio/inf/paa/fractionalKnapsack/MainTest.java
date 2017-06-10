package br.puc.rio.inf.paa.fractionalKnapsack;

import java.util.Map;
import java.util.Map.Entry;

public class MainTest {

	public static void main(String[] args) {

		Item[] items = new Item[8];

		for (int i = 0; i < items.length; i++) {
			items[i] = new Item(i + 1, 30, 6);
		}

		items[0].setWeight(5);
		items[1].setWeight(5);
		items[2].setWeight(10); 
		items[3].setWeight(100);
		items[4].setWeight(76);
		items[5].setWeight(43);
		items[6].setWeight(60);
		items[7].setWeight(22);

		FractionalKnapsackInstance knapsack = new FractionalKnapsackInstance(15.0, items);		
		FractionalKnapsackN2 knapsackN = new FractionalKnapsackN2();
		Map<Item, Double> map = knapsackN.knapsack(knapsack);
		 
		double soma = 0;
		
	    for (Entry<Item, Double> entry: map.entrySet()) {
	    	System.out.println(entry.getKey().id + " " + entry.getValue());
	    	soma = soma + entry.getValue();
			
		}
		System.out.println("SOMA: " + soma);

	}
}
