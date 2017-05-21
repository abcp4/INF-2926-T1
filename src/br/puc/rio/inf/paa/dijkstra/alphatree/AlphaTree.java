package br.puc.rio.inf.paa.dijkstra.alphatree;

import java.util.ArrayList;
import java.util.List;

public class AlphaTree {

	public static double alpha = 0.7;
	private AlphaTreeNode root;

	public AlphaTree() {
		this.setRoot(null);
	}

	public void insert(int key, int cost) {
		if (this.root == null) {
			this.root = new AlphaTreeNode(key, cost, null);
		} else {
			AlphaTreeNode nodeRoot = this.root;
			AlphaTreeNode parent;
			while (true) {

				if (nodeRoot.getCost() == cost) {
					nodeRoot.insertKey(key);
					return;
				}

				parent = nodeRoot;

				boolean goLeft = nodeRoot.getCost() > cost;
				nodeRoot = goLeft ? nodeRoot.getLeft() : nodeRoot.getRight();

				if (nodeRoot == null) {
					AlphaTreeNode nodeAdd = new AlphaTreeNode(key, cost, parent);
					if (goLeft) {
						parent.setLeft(nodeAdd);
					} else {
						parent.setRight(nodeAdd);
					}
					this.updateSizesInsert(nodeAdd);
					break;
				}
			}
		}
	}

	public void updateSizesInsert(AlphaTreeNode nodeAdd) {
		AlphaTreeNode nodeNotBalance = null;
		AlphaTreeNode node = nodeAdd.getParent();
		while (node != null) {
			node.increraseSize();
			if (!(node.getSizeLeft() <= alpha * node.getSize() && node.getSizeRight() <= alpha * node.getSize())) {
				nodeNotBalance = node;
			}
			node = node.getParent();
		}
		this.rebuidTree(nodeNotBalance);
	}

	public void updateSizesDelete(AlphaTreeNode nodeDel) {
		AlphaTreeNode nodeNotBalance = null;
		AlphaTreeNode node = nodeDel.getParent();
		while (node != null) {
			node.decreraseSize();
			if (!(node.getSizeLeft() <= alpha * node.getSize() && node.getSizeRight() <= alpha * node.getSize())) {
				nodeNotBalance = node;
			}
			node = node.getParent();
		}
		this.rebuidTree(nodeNotBalance);
	}

	public void rebuidTree(AlphaTreeNode nodeRoot) {
		if (nodeRoot != null) {

			AlphaTreeNode[] array = null;

			boolean leftChild = false;
			boolean isRoot = false;

			AlphaTreeNode nodeParent = null;

			if (nodeRoot.getParent() == null) {
				isRoot = true;
			} else if (nodeRoot.getParent().getRight() != null && nodeRoot.getParent().getRight() == nodeRoot) {
				leftChild = false;
				nodeParent = nodeRoot.getParent();
			} else {
				leftChild = true;
				nodeParent = nodeRoot.getParent();
			}

			if (nodeRoot != null) {

				List<AlphaTreeNode> list = extractValues(nodeRoot);

				array = new AlphaTreeNode[list.size()];

				for (int i = 0; i < list.size(); i++) {
					array[i] = new AlphaTreeNode(list.get(i));
				}

				AlphaTreeNode node = this.rebuildTree(array, 0, array.length - 1);

				if (!isRoot) {
					if (leftChild) {
						node.setParent(nodeParent);
						nodeParent.setLeft(node);
					} else {
						node.setParent(nodeParent);
						nodeParent.setRight(node);
					}
				} else {
					this.root = node;
				}
			}
		}
	}

	public AlphaTreeNode rebuildTree(AlphaTreeNode[] array, int start, int end) {

		if (start > end) {
			return null;
		} else {
			int indexRoot = (start + end) / 2;
			int size = end - start + 1;

			AlphaTreeNode node = array[indexRoot];
			node.setSize(size);

			AlphaTreeNode left = this.rebuildTree(array, start, indexRoot - 1);
			AlphaTreeNode right = this.rebuildTree(array, indexRoot + 1, end);

			if (left != null) {
				left.setParent(node);
			}
			if (right != null) {
				right.setParent(node);
			}

			node.setLeft(left);
			node.setRight(right);

			return node;
		}

	}

	private static List<AlphaTreeNode> extractValues(AlphaTreeNode node) {
		List<AlphaTreeNode> result = new ArrayList<AlphaTreeNode>();
		if (node.getLeft() != null) {
			result.addAll(extractValues(node.getLeft()));
		}
		result.add(node);
		if (node.getRight() != null) {
			result.addAll(extractValues(node.getRight()));
		}
		return result;
	}

	private void delete(AlphaTreeNode node) {
		if (node.getLeft() == null && node.getRight() == null) {
			if (node.getParent() == null)
				this.root = null;
			else {
				AlphaTreeNode parent = node.getParent();
				if (parent.getLeft() == node) {
					parent.setLeft(null);
				} else
					parent.setRight(null);
			}
			return;
		}
		if (node.getLeft() != null) {
			AlphaTreeNode child = node.getLeft();
			child.decreraseSize();
			while (child.getRight() != null)
				child = child.getRight();
			node.setCost(child.getCost());
			node.setKeys(child.getKeys());
			node.decreraseSize();
			this.delete(child);
		} else {
			AlphaTreeNode child = node.getRight();
			child.decreraseSize();
			while (child.getLeft() != null)
				child = child.getLeft();
			node.setCost(child.getCost());
			node.setKeys(child.getKeys());
			node.decreraseSize();
			this.delete(child);
		}
	}

	public void delete(int delCost) {
		if (root == null)
			return;
		AlphaTreeNode node = root;
		AlphaTreeNode child = root;

		while (child != null) {
			node = child;
			child = delCost >= node.getCost() ? node.getRight() : node.getLeft();
			if (delCost == node.getCost()) {
				this.delete(node);
				this.updateSizesDelete(node);
				return;
			}
		}
	}

	public int getMin() {
		if (this.root == null) {
			return -1;
		} else {
			AlphaTreeNode node;
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

	public int findKey(int key) {

		if (this.root == null) {
			return -1;
		} else {
			AlphaTreeNode node = this.root;
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
			AlphaTreeNode node = this.root;
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

	public AlphaTreeNode getRoot() {
		return root;
	}

	public void setRoot(AlphaTreeNode root) {
		this.root = root;
	}

	public void printCost() {
		printCost(this.root);
		System.out.println();
	}

	private void printCost(AlphaTreeNode node) {
		if (node != null) {
			printCost(node.getLeft());
			System.out.printf("%s ", node.getCost());
			printCost(node.getRight());
		}
	}

	public void printSize() {
		printSize(this.root);
		System.out.println();
	}

	private void printSize(AlphaTreeNode node) {
		if (node != null) {
			printSize(node.getLeft());
			System.out.printf("%s ", node.getSize());
			printSize(node.getRight());
		}
	}

}