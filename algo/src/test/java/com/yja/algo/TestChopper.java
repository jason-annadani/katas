package com.yja.algo;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TestChopper {

	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] { 
				{ -1, 3, new int[] {} },
				{ -1, 3, new int[] { 1 } }, 
				{ 0, 1, new int[] { 1 } },
				{ 0, 1, new int[] { 1, 3, 5 } },
				{ 1, 3, new int[] { 1, 3, 5 } },
				{ 2, 5, new int[] { 1, 3, 5 } },
				{ -1, 0, new int[] { 1, 3, 5 } },
				{ -1, 2, new int[] { 1, 3, 5 } },
				{ -1, 4, new int[] { 1, 3, 5 } },
				{ -1, 6, new int[] { 1, 3, 5 } },
				{ 0,  1, new int[] { 1, 3, 5, 7} },
				{ 1,  3, new int[] { 1, 3, 5, 7} },
				{ 2,  5, new int[] { 1, 3, 5, 7} },
				{ 3,  7, new int[] { 1, 3, 5, 7} },
				{ -1, 0, new int[] { 1, 3, 5, 7} },
				{ -1, 2, new int[] { 1, 3, 5, 7} },
				{ -1, 4, new int[] { 1, 3, 5, 7} },
				{ -1, 6, new int[] { 1, 3, 5, 7} },
				{ -1, 8, new int[] { 1, 3, 5, 7} },
		});
	}

	private int toFind;
	private int[] toSearch;
	private int expectedResult;

	public TestChopper(int expectedResult, int toFind, int[] toSearch) {
		this.expectedResult = expectedResult;
		this.toFind = toFind;
		this.toSearch = toSearch;
	}

	@Test
	public void shouldRecursivelyChop() {
		assertThat(Chopper.chop(toFind, toSearch), is(equalTo(expectedResult)));
	}

	@Test
	public void shouldIterativelyChop() {
		assertThat(Chopper.iterativeChop(toFind, toSearch), is(equalTo(expectedResult)));
	}
	
}
