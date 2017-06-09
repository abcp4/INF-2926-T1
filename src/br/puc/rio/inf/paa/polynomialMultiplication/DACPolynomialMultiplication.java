package br.puc.rio.inf.paa.polynomialMultiplication;

public class DACPolynomialMultiplication implements IPolynomialMultiplication{

	private Polynomials polynomials;
	private int[] polynomialResult;

	@Override
	public void multiplication() {
		this.polynomialResult = this.DACMultiplication(polynomials.getPolynomialA(), polynomials.getPolynomialB(), polynomials.getPolynomialA().length);
	}

	public int[] DACMultiplication(int[] a, int[] b, int m){

		if(m == 1){
			int[] result = new int[1];
			result[0] = a[0]*b[0];
			return result;
		}

		int[] a1 = new int[m/2];
		int[] a2 = new int[m/2];
		int[] b1 = new int[m/2];
		int[] b2 = new int[m/2];

		//		Dividindo os polinômios
		for(int i = 0; i < m/2; i++){
			a1[i] = a[i]; 
			a2[i] = a[i+(m/2)];
			b1[i] = b[i]; 
			b2[i] = b[i+(m/2)];
		}

		//		DACMultiplication retorna um vetor de tamanho 2(m/2)-1
		int[] y = this.DACMultiplication(this.addition(a1, a2, m/2), this.addition(b1, b2, m/2), m/2);
		int[] u = this.DACMultiplication(a1, b1, m/2);
		int[] z = this.DACMultiplication(a2, b2, m/2);
		
		int[] yu = this.subtraction(y, u, y.length);
		int[] yuz = this.subtraction(yu, z, y.length);
		
		int[] result = new int[2*m-1];
		for(int i = 0; i < y.length; i++){
			result[i] += u[i];
		}

		for(int x = result.length-1, i = z.length-1; i >= 0; x--, i--){
			result[x] += z[i];
		}
		
		for(int i = 0, x = m/2; i < yuz.length; i++, x++){
			result[x] += yuz[i];
		}

		return result;

	}

	public int[] addition(int[] a, int[] b, int m){
		int[] result = new int[m];
		for(int i = 0; i < m; i++){
			result[i] = a[i] + b[i];
		}
		return result;
	}
	
	public int[] subtraction(int[] a, int[] b, int m){
		int[] result = new int[m];
		for(int i = 0; i < m; i++){
			result[i] = a[i] - b[i];
		}
		return result;
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

}
