package graphe;
import java.util.*;
import java.util.Map.Entry;

public abstract class Graphe {
	protected Map<String,Sommet> liste_sommets;
	protected Map<Arete, Integer> liste_couts;
	
	/**
	 * Constructeur de la classe Graphe.
	 */
	public Graphe() {
		this.liste_sommets = new HashMap<String, Sommet>();
		this.liste_couts = new HashMap<Arete, Integer>();
	}
	
	/**
	 * Ajoute un sommet dans le graphe.
	 * @param s
	 * @throws IllegalArgumentException
	 */
	public void addSommet(Sommet s) throws IllegalArgumentException{
		if (s == null) {
			throw new IllegalArgumentException("Le sommet passé en paramètre est null!");
		}
		this.liste_sommets.put(s.getId(), s);
	}
	
	/**
	 * Ajoute une arête dans le graphe.
	 * @param a
	 * @throws IllegalArgumentException
	 */
	abstract public void addArete(Arete a) throws IllegalArgumentException;

	
	/**
	 * Supprime le sommet passé en paramètre dans le graphe.
	 * @param s
	 * @throws IllegalArgumentException
	 */
	abstract public void delSommet(Sommet s) throws IllegalArgumentException;
	
	/**
	 * Supprime du graphe l'arête passée en paramètre.
	 * @param a
	 * @throws IllegalArgumentException
	 */
	abstract public void delArete(Arete a) throws IllegalArgumentException;

	
	/**
	 * Reset du marquage effectué dans l'algorithme de parcours en largeur.
	 */
	public void resetMarquage() {
		Iterator<Entry<String, Sommet>> it = this.liste_sommets.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry<String, Sommet> entry = (Map.Entry)it.next();
	        Sommet s = entry.getValue();
	        if (s.getMarquage()) {
	        	s.demarqueSommet();
	        }
	    }
	}
	
	/**
	 * Algorithme de parcours en largeur du graphe, partant du sommet s.
	 * On retourne une TreeMap ayant pour clé l'ordre d'insertion des sommets
	 * et pour valeurs les sommets eux-mêmes.
	 * @param dep
	 */
	public TreeMap<Integer,Sommet> parcours_largeur(Sommet dep) {
		
		TreeMap<Integer, Sommet> sommets_parcours_largeur = new TreeMap<Integer,Sommet>();
		int nombre_insertion = 0;
		LinkedList<Sommet> file_sommets = new LinkedList<Sommet>(); //On utilise une file
		
		file_sommets.addFirst(dep);
		dep.marqueSommet(); //Marquage du sommet de départ
		
		while (! file_sommets.isEmpty()) {
			Sommet s = file_sommets.removeLast();
			sommets_parcours_largeur.put(nombre_insertion, s);
			nombre_insertion++;
			for (Sommet s_voisin : s.getVoisin()) { //Et on regarde ses voisins non-marqués
				if (! s_voisin.getMarquage()) {
					file_sommets.addFirst(s_voisin);
					s_voisin.marqueSommet();
				}
			}
		}
		this.resetMarquage(); //On n'oublie pas de démarquer les sommets à la fin
		return sommets_parcours_largeur;
	}
	
	/**
	 * Mutateur qui permet de modifier la liste des sommets du graphe.
	 * Utile lorsqu'on construit des graphes auxiliaires.
	 * @param liste_sommets
	 * @throws NullPointerException
	 */
	public void setListeSommets(Map<String,Sommet> liste_sommets) throws NullPointerException{
		if (liste_sommets == null) {
			throw new NullPointerException("La liste des sommets en paramètre ne peut pas être null!");
		}
		this.liste_sommets = liste_sommets;
	}
	
	/**
	 * Méthode abstraite de modification du coût du flot. Seulement pour les réseaux.
	 * @param a
	 * @param cst_x
	 * @throws NullPointerException
	 * @throws IllegalArgumentException
	 */
	public abstract void setCstListeX(Arete a, int cst_x) throws NullPointerException, IllegalArgumentException;
	
	/**
	 * Redéfinition de la classe toString.
	 */
	@Override
	public String toString() {
		
		String s = new String();
		
		System.out.println("Graphe G = (V,A): \n");
		
		//On choisit un sommet arbitraire pour le parcours en largeur dans le cadre de l'affichage
		List<Sommet> sommets = new ArrayList<Sommet>(this.liste_sommets.values());
		int randomIndex = new Random().nextInt(sommets.size());
		Sommet dep = sommets.get(randomIndex);

		TreeMap<Integer,Sommet> sommets_parcours_largeur = this.parcours_largeur(dep);
		
		Set<Integer> keys = sommets_parcours_largeur.keySet();
	    for (Iterator<Integer> i = keys.iterator(); i.hasNext();) {
	      Integer key = i.next();
	      s += sommets_parcours_largeur.get(key) + "\n";
	    }
		
		s += "Arêtes : \n"; // On affiche à présent les arêtes
		Set<Arete> aretes = this.liste_couts.keySet();
		for (Arete a : aretes) {
			s += a.toString() + "\n";
		}
		return s;
	}
	
	/**
	 * Méthode toString spécifique.
	 * Permet d'effectuer l'affichage en parcours en largeur depuis une source spécifique.
	 * Très important de procéder comme ceci dans les réseaux du fait de l'orientation des arcs dans le graphe.
	 * @param dep
	 * @return
	 */
	public String toStringFromSource(Sommet dep) {

		String s = new String();
		
		TreeMap<Integer,Sommet> sommets_parcours_largeur = this.parcours_largeur(dep);
		
		Set<Integer> keys = sommets_parcours_largeur.keySet();
	    for (Iterator<Integer> i = keys.iterator(); i.hasNext();) {
	      Integer key = i.next();
	      s += sommets_parcours_largeur.get(key) + "\n";
	    }
		
		s += "Arêtes : \n"; // On affiche à présent les arêtes
		Set<Arete> aretes = this.liste_couts.keySet();
		for (Arete a : aretes) {
			s += a.toString() + "\n";
		}
		return s;
	}
}
