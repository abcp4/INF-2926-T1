package br.puc.rio.inf.paa.file;

import br.puc.rio.inf.paa.djikstra.array.GraphInstance;

public class PrincipalTeste {

	public static void main(String[] args) {
		
		ReadAllFiles readAllFiles = new ReadAllFiles();
		
		for(GraphInstance graphInstance:readAllFiles.creatAllInstances()){
			System.out.println(graphInstance.graph.size());
		}
		
	}

}
