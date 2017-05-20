package br.puc.rio.inf.paa.dijkstra.alphatree;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import br.puc.rio.inf.paa.dijkstra.DijkstraSolution;
import br.puc.rio.inf.paa.dijkstra.GraphInstance;
import br.puc.rio.inf.paa.dijkstra.IDijkstra;
import br.puc.rio.inf.paa.dijkstra.avl.DijkstraAvlTreeStore;
import br.puc.rio.inf.paa.dijkstra.bucket.DijkstraBucket;
import br.puc.rio.inf.paa.utils.CsvWriter;
import br.puc.rio.inf.paa.utils.ReadAllFiles;
import br.puc.rio.inf.paa.utils.ReadFile;
import br.puc.rio.inf.paa.utils.Utils;

public class DijkstraAlphaTreeMain {
	
	public static void main(String[] args) {
			
		String nameCSV = "nameCSVAlphaTree.csv";

		CsvWriter writer = new CsvWriter(nameCSV, ',', Charset.forName("ISO-8859-1"));

		// new DijkstraVetorMain().testDjistraReadAllInstances();

		List<GraphInstance> instances = new ReadAllFiles().creatAllInstances();

		int count = 0;
		int numInstance = 0;

		int timeout = 5;
		double temp_final = 0.0;
		double durationEnd = 0.0;
		double ctTime = 0.0;
		double cpuTime = 0.0;
		
		try {
			writer.write("Instance");
			writer.write("N");
			writer.write("M");
			writer.write("N+M");
			writer.write("CPU");
			writer.write("CT");
			writer.write("CT/CPU");
			writer.write("Log(CPU)");
			writer.write("Log(CT/CPU)");
		
			writer.endRecord();		

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for (GraphInstance instance : instances) {

			IDijkstra iDijkstra = new DijkstraAlphaTree();
			double temp_inicio = System.nanoTime();
			DijkstraSolution solution = null;

			while (durationEnd <= timeout) {
				solution = instance.dijkstra(1, iDijkstra);
				temp_final = System.nanoTime();

				durationEnd = temp_final - temp_inicio;

				count++;
			}

			numInstance++;
			try {
				ctTime = (instance.numVertex * instance.numVertex) + instance.numEdges;
				
				cpuTime = (durationEnd/count);
				
				cpuTime = cpuTime/100;
				
				int nm = instance.numEdges + instance.numVertex;
				
				writer.write(instance.name);
				
				double logCPU = Utils.logBase2(cpuTime);
				
				double logCTCPU = Utils.logBase2(ctTime/cpuTime);
				
				writer.write(String.valueOf(instance.numVertex));
				writer.write(String.valueOf(instance.numEdges));
				
				writer.write(String.valueOf(nm));
				
				writer.write(String.valueOf(cpuTime));
				
				writer.write(String.valueOf(ctTime));
				
				writer.write(String.valueOf(ctTime/cpuTime));
				
				writer.write(String.valueOf(logCPU));
				
				writer.write(String.valueOf(logCTCPU));
				
				writer.endRecord();		

			} catch (IOException e) {
				e.printStackTrace();
			}
			
			System.out.println("No Instance: " + numInstance);
			System.out.println(instance.name);
			System.out.println("N: " + instance.numVertex + " x " + "M: " + instance.numEdges);
			System.out.println("Quantidade de vezes: " + count);
			System.out.println("Tempo medio: " + (durationEnd / count));
			System.out.println("CT: " + ctTime);
			System.out.println();

			count = 0;
			durationEnd = 0;
		}
		writer.close();
	}
	
		public void testDjikstraSimple() {
			GraphInstance instance = new ReadFile("../INF-2926/input/teste.stp").createInstance();
	
			IDijkstra iDijkstra = new DijkstraAlphaTree();
			DijkstraSolution solution = instance.dijkstra(1, iDijkstra);
	
			for (int i = 1; i < solution.distance.length; i++) {
				
				System.out.println("From: " + solution.path[i] + " To: " + i + " Custo: " + solution.distance[i]);
			}
	
		}
	
		public void testDjistraReadAllInstances() {
	
			List<GraphInstance> instances = new ReadAllFiles().creatAllInstances();
			int count = 0;
			IDijkstra iDijkstra = new DijkstraAlphaTree();
			for (GraphInstance graphInstance : instances) {
	
				DijkstraSolution solution = graphInstance.dijkstra(1, iDijkstra);
	
				for (int i = 0; i < solution.distance.length; i++) {
					System.out.println(graphInstance.name);
					System.out.println("N: " + graphInstance.numVertex + " x " + "M: " + graphInstance.numEdges);
					System.out.println(  "From: " + solution.path[i] + " To: " + i + " Custo: " + solution.path[i]);
				}
				count++;
	
			}
		}
		
}
