package br.puc.rio.inf.paa.dijkstra.avl;

import br.puc.rio.inf.paa.dijkstra.GraphInstance;
import br.puc.rio.inf.paa.dijkstra.IDijkstra;

public class DijkstraAvlTreeStore implements IDijkstra {

	AvlTree avlTreeCost;
	AvlTree avlTreeKey;
	boolean[] visited;
	int visitedNodes;
	int vertexTotal;

	public DijkstraAvlTreeStore() {
		this.avlTreeCost = new AvlTree();
		this.avlTreeKey = new AvlTree();
	}

	@Override
	public void initialize(GraphInstance instance, int start) {

		this.vertexTotal = instance.graph.size();

		for (int key : instance.graph.keySet()) {

			if (key != start) {
				this.avlTreeCost.insert(key, Integer.MAX_VALUE);
				this.avlTreeKey.insert(Integer.MAX_VALUE, key);
			} else {
				this.avlTreeCost.insert(key, 0);
				this.avlTreeKey.insert(0, key);
			}
		}

		this.visited = new boolean[instance.graph.size() + 1];

	}

	@Override
	public int getMin() {
		return this.avlTreeCost.getMin();
	}

	@Override
	public int[] getDistanceTotal() {
		int[] custos = new int[this.vertexTotal + 1];

		custos[0] = -1;

		for (int i = 1; i <= this.vertexTotal; i++) {
			custos[i] = this.avlTreeKey.findKey(i);
		}

		return custos;
	}

	@Override
	public int[] getPath() {
		return null;
	}

	@Override
	public void relax(int from, int to, int distance) {

		if (this.avlTreeKey.findKey(from) + distance < this.avlTreeKey.findKey(to) && !this.visited[to]) {

			this.avlTreeCost.delete(this.avlTreeKey.findKey(to));
			this.avlTreeCost.insert(to, this.avlTreeKey.findKey(from) + distance);

			this.avlTreeKey.findKeyAndUpdate(to, this.avlTreeKey.findKey(from) + distance);
		}

	}

	@Override
	public void setVisited(int vertice) {
		this.visited[vertice] = true;

	}

}
