package br.puc.rio.inf.paa.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import br.puc.rio.inf.paa.djikstra.GraphInstance;

public class ReadAllFiles {
	
	
	public List<GraphInstance> creatAllInstances(){
		
		List<String> fileNames = this.getAllFileNames();
		List<GraphInstance> graphInstances = new ArrayList<GraphInstance>();
		
		for(String fileName:fileNames){
			graphInstances.add(new ReadFile(fileName).createInstance());
		}
		
		return graphInstances;
	}
	
	public List<String> getAllFileNames(){
		
		List<String> fileNames = new ArrayList<String>();

		try(Stream<Path> paths = Files.walk(Paths.get("../INF-2926/input"))) {
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
