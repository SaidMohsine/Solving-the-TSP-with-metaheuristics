package TSP;

import java.util.ArrayList;

public class Ville {
	
	ArrayList<Double> distances = new ArrayList<>();
	//nom de la ville
	public String nom;
	public double latitude;
	public double longitude;
	
	public Ville(ArrayList<Double> distances, String nom){
	    this.distances.addAll(distances);
	    this.nom=nom;
	}
	public Ville(String nom,double dLat, double dLng){
	    this.nom=nom;
	    this.latitude=dLat;
	    this.longitude=dLng;
	}
	
	 public double distance2villes(Ville ville)
	    {
	        double d = 0;
	        double x1 = this.latitude;
	        double y1 = this.longitude;
	        double x2 = ville.latitude;
	        double y2 = ville.longitude;
	        double d1 = (x1-x2)*(x1-x2);
	        double d2 = (y1-y2)*(y1-y2);
	        d= Math.sqrt(d1+d2);
	        return d;
	    }
	 public String toString() {
		 return nom;
	 }
}
