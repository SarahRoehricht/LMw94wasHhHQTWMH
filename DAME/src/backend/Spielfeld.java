package backend;
/**
 * 
 * @author A2
 *
 */
public class Spielfeld {

	private String ID;
	private Spielfigur spielfigur;

	public Spielfeld(String id) {
		this.setID(id);
	}
public Spielfeld(int i, int j) {
		// TODO Auto-generated constructor stub
	String c="";
	c+=i;
	c+=j;
	this.setID(c);
	}
/**
 * 
 * @return String ID
 */
	public String getID() {
		return ID;
	}
	/**
	 * 
	 * @param iD
	 */
	public void setID(String iD) {
		ID = iD;
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

}
