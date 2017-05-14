package br.puc.rio.inf.paa.djikstra;

public interface IDijkstra {

	public void init(GraphInstance graph, int start);

	public int getMin();

	public int[] getCusto();

	public int[] getPath();

	public void relax(int from, int to, int distance);
	
	public void mark(int vertice);
	
	public boolean isEmpty();

}
