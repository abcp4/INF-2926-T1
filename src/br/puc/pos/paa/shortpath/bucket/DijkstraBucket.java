
package br.puc.pos.paa.shortpath.bucket;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import br.puc.rio.inf.paa.djikstra.Adjacent;
import br.puc.rio.inf.paa.djikstra.GraphInstance;
import br.puc.rio.inf.paa.djikstra.IDijkstra;


public class DijkstraBucket implements IDijkstra {

	Bucket bucket;
	List<LinkedList<Integer>> nodesRef;
	List<LinkedList<Integer>> costInfinity;
	int path[];
	int costs[];
	int pos_index;
	int nodesMarked = 0;

	@Override
	public void init(GraphInstance graph, int start) {

		int maxEdge = maxCostEdge(graph);
		bucket = new Bucket(graph.graph.size(), maxEdge);
		
		costs = new int[graph.graph.size()+1];
		path = new int[graph.graph.size()+1];
		
		pos_index = 0;
		
		nodesRef = new ArrayList<LinkedList<Integer>>(graph.graph.size());
		costInfinity = new ArrayList<LinkedList<Integer>>(graph.graph.size());
		
	
	}

	@Override
	public int getMin() {
		// TODO Auto-generated method stub
		for(int i = pos_index; i < bucket.buckets.size(); i++){
			int temp = bucket.buckets.get(i).getFirst();
			bucket.buckets.get(i).removeFirst();
			
			pos_index = i;
			return temp;
		}
		
		LinkedList<Integer> costInfinityAux = costInfinity.get(0); //TODO - fix this
		costInfinity.remove(0);
		
		return costInfinityAux.get(0);

	}

	@Override
	public int[] getCusto() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getPath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void relax(int from, int to, int distance) {
		// TODO Auto-generated method stub
		
		if (costs[from] != Integer.MAX_VALUE && costs[from] + distance < costs[to]) {
			costs[to] = costs[from] + distance;
			path[to] = from;
		}

	}

	@Override
	public void mark(int vertice) {
		// TODO Auto-generated method stub
		nodesMarked++;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	// TODO TERMINAR ESTE MÉTODO
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
}
