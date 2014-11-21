package com.yja.algo;

public class Chopper {

	static int NOT_FOUND = -1;
	private int[] toSearch;
	private int toFind;
	public Chopper(int[] toSearch, int toFind) {
		this.toSearch = toSearch;
		this.toFind = toFind;		
	}

	public static int chop(int toFind, int[] toSearch) {
		return new Chopper(toSearch, toFind).recursiveChop(0, toSearch.length -1);
	}
	
	private int recursiveChop(int fromIndex, int toIndex) {
		if (fromIndex > toIndex)
			return NOT_FOUND;
		if (fromIndex == toIndex)
			return toSearch[toIndex] == toFind ? toIndex : NOT_FOUND;
		
		int midPoint = (fromIndex + toIndex)/2;
		if (toSearch[midPoint] == toFind)
			return midPoint;
		if (toSearch[midPoint] > toFind)
			return recursiveChop(fromIndex, midPoint);
		else 
			return recursiveChop(midPoint+1, toIndex);
	}

}
