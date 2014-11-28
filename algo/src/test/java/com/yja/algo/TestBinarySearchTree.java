package com.yja.algo;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class TestBinaryTree {
	private static final Integer SENTINEL = 42;
	private BinaryTree<Integer> underTest = new BinaryTree<>();  
	
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
	public void calculatesHeight() {
		underTest.add(5);
		assertThat(underTest.height(), equalTo(0));
		underTest.add(1);
		assertThat(underTest.height(), equalTo(1));
		underTest.add(7); // balanced
		assertThat(underTest.height(), equalTo(1));
		underTest.add(0); 
		assertThat(underTest.height(), equalTo(2));
	}	
}

class BinaryTree<T> {
	private T value;
	private BinaryTree<T> left = NullBinaryTree.NULL_TREE; 
	private BinaryTree<T> right = NullBinaryTree.NULL_TREE;
	
	public BinaryTree() {
	}

	public void add(T element) {
		if (noValue()) {
			value = element;
			return;
		}
		if (noLeft()) {
			left = new BinaryTree<T>();
			left.add(value);
			return;
		}
		if (noRight()) {
			right = new BinaryTree<T>();
			right.add(value);
			return;
		}
		left.add(element);
	}

	private boolean noValue() {
		return null == value;
	}

	private boolean noRight() {
		return right == NullBinaryTree.NULL_TREE;
	}

	private boolean noLeft() {
		return left == NullBinaryTree.NULL_TREE;
	}

	public int size() {
		if (noValue())
			return 0;
		return 1 + left.size() + right.size();
	}

	public int height() {
		if (noLeft() && noRight() || noValue()) 
			return 0;
		return 1 + Math.max(left.height(), right.height());
	}
}

class NullBinaryTree<T> extends BinaryTree<T> {
	static NullBinaryTree NULL_TREE = new NullBinaryTree();
	
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
	
	
}