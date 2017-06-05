package br.puc.rio.inf.paa.fractionalKnapsack;

public class Item {

	public int id;
	public int value;
	public int weight;
	public double ratio;

	public Item(int id, int value, int weight) {
		this.id = id;
		this.value = value;
		this.weight = weight;
		this.ratio = value / weight;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public double getRatio() {
		return ratio;
	}

	public void setRatio(double ratio) {
		this.ratio = ratio;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", value=" + value + ", weight=" + weight + ", ratio=" + ratio + "]\n";
	}

}