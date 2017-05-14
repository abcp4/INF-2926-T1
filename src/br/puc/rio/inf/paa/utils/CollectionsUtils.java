package br.puc.rio.inf.paa.utils;

import java.util.Collections;
import java.util.List;

public class CollectionsUtils {
	
	
	public static  <T> List<T> setSize(int size){
		return (List<T>) Collections.nCopies(size, 0);	
	}

}
