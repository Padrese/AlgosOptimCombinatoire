package graph;
import java.util.*;

/**
 * Vertex class that represents a vertex in a graph.
 * @author alan
 *
 */
public class Vertex {
	
	private String id;
	private Set<Vertex> neighbors;
	private String cut; //To indicate the subset of vertices where vertex v belongs to (useful for bipartite graphs)
	
	/**
	 * Vertex class constructor, without specification of the cut.
	 * @param id
	 * @throws IllegalArgumentException
	 */
	public Vertex(String id) throws IllegalArgumentException {
		if (id == null) {
			throw new IllegalArgumentException("Vertex identifier cannot be null");
		}
		this.id = id;
		this.neighbors = new HashSet<Vertex>();
		this.cut = new String();
	}
	
	/**
	 * Vertex class constructor, with specification of the cut.
	 * @param id
	 * @param cut
	 * @throws IllegalArgumentException
	 */
	public Vertex(String id, String cut) throws IllegalArgumentException {
		if (id == null) {
			throw new IllegalArgumentException("Vertex identifier cannot be null");
		}
		this.id = id;
		this.neighbors = new HashSet<Vertex>();
		this.cut = cut;
	}
	
	/**
	 * Vertex identifier getter.
	 * @return
	 */
	public String getId() {
		return this.id;
	}
	
	/**
	 * Vertex neighbors getter.
	 * @return
	 */
	public Set<Vertex> getNeighbors() {
		return this.neighbors;
	}
	
	/**
	 * Cut getter associated to the vertex this.
	 * @return
	 */
	public String getCut() {
		return this.cut;
	}
	
	/**
	 * Adds a neighbor.
	 * @param s
	 * @throws IllegalArgumentException
	 */
	public void addNeighbor(Vertex v) throws IllegalArgumentException{
		if (v == null) {
			throw new IllegalArgumentException("Vertex v as a parameter is null");
		}
		this.neighbors.add(v);
	}
	
	
	/**
	 * Deletes a neighbor, if it exists.
	 * @param v
	 * @throws IllegalArgumentException
	 */
	public void delNeighbor(Vertex v) throws IllegalArgumentException {
		if (! this.neighbors.contains(v)) {
			throw new IllegalArgumentException("Le sommet passé en paramètre n'est pas voisin du sommet " + this.id);
		}
		else if (v == null) {
			throw new IllegalArgumentException("Le sommet passé en paramètre est null!");
		}
		this.neighbors.remove(v);
	}
	
	/**
	 * Sets the cut where the current vertex belongs to.
	 * @param s
	 */
	public void setCut(String s) {
		this.cut = s;
	}
	
	/**
	 * Redefines equals for class Vertex.
	 * @param o
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof Vertex) {
			Vertex s = (Vertex) o;
			if (this.id == s.id) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Overrides toString to display a Vertex and its neighbors.
	 */
	@Override
	public String toString() {
		String s = new String();
		s += "Vertex " + this.id + ", neighbors: ";
		for (Vertex sommet : this.neighbors) {
			s += sommet.getId() + " ";
		}
		return s;
	}
	
}
