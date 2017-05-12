package br.puc.rio.inf.paa.djikstra.heap;

public class FibonacciNodeValue implements Comparable<FibonacciNodeValue> {

	public int distance;
	public int node;

	public FibonacciNodeValue(int distance, int node) {
		this.distance = distance;
		this.node = node;
	}

	public int getDistance() {
		return distance;
	}

	public void setDj(int distance) {
		this.distance = distance;
	}

	public int getNode() {
		return node;
	}

	public void setNode(int node) {
		this.node = node;
	}

		@Override
	public int compareTo(FibonacciNodeValue object) {
		int distanceObject = object.distance;

		if (distance > distanceObject) {
			return 1;
		} else if (distance < distanceObject) {
			return -1;
		}

		int objNode = object.node;
		if (node > objNode) {
			return 1;
		}
		return -1;
	}

}
