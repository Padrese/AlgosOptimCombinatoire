package test;

import graphe.*;

public class TestEdmondsKarp {
	
	public static void main(String[] args) {
		
		//Définition du réseau
		Graphe g = new Reseau();
		
		//Définition des sommets
		Sommet s = new Sommet("s");
		Sommet s1 = new Sommet("s1");
		Sommet s2 = new Sommet("s2");
		Sommet s3 = new Sommet("s3");
		Sommet t1 = new Sommet("t1");
		Sommet t2 = new Sommet("t2");
		Sommet t3 = new Sommet("t3");
		Sommet t = new Sommet("t");
		
		g.addSommet(s);
		g.addSommet(s1);
		g.addSommet(s2);
		g.addSommet(s3);
		g.addSommet(t1);
		g.addSommet(t2);
		g.addSommet(t3);
		g.addSommet(t);
		
		//Définition des arêtes
		Arete ss1 = new Arete(s,s1,0,1);
		Arete ss2 = new Arete(s,s2,0,2);
		Arete ss3 = new Arete(s,s3,0,1);
		Arete s1t1 = new Arete(s1,t1,0,2);
		Arete s1s2 = new Arete(s1,s2,0,2);
		Arete s2t2 = new Arete(s2,t2,0,1);
		Arete s3s2 = new Arete(s3,s2,0,2);
		Arete s3t3 = new Arete(s3,t3,0,2);
		Arete t2t1 = new Arete(t2,t1,0,2);
		Arete t2t3 = new Arete(t2,t3,0,2);
		Arete t1t = new Arete(t1,t,0,1);
		Arete t2t = new Arete(t2,t,0,2);
		Arete t3t = new Arete(t3,t,0,1);
		
		g.addArete(ss1);
		g.addArete(ss2);
		g.addArete(ss3);
		g.addArete(s1t1);
		g.addArete(s1s2);
		g.addArete(s2t2);
		g.addArete(s3s2);
		g.addArete(s3t3);
		g.addArete(t2t1);
		g.addArete(t2t3);
		g.addArete(t1t);
		g.addArete(t2t);
		g.addArete(t3t);
		
		System.out.println(g.toStringFromSource(s));
		
	}

}
