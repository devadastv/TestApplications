package com.devadas.poc.algo.graph;

/**
 * To identify if two vertices in a graph is connected - at a CONSTANT time (not varying)
 * This solution keeps the info in a datastructure. All connected vertices will have same cc[v] id.
 * Do a DFS for all unmarked vertices, with incrementing cc ID.
 */
public class ConnectedComponents {
	int[] cc;
	boolean[] visited;
	
	public ConnectedComponents(Graph g) {
		int verticesCount = g.getVerticesCount();
		cc = new int[verticesCount];
		int index = 0;
		visited = new boolean[verticesCount];
		for (int v = 0; v < verticesCount; v++) { // LOGIC: For each vertices in graph...
			if (!visited[v]) { //...which are not visited
				dfs(g, v, index++);
			}
		}
	}

	private void dfs(Graph g, int v, int index) {
		visited[v] = true;
		cc[v] = index;
		for (int w : g.adj(v)) {
			if (!visited[w]) {
				dfs(g, w, index);
			}
		}
	}
	
	public boolean isConnected(int v, int w) {
		return cc[v] == cc[w];
	}
}
