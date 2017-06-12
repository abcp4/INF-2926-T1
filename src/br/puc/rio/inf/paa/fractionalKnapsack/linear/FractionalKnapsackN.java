package br.puc.rio.inf.paa.fractionalKnapsack.linear;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.puc.rio.inf.paa.fractionalKnapsack.FractionalKnapsack;
import br.puc.rio.inf.paa.fractionalKnapsack.Item;
import br.puc.rio.inf.paa.fractionalKnapsack.ItemRepository;
import br.puc.rio.inf.paa.utils.KnapsackUtil;

public class FractionalKnapsackN {
	

	List<Item> selectedItems; 
	
	public FractionalKnapsack knapsack(ItemRepository repository, FractionalKnapsack knapsack) {
	
		selectedItems = new ArrayList<>();
		knapsack.selectedItems = knapsackRecursive(repository.items, 0, repository.items.length - 1, knapsack.capacity);
		return knapsack;
	
	}

	public List<Item> knapsackRecursive(Item[] items, int start, int end, double currentCapacity) {

		if (start == end) {
			if (items[start].weight > currentCapacity) {
				//itemsAdd.put(items[start], capacity / items[start].weight);
				items[start].selectedWeight = currentCapacity/items[start].weight;
				selectedItems.add(items[start]);
				
				currentCapacity = currentCapacity - items[start].selectedWeight;
			} else {
				//itemsAdd.put(items[start], items[start].weight);
				items[start].selectedWeight = items[start].weight;
				selectedItems.add(items[start]);
				
				currentCapacity = currentCapacity - items[start].selectedWeight;
			}

			return selectedItems;

		} else {
			// calcula-se media do valor/peso e particiona-se ao redor desse
			// valor
			int middle = start + (end - start) / 2;

			Item pivot = medianOfMedians(items, middle, start, end);
			
			int indexPivo = getIndex(items, start, end, pivot);

			int post_p = partition(items, start, end, indexPivo);
	
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
					//itemsAdd.put(items[i], items[i].weight);
					items[i].selectedWeight = items[i].weight;
					selectedItems.add(items[i]);
				}

				currentCapacity = currentCapacity - sumWeight;

				if (items[post_p].weight <= currentCapacity) {
					//itemsAdd.put(items[post_p], items[post_p].weight);
					items[post_p].selectedWeight = items[post_p].weight;
					selectedItems.add(items[post_p]);
					
					currentCapacity = currentCapacity - items[post_p].selectedWeight;
				} else {

					//itemsAdd.put(items[post_p], capacity / items[post_p].weight);
					items[post_p].selectedWeight = currentCapacity/items[post_p].weight;
					selectedItems.add(items[post_p]);
					
					currentCapacity = currentCapacity - items[post_p].selectedWeight;
					knapsackRecursive(items, post_p + 1, end, currentCapacity);
				}

			}
		}

		return selectedItems;

	}

	public Item medianOfMedians(Item[] items, int k, int start, int end) {

		int size = items.length;

		if (end - start <= 5) {
			
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

		Item[] medians = new Item[groupsQuantity];

		int medianIndex = 0;
		int i;

		for (i = 0; i < items.length - rest; i = i + 5) {

			KnapsackUtil.mergeSort(items, i, i + 4);

			medians[medianIndex] = items[(i + 5) / 2];
			medianIndex++;
		}

		if (rest != 0) {
			KnapsackUtil.mergeSort(items, i, items.length - 1);

			int indexMedianRestant = i + (rest / 2);
			medians[medianIndex] = items[indexMedianRestant];
		}

		return medianOfMedians(medians, (medians.length - 1) / 2, 0, medians.length - 1);
	}

	public int getIndex(Item[] items, int start, int end, Item kItem) {

		for (int i = start; i <= end; i++) {
			if (items[i].id == kItem.id) {
				return i;
			}
		}
		return -1;
	}

	public int partition(Item[] items, int left, int right, int pivotIndex) {
		
		Item pivotValue = items[pivotIndex];
		swap(items, pivotIndex, right); // move pivot to end
		int storeIndex = left;
		
		for (int i = left + 1; i < right; i++) {
			if (items[i].ratio > pivotValue.ratio) {
				swap(items, storeIndex, i);
				storeIndex++;
			}
		}
		swap(items, right, storeIndex); // Move pivot to its final place
		return storeIndex;
	}

	private void swap(Item[] array, int a, int b) {
		Item tmp = array[a];
		array[a] = array[b];
		array[b] = tmp;
	}

}
