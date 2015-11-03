package backend;

import java.io.Serializable;

public abstract class Ki implements Serializable{
private FarbEnum farbe;
	
	Ki(FarbEnum farbe){
		this.setFarbe(farbe);
	}
	public abstract void setFarbe(FarbEnum farbe);
	public abstract Spielfeld[] kiAct(Spielfeld[][] brett);

	public abstract Spielfeld actAgain(Spielfeld startfeld, Spielfeld[][] brett);

	}

