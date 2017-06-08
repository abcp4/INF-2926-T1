package br.puc.rio.inf.paa.fractionalKnapsack;

import java.util.HashMap;

import java.util.Map;

import br.puc.rio.inf.paa.utils.KnapsackUtil;

public class Teste {

	private Map<Item, Double> itemsAdd;

	public Teste() {
		itemsAdd = new HashMap<Item, Double>();
	}

	public Map<Item, Double> knapsack(FractionalKnapsackInstance knapsack) {

		return knapsackRecursive(knapsack.items, 0, knapsack.items.length - 1, knapsack.capacity);

	}

	public Map<Item, Double> knapsackRecursive(Item[] items, int start, int end, double capacity) {

		// condicao de parada
		if (start == end) {

			if (items[start].weight > capacity) {

				itemsAdd.put(items[start], capacity / items[start].weight);
				// capacity = capacity - (capacity / items[start].weight);

			} else {

				itemsAdd.put(items[start], items[start].weight);
			}

			return itemsAdd;

		} else {

			// calcula-se media do valor/peso e particiona-se ao redor desse
			// valor
			int meio = (start + end) / 2;

			double pivot = getPivot(items, start, end);

			// particionamento os maiores na primeira metade
			int post_p = KnapsackUtil.partition(items, pivot, start, end);

			double sumWeight = 0;

			for (int i = start; i < post_p; i++) {
				sumWeight = sumWeight + items[i].weight;
			}

			if (sumWeight > capacity) {

				knapsackRecursive(items, start, post_p, capacity);

			} else {

				for (int i = start; i < post_p; i++) {
					itemsAdd.put(items[i], items[i].weight);
				}

				// capacity = capacity - sumWeight;

				knapsackRecursive(items, post_p, end, capacity - sumWeight);
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
		return (ratio_f / k);
	}

//	private Item kthValue(Item[] items, int k, int start, int end) {
//		// TODO Auto-generated method stub
//		int size = items.length;
//		if (items.length < 5) {
//			// ordena items
//			return items[size / 2];
//
//		}
//		int groupsQuantity = 0;
//		int rest = size % 5;
//		if (rest == 0) {
//			groupsQuantity = size / 5;
//		} else {
//			groupsQuantity = (size / 5) + 1;
//		}
//
//		// Creating auxiliar array to find medians in each group
//		Item[] medians = new Item[groupsQuantity];
//
//		int indexItems;
//		int medianIndex = 0;
//		for (indexItems = 0; indexItems < rest; indexItems = indexItems + 5) {
//			// sort(itemsAux, indexItems, indexItems + 4);
//
//			medians[medianIndex] = items[indexItems + 2];
//			medianIndex++;
//		}
//
//		if (rest != 0) {
//			// sort(itemsAux, indexItems, n - 1);
//			int indexMedianRestant = indexItems + (rest / 2);
//
//			medians[medianIndex] = items[indexMedianRestant];
//		}
//
//		Item pivo = kthValue(medians, medians.length / 2, 0, medians.length - 1);
//	//	int indexPivo = partition(items, pivo); // get index pivo
//		int p = indexPivo + 1;
//
//		if (k == p) {
//			return pivo;
//		} else if (k > p) {
//			return kthValue(items, k - p, p, end);
//		}
//		return kthValue(items, k, start, indexPivo);
//
//	}

}
