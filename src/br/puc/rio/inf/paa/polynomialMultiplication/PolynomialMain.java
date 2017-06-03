package br.puc.rio.inf.paa.polynomialMultiplication;

import br.puc.rio.inf.paa.polynomialMultiplication.reader.PolynomialMultiplicationReader;

public class PolynomialMain {

	public static void main(String[] args) {
		
		Polynomials polynomials = new PolynomialMultiplicationReader().readFile("../INF-2926/input-polynomial-multiplication/instancia_17.dat");
		
		DirectPolynomialMultiplication multiplication = new DirectPolynomialMultiplication();
		multiplication.setPolynomials(polynomials);
		multiplication.multiplication();
		
		
		System.out.println("Polinômio A:");
		for(int n:polynomials.getPolynomialA()){
			System.out.print(n+" ");
		}
		System.out.println();
		System.out.println("Polinômio B:");
		for(int n:polynomials.getPolynomialB()){
			System.out.print(n+" ");
		}
		System.out.println();
		
		for(int n:multiplication.getPolynomialResult()){
			System.out.print(n+" ");
		}
			

		
//		String a = " -12 12";
//		for(String s:a.split(" ")){
//			System.out.println(s);
//		}
		
		
	}

}
