package backend;

import java.util.ArrayList;

/**
 * 
 * @author A2
 *
 */
public class Regelwerk {
	
	private Spiel spiel;
	
public Regelwerk(Spiel spiel){
	this.setSpiel(spiel);
}


	/**
	 * 
	 * @param stein1
	 * @param posxy
	 */
	public void move(Spielfigur stein1, Spielfeld posxy) {
		boolean hatGezogen = false;
		boolean gegnerGeschlagen = false;

		while (istZugErlaubt(posxy) && ((!hatGezogen(hatGezogen) && !hatGeschlagen(gegnerGeschlagen)) || (hatGezogen(hatGezogen) && hatGeschlagen(gegnerGeschlagen)))) {
			if (stein1.getPosition() != posxy)
				stein1.setPosition(posxy);
			hatGezogen = true;
			wirdDame(stein1);
		}
	}
	
	public void moveDame(Spielfigur dame, Spielfeld posxy){
		ArrayList <Spielfeld> möglicheFelder = new ArrayList<>();
		boolean gegnerischerStein = false;
		//Alle Felder von Dame diagonal nach rechts oben
		for(int positionx = dame.getPosition().getPosX(); positionx <= spiel.getSpielbrett().groesse ;positionx ++){
			for(int positiony = dame.getPosition().getPosY(); positiony <= spiel.getSpielbrett().groesse; positiony++){
				positionx = dame.getPosition().getPosX()+1;
				positiony = dame.getPosition().getPosY()+1;
				
				//Überprüfung ob gegenerischer Stein auf einem der Felder
			
				//Überprüfung ob Platz hinter gegenerischem Stein frei ist
				
				//Überprüfung ob eigener Stein auf einem der Felder
				
			}
		}
		
		//Alle Felder von Dame nach links oben
		for(int positionx = dame.getPosition().getPosX(); positionx <= spiel.getSpielbrett().groesse-11 ;positionx--){
			for(int positiony = dame.getPosition().getPosY(); positiony <= spiel.getSpielbrett().groesse; positiony++){
				Spielfeld feld = new Spielfeld(positionx, positiony, null);
				möglicheFelder.add(feld);
			}
		}
				
		//Alle Felder von Dame nach rechts unten
		for(int positionx = dame.getPosition().getPosX(); positionx <= spiel.getSpielbrett().groesse ;positionx ++){
			for(int positiony = dame.getPosition().getPosY(); positiony <= spiel.getSpielbrett().groesse-11; positiony--){
				Spielfeld feld = new Spielfeld(positionx, positiony, null);
				möglicheFelder.add(feld);
			}
		}
		
		//Alle Felder von Dame nach links unten
		for(int positionx = dame.getPosition().getPosX(); positionx <= spiel.getSpielbrett().groesse-11 ;positionx--){
			for(int positiony = dame.getPosition().getPosY(); positiony <= spiel.getSpielbrett().groesse-11; positiony--){
				Spielfeld feld = new Spielfeld(positionx, positiony, null);
				möglicheFelder.add(feld);
			}
		}
		
		if(möglicheFelder.contains(posxy)){
			dame.setPosition(posxy);
		}
		
	}
	
	/**
	 * 
	 * @param posxy
	 * @return
	 */
	public boolean istZugErlaubt(Spielfeld posxy) {
		if (posxy != null && posxy.getSpielfigur() == null) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param hatGezogen
	 * @return
	 */
	public boolean hatGezogen(boolean hatGezogen) {
		if (hatGezogen) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param hatGeschlagen
	 * @return
	 */
	public boolean hatGeschlagen(boolean hatGeschlagen) {
		if (hatGeschlagen) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param stein1
	 */
	// Überprüfung ob aus Stein eine Dame wird oder ob Stein schon Dame ist
	public void wirdDame(Spielfigur stein1) {
		if (stein1.isDame() == false) {
			if (stein1.getPosition().getPosY() == 11 && stein1.getFarbe() == FarbEnum.weiß) {
				// Wenn if-Bedingung erfüllt, dann wird Stein zur Dame
			}
			if (stein1.getPosition().getPosY() == 0 && stein1.getFarbe() == FarbEnum.schwarz) {
				// Wenn if-Bedingung erfüllt, dann wird Stein zur Dame
			}
		}
	}
	
	
	
	/**
	 * 
	 * @return
	 */
	// schließt die äußerste Reihe des Brettes aus. Hier ist grundsätzlich kein Schlagen möglich
	public Spielfeld[][] schlagenMoeglichFelder() {
		Spielfeld[][] zulaessig = new Spielfeld[11][11];
		for (int i = zulaessig.length - 1; i >= 1; i--) {
			for (int j = 1; j < zulaessig[i].length; j++) {
				if (i % 2 == j % 2) {
					zulaessig[i][j] = new Spielfeld(i, j, FarbEnum.schwarz);
				} else {
					zulaessig[i][j] = new Spielfeld(i, j, FarbEnum.weiß);
				}
			}
		}
		return zulaessig;
	}

	/**
	 * 
	 * @param akt_posxy
	 * @return
	 */
	// überprüft, ob sich der Stein auf dem inneren Brett befindet
	public boolean schlagenFeldGroesse(Spielfeld akt_posxy) {
		for (int i = 0; i < schlagenMoeglichFelder().length; i++) {
			for (int j = 0; j < schlagenMoeglichFelder()[i].length; j++) {
				if (akt_posxy == schlagenMoeglichFelder()[i][j]) {
					return true;
				}
			}
		}
		return false;
	}
	public Spiel getSpiel() {
		return spiel;
	}
	public void setSpiel(Spiel spiel) {
		this.spiel = spiel;
	}

}
