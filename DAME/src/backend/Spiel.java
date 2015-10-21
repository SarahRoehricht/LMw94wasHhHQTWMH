package backend;

public class Spiel implements iBediener {

	private SpielBrett spielbrett;
	private static final int spielerMax = 2;
	private Spieler[] spieler = new Spieler[spielerMax];
	private int spielerBisher = 0;

	public Spiel(int i) {

	}

	public Spiel() {
		spielbrett = new SpielBrett();
	}

	/**
	 * Fuegt einen Spieler dem Spieler Array hinzu. Max 2 Spieler
	 * 
	 * @param Spieler
	 *          s1
	 */
	public void add(Spieler s1) {
		if (spielerBisher >= 2) {
			throw new RuntimeException("Maximale Spieleranzahl erreicht!");
		} else if (spielerBisher == 0) {
			spieler[0] = s1;
			this.setSpielerBisher(++spielerBisher);
		} else {
			spieler[1] = s1;
			this.setSpielerBisher(++spielerBisher);
		}
		if (spielerBisher == 2) {// überprüft, dass kine zwei identischen Namen
															// vergeben wurden
			if (spieler[0].getName().equals(spieler[1].getName())) {
				throw new RuntimeException("Dieser Name ist bereits vergeben!");
			}
		}
	}

	// posxy gibt feld an auf das gezogen werden soll.
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

	public int getSpielerBisher() {
		return spielerBisher;
	}

	public void setSpielerBisher(int spielerBisher) {
		this.spielerBisher = spielerBisher;
	}

}
