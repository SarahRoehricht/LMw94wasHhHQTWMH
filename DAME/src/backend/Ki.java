package backend;

public abstract class Ki {
private FarbEnum farbe;
	
	Ki(FarbEnum farbe){
		this.setFarbe(farbe);
	}
	public abstract void setFarbe(FarbEnum farbe);
	public abstract Spielfeld[] kiAct(Spielfeld[][] brett);

	public abstract Spielfeld actAgain(Spielfeld startfeld, Spielfeld[][] brett);

	}

