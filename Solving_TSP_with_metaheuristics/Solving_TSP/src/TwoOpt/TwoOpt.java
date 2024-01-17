package TwoOpt;

import TSP.Chemin;
import TSP.Ville;

public class TwoOpt {
	Chemin chemin;
	Chemin BstCh;
	boolean amelioration = true;
	static int cmp =0;
	public TwoOpt(Chemin chemin) {
		this.chemin = new Chemin(chemin.villes);
		BstCh = new Chemin(chemin);
		this.amelioration = true;
	}
	public Chemin TwoOpt_Start() {
		long start_Time_ = System.nanoTime();
		long end_Time_ = System.nanoTime();
		while (amelioration == true) {
			amelioration = false;
			for(int i=0;i<chemin.villes.size()-1;i++) {
				for(int j=0;j<chemin.villes.size()-1;j++) {
					if(j!=i && j!=i+1 &&j!=i-1) {
						Ville ville_1 = chemin.villes.get(i);
						Ville ville_2 = chemin.villes.get(i+1);
						Ville ville_3 = chemin.villes.get(j);
						Ville ville_4 = chemin.villes.get(j+1);
						if(ville_1.distance2villes(ville_2)+ville_3.distance2villes(ville_4)>ville_1.distance2villes(ville_3)+ville_2.distance2villes(ville_4)) {
							chemin.villes.set(i+1, ville_3);
							chemin.villes.set(j,ville_2);
							amelioration=true;
						}
						if(chemin.getDistance_total()<BstCh.getDistance_total()) {
							BstCh = new Chemin(chemin);
						}
						if((int)BstCh.getDistance_total()==426) {
							amelioration=false;
						}
					}
				}
			}
			
			//end_Time_ = System.nanoTime();
		}
		//System.out.println(chemin.printChemin());
		return BstCh;
	}

}
