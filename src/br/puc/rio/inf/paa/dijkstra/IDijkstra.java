package br.puc.rio.inf.paa.dijkstra;

public interface IDijkstra {

	public void initialize(GraphInstance graph, int start);

	public int getMin();

	public int[] getDistanceTotal();

	public int[] getPath();

	public void relax(int from, int to, int distance);
	
	public void setVisited(int vertice);
	
<<<<<<< HEAD
	
=======
	public boolean isEmpty();
	

>>>>>>> 0bf049f730dc2a97aa1514e95de0172a5b2b934e
}
