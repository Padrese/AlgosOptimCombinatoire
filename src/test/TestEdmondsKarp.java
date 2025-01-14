package test;

import graph.*;

public class TestEdmondsKarp {
	
	public static void main(String[] args) {
		
		Graph g = new DirectedGraph();
		
		Vertex s = new Vertex("s");
		Vertex s1 = new Vertex("s1");
		Vertex s2 = new Vertex("s2");
		Vertex s3 = new Vertex("s3");
		Vertex t1 = new Vertex("t1");
		Vertex t2 = new Vertex("t2");
		Vertex t3 = new Vertex("t3");
		Vertex t = new Vertex("t");
		
		g.addVertex(s);
		g.addVertex(s1);
		g.addVertex(s2);
		g.addVertex(s3);
		g.addVertex(t1);
		g.addVertex(t2);
		g.addVertex(t3);
		g.addVertex(t);
		
		Edge ss1 = new Edge(s,s1,0,1);
		Edge ss2 = new Edge(s,s2,0,2);
		Edge ss3 = new Edge(s,s3,0,1);
		Edge s1t1 = new Edge(s1,t1,0,2);
		Edge s1s2 = new Edge(s1,s2,0,2);
		Edge s2t2 = new Edge(s2,t2,0,1);
		Edge s3s2 = new Edge(s3,s2,0,2);
		Edge s3t3 = new Edge(s3,t3,0,2);
		Edge t2t1 = new Edge(t2,t1,0,2);
		Edge t2t3 = new Edge(t2,t3,0,2);
		Edge t1t = new Edge(t1,t,0,1);
		Edge t2t = new Edge(t2,t,0,2);
		Edge t3t = new Edge(t3,t,0,1);
		
		g.addEdge(ss1);
		g.addEdge(ss2);
		g.addEdge(ss3);
		g.addEdge(s1t1);
		g.addEdge(s1s2);
		g.addEdge(s2t2);
		g.addEdge(s3s2);
		g.addEdge(s3t3);
		g.addEdge(t2t1);
		g.addEdge(t2t3);
		g.addEdge(t1t);
		g.addEdge(t2t);
		g.addEdge(t3t);
		
		System.out.println(g.toStringFromSource(s));
		
	}

}
