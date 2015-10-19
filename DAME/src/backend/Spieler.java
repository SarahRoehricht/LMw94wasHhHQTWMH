package backend;
/**
 * 
 * @author A-2
 * @Version 0.0.1
 *
 */
public class Spieler {
	private String name; // Der Name des Spieler
	private Ki ki;			// Das Objekt KI, welches definiert werden muss.
	private FarbEnum farbe; //Farbe(schwarz, weiss aus FarbEnum
/**
 * 
 */
	public Spieler() {//default Konstruktor name leer.
		this.setName("");
	}
/**
 * 
 * @param name
 */
	public Spieler(String name) {//setzt Namen
		this.setName(name);
	}
/**
 * 
 * @param name
 * @param farbe
 */
	public Spieler(String name, FarbEnum farbe) {      //ruft Konstruktor name auf, setzt farbe
		this(name);
		this.setFarbe(farbe);
	}
	/**
	 * 
	 * @param name
	 * @param farbe
	 * @param ki
	 */
	public Spieler(String name, FarbEnum farbe, Ki ki) {//ruft Konstruktor name, farbe auf, setzt ki Objekt(klug so?) nochmal im Kopf durchgehen..
		this(name, farbe);
		this.ki= new Ki_Dame();
	}

	
	
	/**
	 * 
	 * @return String name
	 */
	public String getName() {
		return name;
	}
	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 
	 * @return FarbEnum farbe
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

	/**
	 * String Override
	 * @return String name
	 */
	@Override
	public String toString() {
		
		return this.name + "\n" + this.getFarbe(); //Name Des Spielers
	}
	
	/**
	 * 
	 * @param args
	 */
public static void main(String[] args) {
	Spieler s1= new Spieler("Eberhard");
	System.out.println(s1);
}}