package backend;

public abstract class Ki {

	
	Ki(FarbEnum farbe){
		this.setFarbe(farbe);
	}
	public abstract void setFarbe(FarbEnum farbe);
	public abstract Spielfeld[] kiAct(Spielfeld[][] brett);

	public abstract void move(Spielfeld start, Spielfeld ziel);
}
