package graph;

public class UndirectedGraph extends Graph{
	
	/**
	 * UndirectedGraph class constructor.
	 */
	public UndirectedGraph() {
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
		e.getV().addVoisin(e.getU());
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
		
		//We pay attention to the reciprocity here
		for (Vertex voisin : v.getNeighbors()) {
			Edge a = new Edge (v, voisin);
			if (this.edges.contains(a)) {
				this.edges.remove(a);
			}
			voisin.delVoisin(v); //We can look at triangles relationships in the graph and anticipate some deletions
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
			throw new IllegalArgumentException("Edge e as a parameter is not an edge from the graph");
		}
		this.edges.remove(e);
		
		//We delete the neighbors
		e.getU().delVoisin(e.getV());
		e.getV().delVoisin(e.getU()); //paying attention to equivalence here
	}
	
}
