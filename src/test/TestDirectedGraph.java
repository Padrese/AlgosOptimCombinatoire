package test;

import java.util.TreeMap;

import graph.*;

public class TestDirectedGraph {

	public static void main(String[] args) {
		
		Vertex s1 = new Vertex("1");
		Vertex s2 = new Vertex("2");
		Vertex s3 = new Vertex("3");
		Vertex s4 = new Vertex("4");
		Graph g = new DirectedGraph();
		
		g.addVertex(s1);
		g.addVertex(s2);
		g.addVertex(s3);
		g.addVertex(s4);
		
		Edge a1 = new Edge(s1,s4,2,0,3);
		Edge a2 = new Edge(s3,s4,5,0,1);
		Edge a3 = new Edge(s2,s3,1,0,2);
		Edge a4 = new Edge(s1,s3,7,0,1);
		Edge a5 = new Edge(s1,s2,0,1);
		g.addEdge(a1);
		g.addEdge(a2);
		g.addEdge(a3);
		g.addEdge(a4);
		g.addEdge(a5);
		System.out.println("Graph G = (V,A): \n");
		System.out.println(g);
		
		//We change the value of the flow on an edge
		a1.setValx(2);
		
		System.out.println("BFS search on G: \n");
		TreeMap<Integer, Vertex> g_bfs = g.breadth_first_search(s1); //BFS test
		for (Vertex s: g_bfs.values()) {
			System.out.println(s);
		}
		System.out.println("\n");
		
		//Deleting a vertex 
		g.delVertex(s3);
		System.out.println("Graph G = (V,A) after deleting s3: \n");
		System.out.println(g);
		
		//Deleting an edge
		g.delEdge(a1);
		System.out.println("Graph G = (V,A) after deleting edge 1-4: \n");
		System.out.println(g);
		
		g.delEdge(a4); //Raises an exception because a4 doesn't exist anymore in G
	}
}
