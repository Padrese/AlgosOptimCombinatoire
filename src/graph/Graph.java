package graph;
import java.util.*;
import java.util.Map.Entry;

public abstract class Graph {
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
	 * BFS algorithm starting from vertex v
	 * @param v
	 * @return vertices_bfs, a TreeMap having the order of insertion of the vertices as keys and the vertices as values.
	 */
	public TreeMap<Integer,Vertex> breadth_first_search(Vertex v) {
		
		TreeMap<Integer, Vertex> vertices_bfs = new TreeMap<Integer,Vertex>();
		int tree_key = 0;
		LinkedList<Vertex> vertex_queue = new LinkedList<Vertex>();
		Set<Vertex> explored = new HashSet<Vertex>(); //Set of explored vertices
		
		vertex_queue.addFirst(v);
		explored.add(v);
		
		while (! vertex_queue.isEmpty()) {
			Vertex s = vertex_queue.removeLast();
			vertices_bfs.put(tree_key, s);
			explored.add(s);
			++tree_key;
			for (Vertex s_voisin : s.getNeighbors()) { 
				if (! explored.contains(s_voisin) && ! vertex_queue.contains(s_voisin)) {
					vertex_queue.addFirst(s_voisin);
				}
			}
		}
		return vertices_bfs;
	}
	
	/**
	 * BFS algorithm starting from vertex v and checking that a path exists from v to t
	 * @param v,t
	 * @return true or false whether a (v,t)-path exists or not.
	 */
	public boolean breadth_first_search(Vertex v, Vertex t) {

		LinkedList<Vertex> vertex_queue = new LinkedList<Vertex>();
		Set<Vertex> explored = new HashSet<Vertex>(); //Set of explored vertices
		
		vertex_queue.addFirst(v);
		explored.add(v);
		
		while (! vertex_queue.isEmpty()) {
			Vertex s = vertex_queue.removeLast();
			explored.add(s);
			for (Vertex s_neighbor : s.getNeighbors()) {
				if (s_neighbor.equals(t)) {
					return true;
				}
				if (! explored.contains(s_neighbor)) {
					vertex_queue.addFirst(s_neighbor);
				}
			}
		}
		return false;
	}
	
	/**
	 * A mutator that modifies the list of vertices in the graph.
	 * Useful when building auxiliary graphs such as the ones into the Edmonds-Karp algorithm.
	 * @param vertices
	 * @throws NullPointerException
	 */
	public void setVertices(Set<Vertex> vertices) throws NullPointerException{
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
		
		for (Vertex v: vertices) {
	      s += v + "\n";
	    }
		
		s += "Edges : \n"; // We now display the edges
		for (Edge a : edges) {
			s += a.toString() + "\n";
		}
		return s;
	}
}
