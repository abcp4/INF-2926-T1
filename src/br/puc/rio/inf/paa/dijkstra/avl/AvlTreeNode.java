package br.puc.rio.inf.paa.dijkstra.avl;

public class AvlTreeNode {
	
	private int key;
	private int cost;
	private int balance;
    private int height;
	private AvlTreeNode parent;
	private AvlTreeNode left;
	private AvlTreeNode right;
	
	
	public AvlTreeNode(int node, int cost, AvlTreeNode parent) {
		this.key = node;
		this.cost = cost;
		this.parent = parent;
	}

	public int getNode() {
		return key;
	}

	public void setNode(int node) {
		this.key = node;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public AvlTreeNode getParent() {
		return parent;
	}

	public void setParent(AvlTreeNode parent) {
		this.parent = parent;
	}

	public AvlTreeNode getLeft() {
		return left;
	}

	public void setLeft(AvlTreeNode left) {
		this.left = left;
	}

	public AvlTreeNode getRight() {
		return right;
	}

	public void setRight(AvlTreeNode right) {
		this.right = right;
	}

}
