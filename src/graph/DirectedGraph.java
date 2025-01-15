package graph;

import java.util.*;
import java.lang.Math;

public class DirectedGraph extends Graph {
	
	/**
	 * DirectGraph class constructor.
	 */
	public DirectedGraph() { 
		super();
	}

	/**
	 * Add edge e in the current graph.
	 * @param a
	 * @throws IllegalArgumentException
	 */
	public void addEdge(Edge e) throws IllegalArgumentException {
		if (e == null) {
			throw new IllegalArgumentException("Edge e as a parameter is null");
		}
		this.edges.add(e);
		e.getU().addNeighbor(e.getV());
	}
	
	/**
	 * Deletes vertex v in the current graph.
	 * @param v
	 * @throws IllegalArgumentException
	 */
	public void delVertex(Vertex v) throws IllegalArgumentException {
		
		if (v == null) {
			throw new IllegalArgumentException("Vertex v as a parameter is null");
		}
		else if (! this.vertices.contains(v)) {
			throw new IllegalArgumentException("Vertex v as a parameter is not a vertex of the graph");
		}

		this.vertices.remove(v);
		
		//We delete the edges where v is the head node
		for (Vertex u: v.getNeighbors()) {
			Edge e = new Edge(v, u);
			this.delEdge(e);
		}
		
		//We delete the edges that entails vertex v
		for (Vertex u: this.vertices) {
			if (u.getNeighbors().contains(v)) {
				Edge e = new Edge (u, v);
				this.delEdge(e);
			}
		}
				
	}
	
	/**
	 * Delete edge e from the graph.
	 * @param e
	 * @throws IllegalArgumentException
	 */	
	public void delEdge(Edge e) throws IllegalArgumentException {
			
		if (e == null) {
			throw new IllegalArgumentException("Edge e as a parameter is null");
		}
		else if (! this.edges.contains(e)) {
			throw new IllegalArgumentException("Edge e as a parameter is not an edge from graph G");
		}
		this.edges.remove(e);
		
		//We delete the neighbors (without reciprocity)
		e.getU().delNeighbor(e.getV());
	}
	
	/**
	 * Executes the Edmonds-Karp algorithm on the directed graph (G,f,g)
	 * to maximize the flow and to obtain cut with minimal capacity.
	 * @param source
	 * @param puits
	 * @return the optimal cut Z as a set of its vertices. TODO: adapt the BFS algorithm to obtain such cut
	 *  The values of the flow are directly updated inside the val_x attributes of the edges of the graph.
	 * TODO For now, we assume f(e) = 0 for every edge e. A generalization using Hoffmann's condition should deal with f(e) != 0.
	 */

	public void edmondsKarp (Vertex start, Vertex end){
		//Set<Vertex> min_cut;
		Graph intermediary_graph;
		Set<Edge> type_1_edges;
		Set<Edge> type_2_edges;
		Set<Integer> type_1_values;
		Set<Integer> type_2_values;
		
		int test = 0;
		
		while(true) { //While the cut is not found, we improve the value of the flow
			
			++test;
			if (test > 10) {
				System.out.println("Issues");
				break;
			}
			
			//Building the intermediary graph
			intermediary_graph = new DirectedGraph();
			intermediary_graph.setVertices(this.vertices);
			type_1_edges = new HashSet<Edge>();
			type_2_edges = new HashSet<Edge>();
			type_1_values = new HashSet<Integer>();
			type_2_values = new HashSet<Integer>();
			
			for (Edge e1: this.edges) {
				//Type 1 edge
				if (e1.getValx() < e1.getCstg()) {
					intermediary_graph.addEdge(e1);
					type_1_edges.add(e1);
					type_1_values.add(e1.getCstg()-e1.getValx());
				}
				//Type 2 edge
				else if (e1.getValx() > 0) {
					Edge e2 = new Edge(e1.getV(),e1.getU(),e1.getCost(),e1.getValx(),e1.getCstf(),e1.getCstg());
					intermediary_graph.addEdge(e2);
					type_2_edges.add(e1);
					type_2_values.add(e1.getValx());
				}
			}
			
			System.out.println("Type 1 values:");
			System.out.println(type_1_values);
			System.out.println("Type 2 values:");
			System.out.println(type_2_values);
			
			//If the end vertex is not reachable from the start vertex, then we have found the optimal flow
			if (! intermediary_graph.breadth_first_search(start, end)){
				//min_cut = (Set<Vertex>) bfs_vertices.values();
				//return min_cut;
				return;
			}
			
			//Otherwise, we improve the value of the current flow
			else {
				
				//TODO: Need to build explicitly build the unique (s,t)-path in the intermediary graph
				
				int eps_1;
				int eps_2;
				
				if (! type_1_values.isEmpty()) {
					eps_1 = Collections.min(type_1_values);
				}
				else {
					eps_1 = Integer.MAX_VALUE;
				}
				
				if (! type_2_values.isEmpty()) {
					eps_2 = Collections.min(type_2_values);
				}
				else {
					eps_2 = Integer.MAX_VALUE;
				}
				
				int eps = Math.min(eps_1,eps_2);
				System.out.println("eps = " + eps);
				
				for(Edge e: this.edges) {
					if (type_1_edges.contains(e)) {
						e.setValx(e.getValx()+eps);
					}
					else if (type_2_edges.contains(e)) {
						e.setValx(e.getValx()-eps);
					}
				}
			}
		}
	}

	
}
