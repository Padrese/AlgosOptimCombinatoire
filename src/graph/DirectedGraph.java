package graph;

import java.util.*;

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
		e.getU().addVoisin(e.getV());
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
		
		//We delete the edges that entails vertex v
		for (Vertex sommet: this.vertices) {
			if (sommet.getNeighbors().contains(v)) {
				Edge e = new Edge (sommet, v);
				if (this.edges.contains(e)) {
					this.edges.remove(e);
				}
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
			throw new IllegalArgumentException("L'arête passée en paramètre est null!");
		}
		else if (! this.edges.contains(e)) {
			throw new IllegalArgumentException("L'arête passée en paramètre n'est pas une arête du graphe!");
		}
		this.edges.remove(e);
		
		//We delete the neighbors
		e.getU().delVoisin(e.getV());
	}
	
	/**
	 * Executes the Edmonds-Karp algorithm on the directed graph (G,f,g)
	 * to maximize the flow and to obtain cut with minimal capacity.
	 * @param source
	 * @param puits
	 * @return the optimal cut Z as a set of its vertices.
	 *  The values of the flow are directly updated inside the val_x attributes of the edges of the graph.
	 * TODO For now, we assume f(e) = 0 for every edge e. A generalization using Hoffmann's condition should deal with f(e) != 0.
	 */

	public Set<Vertex> edmondsKarp (Vertex start, Vertex end){
		Set<Vertex> coupe_min;

		while(true) { //While the cut is not found, we improve the value of the flow
			
			//Building the intermediary graph
			Graph auxiliary_graph = new DirectedGraph();
			auxiliary_graph.setListeSommets(this.vertices);
			for (Edge e1: this.edges) {
				//Type 1 edge
				if (e1.getValx() < e1.getCstg()) {
					auxiliary_graph.addEdge(e1);
				}
				//Type 2 edge
				else if (e1.getValx() > 0) {
					Edge e2 = new Edge(e1.getV(),e1.getU(),e1.getCost(),e1.getValx(),e1.getCstf(),e1.getCstg());
					auxiliary_graph.addEdge(e2);
				}
			}
			
			TreeMap<Integer,Vertex> dfs_vertices = auxiliary_graph.depth_first_search(start);
			
			//If the end vertex is not in the vertices, then we have found the optimal flow
			if (! dfs_vertices.containsValue(end)){
				coupe_min = (Set<Vertex>) dfs_vertices.values();
				return coupe_min;
			}
			
			//Otherwise, we improve the value of the current flow
			else {
				; // TODO
			}
		}
	}

	
}
