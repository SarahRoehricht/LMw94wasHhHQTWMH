package backend;

import java.util.ArrayList;
import java.util.Random;

public class Ki_Dame extends Ki {
	private Spielfeld[][] spielbrett;
	private Spielfeld[][] startWeiß;
	private Spielfeld[][] startSchwarz;
	private Spieler ki;
	private FarbEnum farbe;
	private Spielfeld StartZiel[];

	public Ki_Dame(FarbEnum farbe) {
		this.setFarbe(farbe);

	}

	public Spieler getKi() {
		return ki;
	}

	public void setKi(Spieler ki) {
		this.ki = ki;
	}

	public FarbEnum getFarbe() {
		return farbe;
	}

	public void setFarbe(FarbEnum farbe) {
		this.farbe = farbe;
	}

	/*
	 * Methode für ersten Zug frägt ab, ob weiß oder schwarz liest Spielbrett ein
	 * schreibt alle gültigen Steine für ersten Zug in Array und gibt zufälligen
	 * Stein zurück
	 */
	public Spielfeld[] kiAct(Spielfeld[][] brett) {
		spielbrett = null;
		startWeiß = null;
		startSchwarz = null;
		Random r;
		int[] lor = { -1, 1 };
		Random linksRechts = new Random();
		int entscheide = linksRechts.nextInt(2);

		// KopieSpielBrett
		// for (int i = 0; i < brett.length; i++) {
		// for (int j = 0; j < brett[i].length; j++) {
		// spielbrett[i][j] = brett[i][j];
		// }
		// }

		// boolean feindlich = (super.getSpielbrett().getBrett()[0][0].getFarbe() ==
		// FarbEnum.schwarz);

		// noch überprüfen, ob Spieler 1 Ki sein soll => 'y' oder 'n'

		// // WEISS
		// if (farbe == farbe.weiß) {
		// for (int j = 0; j < spielbrett[4].length; j = j++) {
		// if (spielbrett[4][j % 2] != null) {
		// startWeiß[4][j] = spielbrett[4][j];
		// r = new Random();
		// int zufall = r.nextInt(startWeiß.length);
		// if (zufall == 0) {
		// move(spielbrett[4][0], spielbrett[5][1]);
		// } else {
		// move(spielbrett[4][zufall], spielbrett[5][zufall + lor[entscheide]]);
		// break;
		// }
		//
		// }
		// }
		// }

		// SCHWARZ
		// if (farbe == farbe.schwarz) {
		//
		// for (int j = 1; j < spielbrett[7].length; j = j + 2) {
		// if (spielbrett[7][j] != null) {
		// startWeiß[7][j] = spielbrett[7][j];
		// r = new Random();
		// int zufall = r.nextInt(startSchwarz.length);
		// if (zufall == 11) {
		// return StartZiel={spielbrett[7][11], spielbrett[6][10]};
		// } else {
		// move(spielbrett[7][zufall], spielbrett[6][zufall + lor[entscheide]]);
		// return spielbrett;
		// }
		// }
		// }
		// }
		// Weiße KI
		if (farbe == FarbEnum.weiß) {
			ArrayList<Spielfeld> figurenWeiß = new ArrayList<Spielfeld>();
			ArrayList<Spielfeld> figurenSchwarz = new ArrayList<Spielfeld>();
			for (int i = 0; i < spielbrett.length; i++) {
				for (int j = 0; j < spielbrett[i].length; j++) {
					if (spielbrett[j][i].getSpielfigur() != null) {
						if (spielbrett[j][i].getSpielfigur().getFarbe() == FarbEnum.schwarz) {
							figurenSchwarz.add(spielbrett[j][i]);
						}
						if (spielbrett[j][i].getSpielfigur().getFarbe() == FarbEnum.weiß) {
							figurenWeiß.add(spielbrett[j][i]);

						}
					}

				}
			}
			Random weiß = new Random();
			int weißZufall = weiß.nextInt(figurenWeiß.size());
			this.StartZiel[0] = spielbrett[figurenWeiß.get(weißZufall).getPosY()][figurenWeiß.get(weißZufall).getPosX()];
			this.StartZiel[1] = spielbrett[figurenWeiß.get(weißZufall).getPosY() + 1][figurenWeiß.get(weißZufall).getPosX() + lor[entscheide]];
			return StartZiel;

		}

		// schwarze KI
		if (farbe == FarbEnum.schwarz) {
			ArrayList<Spielfeld> figurenSchwarz = new ArrayList<Spielfeld>();
			ArrayList<Spielfeld> figurenWeiß = new ArrayList<Spielfeld>();
			for (int i = 0; i < spielbrett.length; i++) {
				for (int j = 0; j < spielbrett[i].length; j++) {
					if (spielbrett[j][i].getSpielfigur() != null) {
						if (spielbrett[j][i].getSpielfigur().getFarbe() == FarbEnum.schwarz) {
							figurenSchwarz.add(spielbrett[j][i]);
						}
						if (spielbrett[j][i].getSpielfigur().getFarbe() == FarbEnum.weiß) {
							figurenWeiß.add(spielbrett[j][i]);

						}
					}

				}
			}

			Random schwarz = new Random();
			int schwarzZufall = schwarz.nextInt(figurenSchwarz.size());
			this.StartZiel[0] = spielbrett[figurenSchwarz.get(schwarzZufall).getPosY()][figurenSchwarz.get(schwarzZufall).getPosX()];
			this.StartZiel[1] = spielbrett[figurenWeiß.get(schwarzZufall).getPosY() + 1][figurenWeiß.get(schwarzZufall).getPosX() + lor[entscheide]];
			return StartZiel;

		}
		

		// public void kiAct(){
		// schlagen(null, null, null);
		// }
	}

	public void move(Spielfeld start, Spielfeld ziel) {

		int xS = start.getPosX();
		int yS = start.getPosY();
		int xZ = ziel.getPosX();
		int yZ = ziel.getPosY();
		spielbrett[yZ][xZ].setSpielfigur(spielbrett[yS][xS].getSpielfigur());
		spielbrett[yS][xS].setSpielfigur(null);

	}

}