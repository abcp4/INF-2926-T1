package br.puc.rio.inf.paa.fractionalKnapsack;

import java.util.Map;
import java.util.Map.Entry;

import br.puc.rio.inf.paa.fractionalKnapsack.linear.FractionalKnapsackN;
import br.puc.rio.inf.paa.fractionalKnapsack.n2.FractionalKnapsackN2;

public class MainTest {

	public static void main(String[] args) {

		Item[] items = new Item[8];

		for (int i = 0; i < items.length; i++) {
			items[i] = new Item(i + 1, 30, 6);
		}

		items[0].setWeight(5);
		items[1].setWeight(5);
		items[2].setWeight(10); 
		items[3].setWeight(100);
		items[4].setWeight(76);
		items[5].setWeight(43);
		items[6].setWeight(60);
		items[7].setWeight(22);

		ItemRepository repository = new ItemRepository(items);
		FractionalKnapsack knapsack = new FractionalKnapsack(15.0);
		
		FractionalKnapsackN knapsackN = new FractionalKnapsackN();
		knapsack = knapsackN.knapsack(repository, knapsack);
		 
		double soma = 0;
		
	    for (Item selectedItem : knapsack.selectedItems) {
	    	System.out.println(selectedItem.id + " " + selectedItem.selectedWeight);
	    	soma = soma + selectedItem.selectedWeight;
			
		}
		System.out.println("SOMA: " + soma);

	}
}
