package br.puc.rio.inf.paa.fractionalKnapsack.reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import br.puc.rio.inf.paa.dijkstra.GraphInstance;
import br.puc.rio.inf.paa.fractionalKnapsack.FractionalKnapsack;
import br.puc.rio.inf.paa.utils.ReadFile;

public class FractionalKnapsackReader {
			
	
	public List<FractionalKnapsack> createAllInstances(){
		
		List<String> fileNames = this.getAllFileNames();
		
		List<FractionalKnapsack> knapsackList = new ArrayList<FractionalKnapsack>();
		
		for(String fileName : fileNames){
			
			//FractionalKnapsack instance = createInstance();
			//instance.name = getNameInstance(fileName);
		//	knapsackList.add(instance);
		}
		
		return knapsackList;
	}
	
	//public FractionalKnapsack createInstance(String fileName){
		
	//}
	
		
	
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
