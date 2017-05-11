package br.puc.rio.inf.paa.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.puc.rio.inf.paa.djikstra.array.Edge;

public class Graph {
	
	private Map<Integer, List<Edge>> vertices;
	
	public Graph(int numNodes){
		
		this.vertices = new HashMap<Integer, List<Edge>>();
		
		for(int i = 1; i <= numNodes; i++){
			this.vertices.put(i, new ArrayList<Edge>());
		}
		
	}
	
	public void addEdge(int source, int destination, int cost){
		
		this.vertices.get(source).add(new Edge(destination, cost));
	}

	public Map<Integer, List<Edge>> getVertices() {
		return vertices;
	}

	public void setVertices(Map<Integer, List<Edge>> vertices) {
		this.vertices = vertices;
	}
	
}
