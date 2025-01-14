package graph;
import java.util.*;
import java.util.Map.Entry;

public abstract class Graph {
	/*
	protected Map<String,Vertex> vertices;
	protected Map<Edge, Integer> edge_costs;
	*/
	protected Set<Vertex> vertices;
	protected Set<Edge> edges;
	
	/**
	 * Graph class constructor.
	 */
	public Graph() {
		/**
		this.vertices = new HashMap<String, Vertex>();
		this.edge_costs = new HashMap<Edge, Integer>();
		*/
		this.vertices = new HashSet<Vertex>();
		this.edges = new HashSet<Edge>();
	}
	
	/**
	 * Adds a vertex in the graph.
	 * @param v
	 * @throws IllegalArgumentException
	 */
	public void addVertex(Vertex v) throws IllegalArgumentException{
		if (v == null) {
			throw new IllegalArgumentException("Vertex v as a parameter is null");
		}
		this.vertices.add(v);
	}
	
	/**
	 * Adds an edge in the graph.
	 * @param e
	 * @throws IllegalArgumentException
	 */
	abstract public void addEdge(Edge e) throws IllegalArgumentException;

	
	/**
	 * Deletes vertex v in the graph.
	 * @param v
	 * @throws IllegalArgumentException
	 */
	abstract public void delVertex(Vertex v) throws IllegalArgumentException;
	
	/**
	 * Deletes edge e in the graph.
	 * @param a
	 * @throws IllegalArgumentException
	 */
	abstract public void delEdge(Edge e) throws IllegalArgumentException;
	
	/**
	 * DFS algorithm starting from vertex v
	 * @param v
	 * @return vertices_dfs, a TreeMap having the order of insertion of the vertices as keys and the vertices as values.
	 */
	public TreeMap<Integer,Vertex> depth_first_search(Vertex v) {
		
		TreeMap<Integer, Vertex> vertices_dfs = new TreeMap<Integer,Vertex>();
		int nombre_insertion = 0;
		LinkedList<Vertex> vertex_queue = new LinkedList<Vertex>();
		Set<Vertex> explored = new HashSet<Vertex>(); //Set of explored vertices
		
		vertex_queue.addFirst(v);
		explored.add(v);
		
		while (! vertex_queue.isEmpty()) {
			Vertex s = vertex_queue.removeLast();
			vertices_dfs.put(nombre_insertion, s);
			explored.add(s);
			nombre_insertion++;
			for (Vertex s_voisin : s.getNeighbors()) { //Et on regarde ses voisins non-marqués
				if (! explored.contains(s_voisin)) {
					vertex_queue.addFirst(s_voisin);
				}
			}
		}
		return vertices_dfs;
	}
	
	/**
	 * A mutator that modifies the list of vertices in the graph.
	 * Useful when building auxiliary graphs such as the ones into the Edmonds-Karp algorithm.
	 * @param vertices
	 * @throws NullPointerException
	 */
	public void setListeSommets(Set<Vertex> vertices) throws NullPointerException{
		if (vertices == null) {
			throw new NullPointerException("Vertex list as a parameter cannot be null");
		}
		this.vertices = vertices;
	}
	

	/**
	 * Overrides toString() to display a graph, its vertices and its edges.
	 */
	@Override
	public String toString() {
		
		String s = new String();
		
		System.out.println("Graph G = (V,A): \n");
		
		//We choose an arbitrary vertex for the DFS algortihm
		int index = new Random().nextInt(vertices.size());
		Iterator<Vertex> iter = vertices.iterator();
		for (int i = 0; i < index; i++) {
		    iter.next();
		}
		Vertex v = iter.next();

		TreeMap<Integer,Vertex> vertices_dfs = this.depth_first_search(v);
		
		Set<Integer> keys = vertices_dfs.keySet();
	    for (Iterator<Integer> i = keys.iterator(); i.hasNext();) {
	      Integer key = i.next();
	      s += vertices_dfs.get(key) + "\n";
	    }
		
		s += "Edges : \n"; // We now display the edges
		for (Edge a : edges) {
			s += a.toString() + "\n";
		}
		return s;
	}
	
	/**
	 * Specific toString method.
	 * Allows display with DFS from a specific source, unlike the classical overridden toString() method behavior.
	 * Very important in networks, because of the orientation of arcs in the graph
	 * @param v
	 * @return
	 */
	public String toStringFromSource(Vertex v) {

		String s = new String();
		
		TreeMap<Integer,Vertex> vertices_dfs = this.depth_first_search(v);
		
		Set<Integer> keys = vertices_dfs.keySet();
	    for (Iterator<Integer> i = keys.iterator(); i.hasNext();) {
	      Integer key = i.next();
	      s += vertices_dfs.get(key) + "\n";
	    }
		
		s += "Edges : \n"; // We now display the edges
		for (Edge a : edges) {
			s += a.toString() + "\n";
		}
		return s;
	}
}
