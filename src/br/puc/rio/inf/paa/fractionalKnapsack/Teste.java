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
				capacity = capacity - (capacity / items[start].weight);

			} else {

				itemsAdd.put(items[start], items[start].weight);
			}

			return itemsAdd;

		} else {

			// calcula-se media do valor/peso e particiona-se ao redor desse
			// valor
			double pivot = KnapsackUtil.mediansOfMedians(items, start, end).ratio;

			// particionamento os maiores na primeira metade
			int post_p = KnapsackUtil.partition(items, pivot, start, end);

			double sumWeight = 0;

			for (int i = end; i > post_p; i--) {
				sumWeight = sumWeight + items[i].weight;
			}

			if (sumWeight > capacity) {

				knapsackRecursive(items, start, post_p - 1, capacity);

			} else {

				for (int i = end; i > post_p; i--) {
					itemsAdd.put(items[i], items[i].weight);
				}
				capacity = capacity - sumWeight;
				
                if( items[post_p].weight <= capacity){
                	itemsAdd.put(items[post_p], items[post_p].weight);	
                	capacity = capacity - sumWeight;
                }
                else{
                	itemsAdd.put(items[post_p], capacity/items[post_p].weight);	
                }
                
				
                knapsackRecursive(items, start, post_p - 1, capacity - sumWeight);
			}

		}

		return itemsAdd;

	}

	public int inverse_partitionByValue(Item[] items, double averageValueWt) {

		double sameValue = 0;
		int i = 0;

		for (int j = 0; j < items.length; j++) {
			if (Math.abs(items[j].ratio - averageValueWt) <= .5e-9) {
				if (sameValue % 2 == 0) {
					Item temp = items[i];
					items[i] = items[j];
					items[j] = temp;
					i += 1;
				}
				sameValue += 1;

			} else if (items[j].ratio > averageValueWt) {
				Item temp = items[i];
				items[i] = items[j];
				items[j] = temp;
				i += 1;
			}
		}
		return i;
	}

}
