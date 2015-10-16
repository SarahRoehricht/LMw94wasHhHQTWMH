package backend;

/**
 * 
 * @author A2
 *
 */


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
	 * @return Farbenum farbe
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
	
	/**
	 * 
	 * @return Spielfeld position
	 */
	public Spielfeld getPosition() {
		return position;
	}

	
	/**
	 * 
	 * @param Spielfeld position
	 */
	public void setPosition(Spielfeld position) {
		this.position = position;
	}
}