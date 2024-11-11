package graphe;
import java.util.*;

/**
 * Classe représentant un graphe biparti.
 * @author alan
 *
 */
public class GrapheBiparti extends GrapheNonOriente {
	protected Map<String,Sommet> sommets_u;
	protected Map<String,Sommet> sommets_v;
	
	/**
	 * Constructeur de la classe GrapheBiparti.
	 */
	public GrapheBiparti() {
		super();
		sommets_u = new HashMap<String,Sommet>();
		sommets_v = new HashMap<String,Sommet>();
	}

	/**
	 * Ajoute un sommet dans la partition U.
	 * @param s
	 */
	public void addU(Sommet s) {
		s.setCoupe("U");
		this.addSommet(s);
		this.sommets_u.put(s.getId(), s);
	}
	
	/**
	 * Ajoute un sommet dans la partition V.
	 * @param s
	 */
	public void addV(Sommet s) {
		s.setCoupe("V");
		this.addSommet(s);
		this.sommets_v.put(s.getId(), s);
	}
	
	/**
	 * Accesseur pour la liste des sommets de la coupe U.
	 * @return
	 */
	public Map<String,Sommet> getSommetsU(){
		return this.sommets_u;
	}
	
	
	/**
	 * Accesseur pour la liste des sommets de la coupe V.
	 * @return
	 */
	public Map<String,Sommet> getSommetsV(){
		return this.sommets_v;
	}	
	
	/**
	 * Supprime le sommet passé en paramètre dans le graphe.
	 * @param s
	 * @throws IllegalArgumentException
	 */
	@Override
	public void delSommet(Sommet s) throws IllegalArgumentException {
		
		if (s == null) {
			throw new IllegalArgumentException("Le sommet passé en paramètre est null!");
		}
		else if (! this.liste_sommets.containsValue(s)) {
			throw new IllegalArgumentException("Le sommet passé en paramètre n'est pas un sommet du graphe!");
		}
		else if (! this.sommets_u.containsValue(s) && ! this.sommets_v.containsValue(s)) {
			throw new IllegalArgumentException("Le sommet passé en paramètre ne figure pas dans l'une des partitions!");
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
			voisin.getVoisin().remove(s); //On n'oublie pas aussi de supprimer le sommet dans ma liste des voisins de ses voisins
		}
		
		//Enfin, on supprime également le sommet des partitions
		if (s.getCoupe().equals("U")) {
			this.sommets_u.remove(s.getId(), s);
		}
		else if (s.getCoupe().equals("V")) {
			this.sommets_v.remove(s.getId(), s);
		}
	}
	
	/**
	 * Redéfinition de la méthode toString pour les graphes bipartis.
	 * On affiche d'abord les sommets de la partition U, puis de la partition V.
	 */
	@Override
	public String toString() {
		String string = new String();
		string += "Sommets de u : \n";
		for (Sommet s : this.sommets_u.values()) {
			string += s.toString() + "\n";
		}
		string += "\n";
		string += "Sommets de v : \n";
		for (Sommet s : this.sommets_v.values()) {
			string += s.toString() + "\n";
		}
		string += "\n";
		string += "Arêtes : \n"; // On affiche à présent les arêtes
		Set<Arete> aretes = this.liste_couts.keySet();
		for (Arete a : aretes) {
			string += a.toString() + "\n";
		}
		return string;
	}
}
