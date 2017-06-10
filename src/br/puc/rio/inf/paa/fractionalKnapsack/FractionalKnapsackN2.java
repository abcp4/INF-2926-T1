package br.puc.rio.inf.paa.fractionalKnapsack;

import java.util.HashMap;

import java.util.Map;

import br.puc.rio.inf.paa.utils.KnapsackUtil;

public class FractionalKnapsackN2 {

	private Map<Item, Double> itemsAdd;

	public FractionalKnapsackN2() {
		itemsAdd = new HashMap<Item, Double>();
	}

	public Map<Item, Double> knapsack(FractionalKnapsackInstance knapsack) {
		return knapsackRecursive(knapsack.items, 0, knapsack.items.length - 1, knapsack.capacity);
	}

	public Map<Item, Double> knapsackRecursive(Item[] items, int start, int end, double capacity) {

		if (start == end) {
			if (items[start].weight > capacity) {
				itemsAdd.put(items[start], capacity / items[start].weight);

			} else {
				itemsAdd.put(items[start], items[start].weight);
			}

			return itemsAdd;

		} else {
			// calcula-se media do valor/peso e particiona-se ao redor desse
			// valor

			int indexPivot = KnapsackUtil.getPivot(items, start, end);

			int post_p = partition(items, start, end, indexPivot);
	
			double sumWeight = 0;

			for (int i = start; i < post_p; i++) {
				sumWeight = sumWeight + items[i].weight;
			}
			
			if (sumWeight > capacity) {

				knapsackRecursive(items, start, post_p -1, capacity);

				// se cabe tudo, poe metade mais valiosa na mochila e repete-se
				// algoritmo na
				// metade menos valiosa
			} else {
				for (int i = start; i < post_p; i++) {
					itemsAdd.put(items[i], items[i].weight);
				}

				capacity = capacity - sumWeight;

				if (items[post_p].weight <= capacity) {
					itemsAdd.put(items[post_p], items[post_p].weight);
				} else {

					itemsAdd.put(items[post_p], capacity / items[post_p].weight);
					knapsackRecursive(items, post_p + 1, end, capacity);
				}

			}
		}

		return itemsAdd;

	}

	public int partition(Item[] array, int left, int right, int pivotIndex) {
		Item pivotValue = array[pivotIndex];
		swap(array, pivotIndex, right); // move pivot to end
		int storeIndex = left;
		for (int i = left; i < right; i++) {
			if (array[i].ratio > pivotValue.ratio) {
				swap(array, storeIndex, i);
				storeIndex++;
			}
		}
		swap(array, right, storeIndex); // Move pivot to its final place
		return storeIndex;
	}

	private void swap(Item[] array, int a, int b) {
		Item tmp = array[a];
		array[a] = array[b];
		array[b] = tmp;
	}

}

