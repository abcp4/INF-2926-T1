package br.puc.rio.inf.paa.fractionalKnapsack.nlogn;

import java.util.HashMap;
import java.util.Map;

import br.puc.rio.inf.paa.fractionalKnapsack.FractionalKnapsackInstance;
import br.puc.rio.inf.paa.fractionalKnapsack.Item;
import br.puc.rio.inf.paa.utils.KnapsackUtil;

public class FractionalKnapsackNlogN {

	public Map<Item, Double> knapsack(FractionalKnapsackInstance knapsack) {

		int n = knapsack.items.length;

		KnapsackUtil.mergeSort(knapsack.items, 0, n - 1);
		
		for (int i = 0; i < knapsack.items.length; i++) {
			System.out.println(knapsack.items[i].toString());
		}
   
		double current_weight = 0;

		Map<Item, Double> itemsAdd = new HashMap<Item, Double>();

		int j = 0;

		while (current_weight < knapsack.capacity && j < n) {

			if (knapsack.items[j].weight + current_weight <= knapsack.capacity) {

				itemsAdd.put(knapsack.items[j], 1.0);

				current_weight = current_weight + knapsack.items[j].weight;

			} else {

				itemsAdd.put(knapsack.items[j], (current_weight / knapsack.items[j].weight));

				current_weight = current_weight + (current_weight / knapsack.items[j].weight);
			}

			j++;
		}
	System.out.println("PESO: " + current_weight);
		return itemsAdd;

	}

}
