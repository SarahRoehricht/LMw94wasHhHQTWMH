package backend;

import java.util.ArrayList;
import java.util.Random;

public class Ki_Dame extends Ki {
	private Spielfeld[][] spielbrett;
	private Spieler ki;
	private FarbEnum farbe;
	final int anzahl = 2;
	private Spielfeld StartZiel[] = new Spielfeld[anzahl];

	ArrayList<Spielfeld> figurenWeiß = new ArrayList<Spielfeld>();
	ArrayList<Spielfeld> damenWeiß = new ArrayList<Spielfeld>();

	ArrayList<Spielfeld> figurenSchwarz = new ArrayList<Spielfeld>();
	ArrayList<Spielfeld> damenSchwarz = new ArrayList<Spielfeld>();

	// private boolean schlagen = false;// daweil true

	/**
	 * 
	 * @param farbe
	 */
	public Ki_Dame(FarbEnum farbe) {
		super(farbe);

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

	@Override
	public Spielfeld[] kiAct(Spielfeld[][] brett) {

		// Weiße KI
		if (farbe == FarbEnum.weiß) {
			for (int i = brett.length - 1; i >= 0; i--) {
				for (int j = 0; j < brett[i].length; j++) {
					if (brett[j][i].getSpielfigur() != null) {

						// weiße Steine zu ArrayList hinzufügen
						if (brett[j][i].getSpielfigur() != null && brett[j][i].getSpielfigur().getFarbe() == FarbEnum.weiß) {
							figurenWeiß.add(brett[j][i]);
						}

						// schwarze Steine zu ArrayList hinzufügen
						if (brett[j][i].getSpielfigur() != null && brett[j][i].getSpielfigur().getFarbe() == FarbEnum.schwarz) {
							figurenSchwarz.add(brett[j][i]);
						}

						// Damen weiß
						if (brett[i][11].getSpielfigur() != null && brett[i][11].getSpielfigur().getFarbe() == FarbEnum.weiß) {
							brett[i][11].getSpielfigur().setDame(true);
							// damenWeiß.add(brett[i][j]);
							figurenWeiß.add(brett[j][i]);
						}

						// Damen schwarz
						if (brett[i][11].getSpielfigur() != null && brett[i][11].getSpielfigur().getFarbe() == FarbEnum.schwarz) {
							brett[i][11].getSpielfigur().setDame(true);
							// damenSchwarz.add(brett[i][j]);
							figurenSchwarz.add(brett[j][i]);
						}

					}

				}
			}
			Random weiß = new Random();
			int steinWeißZufall = weiß.nextInt(figurenWeiß.size());
			// int dameWeißZufall = weiß.nextInt(damenWeiß.size());

			// this.StartZiel[0] =
			// brett[figurenWeiß.get(weißZufall).getPosY()][figurenWeiß.get(weißZufall).getPosX()];
			// this.StartZiel[1] = brett[figurenWeiß.get(weißZufall).getPosY() +
			// 1][figurenWeiß.get(weißZufall).getPosX() + lor[entscheide]];

			kiRadar(brett, figurenWeiß.get(steinWeißZufall).getPosY(), figurenWeiß.get(steinWeißZufall).getPosX());

			return StartZiel;

		}

		// schwarze KI
		if (farbe == FarbEnum.schwarz) {
			for (int i = brett.length - 1; i >= 0; i--) {
				for (int j = 0; j < brett[i].length; j++) {
					if (brett[j][i].getSpielfigur() != null) {

						// weiße Steine zu ArrayList hinzufügen
						if (brett[j][i].getSpielfigur() != null && brett[j][i].getSpielfigur().getFarbe() == FarbEnum.weiß) {
							figurenWeiß.add(brett[j][i]);
							System.out.println("weißer Stein: " + brett[j][i]);
						}

						// schwarze Steine zu ArrayList hinzufügen
						if (brett[j][i].getSpielfigur() != null && brett[j][i].getSpielfigur().getFarbe() == FarbEnum.schwarz) {
							figurenSchwarz.add(brett[j][i]);
						}

						// Damen weiß
						if (brett[i][11].getSpielfigur() != null && brett[i][11].getSpielfigur().getFarbe() == FarbEnum.weiß) {
							brett[i][11].getSpielfigur().setDame(true);
							// damenWeiß.add(brett[i][j]);
							figurenWeiß.add(brett[j][i]);
						}

						// Damen schwarz
						if (brett[i][11].getSpielfigur() != null && brett[i][11].getSpielfigur().getFarbe() == FarbEnum.schwarz) {
							brett[i][11].getSpielfigur().setDame(true);
							// damenSchwarz.add(brett[i][j]);
							figurenSchwarz.add(brett[j][i]);
						}
					}
				}
			}
			Random schwarz = new Random();
			int steinSchwarzZufall = schwarz.nextInt(figurenSchwarz.size());
			int dameSchwarzZufall = schwarz.nextInt(damenSchwarz.size());

			kiRadar(brett, figurenSchwarz.get(steinSchwarzZufall).getPosY(), figurenSchwarz.get(steinSchwarzZufall).getPosX());

			return StartZiel;
		}

		return null;
	}

	/************************************ Radar-Methode ******************************************/

	public Spielfeld[] kiRadar(Spielfeld[][] brett, int j, int i) {

		final int max = 2;
		int[] lor = new int[max];
		lor[0] = -1;
		lor[1] = 1;
		Random linksRechts = new Random();
		int entscheide = linksRechts.nextInt(max);

		StartZiel[0] = brett[j][i];

		/*************** WEISS ******************/
		if (brett[j][i].getSpielfigur().getFarbe() == FarbEnum.weiß) {

			// rechts oben schlagen
			if ((brett[j + 1][i + 1].getSpielfigur() != null && brett[j + 1][i + 1].getSpielfigur().getFarbe() == FarbEnum.schwarz) && (brett[j + 2][i + 2].getSpielfigur() == null && (i <= brett.length - 3 && j <= brett.length - 3))) {
				StartZiel[1] = brett[j + 2][i + 2];
			}
			// links oben schlagen
			else if ((brett[j + 1][i - 1].getSpielfigur() != null && brett[j + 1][i - 1].getSpielfigur().getFarbe() == FarbEnum.schwarz) && (brett[j + 2][i - 2].getSpielfigur() == null && i >= 2 && j <= brett.length - 3)) {
				StartZiel[1] = brett[j + 2][i - 2];
			}

			// rechts unten schlagen
			else if ((brett[j - 1][i + 1].getSpielfigur() != null && brett[j - 1][i + 1].getSpielfigur().getFarbe() == FarbEnum.schwarz) && (brett[j - 2][i + 2].getSpielfigur() == null && (i <= brett.length - 3 && j >= 2))) {
				StartZiel[1] = brett[j - 2][i + 2];
			}

			// links unten schlagen
			else if ((brett[j - 1][i - 1].getSpielfigur() != null && brett[j - 1][i - 1].getSpielfigur().getFarbe() == FarbEnum.schwarz) && (brett[j - 2][i - 2].getSpielfigur() == null && (i - 2 >= 0 && j - 2 >= 0))) {
				StartZiel[1] = brett[j - 2][i - 2];
			}

			// SICHERER ZUG nach rechts oben
			else if ((brett[j + 1][i + 1].getSpielfigur() == null && brett[j + 2][i + 2].getSpielfigur() == null) && (i <= brett.length - 3 && j <= brett.length - 3)) {
				StartZiel[1] = brett[j + 1][i + 1];
			}
			// SICHERER ZUG nach links oben
			else if ((brett[j + 1][i - 1].getSpielfigur() == null && brett[j + 2][i - 2].getSpielfigur() == null) && (i >= 3 && j <= brett.length - 3)) {
				StartZiel[1] = brett[j + 1][i - 1];
			}

			// UNSICHERER ZUG nach rechts oben
			else if ((brett[j + 1][i + 1].getSpielfigur() == null) && (i <= brett.length - 2 && j <= brett.length - 2)) {
				StartZiel[1] = brett[j + 1][i + 1];
			}
			// UNSICHERER ZUG nach links oben
			else if ((brett[j + 1][i - 1].getSpielfigur() == null) && (i >= 2 && j <= brett.length - 2)) {
				StartZiel[1] = brett[j + 1][i - 1];
			}

			// WEISSE DAMEN REGELN HIER EINBINDEN

		}

		/*************** SCHWARZ ******************/

		// NOCH ALLES PRÜFEN, MOMENTAN NUR COPY PASTE VON WEIß !!!!!!!!!!!!!!!!!

		if (brett[j][i].getSpielfigur().getFarbe() == FarbEnum.schwarz) {
			// rechts oben schlagen
			if ((brett[j + 1][i + 1].getSpielfigur() != null && brett[j + 1][i + 1].getSpielfigur().getFarbe() == FarbEnum.weiß) && (brett[j + 2][i + 2].getSpielfigur() == null && (i < brett.length - 3 && j <= brett.length - 3))) {
				StartZiel[1] = brett[j + 2][i + 2];
			}
			// links oben schlagen
			else if ((brett[j + 1][i - 1].getSpielfigur() != null && brett[j + 1][i - 1].getSpielfigur().getFarbe() == FarbEnum.weiß) && (brett[j + 2][i - 2].getSpielfigur() == null && ((i >= 2 && j <= brett.length - 3)))) {
				StartZiel[1] = brett[j + 2][i - 2];
			}

			// rechts unten schlagen
			else if ((brett[j - 1][i + 1].getSpielfigur() != null && brett[j - 1][i + 1].getSpielfigur().getFarbe() == FarbEnum.weiß) && (brett[j - 2][i + 2].getSpielfigur() == null && (i <= brett.length - 3 && j >= 2))) {
				StartZiel[1] = brett[j - 2][i + 2];
			}

			// links unten schlagen
			else if ((brett[j - 1][i - 1].getSpielfigur() != null && brett[j - 1][i - 1].getSpielfigur().getFarbe() == FarbEnum.weiß) && (brett[j - 2][i - 2].getSpielfigur() == null && (i - 2 >= 0 && j - 2 >= 0))) {
				StartZiel[1] = brett[j - 2][i - 2];
			}

			// SICHERER ZUG nach rechts unten
			else if ((brett[j - 1][i + 1].getSpielfigur() == null && brett[j - 2][i + 2].getSpielfigur() == null) && (i <= brett.length - 3 && j >= 0)) {
				StartZiel[1] = brett[j - 1][i + 1];
			}
			// SICHERER ZUG nach links unten
			else if ((brett[j - 1][i - 1].getSpielfigur() == null && brett[j - 2][i - 2].getSpielfigur() == null) && (i >= 3 && j >= 0)) {
				StartZiel[1] = brett[j - 1][i - 1];
			}

			// UNSICHERER ZUG nach rechts unten
			else if ((brett[j - 1][i + 1].getSpielfigur() == null) && (i <= brett.length - 2 && j >= 0)) {
				StartZiel[1] = brett[j - 1][i + 1];
			}
			// UNSICHERER ZUG nach links unten
			else if ((brett[j - 1][i - 1].getSpielfigur() == null) && (i >= 2 && j >= 0)) {
				StartZiel[1] = brett[j + 1][i - 1];
			}

			// SCHWARZE DAMEN REGELN HIER EINBINDEN

		}
		return StartZiel;
	}

	@Override
	public void move(Spielfeld start, Spielfeld ziel) {

		int xS = start.getPosX();
		int yS = start.getPosY();
		int xZ = ziel.getPosX();
		int yZ = ziel.getPosY();
		spielbrett[yZ][xZ].setSpielfigur(spielbrett[yS][xS].getSpielfigur());// kopie
		// erstellen
		spielbrett[yS][xS].setSpielfigur(null);
	}
}
