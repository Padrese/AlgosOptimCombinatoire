package test;

import graphe.*;

public class TestGrapheBiparti {
	
	public static void main(String[] args) {
		
		GrapheBiparti gb = new GrapheBiparti();
		
		//Sommets de u
		Sommet u1 = new Sommet("u1");
		Sommet u2 = new Sommet("u2");
		Sommet u3 = new Sommet("u3");
		Sommet u4 = new Sommet("u4");
		
		//Sommets de v
		Sommet v1 = new Sommet("v1");
		Sommet v2 = new Sommet("v2");
		Sommet v3 = new Sommet("v3");
		Sommet v4 = new Sommet("v4");
		
		//Arêtes du graphe biparti
		Arete a12 = new Arete(u1, v2, 2);
		Arete a21 = new Arete(u2, v1, 1);
		Arete a34 = new Arete(u3, v4, 1);
		Arete a43 = new Arete(u4, v3, 2);
		Arete a41 = new Arete(u4, v1, 4);
		
		//Ajout des éléments
		gb.addU(u1);
		gb.addU(u2);
		gb.addU(u3);
		gb.addU(u4);
		
		gb.addV(v1);
		gb.addV(v2);
		gb.addV(v3);
		gb.addV(v4);
		
		gb.addArete(a12);
		gb.addArete(a21);
		gb.addArete(a34);
		gb.addArete(a43);
		gb.addArete(a41);
		
		System.out.println(gb);
	}

}
