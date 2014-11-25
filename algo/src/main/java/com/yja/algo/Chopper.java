package com.yja.algo;

public class Chopper {
	class Result {
		public Result(IndexPair next) {
			this.next = next;
			
		}
		IndexPair next;
		Integer result;
	}

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
		IndexPair indices = new IndexPair(0, toSearch.length-1);
		do {
			if (indices.fromIndex> indices.toIndex)
				return NOT_FOUND;
			if (indices.fromIndex == indices.toIndex)
				return toSearch[indices.fromIndex] == toFind ? indices.toIndex : NOT_FOUND;

			Result result = calculateResult(indices);
			if (result.result != null)
				return result.result;
		} while (indices.fromIndex <= indices.toIndex);
		return NOT_FOUND;
	}

	private Result calculateResult(IndexPair indices) {
		Result result = new Result(indices);
		int midPoint = (indices.fromIndex + indices.toIndex) / 2;
		if (toSearch[midPoint] == toFind)
			result.result = midPoint;

		if (toSearch[midPoint] > toFind) {
			indices.toIndex= midPoint;
		}
		else {
			indices.fromIndex = midPoint + 1;
		}
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
		int midPoint = (next.fromIndex + next.toIndex) / 2;
		if (toSearch[midPoint] > toFind) {
			next.toIndex= midPoint;
		}
		else {
			next.fromIndex = midPoint + 1;
		}
		return next;
	}

	private int findAtMidPoint(IndexPair indices) {
		int midPoint = (indices.fromIndex + indices.toIndex) / 2;
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
