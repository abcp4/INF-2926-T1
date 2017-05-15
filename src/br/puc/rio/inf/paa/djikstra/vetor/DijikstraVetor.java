package br.puc.rio.inf.paa.djikstra.vetor;

import br.puc.rio.inf.paa.djikstra.GraphInstance;
import br.puc.rio.inf.paa.djikstra.IDijkstra;

public class DijikstraVetor implements IDijkstra {

	int custo[];
	int path[];
	boolean marked[];
	int nodesMarked;



	@Override
	public void init(GraphInstance graphInstance, int start) {

		custo = new int[graphInstance.graph.size() + 1];
		path = new int[graphInstance.graph.size() + 1];
		marked = new boolean[graphInstance.graph.size() + 1];
		custo[0] = Integer.MAX_VALUE;
		path[0] = Integer.MAX_VALUE;

		for (int vertex : graphInstance.graph.keySet()) {

			if (vertex == start) {
				custo[vertex] = 0;
				path[vertex] = -1;
			} else {
				custo[vertex] = Integer.MAX_VALUE;
			}
		}

	}

	@Override
	public int getMin() {

		int min = Integer.MAX_VALUE;
		int element = -1;

		for (int i = 0; i < custo.length; i++) {
			if (!(marked[i]) && min > custo[i]) {
				min = custo[i];
				element = i;
			}

		}
		return element;
	}

	
	@Override
	public void relax(int vertexA, int vertexB, int distance) {

		if (custo[vertexA] != Integer.MAX_VALUE && custo[vertexA] + distance < custo[vertexB]) {
			custo[vertexB] = custo[vertexA] + distance;
			path[vertexB] = vertexA;
		}
	}

	@Override
	public void mark(int vertice) {
		marked[vertice] = true;
		nodesMarked++;

	}

	@Override
	public boolean isEmpty() {
		return nodesMarked == marked.length - 1;
	}

	public int[] getCusto() {
		return custo;
	}

	public void setCusto(int[] custo) {
		this.custo = custo;
	}

	public int[] getPath() {
		return path;
	}

	public void setPath(int[] path) {
		this.path = path;
	}
	
	

}
