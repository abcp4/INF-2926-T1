package br.puc.rio.inf.paa.dijkstra.avl;

import java.util.ArrayList;
import java.util.List;

public class AvlTreeNode {
	
	private List<Integer> keys;
	private int cost;
	private int balance;
    private int height;
	private AvlTreeNode parent;
	private AvlTreeNode left;
	private AvlTreeNode right;
	
	
	public AvlTreeNode(int key, int cost, AvlTreeNode parent) {
		this.keys = new ArrayList<Integer>();
		this.keys.add(key);
		this.cost = cost;
		this.parent = parent;
	}
	
	public void insertKey(int key){
		this.keys.add(key);
	}

	public List<Integer> getNode() {
		return keys;
	}

	public void setNode(List<Integer> keys) {
		this.keys = keys;
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
