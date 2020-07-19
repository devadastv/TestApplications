package com.devadas.poc.algo;

import java.util.NoSuchElementException;

/**
 * https://algs4.cs.princeton.edu/32bst/BST.java.html
 *
 */
public class BST<K extends Comparable<K>, V> {

	class Node {
		private K k;
		private V v;
		private Node right;
		private Node left;

		public Node(K k, V v) {
			this.v = v;
		}
	}
	
	private Node root;
	
	public void put(K k, V v) {
		if (k == null) throw new IllegalArgumentException("Key cannot be null");
		root = put(root, k, v);
	}
	
	private Node put(Node node, K k, V v) {
		if (node == null) return new Node(k, v);
		int cmp = node.k.compareTo(k);
		if (cmp < 1) node.left = put(node.left, k, v);
		else if (cmp > 1) node.right = put(node.right, k, v);
		else node.v = v;
		return node; 
	}

	public V get(K k) {
		return get(root, k);
	}
	
	private V get(Node node, K k) {
		if (node == null) throw new IllegalArgumentException("Key cannot be null");
		int cmp = node.k.compareTo(k);
		if (cmp > 0) return get(node.right, k);
		else if (cmp < 0) return get(node.left, k);
		else return node.v;
	}

	
	public K max() {
		if (root == null) throw new NoSuchElementException("Tree is empty");
		return max(root).k;
	}
	
	private Node max(Node node) {
		if (node.right == null) return node;
		else return max(node.right);
	}

	public K min() {
		if (root == null) throw new NoSuchElementException("Tree is empty");
		return min(root).k;
	}
	
	private Node min(Node node) {
		if (node.left == null) return node;
		else return min(node.left);
	}

	public void deleteMin() {
		if (root == null) throw new NoSuchElementException("Tree is empty");
		root = deleteMin(root);
	}
	
	private Node deleteMin(Node node) {
		if (node.left == null) return node.right;
		if (node.left != null) node.left = deleteMin(node.left);
		return node;
	}

	public void delete(K k) {
		if (k == null) throw new IllegalArgumentException("Key cannot be null");
		root = delete(root, k);
	}

	private Node delete(Node node, K k) {
		if (node == null) throw new NoSuchElementException("Tree is empty");
		int cmp = node.k.compareTo(k);
		if (cmp > 1) node.left =  delete(node.left, k);
		else if (cmp < 1) node.right = delete(node.right, k);
		else {
			if (node.right == null) return node.left;
			if (node.left == null) return node.right;
			Node min = min(node.right);
			deleteMin(node.right);
			return min;
		}
		return node;
	}

	public int size() {
		return size(root);
	}
	
	private int size(Node node) {
		if (node == null) return 0;
		return 1 + size(node.left) + size(node.right);
	}
	
	// number of keys existing in tree below this given key (need not exist in tree)
	public int rank(K k) {
		return rank(root, k); 
	}
	
	private int rank(Node node, K k) {
		if (node == null) return 0;
		int cmp = node.k.compareTo(k);
		if (cmp < 0) return rank(node.left, k);
		else if (cmp > 0) return 1 + size(node.left) + rank(node.right ,k);
		else return size(node.left);
	}
	
	public static void main(String[] args) {

	}

}
