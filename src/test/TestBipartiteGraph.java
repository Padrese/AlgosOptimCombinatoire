package test;

import graph.*;

public class TestBipartiteGraph {
	
	public static void main(String[] args) {
		
		BipartiteGraph bg = new BipartiteGraph();
		
		//Vertices from subset U
		Vertex u1 = new Vertex("u1");
		Vertex u2 = new Vertex("u2");
		Vertex u3 = new Vertex("u3");
		Vertex u4 = new Vertex("u4");
		
		//Vertices from subset V
		Vertex v1 = new Vertex("v1");
		Vertex v2 = new Vertex("v2");
		Vertex v3 = new Vertex("v3");
		Vertex v4 = new Vertex("v4");
		
		//Edges from the bipartite graph
		Edge a12 = new Edge(u1, v2, 2);
		Edge a21 = new Edge(u2, v1, 1);
		Edge a34 = new Edge(u3, v4, 1);
		Edge a43 = new Edge(u4, v3, 2);
		Edge a41 = new Edge(u4, v1, 4);
		
		//Adding the vertices
		bg.addU(u1);
		bg.addU(u2);
		bg.addU(u3);
		bg.addU(u4);
		
		bg.addV(v1);
		bg.addV(v2);
		bg.addV(v3);
		bg.addV(v4);
		
		bg.addEdge(a12);
		bg.addEdge(a21);
		bg.addEdge(a34);
		bg.addEdge(a43);
		bg.addEdge(a41);
		
		System.out.println(bg);
	}

}
