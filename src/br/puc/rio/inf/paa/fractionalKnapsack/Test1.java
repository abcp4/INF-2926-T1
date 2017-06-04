package br.puc.rio.inf.paa.fractionalKnapsack;

import br.puc.rio.inf.paa.fractionalKnapsack.mergesort.KnapsackUtil;

public class Test1 {
/*
	*//**
	 * Returns the ith smallest element in a subarray <code>array[p..r]</code>.
	 *
	 * @param items
	 *            The array containing the subarray to be sorted.
	 * @param startIndex
	 *            Index of the beginning of the subarray.
	 * @param endIndex
	 *            Index of the end of the subarray.
	 * @param i
	 *            Which order statistic we want.
	 */
	

	/*
	 * Sorts a small subarray.
	 *
	 * @param array
	 *            The array containing the subarray to be sorted.
	 * @param start
	 *            Index of the start of the subarray.
	 * @param size
	 *            Number of elements in the subarray.
	 *//*
	public static void insertionSortSubarray(Item[] array, int start, int size) {
		int end = start + size - 1;

		for (int j = start + 1; j <= end; j++) {
			Item k = array[j];

			// Insert array[j] into the sorted sequence array[start..j-1].
			int i = j - 1;

			while (i >= start && array[i].compareTo(k) > 0) {
				array[i + 1] = array[i];
				i--;
			}

			array[i + 1] = k;
		}
	}

	*//**
	 * Partitions a subarray around its last element.
	 *
	 * @param array
	 *            The array containing the subarray to be partitioned.
	 * @param p
	 *            Index of the beginning of the subarray.
	 * @param r
	 *            Index of the end of the subarray.
	 * @return An index, say <code>q</code>, such that
	 *         <ul>
	 *         <li><code>array[q]</code> = the original item in
	 *         <code>array[r]</code>
	 *         <li><code>array[p..q-1]</code> <= <code>array[q]</code>, and
	 *         <li><code>array[q+1..r]</code> > <code>array[q]</code>.
	 *         </ul>
	 *         Works in place.
	 *//*
	public static int partition(Item[] array, int p, int r) {
		Item x = array[r]; // x is the pivot
		int i = p - 1;

		// Maintain the following invariant:
		// array[p..i] <= x,
		// array[i+1..j-1] > x, and
		// array[r] = x.
		for (int j = p; j < r; j++) {
			if (array[j].compareTo(x) <= 0) {
				i++;
				exchange(array, i, j);
			}
		}

		// Put the pivot value in its correct place and return that
		// index.
		exchange(array, i + 1, r);
		return i + 1;
	}

	*//**
	 * Exchanges the objects at two positions within an array.
	 *
	 * @param i
	 *            The index of one object.
	 * @param j
	 *            The index of the other object.
	 *//*
	public static void exchange(Object[] array, int i, int j) {
		Object t = array[i];
		array[i] = array[j];
		array[j] = t;
	}*/
}
