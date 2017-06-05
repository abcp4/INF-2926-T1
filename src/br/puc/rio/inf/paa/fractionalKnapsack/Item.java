package br.puc.rio.inf.paa.fractionalKnapsack;

public class Item {

	public int id;
	public double value;
	public double weight;
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

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
		this.ratio = value/weight; //TODO - lembrar de tirar isso
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