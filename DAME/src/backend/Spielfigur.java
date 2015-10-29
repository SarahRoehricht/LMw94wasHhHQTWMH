package backend;

/**
 * 
 * @author A2
 *
 */

public class Spielfigur {
	
	private FarbEnum farbe;
//	private Spielfeld position; //noch noetig?
	private boolean dame = false;

	/**
	 * 
	 * @param sstein 
	 * @param FarbeEnum farbe
	 *          
	 * @param Spielfeld position
	 *          
	 */
	public Spielfigur(FarbEnum farbe, boolean b) {

		this.setFarbe(farbe);
		//this.setPosition(position); noch noetig?
		this.setDame(b);

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
//	public Spielfeld getPosition() {
//		return position; noch noetig?
//	}

	/**
	 * 
	 * @param Spielfeld
	 *          position
	 */
//	public void setPosition(Spielfeld position) {
//		this.position = position; noch noetig?
//	}

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
			if(this.dame==false){
			return "|O|";
			}
			if(this.dame==true){
				return "|LOL|";
			}
		}
		if(farbe==FarbEnum.schwarz){
			if(this.dame==false){
				
			
			return "|X|";	
		}if(this.dame==true){
		return "|OXO|";	
		}
		}
			return "|_DASD|";
		
	}}
