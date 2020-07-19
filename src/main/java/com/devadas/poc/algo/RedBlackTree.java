package com.devadas.poc.algo;

public class RedBlackTree<K extends Comparable<K>, V> extends BST <K, V>{
	
	enum Color {RED, BLACK}
	class Node {
		K k;
		V v;
		Color color;
		Node right, left;
		
		public Node(K k, V v) {
			this.k = k;
			this.v = v;
		}
	}
}
