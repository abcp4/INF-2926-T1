package br.puc.rio.inf.paa.dijkstra.alphatree;

public class AlphaTree {
	
	public static double alpha = 0.6;
	
	private AlphaTreeNode root;
	
	public AlphaTree() {
		this.setRoot(null);
	}
	
	public void inserir(int key, int cost){
		if(this.root == null){
			this.root = new AlphaTreeNode(key, cost, null);
		}else{
			AlphaTreeNode nodeRoot = this.root;
			AlphaTreeNode parent;
			while (true) {
				//            	Testa se o valor a ser inserido já está na árvore
				if (nodeRoot.getCost() == cost){
					nodeRoot.insertKey(key);
				}
				
				nodeRoot.increaseNumNode();
				parent = nodeRoot;

				boolean goLeft = nodeRoot.getCost() > cost;
				nodeRoot = goLeft ? nodeRoot.getLeft() : nodeRoot.getRight();

				if (nodeRoot == null) {
					if (goLeft) {
						parent.setLeft(new AlphaTreeNode(key, cost, parent));
					} else {
						parent.setRight(new AlphaTreeNode(key, cost, parent));
					}
//					this.rebalance(parent);
					break;
				}
			}
		}
	}

	public AlphaTreeNode getRoot() {
		return root;
	}

	public void setRoot(AlphaTreeNode root) {
		this.root = root;
	}
	
	public void printNumNodes() {
		printNumNodes(this.root);
		System.out.println();
	}

	private void printNumNodes(AlphaTreeNode node) {
		if (node != null) {
			printNumNodes(node.getLeft());
			System.out.printf("%s ", node.getNumNode());
			printNumNodes(node.getRight());
		}
	}
	
}
