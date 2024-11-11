package graphe;
import java.util.*;

/**
 * Classe Sommet représentant un sommet dans un graphe.
 * @author alan
 *
 */
public class Sommet {
	
	private String id;
	private Set<Sommet> voisins;
	private String coupe; //Pour spécifier, dans un graphe biparti, la partition dont fait partie le sommet
	private boolean marque; //Pour l'algorithme de recherche en largeur
	
	/**
	 * Constructeur de la classe Sommet, sans spécification de la coupe.
	 * @param numero
	 * @throws IllegalArgumentException
	 */
	public Sommet(String id) throws IllegalArgumentException {
		if (id == null) {
			throw new IllegalArgumentException("L'identifiant d'un sommet ne peut pas être null!");
		}
		this.id = id;
		this.voisins = new HashSet<Sommet>();
		this.coupe = new String();
		this.marque = false;
	}
	
	/**
	 * Constructeur de la classe Sommet, avec spécification de la coupe.
	 * @param numero
	 * @param coupe
	 * @throws IllegalArgumentException
	 */
	public Sommet(String id, String coupe) throws IllegalArgumentException {
		if (id == null) {
			throw new IllegalArgumentException("L'identifiant d'un sommet ne peut pas être null!");
		}
		this.id = id;
		this.voisins = new HashSet<Sommet>();
		this.coupe = coupe;
		this.marque = false;
	}
	
	/**
	 * Accesseur de l'identifiant un sommet.
	 * @return
	 */
	public String getId() {
		return this.id;
	}
	
	/**
	 * Accesseur des voisins d'un sommet donné.
	 * @return
	 */
	public Set<Sommet> getVoisin() {
		return this.voisins;
	}
	
	/**
	 * Accesseur de la coupe (l'ensemble des sommets auquel appartient le sommet this).
	 * @return
	 */
	public String getCoupe() {
		return this.coupe;
	}
	
	/**
	 * Ajoute un voisin.
	 * On construit ainsi l'arête entre deux sommets dans un graphe non-orienté.
	 * @param s
	 * @throws IllegalArgumentException
	 */
	public void addVoisin(Sommet s) throws IllegalArgumentException{
		if (s == null) {
			throw new IllegalArgumentException("Le sommet passé en paramètre est null!");
		}
		this.voisins.add(s);
	}
	
	
	/**
	 * Supprime un voisin, s'il existe.
	 * On supprime ainsi l'arête entre les deux sommets dans un graphe non-orienté.
	 * @param s
	 * @throws IllegalArgumentException
	 */
	public void delVoisin(Sommet s) throws IllegalArgumentException {
		if (! this.voisins.contains(s)) {
			throw new IllegalArgumentException("Le sommet passé en paramètre n'est pas voisin du sommet " + this.id);
		}
		else if (s == null) {
			throw new IllegalArgumentException("Le sommet passé en paramètre est null!");
		}
		this.voisins.remove(s);
	}
	
	/**
	 * Retourne l'état du marquage.
	 * @return
	 */
	public boolean getMarquage() {
		return this.marque;
	}
	
	/**
	 * Marque un sommet.
	 */
	public void marqueSommet() {
		this.marque = true;
	}
	
	/**
	 * Démarque un sommet.
	 */
	public void demarqueSommet() {
		this.marque = false;
	}
	
	/**
	 * Mutateur pour modifier la valeur d'appartenance d'un sommet à une coupe.
	 * @param s
	 */
	public void setCoupe(String s) {
		this.coupe = s;
	}
	
	/**
	 * Redéfinit equals pour des sommets.
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof Sommet) {
			Sommet s = (Sommet) o;
			if (this.id == s.id) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Affichage d'un sommet et de ses voisins.
	 */
	@Override
	public String toString() {
		String s = new String();
		s += "Sommet numéro " + this.id + ", voisins: ";
		for (Sommet sommet : this.voisins) {
			s += sommet.getId() + " ";
		}
		return s;
	}
	
}
