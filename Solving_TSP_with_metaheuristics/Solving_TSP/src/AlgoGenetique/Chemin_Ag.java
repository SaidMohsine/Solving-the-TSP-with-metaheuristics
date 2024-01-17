package AlgoGenetique;

import java.util.ArrayList;
import TSP.Chemin;
import TSP.Ville;

public class Chemin_Ag extends Chemin{
	private boolean Fitness_change = true;
	private double Fitness = 0;

	public Chemin_Ag(ArrayList<Ville> villes) {
		super(villes);
		// TODO Auto-generated constructor stub
	}
	
	public Chemin_Ag(AG ag) {
		ag.getIntVilles().forEach(x->villes.add(null));
	}
	
	public ArrayList<Ville> getVilles(){
		Fitness_change=true;
		return super.villes;
	}
	public double getFitness() {
		if(Fitness_change==true) {
			Fitness = (1/super.getDistance_total())*10000;
			Fitness_change=false;
		}
		return Fitness;
	}

}
