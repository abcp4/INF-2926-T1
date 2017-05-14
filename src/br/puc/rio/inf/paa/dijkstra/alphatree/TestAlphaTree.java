package br.puc.rio.inf.paa.dijkstra.alphatree;

public class TestAlphaTree {
	
	public static void main(String[] args) {
		
		AlphaTree tree = new AlphaTree();
		
		tree.inserir(1, 15);
		tree.printNumNodes();
		tree.inserir(2, 10);
		tree.printNumNodes();
		tree.inserir(3, 8);
		tree.printNumNodes();
		tree.inserir(5, 25);
		tree.printNumNodes();
		tree.inserir(6, 11);
		tree.printNumNodes();
		
	}
}
