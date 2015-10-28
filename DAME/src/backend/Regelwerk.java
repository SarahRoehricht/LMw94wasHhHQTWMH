package backend;

import java.util.ArrayList;

/**
 * 
 * @author A2
 *
 */
public class Regelwerk {

	private Spiel spiel;
	private Spielfigur spielfigur;
	private Spielfeld spielfeld;

	public Regelwerk(Spiel spiel) {
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
	
	public void moveObenRechts(Spielfigur dame, Spielfeld posxy){
	
		Spielfeld[][] tempBrett = spiel.getSpielbrett().getBrett();
		
		//Alle Felder von Dame diagonal nach rechts oben
		for(int positionx = dame.getPosition().getPosX(); positionx <= spiel.getSpielbrett().groesse ;positionx ++){
			for(int positiony = dame.getPosition().getPosY(); positiony <= spiel.getSpielbrett().groesse; positiony++){
				int i = 1;
				int j = 1;
				int posiX = dame.getPosition().getPosX()+i;
				int posiY =  dame.getPosition().getPosY()+j;

				if(tempBrett[posiX][posiY].getSpielfigur() != null){
					if(tempBrett[posiX][posiY].getSpielfigur().getFarbe()!=dame.getFarbe()){
						//null? Außerhalb des Brettes noch prüfen
						if(tempBrett[posiX+1][posiY+1].getSpielfigur()==null){
							//Entscheidung ob man schlagen will
							schlagen(dame, tempBrett[posiX][posiY].getSpielfigur(),tempBrett[posiX][posiY], posxy);					
							
							//schlagen möglich
						}
					}
				}
				else{
					i++;
					j++;
				}
				
				//Überprüfung ob eigener Stein auf einem der Felder	
			}
		}
		
		
		//Alle Felder von Dame nach links oben
		for(int positionx = dame.getPosition().getPosX(); positionx <= spiel.getSpielbrett().groesse-11 ;positionx--){
			for(int positiony = dame.getPosition().getPosY(); positiony <= spiel.getSpielbrett().groesse; positiony++){			
			}
		}
				
		//Alle Felder von Dame nach rechts unten
		for(int positionx = dame.getPosition().getPosX(); positionx <= spiel.getSpielbrett().groesse ;positionx ++){
			for(int positiony = dame.getPosition().getPosY(); positiony <= spiel.getSpielbrett().groesse-11; positiony--){
			}
		}
		
		//Alle Felder von Dame nach links unten
		for(int positionx = dame.getPosition().getPosX(); positionx <= spiel.getSpielbrett().groesse-11 ;positionx--){
			for(int positiony = dame.getPosition().getPosY(); positiony <= spiel.getSpielbrett().groesse-11; positiony--){
			}
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


	public Spiel getSpiel() {
		return spiel;
	}

	public void setSpiel(Spiel spiel) {
		this.spiel = spiel;
	}


	public void schlagen(Spielfigur figur, Spielfigur gegner, Spielfeld positionGenger, Spielfeld Endposition) {
		figur.setPosition(Endposition);
		//Stein wird von Feld gelöscht
		positionGenger.setSpielfigur(null);
		//Referenz von Stein auf Feld gelöscht
		gegner.setPosition(null);
	}

}
