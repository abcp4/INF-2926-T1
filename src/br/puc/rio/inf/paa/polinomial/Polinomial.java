package br.puc.rio.inf.paa.polinomial;

public class Polinomial {
	public double p1;
	public double p2;
	public Polinomial(double p1, double p2) {
		super();
		this.p1 = p1;
		this.p2 = p2;
	}
	
	
	public double[] sum(double[] p1, double[] p2){
	 //return Operations(p1, p2, 1);
		return null;
	 }
	
	//Sum of two polynomials
	public double[] sum(double[] p1, double[] p2, int left, int right)
	 {
	  //return Operations(p1, p2, 1, left, right)
		return null;
	 }
	//Rest of two polynomials
	 public double[] Rest(double[] p1, double[] p2)
	 {
	 //return Operations(p1, p2, -1);
		 return null;
	 }
	 public double[] rest(double[] p1, double[] p2, int left, int right)
	 {
	// return Operations(p1, p2, -1, left, right);
		 return null;
	 }

	//Scalar product of two polynomials
	 public double[] scalarProduct(double[] p, double k)
	 {
	// return Operations(new double[1], p, k);
		 return null;
	 }
	 public double[] scalarProduct(double[] p, double k, int left, int right)
	 {
	// return Operations(new double[1], p, k, left, right
		 return null;
	 }

	
}
