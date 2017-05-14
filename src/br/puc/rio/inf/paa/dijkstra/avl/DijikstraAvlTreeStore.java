package br.puc.rio.inf.paa.dijkstra.avl;

import br.puc.rio.inf.paa.djikstra.GraphInstance;
import br.puc.rio.inf.paa.djikstra.IDijkstra;

public class DijikstraAvlTreeStore implements IDijkstra{
	
	AvlTree avlTreeCost;
	AvlTree avlTreeKey;
	boolean[] marked;
	int nodesMarked;
	int numVertices;
	
	public DijikstraAvlTreeStore() {
		
		this.avlTreeCost = new AvlTree();
		this.avlTreeKey = new AvlTree();
		
	}

	@Override
	public void init(GraphInstance graph, int start) {
		
		this.numVertices = graph.graph.size();
		
		for(int key:graph.graph.keySet()){
					
			if(key != start){
				this.avlTreeCost.insert(key, Integer.MAX_VALUE);
				this.avlTreeKey.insert(Integer.MAX_VALUE, key);
			}else{
				this.avlTreeCost.insert(key, 0);
				this.avlTreeKey.insert(0, key);
			}
		}
		
		this.marked = new boolean[graph.graph.size() + 1];
		
	}

	@Override
	public int getMin() {
		return this.avlTreeCost.getMin();
	}

	@Override
	public int[] getCusto() {
		int[] custos = new int[this.numVertices+1];
		
		custos[0] = -1;
		
		for(int i = 1; i <= this.numVertices; i++){
			custos[i] = this.avlTreeKey.findKey(i);
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
		
		if(this.avlTreeKey.findKey(from) + distance < this.avlTreeKey.findKey(to) && !this.marked[to]){
			
			this.avlTreeCost.delete(this.avlTreeKey.findKey(to));
			this.avlTreeCost.insert(to, this.avlTreeKey.findKey(from) + distance);
			
			this.avlTreeKey.findKeyAndUpdate(to, this.avlTreeKey.findKey(from) + distance);
		}
		
	}

	@Override
	public void mark(int vertice) {
		this.marked[vertice] = true;
		
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

}
