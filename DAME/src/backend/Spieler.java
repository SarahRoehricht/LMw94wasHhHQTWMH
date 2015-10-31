package backend;

import java.io.IOException;
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.util.Properties;

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
	private Spieler() {//default Konstruktor name leer.
		this.setName("");
	}
/**
 * 
 * @param name
 */
	public Spieler(String name) {//setzt Namen
		if((name == null || name.length() <= 2)){// überprüft auf korrekte Eingabe 
			throw new RuntimeException("Der Name ist zu kurz. Er muss mindestens 3 Buchstaben lang sein!");
		}
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
	public Spieler(String name, FarbEnum farbe, boolean ki) {//ruft Konstruktor name, farbe auf, setzt ki Objekt(klug so?) nochmal im Kopf durchgehen..
		this(name, farbe);
		if(ki==true){
		this.ki= new Ki_Dame(farbe);
	}}

	
	
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

	public Ki getKi() {
		return ki;
	}
	public void setKi(Ki ki) {
		this.ki = ki;
	}

	public String generiereCSV(){
		return "" + getClass() + ":name=" + this.getName() + ", farbe=" + this.getFarbe() + ";";
	}
	
	/**
	 * String Override
	 * @return String name
	 */
	@Override
	public String toString() {
		if(this.getKi()!=null){
			return this.name+", '"+this.getFarbe()+"'"+" (KI)";
		}
		return this.name + ", '" + this.getFarbe()+"'"; //Name Des Spielers + Farbe


																											// Farbe
	}
}
