package com.devadas.poc.algo.graph;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class BFS_Util {

	boolean[] visited;
	int[] edgeTo;
	int s;
	
	public BFS_Util(Graph g, int s) {
		edgeTo = new int[g.getVerticesCount()];
		visited = new boolean[g.getVerticesCount()];
		this.s = s;
		bfs(g, s);
	}

	private void bfs(Graph g, int s) {
		// LinkedList is used as a Queue impl here. add() adds to end of list, remove() removes from head.
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(s);
		visited[s] = true;
		while(!queue.isEmpty()) {
			int v = queue.remove(); //ie, dequeue
			List<Integer> neighbours = g.adj(v);
			for (Integer w : neighbours) {
				if (!visited[w]) {
					queue.add(w); //ie, enqueue
					edgeTo[w] = v;
					visited[w] = true;
				}
			}
		}
	}
	
	//////////////////// BOTH BELOW METHODS ARE SAME AS IN DFS /////////////////////////
	
	// Check if vertices s and w are connected
	public boolean hasPathTo(int v) {
		return visited[v];
	}
	
	public Iterable<Integer> getPath(int v) {
		if (!hasPathTo(v)) return null;
		Stack<Integer> path = new Stack<>();
		while(v != s) {
			path.push(edgeTo[v]);
			v = edgeTo[v];
		}
		return path;
	}
}
