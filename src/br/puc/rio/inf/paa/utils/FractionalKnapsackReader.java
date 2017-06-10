package br.puc.rio.inf.paa.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import br.puc.rio.inf.paa.fractionalKnapsack.ItemRepository;
import br.puc.rio.inf.paa.fractionalKnapsack.ProblemFractionalKnapsack;
import br.puc.rio.inf.paa.fractionalKnapsack.FractionalKnapsack;
import br.puc.rio.inf.paa.fractionalKnapsack.Item;

public class FractionalKnapsackReader {

	public List<ProblemFractionalKnapsack> createAllInstances() {

		List<String> fileNames = this.getAllFileNames();

		List<ProblemFractionalKnapsack> problemKnapsackList = new ArrayList<ProblemFractionalKnapsack>();

		for (String fileName : fileNames) {

			ProblemFractionalKnapsack instance = createInstance(fileName);

			problemKnapsackList.add(instance);
		}

		return problemKnapsackList;
	}

	public ProblemFractionalKnapsack createInstance(String fileName) {
		Item[] items = null;

		Path path = Paths.get(fileName);

		double capacityKnapsack = 0;

		List<String> lines;
		try {
			lines = Files.readAllLines(path);
			int quantityItems = Integer.parseInt(lines.get(0));

			items = new Item[quantityItems];
			int i = 0;

			for (int j = 1; j < lines.size(); j++) {

				String wordsItems[] = lines.get(j).trim().split("\\s+");

				// Creating knapsack
				if (wordsItems.length > 1) {

					int idKanapsack = Integer.parseInt(wordsItems[0]);
					int valueKnapsack = Integer.parseInt(wordsItems[1]);
					int weigthKnapsack = Integer.parseInt(wordsItems[2]);

					Item item = new Item(idKanapsack, valueKnapsack, weigthKnapsack);
					items[i] = item;

					i++;
				} else {
					capacityKnapsack = Double.valueOf((wordsItems[0]));
				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ItemRepository repository = new ItemRepository(items);
		FractionalKnapsack knapsack = new FractionalKnapsack(capacityKnapsack);
		
		ProblemFractionalKnapsack problemInstance = new ProblemFractionalKnapsack(repository, knapsack); 
		return problemInstance;
	}

	public List<String> getAllFileNames() {

		List<String> fileNames = new ArrayList<String>();

		try (Stream<Path> paths = Files.walk(Paths.get("../INF-2926/input-knapsack"))) {
			paths.forEach(filePath -> {
				if (Files.isRegularFile(filePath)) {
					fileNames.add(filePath.toString());
				}
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return fileNames;

	}

}
