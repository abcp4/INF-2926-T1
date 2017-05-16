package br.puc.rio.inf.paa.dijkstra.alphatree;

import br.puc.rio.inf.paa.dijkstra.GraphInstance;
import br.puc.rio.inf.paa.dijkstra.IDijkstra;

public class DijkstraAlphaTree implements IDijkstra{

	AlphaTree alphaTreeCost;
	AlphaTree alphaTreeKey;
	boolean[] marked;
	int nodesMarked;
	int numVertices;
	
	public DijkstraAlphaTree() {
		this.alphaTreeCost = new AlphaTree();
		this.alphaTreeKey = new AlphaTree();
	}

	@Override
	public void init(GraphInstance graph, int start) {
		
		this.numVertices = graph.graph.size();
		
		for(int key:graph.graph.keySet()){
					
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
		
		this.marked = new boolean[graph.graph.size() + 1];
		
	}

	@Override
	public int extractMin() {
		return this.alphaTreeCost.getMin();
	}

	@Override
	public int[] getCusto() {
		int[] custos = new int[this.numVertices+1];
		
		custos[0] = -1;
		
		for(int i = 1; i <= this.numVertices; i++){
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
		
		if(this.alphaTreeKey.findKey(from) + distance < this.alphaTreeKey.findKey(to) && !this.marked[to]){
			
//			System.out.println("Antes do delete: "+to);
//			alphaTreeCost.printCost();
//			alphaTreeCost.printSize();
			
			this.alphaTreeCost.delete(this.alphaTreeKey.findKey(to));
			
//			System.out.println("Depois do delete: "+to);
//			alphaTreeCost.printCost();
//			alphaTreeCost.printSize();
			
			this.alphaTreeCost.insert(to, this.alphaTreeKey.findKey(from) + distance);
			
//			System.out.println("Depois de inserir: "+to+", "+this.alphaTreeKey.findKey(from) + distance);
//			alphaTreeCost.printCost();
//			alphaTreeCost.printSize();
			
			this.alphaTreeKey.findKeyAndUpdate(to, this.alphaTreeKey.findKey(from) + distance);
		}
		
//		System.out.println("Raiz: "+alphaTreeCost.getRoot().getCost());
//		alphaTreeKey.printCost();
//		alphaTreeKey.printSize();
		
	}

	@Override
	public void setVisited(int vertice) {
		this.marked[vertice] = true;
		
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

}
