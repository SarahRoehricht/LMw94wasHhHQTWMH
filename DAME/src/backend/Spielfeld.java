package backend;

/**
 * 
 * @author A2
 *
 */
public class Spielfeld {

	private int[] ID = new int[2];
	private Spielfigur spielfigur;


	public Spielfeld(int i, int j) {
		this.setID(i, j);
	}

	/**
	 * 
	 * @return String ID
	 */
	public int[] getID() {
		return ID;
	}

	/**
	 * 
	 * @param iD
	 */
	public void setID(int posX, int posY) {
		ID[0] = posX;
		ID[1] = posY;
	}

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

	@Override
	public String toString() {
		return;

	}
}
