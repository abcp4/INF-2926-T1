package br.puc.rio.inf.paa.fractionalKnapsack;

import java.util.Map;

import br.puc.rio.inf.paa.fractionalKnapsack.linear.FractionalKnapsackN;
import br.puc.rio.inf.paa.fractionalKnapsack.n2.FractionalKnapsackN2;
import br.puc.rio.inf.paa.fractionalKnapsack.nlogn.FractionalKnapsackNlogN;
import br.puc.rio.inf.paa.utils.KnapsackUtil;

public class MainTest {

	public static void main(String[] args) {

		Item[] items = new Item[17];

		for (int i = 0; i < items.length; i++) {
			items[i] = new Item(i, 30, 6);
		}
		items[0].setWeight(5);
		items[1].setWeight(5);
		items[2].setWeight(10); // 76
		items[3].setWeight(100);
		items[4].setWeight(76);

		items[5].setWeight(22);
		items[6].setWeight(43);
		items[7].setWeight(60);// 60
		items[8].setWeight(89);
		items[9].setWeight(87);

		items[10].setWeight(23);
		items[11].setWeight(41);
		items[12].setWeight(62);// 62
		items[13].setWeight(88);
		items[14].setWeight(85);

		items[15].setWeight(28);// 40
		items[16].setWeight(40);

		// Item item = KnapsackUtil.medianOfMedians(items, 0, 16);

		

		
		FractionalKnapsackInstance knapsack = new FractionalKnapsackInstance(15.0, items);


//		KnapsackUtil.mergeSort(knapsack.items, 0, items.length - 1);
//		 for (int i = 0; i < items.length; i++) {
//		 System.out.println(knapsack.items[i].toString());
//		 
//		 }

		
		FractionalKnapsackN knapsackN = new FractionalKnapsackN();
		//FractionalKnapsackNlogN knapsackN = new FractionalKnapsackNlogN();
		Map<Item, Double> map = knapsackN.knapsack(knapsack);
		map.entrySet().forEach(entry -> {
			System.out.println(entry.getKey().id + " " + entry.getValue());
		});

	}

}

