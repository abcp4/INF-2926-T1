package br.puc.rio.inf.paa.dijkstra.avl;

import java.util.List;

import br.puc.rio.inf.paa.djikstra.GraphInstance;
import br.puc.rio.inf.paa.utils.ReadAllFiles;
import br.puc.rio.inf.paa.utils.ReadFile;

public class DjikstraAvlTreeMain {

	public static void main(String[] args) {
		
		ReadAllFiles readAllFiles = new ReadAllFiles();
		
		DijikstraAvlTreeStore store = new DijikstraAvlTreeStore();
		
		GraphInstance instance = new ReadFile("../INF-2926/input/teste.stp").createInstance();
		
		instance.dijkstra(1, store);
		
		for(int i = 1; i <= store.numVertices; i++){
			System.out.println("Vertice: "+i+" , Custo: "+store.getCusto()[i]);
		}
//		
//		List<GraphInstance> instances = readAllFiles.creatAllInstances();
		
//		AvlTree avl = new AvlTree();
//		avl.insert(10, 7);
//		avl.insert(11, 4);
//		avl.insert(12, 10);
//		avl.insert(13, 1);
//		avl.insert(14, 6);
//		avl.insert(15, 8);
//		avl.insert(16, 12);
//		
//		DijikstraAvlTreeStore store = new DijikstraAvlTreeStore();
//		store.avlTreeKey = avl;
//		
//		System.out.println(avl.findKey(8));
//		avl.findKeyAndUpdate(8, 20);
//		System.out.println(avl.findKey(8));
//		
//		boolean[] a = new boolean[10];
//		System.out.println(a[1]);
		
	}

}
