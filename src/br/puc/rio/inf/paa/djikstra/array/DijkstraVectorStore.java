package br.puc.rio.inf.paa.djikstra.array;

import java.util.*;

public class DijkstraVectorStore extends DijkstraStore { 
	
	int nodesMarked;
	int[] route;
	
	public int getMin(){
		return 0;
	}
	public int getMin(GraphInstance g) { 
		int i = 0; 
		int min = Integer.MAX_VALUE;
		int element = -1;
		
		for (i = 0; i < costs.length; i++) {
			if (!marked[i] && g.graph.containsKey(i)) { // senao foi marcado 
				min = costs[i]; 
				element = i;
				i++; 
				break; 
			}
		}
		
		for (; i < costs.length; i++) {
			if (!(marked[i]) && min > costs[i] && g.graph.containsKey(i)) { 
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
	

	
	public void buildStore(GraphInstance g, int start) { 
				
		costs = new int[g.graph.size() + 1];
		tree =  new int[g.graph.size() + 1];
		marked = new boolean[g.graph.size() + 1];
		
		for (Integer key :  g.graph.keySet()) { 
	        
			if (key != start) { 
				costs[key] = Integer.MAX_VALUE; 		
			} 
			else { 
				costs[key] = 0;
				tree[key] = -1;
				
			}
		}
		
	}
	
	
	
	
	public boolean isEmpty() { 
		return nodesMarked == marked.length - 1;
	}

	

}