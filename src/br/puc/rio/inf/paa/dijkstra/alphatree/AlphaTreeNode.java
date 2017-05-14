package br.puc.rio.inf.paa.dijkstra.alphatree;

import java.util.ArrayList;
import java.util.List;

public class AlphaTreeNode {
	
	private List<Integer> keys;
	private int cost;
	private float balance;
	private int numNode;
	private AlphaTreeNode parent;
	private AlphaTreeNode left;
	private AlphaTreeNode right;
	
	public AlphaTreeNode(int key, int cost, AlphaTreeNode parent) {
		this.keys = new ArrayList<Integer>();
		this.keys.add(key);
		this.cost = cost;
		this.parent = parent;
		this.numNode = 1;
	}
	
	public void increaseNumNode(){
		this.numNode++;
	}
	
	public void insertKey(int key){
		this.keys.add(key);
	}
	
	public List<Integer> getKeys() {
		return keys;
	}
	public void setKeys(List<Integer> keys) {
		this.keys = keys;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public AlphaTreeNode getParent() {
		return parent;
	}
	public void setParent(AlphaTreeNode parent) {
		this.parent = parent;
	}
	public AlphaTreeNode getLeft() {
		return left;
	}
	public void setLeft(AlphaTreeNode left) {
		this.left = left;
	}
	public AlphaTreeNode getRight() {
		return right;
	}
	public void setRight(AlphaTreeNode right) {
		this.right = right;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}
	public int getNumNode() {
		return numNode;
	}

	public void setNumNode(int numNode) {
		this.numNode = numNode;
	}
	
}
