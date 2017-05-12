package br.puc.rio.inf.paa.djikstra.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestList {

	public static void main(String args[]){
		
		List<String> nomes = new ArrayList<>(Collections.nCopies(10, ""));
		
		System.out.println(nomes.size());
		
	}
}
