package AlgoGenetique;

import java.util.ArrayList;
import java.util.stream.IntStream;

import TSP.Ville;

public class Population {
	public ArrayList<Chemin_Ag> chemins = new ArrayList<Chemin_Ag>();
	
	public Population(int taille_pop, ArrayList<Ville> villes) {
		IntStream.range(0, taille_pop).forEach(x->chemins.add(new Chemin_Ag(villes)));
	}
	
	public Population(int taille_pop,AG ag) {
		IntStream.range(0, taille_pop).forEach(x->chemins.add(new Chemin_Ag(ag.getIntVilles())));
	}
	
	public ArrayList<Chemin_Ag> getChemins(){
		return chemins;
	}
	
	public void sort_Chemins() {
		chemins.sort((chemin1,chemin2)->{
			int flag=0;
			if(chemin1.getFitness()>chemin2.getFitness()) flag=-1;
			else if (chemin1.getFitness()<chemin2.getFitness()) flag=1;
			return flag;
		});
	}
}
