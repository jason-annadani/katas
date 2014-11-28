package com.yja.algo;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class TestBinarySearchTree {
	private static final Integer SENTINEL = 42;
	private BinaryTree<Integer> underTest = new BinarySearchTree<>();  
	
	@Test
	public void adds() {
		assertThat(underTest.size(), equalTo(0));
		underTest.add(SENTINEL);
		assertThat(underTest.size(), equalTo(1));
	}	

	@Test
	public void addsMultiple() {
		underTest.add(5);
		underTest.add(1);
		underTest.add(7);
		underTest.add(0);
		assertThat(underTest.size(), equalTo(4));
	}	

	@Test
	public void shouldNotAddDuplicate() {
		underTest.add(5);
		underTest.add(5);
		assertThat(underTest.size(), equalTo(1));
	}	

	@Test
	public void calculatesHeight() {
		underTest.add(5);
		assertThat(underTest.height(), equalTo(0));
		underTest.add(1);
		assertThat(underTest.height(), equalTo(1));
		underTest.add(7); 
		assertThat(underTest.height(), equalTo(1));
		underTest.add(0); 
		assertThat(underTest.height(), equalTo(2));
	}	

	@Test
	public void isBinarySearchTree() {
		underTest.add(5);
		underTest.add(1);
		assertThat(underTest.height(), equalTo(1));
		underTest.add(3); 
		assertThat(underTest.height(), equalTo(2));
		underTest.add(4); 
		assertThat(underTest.height(), equalTo(3));
	}	

	@Test
	public void shouldFind() {
//		assertThat(underTest.find(5), equalTo(false));
		underTest.add(5);
//		assertThat(underTest.find(5), equalTo(true));
//		assertThat(underTest.find(1), equalTo(false));
		underTest.add(1);
//		assertThat(underTest.find(1), equalTo(true));
		underTest.add(3);
		underTest.add(4);
		underTest.add(7);
		assertThat(underTest.find(7), equalTo(true));
	}	
}

class BinarySearchTree<T extends Comparable<T>> implements BinaryTree<T> {
	private T value;
	private BinaryTree<T> left = new NullBinaryTree<>(); 
	private BinaryTree<T> right = new NullBinaryTree<>();
	
	@Override
	public void add(T toAdd) {
		if (!hasValue()) {
			value = toAdd;
			return;
		}
		if (value.compareTo(toAdd) > 0) {
			if (!left.hasValue())
				left = new BinarySearchTree<>();
			left.add(toAdd);
		} else if (value.compareTo(toAdd) < 0) {
			if (!right.hasValue())
				right = new BinarySearchTree<>();
			right.add(toAdd);
		}
		// duplicate so ignore
	}

	@Override
	public int size() {
		if (!hasValue())
			return 0;
		return 1 + left.size() + right.size();
	}

	@Override
	public int height() {
		if (noChildren() || !hasValue()) 
			return 0;
		return 1 + Math.max(left.height(), right.height());
	}

	private boolean noChildren() {
		return !left.hasValue() && !right.hasValue();
	}
	
	@Override
	public boolean hasValue() {
		return value != null;
	}

	@Override
	public T getValue() {
		return value;
	}

	@Override
	public boolean find(T toFind) {
		if (!hasValue())
			return false;
		int cmp = toFind.compareTo(value);
		if (cmp == 0)
			return true;
		if (cmp < 0) 
			return left.find(toFind);
		return right.find(toFind);
	}
}

class NullBinaryTree<T extends Comparable<T>> implements BinaryTree<T> {
	@Override
	public void add(T element) {
		return;
	}

	@Override
	public int size() {
		return 0;
	}

	@Override
	public int height() {
		return 0;
	}
	
	@Override
	public boolean hasValue() {
		return false;
	}

	@Override
	public T getValue() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean find(T toFind) {
		return false;
	}	
}