package br.puc.rio.inf.paa.polynomialMultiplication;

public class DirectPolynomialMultiplication implements IPolynomialMultiplication{
	
	private Polynomials polynomials;
	
	@Override
	public int[] multiplication() {
		
		int[] result = new int[2*this.polynomials.getDegree()-1];
		
		for(int i = 0; i < this.polynomials.getPolynomialA().length; i++){
			for(int j = 0; j < this.polynomials.getPolynomialB().length; j++){
				result[i+j] += this.polynomials.getPolynomialA()[i] * this.polynomials.getPolynomialB()[j];
			}
		}
		
		return result;
	}

	public Polynomials getPolynomials() {
		return polynomials;
	}

	public void setPolynomials(Polynomials polynomials) {
		this.polynomials = polynomials;
	}
	
}
