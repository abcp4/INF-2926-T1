package br.puc.inf.paa.djikstra.bucket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import br.puc.rio.inf.paa.utils.CollectionsUtils;

public class Bucket {

	public LinkedList<LinkedList<Integer>> buckets;
	int MAX_WEIGHT;

	//-XX:MaxPermSize=1024m
	public Bucket(int numVertex, int maxCostEdge) {
		buckets = new LinkedList<LinkedList<Integer>>(CollectionsUtils.setSize(numVertex*maxCostEdge + 1));
		MAX_WEIGHT = numVertex*maxCostEdge;
	
		
		for (int i = 0; i < buckets.size(); i++) {
			
			buckets.set(i, new LinkedList<Integer>());
		//	System.out.println(i);	
		}
	}
	
	public void add(int p, LinkedList<Integer> list){
		buckets.add(p, list);
	}
	
	
}

