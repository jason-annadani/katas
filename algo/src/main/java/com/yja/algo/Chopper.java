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
		return new Chopper(toSearch, toFind).recursiveChop(new IndexPair(0, toSearch.length - 1));
	}

	public static int iterativeChop(int toFind, int[] toSearch) {
		return new Chopper(toSearch, toFind).iterativeChop();
	}

	private int iterativeChop() {
		boolean stop = false;
		int result;
		IndexPair indices = new IndexPair(0, toSearch.length-1);
		do {
			if (cannotFind(indices)) {
				stop = true;
				result = NOT_FOUND;
			} else {
				result = findAtMidPoint(indices);
			}
			if (result != NOT_FOUND)
				stop = true;
			if (!stop) {
				 indices = getNextIndices(indices); 
			}
		} while (!stop);
		return result;
	}

	private int recursiveChop(IndexPair indices) {
		boolean stop = false;
		int result;
		if (cannotFind(indices)) {
			stop = true;
			result = NOT_FOUND;
		} else {
			result = findAtMidPoint(indices);
		}
		if (result != NOT_FOUND)
			stop = true;
		if (!stop) {
			IndexPair next = getNextIndices(indices); 
			result = recursiveChop(next);
		}
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
		int midPoint = indices.getMidPoint();
		if (toSearch[midPoint] == toFind)
			return midPoint;
		return NOT_FOUND;
	}

	private boolean cannotFind(IndexPair indices) {
		return indices.fromIndex > indices.toIndex 
				|| (indices.fromIndex == indices.toIndex 
				&& toSearch[indices.fromIndex] != toFind );
	}

}
