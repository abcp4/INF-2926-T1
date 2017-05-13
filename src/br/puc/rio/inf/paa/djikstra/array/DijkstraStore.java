package br.puc.rio.inf.paa.djikstra.array;

public abstract class DijkstraStore{ 
	
	//Return the list of dj
	protected int[] costs;
	
	//Return the mct 
	protected int[] tree; 
	
	public boolean[] marked;
	
	//Get the node with min cost 
	public abstract int getMin();
	
	public abstract int getMin(GraphInstance g);
	
	//Relax edge (v, w)
	public abstract void relax(int v, int w, int lvw);
	
	//Index the store and make decrease_key in all cases
	//int this[int v] { set; get; }
	
	//Mark node v
	public abstract void mark(int v);
	
	
	//Verifying if the store is empty
	public abstract boolean isEmpty(); 


   //Initializes the store
	public abstract void buildStore(GraphInstance g, int start);
}
