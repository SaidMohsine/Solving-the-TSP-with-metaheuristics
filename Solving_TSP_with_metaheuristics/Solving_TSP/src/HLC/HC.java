package HLC;

import java.util.Random;

import RecuitSimule.Utility;
import TSP.Chemin;
import TSP.Ville;

public class HC {
	Chemin best;
	int compt = 0;
	public HC(Chemin chemin) {
		//System.out.println(chemin.printChemin());
		best = new Chemin(chemin);
		System.out.println(best.printChemin());
		while(compt<10000) {
			Chemin s = new Chemin(best);
			int index1 = Utility.randomInt(0, s.villes.size());
			int index2 = Utility.randomInt(0, s.villes.size());
			Ville v1 = s.villes.get(index1);
			Ville v2 = s.villes.get(index2);
			s.villes.set(index1, v2);
			s.villes.set(index2, v1);
			if(s.getDistance_total()<best.getDistance_total()) {
				best = new Chemin(s);
			}
			compt++;
		}
		System.out.println(best.printChemin()+"  -->  " +best.getDistance_total());
		
	}

}
