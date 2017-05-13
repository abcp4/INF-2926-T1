package br.puc.rio.inf.paa.djikstra.heap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.puc.rio.inf.paa.djikstra.array.DijkstraStore;
import br.puc.rio.inf.paa.djikstra.array.GraphInstance;
import br.puc.rio.inf.paa.djikstra.heap.FibonacciHeap.Entry;

public class DijkstraFibonacciStore extends DijkstraStore{

	FibonacciHeap<FibonacciNodeValue> heap;
	Map<Integer, Entry<FibonacciNodeValue>> nodesRef;
	int nodesMarked = 0;

	public DijkstraFibonacciStore() {
		heap = new FibonacciHeap<FibonacciNodeValue>();
	}

	public void buildStore(GraphInstance g, int start) {
		tree = new int[g.graph.size() + 1];
		costs = new int[g.graph.size() + 1];
		nodesRef = new HashMap<>();

		for (int item : g.graph.keySet()) {
			if (item != start) {
				FibonacciNodeValue node = new FibonacciNodeValue(Integer.MAX_VALUE, item);
				Entry<FibonacciNodeValue> parent = heap.enqueue(node, Integer.MAX_VALUE);
				costs[item] = Integer.MAX_VALUE;
				nodesRef.put(item, parent);
				
			} else {
				FibonacciNodeValue node = new FibonacciNodeValue(0, start);
				Entry<FibonacciNodeValue> parent = heap.enqueue(node, 0);
				tree[start] = -1;
				costs[start] = 0;
				nodesRef.put(item, parent);
			}
		}

		 g.graph
		  .entrySet()
		  .forEach( entry -> {
			System.out.println(entry.getKey());
			System.out.println(entry.getValue().toString());
		 });
		 
	}

	public int[] costs() {
	//	int[] costs = new int[tree.length];
	//	for (int i = 0; i < nodesRef.size(); i++) {
	//		costs[i] = nodesRef[i].getValue().distance;
	//	}
		return costs;
	}

	public int getMin(GraphInstance g) {
		Entry<FibonacciNodeValue> min = heap.dequeueMin();
		return min.getValue().getNode();
	}

	public void relax(int v, int w, int lvw) {
		//System.out.println(nodesRef.get(v).getValue().distance);
		//System.out.println(nodesRef.get(w).getValue().distance);
		//System.out.println(nodesRef.get(lvw).getValue().distance);
		if (nodesRef.get(v).getValue().distance != Integer.MAX_VALUE
				&& nodesRef.get(v).getValue().distance + lvw < nodesRef.get(w).getValue().distance) {

			nodesRef.get(w).getValue().distance = nodesRef.get(v).getValue().distance + lvw;
			tree[w] = v;
			Entry<FibonacciNodeValue> result = nodesRef.get(w);
			result.setValue(
					new FibonacciNodeValue(nodesRef.get(w).getValue().distance, nodesRef.get(w).getValue().node));

			heap.decreaseKey(result, nodesRef.get(w).getValue().distance);

		}
	}

	public int[] getCosts() {

		int[] costs = new int[tree.length];
		for (int i = 0; i < nodesRef.size(); i++) {
			costs[i] = nodesRef.get(i).getValue().distance;
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

	@Override
	public int getMin() {
		// TODO Auto-generated method stub
		return 0;
	}

}
