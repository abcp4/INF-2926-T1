package br.puc.inf.paa.dijkstra.bucket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import br.puc.rio.inf.paa.utils.Utils;

public class BucketBox {

	public LinkedList<LinkedList<Integer>> buckets;
	int MAX_DISTANCE;

	public BucketBox(int numVertex, int maxCostEdge) {
		buckets = new LinkedList<LinkedList<Integer>>(Utils.setSize(numVertex*maxCostEdge + 1));
		MAX_DISTANCE = numVertex*maxCostEdge;
	
		
		for (int i = 0; i < buckets.size(); i++) {
			
			buckets.set(i, new LinkedList<Integer>());	
		}
	}
	
	public void add(int p, LinkedList<Integer> list){
		buckets.add(p, list);
	}
	
	
}

