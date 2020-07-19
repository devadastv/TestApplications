package com.devadas.poc.misc;

import static org.junit.Assert.assertTrue;
import java.util.Objects;

public class ParenthesisValidator {

	public static void main(String[] args) {
		assertTrue(checkValidString("   "));
		assertTrue(checkValidString("[[]]"));
		assertTrue(checkValidString("**]**"));
		assertTrue(checkValidString("*[[[**)*[[**"));
		assertTrue(checkValidString(null));
	}

	/**
	 * Idea is to keep updating the max and min possible open bracket counts. 
	 * The counts differ only when a joker '$' character appears. It can be 
	 * treated as closing bracket, which reduces the count of existing open brackets, if any. 
	 * It can also be treated as opening bracket which increases the max possible count.
	 * It can also be treated as blank, which gets included in the range.
	 * I knew this solution from another interview, but submitting it what I remembers.
	 * @param s
	 * @return
	 */
	public static boolean checkValidString(String s) {
		Objects.requireNonNull(s, "Expression should not be null");
		int minOpen = 0, maxOpen = 0;
		for (char c : s.toCharArray()) {
			if (c == '[') {
				minOpen++;
				maxOpen++;
			} else if (c == ']') {
				minOpen = (minOpen - 1) < 0 ? 0 : (minOpen - 1);
				if (--maxOpen < 0) return false;
			} else if (c == '$') {
				minOpen = (minOpen - 1) < 0 ? 0 : (minOpen - 1);
				maxOpen++;
			}
			System.out.println(c + ": minOpen " + minOpen + " maxOpen " + maxOpen);
		}
		return minOpen == 0;
	}
}
