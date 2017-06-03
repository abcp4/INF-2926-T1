package br.puc.rio.inf.paa.fractionalKnapsack.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import br.puc.rio.inf.paa.fractionalKnapsack.FractionalKnapsack;
import br.puc.rio.inf.paa.fractionalKnapsack.Item;

public class FractionalKnapsackReader {
			
	
	public List<FractionalKnapsack> createAllInstances(){
		
		List<String> fileNames = this.getAllFileNames();
		
		List<FractionalKnapsack> knapsackList = new ArrayList<FractionalKnapsack>();
		
		for(String fileName : fileNames){
			
			FractionalKnapsack instance = createInstance(fileName);

		    knapsackList.add(instance);
		}
		
		return knapsackList;
	}
	
	public FractionalKnapsack createInstance(String fileName){
		
		
		List<Item> itens = new ArrayList<Item>();
		double capacityKnapsack = 0;
		try (BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)))) {
		    String line;
		  
		    while ((line = reader.readLine()) != null) {
		       String words[] = line.split(" ");
		       
		       // Creating knapsack
		       if(words[0].equals(" ")){
		    	   int idKanapsack = Integer.parseInt(words[1]);
		    	   int valueKnapsack = Integer.parseInt(words[2]);
		    	   int weigthKnapsack = Integer.parseInt(words[3]);

		    	   Item item = new Item(idKanapsack, valueKnapsack, weigthKnapsack, 0);
		    	   itens.add(item);
		       }
		       else{
		    	   capacityKnapsack = Double.valueOf((words[0]));
		       }
		       
		    } 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		FractionalKnapsack instance = new FractionalKnapsack(capacityKnapsack, itens);
		return instance;
	}
	
		
	
	public List<String> getAllFileNames(){
					
		List<String> fileNames = new ArrayList<String>();
			
		try(Stream<Path> paths = Files.walk(Paths.get("../INF-2926/input-knapsack"))) {
			paths.forEach(filePath -> {
				if (Files.isRegularFile(filePath)) {
					  fileNames.add(filePath.toString());
			    }
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
					
		return fileNames;
					
	}
			
		

}
