package backend;

public abstract class Ki {

	
	Ki(FarbEnum farbe){
		
	}
	public abstract Spielfeld[] kiAct(Spielfeld[][] brett);

	public abstract void move(Spielfeld start, Spielfeld ziel);
}
