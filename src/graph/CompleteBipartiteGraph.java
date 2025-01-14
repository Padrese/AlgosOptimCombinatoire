package graph;
import java.util.*;

public class CompleteBipartiteGraph extends BipartiteGraph {

	/**
	 * Constructor of the CompleteBipartiteGraph.
	 * We build all the possible vertices and edges in the constructor as we know the graph is complete.
	 * @param dimension
	 * @throws IllegalArgumentException
	 */
	public CompleteBipartiteGraph(int dimension, int[][] cost_table) throws IllegalArgumentException{
		
		super();
		if (dimension <= 1) {
			throw new IllegalArgumentException("La dimension d'un graphe biparti complet doit être au moins 2!");
		}
		if (cost_table.length != dimension && cost_table[0].length != dimension) {
			throw new IllegalArgumentException("La matrice de coûts n'est pas carrée et/ou n'est pas de la bonne dimension!");
		}
		
		for (int i=0; i<dimension; i++) {
			Vertex u = new Vertex("u"+Integer.toString(i+1),"U");
			this.addU(u);
			
			/**
			if(i == 0) { //We only need to create the vertices during the first iteration of the loop
				for (int j=0; j<dimension; j++) {
					Vertex v = new Vertex("v"+Integer.toString(j+1),"V");
					this.addV(v);
					Edge uv = new Edge(u, v);
					uv.setCost(cost_table[i][j]);
					this.addEdge(uv);
				}
			}
			else {
				for (Vertex v : this.vertices_v) {
					Edge uv = new Edge(u, v);
					System.out.println(Integer.parseInt(v.getId()));
					uv.setCost(cost_table[i][Integer.valueOf(v.getId())-1]);
					this.addEdge(uv);		
				}
			}
			*/
			
			
			for (int j=0; j<dimension; j++) {
				Vertex v;
				if(i == 0) { //We only need to create the vertices during the first iteration of the loop
					v = new Vertex("v"+Integer.toString(j+1),"V");
					this.addV(v);
				}
				else {
					v = (Vertex) this.getverticesV().toArray()[j];
				}
				Edge uv = new Edge(u, v);
				uv.setCost(cost_table[i][j]);
				this.addEdge(uv);
			}
		}
	}
	
}
