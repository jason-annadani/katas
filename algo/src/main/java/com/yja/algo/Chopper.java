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
		if (indices.fromIndex > indices.toIndex)
			return NOT_FOUND;
		if (indices.fromIndex == indices.toIndex)
			return toSearch[indices.toIndex] == toFind ? indices.toIndex : NOT_FOUND;

		Result result = calculateResult(indices);
		if (result.result != null)
			return result.result;
		return recursiveChop(indices);

	}

}