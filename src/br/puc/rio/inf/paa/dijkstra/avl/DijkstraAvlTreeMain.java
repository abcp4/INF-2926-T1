package br.puc.rio.inf.paa.dijkstra.avl;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.TimeUnit;

import br.puc.inf.paa.dijkstra.bucket.DijkstraBucketMain;
import br.puc.rio.inf.paa.dijkstra.DijkstraSolution;
import br.puc.rio.inf.paa.dijkstra.GraphInstance;
import br.puc.rio.inf.paa.dijkstra.IDijkstra;
import br.puc.rio.inf.paa.dijkstra.heap.fibonacci.DijkstraFibonacciHeap;
import br.puc.rio.inf.paa.utils.CsvWriter;
import br.puc.rio.inf.paa.utils.ReadAllFiles;
import br.puc.rio.inf.paa.utils.ReadFile;
import br.puc.rio.inf.paa.utils.Utils;

public class DijkstraAvlTreeMain {

	public static void main(String[] args) {
		
		String nameCVSVetor = "nameCVSAVLTree.csv";
	
		CsvWriter writer = new CsvWriter(nameCVSVetor, ',', Charset.forName("ISO-8859-1"));

		
		List<GraphInstance> instances = new ReadAllFiles().creatAllInstances();

		int count = 0;
		int numInstance = 0;

		int timeout = 5;
		double temp_final = 0.0;
		double durationEnd = 0.0;
		
		try {
			writer.write("Name Instance");
			writer.write("Number of Vertex");
			writer.write("Number of Edge");
			writer.write("Average time");
			writer.write("Theoretical complexity");

			writer.endRecord();

		} catch (IOException e) {
			e.printStackTrace();
		}
	
		for (GraphInstance instance : instances) {

			IDijkstra iDijkstra = new DijkstraAvlTreeStore();
			double temp_inicio = System.currentTimeMillis();
			DijkstraSolution solution = null; 
			
			while (durationEnd <= timeout) {	
				solution = instance.dijkstra(1, iDijkstra);
				temp_final = System.currentTimeMillis();

				durationEnd = temp_final - temp_inicio;

				count++;
			}
			
			numInstance++;
			
			try {
				//O(V*log(V) + E*log(V))
				double complexity = instance.numVertex *  Utils.logBase2(instance.numVertex) 
								  + instance.numEdges * Utils.logBase2(instance.numVertex); 
				
				writer.write(instance.name);
				writer.write(String.valueOf(instance.numVertex));
				writer.write(String.valueOf(instance.numEdges));
				writer.write(String.valueOf((durationEnd / count)));
				writer.write(String.valueOf(complexity));

				writer.endRecord();

			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("No Instance: " + numInstance);
			System.out.println(instance.name);
			System.out.println("N: " + instance.numVertex + " x " + "M: " + instance.numEdges);
			System.out.println("Quantidade de vezes: " + count);
			System.out.println("Tempo medio: " +  durationEnd / count);
			System.out.println();
			
			
			count = 0;
			durationEnd = 0;
		}
		writer.close();
	}

	public void testDjikstraSimple() {
		GraphInstance instance = new ReadFile("../INF-2926/input/teste.stp").createInstance();

		IDijkstra iDijkstra = new DijkstraAvlTreeStore();
		DijkstraSolution solution = instance.dijkstra(1, iDijkstra);

		for (int i = 1; i < solution.distance.length; i++) {
			
			System.out.println("From: " + solution.path[i] + " To: " + i + " Custo: " + solution.distance[i]);
		}

	}

	public void testDjistraReadAllInstances() {

		List<GraphInstance> instances = new ReadAllFiles().creatAllInstances();
		int count = 0;
		IDijkstra iDijkstra = new DijkstraAvlTreeStore();
		for (GraphInstance graphInstance : instances) {

			DijkstraSolution solution = graphInstance.dijkstra(1, iDijkstra);

			for (int i = 0; i < solution.distance.length; i++) {
				System.out.println(graphInstance.name);
				System.out.println("N: " + graphInstance.numVertex + " x " + "M: " + graphInstance.numEdges);
				System.out.println(  "From: " + solution.path[i] + " To: " + i + " Custo: " + solution.distance[i]);
			}
			count++;

		}
	}
}
