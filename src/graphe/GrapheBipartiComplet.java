package graphe;
import java.util.*;

public class GrapheBipartiComplet extends GrapheBiparti {

	/**
	 * Constructeur de la classe GrapheBipartiComplet.
	 * Construction en O(|V|^2) car |E| = |V|^2 dans un tel graphe.
	 * @param dimension
	 * @throws IllegalArgumentException
	 */
	public GrapheBipartiComplet(int dimension, int[][] tab_couts) throws IllegalArgumentException{
		
		super();
		if (dimension <= 1) {
			throw new IllegalArgumentException("La dimension d'un graphe biparti complet doit être au moins 2!");
		}
		if (tab_couts.length != dimension && tab_couts[0].length != dimension) {
			throw new IllegalArgumentException("La matrice de coûts n'est pas carrée et/ou n'est pas de la bonne dimension!");
		}
		
		for (int i=0; i<dimension; i++) {
			Sommet u = new Sommet("u"+Integer.toString(i+1),"U");
			this.addU(u);
			
			for (int j=0; j<dimension; j++) {
				if(i == 0) { //On a besoin de créer et d'ajouter les v_{j} que lors de la première itération de la première boucle
					Sommet v = new Sommet("v"+Integer.toString(j+1),"V");
					this.addV(v);
				}
				Arete uv = new Arete(u, this.sommets_v.get("v"+Integer.toString(j+1)), tab_couts[i][j]);
				this.addArete(uv);
			}
		}
	}
	
}
