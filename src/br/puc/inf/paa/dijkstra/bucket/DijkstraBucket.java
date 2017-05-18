package br.puc.inf.paa.dijkstra.bucket;

import java.util.LinkedList;

import br.puc.rio.inf.paa.dijkstra.Adjacent;
import br.puc.rio.inf.paa.dijkstra.GraphInstance;
import br.puc.rio.inf.paa.dijkstra.IDijkstra;
import br.puc.rio.inf.paa.utils.CollectionsUtils;

public class DijkstraBucket implements IDijkstra {

	BucketBox box;
	LinkedList<LinkedList<Integer>> nodesRef;
	
	int path[];
	int distance[];
	int pos_index;
	int visitedTotal = 0;

	@Override
	public void initialize(GraphInstance graph, int start) {

		int maxEdge = maxCostEdge(graph);
		box = new BucketBox(graph.graph.size(), maxEdge);
		
		distance = new int[graph.graph.size() + 1];
		path = new int[graph.graph.size() + 1];

		pos_index = 0;

		nodesRef = new LinkedList<LinkedList<Integer>>(CollectionsUtils.setSize(graph.graph.size() + 1));
	
		distance[0] = box.MAX_DISTANCE;
		path[0] = box.MAX_DISTANCE;
		
		for (int vertex : graph.graph.keySet()) {
			if (vertex != start) {
				distance[vertex] = box.MAX_DISTANCE;
				
				LinkedList<Integer> newNode = new LinkedList<Integer>();
				newNode.add(vertex);
				
				box.add(box.MAX_DISTANCE, newNode);
				nodesRef.add(vertex, newNode);
				
				
			}else{
				distance[start] = 0;
				
				LinkedList<Integer> newNode = new LinkedList<Integer>();
				
				newNode.add(start);
				box.add(0, newNode);
				
				path[start] = -1;
				
				nodesRef.add(start,  newNode);
				
			}
			

		}
	}

	@Override
	public int getMin() {
		
		
		int min = -1;
		for (int i = pos_index; i < box.buckets.size(); i++) {
				
			if (box.buckets.get(i).size() > 0) {
				
				min = box.buckets.get(i).getFirst();
				box.buckets.get(i).removeFirst();

				pos_index = i;
				
				return min;
			}
				
		}
		
		if(box.buckets.get(box.MAX_DISTANCE).size() > 0){

			min = box.buckets.get(box.MAX_DISTANCE).getFirst();	
			box.buckets.get(box.MAX_DISTANCE).removeFirst();
			
            return min;
		}
		
		
		return min;
		
	}


	@Override
	public int[] getPath() {
		return path;
	}

	@Override
	public void relax(int vertexA, int vertexB, int distanceAB) {

		if (distance[vertexA] != box.MAX_DISTANCE  && distance[vertexA] + distanceAB < distance[vertexB]) {
				
			path[vertexB] = vertexA;
			decreaseKey(vertexB, distance[vertexA] + distanceAB);
		}

	}

	@Override
	public void setVisited(int vertex) {
		box.buckets.get(box.MAX_DISTANCE).remove(new Integer(vertex));
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
	
	
<<<<<<< HEAD
	public void decreaseKey(int vertex, int currentDistanceToVertex){
		if (distance[vertex] == box.MAX_DISTANCE){
			setVisited(vertex);
=======
	
	
	
	public void setCosts(int from, int to, int totalDistanceToVertex){
		if (costs[to] == bucket.MAX_WEIGHT){
			//System.out.println("remove: " + to);
		 //	System.out.println("size infinity1 "+ costInfinity.size());
			
			bucket.buckets.get(bucket.MAX_WEIGHT).remove(new Integer(to));
			
			//System.out.println("size infinity "+ costInfinity.size());
>>>>>>> 0bf049f730dc2a97aa1514e95de0172a5b2b934e
		}
		else{
			//Removed old costs of bucket of vertex
			box.buckets.get(distance[vertex]).remove(new Integer(vertex));
			
		}
		
		//Replace to new costs
		distance[vertex] = currentDistanceToVertex;
		box.buckets.get(currentDistanceToVertex).add(vertex);	
	}

	@Override
	public int[] getDistanceTotal() {
		// TODO Auto-generated method stub
		return distance;
	}
}