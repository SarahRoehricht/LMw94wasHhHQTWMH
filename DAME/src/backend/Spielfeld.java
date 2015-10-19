package backend;

/**
 * 
 * @author A2
 *
 */
public class Spielfeld {

	private int posX, posY;
	private Spielfigur spielfigur;


	public Spielfeld(int i, int j) {
		this.setPosX(i);
		this.setPosY(j);
	}

	/**
	 * 
	 * @return String ID
	 */
//	public int[] getID() {
//		return ID;
//	}

	/**
	 * 
	 * @param iD
	 */
//	public void setID(int posX, int posY) {
//		ID[0] = posX;
//		ID[1] = posY;
//	}

	/**
	 * 
	 * @return Spielfigur spielfigur
	 */
	public Spielfigur getSpielfigur() {
		return spielfigur;
	}

	/**
	 * 
	 * @param spielfigur
	 */
	public void setSpielfigur(Spielfigur spielfigur) {
		this.spielfigur = spielfigur;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
@Override
public String toString() {
	int c=this.getPosY()+1;
	char d=97;
	d+=getPosX();
	
	return ""+  d +c;
}
}
