package br.puc.rio.inf.paa.fractionalKnapsack.n2;

import java.util.HashMap;
import java.util.Map;

import br.puc.rio.inf.paa.fractionalKnapsack.FractionalKnapsackInstance;
import br.puc.rio.inf.paa.fractionalKnapsack.Item;
import br.puc.rio.inf.paa.utils.KnapsackUtil;

public class FractionalKnapsackN2 {

	private Map<Item, Double> itemsAdd;

	public FractionalKnapsackN2() {
		itemsAdd = new HashMap<>();
	}

	public Map<Item, Double> knapsack(FractionalKnapsackInstance knapsack) {

		return knapsackRecursive(knapsack.items, 0, knapsack.items.length - 1, knapsack.capacity, 0);

	}

	public Map<Item, Double> knapsackRecursive(Item[] items, int left, int right, double capacity,  double currentWeight) {


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
					
						currentWeight = currentWeight + items[right].weight;
						
			
					} else {
						//Colocar na mochila <=  peso disponivel / peso do objeto
						itemsAdd.put(items[right], ((capacity - currentWeight) / items[right].weight));
						currentWeight = currentWeight + ((capacity - currentWeight) / items[right].weight);
					
						break;
					}

					right--;

				}
			}
		} else {

			double pivot = 0.0;

			pivot = getPivot(items, left, right);
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
				// capacity = capacity - cw;
				currentWeight = currentWeight + cw;

				if (items[pos_p].weight + currentWeight <= capacity) {

					itemsAdd.put(items[pos_p], 1.0);

					currentWeight = currentWeight + items[pos_p].weight;

				
					knapsackRecursive(items, left, pos_p - 1, capacity, currentWeight);

				} else {
					itemsAdd.put(items[pos_p], ((capacity - currentWeight) / items[pos_p].weight));
					currentWeight = currentWeight + ((capacity - currentWeight) / items[pos_p].weight);
					

			
				}

			}

		}

		return itemsAdd;

	}

	

	private double getPivot(Item[] items, int left, int right) {
		int k = 0;
		double ratio_f = 0;
		for (int i = left; i <= right; i++) {
			ratio_f += items[i].ratio;
			k++;
		}
		return ratio_f / k;
	}

}
