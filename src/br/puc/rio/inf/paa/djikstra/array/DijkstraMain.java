package br.puc.rio.inf.paa.djikstra.array;


import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.TimeUnit;

import br.puc.rio.inf.paa.file.ReadAllFiles;
import br.puc.rio.inf.paa.file.ReadFile;

public class DijkstraMain {

	public static void main(String[] args) {

		Map<Integer, List<Edge>> graph = new LinkedHashMap<>();

		Edge e1 = new Edge(2, 10);
		Edge e2 = new Edge(3, 15);
		Edge e3 = new Edge(4, 20);

		graph.put(1, Arrays.asList(e1, e2));// 1 --> 7, 3
		graph.put(2, Arrays.asList(e2, e3));// 7 --> 3, 4
		graph.put(3, Arrays.asList());
		graph.put(4, Arrays.asList());

		GraphInstance instanceTest = new GraphInstance(graph);
		DijkstraStore vectorStore = new DijkstraVectorStore();

		List<br.puc.rio.inf.paa.depressao.GraphInstance> instances = new ReadAllFiles().creatAllInstances();

		int countInstance = 0;
		long timeout = 5;
		long durationTime = 0;
		long durationEnd = 0;
		
		for (int i = 0; i < instances.size(); i++) {

			long timeStart = System.currentTimeMillis();
			long timeSeconds = TimeUnit.MILLISECONDS.toSeconds(timeStart);

			long count = 0;
			while (true) {

				if (durationEnd > timeout) {
					break;
				}

				DijkstraSolution solution = instances.get(i).dijkstra(1, vectorStore);

			//	Instant stop = Instant.now();

				long timeEnd = System.currentTimeMillis();
				durationEnd = TimeUnit.MILLISECONDS.toSeconds(timeEnd);
				durationTime = durationTime + durationEnd;
				// System.out.println(durationTime);
				count++;
			}
			countInstance++;
			System.out.println("No Instance: " + countInstance);
			System.out.println("Quantidade de vezes: " + count);
			System.out.println("Tempo medio: " + (durationTime / count));

		}

	}

}
