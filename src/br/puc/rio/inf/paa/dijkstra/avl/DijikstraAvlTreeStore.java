package br.puc.rio.inf.paa.dijkstra.avl;

import br.puc.rio.inf.paa.djikstra.array.DijkstraStore;
import br.puc.rio.inf.paa.djikstra.array.GraphInstance;

public class DijikstraAvlTreeStore extends DijkstraStore{
	
	AvlTree avlTree;
	
	public DijikstraAvlTreeStore() {
		
		this.avlTree = new AvlTree();
		
	}

	@Override
	public int getMin() {
		return 0;
	}

	@Override
	public int getMin(GraphInstance g) {
		
		return 0;
	}

	@Override
	public void relax(int v, int w, int lvw) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mark(int v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void buildStore(GraphInstance instance, int start) {
		
		for(int key:instance.graph.keySet()){
			
			if(key != start){
				this.avlTree.insert(key, Integer.MAX_VALUE);
			}else{
				this.avlTree.insert(key, 0);
			}
		}
	}
	
	

}
