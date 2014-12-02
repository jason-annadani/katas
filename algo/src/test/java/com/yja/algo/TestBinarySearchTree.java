package com.yja.algo;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import org.junit.Test;
import org.mockito.InOrder;

import com.google.common.collect.Lists;

public class TestBinarySearchTree {
	private static final Integer SENTINEL = 42;
	private BinaryTree<Integer> underTest = new BinarySearchTree<Integer>();

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
		assertThat(underTest.find(5), equalTo(false));
		underTest.add(5);
		assertThat(underTest.find(5), equalTo(true));
		assertThat(underTest.find(1), equalTo(false));
		underTest.add(1);
		assertThat(underTest.find(1), equalTo(true));
		underTest.add(3);
		underTest.add(4);
		underTest.add(7);
		assertThat(underTest.find(7), equalTo(true));
	}

	@Test
	public void shouldGetValuesInOrder() {
		underTest.add(5);
		underTest.add(1);
		underTest.add(3);
		underTest.add(4);
		underTest.add(7);
		
		List<Integer> actual = Lists.newArrayList();		
		Consumer<Integer> collector = t -> actual.add(t);		
		underTest.inOrder(collector);
		
		assertThat(actual, equalTo(Arrays.asList(1, 3, 4, 5, 7)));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void shouldTraverseInOrder() {
		underTest.add(5);
		underTest.add(1);
		underTest.add(3);
		underTest.add(4);
		underTest.add(7);
		Consumer<Integer> func = mock(Consumer.class);

		underTest.inOrder(func);

		InOrder inOrder = inOrder(func);
		inOrder.verify(func).accept(1);
		inOrder.verify(func).accept(3);
		inOrder.verify(func).accept(4);
		inOrder.verify(func).accept(5);
		inOrder.verify(func).accept(7);
		verifyNoMoreInteractions(func);
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

	@Override
	public void inOrder(Consumer<T> action) {

	}
}