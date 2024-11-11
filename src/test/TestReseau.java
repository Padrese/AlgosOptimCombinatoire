package test;

import graphe.*;

public class TestReseau {

	public static void main(String[] args) {
		
		Sommet s1 = new Sommet("1");
		Sommet s2 = new Sommet("2");
		Sommet s3 = new Sommet("3");
		Sommet s4 = new Sommet("4");
		Graphe g = new Reseau();
		
		g.addSommet(s1); //Ajout des sommets dans le graphe
		g.addSommet(s2);
		g.addSommet(s3);
		g.addSommet(s4);
		
		Arete a1 = new Arete(s1,s4,2,0,3);
		Arete a2 = new Arete(s3,s4,5,0,1);
		Arete a3 = new Arete(s2,s3,1,0,2);
		Arete a4 = new Arete(s1,s3,7,0,1);
		Arete a5 = new Arete(s1,s2,0,1);
		g.addArete(a1);
		g.addArete(a2);
		g.addArete(a3);
		g.addArete(a4);
		g.addArete(a5);
		System.out.println("Graphe G = (V,A): \n");
		System.out.println(g.toStringFromSource(s1));
		
		//On change la valeur du flot sur une arête
		g.setCstListeX(a1,2);
		
		g.delSommet(s3); //Suppresion d'un sommet dans le graphe
		System.out.println("Graphe G = (V,A) après suppression de s3: \n");
		System.out.println(g.toStringFromSource(s1));
		
		g.delArete(a1); //Suppression d'une arête dans le graphe
		System.out.println("Graphe G = (V,A) après suppression de 1-4: \n");
		System.out.println(g.toStringFromSource(s1));
		
		g.delArete(a4); //Lève une exception car à ce stade, cette arête n'existe plus dans G
	}
}
