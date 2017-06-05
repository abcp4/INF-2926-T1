package br.puc.rio.inf.paa.polynomialMultiplication;

public class FFTPolynomialsMultiplication implements IPolynomialMultiplication{
	
	private Polynomials polynomials;
	
	@Override
	public void multiplication() {
		
		int m = polynomials.getPolynomialA().length*2;
		
		Complex[] pa = this.intToComplex(polynomials.getPolynomialA());
		Complex[] pb = this.intToComplex(polynomials.getPolynomialB());
		
		Complex[] fa = this.DFT(pa, pa.length);
		Complex[] fb = this.DFT(pb, pb.length);
		
		Complex[] fc = new Complex[m];
		
		for(int i = 0; i < m; i++){
			fc[i] = fa[i].plus(fb[i]);
		}
		
		for(int i = 0; i < m; i++){
			fc[i].toString();
		}
		
	}

	@Override
	public int[] getResult() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Complex[] DFT(Complex[] a, int n){
		Complex[] y;
		if(n == 1){
			return a;
		}else{
			Complex[] aEven = this.getEvenIndexes(a);
			Complex[] aOdd = this.getOddIndexes(a);
			
			Complex[] f0 = this.DFT(aEven, n/2);
			Complex[] f1 = this.DFT(aOdd, n/2);
			
			Complex wn = new Complex(Math.cos((2*Math.PI)/n), Math.sin((2*Math.PI)/n));
			Complex w = new Complex(1, 0);
			
			y = new Complex[n];
			
			for(int k = 0; k < n/2-1; k++){
				y[k] = f0[k].plus(w.times(f1[k]));
				y[k+n/2] = f0[k].minus(w.times(f1[k]));
				w = w.times(wn);
			}
		}
		
		return y;
	}
	
//	Converte o vetor de coeficiente do polinômio de grau n em um vetor 
//	de números complexos de tamanho 2n+2
	private Complex[] intToComplex(int[] array){
		Complex[] numbers = new Complex[2*array.length];
		for(int i = 0; i < array.length; i++){
			if(i < array.length){
				numbers[i] = new Complex(array[i], 0);
			}else{
				numbers[i] = new Complex(0, 0);
			}
		}
		return numbers;
	}
	
	private Complex[] getEvenIndexes(Complex[] array){
		Complex[] result = new Complex[array.length/2];
		int index = 0;
		for(int i = 0; i < array.length; i++){
			if(i % 2 == 0){
				result[index++] = array[i];
			}
		}
		return result;
	}
	
	private Complex[] getOddIndexes(Complex[] array){
		Complex[] result = new Complex[array.length/2];
		int index = 0;
		for(int i = 0; i < array.length; i++){
			if(i % 2 == 1){
				result[index++] = array[i];
			}
		}
		return result;
	}
	
	private Complex[] calculateOmega(int length){
		
		Complex[] complexs = new Complex[length];
		
		for(int i = 0; i < length; i++){
			complexs[i] = new Complex(Math.cos((2*i*Math.PI)/length),Math.sin((2*i*Math.PI)/length));
		}
		
		return complexs;
	}

	public Polynomials getPolynomials() {
		return polynomials;
	}

	public void setPolynomials(Polynomials polynomials) {
		this.polynomials = polynomials;
	}
	
	

}
