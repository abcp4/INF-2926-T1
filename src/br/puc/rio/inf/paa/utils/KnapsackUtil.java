package br.puc.rio.inf.paa.utils;

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



	public static int getPivot(Item[] items, int left, int right) {
		int k = 0;
		double ratio_f = 0;
		for (int i = left; i <= right; i++) {
			ratio_f += items[i].ratio;
			k++;
		}
		return (int)(ratio_f / k);
	}
	
	public static void sort(Item[] subitems, int left, int right) {

		if (left < right) {

			for (int i = left + 1; i <= right; i++) {
				Item aux = subitems[i];

				int j = i - 1;

				while (j >= left && aux.getRatio() < subitems[j].getRatio()) {
					subitems[j + 1] = subitems[j];
					j--;
				}

				subitems[j + 1] = aux;

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