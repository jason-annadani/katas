package com.yja.algo;

interface BinaryTree<T extends Comparable<T>> {

	public void add(T element);

	public int size();

	public int height();

	public boolean hasValue();
	public T getValue();

}