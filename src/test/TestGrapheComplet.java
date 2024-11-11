package test;
import graphe.*;
import java.util.*;

public class TestGrapheComplet {

	public static void main(String[] args) {
		int[][] tab_couts =
				{{1,2,0},
				 {4,7,1},
				 {2,0,3}
				};
		
		//Création du graphe complet K3,3
		GrapheBipartiComplet gbc = new GrapheBipartiComplet(3, tab_couts);
		System.out.println(gbc);
	}
}
