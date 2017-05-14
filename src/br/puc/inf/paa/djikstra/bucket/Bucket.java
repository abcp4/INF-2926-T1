package br.puc.inf.paa.djikstra.bucket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import br.puc.rio.inf.paa.utils.CollectionsUtils;

public class Bucket {

	public List<LinkedList<Integer>> buckets;


	public Bucket(int numVertex, int maxCostEdge) {
		buckets = new ArrayList<LinkedList<Integer>>(CollectionsUtils.setSize(numVertex*maxCostEdge + 1));
		
		for (int i = 0; i < buckets.size(); i++) {
			buckets.add(i, new LinkedList<Integer>());
		}
	}
	
	public void add(LinkedList<Integer> list, int p){
		buckets.add(p, new LinkedList(list));
	}
	
	
}

