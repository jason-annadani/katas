package com.yja.algo;

interface BinaryTree<T extends Comparable<T>> {

	public void add(T toAdd);

	public int size();

	public int height();

	public boolean hasValue();
	
	public T getValue();
	
	public boolean find(T toFind);

}