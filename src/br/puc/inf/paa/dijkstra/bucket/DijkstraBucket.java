package br.puc.inf.paa.dijkstra.bucket;

import java.util.LinkedList;

import javax.swing.plaf.synth.SynthSeparatorUI;

import br.puc.rio.inf.paa.dijkstra.Adjacent;
import br.puc.rio.inf.paa.dijkstra.GraphInstance;
import br.puc.rio.inf.paa.dijkstra.IDijkstra;
import br.puc.rio.inf.paa.utils.CollectionsUtils;

public class DijkstraBucket implements IDijkstra {

	Bucket bucket;
	LinkedList<LinkedList<Integer>> nodesRef;
	
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
	
		
	//	System.out.println("size - Custo1: " + costInfinity.size());
		
		costs[0] = bucket.MAX_WEIGHT;
		path[0] = bucket.MAX_WEIGHT;
		
		for (int vertex : graph.graph.keySet()) {
			if (vertex != start) {
				costs[vertex] = bucket.MAX_WEIGHT;
				
				LinkedList<Integer> newNode = new LinkedList<Integer>();
				newNode.add(vertex);
				
				bucket.add(bucket.MAX_WEIGHT, newNode);
				nodesRef.add(vertex, newNode);
				
				
			}else{
				costs[start] = 0;
				
				LinkedList<Integer> newNode = new LinkedList<Integer>();
				
				newNode.add(start);
				bucket.add(0, newNode);
				
				path[start] = -1;
				
				nodesRef.add(start,  newNode);
				
			}
			

		}

//		System.out.println("size - Custo: " + costInfinity.size());
	}

	@Override
	public int extractMin() {
		
		
		int min = -1;
		for (int i = pos_index; i < bucket.buckets.size(); i++) {
				
			if (bucket.buckets.get(i).size() > 0) {
				
				min = bucket.buckets.get(i).getFirst();
				bucket.buckets.get(i).removeFirst();

				pos_index = i;
				
				return min;
			}
				
		}
		
		if(bucket.buckets.get(bucket.MAX_WEIGHT).size() > 0){

			min = bucket.buckets.get(bucket.MAX_WEIGHT).getFirst();	
			bucket.buckets.get(bucket.MAX_WEIGHT).removeFirst();
			
            return min;
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
		if (costs[from] != bucket.MAX_WEIGHT  && costs[from] + distance < costs[to]) {
				
			path[to] = from;
	
			if(distance == 2147483647){
				System.out.println("From: " + from + " To: " + to);
			}
			setCosts(from, to, costs[from] + distance);
		}

	}

	@Override
	public void setVisited(int vertice) {
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
		if (costs[to] == bucket.MAX_WEIGHT){
			//System.out.println("remove: " + to);
		 //	System.out.println("size infinity1 "+ costInfinity.size());
			
			bucket.buckets.get(bucket.MAX_WEIGHT).remove(new Integer(to));
			
			//System.out.println("size infinity "+ costInfinity.size());
		}
		else{
			//Removed old costs of bucket of vertex
			bucket.buckets.get(costs[to]).remove(new Integer(to));
			
		}
		
		//Replace to new costs
		costs[to] = totalDistanceToVertex;
		
	//	System.out.println("TotalDistance: " + totalDistanceToVertex + " To: " +  to);
		
		bucket.buckets.get(totalDistanceToVertex).add(to);
		
  //		System.out.println("Size de bucket - new bucket: " + bucket.buckets.get(totalDistanceToVertex).size());
		
	}
	
	
	
}
