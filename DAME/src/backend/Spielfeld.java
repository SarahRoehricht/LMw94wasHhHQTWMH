package backend;

/**
 * 
 * @author A2
 *
 */
public class Spielfeld {

	private int posX, posY;
	private Spielfigur spielfigur;
	private FarbEnum farbe;

	// public Spielfeld(int i, int j) {
	// this.setPosX(i);
	// this.setPosY(j);
	// }

	/**
	 * 
	 * @return String ID
	 */
	// public int[] getID() {
	// return ID;
	// }

	/**
	 * 
	 * @param iD
	 */
	// public void setID(int posX, int posY) {
	// ID[0] = posX;
	// ID[1] = posY;
	// }

	public Spielfeld(int i, int j, FarbEnum farbe) {
		this.setPosX(i);
		this.setPosY(j);
		this.setFarbe(farbe);
		// kommentar test
	}

	/**
	 * 
	 * @return Spielfigur spielfigur
	 */
	public Spielfigur getSpielfigur(){
		return spielfigur;
	}

	/**
	 * 
	 * @param spielfigur
	 */
	public void setSpielfigur(Spielfigur spielfigur) {
		this.spielfigur = spielfigur;
	}

	/**
	 * 
	 * @return posX
	 */
	public int getPosX() {
		return posX;
	}

	/**
	 * 
	 * @param posX
	 */
	public void setPosX(int posX) {
		this.posX = posX;
	}

	/**
	 * 
	 * @return posY
	 */
	public int getPosY() {
		return posY;
	}

	/**
	 * 
	 * @param posY
	 */
	public void setPosY(int posY) {
		this.posY = posY;
	}

	/**
	 * 
	 * @return farbe
	 */
	public FarbEnum getFarbe() {
		return farbe;
	}

	/**
	 * 
	 * @param farbe
	 */
	public void setFarbe(FarbEnum farbe) {
		
		this.farbe = farbe;
		if(this.posY<5 && farbe==FarbEnum.schwarz){
			this.spielfigur= new Spielfigur(FarbEnum.weiß, this); 
			
		}
		if(this.posY>6 && farbe==FarbEnum.schwarz){
			this.spielfigur= new Spielfigur(FarbEnum.schwarz, this); 
			
		}
	}
/**gibt Position in Schach Notation aus, mit der Information der Spielfigur die sich darauf befindet
 * Override toString
 */
	@Override
	public String toString() {
		int c = this.getPosY() + 1;
		char d = 97;
		d += getPosX();
		if(this.spielfigur!=null){
		return "" + d + c +spielfigur+"\t";							
		//	return spielfigur+"\t"; //Meinung? das hier vielleicht besser?
	}else{
		return "" + d + c+"\t";
	}
	}
}
