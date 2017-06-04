package br.puc.rio.inf.paa.fractionalKnapsack;

public class Test1 {
/*
	*//**
	 * Returns the ith smallest element in a subarray <code>array[p..r]</code>.
	 *
	 * @param array
	 *            The array containing the subarray to be sorted.
	 * @param p
	 *            Index of the beginning of the subarray.
	 * @param r
	 *            Index of the end of the subarray.
	 * @param i
	 *            Which order statistic we want.
	 *//*
	public static Item select(Item[] array, int p, int r, int i) {
		int n = r - p + 1; // number of elements in the subarray

		if (n == 1)
			return array[p]; // base case: return the only element
		else {
			// Divide the subarray into ceil(n / GROUP_SIZE) groups,
			// and find the median of each group by insertion sorting
			// the group and picking the median from the sorted list.
			final int GROUP_SIZE = 5; // size of each group
			int groups; // how many groups
			if (n % GROUP_SIZE == 0)
				groups = n / GROUP_SIZE;
			else
				groups = (n / GROUP_SIZE) + 1;

			// Create an array of medians.
			Item[] medians = new Item[groups];

			// Fill in medians to contain the medians of the groups.
			for (int groupStart = p, groupNumber = 0; groupStart <= r; groupStart += GROUP_SIZE, groupNumber++) {
			
				int thisGroupSize = Math.min(r - groupStart + 1, GROUP_SIZE);
				
				insertionSortSubarray(array, groupStart, thisGroupSize);
				
				medians[groupNumber] = array[groupStart + ((thisGroupSize - 1) / 2)];
			}

			// Recursively find the median of the medians.
			Item theMedian = select(medians, 0, groups - 1, (groups + 1) / 2);

			// We need to figure out where in the array the median of
			// the medians is. Go through the array, comparing
			// elements to theMedian until we find they're equal. We
			// are guaranteed that we will find an element equal to
			// the median, so we do not need to check for running off
			// the end of the subarray. Because we are doing no
			// arithmetic on the elements, it is safe to compare the
			// elements even if they are floating-point values. Note
			// also that running through the subarray does not
			// increase the asymptotic running time.
			int medianIndex = p;
			while (theMedian.compareTo(array[medianIndex]) != 0)
				medianIndex++;

			// Partition the input array around the median of the
			// medians.
			exchange(array, r, medianIndex);
			int q = partition(array, p, r);

			// The low side of the partition is array[p..q-1]. Set k
			// to the number of elements in array[p..q], so that we
			// include the median of the medians in our count.
			int k = q - p + 1;

			if (i == k)
				return array[q]; // ith smallest is at index q
			else if (i < k)
				return select(array, p, q - 1, i); // ith smallest is ith
			// smallest in the low side
			else
				return select(array, q + 1, r, i - k); // ith smallest is
														// (i-k)th
			// smallest in high side
		}
	}

	*//**
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
