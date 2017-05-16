package br.puc.rio.inf.paa.dijkstra.alphatree;

import br.puc.rio.inf.paa.djikstra.GraphInstance;
import br.puc.rio.inf.paa.utils.ReadAllFiles;
import br.puc.rio.inf.paa.utils.ReadFile;

public class TestAlphaTree {
	
	public static void main(String[] args) {
		
		ReadAllFiles readAllFiles = new ReadAllFiles();
		
		DijkstraAlphaTree store = new DijkstraAlphaTree();
		
		GraphInstance instance = new ReadFile("../INF-2926/input/teste.stp").createInstance();
		
		instance.dijkstra(1, store);
		
		for(int i = 1; i <= store.numVertices; i++){
			System.out.println("Vertice: "+i+" , Custo: "+store.getCusto()[i]);
		}
	}
}
