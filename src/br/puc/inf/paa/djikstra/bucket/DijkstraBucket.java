package br.puc.inf.paa.djikstra.bucket;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import br.puc.rio.inf.paa.djikstra.Adjacent;
import br.puc.rio.inf.paa.djikstra.GraphInstance;
import br.puc.rio.inf.paa.djikstra.IDijkstra;
import br.puc.rio.inf.paa.utils.CollectionsUtils;
import br.puc.rio.inf.paa.utils.CsvWriter;

public class DijkstraBucket implements IDijkstra {

	Bucket bucket;
	LinkedList<LinkedList<Integer>> nodesRef;
	LinkedList<Integer> costInfinity;
	int path[];
	int costs[];
	int pos_index;
	int nodesMarked = 0;

	@Override
	public void init(GraphInstance graph, int start) {

		int maxEdge = maxCostEdge(graph);
		bucket = new Bucket(graph.graph.size(), maxEdge);
		
		costs = new int[graph.graph.size() + 1];
		path = new int[graph.graph.size() + 1];

		pos_index = 0;

		nodesRef = new LinkedList<LinkedList<Integer>>(CollectionsUtils.setSize(graph.graph.size() + 1));
	
		costInfinity = new LinkedList<Integer>(CollectionsUtils.setSize(graph.graph.size() + 1));
		
		costs[0] = Integer.MAX_VALUE;
		path[0] = Integer.MAX_VALUE;
		
		for (int vertex : graph.graph.keySet()) {
			if (vertex != start) {
				costs[vertex] = Integer.MAX_VALUE;
				
				LinkedList<Integer> newNode = new LinkedList<Integer>(CollectionsUtils.setSize(vertex));
				costInfinity.addLast(vertex);
				nodesRef.add(vertex, newNode);
				
				
			}else{
				costs[start] = 0;
				
				LinkedList<Integer> newNode = new LinkedList<Integer>(CollectionsUtils.setSize(start));
				bucket.add(0, newNode);
				path[start] = -1;
				nodesRef.add(start,  newNode);
				
			}
			

		}

	}

	@Override
	public int getMin() {
		
		int min = -1;
		for (int i = pos_index; i < bucket.buckets.size(); i++) {
			
			//min1: 1-0p, min2: 2-5p;
			if (bucket.buckets.get(i).size() > 0) {
				
				int temp = bucket.buckets.get(i).getFirst();
				
				min = bucket.buckets.get(i).size();
				
				System.out.println("Vertex: " + min + " Cost: " + temp);
				
				bucket.buckets.get(i).removeFirst();
				pos_index = i;
				
				return min;

			}

		}
		if(costInfinity.size() > 0){
			min = costInfinity.getFirst();
			costInfinity.removeFirst();
		}
		
		return min;

	}

	@Override
	public int[] getCusto() {
		return costs;
	}

	@Override
	public int[] getPath() {
		return path;
	}

	@Override
	public void relax(int from, int to, int distance) {
        //r1: 1-2, 1-4 
		if (costs[from] != Integer.MAX_VALUE && costs[from] + distance < costs[to]) {
			
			System.out.println("From: " + from + " To: " + to);
			
			path[to] = from;
	
			setCosts(from, to, costs[from] + distance);
		}

	}

	@Override
	public void mark(int vertice) {
		nodesMarked ++;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}


	public int maxCostEdge(GraphInstance graphInstance) {
		int maxCost = Integer.MIN_VALUE;

		for (int vertex : graphInstance.graph.keySet()) {
			for (Adjacent edge : graphInstance.graph.get(vertex)) {
				if (edge.distance > maxCost) {
					maxCost = 
							edge.distance;
				}
			}
		}
		return maxCost;

	}
	
	
	public void setCosts(int from, int to, int totalDistanceToVertex){
		if (costs[to] == Integer.MAX_VALUE){
			
			costInfinity.remove(to);
		}
		else{
			//Removed old costs of bucket of vertex
			bucket.buckets.get(costs[to]).remove(nodesRef.get(to));
			
		}
		//Remove old min
		bucket.buckets.get(costs[from]).remove(nodesRef.get(from));
		System.out.println("Size de bucket: " + costs[from] + bucket.buckets.get(costs[from]).size());
		
		
		//Replace to new costs
		costs[to] = totalDistanceToVertex;
		System.out.println("TotalDistance: " + totalDistanceToVertex + " To: " +  to);
		bucket.buckets.add(totalDistanceToVertex, nodesRef.get(to));
	}
	
	
	
}
