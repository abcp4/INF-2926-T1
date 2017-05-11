package br.puc.rio.inf.paa.djikstra.array;

import java.util.*;
public class DijkstraMain {
	
	public static void main(String[] args){
		
		Map<Integer, List<Edge>> graph = new LinkedHashMap<>();
		
		Edge e1 = new Edge(7, 10);
		Edge e2 = new Edge(3, 15);
		Edge e3 = new Edge(4, 20);
		
		
	    graph.put(1, Arrays.asList(e1, e2));//1 --> 7, 3
	    graph.put(7, Arrays.asList(e2, e3));//7 --> 3, 4
	    graph.put(3, Arrays.asList());
	    graph.put(4, Arrays.asList());

	    GraphInstance instance = new GraphInstance(graph);
	    DijkstraVectorStore vectorStore = new DijkstraVectorStore();
	    
	    DijkstraSolution solution = instance.dijkstra(1,  vectorStore);
	    
	    
	    for(int i = 0; i< vectorStore.marked.length; i++){
	    	
	 	    if(vectorStore.marked[i]){
	 	    	System.out.println(i);
	 	    }
	    }
	   
	    
	
	   
	   
	}

}
