package PSO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import RecuitSimule.RecuitSimule;
import TSP.Chemin;
import TSP.Ville;
import TwoOpt.TwoOpt;

public class PSO_Algo {
	public static int max_particules = 160;
    public static int max_Iterations = 80;
    public static double c1 = 1.3;//influence personnelle
    public static double c2 = 1.9;//influence sociale
    //coefficients pour calculer inertie
    public static double W_min = 0.0;
    public static double W_max = 1.0;
    public static Random random = new Random();
    
    public ArrayList<particule> Liste_particule = new ArrayList<particule>();
    public Chemin gBest = null;
    public Position gBestPosition;
    public int min_distance;

    public PSO_Algo(Chemin chemin)
    {
        System.out.println("========================= Initialisation =======================");
        System.out.println();
        
    	//RecuitSimule rc = new RecuitSimule(chemin);
    	//Chemin ch = rc.getBestSolution();
        for(int i = 0 ; i < max_particules ; i++)
        {
            Collections.shuffle(chemin.villes);
        	//TwoOpt tw = new TwoOpt(new Chemin(chemin.villes));
        	//Chemin ch = tw.TwoOpt_Start();
            this.Liste_particule.add(new particule(new Chemin(chemin)));
        }
        gBestPosition = new Position(new ArrayList<Double>());
        initialisation_vitesses();
        initialisation_Positions();
        sort();
        gBest = new Chemin(this.Liste_particule.get(0).pBest);
    }

    public ArrayList<Ville> Plus_court_chemin()
    {
        System.out.println("========================= Début =======================");
        //System.out.println();
        double w = 0;
        int compteur = 0 ;
        while (compteur < max_Iterations)
        {
            for(int i = 0 ; i < this.Liste_particule.size()-1 ; i ++)
            {
                this.Liste_particule.get(i).getVBest();
            }
            //printParticules();
            sort();
            if(this.Liste_particule.get(0).pBest.getDistance_total() < this.gBest.getDistance_total())
            {
                gBest = new Chemin(this.Liste_particule.get(0).pBest);
            }
            //System.out.println("gBest = "+(int)gBest.getDistance_total()+" km");
            w = W_max - (((double) compteur) / max_Iterations) * (W_max - W_min);
            //System.out.println("... Update les vitesses ...");
            for(int i = 0 ; i < this.Liste_particule.size() - 1 ; i++)
            {
                for(int j = 0 ; j< particule.taille_probleme-1 ; j++)
                {
                    // 2 réelle aléatoire :
                    double r1 = random.nextDouble();
                    double r2 = random.nextDouble();
                    // vitesse du particle actuel :
                    double vel = this.Liste_particule.get(i).vitesse.getVitesses().get(j);
                    // Position :
                    double loc = this.Liste_particule.get(i).position.getPositions().get(j);
                    // position de pBest :
                    double pBestLoc = this.Liste_particule.get(i).meilleur_position.getPositions().get(j);
                    // position de gBest :
                    double gBestLoc = this.gBestPosition.getPositions().get(j);
                    // nouvelle valeur de la vetesse :
                    double newVel = (w * vel) + (r1 * c1) * (pBestLoc - loc) + (r2 * c2) * (gBestLoc - loc);
                    // mise a jour de vetesse :
                    this.Liste_particule.get(i).vitesse.getVitesses().set(j,newVel);
                    // nouvelle position :
                    double newPos = loc + newVel;
                    this.Liste_particule.get(i).position.Positions.set(j,newPos);
                    this.Liste_particule.get(i).ModifierPositions((int)Math.abs(loc-newPos));
                }

            }
            compteur++;

        }
        //System.out.println("========================= Fin =======================");
        //System.out.println();
        //TwoOpt tw = new TwoOpt(this.gBest);
        //Chemin ch = tw.TwoOpt_Start();
        //if(ch.getDistance_total()<this.gBest.getDistance_total()) {
        //	this.gBest = ch;
        //}
        //RecuitSimule rc = new RecuitSimule(this.gBest);
        //ch = rc.getBestSolution();
        //if(ch.getDistance_total()<this.gBest.getDistance_total()) {
        //	this.gBest = ch;
        //}
        System.out.println("Distance minimale : "+(int)this.gBest.getDistance_total());
        min_distance=(int)this.gBest.getDistance_total();
        //System.out.println("Chemin : "+this.gBest.printChemin());
        
        ArrayList<Ville> plus_court_chemin = new ArrayList<>();
        plus_court_chemin.addAll(gBest.villes);
        return plus_court_chemin;
    }
    public int Distance_min() {
    	return (int)this.gBest.getDistance_total();
    }


    public void sort()
    {
        int n = this.Liste_particule.size();
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (this.Liste_particule.get(j).pBest.getDistance_total() > this.Liste_particule.get(j+1).pBest.getDistance_total())
                {
                    particule temp = this.Liste_particule.get(j);
                    this.Liste_particule.set(j,this.Liste_particule.get(j+1));
                    this.Liste_particule.set(j+1,temp);
                }
    }

    public void initialisation_vitesses()
    {
        for(particule p : this.Liste_particule)
        {
            for(int i = 0 ; i < particule.taille_probleme - 1 ; i++)
            {
                p.vitesse.vitesses.add(random.nextDouble() * 2.0 - 1.0);
            }
        }
    }

    public void initialisation_Positions()
    {
        for(particule p : this.Liste_particule)
        {
            for(int i = 0 ; i < particule.taille_probleme - 1 ; i++)
            {
                p.position.Positions.add((double)i);
                p.meilleur_position.Positions.add((double)i);
            }
        }
        for (int i = 0 ; i < particule.taille_probleme - 1 ; i++)
        {
            gBestPosition.getPositions().add((double)i);
        }
    }

    public void printParticules()
    {
        for(int i = 0 ; i< this.Liste_particule.size()-1;i++)
        {
            System.out.println("particule : "+i);
            for(int j = 0 ; j < particule.taille_probleme-1 ; j++)
            {
                System.out.print("Position : "+this.Liste_particule.get(i).position.getPositions().get(j)+" , Vetesse : "
                + this.Liste_particule.get(i).vitesse.getVitesses().get(j));
                System.out.println();
            }
        }
    }


}
