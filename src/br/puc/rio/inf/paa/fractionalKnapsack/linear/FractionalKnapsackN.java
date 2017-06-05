package br.puc.rio.inf.paa.fractionalKnapsack.linear;

import java.util.HashMap;
import java.util.Map;

import br.puc.rio.inf.paa.fractionalKnapsack.FractionalKnapsackInstance;
import br.puc.rio.inf.paa.fractionalKnapsack.Item;
import br.puc.rio.inf.paa.utils.KnapsackUtil;

public class FractionalKnapsackN {

	private Map<Item, Double> itemsAdd;

	public FractionalKnapsackN() {
		itemsAdd = new HashMap<>();
	}

	public Map<Item, Double> knapsack(FractionalKnapsackInstance knapsack) {

		return knapsackRecursive(knapsack.items, 0, knapsack.items.length - 1, knapsack.capacity, 0);

	}

	public Map<Item, Double> knapsackRecursive(Item[] items, int left, int right, double capacity, double currentWeight) {

		if (right - left <= 1) {

			if (right >= 0 && left < items.length) {

				// order left and right items
				if (items[left].ratio > items[right].ratio) {

					Item aux = items[right];
					items[right] = items[left];
					items[left] = aux;
				}
				// get items to knapsack
				while (right >= left) {

					if (items[right].weight + currentWeight <= capacity) {
						itemsAdd.put(items[right], 1.0);
						//capacity = capacity - items[right].weight;
						
						currentWeight = currentWeight + items[right].weight;
						//System.out.println(items[right].weight + " " + currentWeight);
						

					} else {
						if((currentWeight/items[right].weight) + currentWeight < capacity){
							itemsAdd.put(items[right], (currentWeight / items[right].weight));
							currentWeight = currentWeight + (currentWeight/items[right].weight);
						}else{
							
							double spaceHired = capacity - currentWeight;
							itemsAdd.put(items[right], (currentWeight / items[right].weight) - spaceHired);
							currentWeight = currentWeight + ((currentWeight/items[right].weight) - spaceHired);
						}
						
						
						//capacity = capacity - (currentWeight / items[right].weight);
						//System.out.println(items[right].weight + " " + currentWeight);
						break;
					}

					right--;

				}
			}
		} else {

			double pivot = 0.0;

			pivot = KnapsackUtil.medianOfMedians(items, left, right).ratio;
			int pos_p = KnapsackUtil.partition(items, pivot, left, right);
			int j = right;

			double cw = 0.0;

			while (j > pos_p && capacity - currentWeight > cw + items[j].weight) {

				cw = cw + items[j].weight;
				j--;
			}

			if (j > pos_p) {
				knapsackRecursive(items, pos_p + 1, right, capacity, currentWeight);
			} else {

				for (int i = right; i > pos_p; i--) {
					itemsAdd.put(items[i], 1.0);
				}
				//capacity = capacity - cw;
				currentWeight = currentWeight + cw;

				if (items[pos_p].weight + currentWeight <= capacity) {
					
					itemsAdd.put(items[pos_p], 1.0);
					
					currentWeight = currentWeight + items[pos_p].weight;
					
				//	System.out.println(items[pos_p] + " " + currentWeight);
					//capacity = capacity - items[pos_p].weight;
					
					knapsackRecursive(items, left, pos_p - 1, capacity, currentWeight);

				} else {
					itemsAdd.put(items[pos_p], currentWeight / items[pos_p].weight);
					
					currentWeight = currentWeight + (currentWeight / items[pos_p].weight);
					
					
					//System.out.println(items[pos_p] + " " + currentWeight);
					//knapsackRecursive(items, left, pos_p - 1, capacity, currentWeight);
				}

			}

		}

		return itemsAdd;

	}

}
