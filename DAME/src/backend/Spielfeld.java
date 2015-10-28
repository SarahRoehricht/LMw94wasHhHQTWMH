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
		
		if(this.getPosY()<5 && this.getFarbe()==FarbEnum.schwarz){
			this.setSpielfigur(new Spielfigur(FarbEnum.weiß, false));
			
		}
		else if(this.getPosY()>6 && this.getFarbe()==FarbEnum.schwarz){
			this.setSpielfigur(new Spielfigur(FarbEnum.schwarz, false)); 
			
		}
		
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
		
	
	}
	
	/**Gibt String als Schachnotation zurueck
	 * 
	 * @return String d+c
	 */
	public String getSchachNotation(){
	int c = this.getPosY() + 1;
			char d = 97;
			d += getPosX();
		return "" + d + c+"";
		
		
		
	}
/**gibt Position in Schach Notation aus, mit der Information der Spielfigur die sich darauf befindet
 * Override toString
 */
	@Override
	public String toString() {
		//int c = this.getPosY() + 1;
		//char d = 97;
		//d += getPosX();
		//if(this.spielfigur!=null){
		//return "" + d + c +spielfigur+"\t";	
		if(this.spielfigur==null){
			return "|_| \t";
		}else{
		return spielfigur+"\t";			
		}//	return spielfigur+"\t"; //Meinung? das hier vielleicht besser?
	//}else{
		//return "" + d + c+"\t";
	//}
	}
}
