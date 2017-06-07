package br.puc.rio.inf.paa.polynomialMultiplication;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.nio.charset.Charset;
import java.util.List;

import br.puc.rio.inf.paa.polynomialMultiplication.reader.PolynomialMultiplicationReader;
import br.puc.rio.inf.paa.utils.CsvWriter;
import br.puc.rio.inf.paa.utils.Utils;

public class PolynomialMain {

	public static void main(String[] args) {
		
//		Polynomials polynomial = new PolynomialMultiplicationReader().readFile("../INF-2926/input-polynomial-multiplication/instancia_03.dat");
//		
//		DirectPolynomialMultiplication multiplication1 = new DirectPolynomialMultiplication();
//		DACPolynomialMultiplication multiplication2 = new DACPolynomialMultiplication();
//		FFTPolynomialMultiplication multiplication3 = new FFTPolynomialMultiplication();
//		
//		multiplication1.setPolynomials(polynomial);
//		multiplication1.multiplication();
//		multiplication2.setPolynomials(polynomial);
//		multiplication2.multiplication();
//		multiplication3.setPolynomials(polynomial);
//		multiplication3.multiplication();
//		
//		for(int i:multiplication1.getResult()){
//			System.out.print(i+" ");
//		}
//		System.out.println();
//		for(int i:multiplication2.getResult()){
//			System.out.print(i+" ");
//		}
//		System.out.println();
//		for(int i:multiplication3.getResult()){
//			System.out.print(i+" ");
//		}
//		System.out.println();
		
		String nameCSV = "PolynomialMultiplication.csv";
		CsvWriter writer = new CsvWriter(nameCSV, ',', Charset.forName("ISO-8859-1"));
		List<Polynomials> polynomials = new PolynomialMultiplicationReader().creatAllInstances();
		
		try {
			writer.write("N");
			writer.write("CT_NSquare");
			writer.write("CPU_NSquare");
			writer.write("CT/CPU_NSquare");
			writer.write("Log(CPU)_NSquare");
			writer.write("CT_NLog3");
			writer.write("CPU_NLog3");
			writer.write("CT/CPU_NLog3");
			writer.write("Log(CPU)_NLog3");
			writer.write("CT_NLogN");
			writer.write("CPU_NLogN");
			writer.write("CT/CPU_NLogN");
			writer.write("Log(CPU)_NLogN");
			writer.endRecord();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < polynomials.size(); i++) {
			System.out.println(polynomials.get(i).getDegree());
			int count = 0;
			double timeout = 5000;
			double temp_final = 0.0;
			double durationEnd = 0.0;
			double ctTime = 0.0;
			double cpuTime = 0.0;
			
			double temp_inicio = System.currentTimeMillis();
			Polynomials p;
			DirectPolynomialMultiplication multiplication1 = new DirectPolynomialMultiplication();
			while (durationEnd <= timeout) {
				p = polynomials.get(i);
				multiplication1 = new DirectPolynomialMultiplication();
				multiplication1.setPolynomials(p);
				multiplication1.multiplication();
				temp_final = System.currentTimeMillis();
				durationEnd = temp_final - temp_inicio;
				count++;
			}
			try {
				writer.write(String.valueOf(multiplication1.getPolynomials().getDegree()));
				
				ctTime = multiplication1.getCTime();
				cpuTime = (durationEnd / count);
				double logCPU = cpuTime;
				
				writer.write(new BigDecimal(ctTime, MathContext.DECIMAL64).toString());
				writer.write(new BigDecimal(cpuTime, MathContext.DECIMAL64).toString());
				writer.write(new BigDecimal((ctTime / cpuTime), MathContext.DECIMAL64).toString());
				writer.write(String.valueOf(logCPU));

//				writer.endRecord();

			} catch (IOException e) {
				e.printStackTrace();
			}

			count = 0;
			durationEnd = 0;
			
			System.out.println("Multiplicação direta");
			
			count = 0;
			timeout = 5000;
			temp_final = 0.0;
			durationEnd = 0.0;
			ctTime = 0.0;
			cpuTime = 0.0;
			
			temp_inicio = System.currentTimeMillis();
			DACPolynomialMultiplication multiplication2 = new DACPolynomialMultiplication();
			while (durationEnd <= timeout) {
				p = polynomials.get(i);
				multiplication2 = new DACPolynomialMultiplication();
				multiplication2.setPolynomials(p);
				multiplication2.multiplication();
				temp_final = System.currentTimeMillis();
				durationEnd = temp_final - temp_inicio;
				count++;
			}
			try {				
				ctTime = multiplication2.getCTime();
				cpuTime = (durationEnd / count);
				double logCPU = cpuTime;
				
				writer.write(new BigDecimal(ctTime, MathContext.DECIMAL64).toString());
				writer.write(new BigDecimal(cpuTime, MathContext.DECIMAL64).toString());
				writer.write(new BigDecimal((ctTime / cpuTime), MathContext.DECIMAL64).toString());
				writer.write(String.valueOf(logCPU));

//				writer.endRecord();

			} catch (IOException e) {
				e.printStackTrace();
			}

			count = 0;
			durationEnd = 0;
			
			System.out.println("Multiplicação DAC");
			
			count = 0;
			timeout = 5000;
			temp_final = 0.0;
			durationEnd = 0.0;
			ctTime = 0.0;
			cpuTime = 0.0;
			
			temp_inicio = System.currentTimeMillis();
			FFTPolynomialMultiplication multiplication3 = new FFTPolynomialMultiplication();
			while (durationEnd <= timeout) {
				p = polynomials.get(i);
				multiplication3 = new FFTPolynomialMultiplication();
				multiplication3.setPolynomials(p);
				multiplication3.multiplication();
				temp_final = System.currentTimeMillis();
				durationEnd = temp_final - temp_inicio;
				count++;
			}
			try {				
				ctTime = multiplication3.getCTime();
				cpuTime = (durationEnd / count);
				double logCPU = cpuTime;
				
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
			System.out.println("Multiplicação FFT");
			System.out.println(i);
		}
		writer.close();
	}

}
