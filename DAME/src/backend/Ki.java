package backend;

import java.util.Random;

public abstract class Ki {

	private Spielfeld[][] spielbrett;
	private Spielfeld[][] startWeiß;
	private Spielfeld[][] startSchwarz;
	private Spieler ki;

	public Ki() {
	}

	public Spieler getKi() {
		return ki;
	}

	public void setKi(Spieler ki) {
		this.ki = ki;
	}
	
	/*
	 * Methode für ersten Zug frägt ab, ob weiß oder schwarz liest Spielbrett ein
	 * schreibt alle gültigen Steine für ersten Zug in Array und gibt zufälligen
	 * Stein zurück
	 */

	public void kiFirstAct() {
		spielbrett = null;
		startWeiß = null;
		startSchwarz = null;
		Random r;
		int[] lor = { -1, 1 };
		Random linksRechts = new Random();
		int entscheide = linksRechts.nextInt(2);

		// KopieSpielBrett
		for (int i = 0; i < spieler.getSpielbrett().getBrett().length; i++) {
			for (int j = 0; j < spieler.getSpielbrett().getBrett()[i].length; j++) {
				spielbrett[i][j] = spieler.getSpielbrett().getBrett()[i][j];
			}
		}

		//boolean feindlich = (super.getSpielbrett().getBrett()[0][0].getFarbe() == FarbEnum.schwarz);
		
		//überprüfen, ob Spieler 1 Ki sein soll => 'y' oder 'n'
		spieler[0].setFarbe(FarbEnum.weiß);
		
		
		// WEISS
		if (spieler.getSpielerBisher() == 0) {
			for (int j = 0; j < spielbrett[4].length; j = j++) {
				if (spielbrett[4][j % 2] != null) {
					startWeiß[4][j] = spielbrett[4][j];
					r = new Random();
					int zufall = r.nextInt(startWeiß.length);
					if (zufall == 0) {
						spieler.move(spielbrett[4][0], spielbrett[5][1]);
					} else {
						spieler.move(spielbrett[4][zufall], spielbrett[5][zufall + lor[entscheide]]);
						break;
					}

				}
			}
		}

		// SCHWARZ
		if (super.getSpielerBisher() == 1) {

			for (int j = 1; j < spielbrett[7].length; j = j + 2) {
				if (spielbrett[7][j] != null) {
					startWeiß[7][j] = spielbrett[7][j];
					r = new Random();
					int zufall = r.nextInt(startSchwarz.length);
					if (zufall == 11) {
						spieler.move(spielbrett[7][11], spielbrett[6][10]);
					} else {
						spieler.move(spielbrett[7][zufall], spielbrett[6][zufall + lor[entscheide]]);
						break;
					}
				}
			}
		}
	}
	
//	public void kiAct(){
//		schlagen(null, null, null);
//	}
	
}
