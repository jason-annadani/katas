package com.yja;

import static org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class ReverserTest    
{
	private Reverser reverser = new Reverser();

	@Test
	public void reversesEmpty() {
		assertThat(reverser.reverse(""), is(equalTo("")));
	}

	@Test
	public void reversesNull() {
		assertThat(reverser.reverse(null), is(nullValue()));
	}

	@Test
	public void reversesSingleChar() {
		assertThat(reverser.reverse("a"), is(equalTo("a")));
	}

	@Test
	public void reversesString() {
		assertThat(reverser.reverse("abc"), is(equalTo("cba")));
	}
}
