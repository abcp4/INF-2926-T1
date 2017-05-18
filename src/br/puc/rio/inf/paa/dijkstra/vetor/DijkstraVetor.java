package br.puc.rio.inf.paa.dijkstra.vetor;

import br.puc.rio.inf.paa.dijkstra.GraphInstance;
import br.puc.rio.inf.paa.dijkstra.IDijkstra;

public class DijkstraVetor implements IDijkstra {

	int distance[];
	int path[];
	boolean visited[];
	int visitedTotal;
	GraphInstance instance; 
    


	@Override
	public void initialize(GraphInstance graphInstance, int start) {

		instance = graphInstance;
		distance = new int[graphInstance.graph.size() + 1];
		path = new int[graphInstance.graph.size() + 1];
		
		visited = new boolean[graphInstance.graph.size() + 1];
		
		distance[0] = Integer.MAX_VALUE;
		path[0] = Integer.MAX_VALUE;

		for (int vertex : instance.graph.keySet()) {

			if (vertex == start) {
				distance[vertex] = 0;
				path[vertex] = -1;
			} else {
				distance[vertex] = Integer.MAX_VALUE;
			}
		}

	}

	@Override
	public int getMin() {

		int min = Integer.MAX_VALUE;
		int element = -1;

		for (int v : instance.graph.keySet()) {
		
			if (!(visited[v]) && min > distance[v]) {
				min = distance[v];
				element = v;
			}

		}
		return element;
	}

	
	@Override
	public void relax(int vertexA, int vertexB, int distanceAB) {

		if (distance[vertexA] != Integer.MAX_VALUE && distance[vertexA] + distanceAB < distance[vertexB]) {
			distance[vertexB] = distance[vertexA] + distanceAB;
			path[vertexB] = vertexA;
		}
	}

	@Override
	public void setVisited(int vertice) {
		visited[vertice] = true;
		visitedTotal ++;

	}

	public int[] getDistanceTotal() {
		return distance;
	}

	public void setCusto(int[] custo) {
		this.distance = custo;
	}

	public int[] getPath() {
		return path;
	}

	public void setPath(int[] path) {
		this.path = path;
	}
	
	

}
