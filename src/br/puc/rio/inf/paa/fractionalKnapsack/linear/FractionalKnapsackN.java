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

		return knapsackRecursive(knapsack.items, 0, knapsack.items.length - 1, knapsack.capacity);

	}

	public Map<Item, Double> knapsackRecursive(Item[] items, int left, int right, double capacity) {
		
		double pivot = 0.0;

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

					if (capacity > items[right].weight) {
						itemsAdd.put(items[right], 1.0);
						capacity = capacity - items[right].weight;
					} else {
						itemsAdd.put(items[right], capacity / (items[right].weight));
						capacity = capacity - (capacity / (items[right].weight));
						break;
					}

					right--;

				}
			}
		} else {
			System.out.println(left);
			System.out.println(right);
			pivot = KnapsackUtil.medianOfMedians(items, left, right).ratio;
			int pos_p = KnapsackUtil.partition(items, pivot, left, right);

			int j = right;

			double current_weight = 0.0;

			while (j > pos_p && capacity > current_weight + items[j].weight) {

				current_weight = current_weight + items[j].weight;
				j--;
			}

			if (j > pos_p) {
				knapsackRecursive(items, pos_p + 1, right, capacity);
			} else {

				for (int i = right; i > pos_p; i--) {
					itemsAdd.put(items[i], 1.0);
				}
				capacity = capacity - current_weight;

				if (capacity > items[pos_p].weight) {
					itemsAdd.put(items[pos_p], 1.0);
					knapsackRecursive(items, left, pos_p - 1, capacity - items[pos_p].weight);

				} else {
					itemsAdd.put(items[pos_p], capacity / items[pos_p].weight);

				}

			}

		}

		return itemsAdd;

	}

}
