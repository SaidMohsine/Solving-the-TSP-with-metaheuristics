package PSO;

import java.util.ArrayList;

public class Position {
	public ArrayList<Double> Positions;
	public Position(ArrayList<Double> Positions) {
		this.Positions = new ArrayList<>();
		this.Positions = Positions;
	}
	public ArrayList<Double> getPositions() {
        return this.Positions;
    }

    public void updatePositions(ArrayList<Double> update)
    {
        this.Positions.addAll(update);
    }
}
