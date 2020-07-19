package com.devadas.poc.algo.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

/**
 * This logic populates the path and 'isTwoVerticesConnected' info at startup
 * based on a start vertex
 */
public class DFS_Util {

	boolean[] visited;
	int[] edgeTo;
	int s;
	
	// s is the source vertex from which search
	public DFS_Util(Graph g, int s) {
		Objects.requireNonNull(g);
		visited = new boolean[g.getVerticesCount()];
		edgeTo = new int[g.getVerticesCount()];
		this.s = s;
		dfs(g, s);
	}

	private void dfs(Graph g, int v) {
		visited[v] = true;
		for (int w : g.adj(v)) {
			if (!visited[w]) {
				edgeTo[w] = v;
				dfs(g, w);
			}
		}
	}
	
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
