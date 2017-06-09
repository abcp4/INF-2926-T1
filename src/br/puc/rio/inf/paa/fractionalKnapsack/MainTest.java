package br.puc.rio.inf.paa.fractionalKnapsack;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import br.puc.rio.inf.paa.fractionalKnapsack.linear.FractionalKnapsackN;
import br.puc.rio.inf.paa.fractionalKnapsack.n2.FractionalKnapsackN2;
import br.puc.rio.inf.paa.fractionalKnapsack.nlogn.FractionalKnapsackNlogN;
import br.puc.rio.inf.paa.utils.KnapsackUtil;

public class MainTest {

	public static void main(String[] args) {

		Item[] items = new Item[8];

		for (int i = 0; i < items.length; i++) {
			items[i] = new Item(i + 1, 30, 6);
		}
		// 5 7 10 76 100 40 35 30 25 20 15 10

		// 50 45 40 35 30 25 20 15 10

		items[0].setWeight(5);
		items[1].setWeight(5);
		items[2].setWeight(10); // 76
		items[3].setWeight(100);
		items[4].setWeight(76);
		items[5].setWeight(43);
		items[6].setWeight(60);
		items[7].setWeight(22);// 60
		//items[8].setWeight(8);

		
		//		items[0].setWeight(48);
//		items[1].setWeight(43);
//		items[2].setWeight(38); // 76
//		items[3].setWeight(33);
//		items[4].setWeight(28);
//		items[5].setWeight(23);
//		items[6].setWeight(18);
//		items[7].setWeight(13);// 60
//		items[8].setWeight(8);
//
//		items[9].setWeight(49);
//		items[10].setWeight(44);
//		items[11].setWeight(39);
//		items[12].setWeight(34);// 62
//		items[13].setWeight(29);
//		items[14].setWeight(24);
//		items[15].setWeight(19);// 40
//		items[16].setWeight(14);
//		items[17].setWeight(9);
//		//
//		items[18].setWeight(50);// ratio = 30/5 = 6
//		items[19].setWeight(45); // 76 //ratio = 30/10 = 3
//		items[20].setWeight(40);
//		items[21].setWeight(35);
//		items[22].setWeight(30);
//		items[23].setWeight(25);
//		items[24].setWeight(20);// 60
//		items[25].setWeight(15);
//		items[26].setWeight(10);
//
//		items[27].setWeight(51);
//		items[28].setWeight(46);
//		items[29].setWeight(41);
//		items[30].setWeight(36);
//		items[31].setWeight(31);
//		items[32].setWeight(26);
//		items[33].setWeight(21);
//		items[34].setWeight(16);
//		items[35].setWeight(53);
//
//		items[36].setWeight(52);
//		items[37].setWeight(47);
//		items[38].setWeight(42);
//		items[39].setWeight(37);
//		items[40].setWeight(32);
//		items[41].setWeight(27);
//		items[42].setWeight(22);
//		items[43].setWeight(17);
//		items[44].setWeight(54);
		//
		// items[9].setWeight(23);
		// items[10].setWeight(41);
		// items[11].setWeight(62);// 62
		// items[12].setWeight(88);
		// items[13].setWeight(85);
		//
		// items[14].setWeight(28);// 40
		// items[15].setWeight(40);
		// items[16].setWeight(9); //ratio = 30/9 = 3

		// Item item = KnapsackUtil.medianOfMedians(items, 0, 16);

		FractionalKnapsackInstance knapsack = new FractionalKnapsackInstance(15.0, items);

		// KnapsackUtil.mergeSort(knapsack.items, 0, items.length - 1);
		// for (int i = 0; i < items.length; i++) {
		// System.out.println(knapsack.items[i].toString());
		//
		// }

		// FractionalKnapsackN knapsackN = new FractionalKnapsackN();
		Teste knapsackN = new Teste();
		//FractionalKnapsackNlogN knapsackNlogN = new FractionalKnapsackNlogN();
		//Item item = knapsackN.medianOfMedians(items, (items.length - 1) / 2, 0, items.length - 1);
		
	  //KnapsackUtil.mergeSort(items, 1, 5 -1);

		// int index = knapsackN.partition(items, 0, items.length - 1, 6);
		// System.out.println("median of medians:" + item.toString());

		 Map<Item, Double> map = knapsackN.knapsack(knapsack);
		 
		double soma = 0;
		
	    for (Entry<Item, Double> entry: map.entrySet()) {
	    	System.out.println(entry.getKey().id + " " + entry.getValue());
	    	soma = soma + entry.getValue();
			
		}
		System.out.println("SOMA: " + soma);
//		map.entrySet()
//		 map.entrySet().forEach(entry -> {
//		 System.out.println(entry.getKey().id + " " + entry.getValue());
//		 
//		   soma = soma +  entry.getValue();
//		 });
		 
		 
		 
		

	}
}
