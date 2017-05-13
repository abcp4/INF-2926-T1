package br.puc.rio.inf.paa.dijkstra.avl;

import java.util.List;

import br.puc.rio.inf.paa.djikstra.array.GraphInstance;
import br.puc.rio.inf.paa.file.ReadAllFiles;

public class TestAvlTree {

	public static void main(String[] args) {
		
		ReadAllFiles readAllFiles = new ReadAllFiles();
		
		List<GraphInstance> instances = readAllFiles.creatAllInstances();
		
		
		
		for(GraphInstance instance:instances){
			DijikstraAvlTreeStore store = new DijikstraAvlTreeStore();
			store.buildStore(instance, 1);
			System.out.println(store.avlTree.getMin().getCost());
		}
		
		
		

	}

}
