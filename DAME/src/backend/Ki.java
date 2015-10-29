package backend;

import java.util.Random;

public abstract class Ki extends Spiel implements iBediener {

	private Spielfeld[][] spielbrett;
	private Spielfeld[][] startWeiß;
	private Spielfeld[][] startSchwarz;

	public Ki() {
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
		for (int i = 0; i < super.getSpielbrett().getBrett().length; i++) {
			for (int j = 0; j < super.getSpielbrett().getBrett()[i].length; j++) {
				spielbrett[i][j] = super.getSpielbrett().getBrett()[i][j];
			}
		}

		// WEISS
		if (super.getSpielerBisher() == 1) {
			for (int j = 0; j < spielbrett[4].length; j = j++) {
				if (spielbrett[4][j % 2] != null) {
					startWeiß[4][j] = spielbrett[4][j];
					r = new Random();
					int zufall = r.nextInt(startWeiß.length);
					if (zufall == 0) {
						super.move(spielbrett[4][0], spielbrett[5][1]);
					} else {
						super.move(spielbrett[4][zufall], spielbrett[5][zufall + lor[entscheide]]);
						break;
					}

				}
			}
		}

		// SCHWARZ
		if (super.getSpielerBisher() == 2) {

			for (int j = 1; j < spielbrett[7].length; j = j + 2) {
				if (spielbrett[7][j] != null) {
					startWeiß[7][j] = spielbrett[7][j];
					r = new Random();
					int zufall = r.nextInt(startSchwarz.length);
					if (zufall == 11) {
						super.move(spielbrett[7][11], spielbrett[6][10]);
					} else {
						super.move(spielbrett[7][zufall], spielbrett[6][zufall + lor[entscheide]]);
						break;
					}
				}
			}
		}

	}
}
