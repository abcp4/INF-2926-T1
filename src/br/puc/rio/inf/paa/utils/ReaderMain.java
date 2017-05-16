package br.puc.rio.inf.paa.utils;

import br.puc.rio.inf.paa.dijkstra.GraphInstance;

public class ReaderMain {

	public static void main(String[] args) {
		
		ReadAllFiles readAllFiles = new ReadAllFiles();
		
		for(GraphInstance graphInstance:readAllFiles.creatAllInstances()){
			System.out.println(graphInstance.graph.size());
		}
		
	}

}
