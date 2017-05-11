package br.puc.rio.inf.paa.file;

import br.puc.rio.inf.paa.djikstra.array.Edge;
import br.puc.rio.inf.paa.djikstra.array.GraphInstance;

public class PrincipalTeste {

	public static void main(String[] args) {
		
		String file =  "../INF-2926/input/alue2087.stp";
		
		ReadFile readFile = new ReadFile(file);
		GraphInstance graphInstance = readFile.CreateInstance();
		
		for(int key:graphInstance.graph.keySet()){
			System.out.println("Vertice: "+key);
			for(Edge edge:graphInstance.graph.get(key)){
				System.out.println(edge.vertex + " " + edge.distance);
			}
		}
		
	}

}
