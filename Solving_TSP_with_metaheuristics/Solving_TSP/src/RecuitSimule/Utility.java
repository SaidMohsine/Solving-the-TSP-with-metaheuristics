package RecuitSimule;



import java.util.Random;

import TSP.Ville;

public class Utility {


	/**
	 * Calcule et renvoie la distance euclidienne entre deux villes
	 * @param ville1 : la première ville
	 * @param ville2 : la deuxième ville
	 * @return distance la distance entre ville1 et ville2
	 */
	public static double distance(Ville ville1, Ville ville2){
		
		double distance = ville1.distance2villes(ville2);

		return distance;
	}
		
	/**
	Calcule la probabilité d'acceptation
	* @param currentDistance la distance totale du tour actuel
	* @param newDistance la distance totale du nouveau tour
	* @param temperature la température actuelle
	* @return value la probabilité d'accepter ou non le nouveau tour
	 */
	public static double ProbaAccept(int distanceActuelle, int nouvelleDistance, double temperature) {
		// Si la nouvelle solution est meilleure, acceptez-la
		if (nouvelleDistance < distanceActuelle) {
			return 1.0;
		}
		// Si la nouvelle solution est pire, calcule une probabilité d'acceptation
		return Math.exp((distanceActuelle - nouvelleDistance) / temperature);
	}

	/**
	* cette méthode renvoie un nombre aléatoire n tel que
	* 0,0 <= n <= 1,0
	* @return random tel que 0.0 <= random <= 1.0
	*/
	static double randomDouble()
	{
		Random r = new Random();
		return r.nextInt(1000) / 1000.0;
	}
	
	/**
	* renvoie une valeur int aléatoire dans une plage donnée
	* min inclus .. max non inclus
	* @param min la valeur minimale de la plage requise (int)
	* @param max la valeur maximale de la plage requise (int)
	* @return rand une valeur int aléatoire entre min et max [min, max)
	*/
	public static int randomInt(int min , int max) {
		Random r = new Random();
		double d = min + r.nextDouble() * (max - min);
		return (int)d;
	}
}
