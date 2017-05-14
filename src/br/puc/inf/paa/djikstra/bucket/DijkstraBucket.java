package br.puc.inf.paa.djikstra.bucket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import br.puc.rio.inf.paa.djikstra.Adjacent;
import br.puc.rio.inf.paa.djikstra.GraphInstance;
import br.puc.rio.inf.paa.djikstra.IDijkstra;
import br.puc.rio.inf.paa.utils.CollectionsUtils;

public class DijkstraBucket implements IDijkstra {

	Bucket bucket;
	List<LinkedListNode> nodesRef;
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

		nodesRef = new LinkedList<LinkedListNode>(CollectionsUtils.setSize(graph.graph.size() + 1));
	
		costInfinity = new LinkedList<>();

		for (int vertex : graph.graph.keySet()) {
			if (vertex != start) {
				costs[vertex] = Integer.MAX_VALUE;
				
				LinkedList<Integer> newNode = new LinkedList<Integer>(CollectionsUtils.setSize(vertex));
				costInfinity.addLast(vertex);
				nodesRef.add(vertex, new LinkedListNode(newNode));
				
			}else{
				costs[start] = 0;
				
				LinkedList<Integer> newNode = new LinkedList<Integer>(CollectionsUtils.setSize(vertex));
				bucket.add(newNode, 0);
				path[start] = -1;
				nodesRef.add(vertex,  new LinkedListNode(newNode));
				
			}

		}

	}

	@Override
	public int getMin() {
		for (int i = pos_index; i < bucket.buckets.size(); i++) {
			if (bucket.buckets.get(i).size() > 0) {
				int temp = bucket.buckets.get(i).getFirst();
				bucket.buckets.get(i).removeFirst();
				pos_index = i;
				return temp;

			}

		}

		int aux = costInfinity.getFirst();
		costInfinity.remove(aux);
		return aux;

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

		if (costs[from] != Integer.MAX_VALUE && costs[from] + distance < costs[to]) {
			costs[to] = costs[from] + distance;
			path[to] = from;
			clearCosts(to, costs[to]);
		}

	}

	@Override
	public void mark(int vertice) {
		nodesMarked++;
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
					maxCost = edge.distance;
				}
			}
		}
		return maxCost;

	}
	
	
	public void clearCosts(int v, int distance){
		if (costs[v] == Integer.MAX_VALUE){
			
			costInfinity.remove(nodesRef.get(v));
		}
		else{
			bucket.buckets.get(costs[v]).remove(nodesRef.get(v));
		}
		costs[v] = distance;
		//bucket.buckets.get(distance).addAll(nodesRef.get(v));
	}
	
	
	
}
