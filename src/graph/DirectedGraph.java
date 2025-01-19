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
		DirectedGraph intermediary_graph;
		HashMap<Edge, Integer> type_1_edges;
		HashMap<Edge, Integer> type_2_edges;
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
			type_1_edges = new HashMap<Edge, Integer>();
			type_2_edges = new HashMap<Edge, Integer>();
			
			for (Edge e1: this.edges) {
				//Type 1 edge
				if (e1.getValx() < e1.getCstg()) {
					intermediary_graph.addEdge(e1);
					type_1_edges.put(e1, e1.getCstg()-e1.getValx());
				}
				//Type 2 edge
				if (e1.getValx() > 0) {
					Edge e2 = new Edge(e1.getV(),e1.getU(),e1.getCost(),e1.getValx(),e1.getCstf(),e1.getCstg());
					intermediary_graph.addEdge(e2);
					type_2_edges.put(e1, e1.getValx());
				}
			}
			
			//TODO: Corriger cette histoire d'ambiguité des voisins entre le graphe initial et le graphe intermédiaire.
			//IL faut construire les sommets et ses voisins dans ce graphe en fonction des arêtes de type 1 et 2.
			
			System.out.println("Graphe intermédiaire:");
			System.out.println(intermediary_graph);
			
			//If the end vertex is not reachable from the start vertex, then we have found the optimal flow
			ArrayList<Edge> path = intermediary_graph.get_path(start, end);
			if (path == null){
				//min_cut = (Set<Vertex>) bfs_vertices.values();
				//return min_cut;
				return;
			}
			
			//Otherwise, we improve the value of the current flow
			else {
				
				//We compute eps_1 and eps_2 based on the (unique) path between the start node and the end node
				
				//These sets contain the values of the edges from the (start, end) path
				type_1_values = new HashSet<Integer>();
				type_2_values = new HashSet<Integer>();
				
				int eps_1;
				int eps_2;
				
				System.out.println("(s,t)-chemin:");
				for (int i=0; i < path.size(); ++i) {
					System.out.println(path.get(i));
				}
				
				for (int i=0; i < path.size(); ++i) {
					Edge e = path.get(i);
					if (type_1_edges.keySet().contains(e)) {
						System.out.println("On entre ici");
						type_1_values.add(type_1_edges.get(e));
					}
					else {
						System.out.println("On entre là");
						type_2_values.add(type_2_edges.get(e));
					}
				}
				
				System.out.println("Type 1 values:");
				System.out.println(type_1_values);
				System.out.println("Type 2 values:");
				System.out.println(type_2_values);
		
				
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
					if (! path.contains(e)) {
						continue;
					}
					if (type_1_edges.keySet().contains(e)) {
						e.setValx(e.getValx()+eps);
					}
					else if (type_2_edges.keySet().contains(e)) {
						e.setValx(e.getValx()-eps);
					}
				}
				
				System.out.println("État courant du graphe initial:");
				System.out.println(this);
			}
		}
	}

	
}
