package br.puc.rio.inf.paa.djikstra.array;

public abstract class DijkstraStore{ 
	
	//Return the list of dj
	int[] costs;
	
	//Return the mct 
	int[] tree; 
	
	boolean[] marked;
	
	//Get the node with min cost 
	abstract int getMin();
	
	//Relax edge (v, w)
	abstract void relax(int v, int w, int lvw);
	
	//Index the store and make decrease_key in all cases
	//int this[int v] { set; get; }
	
	//Mark node v
	abstract void mark(int v);
	
	
	//Verifying if the store is empty
	abstract boolean isEmpty(); 


   //Initializes the store
	abstract void buildStore(GraphInstance g, int start);
}
