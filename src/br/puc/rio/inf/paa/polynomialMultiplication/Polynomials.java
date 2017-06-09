package br.puc.rio.inf.paa.polynomialMultiplication;

public class Polynomials {
	
	private int[] polynomialA;
	private int[] polynomialB;
	private int degree;
	
	public Polynomials(int[] polynomialA, int[] polynomialB, int degree) {
		this.setPolynomialA(polynomialA);
		this.setPolynomialB(polynomialB);
		this.degree = degree;
	}
	
	public int[] getPolynomialA() {
		return polynomialA;
	}

	public void setPolynomialA(int[] polynomialA) {
		this.polynomialA = polynomialA;
	}

	public int[] getPolynomialB() {
		return polynomialB;
	}

	public void setPolynomialB(int[] polynomialB) {
		this.polynomialB = polynomialB;
	}

	public int getDegree() {
		return degree;
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}
	
}
