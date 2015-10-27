package backend;

/**
 * 
 * @author A2
 *
 */

public class Spielfigur {
	private ZustandEnum zustand;
	private FarbEnum farbe;
	private Spielfeld position;
	private boolean dame = false;

	/**
	 * 
	 * @param sstein 
	 * @param FarbeEnum farbe
	 *          
	 * @param Spielfeld position
	 *          
	 */
	public Spielfigur(FarbEnum farbe, Spielfeld position, ZustandEnum Stein) {

		this.setFarbe(farbe);
		this.setPosition(position);
		this.setZustand(Stein);

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
	public ZustandEnum getZustand() {
		return zustand;
	}

	public void setZustand(ZustandEnum zustand) {
		this.zustand = zustand;
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
			return "|_|";
		}
	}
}