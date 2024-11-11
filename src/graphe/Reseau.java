package graphe;

import java.util.*;

public class Reseau extends Graphe {
	private Map<Arete, Integer> liste_cst_f; //Contraintes de flot sur le réseau
	private Map<Arete, Integer> liste_cst_g;
	private Map<Arete, Integer> liste_cst_x;
	
	/**
	 * Construceur de la classe Réseau, pour modéliser un flot réalisable: f(e) <= x(e) <= g(e) pour tout e.
	 */
	public Reseau() { 
		super();
		this.liste_cst_f = new HashMap<Arete, Integer>();
		this.liste_cst_g = new HashMap<Arete, Integer>();
		this.liste_cst_x = new HashMap<Arete, Integer>();
	}
	
	//Il y aura des méthodes à redéfinir! (qui concernent les voisins des sommets, qui changent selon l'orientation)
	
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
		this.liste_cst_f.put(a, a.getCstf());
		this.liste_cst_g.put(a, a.getCstg());
		this.liste_cst_x.put(a, a.getCstx());
		a.getU().addVoisin(a.getV());
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
				this.delArete(a);
			}
		}
		
		//Réciproquement, pour chaque sommet voisin du sommet supprimé, on supprime l'arête qui est reliée
		for (Sommet voisin : s.getVoisin()) {
			Arete a = new Arete (s, voisin);
			this.delArete(a);
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
		this.liste_cst_f.remove(a);
		this.liste_cst_g.remove(a);
		this.liste_cst_x.remove(a);
		
		//On supprime les voisins associés
		a.getU().delVoisin(a.getV());
	}
	
	/**
	 * Accesseur permettant d'accéder aux valeurs du flot associé au réseau construit.
	 * @return le flot associé à l'instance de la classe Réseau.
	 */
	public Map<Arete, Integer> getListeCstX(){
		return this.liste_cst_x;
	}
	
	/**
	 * Mutateur permettant de modifier la valeur de x(a) pour l'arête a concernée.
	 * @param a
	 * @param cst_x
	 * @throws NullPointerException
	 * @throws IllegalArgumentException
	*/
	public void setCstListeX(Arete a, int cst_x) throws NullPointerException, IllegalArgumentException{
		if (a == null) {
			throw new IllegalArgumentException("L'arête passée en paramètre est null!");
		}
		a.setCstx(cst_x);
		this.liste_cst_x.replace(a, cst_x);
	}
	
	/**
	 * Exécute l'algorithme d'Edmonds-Karp sur un réseau (G,g)
	 * pour trouver un flot de valeur maximum et une coupe de capacité minimum.
	 * @param source
	 * @param puits
	 * @return la coupe min Z sous forme d'ArrayList composée de ses sommets.
	 *  Le flot sera directement modifié au sein de l'instance et pourra être obtenu via l'accesseur associé.
	 * @todo Une généralisation liée aux circulations est prévue, on pose pour le moment f(e) = 0 pour tout e. 
	 */

	public Set<Sommet> edmondsKarp (Sommet source, Sommet puits){
		Set<Sommet> coupe_min;
		HashMap<Arete, Integer> flot_max;
		
		//Initialisation des valeurs du flot
		for (Arete a: this.liste_cst_x.keySet()) {
			this.setCstListeX(a, 0);
		}
		
		while(true) { //Tant que l'on ne retourne pas la coupe, on effectue toutes ces étapes
			
			//Construction du graphe auxiliaire
			Graphe graphe_auxiliaire = new Reseau();
			graphe_auxiliaire.setListeSommets(this.liste_sommets);
			for (Arete a: this.liste_couts.keySet()) {
				//Arête de type 1
				if (a.getCstx() < a.getCstg()) {
					graphe_auxiliaire.addArete(a);
				}
				//Arête de type 2
				else if (a.getCstx() > 0) {
					Arete a2 = new Arete(a.getV(),a.getU(),a.getCout(),a.getCstx(),a.getCstf(),a.getCstg());
					graphe_auxiliaire.addArete(a2);
				}
			}
			
			//On effectue le parcours en largeur dans le graphe auxiliaire
			TreeMap<Integer,Sommet> sommets_parcours_largeur = graphe_auxiliaire.parcours_largeur(source);
			
			//Si le puits n'est pas dans les sommets parcourus, on peut s'arrêter avec notre coupe de capacité minimum
			if (! sommets_parcours_largeur.containsValue(puits)){
				coupe_min = (Set<Sommet>) sommets_parcours_largeur.values();
				return coupe_min;
			}
			
			//Sinon, on récupère le (source,puits)-chemin du graphe auxiliaire
			else {
				; // TODO: À COMPLÉTER...
			}
		}
	}

	
}
