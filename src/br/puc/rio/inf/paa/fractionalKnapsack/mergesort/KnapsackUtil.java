package br.puc.rio.inf.paa.fractionalKnapsack.mergesort;

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

/*	public static Item medium(Item[] items, int start, int end) {

		int n = end - start + 1;

		if(n == 1) {
			return items[start];
		} else {
			final int GROUP_SIZE = 5;
			int groups;

			if(n % GROUP_SIZE == 0) {
				groups = n / GROUP_SIZE;
			}else{
				groups = (n / GROUP_SIZE) + 1;
			}

			Item[] medians = new Item[groups];
		
			for (int i = 0; i < medians.length; i++) {
				
			}
		
		
		
		}
		return null;
	}*/

	public static Item mediansOfMedians(Item[] items, int start, int end) {

		int n = end - start + 1;

		Item[] subItems = new Item[n];

		int rest = n % 5;

		int count = 0;

		// inicializa o vetor de subitens
		for (int i = start; i <= end; i++) {
			subItems[count] = items[i];
			count++;
		}

		// verifica se pode dividir em 5 blocos
		// caso contrário teremos um unico bloco
		if (end - start > 4) {

			Item[] mediums = null;

			// calcula o tamanho do vetor de medianas
			if (rest > 0) {
				mediums = new Item[(n / 5) + 1];
			} else {
				mediums = new Item[(n / 5)];
			}

			int j = 0;
			int i;

			// pega a mediana de cada bloco
			for (i = 0; i < n - rest; i = i + 5) {
				sort(subItems, i, i + 4);
				mediums[j] = subItems[i + 2];
				System.out.println("Bloco " + i + " : " + mediums[j]);
				j++;
			}

			if (rest != 0) {

				sort(subItems, i, n - 1);

				int indexMedianRestant = rest / 2;

				int startRestantBlock = n - rest + 1;

				indexMedianRestant = startRestantBlock + indexMedianRestant;

				mediums[j] = subItems[indexMedianRestant];
				System.out.println("Bloco restante : " + mediums[j]);
			}
			return mediansOfMedians(mediums, 0, mediums.length - 1);
		} else {
			sort(subItems, 0, subItems.length - 1);
			return subItems[rest / 2];
		}
	}

	public static void sort(Item[] objects, int left, int right) {
		int lenght = 1;
		if (left < right) {
			for (int i = left + 1; i < right + 1; i++) {
				Item aux = objects[i];
				if (aux == null) {
					break;
				}
				int j = i - 1;
				while (j >= left && aux.getRatio() < objects[j].getRatio()) {
					objects[j + 1] = objects[j];
					j--;
				}
				objects[j + 1] = aux;
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