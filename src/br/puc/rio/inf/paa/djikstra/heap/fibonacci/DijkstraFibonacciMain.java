package br.puc.rio.inf.paa.djikstra.heap.fibonacci;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import br.puc.rio.inf.paa.djikstra.Adjacent;
import br.puc.rio.inf.paa.djikstra.DijkstraSolution;
import br.puc.rio.inf.paa.djikstra.GraphInstance;
import br.puc.rio.inf.paa.djikstra.IDijkstra;
import br.puc.rio.inf.paa.utils.ReadAllFiles;
import br.puc.rio.inf.paa.utils.ReadFile;



public class DijkstraFibonacciMain {

	// TODO - change timer
	public static void main(String[] args) {
		
		new DijkstraFibonacciMain().testDjikstraReadOneInstance();
	}

		
	public void testDjikstraSimple() {
		Map<Integer, List<Adjacent>> graph = new LinkedHashMap<>();

		Adjacent e1 = new Adjacent(2, 10);
		Adjacent e2 = new Adjacent(3, 15);
		Adjacent e3 = new Adjacent(4, 20);
		Adjacent e4 = new Adjacent(4, 2);

		graph.put(1, Arrays.asList(e1, e2)); // 1 --> 7, 3
		graph.put(2, Arrays.asList(e2, e3)); // 7 --> 3, 4
		graph.put(3, Arrays.asList(e4));
		graph.put(4, Arrays.asList());

		GraphInstance instance = new GraphInstance(graph);
		IDijkstra iDijkstra = new DijkstraFibonacciHeap();

		DijkstraSolution solution = instance.dijkstra(1, iDijkstra);

		for (int i = 1; i < solution.costs.length; i++) {
			System.out.println(solution.costs[i] + " index: " + i + "path: " + solution.tree[i]);
			// System.out.println(solution.tree[i]);
		}

	}

	public void testDjikstraReadOneInstance() {
		GraphInstance instance = new ReadFile("../INF-2926/input/teste.stp").createInstance();
		
		IDijkstra iDijkstra = new DijkstraFibonacciHeap();
		DijkstraSolution solution = instance.dijkstra(1, iDijkstra);
		
		for(int i = 1; i < solution.costs.length; i++){
			System.out.println(  "From: " + solution.tree[i] + " To: " + i + " Custo: " + solution.costs[i]);
		}
	}

	public void testDjistraReadAllInstances() {

		List<GraphInstance> instances = new ReadAllFiles().creatAllInstances();
		int count = 0;
		IDijkstra iDijkstra = new DijkstraFibonacciHeap();
		for (GraphInstance graphInstance : instances) {

			DijkstraSolution solution = graphInstance.dijkstra(1, iDijkstra);

			for (int i = 0; i < solution.costs.length; i++) {
				System.out.println(solution.costs[i] + " index: " + i + "path: " + solution.tree[i]);
				// System.out.println(solution.tree[i]);
			}
			count++;

		}
	}

}
