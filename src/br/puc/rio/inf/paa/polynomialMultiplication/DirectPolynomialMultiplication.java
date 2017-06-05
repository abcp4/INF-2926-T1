package br.puc.rio.inf.paa.polynomialMultiplication;

public class DirectPolynomialMultiplication implements IPolynomialMultiplication{
	
	private Polynomials polynomials;
	private Integer[] polynomialResult;
	
	@Override
	public void multiplication() {
		
		Integer[] result = new Integer[2*this.polynomials.getPolynomialA().length];
		
		for(int i = 0; i < result.length; i++){
			result[i] = 0;
		}
		
		for(int i = 0; i < this.polynomials.getPolynomialA().length; i++){
			for(int j = 0; j < this.polynomials.getPolynomialB().length; j++){
				result[i+j] += this.polynomials.getPolynomialA()[i] * this.polynomials.getPolynomialB()[j];
			}
		}
		
		this.polynomialResult = result;
	}
	
	@Override
	public Integer[] getResult() {
		return polynomialResult;
	}

	public Polynomials getPolynomials() {
		return polynomials;
	}

	public void setPolynomials(Polynomials polynomials) {
		this.polynomials = polynomials;
	}

	public void setPolynomialResult(Integer[] polynomialResult) {
		this.polynomialResult = polynomialResult;
	}
	
}
