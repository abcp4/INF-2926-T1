package br.puc.rio.inf.paa.djikstra.heap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.puc.rio.inf.paa.djikstra.array.DijkstraStore;
import br.puc.rio.inf.paa.djikstra.array.GraphInstance;
import br.puc.rio.inf.paa.djikstra.heap.FibonacciHeap.Entry;

public class DijkstraFibonacciStore extends DijkstraStore {

	FibonacciHeap<FibonacciNodeValue> heap;
	HashMap<Integer, FibonacciNodeValue> nodesRef;
	//Entry<FibonacciNodeValue>[] nodesRef;
	int costs[];
	int[] tree;
	int nodesMarked = 0;

	public DijkstraFibonacciStore() {
		heap = new FibonacciHeap<FibonacciNodeValue>();
	}

	public void buildStore(GraphInstance g, int start) {
		tree = new int[g.graph.size()];
		costs = new int[g.graph.size()];
		nodesRef = new HashMap(g.graph.size());
		
		for (int item : g.graph.keySet()) {
			// System.out.println(nodesRef.size());

			if (item != start) {

				FibonacciNodeValue node = new FibonacciNodeValue(Integer.MAX_VALUE, item);
				Entry<FibonacciNodeValue> parent = heap.enqueue(node, Integer.MAX_VALUE);
				
				
				nodesRef[item] = parent;
				costs[item] = Integer.MAX_VALUE;
				// FibonacciNodeValue node = new
				// FibonacciNodeValue(Integer.MAX_VALUE, item);
				// Entry<FibonacciNodeValue> parent =
				// heap.enqueue(node,Integer.MAX_VALUE);
				// nodesRef.set(item, parent);

			} else {
				FibonacciNodeValue node = new FibonacciNodeValue(0, start);
				Entry<FibonacciNodeValue> parent = heap.enqueue(node, 0);
				tree[start] = -1;
				nodesRef[start] = parent;
			}
		}

	}

	public int getMin() {
		Entry<FibonacciNodeValue> min = heap.dequeueMin();
		return min.getValue().node;
	}

	public void relax(int v, int w, int lvw) {
		if (nodesRef[v].getValue().distance != Integer.MAX_VALUE
				&& nodesRef[v].getValue().distance + lvw < nodesRef[w].getValue().distance) {

			nodesRef[w].getValue().distance = nodesRef[v].getValue().distance + lvw;
			tree[w] = v;
			Entry<FibonacciNodeValue> result = nodesRef[w];
			result.setValue(
					new FibonacciNodeValue(nodesRef[w].getValue().distance, nodesRef[w].getValue().node));

			heap.decreaseKey(result, nodesRef[w].getValue().distance);

		}
	}

	public int[] getCosts() {

		int[] costs = new int[tree.length];
		for (int i = 0; i < nodesRef.length; i++) {
			costs[i] = nodesRef[i].getValue().distance;
		}
		return costs;
	}

	public int[] getTree() {
		return tree;
	}

	public int getCount() {
		return tree.length - nodesMarked;

	}

	public boolean isEmpty() {
		return nodesMarked == tree.length;

	}

	public void mark(int v) {
		nodesMarked++;

	}

}
