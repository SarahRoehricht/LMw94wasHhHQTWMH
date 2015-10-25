package backend;

/**
 * 
 * @author A2
 *
 */

public class Spielfigur {

	private FarbEnum farbe;
	private Spielfeld position;
	private boolean dame = false;

	/**
	 * 
	 * @param FarbeEnum farbe
	 *          
	 * @param Spielfeld position
	 *          
	 */
	public Spielfigur(FarbEnum farbe, Spielfeld position) {

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
	 * @param Farbenum
	 *          farbe
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
	 * @param Spielfeld
	 *          position
	 */
	public void setPosition(Spielfeld position) {
		this.position = position;
	}

	public boolean isDame() {
		return dame;
	}

	public void setDame(boolean dame) {
		this.dame = dame;
	}
	/** gibt |O|, |x| v "farblos zurueck bei Weiss, schwarz oder bei falschem aufruf
	 * @return |O|, |x| v "farblos
	 */
	@Override
	public String toString() {
		if(farbe== FarbEnum.weiß){
			return "|O|";
		}
		else if(farbe==FarbEnum.schwarz){
			return "|X|";	
		}
		else{
			return "farblos";
		}
	}
}