package graph;
import java.util.*;


public class BipartiteGraph extends UndirectedGraph {
	protected Set<Vertex> vertices_u;
	protected Set<Vertex> vertices_v;
	
	/**
	 * Constructor of the BipartiteGraph class.
	 */
	public BipartiteGraph() {
		super();
		vertices_u = new HashSet<Vertex>();
		vertices_v = new HashSet<Vertex>();
	}

	/**
	 * Adds vertex u in subset U.
	 * @param u
	 */
	public void addU(Vertex u) {
		u.setCut("U");
		this.addVertex(u);
		this.vertices_u.add(u);
	}
	
	/**
	 * Adds vertex v in subset V.
	 * @param v
	 */
	public void addV(Vertex v) {
		v.setCut("V");
		this.addVertex(v);
		this.vertices_v.add(v);
	}
	
	/**
	 * Returns the set of vertices from subset U.
	 * @return
	 */
	public Set<Vertex> getverticesU(){
		return this.vertices_u;
	}
	
	
	/**
	 * Returns the set of vertices from subset V.
	 * @return
	 */
	public Set<Vertex> getverticesV(){
		return this.vertices_v;
	}	
	
	/**
	 * Deletes vertex v in the bipartite graph.
	 * @param v
	 * @throws IllegalArgumentException
	 */
	@Override
	public void delVertex(Vertex v) throws IllegalArgumentException {
		
		if (v == null) {
			throw new IllegalArgumentException("Le sommet passé en paramètre est null!");
		}
		else if (! this.vertices.contains(v)) {
			throw new IllegalArgumentException("Le sommet passé en paramètre n'est pas un sommet du graphe!");
		}
		else if (! this.vertices_u.contains(v) && ! this.vertices_v.contains(v)) {
			throw new IllegalArgumentException("Le sommet passé en paramètre ne figure pas dans l'une des partitions!");
		}
		

		this.vertices.remove(v);
		
		//We delete the edges
		for (Vertex v2: this.vertices) {
			if (v2.getNeighbors().contains(v)) {
				Edge e = new Edge (v2, v);
				if (this.edges.contains(e)) {
					this.edges.remove(e);
				}
			}
		}
		
		//We pay attention to reciprocity in the neighbors
		for (Vertex neighbor : v.getNeighbors()) {
			Edge e = new Edge (v, neighbor);
			if (this.vertices.contains(e)) {
				this.vertices.remove(e);
			}
			neighbor.getNeighbors().remove(v); //We can anticipate deletion in triangles sub-structures
		}
		
		//We delete the vertex in the correct subset
		if (v.getCut().equals("U")) {
			this.vertices_u.remove(v);
		}
		else if (v.getCut().equals("V")) {
			this.vertices_v.remove(v);
		}
	}
	
	/**
	 * Overrides toString() so that vertices from subsets U and V appear distinctly.
	 */
	@Override
	public String toString() {
		String string = new String();
		string += "vertices in U : \n";
		for (Vertex s : this.vertices_u) {
			string += s.toString() + "\n";
		}
		string += "\n";
		string += "vertices in V : \n";
		for (Vertex s : this.vertices_v) {
			string += s.toString() + "\n";
		}
		string += "\n";
		string += "Edges : \n"; // We now display the edges
		for (Edge e : this.edges) {
			string += e.toString() + "\n";
		}
		return string;
	}
}
