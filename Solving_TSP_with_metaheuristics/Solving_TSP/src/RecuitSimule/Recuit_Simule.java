package RecuitSimule;

import java.util.Random;

import TSP.Chemin;
import TSP.Ville;

public class Recuit_Simule {
	Chemin Best_chemin;// = new Chemin();
	double t_max = 100;
	double Alpha = 0.999922;
	double t_min = 0.001;
	int n = 20;
	int size=0;
	Random random = new Random();
	public Recuit_Simule(Chemin chemin) {
		this.size= chemin.villes.size();
		this.Best_chemin = new Chemin(chemin);
	}
	public Chemin Start() {
		Chemin v;
		System.out.println("ch init = "+Best_chemin.printChemin()+" d= "+Best_chemin.getDistance_total());
		double t = t_max;
		while (t>t_min) {
			for(int i=0;i<n;i++) {
				v = new Chemin(Best_chemin);
				
				int index1 = random.nextInt(size);
				int index2 = random.nextInt(size);
				while(index1==index2)
					{
					index2 = random.nextInt(size);
					//System.out.println("ok");
					}
				Ville v1 = v.villes.get(index1);
				Ville v2 = v.villes.get(index2);
				v.villes.set(index1, v2);
				v.villes.set(index2, v1);
				double delta = v.getDistance_total() - Best_chemin.getDistance_total();
				if(delta>0) {
					double rand = Math.random();
					//System.out.println(rand);
					if(rand<Math.exp((-delta/t))){
						Best_chemin = new Chemin(v);
						//System.out.println("ok");
					}
				}else {
					Best_chemin = new Chemin(v);
				}
				//System.out.println((int)Best_chemin.getDistance_total());
				
				if((int)Best_chemin.getDistance_total()==426) {
					System.out.println("ch fin = "+Best_chemin.printChemin()+" d= "+Best_chemin.getDistance_total());
					
					return Best_chemin;
				}
			}
			t = t*Alpha;
		}
		System.out.println("ch fin = "+Best_chemin.printChemin()+" d= "+Best_chemin.getDistance_total());
		return Best_chemin;
	}

}
