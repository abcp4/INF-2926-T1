package br.puc.rio.inf.paa.djikstra.heap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


import br.puc.rio.inf.paa.djikstra.array.DijkstraStore;
import br.puc.rio.inf.paa.djikstra.array.GraphInstance;
import br.puc.rio.inf.paa.djikstra.heap.FibonacciHeap.Entry;


public class DijkstraFibonacciStore extends DijkstraStore {

	FibonacciHeap<FibonacciNodeValue> heap;
	List<Entry<FibonacciNodeValue>> nodesRef;
	int[] tree;
	int nodesMarked = 0;
	
	
	public void buildStore(GraphInstance g, int start) {
		tree = new int[g.graph.size()];
		heap = new FibonacciHeap<>();
		nodesRef = new ArrayList<Entry<FibonacciNodeValue>>(Collections.nCopies(g.graph.size()+1,null));
	
		for (int item : g.graph.keySet()) {
			System.out.println(nodesRef.size());

	         if (item != start){
                FibonacciNodeValue node = new FibonacciNodeValue(Integer.MAX_VALUE, item);
               // System.out.println(node.distance);
               // System.out.println(heap.enqueue(node,Integer.MAX_VALUE).getValue().node);	
                Entry<FibonacciNodeValue> parent = heap.enqueue(node,Integer.MAX_VALUE);
              //  System.out.println(nodesRef.size());
                nodesRef.set(item, parent);
               
                }else{
                FibonacciNodeValue node = new FibonacciNodeValue(0, start);
                Entry<FibonacciNodeValue> parent = heap.enqueue(node, 0);
                tree[start] = -1;
                nodesRef.set(start, parent);
            }
        }
		
		
	}

	public int getMin() {
		Entry<FibonacciNodeValue> min = heap.dequeueMin();
		return min.getValue().node;
	}

	public void relax(int v, int w, int lvw) {
		if (nodesRef.get(v).getValue().distance != Integer.MAX_VALUE
				&& nodesRef.get(v).getValue().distance + lvw < nodesRef.get(w).getValue().distance) {
			
			nodesRef.get(w).getValue().distance = nodesRef.get(v).getValue().distance + lvw;
			tree[w] = v;
            Entry<FibonacciNodeValue> result = nodesRef.get(w);
            result.setValue(new FibonacciNodeValue(nodesRef.get(w).getValue().distance, nodesRef.get(w).getValue().node));

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

}
