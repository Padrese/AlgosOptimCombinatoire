package test;

import java.util.*;

import graph.*;

public class TestUndirectedGraph {
	
	public static void main(String[] args) {
		
		//Testing some vertices
		Vertex s1 = new Vertex("1");
		Vertex s2 = new Vertex("2");
		Vertex s3 = new Vertex("3");
		Vertex s4 = new Vertex("4");
		Graph g = new UndirectedGraph();
		
		g.addVertex(s1); //Adding vertices
		g.addVertex(s2);
		g.addVertex(s3);
		g.addVertex(s4);
		
		//Adding edges with generic cost only
		Edge e1 = new Edge(s1,s4,2);
		Edge e2 = new Edge(s3,s4,5);
		Edge e3 = new Edge(s2,s3,1);
		Edge e4 = new Edge(s1,s3,7);
		Edge e5 = new Edge(s2,s1);
		g.addEdge(e1);
		g.addEdge(e2);
		g.addEdge(e3);
		g.addEdge(e4);
		g.addEdge(e5);
		System.out.println(g);
		
		System.out.println("BFS search on G: \n");
		TreeMap<Integer, Vertex> g_bfs = g.breadth_first_search(s1); //BFS test
		for (Vertex s: g_bfs.values()) {
			System.out.println(s);
		}
		System.out.println("\n");
		
		g.delVertex(s3); //Deleting a vertex
		System.out.println("Graph G = (V,A) after deleting s3: \n");
		System.out.println(g);
		
		g.delEdge(e1); //Deleting an edge
		System.out.println("Graph G = (V,A) after deleting edge 1-4: \n");
		System.out.println(g);
		
		g.delEdge(e4); //Raises an exception because e4 doesn't exist anymore
	
	}

}
