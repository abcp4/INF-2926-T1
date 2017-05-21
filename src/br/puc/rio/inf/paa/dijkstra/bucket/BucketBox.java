package br.puc.rio.inf.paa.dijkstra.bucket;

import java.util.LinkedList;

import br.puc.rio.inf.paa.utils.Utils;

public class BucketBox {

	public LinkedList<LinkedList<Integer>> buckets;
	int MAX_DISTANCE;

	public BucketBox(int numVertex, int maxCostEdge) {
		buckets = new LinkedList<LinkedList<Integer>>(Utils.setSize(numVertex * maxCostEdge + 1));
		MAX_DISTANCE = numVertex * maxCostEdge + 1;

		for (int i = 0; i < buckets.size(); i++) {

			buckets.set(i, new LinkedList<Integer>());
		}
	}

	public void add(int p, LinkedList<Integer> list) {
		buckets.add(p, list);
	}

}
