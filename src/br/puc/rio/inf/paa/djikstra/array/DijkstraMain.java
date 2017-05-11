package br.puc.rio.inf.paa.djikstra.array;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

import br.puc.rio.inf.paa.file.ReadFile;
public class DijkstraMain {
	
	public static void main(String[] args){
		
		Map<Integer, List<Edge>> graph = new LinkedHashMap<>();
		
		Edge e1 = new Edge(7, 10);
		Edge e2 = new Edge(3, 15);
		Edge e3 = new Edge(4, 20);
		
		
	    graph.put(1, Arrays.asList(e1, e2));//1 --> 7, 3
	    graph.put(7, Arrays.asList(e2, e3));//7 --> 3, 4
	    graph.put(3, Arrays.asList());
	    graph.put(4, Arrays.asList());

	    GraphInstance instance = new GraphInstance(graph);
	    DijkstraVectorStore vectorStore = new DijkstraVectorStore();
	    
        
	    String file =  "../INF-2926/input/alue2087.stp";
		
		ReadFile readFile = new ReadFile(file);
		GraphInstance graphInstance = readFile.CreateInstance();
	   
	    Instant start = Instant.now(); 
	    long timeout = Duration.between(start , start).getSeconds() + 5;
		long durationTime = Duration.between(start, start).getSeconds();  
	    long count = 0;
		while(true){
	    	
	    	
            if(durationTime > timeout){
            	break;
            }
                
             
	    	DijkstraSolution solution = graphInstance.dijkstra(1,  vectorStore);
	    	
	    	Instant stop = Instant.now(); 
	    	System.out.println("stop" + stop);
	        
	    	durationTime = Duration.between( start , stop).getSeconds();
	        System.out.println("durationTime" + durationTime);
            count ++;
	    }
		
		
		 
		System.out.println("Quantidade de vezes: " + count);
		System.out.println("Tempo medio: " + String.format("%.4f", (double)(durationTime/count)) );
		
	    for(int i = 0; i< vectorStore.marked.length; i++){
	    	
	 	    if(vectorStore.marked[i]){
	 	    	System.out.println(i);
	 	    }
	    }
	   
	    
	
	   
	   
	}

}
