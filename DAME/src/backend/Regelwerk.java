package backend;
/**
 * 
 * @author A2
 *
 */
public class Regelwerk {
/**
 * 
 * @param stein1
 * @param posxy
 */
	// posxy gibt feld an auf das gezogen werden soll.
	//Methode move() in mehrere Methoden zu unterteilen!!
	public void move(Spielfigur stein1, Spielfeld posxy) {
		boolean hatGezogen = false;
		boolean gegnerGeschlagen = false;

		while (posxy != null && posxy.getSpielfigur() == null && ((hatGezogen == false && gegnerGeschlagen == false) || (hatGezogen == true && gegnerGeschlagen == true))) {
			if (stein1.getPosition() != posxy)
				stein1.setPosition(posxy);
			hatGezogen = true;
			// Überprüfung ob aus Stein eine Dame wird oder ob Stein schon Dame ist
			if (stein1.isDame() != true) {
				if (stein1.getPosition().getPosY() == 11 && stein1.getFarbe() == FarbEnum.weiß) {
					// Wenn if-Bedingung erfüllt, dann wird Stein zur Dame
				}
				if (stein1.getPosition().getPosY() == 0 && stein1.getFarbe() == FarbEnum.schwarz) {
					// Wenn if-Bedingung erfüllt, dann wird Stein zur Dame
				}
			}
		}
	}

}
