package br.puc.rio.inf.paa.dijkstra.avl;

import java.util.ArrayList;
import java.util.List;

public class AvlTree {

	private AvlTreeNode root;

	public AvlTree() {
		this.root = null;
	}

	public int getMin() {
		if (this.root == null) {
			return -1;
		} else {
			AvlTreeNode node;
			node = this.root;
			while (true) {
				if (node.getLeft() == null) {
					int menor = node.getKeys().get(0);
					node.getKeys().remove(0);

					if (node.getKeys().isEmpty()) {
						this.delete(node.getCost());
					}

					return menor;
				} else {
					node = node.getLeft();
				}
			}
		}
	}

	public boolean insert(int key, int cost) {

		// Testa se a raiz é nula
		if (this.root == null) {
			this.root = new AvlTreeNode(key, cost, null);
		} else {
			AvlTreeNode nodeRoot = this.root;
			AvlTreeNode parent;
			while (true) {
				// Testa se o valor a ser inserido já está na árvore
				if (nodeRoot.getCost() == cost) {
					nodeRoot.insertKey(key);
					return true;
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

	// Método utilizado apenas pela árvore que armazena ordenado pela chave
	public int findKey(int key) {

		if (this.root == null) {
			return -1;
		} else {
			AvlTreeNode node = this.root;
			while (true) {
				if (node == null)
					return -1;
				if (node.getCost() > key) {
					node = node.getLeft();
				} else if (node.getCost() < key) {
					node = node.getRight();
				} else {
					return node.getKeys().get(0);
				}
			}
		}

	}

	public void findKeyAndUpdate(int key, int cost) {

		if (this.root == null) {
			return;
		} else {
			AvlTreeNode node = this.root;
			while (true) {
				if (node == null)
					return;
				if (node.getCost() > key) {
					node = node.getLeft();
				} else if (node.getCost() < key) {
					node = node.getRight();
				} else {
					node.getKeys().remove(0);
					node.getKeys().add(cost);
					return;
				}
			}
		}
	}

	private void delete(AvlTreeNode node) {
		if (node.getLeft() == null && node.getRight() == null) {
			if (node.getParent() == null)
				this.root = null;
			else {
				AvlTreeNode parent = node.getParent();
				if (parent.getLeft() == node) {
					parent.setLeft(null);
				} else
					parent.setRight(null);
				this.rebalance(parent);
			}
			return;
		}
		if (node.getLeft() != null) {
			AvlTreeNode child = node.getLeft();
			while (child.getRight() != null)
				child = child.getRight();
			node.setCost(child.getCost());
			node.setKeys(child.getKeys());
			this.delete(child);
		} else {
			AvlTreeNode child = node.getRight();
			while (child.getLeft() != null)
				child = child.getLeft();
			node.setCost(child.getCost());
			node.setKeys(child.getKeys());
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
		for (AvlTreeNode node : nodes) {
			reheight(node);
			node.setBalance(this.height(node.getRight()) - height(node.getLeft()));
		}
	}

	private void reheight(AvlTreeNode node) {
		if (node != null) {
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
		System.out.println();
	}

	private void printBalance(AvlTreeNode node) {
		if (node != null) {
			printBalance(node.getLeft());
			System.out.printf("%s ", node.getBalance());
			printBalance(node.getRight());
		}
	}

	public void printCost() {
		printCost(this.root);
		System.out.println();
	}

	private void printCost(AvlTreeNode node) {
		if (node != null) {
			printCost(node.getLeft());
			System.out.printf("%s ", node.getCost());
			printCost(node.getRight());
		}
	}

	public AvlTreeNode getRoot() {
		return root;
	}

	public void setRoot(AvlTreeNode root) {
		this.root = root;
	}

}
