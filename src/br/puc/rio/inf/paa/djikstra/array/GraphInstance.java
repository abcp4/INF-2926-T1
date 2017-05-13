
package br.puc.rio.inf.paa.djikstra.array;
import java.util.*;


public class GraphInstance { 
	
	public Map<Integer, List<Edge>> graph; 
	
	public GraphInstance(Map<Integer, List<Edge>> graph) { 
		this.graph = graph;
	}
	
	public DijkstraSolution dijkstra(int start, DijkstraStore store) { //Initialization store.BuildStore(this, start);
	//While there are nodes unmarked in the store 
		//Initialization 
	
		store.buildStore(this, start);
		
		while (!store.isEmpty()) { 
			
			 int minNode = store.getMin(this);            //Get node with min d[j]
			if(graph.containsKey(minNode)){
				//System.out.println("min");
				if (store.costs[minNode] == Integer.MAX_VALUE) { 
					break;
				}
				
				store.mark(minNode); //Mark node
				
				//Relax all edges leaving the node 			

				List<Edge> edges = graph.get(minNode);
							
				for(int i = 0; i < edges.size(); i++) {
					
					int adjV = edges.get(i).vertex;
					//System.out.println(adjV);
					store.relax(minNode, adjV, edges.get(i).distance);
				}
				
		   }
		}
	
		return new DijkstraSolution(store.costs, store.tree);
		
	}
	
	
	

}