package backend;

public class Spielfigur {
	
	private FarbEnum farbe;
	private Spielfeld position;
	
	/**
	 * 
	 * @param FarbeEnum farbe 
	 * @param Spielfeld position 
	 */
	public Spielfigur(FarbEnum farbe,Spielfeld position){
		
		this.setFarbe(farbe);
		this.setPosition(position);
		
	}
	/**
	 * 
	 * @return Farbenum
	 */
	public FarbEnum getFarbe() {
		return farbe;
	}

	/**
	 * 
	 * @param Farbenum farbe
	 */
	public void setFarbe(FarbEnum farbe) {
		this.farbe = farbe;
	}

	public Spielfeld getPosition() {
		return position;
	}

	public void setPosition(Spielfeld position) {
		this.position = position;
	}
}

	
