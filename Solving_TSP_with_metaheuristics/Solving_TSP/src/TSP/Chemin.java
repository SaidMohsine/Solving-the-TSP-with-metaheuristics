package TSP;

import java.util.ArrayList;
import java.util.Collections;


public class Chemin {
	public ArrayList<Ville> villes = new ArrayList<Ville>();
	
    public Chemin(ArrayList<Ville> villes)
    {
    	Collections.shuffle(villes);
        this.villes.addAll(villes);
        
    }

    public Chemin(Chemin chemin){
    	villes = new ArrayList<>();
    	villes.addAll(chemin.villes);
    }
    public Chemin() {
    	
    }

    //Cette méthode retourne la distance totale d'un chemin entre ville1,ville2,...,ville1
    public double getDistance_total()
    {
        double Distance_total = 0;
        for(int i=0;i<villes.size()-1;i++) {
        	Distance_total += (int)villes.get(i).distance2villes(villes.get(i+1));
        }
        Distance_total+=villes.get(villes.size()-1).distance2villes(villes.get(0));
        return Distance_total;
    }

    public String printChemin()
    {
        String str = "";
        str+="{ ";
        for (Ville ville:villes) {
            str+=ville.nom+" ";
        }
        str+=" }";
        return str;
    }
    public void setVille(ArrayList<Ville> villes){
    	this.villes = new ArrayList<>();
    	this.villes.addAll(villes);
    	
    }

}
