package graph;

public class Edge {
	private Vertex u; // The two vertices related to the considered edge.
	private Vertex v;
	private int cost; //Generic cost unrelated to a flow problem
	private int cst_f;
	private int cst_g;
	private int val_x;
	
	/**
	 * Edge class constructor, with specification of the cost and without specification of the flow constraints.
	 * @param u
	 * @param v
	 * @param cost
	 * @throws NullPointerException
	 * @throws IllegalArgumentException
	 */
	public Edge(Vertex u, Vertex v, int cost) throws NullPointerException{
		if (u == null) {
			throw new NullPointerException("Vertex u related to current edge is null");
		}
		if (v == null) {
			throw new NullPointerException("Vertex v related to current edge is null");
		}
		this.u = u;
		this.v = v;
		this.cost = cost;
		this.cst_f = 0;
		this.cst_g = 0;
		this.val_x = 0;
	}
	
	/**
	 * Edge class constructor, without specification of the cost nor the flow constraints.
	 * @param u
	 * @param v
	 * @throws NullPointerException
	 * @throws IllegalArgumentException
	 */
	public Edge(Vertex u, Vertex v) throws NullPointerException{
		if (u == null) {
			throw new NullPointerException("Vertex u related to current edge is null");
		}
		if (v == null) {
			throw new NullPointerException("Vertex v related to current edge is null");
		}
		this.u = u;
		this.v = v;
		this.cost = 0;
		this.cst_f = 0;
		this.cst_g = 0;
		this.val_x = 0;
	}
	
	
	/**
	 * Edge class constructor, with specification of the flow constraints only.
	 * Flow value x is initially set to zero.
	 * @param u
	 * @param v
	 * @param cst_g
	 * @param cst_f
	 * @throws NullPointerException
	 * @throws IllegalArgumentException
	 */
	public Edge(Vertex u, Vertex v, int cst_f, int cst_g) throws NullPointerException, IllegalArgumentException{
		if (u == null) {
			throw new NullPointerException("Vertex u related to current edge is null");
		}
		if (v == null) {
			throw new NullPointerException("Vertex v related to current edge is null");
		}
		else if (cst_f > cst_g) {
			throw new IllegalArgumentException("Constraint f is greater than constraint g");
		}
		this.u = u;
		this.v = v;
		this.cost = 0;
		this.cst_f = cst_f;
		this.cst_g = cst_g;
		this.val_x = 0;
	}
	
	
	/**
	 * Edge class constructor, with specification of the cost and with specification of the flow constraints.
	 * @param u
	 * @param v
	 * @param cost
	 * @param cst_f
	 * @param cst_g
	 * @throws NullPointerException
	 * @throws IllegalArgumentException
	 */
	public Edge(Vertex u, Vertex v, int cost, int cst_f, int cst_g) throws NullPointerException, IllegalArgumentException{
		if (u == null) {
			throw new NullPointerException("Vertex u related to current edge is null");
		}
		if (v == null) {
			throw new NullPointerException("Vertex v related to current edge is null");
		}
		else if (cst_f > cst_g) {
			throw new IllegalArgumentException("Constraint f is greater than constraint g");
		}
		this.u = u;
		this.v = v;
		this.cost = cost;
		this.cst_f = cst_f;
		this.cst_g = cst_g;
		this.val_x = 0;
	}
	
	/**
	 * Edge class constructor, with specification of the cost, flow constraints and flow value.
	 * @param u
	 * @param v
	 * @param cout
	 * @param val_x
	 * @param cst_f
	 * @param cst_g
	 * @throws NullPointerException
	 * @throws IllegalArgumentException
	 */
	public Edge(Vertex u, Vertex v, int cost, int val_x, int cst_f, int cst_g) throws NullPointerException, IllegalArgumentException{
		if (u == null) {
			throw new NullPointerException("Vertex u related to current edge is null");
		}
		if (v == null) {
			throw new NullPointerException("Vertex v related to current edge is null");
		}
		else if (cst_f > cst_g) {
			throw new IllegalArgumentException("Constraint f is greater than constraint g");
		}
		this.u = u;
		this.v = v;
		this.cost = cost;
		this.cst_f = cst_f;
		this.cst_g = cst_g;
		this.val_x = val_x;
	}	
	
	/**
	 * Vertex u accessor.
	 * @return v
	 */
	public Vertex getU() {
		return this.u;
	}
	
	/**
	 * Vertex v accessor.
	 * @return v
	 */
	public Vertex getV() {
		return this.v;
	}
	
	/**
	 * Edge generic cost accessor.
	 * @return
	 */
	public int getCost() {
		return this.cost;
	}
	
	/**
	 * Edge generic cost mutator.
	 * @return
	 */
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	/**
	 * Edge flow constraint with respect to f accessor.
	 * @return
	 */
	public int getCstf() {
		return this.cst_f;
	}
	
	/**
	 * Edge flow constraint with respect to g accessor.
	 * @return
	 */
	public int getCstg() {
		return this.cst_g;
	}
	
	/**
	 * Edge flow cost x accessor.
	 * @return
	 */
	public int getValx() {
		return this.val_x;
	}
	
	/**
	 * Edge flow cost mutator.
	 * @param val_x
	 * @throws IllegalArgumentException
	 */
	public void setValx(int val_x) throws IllegalArgumentException{
		if (cst_f > val_x) {
			throw new IllegalArgumentException("The flow won't be feasible because f(e) > x(e)");
		}
		else if (cst_g < val_x) {
			throw new IllegalArgumentException("The flow won't be feasible because g(e) < x(e)");
		}
		this.val_x = val_x;
	}
	
	/**
	 * Overrides toString to represent an edge with its generic cost and flow parameters.
	 */
	@Override
	public String toString() {
		String s = new String();
		s += "Edge " + this.u.getId() + this.v.getId() + " having generic cost " + this.cost + " and flow constraints f(e): "
				+ this.cst_f + " and g(e): " + this.cst_g + " and flow cost x(e): " + this.val_x;
		return s;
	}
	
	/**
	 * Override equals to compare two edges.
	 */
	@Override
	public boolean equals(Object o) {
		if(o instanceof Edge) {
			Edge a = (Edge) o;
			if ((this.u == a.getU() && this.v == a.getV())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Overrides hashCode for an Edge.
	 */
	@Override
	public int hashCode() {
		String concat = this.u.getId() + this.v.getId();
		return concat.hashCode();
	}
	

}
