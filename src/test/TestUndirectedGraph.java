package test;

import java.util.*;

import graph.*;

/**
 * Classe test des sommets d'un graphe.
 * @author alan
 *
 */
public class TestUndirectedGraph {
	
	public static void main(String[] args) {
		
		//Testing some vertices
		Vertex s1 = new Vertex("1");
		Vertex s2 = new Vertex("2");
		Vertex s3 = new Vertex("3");
		Vertex s4 = new Vertex("4");
		Graph g = new UndirectedGraph();
		
		s1.addVoisin(s2);
		s2.addVoisin(s1);
		s1.addVoisin(s3);
		s3.addVoisin(s1);
		s2.addVoisin(s3);
		s3.addVoisin(s2);
		s3.addVoisin(s4);
		s4.addVoisin(s3);
		s4.addVoisin(s1);
		s1.addVoisin(s4);
		
		System.out.println("Adding neighbors: \n");
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s3);
		System.out.println(s4);
		
		s1.delVoisin(s3);
		s3.delVoisin(s1);
		s2.delVoisin(s1);
		s1.delVoisin(s2);
		
		System.out.println("\n");
		System.out.println("Deleting neighbors: \n");
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s3);
		System.out.println(s4);
		System.out.println("\n");
		
		
		s1.addVoisin(s3); //Re-creating the deleted neighbors
		s3.addVoisin(s1);
		s2.addVoisin(s1);
		s1.addVoisin(s2);
		
		
		g.addVertex(s1); //Adding vertices
		g.addVertex(s2);
		g.addVertex(s3);
		g.addVertex(s4);
		
		Edge a1 = new Edge(s1,s4,2);
		Edge a2 = new Edge(s3,s4,5);
		Edge a3 = new Edge(s2,s3,1);
		Edge a4 = new Edge(s1,s3,7);
		Edge a5 = new Edge(s2,s1);
		g.addEdge(a1);
		g.addEdge(a2);
		g.addEdge(a3);
		g.addEdge(a4);
		g.addEdge(a5);
		System.out.println(g);
		
		System.out.println("DFS search on G: \n");
		TreeMap<Integer, Vertex> g_parcours_largeur = g.depth_first_search(s1); //DFS test
		for (Vertex s: g_parcours_largeur.values()) {
			System.out.println(s);
		}
		System.out.println("\n");
		
		g.delVertex(s3); //Deleting a vertex
		System.out.println("Graph G = (V,A) after deleting s3: \n");
		System.out.println(g);
		
		g.delEdge(a1); //Deleting an edge
		System.out.println("Graph G = (V,A) après deleting 1-4: \n");
		System.out.println(g);
		
		g.delEdge(a4); //Raises an exception because a4 doesn't exist anymore
	
	}

}
