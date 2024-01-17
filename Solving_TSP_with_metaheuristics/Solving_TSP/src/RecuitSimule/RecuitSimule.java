package RecuitSimule;

import TSP.Chemin;
import TSP.Ville;

public class RecuitSimule {
	//Set initial temperature
    double temp = 0.1;

    //Cooling rate
    //taux de refroidissement
    double coolingRate = 0.9999;
    Chemin best_chemin;
	
	public RecuitSimule(Chemin cheminInit) {        
        System.out.println("Total distance of initial solution: " + cheminInit.getDistance_total());
        System.out.println("Tour: " + cheminInit.printChemin());


        best_chemin = new Chemin(cheminInit);
        // Loop until system has cooled
        while (temp > 0.0001) {
            for(int j=0;j<15;j++) {
            Chemin newChemin = new Chemin(cheminInit);
            // Get random positions in the tour
            
            int ville1 = Utility.randomInt(0 , newChemin.villes.size());
            int ville2 = Utility.randomInt(0 , newChemin.villes.size());
            
            //to make sure that tourPos1 and tourPos2 are different
    		while(ville1 == ville2) {ville2 = Utility.randomInt(0 , newChemin.villes.size());}
            // Get the cities at selected positions in the tour
            
            Ville VilleSwap_1 = newChemin.villes.get(ville1);
            Ville VilleSwap_2 = newChemin.villes.get(ville2);
            
            // Swap them
            
            newChemin.villes.set(ville1, VilleSwap_2);
            newChemin.villes.set(ville2, VilleSwap_1);
            // Get energy of solutions
            
            
            double currentDistance_   = cheminInit.getDistance_total();
            double neighbourDistance_ = newChemin.getDistance_total();
            
            // Decide if we should accept the neighbour
            double rand = Utility.randomDouble();
            if (Utility.ProbaAccept((int)currentDistance_, (int)neighbourDistance_, temp) > rand) {
              //  currentSolution = new Tour(newSolution.getTour());
                cheminInit = new Chemin(newChemin);
            }

            // Keep track of the best solution found
            if (cheminInit.getDistance_total() < best_chemin.getDistance_total()) {
                best_chemin = new Chemin(cheminInit);
                System.out.println("OK");
            }
            if((int)best_chemin.getDistance_total()==7542) {
            	System.out.println("Yes");
            	System.exit(0);
            }
            }
            
            System.out.println(best_chemin.getDistance_total());
            // Cool system
            temp = temp*coolingRate;
        }

        System.out.println("Final solution distance: " + best_chemin.getDistance_total());
        System.out.println("Tour: " + best_chemin.printChemin());
		
	}
	public Chemin getBestSolution() {
		return best_chemin;
	}
}
