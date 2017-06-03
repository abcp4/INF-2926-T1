package br.puc.rio.inf.paa.fractionalKnapsack;

import java.util.Arrays;

public class Item {

	int id;
	int value;
	int weight;
	double ratio;

	
	public Item(int id, int value, int weight, double ratio) {
		this.id = id;
		this.value = value;
		this.weight = weight;
		this.ratio = ratio;
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

}