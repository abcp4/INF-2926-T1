package br.puc.rio.inf.paa.djikstra.heap.fibonacci;

import br.puc.rio.inf.paa.djikstra.GraphInstance;
import br.puc.rio.inf.paa.djikstra.IDijkstra;

public class DijkstraFibonacciHeap implements IDijkstra {

	int custo[];
	FibonacciHeap fibonacciHeap;
	FibonacciHeap.Node[] nodeArray;

	@Override
	public void init(GraphInstance graph, int start) {
		custo = new int[graph.graph.size() + 1];
		fibonacciHeap = new FibonacciHeap();
		nodeArray = new FibonacciHeap.Node[graph.graph.size() + 1];
		custo[0] = Integer.MAX_VALUE;

		for (int vertex : graph.graph.keySet()) {

			if (vertex == start) {
				custo[vertex] = 0;
			} else {
				custo[vertex] = Integer.MAX_VALUE;
			}
			nodeArray[vertex] = fibonacciHeap.insert(vertex, custo[vertex]);
		}

	}

	@Override
	public int getMin() {

		FibonacciHeap.Node min = fibonacciHeap.returnMin();

		if (min != null) {
			return min.getKeyValue();
		} else {
			return -1;
		}
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
	public void relax(int vertexA, int vertexB, int distance) {
		
		if (custo[vertexA] != Integer.MAX_VALUE && custo[vertexA] + distance < custo[vertexB]) {
			custo[vertexB] = custo[vertexA] + distance;
			fibonacciHeap.decreaseKey(nodeArray[vertexB], custo[vertexB]);
		}

	}

	@Override
	public void mark(int vertice) {
		try {
			fibonacciHeap.removeMin();
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}



}
