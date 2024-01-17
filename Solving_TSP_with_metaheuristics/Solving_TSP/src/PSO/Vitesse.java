package PSO;

import java.util.ArrayList;

public class Vitesse {
	public ArrayList<Double> vitesses;

    public Vitesse(ArrayList<Double> vitesses)
    {
        this.vitesses = new ArrayList<Double>();
        this.vitesses = vitesses;
    }
    public ArrayList<Double> getVitesses() {
        return this.vitesses;
    }

    public void updateVitesses(ArrayList<Double> vitesses)
    {
        this.vitesses.addAll(vitesses);
    }
}
