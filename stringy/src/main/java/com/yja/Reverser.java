package com.yja;


/**
 * Hello world!
 *
 */
public class Reverser 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }

	public String reverse(String s) {
		if (null == s) {
			return null;
		}
		if (s.length() < 1)
			return s;
		return reverse(s.substring(1)) + s.charAt(0);
	}

	public String reverseIter(String s) {
		if (null == s) {
			return null;
		}
		int length = s.length();
		char[] copy = new char[length];
		for (char c : s.toCharArray()) {
			copy[--length] = c;
		}
		return new String(copy);
	}
}
