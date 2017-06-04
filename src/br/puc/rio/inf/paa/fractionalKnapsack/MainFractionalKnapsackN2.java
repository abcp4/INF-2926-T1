package br.puc.rio.inf.paa.fractionalKnapsack;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import br.puc.rio.inf.paa.fractionalKnapsack.reader.FractionalKnapsackReader;
import br.puc.rio.inf.paa.utils.CsvWriter;
import br.puc.rio.inf.paa.utils.Utils;

public class MainFractionalKnapsackN2 {

	public static void main(String[] args) {

		String nameCSV = "nameCSVMainFractionalKnapsackN2.csv";

		CsvWriter writer = new CsvWriter(nameCSV, ',', Charset.forName("ISO-8859-1"));

		FractionalKnapsackReader knapsackReader = new FractionalKnapsackReader();

		List<FractionalKnapsack> fractionalKnapsacks = knapsackReader.createAllInstances();

		int count = 0;
		int numInstance = 0;

		int timeout = 5;
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
			FractionalKnapsackN2 knapsackN = new FractionalKnapsackN2();
			Map<Item, Double> map = null;
			double temp_inicio = System.nanoTime();

			while (durationEnd <= timeout) {
				map = knapsackN.knapsack(fractionalKnapsacks.get(i));

				temp_final = System.nanoTime();
				durationEnd = temp_final - temp_inicio;
				count++;
			}

			numInstance++;
			try {

				ctTime = Math.pow(fractionalKnapsacks.get(i).items.length, 2);

				cpuTime = (durationEnd / count);

				cpuTime = cpuTime / 100;

				double logCPU = Utils.logBase2(cpuTime);

				writer.write(String.valueOf(fractionalKnapsacks.get(i).items.length));

				writer.write(new BigDecimal(ctTime, MathContext.DECIMAL64).toString());

				writer.write(new BigDecimal(cpuTime, MathContext.DECIMAL64).toString());

				writer.write(new BigDecimal((ctTime / cpuTime), MathContext.DECIMAL64).toString());

				writer.write(String.valueOf(logCPU));

				writer.endRecord();

			} catch (IOException e) {
				e.printStackTrace();
			}

			count = 0;
			durationEnd = 0;

		}
		writer.close();
	}

}
