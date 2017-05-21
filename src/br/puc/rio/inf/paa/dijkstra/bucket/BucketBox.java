package br.puc.rio.inf.paa.dijkstra.bucket;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import br.puc.rio.inf.paa.utils.Utils;

public class BucketBox {

	public List<LinkedList<Integer>> buckets;
	int MAX_DISTANCE;

	public BucketBox(int numVertex, int maxCostEdge) {
		buckets = new ArrayList<LinkedList<Integer>>(Utils.setSize(numVertex * maxCostEdge + 1));
		MAX_DISTANCE = numVertex * maxCostEdge + 1;

		for (int i = 0; i < buckets.size(); i++) {

			buckets.set(i, new LinkedList<Integer>());
		}
	}

	public void add(int p, LinkedList<Integer> list) {
		buckets.add(p, list);
	}

}
