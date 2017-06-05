package br.puc.rio.inf.paa.utils;

import java.util.Arrays;

import br.puc.rio.inf.paa.fractionalKnapsack.Item;

public class KnapsackUtil {

	public static void mergeSort(Item[] items, int left, int right) {

		if (left < right) {

			int medio = left + (right - left) / 2;
			mergeSort(items, left, medio);
			mergeSort(items, medio + 1, right);
			merge(items, medio, left, right);

		}
	}

	public static void merge(Item[] items, int medio, int left, int right) {
		Item[] aux = new Item[right - left + 1];
		for (int i = left; i <= right; i++) {
			aux[i - left] = items[i];
		}

		int i = 0, j = (right - left) / 2 + 1;
		for (int k = left; k <= right; k++) {

			if (i <= (right - left) / 2 && j < aux.length) {
				items[k] = ((aux[i].getRatio() >= aux[j].getRatio()) ? aux[i++] : aux[j++]);
			} else {
				if (j >= aux.length) {
					items[k] = aux[i++];
				} else if (i >= (right - left + 1) / 2) {
					items[k] = aux[j++];
				}
			}
		}
	}

	/*
	 * public static Item medium(Item[] items, int start, int end) {
	 * 
	 * int n = end - start + 1;
	 * 
	 * if(n == 1) { return items[start]; } else { final int GROUP_SIZE = 5; int
	 * groups;
	 * 
	 * if(n % GROUP_SIZE == 0) { groups = n / GROUP_SIZE; }else{ groups = (n /
	 * GROUP_SIZE) + 1; }
	 * 
	 * Item[] medians = new Item[groups];
	 * 
	 * for (int i = 0; i < medians.length; i++) {
	 * 
	 * }
	 * 
	 * 
	 * 
	 * } return null; }
	 */

	/**
	 * Divide the subarray into ceil(n / GROUP_SIZE) groups, and find the median
	 * of each group by insertion sorting the group and picking the median from
	 * the sorted list.
	 **/
	public static Item medianOfMedians(Item[] items, int startIndex, int endIndex) {

		int n = endIndex - startIndex + 1; // number of elements in the subarray

		final int GROUP_SIZE = 5; // size of each group
		int rest = n % GROUP_SIZE;

		if (endIndex - startIndex < 4) {
			sort(items, 0, items.length - 1);
			return items[rest / 2];
		} else {

			int groupsQuantity; // how many groups

			if (rest == 0) {
				groupsQuantity = n / GROUP_SIZE;
			} else {
				groupsQuantity = (n / GROUP_SIZE) + 1;
			}

			// Creating auxiliar array to find medians in each group
			Item[] medians = new Item[groupsQuantity];
			Item[] itemsAux = new Item[n];
			int count = 0;

			for (int i = startIndex; i <= endIndex; i++) {
				itemsAux[count] = items[i];
				count++;
			}

			int medianIndex = 0;
			int indexItems;

			// pega a mediana de cada bloco
			for (indexItems = 0; indexItems < n - rest; indexItems = indexItems + 5) {
				sort(itemsAux, indexItems, indexItems + 4);

				medians[medianIndex] = itemsAux[indexItems + 2];
				medianIndex++;
			}

			if (rest != 0) {

				sort(itemsAux, indexItems, n - 1);

				// i-> index initial of rest block
				// (rest/2) -> median element of rest block
				int indexMedianRestant = indexItems + (rest / 2);

				medians[medianIndex] = itemsAux[indexMedianRestant];
			}
			return medianOfMedians(medians, 0, medians.length - 1);

		}
	}

	public static void sort(Item[] subitems, int left, int right) {
	
		int lenght = 1;
		if (left < right) {
			
			for (int i = left + 1; i < right + 1; i++) {
				Item aux = subitems[i];
				if (aux == null) {
					break;
				}
				int j = i - 1;
				while (j >= left && aux.getRatio() < subitems[j].getRatio()) {
					subitems[j + 1] = subitems[j];
					j--;
				}
				subitems[j + 1] = aux;
				lenght++;
			}
		}
	}

	public static int partition(Item[] items, double pivot_ext, int left, int right) {

		double min = Math.abs(items[left].getRatio() - pivot_ext);
		int pos_min = left;
		double aux = 0;
		for (int j = left; j < right; j++) {
			aux = Math.abs(items[left].getRatio() - pivot_ext);
			if (min > aux) {
				min = aux;
				pos_min = j;
			}
		}
		return partition_PivoteInGivenPosition(items, left, right, pos_min);
	}

	public static int partition_PivoteInGivenPosition(Item[] items, int left, int right, int i) {

		Item aux = items[i];
		items[i] = items[right];
		items[right] = aux;
		return partition_PivoteInLastPosition(items, left, right);
	}

	public static int partition_PivoteInLastPosition(Item[] items, int left, int right) {

		Item pivot = items[right];
		int i = left - 1;
		boolean equalPos = true;
		for (int j = left; j < right; j++) {
			if (items[j].getRatio() < pivot.getRatio() || (items[j].getRatio() == pivot.getRatio() && equalPos)) {
				i++;

				Item _aux = items[i];
				items[i] = items[j];
				items[j] = _aux;
				if (items[j].getRatio() == pivot.getRatio()) {
					equalPos = !equalPos;
				}
			}
		}

		Item aux = items[i + 1];
		items[i + 1] = items[right];
		items[right] = aux;

		return i + 1;
	}

}