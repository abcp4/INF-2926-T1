package br.puc.rio.inf.paa.fractionalKnapsack;

import java.util.List;

import br.puc.rio.inf.paa.fractionalKnapsack.reader.FractionalKnapsackReader;

public class MainFractionalKnapsack {
	
	
	public static void main(String[] args) {
		
		
		FractionalKnapsackReader knapsackReader = new FractionalKnapsackReader();
		
		List<FractionalKnapsack> fractionalKnapsacks = knapsackReader.createAllInstances();
		
		for (int i = 0; i < fractionalKnapsacks.size(); i++) {
			System.out.println(fractionalKnapsacks.toString());
		}
		
		
		
		
		
		
		
		
	}
	
	
	

}
