package br.puc.rio.inf.paa.fractionalKnapsack;

import java.util.Map;

import br.puc.rio.inf.paa.fractionalKnapsack.mergesort.KnapsackUtil;


public class MainTest {
	
	public static void main(String[] args) {
		
		
		Item[] items = new Item[10];
		
		for (int i = 0; i < items.length; i++) {
			items[i] = new Item(i, i+1, 12, 0.0);
		}
		items[0].setWeight(25);
		items[1].setWeight(21);
		items[2].setWeight(98);
		items[3].setWeight(100);
		items[4].setWeight(76);
		items[5].setWeight(22);
		items[6].setWeight(43);
		items[7].setWeight(60);
		items[8].setWeight(89);
		items[9].setWeight(87);
		
		Item item = KnapsackUtil.mediansOfMedians(items, 0, 9);
		
		System.out.println(item.weight);
//		for (int i = 0; i < items.length; i++) {
//		System.out.println(items[i].value);
//		}
		
		
//		FractionalKnapsack knapsack = new FractionalKnapsack(15.0, items);
//		
//		FractionalKnapsackNlogN knapsackN = new FractionalKnapsackNlogN();
//	
//		Map<Item, Double> map = knapsackN.knapsack(knapsack);
//	
//		map.entrySet().forEach( entry-> {
//			System.out.println(entry.getKey().id + " " + entry.getValue());
//		});
//	
	}

}
