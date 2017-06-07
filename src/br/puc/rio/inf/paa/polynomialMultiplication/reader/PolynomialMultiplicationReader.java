package br.puc.rio.inf.paa.polynomialMultiplication.reader;

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

import br.puc.rio.inf.paa.dijkstra.GraphInstance;
import br.puc.rio.inf.paa.polynomialMultiplication.IPolynomialMultiplication;
import br.puc.rio.inf.paa.polynomialMultiplication.Polynomials;
import br.puc.rio.inf.paa.utils.Graph;

public class PolynomialMultiplicationReader {
	
	public List<String> getAllFileNames(){
		
		List<String> fileNames = new ArrayList<String>();

		try(Stream<Path> paths = Files.walk(Paths.get("../INF-2926/input-polynomial-multiplication"))) {
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
	
	public List<Polynomials> creatAllInstances(){
		
		List<String> fileNames = this.getAllFileNames();
		
		List<Polynomials> PolynomialsList = new ArrayList<Polynomials>();
		
		for(String fileName:fileNames){
			
			Polynomials polynomials = this.readFile(fileName);
			PolynomialsList.add(polynomials);
		}
		
		return PolynomialsList;
	}
	
	public Polynomials readFile(String fileName){
		
		int[] polynomialA = null;
	    int[] polynomialB = null;
	    int degree = 0;
		
		try (BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)))) {
		    String line;
		    
		    boolean isPolynomialA = true, waitingDegree = true;
		    int indexA = 0, indexB = 0;
		    
		    while ((line = reader.readLine()) != null) {
		       String words[] = line.split(" ");
		       if(words.length == 1 && waitingDegree){ // Indica o grau do polinômio
		    	   degree = Integer.parseInt(words[0]);
		    	   polynomialA = new int[degree+1];
		    	   polynomialB = new int[degree+1];
		    	   waitingDegree = false;
		       }else if(words.length == 1 && words[0].equals("")){//Divisão entre os polinômios
		    	   isPolynomialA = false;
		       }else if(words.length >= 1 && !waitingDegree){// Pôlinomio
		    	   for(String num: words){
//		    		   System.out.println(num);
		    		   if(isPolynomialA && !num.equals("")){
		    			   polynomialA[indexA] = Integer.parseInt(num);
		    			   indexA++;
		    		   }else if(!isPolynomialA && !num.equals("")){
		    			   polynomialB[indexB] = Integer.parseInt(num);
		    			   indexB++;
		    		   }
		    	   }
		       }
		    }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new Polynomials(polynomialA, polynomialB, degree);
	
	}
	
}
