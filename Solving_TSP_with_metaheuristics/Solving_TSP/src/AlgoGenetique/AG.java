package AlgoGenetique;

import java.util.ArrayList;
import java.util.stream.IntStream;

import TSP.Ville;

public class AG {
	public double mutation_rate = 0.25;
	public int tournament_selection_taille = 3;
	public int nbr_elite_chemin = 1;
	public int numbre_generation = 30;
	public int Population_size = 8;
	public ArrayList<Ville> intVilles = null;
	
	public AG(ArrayList<Ville> villes) {
		this.intVilles = villes;
	}
	
	public ArrayList<Ville> getIntVilles(){
		return this.intVilles;
	}
	
	public Population evolve(Population population) {
		return mutation_Pop(crossover_Pop(population));
	}
	
	Population crossover_Pop(Population population) {
		Population crossoverPopulation = new Population(population.getChemins().size(), this);
		IntStream.range(0, nbr_elite_chemin).forEach(x->crossoverPopulation.getChemins().set(x, population.getChemins().get(x)));
		IntStream.range(nbr_elite_chemin, crossoverPopulation.getChemins().size()).forEach(x->{
			Chemin_Ag chemin1 = select_TournamentPopulation(population).getChemins().get(0);
			Chemin_Ag chemin2 = select_TournamentPopulation(population).getChemins().get(0);
			crossoverPopulation.getChemins().set(x,crossoverChemin(chemin1, chemin2));
		});
		
		return crossoverPopulation; 
	}
	
	Population mutation_Pop(Population population) {
		population.getChemins().stream().filter(x->population.getChemins().indexOf(x)>= nbr_elite_chemin).forEach(x->mutate_chemin(x));
		return population; 
	}
	
	Chemin_Ag crossoverChemin(Chemin_Ag chemin1, Chemin_Ag chemin2) {
		Chemin_Ag crossoverChemin = new Chemin_Ag(this);
		Chemin_Ag tmpChemin1 = chemin1;
		Chemin_Ag tmpChemin2 = chemin2;
		if(Math.random()<0.5) {
			tmpChemin1 = chemin2;
			tmpChemin2 = chemin1;
		}
		for(int x=0;x<crossoverChemin.getVilles().size()/2;x++)
			crossoverChemin.getVilles().set(x, tmpChemin1.getVilles().get(x));
		return filnullInCrossover(crossoverChemin, tmpChemin2);
	}
	private Chemin_Ag filnullInCrossover(Chemin_Ag crossoverChemin,Chemin_Ag chemin_Ag){
	{
		chemin_Ag.villes.stream().filter(x->!crossoverChemin.villes.contains(x)).forEach(ville1->{
			for(int i=0;i<chemin_Ag.villes.size();i++) {
				if(crossoverChemin.villes.get(i)==null) {
					crossoverChemin.villes.set(i, ville1);
					break;
				}
			}
		});
		return crossoverChemin;
	}
		
	}
	Population select_TournamentPopulation(Population population) {
		Population tournamentPopulation = new Population(tournament_selection_taille, this);
		IntStream.range(0, tournament_selection_taille).forEach(x->tournamentPopulation.getChemins().set(x,
				population.getChemins().get((int)Math.random()*population.getChemins().size())));
		tournamentPopulation.sort_Chemins();
		return tournamentPopulation;
	}
	Chemin_Ag mutate_chemin(Chemin_Ag chemin) {
		chemin.getVilles().stream().filter(x->Math.random()<mutation_rate).forEach(ville1->{
			int y=(int)(chemin.getVilles().size()*Math.random());
			Ville ville2 = chemin.getVilles().get(y);
			chemin.getVilles().set(chemin.getVilles().indexOf(ville1), ville2);
			chemin.getVilles().set(y, ville1);
		});
		return chemin;
	}
}
