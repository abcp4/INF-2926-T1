package br.puc.rio.inf.paa.utils;

import java.util.Collections;
import java.util.List;

public class Utils {

	public static <T> List<T> setSize(int size) {

		return (List<T>) Collections.nCopies(size, 0);
	}

	public static double logBase2(double num) {

		return (Math.log(num) / Math.log(2));
	}

	public static void main(String args[]) {

	}

}
