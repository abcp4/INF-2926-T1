package br.puc.rio.inf.paa.dijkstra.heap.fibonacci;

import br.puc.rio.inf.paa.dijkstra.GraphInstance;
import br.puc.rio.inf.paa.dijkstra.IDijkstra;

public class DijkstraFibonacciHeap implements IDijkstra {

	int distance[];
	int path[];
	FibonacciHeap fibonacciHeap;
	FibonacciHeap.Node[] nodes;

	@Override
	public void initialize(GraphInstance graph, int start) {
		fibonacciHeap = new FibonacciHeap();
		distance = new int[graph.graph.size() + 1];
		nodes = new FibonacciHeap.Node[graph.graph.size() + 1];
		path = new int[graph.graph.size() + 1];
			
		distance[0] = Integer.MAX_VALUE;
		path[0] = Integer.MAX_VALUE;
		
		for (int vertex : graph.graph.keySet()) {

			if (vertex == start) {
				distance[vertex] = 0;
				path[vertex] = -1;
			} else {
				distance[vertex] = Integer.MAX_VALUE;
			}
			nodes[vertex] = fibonacciHeap.insert(vertex, distance[vertex]);
		}

	}

	@Override
	public int getMin() {

		FibonacciHeap.Node min = fibonacciHeap.returnMin();

		if (min != null) {
			return min.getNodeNumber();
		} else {
			return -1;
		}
	}

	@Override
	public int[] getDistanceTotal() {
		return distance;
	}

	@Override
	public int[] getPath() {
		
		return path;
	}

	@Override
	public void relax(int vertexA, int vertexB, int distanceAB) {

		if (distance[vertexA] != Integer.MAX_VALUE && distance[vertexA] + distanceAB < distance[vertexB]) {
			distance[vertexB] = distance[vertexA] + distanceAB;
			fibonacciHeap.decreaseKey(nodes[vertexB], distance[vertexB]);
			path[vertexB] = vertexA;

		}

	}

	@Override
	public void setVisited(int vertice) {
		try {
			fibonacciHeap.removeMin();
		} catch (Exception e) {

			e.printStackTrace();
		}

	}


}
