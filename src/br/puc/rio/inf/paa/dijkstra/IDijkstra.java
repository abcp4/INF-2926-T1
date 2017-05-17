package br.puc.rio.inf.paa.dijkstra;

public interface IDijkstra {

	public void init(GraphInstance graph, int start);

	public int extractMin();

	public int[] getCusto();

	public int[] getPath();

	public void relax(int from, int to, int distance);
	
	public void setVisited(int vertice);
	
	public boolean isEmpty();
	

}
