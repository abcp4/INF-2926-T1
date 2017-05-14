package br.puc.inf.paa.djikstra.bucket;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import br.puc.rio.inf.paa.djikstra.Adjacent;
import br.puc.rio.inf.paa.djikstra.DijkstraSolution;
import br.puc.rio.inf.paa.djikstra.GraphInstance;
import br.puc.rio.inf.paa.djikstra.IDijkstra;
import br.puc.rio.inf.paa.utils.CsvWriter;
import br.puc.rio.inf.paa.utils.ReadAllFiles;

public class DijkstraBucketMain {

	
	
	public static void main(String[] args) {
	
		String nameCVSVetor = "nameCVSBucket.csv";

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

			writer.endRecord();

		} catch (IOException e) {
			e.printStackTrace();
		}

		for (GraphInstance instance : instances) {

			IDijkstra iDijkstra = new DijkstraBucket();
			double temp_inicio = System.currentTimeMillis();

			while (durationEnd <= timeout) {
				DijkstraSolution solution = instance.dijkstra(1, iDijkstra);
				temp_final = System.currentTimeMillis();

				durationEnd = temp_final - temp_inicio;

				count++;
			}

			numInstance++;
			try {
				writer.write(instance.name);
				writer.write(String.valueOf(instance.numVertex));
				writer.write(String.valueOf(instance.numEdges));
				writer.write(String.valueOf((durationEnd / count)));

				writer.endRecord();

			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("No Instance: " + numInstance);
			System.out.println("Quantidade de vezes: " + count);
			System.out.println("Tempo medio: " + (durationEnd / count));
			count = 0;
			durationEnd = 0;
		}

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
		IDijkstra iDijkstra = new DijkstraBucket();

		DijkstraSolution solution = instance.dijkstra(1, iDijkstra);

		for (int i = 0; i < solution.costs.length; i++) {
			System.out.println(solution.costs[i] + " index: " + i + "path: " + solution.tree[i]);
			// System.out.println(solution.tree[i]);
		}

	}

	public void testDjistraReadAllInstances() {

		List<GraphInstance> instances = new ReadAllFiles().creatAllInstances();
		int count = 0;
		IDijkstra iDijkstra = new DijkstraBucket();
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
