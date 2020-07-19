package com.devadas.poc.algo.graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Graph {
	
	private int verticesCount;
	private List<Integer>[] edgeList;
	
	public Graph(int verticesCount) {
		this.verticesCount = verticesCount;
		edgeList = (List<Integer>[])new List[verticesCount];
		for (int i = 0; i < verticesCount; i++) {
			edgeList[i] = new ArrayList<Integer>();
		}
	}
	
	public void addEdge(int v, int w) {
		edgeList[v].add(w);
		edgeList[w].add(v);
	}
	
	public List<Integer> adj(int v) {
		return edgeList[v];
	}
	
	public int getVerticesCount() {
		return verticesCount;
	}
	
	public static void main(String[] args) {
		Graph g = new Graph(20);
		g.addEdge(0, 1);
		g.addEdge(1, 2);
		g.addEdge(2, 6);
		g.addEdge(2, 5);
		g.addEdge(1, 5);
		g.addEdge(5, 9);
		g.addEdge(5, 8);
		g.addEdge(8, 9);
		g.addEdge(4, 7);
		g.addEdge(3, 8);
		g.addEdge(7, 8);
		g.addEdge(3, 7);
		
		BFS_Util algo = new BFS_Util(g, 1);
//		DFS_Util algo = new DFS_Util(g, 1);
		System.out.println(algo.hasPathTo(9));
		
		Iterable<Integer> path = algo.getPath(9);
		if (null != path) {
			Iterator<Integer> ite = path.iterator();
			while (ite.hasNext()) {
				System.out.println(ite.next());
			}
		}
	}
}
