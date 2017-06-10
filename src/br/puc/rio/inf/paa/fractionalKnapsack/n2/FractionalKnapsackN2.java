package br.puc.rio.inf.paa.fractionalKnapsack.n2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.puc.rio.inf.paa.fractionalKnapsack.FractionalKnapsack;
import br.puc.rio.inf.paa.fractionalKnapsack.Item;
import br.puc.rio.inf.paa.fractionalKnapsack.ItemRepository;
import br.puc.rio.inf.paa.utils.KnapsackUtil;

public class FractionalKnapsackN2 {

	private List<Item> selectedItems;

	public FractionalKnapsack knapsack(ItemRepository repository, FractionalKnapsack knapsack) {
		
		selectedItems = new ArrayList<Item>();
		knapsack.selectedItems = knapsackRecursive(repository.items, 0, repository.items.length - 1, knapsack.capacity);
	
		return knapsack;
	}

	public List<Item> knapsackRecursive(Item[] items, int start, int end, double currentCapacity) {

		if (start == end) {
			if (items[start].weight > currentCapacity) {
				//selectedItems.put(items[start], capacity / items[start].weight);
				items[start].selectedWeight = currentCapacity/items[start].weight; 
				selectedItems.add(items[start]);
				
				currentCapacity = currentCapacity - items[start].selectedWeight;

			} else {
				//selectedItems.put(items[start], items[start].weight);
				items[start].selectedWeight = items[start].weight;
				selectedItems.add(items[start]);

				currentCapacity = currentCapacity - items[start].selectedWeight;

			}

			return selectedItems;

		} else {
			// calcula-se media do valor/peso e particiona-se ao redor desse
			// valor

			int indexPivot = KnapsackUtil.getPivot(items, start, end);

			int post_p = partition(items, start, end, indexPivot);
	
			double sumWeight = 0;

			for (int i = start; i < post_p; i++) {
				sumWeight = sumWeight + items[i].weight;
			}
			
			if (sumWeight > currentCapacity) {

				knapsackRecursive(items, start, post_p -1, currentCapacity);

				// se cabe tudo, poe metade mais valiosa na mochila e repete-se
				// algoritmo na
				// metade menos valiosa
			} else {
				for (int i = start; i < post_p; i++) {
					//selectedItems.put(items[i], items[i].weight);
					items[i].selectedWeight = items[i].weight;
					selectedItems.add(items[i]);
				}

				currentCapacity = currentCapacity - sumWeight;

				if (items[post_p].weight <= currentCapacity) {
					//selectedItems.put(items[post_p], items[post_p].weight);
					items[post_p].selectedWeight = items[post_p].weight;
					selectedItems.add(items[post_p]);
										
					currentCapacity = currentCapacity - items[post_p].selectedWeight;
				} else {

					//selectedItems.put(items[post_p], capacity / items[post_p].weight);
					items[post_p].selectedWeight =  currentCapacity/items[post_p].weight;
					selectedItems.add(items[post_p]);
					
					currentCapacity = currentCapacity - items[post_p].selectedWeight;
					knapsackRecursive(items, post_p + 1, end, currentCapacity);
				}

			}
		}

		return selectedItems;

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

