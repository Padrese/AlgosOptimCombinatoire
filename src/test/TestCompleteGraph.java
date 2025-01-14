package test;
import java.util.*;

import graph.*;

public class TestCompleteGraph {

	public static void main(String[] args) {
		int[][] cost_table =
				{{1,2,0},
				 {4,7,1},
				 {2,0,3}
				};
		
		//Creating complete graph K3,3
		CompleteBipartiteGraph cbg = new CompleteBipartiteGraph(3, cost_table);
		System.out.println(cbg);
	}
}
