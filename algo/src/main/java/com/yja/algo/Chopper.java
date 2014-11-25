package com.yja.algo;

public class Chopper {
	static int NOT_FOUND = -1;
	static IndexPair STOP = null;
	
	private int[] toSearch;
	private int toFind;

	public Chopper(int[] toSearch, int toFind) {
		this.toSearch = toSearch;
		this.toFind = toFind;
	}

	public static int chop(int toFind, int[] toSearch) {
		return new Chopper(toSearch, toFind).recursiveChop(new IndexPair(0, toSearch.length - 1));
	}

	public static int iterativeChop(int toFind, int[] toSearch) {
		return new Chopper(toSearch, toFind).iterativeChop();
	}

	private int iterativeChop() {
		int result;
		IndexPair indices = new IndexPair(0, toSearch.length-1);
		do {
			if (indices.outOfBounds())
				return NOT_FOUND;
			if (notThereWithNothingLeftToBisect(indices))
				return NOT_FOUND;		
			result = findAtMidPoint(indices);
			if (result != NOT_FOUND)
				return result;
			indices = getNextIndices(indices); 
		} while (true);
	}

	private int recursiveChop(IndexPair indices) {
		if (indices.outOfBounds())
			return NOT_FOUND;
		if (notThereWithNothingLeftToBisect(indices))
			return NOT_FOUND;		
		int result = findAtMidPoint(indices);
		if (result != NOT_FOUND)
			return result;
		IndexPair next = getNextIndices(indices); 
		result = recursiveChop(next);
		return result;
	}
	
	private IndexPair getNextIndices(IndexPair indices) {
		IndexPair next = new IndexPair(indices.fromIndex, indices.toIndex);
		int midPoint = next.getMidPoint();
		if (toSearch[midPoint] > toFind) {
			next.toIndex= midPoint;
		}
		else {
			next.fromIndex = midPoint + 1;
		}
		return next;
	}

	private int findAtMidPoint(IndexPair indices) {
		if (indices.outOfBounds())
			return NOT_FOUND;		
		int midPoint = indices.getMidPoint();
		if (toSearch[midPoint] == toFind)
			return midPoint;
		return NOT_FOUND;
	}

	private boolean notThereWithNothingLeftToBisect(IndexPair indices) {
		return indices.fromIndex == indices.toIndex 
				&& toSearch[indices.fromIndex] != toFind;
	}

}
