package br.puc.inf.paa.djikstra.bucket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Bucket {

	public List<LinkedList<Integer>> buckets;


	public Bucket(int numVertex, int maxCostEdge) {
		buckets = new ArrayList<LinkedList<Integer>>(numVertex*maxCostEdge + 1);
		System.out.println(buckets.size());
		for (int i = 0; i < buckets.size(); i++) {
			buckets.set(i, new LinkedList<Integer>());
		}
	}
	
	public void add(LinkedList<Integer> s, int p){
		buckets.add(p, s);
	}
	
	
}

