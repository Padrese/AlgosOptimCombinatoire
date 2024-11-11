package test;

import graphe.*;
import java.util.*;

/**
 * Classe test des sommets d'un graphe.
 * @author alan
 *
 */
public class TestGrapheNonOriente {
	
	public static void main(String[] args) {
		
		//On fait quelques tests sur les sommets au préalable
		Sommet s1 = new Sommet("1");
		Sommet s2 = new Sommet("2");
		Sommet s3 = new Sommet("3");
		Sommet s4 = new Sommet("4");
		Graphe g = new GrapheNonOriente();
		
		s1.addVoisin(s2); //Ajout de voisins
		s2.addVoisin(s1);
		s1.addVoisin(s3);
		s3.addVoisin(s1);
		s2.addVoisin(s3);
		s3.addVoisin(s2);
		s3.addVoisin(s4);
		s4.addVoisin(s3);
		s4.addVoisin(s1);
		s1.addVoisin(s4);
		
		System.out.println("Ajout de voisins: \n");
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s3);
		System.out.println(s4);
		
		s1.delVoisin(s3);
		s3.delVoisin(s1);
		s2.delVoisin(s1);
		s1.delVoisin(s2);
		
		System.out.println("\n");
		System.out.println("Suppression de voisins: \n");
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s3);
		System.out.println(s4);
		System.out.println("\n");
		
		
		s1.addVoisin(s3); //On recrée les voisins supprimés
		s3.addVoisin(s1);
		s2.addVoisin(s1);
		s1.addVoisin(s2);
		
		
		g.addSommet(s1); //Ajout des sommets dans le graphe
		g.addSommet(s2);
		g.addSommet(s3);
		g.addSommet(s4);
		
		Arete a1 = new Arete(s1,s4,2);
		Arete a2 = new Arete(s3,s4,5);
		Arete a3 = new Arete(s2,s3,1);
		Arete a4 = new Arete(s1,s3,7);
		Arete a5 = new Arete(s2,s1);
		g.addArete(a1);
		g.addArete(a2);
		g.addArete(a3);
		g.addArete(a4);
		g.addArete(a5);
		System.out.println(g);
		
		System.out.println("Parcours en largeur de G: \n");
		TreeMap<Integer, Sommet> g_parcours_largeur = g.parcours_largeur(s1); //Test (explicite) d'un parcours en largeur
		for (Sommet s: g_parcours_largeur.values()) {
			System.out.println(s);
		}
		System.out.println("\n");
		
		g.delSommet(s3); //Suppresion d'un sommet dans le graphe
		System.out.println("Graphe G = (V,A) après suppression de s3: \n");
		System.out.println(g);
		
		g.delArete(a1); //Suppression d'une arête dans le graphe
		System.out.println("Graphe G = (V,A) après suppression de 1-4: \n");
		System.out.println(g);
		
		g.delArete(a4); //Lève une exception car à ce stade, cette arête n'existe plus dans G
	
	}

}
