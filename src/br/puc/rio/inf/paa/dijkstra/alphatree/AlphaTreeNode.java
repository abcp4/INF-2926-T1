package br.puc.rio.inf.paa.dijkstra.alphatree;

import java.util.ArrayList;
import java.util.List;

public class AlphaTreeNode {
	
	private List<Integer> keys;
	private int cost;
	private int size;
	private AlphaTreeNode parent;
	private AlphaTreeNode left;
	private AlphaTreeNode right;
	
	public AlphaTreeNode(int key, int cost, AlphaTreeNode parent) {
		this.keys = new ArrayList<Integer>();
		this.keys.add(key);
		this.cost = cost;
		this.parent = parent;
		this.size = 1;
	}
	
	public AlphaTreeNode(AlphaTreeNode node){
		this.setCost(node.getCost());
		this.setKeys(node.getKeys());
	}
	
//	public AlphaTreeNode() {
//		// TODO Auto-generated constructor stub
//	}

	public void increraseSize(){
		this.size++;
	}
	
	public void decreraseSize(){
		this.size--;
	}
	
	public int getSizeRight(){
		if(this.getRight() == null) return 0;
		else return this.getRight().size;
	}
	
	public int getSizeLeft(){
		if(this.getLeft() == null) return 0;
		else return this.getLeft().size;
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

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
}
