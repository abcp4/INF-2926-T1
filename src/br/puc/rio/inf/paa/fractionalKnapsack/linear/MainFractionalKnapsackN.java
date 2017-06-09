package br.puc.rio.inf.paa.fractionalKnapsack.linear;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import br.puc.rio.inf.paa.fractionalKnapsack.FractionalKnapsackInstance;
import br.puc.rio.inf.paa.fractionalKnapsack.Item;
import br.puc.rio.inf.paa.utils.CsvWriter;
import br.puc.rio.inf.paa.utils.FractionalKnapsackReader;
import br.puc.rio.inf.paa.utils.Utils;
	
public class MainFractionalKnapsackN {

	public static void main(String[] args) {

		String nameCSV = "nameCSVMainFractionalKnapsackN.csv";

		CsvWriter writer = new CsvWriter(nameCSV, ',', Charset.forName("ISO-8859-1"));

		FractionalKnapsackReader knapsackReader = new FractionalKnapsackReader();

		List<FractionalKnapsackInstance> fractionalKnapsacks = knapsackReader.createAllInstances();

		int count = 0;
		int numInstance = 0;

		double timeout = 5000;
		double temp_final = 0.0;
		double durationEnd = 0.0;
		double ctTime = 0.0;
		double cpuTime = 0.0;

		try {
			writer.write("N");
			writer.write("CT");
			writer.write("CPU");

			writer.write("CT/CPU");

			writer.write("Log(CPU)");
			writer.endRecord();

		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < fractionalKnapsacks.size(); i++) {
			
			Map<Item, Double> map = null;
			double temp_inicio = System.currentTimeMillis();

			while (durationEnd <= timeout) {
				FractionalKnapsackN knapsackN = new FractionalKnapsackN();
				map = knapsackN.knapsack(fractionalKnapsacks.get(i));

				temp_final = System.currentTimeMillis();
				durationEnd = temp_final - temp_inicio;
				count++;
			}

			numInstance++;
			try {

				ctTime = fractionalKnapsacks.get(i).items.length;

				cpuTime = (durationEnd / count);


				double logCPU = Utils.logBase2(cpuTime);

				writer.write(String.valueOf(fractionalKnapsacks.get(i).items.length));

				writer.write(String.valueOf(ctTime));

				writer.write(String.valueOf(cpuTime));

				writer.write(String.valueOf(ctTime / cpuTime));

				writer.write(String.valueOf(logCPU));

				writer.endRecord();

			} catch (IOException e) {
				e.printStackTrace();
			}

			System.out.println("No Instance: " + numInstance);
			// System.out.println(instance.name);
			// System.out.println("N: " + instance.numVertex + " x " + "M: " +
			// instance.numEdges);
			System.out.println("Quantidade de vezes: " + count);
			System.out.println("Tempo medio: " + (durationEnd / count));
			System.out.println("CT: " + ctTime);
			System.out.println();

			count = 0;
			durationEnd = 0;

		}
		writer.close();
	}

}