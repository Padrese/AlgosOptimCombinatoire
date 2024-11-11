package graphe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class GrapheNonOriente extends Graphe{
	
	public GrapheNonOriente() {
		super();
	}
	
	/**
	 * Ajoute une arête dans le graphe.
	 * @param a
	 * @throws IllegalArgumentException
	 */
	public void addArete(Arete a) throws IllegalArgumentException {
		if (a == null) {
			throw new IllegalArgumentException("L'arête passée en paramètre est null!");
		}
		this.liste_couts.put(a, a.getCout());
		a.getU().addVoisin(a.getV());
		a.getV().addVoisin(a.getU()); //Il y a une réciprocité des voisins dans un graphe non-orienté
	}
	
	/**
	 * Supprime le sommet passé en paramètre dans le graphe.
	 * @param s
	 * @throws IllegalArgumentException
	 */
	public void delSommet(Sommet s) throws IllegalArgumentException {
		
		if (s == null) {
			throw new IllegalArgumentException("Le sommet passé en paramètre est null!");
		}
		else if (! this.liste_sommets.containsValue(s)) {
			throw new IllegalArgumentException("Le sommet passé en paramètre n'est pas un sommet du graphe!");
		}
		
		//Suppression du sommet dans la liste des sommets
		this.liste_sommets.remove(s.getId(), s);
		
		//On supprime l'arête associée pour chaque sommet dont le voisin est le sommet supprimé
		for (Sommet sommet: this.liste_sommets.values()) {
			if (sommet.getVoisin().contains(s)) {
				Arete a = new Arete (sommet, s);
				if (this.liste_couts.containsKey(a)) {
					this.liste_couts.remove(a);
				}
			}
		}
		
		//Réciproquement, pour chaque sommet voisin du sommet supprimé, on supprime l'arête qui est reliée
		for (Sommet voisin : s.getVoisin()) {
			Arete a = new Arete (s, voisin);
			if (this.liste_couts.containsKey(a)) {
				this.liste_couts.remove(a);
			}
			voisin.delVoisin(s); //On n'oublie pas aussi de supprimer le sommet dans la liste des voisins de ses voisins
		}
				
	}
	
	/**
	 * Supprime du graphe l'arête passée en paramètre.
	 * @param a
	 * @throws IllegalArgumentException
	 */	
	public void delArete(Arete a) throws IllegalArgumentException {
			
		if (a == null) {
			throw new IllegalArgumentException("L'arête passée en paramètre est null!");
		}
		else if (! this.liste_couts.containsKey(a)) {
			throw new IllegalArgumentException("L'arête passée en paramètre n'est pas une arête du graphe!");
		}
		this.liste_couts.remove(a);
		
		//On supprime les voisins associés
		a.getU().delVoisin(a.getV());
		a.getV().delVoisin(a.getU()); //toujours en prêtant attention à la réciprocité
	}
	
	/**
	 * Mutateur permettant de modifier la valeur de x(a) pour l'arête a concernée.
	 * Seulement pour les réseaux, donc on ne fait rien ici.
	 * @param a
	 * @param cst_x
	 * @throws NullPointerException
	 * @throws IllegalArgumentException
	*/
	public void setCstListeX(Arete a, int cst_x) throws NullPointerException, IllegalArgumentException{
		return;
	}
	
}
