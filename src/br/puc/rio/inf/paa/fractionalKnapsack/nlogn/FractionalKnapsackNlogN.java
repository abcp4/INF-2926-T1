package br.puc.rio.inf.paa.fractionalKnapsack.nlogn;

import java.util.HashMap;
import java.util.Map;

import br.puc.rio.inf.paa.fractionalKnapsack.ItemRepository;
import br.puc.rio.inf.paa.fractionalKnapsack.FractionalKnapsack;
import br.puc.rio.inf.paa.fractionalKnapsack.Item;
import br.puc.rio.inf.paa.utils.KnapsackUtil;

public class FractionalKnapsackNlogN {
	
	

	public FractionalKnapsack knapsack(ItemRepository repository, FractionalKnapsack knapsack) {

		int n = repository.items.length;

		KnapsackUtil.mergeSort(repository.items, 0, n - 1);

		double current_weight = 0;

		int j = 0;

		while (current_weight < knapsack.capacity && j < n) {

			if (repository.items[j].weight + current_weight <= knapsack.capacity) {

				//itemsAdd.put(repository.items[j],repository.items[j].weight );
				repository.items[j].selectedWeight = repository.items[j].weight; 
				knapsack.selectedItems.add(repository.items[j]);
			
				current_weight = current_weight + repository.items[j].weight;

			} else {

				//itemsAdd.put(repository.items[j], ((knapsack.capacity - current_weight) / repository.items[j].weight));
				repository.items[j].selectedWeight = knapsack.capacity /repository.items[j].weight; 
				knapsack.selectedItems.add(repository.items[j]);
				
				current_weight = current_weight + ((knapsack.capacity - current_weight) / repository.items[j].weight);
			}

			j++;
		}

		return knapsack;

	}

}
