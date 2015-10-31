package backend;

import java.util.ArrayList;
import java.util.Random;

public class Ki_Dame extends Ki {
	private Spielfeld[][] spielbrett;
	private Spieler ki;
	private FarbEnum farbe;
	final int anzahl = 2;
	private Spielfeld StartZiel[] = new Spielfeld[anzahl];
	private boolean schlagen = false;// daweil true

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

		final int max = 2;
		int[] lor = new int[max];
		lor[0] = -1;
		lor[1] = 1;
		Random linksRechts = new Random();
		int entscheide = linksRechts.nextInt(2);

		// Weiße KI
		if (farbe == FarbEnum.weiß) {
			ArrayList<Spielfeld> figurenWeiß = new ArrayList<Spielfeld>();
			ArrayList<Spielfeld> figurenSchwarz = new ArrayList<Spielfeld>();
			for (int i = 0; i < brett.length; i++) {
				for (int j = 0; j < brett[i].length; j++) {
					if (brett[j][i].getSpielfigur() != null) {
						if (brett[j][i].getSpielfigur().getFarbe() == FarbEnum.schwarz) {
							figurenSchwarz.add(brett[j][i]);
						}
						if (brett[j][i].getSpielfigur().getFarbe() == FarbEnum.weiß) {
							figurenWeiß.add(brett[j][i]);
						}
					}

				}
			}
			Random weiß = new Random();
			int weißZufall = weiß.nextInt(figurenWeiß.size());
			
			//this.StartZiel[0] = brett[figurenWeiß.get(weißZufall).getPosY()][figurenWeiß.get(weißZufall).getPosX()];
			//this.StartZiel[1] = brett[figurenWeiß.get(weißZufall).getPosY() + 1][figurenWeiß.get(weißZufall).getPosX() + lor[entscheide]];
			
			kiRadar(brett, figurenWeiß.get(weißZufall).getPosY(), figurenWeiß.get(weißZufall).getPosX(), lor, entscheide);
			
			return StartZiel;

		}

		// schwarze KI
		if (farbe == FarbEnum.schwarz) {
			ArrayList<Spielfeld> figurenSchwarz = new ArrayList<Spielfeld>();
			ArrayList<Spielfeld> figurenWeiß = new ArrayList<Spielfeld>();
			for (int i = 0; i < brett.length; i++) {
				for (int j = 0; j < brett[i].length; j++) {
					if (brett[j][i].getSpielfigur() != null) {
						if (brett[j][i].getSpielfigur().getFarbe() == FarbEnum.schwarz) {
							figurenSchwarz.add(brett[j][i]);
						}
						if (brett[j][i].getSpielfigur().getFarbe() == FarbEnum.weiß) {
							figurenWeiß.add(brett[j][i]);

						}
					}

				}
			}
			if (schlagen == false) {
				Random schwarz = new Random();
				int schwarzZufall = schwarz.nextInt(figurenSchwarz.size());
				// this.StartZiel[0] = brett[figurenSchwarz.get(schwarzZufall).getPosY()][figurenSchwarz.get(schwarzZufall).getPosX()];
				// this.StartZiel[1] = brett[figurenWeiß.get(schwarzZufall).getPosY() + 1][figurenWeiß.get(schwarzZufall).getPosX() + lor[entscheide]];
				
				kiRadar(brett, figurenSchwarz.get(schwarzZufall).getPosY(), figurenSchwarz.get(schwarzZufall).getPosX(), lor, entscheide);
				
				
				return StartZiel;
			}

		}
		return null;
	}

	/************************************ Radar-Methode ******************************************/
	public Spielfeld[] kiRadar(Spielfeld[][] brett, int j, int i, int[] lor, int entscheide) {

		StartZiel[0] = brett[j][i];

		/*************** WEISS ******************/
		if (brett[j][i].getSpielfigur().getFarbe() == FarbEnum.weiß) {
			
			// rechts oben schlagen
			if ((brett[j + 1][i + 1] != null && brett[j + 1][i + 1]
					.getSpielfigur().getFarbe() == FarbEnum.schwarz)
					&& (brett[j + 2][i + 2] == null && (i+1 < brett.length && j+1 < brett.length))) {
				StartZiel[1] = brett[j + 2][i + 2];
				schlagen = true;
			}
			// links oben schlagen
			else if ((brett[j + 1][i - 1] != null && brett[j + 1][i - 1]
					.getSpielfigur().getFarbe() == FarbEnum.schwarz)
					&& (brett[j + 2][i - 2] == null && ((i-2 >= 0 && j+2 < brett.length-1)))) {
				StartZiel[1] = brett[j + 2][i - 2];
				schlagen = true;
			}
			// sind rechts und links 2 Felder frei
			// und kein Schlagen möglich, dann zufälliges Feld wählen
			if ((brett[j + 1][i + 1] == null && brett[j + 2][i + 2] == null)
					|| (brett[j + 1][i - 1] == null && brett[j + 2][i - 2] == null) &&
					(i+2 < brett.length && j+2 < brett.length) || (i-1 >= 0 && j+1 < brett.length-1)) {
				StartZiel[1] = brett[j + 1][i + lor[entscheide]];
			}

			for (int k = 0; k < brett.length; k++) {
				if (StartZiel[1] == brett[11][k]) {
					// weißer Stein wird zu Dame
				}
			}
		}
		
		/*************** SCHWARZ ******************/
		if (brett[j][i].getSpielfigur().getFarbe() == FarbEnum.schwarz) {
			// rechts unten schlagen
			if ((brett[j - 1][i + 1] != null && brett[j - 1][i + 1]
					.getSpielfigur().getFarbe() == FarbEnum.weiß)
					&& (brett[j - 2][i + 2] == null && (i+1 < brett.length-1 && j-1 >= 0))) {
				StartZiel[1] = brett[j - 2][i + 2];
				schlagen = true;
			}
			// links unten schlagen
			else if ((brett[j - 1][i - 1] != null && brett[j - 1][i - 1]
					.getSpielfigur().getFarbe() == FarbEnum.weiß)
					&& (brett[j - 2][i - 2] == null && (i-1 >= 0 && j-1 >= 0))) {
				StartZiel[1] = brett[j - 2][i - 2];
				schlagen = true;
			}
			// sind rechts und links 2 Felder frei
			// und kein Schlagen möglich, dann zufälliges Feld wählen
			if ((brett[j - 1][i + 1] == null && brett[j - 2][i + 2] == null)
					|| (brett[j - 1][i - 1] == null && brett[j - 2][i - 2] == null) && (j-1 >= 0 && i-1 >= 0) && i+1 < brett.length-1){
				StartZiel[1] = brett[j + 1][i + lor[entscheide]];
			}

			for (int k = 0; k < brett.length; k++) {
				if (StartZiel[1] == brett[0][k]) {
					// schwarzer Stein wird zu Dame
				}
			}
		}
		return StartZiel;
	}

	@Override
	public void move(Spielfeld start, Spielfeld ziel) {

		int xS = start.getPosX();
		int yS = start.getPosY();
		int xZ = ziel.getPosX();
		int yZ = ziel.getPosY();
		spielbrett[yZ][xZ].setSpielfigur(spielbrett[yS][xS].getSpielfigur());// kopie erstellen
		spielbrett[yS][xS].setSpielfigur(null);
	}
}