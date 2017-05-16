package br.puc.rio.inf.paa.dijkstra.heap.fibonacci;

import br.puc.rio.inf.paa.dijkstra.GraphInstance;
import br.puc.rio.inf.paa.dijkstra.IDijkstra;

public class DijkstraFibonacciHeap implements IDijkstra {

	int custo[];
	int path[];
	FibonacciHeap fibonacciHeap;
	FibonacciHeap.Node[] nodeArray;

	@Override
	public void init(GraphInstance graph, int start) {
		fibonacciHeap = new FibonacciHeap();
		custo = new int[graph.graph.size() + 1];
		nodeArray = new FibonacciHeap.Node[graph.graph.size() + 1];
		path = new int[graph.graph.size() + 1];
			
		custo[0] = Integer.MAX_VALUE;
		path[0] = Integer.MAX_VALUE;
		
		for (int vertex : graph.graph.keySet()) {

			if (vertex == start) {
				custo[vertex] = 0;
				path[vertex] = -1;
			} else {
				custo[vertex] = Integer.MAX_VALUE;
			}
			nodeArray[vertex] = fibonacciHeap.insert(vertex, custo[vertex]);
		}

	}

	@Override
	public int extractMin() {

		FibonacciHeap.Node min = fibonacciHeap.returnMin();

		if (min != null) {
			return min.getNodeNumber();
		} else {
			return -1;
		}
	}

	@Override
	public int[] getCusto() {
		return custo;
	}

	@Override
	public int[] getPath() {
		
		return path;
	}

	@Override
	public void relax(int vertexA, int vertexB, int distance) {

		if (custo[vertexA] != Integer.MAX_VALUE && custo[vertexA] + distance < custo[vertexB]) {
			custo[vertexB] = custo[vertexA] + distance;
			fibonacciHeap.decreaseKey(nodeArray[vertexB], custo[vertexB]);
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

	@Override
	public boolean isEmpty() {
		return false;
	}

}
