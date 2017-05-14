package br.puc.rio.inf.paa.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import br.puc.rio.inf.paa.djikstra.GraphInstance;

public class ReadFile {
	
	private String fileName;
	
	public ReadFile(String fileName) {
		this.fileName = fileName;
	}
	
	public GraphInstance createInstance(){
		
		Graph graph = null;
		int numEdges = 0;
		try (BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)))) {
		    String line;
		    while ((line = reader.readLine()) != null) {
		       String words[] = line.split(" ");
		       
		       // Creating graph
		       if(words[0].equals("Nodes")){
		    	   int numNodes = Integer.parseInt(words[1]);
		    	   graph = new Graph(numNodes);
		    	   
		       }
		       else if(words[0].equals("Edges")){
		    	   numEdges = Integer.parseInt(words[1]);
		    	   graph.numEdges = numEdges;
		       }
		       else if(words[0].equals("E")){
		    	   int source = Integer.parseInt(words[1]);
		    	   int destiny = Integer.parseInt(words[2]);
		    	   int cost = Integer.parseInt(words[3]);

		    	   graph.addEdge(source, destiny, cost);
		       }
		       
		    } 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		GraphInstance instance = new GraphInstance(graph.getVertices());
		instance.numEdges = numEdges;
		
		return instance;
	
		
	}
	
}
