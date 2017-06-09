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
			int middle = items.length / 2;
			Item pivo = medianOfMedians(items, middle, start, end);

			// particionamento os maiores na primeira metade
			int post_p = partition(items, pivo.id - 1);

			double sumWeight = 0;

			for (int i = 0; i < middle; i++) {
				sumWeight = sumWeight + items[i].weight;
			}

			if (sumWeight > capacity) {

				knapsackRecursive(items, 0, middle, capacity);

			} else {

				for (int i = 0; i < middle; i++) {
					itemsAdd.put(items[i], items[i].weight);
				}
				// capacity = capacity - sumWeight;

				// if( items[post_p].weight <= capacity){
				// itemsAdd.put(items[post_p], items[post_p].weight);
				// capacity = capacity - sumWeight;
				// }
				// else{
				// itemsAdd.put(items[post_p], capacity/items[post_p].weight);
				// }

				knapsackRecursive(items, middle, end, capacity - sumWeight);

			}

		}

		return itemsAdd;

	}

	public Item medianOfMedians(Item[] items, int k, int start, int end) {
		// TODO Auto-generated method stub
		int size = items.length;

		if (items.length < 5) {
			// ordena items
			KnapsackUtil.mergeSort(items, start, end);
			return items[k];

		}

		int groupsQuantity = 0;
		int rest = size % 5;

		if (rest == 0) {
			groupsQuantity = size / 5;
		} else {
			groupsQuantity = (size / 5) + 1;
		}

		System.out.println("grupos: " + groupsQuantity);
		// Creating auxiliar array to find medians in each group
		Item[] medians = new Item[groupsQuantity];

		// int indexItems;
		int medianIndex = 0;
		int i;
		for (i = 0; i < items.length - rest; i = i + 5) {
			KnapsackUtil.mergeSort(items, i, i + 4);
			medians[medianIndex] = items[(i + 4) / 2];
			System.out.println("mediaaaaaaa: " + medians[medianIndex]);
			medianIndex++;
		}

		if (rest != 0) {

			KnapsackUtil.mergeSort(items, i, end);
			int indexMedianRestant = i + (rest / 2);

			medians[medianIndex] = items[indexMedianRestant];
			System.out.println("mediaaaaaaa: " + medians[medianIndex]);
		}
		//

		Item medianOfMedians = medianOfMedians(medians, medians.length / 2, 0, medians.length - 1);
		
		int indexPivo = partition(items, medianOfMedians.id - 1); // get
		
		
		// index
		// pivo
		// int p = indexPivo + 1;
		//
		// if (k == p) {
		// return items[indexPivo];
		// }
		// if (k < p) {
		// return kthValue(items, k, 0, indexPivo);
		// } else {
		// return kthValue(items, k - p, indexPivo + 1, end);
		// }
		return medianOfMedians(items, k, start, indexPivo);

	}

	public int partition(Item[] items, int indexPivo) {

		int sameValue = 0;
		int i = 0;
		Item temp = null;

		Item itemAux = items[indexPivo];
		items[indexPivo] = items[items.length - 1];
		items[items.length - 1] = itemAux;

		for (int j = i + 1; j < items.length; j++) {

			if (items[j].ratio < itemAux.ratio) {
				temp = items[i];
				items[i] = items[j];
				items[j] = temp;
				i += 1;

			}
			else if (items[j].ratio == itemAux.ratio) {
				if (sameValue % 2 == 0) {
					temp = items[i];
					items[i] = items[j];
					items[j] = temp;
					i += 1;

				}
				sameValue += 1;
			}
			else{
				temp = items[j];
				items[j] = items[i];
				items[i] = temp;
				i += 1;	
			}


		}

		// por pivot no devido lugar
		temp = items[i];
		items[i] = itemAux;
		int size = items.length - 1;
		items[size] = temp;

		return i;
	}

}
