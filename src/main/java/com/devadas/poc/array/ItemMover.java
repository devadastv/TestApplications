package com.devadas.poc.array;

import java.util.Arrays;

/**
 * Given an array containing integers, modify the array such that the 5’s are at
 * the end and the rest are at the beginning (maintaining the same order).
 * https://www.geeksforgeeks.org/amazon-interview-set-104/
 */
public class ItemMover {

	public static void main(String[] args) {
		int[] a = {1, 2, 3, 4, 5, 6, 7, 5, 4, 5, 5, 5, 10, 11, 5, 5 };
		moveToEnd(a, 5);
	}

	/**
	 * j is the runner which is the pointer of the item to replace. j skips 5s.
	 * i is the pointer to the item to get replaced
	 * @param a
	 * @param d
	 */
	private static void moveToEnd(int[] a, int d) {
		int i = 0, j = 0;
		while (j < a.length - 1) {
			while (a[j] == d && j < a.length - 1) {
				j++;
				System.out.println("match at " + j + ", incrementing");
			}
			System.out.println("replacing " + i + " with " + j);
			a[i++] = a[j++];
		}
		while (i < a.length) {
			a[i++] = d;
		}
		System.out.println("Done");
		System.out.println(Arrays.toString(a));
	}

}
