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
		e.getU().addNeighbor(e.getV());
		e.getV().addNeighbor(e.getU());
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
		for (Vertex u: this.vertices) {
			if (u.getNeighbors().contains(v)) {
				try {
					Edge e = new Edge (u, v);
					this.delEdge(e);
				}
				/**
				In the case of an undirected graph, an edge can count for both directions
				but this implementation make defining it for u->v OR for v->u (one side only) sufficient
				*/
				catch(Exception IllegalArgumentException) { 
					Edge e = new Edge(v, u);
					this.delEdge(e);
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
			throw new IllegalArgumentException("Edge e as a parameter is null");
		}
		else if (! this.edges.contains(e)) {
			throw new IllegalArgumentException("Edge e as a parameter is not an edge from the graph");
		}
		this.edges.remove(e);
		
		//We delete the neighbors
		e.getU().delNeighbor(e.getV());
		e.getV().delNeighbor(e.getU()); //paying attention to equivalence here
	}
	
}
