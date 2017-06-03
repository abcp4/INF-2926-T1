package br.puc.rio.inf.paa.fractionalKnapsack;

import java.util.List;
import java.util.Map;

import br.puc.rio.inf.paa.fractionalKnapsack.reader.FractionalKnapsackReader;

public class MainFractionalKnapsack {
	
	
	public static void main(String[] args) {
	
		FractionalKnapsackReader knapsackReader = new FractionalKnapsackReader();
		
		List<FractionalKnapsack> fractionalKnapsacks = knapsackReader.createAllInstances();
		

		
		for (int i = 0; i < fractionalKnapsacks.size(); i++) {
			FractionalKnapsackNlogN knapsackN = new FractionalKnapsackNlogN();
	
			System.out.println("#####################" + "INSTANCIA - " + i);
			System.out.println("##" + "CAPACIDADE - " + fractionalKnapsacks.get(i).capacity);
			Map<Item, Double> map = knapsackN.knapsack(fractionalKnapsacks.get(i));
			
			map.entrySet().forEach( entry-> {
				System.out.println(entry.getKey().id + " " + entry.getValue());
			});
		break;
		}
		
		
	
	}
		
		
	}
	
	

