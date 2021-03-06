package br.puc.rio.inf.paa.polynomialMultiplication;

import br.puc.rio.inf.paa.polynomialMultiplication.reader.PolynomialMultiplicationReader;

public class PolynomialMain {

	public static void main(String[] args) {
		
		Polynomials polynomials = new PolynomialMultiplicationReader().readFile("../INF-2926/input-polynomial-multiplication/instancia_03.dat");
		
		FFTPolynomialMultiplication multiplication1 = new FFTPolynomialMultiplication();
		multiplication1.setPolynomials(polynomials);
		multiplication1.multiplication();
		
		DirectPolynomialMultiplication multiplication2 = new DirectPolynomialMultiplication();
		multiplication2.setPolynomials(polynomials);
		multiplication2.multiplication();
		
		DACPolynomialMultiplication multiplication3 = new DACPolynomialMultiplication();
		multiplication3.setPolynomials(polynomials);
		multiplication3.multiplication();
		
		for(int n:multiplication1.getResult()){
			System.out.print(n+" ");
		}
		System.out.println();
		
		for(int n:multiplication2.getResult()){
			System.out.print(n+" ");
		}
		System.out.println();
		
		for(int n:multiplication3.getResult()){
			System.out.print(n+" ");
		}
		System.out.println();
	}

}
