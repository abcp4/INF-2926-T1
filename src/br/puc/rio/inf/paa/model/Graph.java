package br.puc.rio.inf.paa.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.puc.rio.inf.paa.depressao.Adjacent;
import br.puc.rio.inf.paa.djikstra.array.Edge;

public class Graph {
	
	private Map<Integer, List<Adjacent>> vertices;
	
	public Graph(int numNodes){
		
		this.vertices = new HashMap<Integer, List<Adjacent>>();
		
		for(int i = 1; i <= numNodes; i++){
			this.vertices.put(i, new ArrayList<Adjacent>());
		}
		
	}
	
	public void addEdge(int source, int destination, int cost){
		
		this.vertices.get(source).add(new Adjacent(destination, cost));
	}

	public Map<Integer, List<Adjacent>> getVertices() {
		return vertices;
	}

	public void setVertices(Map<Integer, List<Adjacent>> vertices) {
		this.vertices = vertices;
	}
}
