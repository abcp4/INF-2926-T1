package br.puc.inf.paa.djikstra.bucket;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import br.puc.rio.inf.paa.dijkstra.avl.DijikstraAvlTreeStore;
import br.puc.rio.inf.paa.dijkstra.avl.DjikstraAvlTreeMain;
import br.puc.rio.inf.paa.djikstra.Adjacent;
import br.puc.rio.inf.paa.djikstra.DijkstraSolution;
import br.puc.rio.inf.paa.djikstra.GraphInstance;
import br.puc.rio.inf.paa.djikstra.IDijkstra;
import br.puc.rio.inf.paa.utils.CsvWriter;
import br.puc.rio.inf.paa.utils.ReadAllFiles;
import br.puc.rio.inf.paa.utils.ReadFile;

public class DijkstraBucketMain {
	
		public static void main(String[] args) {

			new DijkstraBucketMain().testDjikstraSimple();
//			String nameCVSVetor = "nameCVSAVLTree.csv";
//
//			CsvWriter writer = new CsvWriter(nameCVSVetor, ',', Charset.forName("ISO-8859-1"));
//
//			
//			List<GraphInstance> instances = new ReadAllFiles().creatAllInstances();
//
//			int count = 0;
//			int numInstance = 0;
//
//			int timeout = 5;
//			double temp_final = 0.0;
//			double durationEnd = 0.0;
//			
//			try {
//				writer.write("Name Instance");
//				writer.write("Number of Vertex");
//				writer.write("Number of Edge");
//				writer.write("Average time");
//
//				writer.endRecord();
//
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		
//			for (GraphInstance instance : instances) {
//
//				IDijkstra iDijkstra = new DijkstraBucket();
//				double temp_inicio = System.currentTimeMillis();
//				DijkstraSolution solution = null; 
//				
//				while (durationEnd <= timeout) {	
//					solution = instance.dijkstra(1, iDijkstra);
//					temp_final = System.currentTimeMillis();
//
//					durationEnd = temp_final - temp_inicio;
//
//					count++;
//				}
//				
//				numInstance++;
//				
//				try {
//					writer.write(instance.name);
//					writer.write(String.valueOf(instance.numVertex));
//					writer.write(String.valueOf(instance.numEdges));
//					writer.write(String.valueOf((durationEnd / count)));
//
//					writer.endRecord();
//
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//				System.out.println("No Instance: " + numInstance);
//				System.out.println(instance.name);
//				System.out.println("N: " + instance.numVertex + " x " + "M: " + instance.numEdges);
//				System.out.println("Quantidade de vezes: " + count);
//				System.out.println("Tempo medio: " +  durationEnd / count);
//				System.out.println();
//				
//				
//				count = 0;
//				durationEnd = 0;
//			}

		}

		public void testDjikstraSimple() {
			GraphInstance instance = new ReadFile("../INF-2926/input/teste.stp").createInstance();

			IDijkstra iDijkstra = new DijkstraBucket();
			DijkstraSolution solution = instance.dijkstra(1, iDijkstra);

			for (int i = 1; i < solution.costs.length; i++) {
				
				System.out.println( " To: " + i + " Custo: " + solution.costs[i]);
			}

		}

		public void testDjistraReadAllInstances() {

			List<GraphInstance> instances = new ReadAllFiles().creatAllInstances();
			int count = 0;
			IDijkstra iDijkstra = new DijkstraBucket();
			for (GraphInstance graphInstance : instances) {

				DijkstraSolution solution = graphInstance.dijkstra(1, iDijkstra);

				for (int i = 0; i < solution.costs.length; i++) {
					System.out.println(graphInstance.name);
					System.out.println("N: " + graphInstance.numVertex + " x " + "M: " + graphInstance.numEdges);
					System.out.println(  "From: " + solution.tree[i] + " To: " + i + " Custo: " + solution.costs[i]);
				}
				count++;

			}
		}
}
