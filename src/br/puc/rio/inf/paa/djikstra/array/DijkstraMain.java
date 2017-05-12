package br.puc.rio.inf.paa.djikstra.array;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.TimeUnit;

import br.puc.rio.inf.paa.file.ReadAllFiles;
import br.puc.rio.inf.paa.file.ReadFile;
public class DijkstraMain {
	
	public static void main(String[] args){
		
		Map<Integer, List<Edge>> graph = new LinkedHashMap<>();
		
		Edge e1 = new Edge(2, 10);
		Edge e2 = new Edge(3, 15);
		Edge e3 = new Edge(4, 20);
		
		
	    graph.put(1, Arrays.asList(e1, e2));//1 --> 7, 3
	    graph.put(2, Arrays.asList(e2, e3));//7 --> 3, 4
	    graph.put(3, Arrays.asList());
	    graph.put(4, Arrays.asList());

	    GraphInstance instanceTest = new GraphInstance(graph);
	    DijkstraStore vectorStore = new DijkstraVectorStore();
	    
  
		List<GraphInstance> instances = new ReadAllFiles().creatAllInstances();
	    Integer countInstance[] = new Integer[1];
	    countInstance[0] = 0;
		instances.forEach( instance -> {
			    Instant start = Instant.now(); 
			    long timeout = Duration.between(start , start).getSeconds() + 5;
				long durationTime = Duration.between(start, start).getSeconds();  
			    long count = 0;
				while(true){
					
		            if(durationTime > timeout){
		            	break;
		            }
		                
			    	DijkstraSolution solution = instance.dijkstra(1,  vectorStore);
			    	
			    	Instant stop = Instant.now();
			    	durationTime = Duration.between( start , stop).getNano();
			    	
			      //  System.out.println(durationTime);
		            count ++;
			    }
			     countInstance[0]++;
			    System.out.println("No Instance: " + countInstance[0]);
				System.out.println("Quantidade de vezes: " + count);
				System.out.println("Tempo medio: " + String.format("%.2f", (double)(durationTime/count)) );
		
				
//			    for(int i = 0; i < vectorStore.marked.length; i++){
//			    	
//			 	    if(vectorStore.marked[i]){
//			 	    	
//			 	    	System.out.println(i);
//			 	    	System.out.println(vectorStore.costs[i]);
//			 	    }
//			    }
		   
		});
	    
	
	   
	   
	}

}
