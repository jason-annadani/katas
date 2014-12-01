package com.yja.algo;

import java.util.List;

interface BinaryTree<T extends Comparable<T>> {

	public void add(T toAdd);

	public int size();

	public int height();

	public boolean hasValue();
	
	public T getValue();
	
	public boolean find(T toFind);

	public List<T> valuesInOrder();

}