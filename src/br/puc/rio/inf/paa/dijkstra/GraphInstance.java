
package br.puc.rio.inf.paa.dijkstra;

import java.util.*;

public class GraphInstance {

	public Map<Integer, List<Adjacent>> graph;
	public String name;
	public int numVertex;
	public int numEdges;
	public int maxCostEdge;

	public GraphInstance(Map<Integer, List<Adjacent>> graph) {
		this.graph = graph;
		this.numVertex = graph.size();
	}

	
	public DijkstraSolution dijkstra(int vertexStart, IDijkstra dijkstra) {

		dijkstra.initialize(this, vertexStart);
		int minVertex = dijkstra.getMin();

		 do {
			
			if(graph.containsKey(minVertex)){
				
				dijkstra.setVisited(minVertex);
	
				for (int i = 0; i < graph.get(minVertex).size(); i++) {
					Adjacent adjacent = graph.get(minVertex).get(i);
					dijkstra.relax(minVertex, adjacent.vertex, adjacent.distance);
	
				}
			}
			
		   minVertex = dijkstra.getMin();
		  
		   
		}while (minVertex != -1);

		return new DijkstraSolution(dijkstra.getDistanceTotal(), dijkstra.getPath()); 

	}

}