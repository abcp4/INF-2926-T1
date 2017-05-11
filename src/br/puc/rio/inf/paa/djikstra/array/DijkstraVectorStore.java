package br.puc.rio.inf.paa.djikstra.array;

import java.util.*;

public class DijkstraVectorStore extends DijkstraStore { 
	
	int nodesMarked;
	int[] route;
	
	public int getMin() { 
		int i = 0; 
		int min = Integer.MAX_VALUE;
		int element = -1;
		
		for (i = 0; i < costs.length; i++) {
			if (!marked[i]) { // senao foi marcado 
				min = costs[i]; 
				element = i;
				i++; 
				break; 
			}
		}
		
		for (; i < costs.length; i++) {
			if (!(marked[i]) && min > costs[i]) { 
				min = costs[i];
				element = i; 
			}
		} 
		
		return element; 
	}
	
	public void relax(int v, int w, int distance) { 
		
		if (costs[v] != Integer.MAX_VALUE && costs[v] + distance < costs[w]) {
			costs[w] = costs[v] + distance;
			tree[w] = v; 
		} 
	}
	
	public void mark(int v) { 
		marked[v] = true; 
		
		nodesMarked++;
	}
	

	
	public void buildStore(GraphInstance g, int indexStart) { 
				
		costs = new int[g.graph.size()];
		tree =  new int[g.graph.size()];
		marked = new boolean[g.graph.size()];
		
		List<Integer> keys = new ArrayList<>(g.graph.keySet());
		
		for ( int currentIndex = 0 ; currentIndex < keys.size(); currentIndex ++) { 
	        
			if (currentIndex != indexStart) { 
				
				costs[currentIndex] = Integer.MAX_VALUE; 
				
			} 
			else { 
				costs[currentIndex] = 0;
				tree[currentIndex] = -1;
				
			}
		}
		
		
	}
	
	
	
	
	public boolean isEmpty() { 
		return nodesMarked == marked.length - 1;
	}

	

}