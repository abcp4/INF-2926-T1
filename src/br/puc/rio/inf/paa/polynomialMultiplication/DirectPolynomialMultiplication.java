package br.puc.rio.inf.paa.polynomialMultiplication;

public class DirectPolynomialMultiplication implements IPolynomialMultiplication{
	
	private Polynomials polynomials;
	private int[] polynomialResult;
	
	@Override
	public void multiplication() {
		
		int[] result = new int[2*this.polynomials.getPolynomialA().length];
		
		for(int i = 0; i < this.polynomials.getPolynomialA().length; i++){
			for(int j = 0; j < this.polynomials.getPolynomialB().length; j++){
				result[i+j] += this.polynomials.getPolynomialA()[i] * this.polynomials.getPolynomialB()[j];
			}
		}
		
		this.polynomialResult = result;
	}
	
	@Override
	public int[] getResult() {
		return polynomialResult;
	}

	public Polynomials getPolynomials() {
		return polynomials;
	}

	public void setPolynomials(Polynomials polynomials) {
		this.polynomials = polynomials;
	}

	public void setPolynomialResult(int[] polynomialResult) {
		this.polynomialResult = polynomialResult;
	}

	@Override
	public double getCTime() {
		return Math.pow(polynomials.getDegree(), 2);
	}
	
}
