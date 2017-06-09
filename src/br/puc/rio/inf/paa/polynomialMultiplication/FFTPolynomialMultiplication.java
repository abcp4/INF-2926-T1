package br.puc.rio.inf.paa.polynomialMultiplication;

public class FFTPolynomialMultiplication implements IPolynomialMultiplication{

	private Polynomials polynomials;
	private int[] result;

	@Override
	public void multiplication() {

		int m = polynomials.getPolynomialA().length*2;
		Complex[] pa = this.intToComplex(polynomials.getPolynomialA());
		Complex[] pb = this.intToComplex(polynomials.getPolynomialB());

		Complex[] omega = calculateOmega(m);
		Complex[] InverseOmega = calculateInverseOmega(m);

		Complex[] fa = this.DFT(pa, pa.length, omega);
		Complex[] fb = this.DFT(pb, pb.length, omega);

		Complex[] fc = new Complex[m];

		for(int i = 0; i < m; i++){
			fc[i] = fa[i].times(fb[i]);
		}

		Complex[] pc = this.DFT(fc, fc.length, InverseOmega);

		this.result = new int[pc.length];

		for(int i = 0; i < m; i++){
			double c = pc[i].re()/m;
			if(c < 0){
				if(Math.abs(c % 1) > 0.5){
					this.result[i] = (int) Math.floor(c);
				}else{
					this.result[i] = (int) Math.ceil(c);
				}
			}else{
				if(c % 1 > 0.5){
					this.result[i] = (int) Math.ceil(c);
				}else{
					this.result[i] = (int) Math.floor(c);
				}
			}
		}
	}

	@Override
	public int[] getResult() {
		return result;
	}

	private Complex[] DFT(Complex[] a, int n, Complex[] omega){
		Complex[] y;
		if(n == 1){
			return a;
		}

		Complex[] aEven = this.getEvenIndexes(a);
		Complex[] aOdd = this.getOddIndexes(a);



		Complex[] omegaSquare = new Complex[n/2];

		for(int i = 0; i < n/2; i++){
			omegaSquare[i] = omega[i].times(omega[i]);
		}

		Complex[] f0 = this.DFT(aEven, n/2, omegaSquare);
		Complex[] f1 = this.DFT(aOdd, n/2, omegaSquare);

		y = new Complex[n];

		for(int k = 0; k < n/2; k++){
			Complex temp = omega[k].times(f1[k]);
			y[k] = f0[k].plus(temp);
			y[k+(n/2)] = f0[k].minus(temp);
		}

		return y;
	}

	//	Converte o vetor de coeficiente do polinômio de grau n em um vetor 
	//	de números complexos de tamanho 2n+2
	private Complex[] intToComplex(int[] array){
		Complex[] numbers = new Complex[2*array.length];
		for(int i = 0; i < array.length*2; i++){
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

	private Complex[] calculateInverseOmega(int length){
		Complex[] complexs = new Complex[length];
		for(int i = 0; i < length; i++){
			complexs[i] = new Complex(Math.cos((2*i*Math.PI)/length),-Math.sin((2*i*Math.PI)/length));
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
