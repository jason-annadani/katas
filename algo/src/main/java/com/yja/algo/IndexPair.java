package com.yja.algo;

public class IndexPair {
	public int fromIndex;
	public int toIndex;

	public IndexPair(int fromIndex, int toIndex) {
		this.fromIndex = fromIndex;
		this.toIndex = toIndex;
	}

	int getMidPoint() {
		return (fromIndex + toIndex) / 2;
	}

	boolean outOfBounds() {
		return fromIndex > toIndex;
	}
}