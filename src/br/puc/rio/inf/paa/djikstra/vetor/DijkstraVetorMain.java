package br.puc.rio.inf.paa.djikstra.vetor;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import br.puc.rio.inf.paa.djikstra.Adjacent;
import br.puc.rio.inf.paa.djikstra.DijkstraSolution;
import br.puc.rio.inf.paa.djikstra.GraphInstance;
import br.puc.rio.inf.paa.djikstra.IDijkstra;
import br.puc.rio.inf.paa.djikstra.heap.fibonacci.DijkstraFibonacciHeap;
import br.puc.rio.inf.paa.utils.ReadAllFiles;
import br.puc.rio.inf.paa.utils.ReadFile;


public class DijkstraVetorMain {

	// TODO - change timer
	public static void main(String[] args) {
		
//		new DijkstraVetorMain().testDjistraReadAllInstances();

		List<GraphInstance> instances = new ReadAllFiles().creatAllInstances();

		int count = 0;
		int numInstance = 0;

		int timeout = 5;
		double temp_final = 0.0;
		double durationEnd = 0.0;
	
		for (GraphInstance instance : instances) {

			IDijkstra iDijkstra = new DijikstraVetor();
			double temp_inicio = System.currentTimeMillis();
			DijkstraSolution solution = null; 
			
			while (durationEnd <= timeout) {	
				solution = instance.dijkstra(1, iDijkstra);
				temp_final = System.currentTimeMillis();

				durationEnd = temp_final - temp_inicio;

				count++;
			}
			
			numInstance++;

			System.out.println("No Instance: " + numInstance);
			System.out.println(instance.name);
			System.out.println("N: " + instance.numVertex + " x " + "M: " + instance.numEdges);
			System.out.println("Quantidade de vezes: " + count);
			System.out.println("Tempo medio: " +  (durationEnd / count));
			System.out.println();
			
			
			count = 0;
			durationEnd = 0;
		}

	}

	public void testDjikstraSimple() {
		GraphInstance instance = new ReadFile("../INF-2926/input/teste.stp").createInstance();

		IDijkstra iDijkstra = new DijikstraVetor();
		DijkstraSolution solution = instance.dijkstra(1, iDijkstra);

		for (int i = 1; i < solution.costs.length; i++) {
			
			System.out.println("From: " + solution.tree[i] + " To: " + i + " Custo: " + solution.costs[i]);
		}

	}

	public void testDjistraReadAllInstances() {

		List<GraphInstance> instances = new ReadAllFiles().creatAllInstances();
		int count = 0;
		IDijkstra iDijkstra = new DijikstraVetor();
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
