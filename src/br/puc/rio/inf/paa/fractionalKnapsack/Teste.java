//package br.puc.rio.inf.paa.fractionalKnapsack;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import br.puc.rio.inf.paa.utils.KnapsackUtil;
//
//public class Teste {
//
//	private Map<Item, Double> itemsAdd;
//
//	public Teste() {
//		itemsAdd = new HashMap<Item, Double>();
//	}
//
//	public Map<Item, Double> knapsack(FractionalKnapsackInstance knapsack) {
//
//		return knapsackRecursive(knapsack.items, 0, knapsack.items.length - 1, knapsack.capacity);
//
//	}
//
//	public Map<Item, Double> knapsackRecursive(Item[] items, int start, int end, double capacity) {
//
//		if (start > end) {
//			return null;
//
//		}
//		// condicao de parada
//		if (start == end) {
//
//			if (items[start].weight >= capacity) {
//				itemsAdd.put(items[start], capacity / items[start].weight);
//
//			} else {
//				itemsAdd.put(items[start], items[start].weight);
//			}
//
//			return itemsAdd;
//
//		} else {
//
//			int meio = (start + end) / 2;
//
//			double x = 0;
//			for (Item item : items) {
//				x = x + item.ratio;
//			}
//
//			// calcula-se media do valor/peso e particiona-se ao redor desse
//			// valor
//			double averageValueWt = x / items.length;
//
//			// particionamento os maiores na primeira metade
//			int firstSmallerIndx = KnapsackUtil.partition(items, averageValueWt, start, end);
//
//			double sumWeight = 0;
//
//			for (int i = 0; i < meio; i++) {
//				sumWeight = sumWeight + items[i].weight;
//			}
//
//			if (sumWeight > capacity) {
//				knapsackRecursive(items, start, meio, capacity);
//			} else {
//				for (int i = 0; i < meio; i++) {
//					itemsAdd.put(items[i], items[i].weight);
//				}
//				knapsackRecursive(items, meio, end, capacity - sumWeight);
//			}
//
//		}
//
//		return itemsAdd;
//
//	}
//
//	public int inverse_partitionByValue(Item[] items, double averageValueWt) {
//
//		double sameValue = 0;
//		int i = 0;
//
//		for (int j = 0; j < items.length; j++) {
//			if (Math.abs(items[j].ratio - averageValueWt) <= .5e-9) {
//				if (sameValue % 2 == 0) {
//					Item temp = items[i];
//					items[i] = items[j];
//					items[j] = temp;
//					i += 1;
//				}
//				sameValue += 1;
//
//			} else if (items[j].ratio > averageValueWt) {
//				Item temp = items[i];
//				items[i] = items[j];
//				items[j] = temp;
//				i += 1;
//			}
//		}
//		return i;
//	}
//
//}
