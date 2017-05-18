package br.puc.rio.inf.paa.dijkstra.alphatree;

import br.puc.rio.inf.paa.dijkstra.GraphInstance;
import br.puc.rio.inf.paa.dijkstra.IDijkstra;

public class DijkstraAlphaTree implements IDijkstra{

	AlphaTree alphaTreeCost;
	AlphaTree alphaTreeKey;
	boolean[] visited;
	int visitedNodes;
	int vertexTotal;
	
	public DijkstraAlphaTree() {
		this.alphaTreeCost = new AlphaTree();
		this.alphaTreeKey = new AlphaTree();
	}

	@Override
	public void initialize(GraphInstance instance, int start) {
		
		this.vertexTotal = instance.graph.size();
		
		for(int key:instance.graph.keySet()){
					
			if(key != start){
				this.alphaTreeCost.insert(key, Integer.MAX_VALUE);
				this.alphaTreeKey.insert(Integer.MAX_VALUE, key);
			}else{
				this.alphaTreeCost.insert(key, 0);
				this.alphaTreeKey.insert(0, key);
			}
		}
		
		alphaTreeKey.printCost();
		alphaTreeKey.printSize();
		
		this.visited = new boolean[instance.graph.size() + 1];
		
	}

	@Override
	public int getMin() {
		return this.alphaTreeCost.getMin();
	}

	@Override
	public int[] getDistanceTotal() {
		int[] custos = new int[this.vertexTotal+1];
		
		custos[0] = -1;
		
		for(int i = 1; i <= this.vertexTotal; i++){
			custos[i] = this.alphaTreeKey.findKey(i);
		}
		
		return custos;
	}

	@Override
	public int[] getPath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void relax(int from, int to, int distance) {
		
		if(this.alphaTreeKey.findKey(from) + distance < this.alphaTreeKey.findKey(to) && !this.visited[to]){
						
			this.alphaTreeCost.delete(this.alphaTreeKey.findKey(to));
		
			this.alphaTreeCost.insert(to, this.alphaTreeKey.findKey(from) + distance);
			
			this.alphaTreeKey.findKeyAndUpdate(to, this.alphaTreeKey.findKey(from) + distance);
		}
		
	}

	@Override
	public void setVisited(int vertice) {
		this.visited[vertice] = true;
		
	}


}
