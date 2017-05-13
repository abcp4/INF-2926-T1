package br.puc.rio.inf.paa.dijkstra.avl;

import java.util.ArrayList;
import java.util.List;

public class DijkstraAvlTree {
	
	private AvlTreeNode root;
	
	public boolean insert(int key, int cost) {
        if (this.root == null)
            this.root = new AvlTreeNode(key, cost, null);
        else {
            AvlTreeNode nodeRoot = this.root;
            AvlTreeNode parent;
            while (true) {
                if (nodeRoot.getCost() == cost){
                    return false;
                }
 
                parent = nodeRoot;
 
                boolean goLeft = nodeRoot.getCost() > cost;
                nodeRoot = goLeft ? nodeRoot.getLeft() : nodeRoot.getRight();
 
                if (nodeRoot == null) {
                    if (goLeft) {
                        parent.setLeft(new AvlTreeNode(key, cost, parent));
                    } else {
                        parent.setRight(new AvlTreeNode(key, cost, parent));
                    }
                    this.rebalance(parent);
                    break;
                }
            }
        }
        return true;
    }
	
	private void delete(AvlTreeNode node){
        if(node.getLeft() == null && node.getRight() == null){
            if(node.getParent() == null) this.root = null;
            else{
                AvlTreeNode parent = node.getParent();
                if(parent.getLeft() == node){
                    parent.setLeft(null);
                }else parent.setRight(null);
                this.rebalance(parent);
            }
            return;
        }
        if(node.getLeft()!=null){
            AvlTreeNode child = node.getLeft();
            while (child.getRight()!=null) child = child.getRight();
            node.setCost(child.getCost());
            this.delete(child);
        }else{
            AvlTreeNode child = node.getRight();
            while (child.getLeft()!=null) child = child.getLeft();
            node.setCost(child.getCost());
            this.delete(child);
        }
    }
	
	public void delete(int delCost) {
        if (root == null)
            return;
        AvlTreeNode node = root;
        AvlTreeNode child = root;
 
        while (child != null) {
            node = child;
            child = delCost >= node.getCost() ? node.getRight() : node.getLeft();
            if (delCost == node.getCost()) {
                this.delete(node);
                return;
            }
        }
    }
	
	private void rebalance(AvlTreeNode node) {
		
		List<AvlTreeNode> list = new ArrayList<AvlTreeNode>();
		list.add(node);
        this.setBalance(list);
        
        if (node.getBalance() == -2) {
            if (this.height(node.getLeft().getLeft()) >= this.height(node.getLeft().getRight()))
                node = this.rotateRight(node);
            else
                node = this.rotateLeftThenRight(node);
 
        } else if (node.getBalance() == 2) {
            if (this.height(node.getRight().getRight()) >= height(node.getRight().getLeft()))
                node = this.rotateLeft(node);
            else
                node = this.rotateRightThenLeft(node);
        }
 
        if (node.getParent() != null) {
            rebalance(node.getParent());
        } else {
            this.root = node;
        }
    }
	
	private AvlTreeNode rotateRight(AvlTreeNode nodeA) {
		 
        AvlTreeNode nodeB = nodeA.getLeft();
        nodeB.setParent(nodeA.getParent());
 
        nodeA.setLeft(nodeB.getRight());
 
        if (nodeA.getLeft() != null)
            nodeA.getLeft().setParent(nodeA);
 
        nodeB.setRight(nodeA);
        nodeA.setParent(nodeB);
 
        if (nodeB.getParent() != null) {
            if (nodeB.getParent().getRight() == nodeA) {
                nodeB.getParent().setRight(nodeB);
            } else {
            	nodeB.getParent().setLeft(nodeB);
            }
        }
        
        List<AvlTreeNode> list = new ArrayList<AvlTreeNode>();
		list.add(nodeA);
		list.add(nodeB);
        this.setBalance(list);
 
        return nodeB;
    }
	
	private AvlTreeNode rotateLeft(AvlTreeNode nodeA) {
		 
        AvlTreeNode nodeB = nodeA.getRight();
        nodeB.setParent(nodeA.getParent());
 
        nodeA.setRight(nodeB.getLeft());
 
        if (nodeA.getRight() != null)
        	nodeA.getRight().setParent(nodeA);
        
        nodeB.setLeft(nodeA);
        nodeA.setParent(nodeB);
 
        if (nodeB.getParent() != null) {
            if (nodeB.getParent().getRight() == nodeA) {
                nodeB.getParent().setRight(nodeB);
            } else {
            	nodeB.getParent().setLeft(nodeB);
            }
        }
        
        List<AvlTreeNode> list = new ArrayList<AvlTreeNode>();
		list.add(nodeA);
		list.add(nodeB);
        this.setBalance(list);
 
        return nodeB;
    }
	
	private AvlTreeNode rotateLeftThenRight(AvlTreeNode node) {
        node.setLeft(this.rotateLeft(node.getLeft()));
        return this.rotateRight(node);
    }
 
    private AvlTreeNode rotateRightThenLeft(AvlTreeNode node) {
        node.setRight(this.rotateRight(node.getRight()));
        return this.rotateLeft(node);
    }
	
	private void setBalance(List<AvlTreeNode> nodes) {
        for (AvlTreeNode node : nodes){
            reheight(node);
            node.setBalance(this.height(node.getRight()) - height(node.getLeft()));
        }
    }
	
	private void reheight(AvlTreeNode node){
        if(node!=null){
            node.setHeight(1 + Math.max(this.height(node.getLeft()), this.height(node.getRight())));
        }
    }
	
	private int height(AvlTreeNode node) {
        if (node == null)
            return -1;
        return node.getHeight();
    }
	
	public void printBalance() {
        printBalance(this.root);
    }
 
    private void printBalance(AvlTreeNode node) {
        if (node != null) {
            printBalance(node.getLeft());
            System.out.printf("%s ", node.getBalance());
            printBalance(node.getRight());
        }
    }
 
    public static void main(String[] args) {
        DijkstraAvlTree tree = new DijkstraAvlTree();
 
        System.out.println("Inserting values 1 to 10");
        for (int i = 1; i < 10; i++)
            tree.insert(10, i);
 
        System.out.print("Printing balance: ");
        tree.printBalance();
    }

}
