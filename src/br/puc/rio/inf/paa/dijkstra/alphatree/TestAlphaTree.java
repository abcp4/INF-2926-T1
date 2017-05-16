package br.puc.rio.inf.paa.dijkstra.alphatree;

import br.puc.rio.inf.paa.djikstra.GraphInstance;
import br.puc.rio.inf.paa.utils.ReadAllFiles;
import br.puc.rio.inf.paa.utils.ReadFile;

public class TestAlphaTree {
	
	public static void main(String[] args) {
		
		ReadAllFiles readAllFiles = new ReadAllFiles();
		
		DijkstraAlphaTree store = new DijkstraAlphaTree();
		
//		for(GraphInstance instance:readAllFiles.creatAllInstances()){
//			instance.dijkstra(1, store);
//			
//			for(int i = 1; i <= store.numVertices; i++){
//				System.out.println("Vertice: "+i+" , Custo: "+store.getCusto()[i]);
//			}
//			
//			break;
//		}
		
		GraphInstance instance = new ReadFile("../INF-2926/input/teste.stp").createInstance();
		
		instance.dijkstra(1, store);
		
		for(int i = 1; i <= store.numVertices; i++){
			System.out.println("Vertice: "+i+" , Custo: "+store.getCusto()[i]);
		}
//		
		
//		AlphaTree tree = new AlphaTree();
//		
//		tree.insert(100, 10);
//		tree.insert(101, 9);
//		tree.insert(102, 8);
//		tree.insert(103, 7);
//		tree.insert(104, 6);
//		tree.insert(105, 5);
//		tree.insert(106, 4);
//		tree.insert(107, 3);
//		tree.insert(108, 2);
//		tree.insert(109, 1);
//		
//		tree.delete(8);
//		tree.delete(2);
//		tree.delete(6);
//		
//		tree.printCost();
//		tree.printSize();
		
		
	}
}
