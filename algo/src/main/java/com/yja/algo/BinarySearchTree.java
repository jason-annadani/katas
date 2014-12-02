package com.yja.algo;

import java.util.List;
import java.util.function.Consumer;

import com.google.common.collect.ImmutableList;

public class BinarySearchTree<T extends Comparable<T>> implements BinaryTree<T> {
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
				left = new BinarySearchTree<T>();
			left.add(toAdd);
		} else if (value.compareTo(toAdd) < 0) {
			if (!right.hasValue())
				right = new BinarySearchTree<T>();
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

	@Override
	public void inOrder(Consumer<T> action) {
		left.inOrder(action);
		action.accept(value);
		right.inOrder(action);
	}
}