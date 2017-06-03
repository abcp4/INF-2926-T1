package br.puc.rio.inf.paa.fractionalKnapsack.mergesort;

import br.puc.rio.inf.paa.fractionalKnapsack.Item;

public class KnapsackUtil {

	private void MergeSort(Item[] items, int left, int right) {

		if (left < right) {

			int medio = left + (right - left) / 2;
			MergeSort(items, left, medio);
			MergeSort(items, medio + 1, right);
			Merge(items, medio, left, right);
		}
	}

	private void Merge(Item[] items, int medio, int left, int right) {
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

	private Item MediansOfMedians(Item[] elements, int l, int r) {
		int _n = r - l + 1;
		Item[] sub_elements = new Item[_n];
		int rest = _n % 5;
		int it = 0;
		for (int i = l; i <= r; i++) {
			sub_elements[it] = elements[i];
			it++;
		}
		if (r - l > 4) {
			Item[] mediums = new Item[_n / 5 + (rest > 0 ? 1 : 0)];
			int j = 0;
			int i = 0;
			for (; i < _n - rest; i = i + 5) {
				Sort(sub_elements, i, i + 4);
				mediums[j] = sub_elements[i + 2];
				j++;
			}
			if (rest != 0) {
				Sort(sub_elements, i, _n - 1);
				mediums[j] = sub_elements[rest / 2];
			}
			return MediansOfMedians(mediums, 0, mediums.length - 1);
		} else {
			Sort(sub_elements, 0, sub_elements.length - 1);
			return sub_elements[rest / 2];
		}
	}

	private void Sort(Item[] objects, int left, int right) {
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


}