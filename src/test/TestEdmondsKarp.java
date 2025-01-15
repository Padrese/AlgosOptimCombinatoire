package test;

import graph.*;

public class TestEdmondsKarp {
	
	public static void main(String[] args) {
		
		DirectedGraph g = new DirectedGraph();
		
		Vertex s = new Vertex("s");
		Vertex t = new Vertex("t");
		Vertex s1 = new Vertex("s1");
		Vertex s2 = new Vertex("s2");
		Vertex t1 = new Vertex("t1");
		Vertex t2 = new Vertex("t2");
		
		Edge ss1 = new Edge(s,s1,0,0,3);
		Edge ss2 = new Edge(s,s2,0,0,7);
		Edge s1s2 = new Edge(s1,s2,0,0,2);
		Edge s1t1 = new Edge(s1,t1,0,0,4);
		Edge s1t2 = new Edge(s1,t2,0,0,1);
		Edge s2t2 = new Edge(s2,t2,0,0,2);
		Edge s2t1 = new Edge(s2,t1,0,0,1);
		Edge t1t2 = new Edge(t1,t2,0,0,1);
		Edge t2t1 = new Edge(t2,t1,0,0,5);
		Edge t1t = new Edge(t1,t,0,0,6);
		Edge t2t = new Edge(t2,t,0,0,7);
		
		g.addEdge(ss1);
		g.addEdge(ss2);
		g.addEdge(s1s2);
		g.addEdge(s1t1);
		g.addEdge(s1t2);
		g.addEdge(s2t2);
		g.addEdge(s2t1);
		g.addEdge(t1t2);
		g.addEdge(t2t1);
		g.addEdge(t1t);
		g.addEdge(t2t);
		
		g.edmondsKarp(s,t);
		
		System.out.println(g);
		
	}

}
