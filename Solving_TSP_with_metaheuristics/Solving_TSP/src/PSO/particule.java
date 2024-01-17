package PSO;

import java.util.ArrayList;

import TSP.Chemin;
import TSP.Ville;

public class particule {
	//Un chemin entre les villes
	public Chemin chemin;
	//Liste des positions d'un particule
	public Position position;
	//vitesse
	public Vitesse vitesse;
	//meilleur chemin
	public Chemin pBest;
	public Position meilleur_position;
	//taille de problème et condition d'aréte
	public static int taille_probleme;
	public static int max_iteration=100;
	
	//---------------initialisation particule----------------//
	
	public particule(Chemin ch) {
		this.chemin =ch;
		this.pBest = ch;
		position = new Position(new ArrayList<Double>());
		meilleur_position = new Position(new ArrayList<Double>());
		vitesse = new Vitesse(new ArrayList<Double>());
		taille_probleme = chemin.villes.size();
	}
	
	// meilleure solution de voisinage
    public void getVBest()
    {
        Chemin chemin_voisinage = null;
        int i = 0 ;
        while (i < max_iteration)
        {
            chemin_voisinage = getSolution_de_voisinage(new Chemin(this.pBest));
            if(chemin_voisinage.getDistance_total() < pBest.getDistance_total()){
                pBest = new Chemin(chemin_voisinage);
               
            }
            i++;
        }
    }

    // get neighborhood solutions
    public Chemin getSolution_de_voisinage(Chemin chemin_v)
    {
        int random1 = 0 ;
        int random2 = 0 ;
        while(random1==random2){
            random1 = (int) (chemin_v.villes.size()* Math.random());
            random2 = (int) (chemin_v.villes.size()* Math.random());
        }
        Ville ville_1 = chemin_v.villes.get(random1);
        Ville ville_2 = chemin_v.villes.get(random2);
        chemin_v.villes.set(random2,ville_1);
        chemin_v.villes.set(random1,ville_2);
        return chemin_v;
    }

    public void ModifierPositions(int coeff)
    {
        for(int i = 0 ; i < coeff ; i++)
        {
            int random1 = 0 ;
            int random2 = 0 ;

            while(random1==random2){
                random1 = (int) (pBest.villes.size()* Math.random());
                random2 = (int) (pBest.villes.size()* Math.random());
            }
            Ville ville_1 = pBest.villes.get(random1);
            Ville ville_2 = pBest.villes.get(random2);
            pBest.villes.set(random2,ville_1);
            pBest.villes.set(random1,ville_2);
        }
    }
	
	
	
}
