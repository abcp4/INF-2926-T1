
package br.puc.rio.inf.paa.djikstra;

import java.util.*;

public class GraphInstance {

	public Map<Integer, List<Adjacent>> graph;

	public GraphInstance(Map<Integer, List<Adjacent>> graph) {
		this.graph = graph;
	}

	public DijkstraSolution dijkstra(int vertexStart, IDijkstra dijkstra) {

		dijkstra.init(this, vertexStart);
		int minVertex = dijkstra.getMin();

		 do {
			
			if(graph.containsKey(minVertex)){
				
				dijkstra.mark(minVertex);
	
				for (int i = 0; i < graph.get(minVertex).size(); i++) {
					Adjacent adjacent = graph.get(minVertex).get(i);
					dijkstra.relax(minVertex, adjacent.vertex, adjacent.distance);
	
				}
			}
			
		   minVertex = dijkstra.getMin();
		}while (minVertex != -1);

		return new DijkstraSolution(dijkstra.getCusto(), dijkstra.getPath()); 

	}

}