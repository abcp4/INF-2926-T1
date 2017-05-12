
package br.puc.rio.inf.paa.djikstra.array;
import java.util.*;


public class GraphInstance { 
	
	public Map<Integer, List<Edge>> graph; 
	public List<Integer> keys;
	
	public GraphInstance(Map<Integer, List<Edge>> graph) { 
		this.graph = graph;
		this.keys = new LinkedList<>(graph.keySet());
	}
	
	public DijkstraSolution dijkstra(int start, DijkstraStore store) { //Initialization store.BuildStore(this, start);
	//While there are nodes unmarked in the store 
		//Initialization 
	
		store.buildStore(this, start);
		
		while (!store.isEmpty()) { 
			
			 int indexMinNode = store.getMin();//Get node with min d[j]
			
			
			if (store.costs[indexMinNode] == Integer.MAX_VALUE) { 
				break;
			}
			store.mark(indexMinNode); //Mark node
			
			//Relax all edges leaving the node 
			
			
			int v = keys.get(indexMinNode);
			List<Edge> edges = graph.get(v);
			
			
			for(int i = 0; i < edges.size(); i++) {
				
				int indexEdgeV = getVertex(edges.get(i).vertex);
				store.relax(indexMinNode, indexEdgeV, edges.get(i).distance);
			}
			
		}
	
		return new DijkstraSolution(store.costs, store.tree);
		
	}
	
	
	public int getVertex(int v){
		
		
		for(int i = 0; i< keys.size() ; i++){
			
			if(keys.get(i) == v){
				
				return i;
			}
		}
		
		return -1;
		
	}
	

	

}