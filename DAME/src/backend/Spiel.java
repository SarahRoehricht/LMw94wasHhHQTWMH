package backend;

import java.util.Scanner;

public class Spiel implements iBediener {

	private SpielBrett spielbrett;

	private static final int spielerMax = 2;

	private boolean Siegkondition = false;
	private Spieler[] spieler = new Spieler[spielerMax];
	private int spielerBisher = 0;
	private Regelwerk regelwerk;

	public Spiel() {
		spielbrett = new SpielBrett();
		this.welcome();
		regelwerk = new Regelwerk(this);
	}

	/**
	 * Spielinitialisierung, fragt die Namen ab und generiert Spieler fuer den
	 * Spielbeginn und Spielbrettausgabe
	 *
	 * @param Scanner
	 *          scanner, String name1, String name2 creates 2 Spieler objects and
	 *          calls this.add method with each of them one after another.
	 */
	public void welcome() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Name Spieler 1:");
		String name1 = scanner.next();
		System.out.println("KI? - y/n");
		String ki1 = scanner.next();
		String kiabfrage = new String("y");
		while (ki1.equals(kiabfrage)) { // Ki funktionsfaehig machen!
			System.out.println("Ki noch nicht funktionsfaehig :(");
			System.out.println("KI? - y/n");
			ki1 = scanner.next();
		}
		Spieler player1 = new Spieler(name1, FarbEnum.weiß);
		add(player1);

		System.out.println("Name Spieler 2:");
		String name2 = scanner.next();
		System.out.println("KI? - y/n");
		String ki2 = scanner.next();
		String kiabfrage2 = "y";
		while (ki2.equals(kiabfrage2)) {
			System.out.println("Ki noch nicht funktionsfaehig :(");// Ki
																															// funktionsfaehig
																															// machen!
			System.out.println("KI? - y/n");
			ki2 = scanner.next();
		}

		Spieler player2 = new Spieler(name2, FarbEnum.schwarz);
		add(player2);

		System.out.println("Moege das Spiel beginnen!");

		System.out.println(spielbrett);
		System.out.println(player1.getName() + " - " + "'" + player1.getFarbe() + "'" + " faengt an!");
		scanner.reset();
		playerRotation(player1, player2);
	}

	public void act(Spieler player1) {
		// boolean actdone=false;
		// while(actdone==true){
		Scanner scanner = new Scanner(System.in);

		System.out.println("Eingabe Startfeld:");
		String coorda = scanner.nextLine();
		Spielfeld startfeld = EingabeSpielfeld(coorda);
		// test ob die While Schleife darunter nur mit der if abfrage funktioniert,
		// und dies hier mit implementiert.
		// if(startfeld.getSpielfigur().getZustand() == ZustandEnum.nichts) {
		// System.out.println("Keine Spielfigur auf dem Spielfeld " + coorda + "!");
		// System.out.println("Eingabe Startfeld:");
		// String coorda2 = scanner.nextLine();
		// Spielfeld startfeldtemp=EingabeSpielfeld(coorda2);
		// startfeld=startfeldtemp;
		// }

		while (!startfeld.getSpielfigur().getFarbe().equals(player1.getFarbe())) {
			if (startfeld.getSpielfigur().getZustand() == ZustandEnum.nichts) {
				System.out.println("Keine Spielfigur auf dem Spielfeld " + coorda + "!");
				System.out.println("Eingabe Startfeld:");
				String coorda2 = scanner.nextLine();
				Spielfeld startfeldtemp = EingabeSpielfeld(coorda2);
				startfeld = startfeldtemp;
			} else {
				System.out.println("kein " + player1.getFarbe() + "er Spielstein!");
				System.out.println("Eingabe Startfeld:");
				String coorda2 = scanner.nextLine();
				Spielfeld startfeldtemp = EingabeSpielfeld(coorda2);
				startfeld = startfeldtemp;
			}
		}

		System.out.println("Eingabe Zielfeld:");
		String coordb = scanner.nextLine();
		Spielfeld zielfeld = EingabeSpielfeld(coordb);
		if (player1.getFarbe() == FarbEnum.weiß && startfeld.getSpielfigur().getZustand() == ZustandEnum.WStein) {
			if (startfeld.getPosX() + 1 == zielfeld.getPosX() && startfeld.getPosY() + 1 == zielfeld.getPosY()) {// Zielfeld:
																																																						// move
																																																						// right
																																																						// up
																																																						// legit
				// if(regelwerk.legitmove()){
				move(startfeld.getSpielfigur(), zielfeld);
				// }
			}
			if (startfeld.getPosX() - 1 == zielfeld.getPosX() && startfeld.getPosY() + 1 == zielfeld.getPosY()) {// Zielfeld:
																																																						// move
																																																						// left
																																																						// up
																																																						// legit
				// if(regelwerk.legitmove()){
				move(startfeld.getSpielfigur(), zielfeld);
				// }
			} else if (startfeld.getPosX() - 1 == zielfeld.getPosX() && startfeld.getPosY() - 1 == zielfeld.getPosY()) { // Zielfeld:
																																																										// move
																																																										// left
																																																										// down
																																																										// not
																																																										// legit
				System.out.println("ungueltiger Zug!");
			} else if (startfeld.getPosX() + 1 == zielfeld.getPosX() && startfeld.getPosY() - 1 == zielfeld.getPosY()) {// Zielfeld:
																																																									// move
																																																									// right
																																																									// down
																																																									// not
																																																									// legit
				System.out.println("ungueltiger Zug!");
			}
		}
		if (player1.getFarbe() == FarbEnum.schwarz && startfeld.getSpielfigur().getZustand() == ZustandEnum.SStein) {
			if (startfeld.getPosX() - 1 == zielfeld.getPosX() && startfeld.getPosY() - 1 == zielfeld.getPosY()) {// Zielfeld:
																																																						// move
																																																						// left
																																																						// down
																																																						// legit
				// if(regelwerk.legitmove()){
				move(startfeld.getSpielfigur(), zielfeld);
				// }
			}
			if (startfeld.getPosX() + 1 == zielfeld.getPosX() && startfeld.getPosY() - 1 == zielfeld.getPosY()) {// Zielfeld:
																																																						// move
																																																						// right
																																																						// down
																																																						// legit
				// if(regelwerk.legitmove()){
				move(startfeld.getSpielfigur(), zielfeld);
				// }
			} else if (startfeld.getPosX() + 1 == zielfeld.getPosX() && startfeld.getPosY() + 1 == zielfeld.getPosY()) {// Zielfeld:
																																																									// move
																																																									// right
																																																									// up
																																																									// not
																																																									// legit
				System.out.println("ungueltiger Zug!");
			} else if (startfeld.getPosX() - 1 == zielfeld.getPosX() && startfeld.getPosY() + 1 == zielfeld.getPosY()) {// Zielfeld:
																																																									// move
																																																									// left
																																																									// up
																																																									// not
																																																									// legit
			} else if (startfeld.getPosX() == zielfeld.getPosX() && startfeld.getPosY() == zielfeld.getPosY()) {// Zielfeld=Startfeld
																																																					// syso,
																																																					// back
																																																					// to
																																																					// act.
				System.out.println("Startfeld ist gleiches Feld wie Zielfeld");
				// break;
			}
		}

		// }

	}

	private void playerRotation(Spieler player1, Spieler player2) {

		while (Siegkondition != true) {
			System.out.println(player1 + " ist am Zug!");
			act(player1);
			System.out.println(player2 + " ist am Zug!");
			act(player2);
		}

		System.out.println("Spiel zuende!"); // Sieger ermitteln
	}

	private Spielfeld EingabeSpielfeld(String coord) {
		char Stelle1 = coord.charAt(0);
		int Stelle2 = coord.charAt(1);
		int Stelle1X = ((int) Stelle1) - 97;
		int Stelle2Y = Stelle2 - 49;
		Spielfeld[][] spielfelder = spielbrett.getBrett();

		Spielfeld spielfeld = spielfelder[Stelle2Y][Stelle1X];
		return spielfeld;
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

	public void move(Spielfigur stein1, Spielfeld posxy) {

		posxy.getSpielfigur().setZustand(stein1.getZustand());
		posxy.getSpielfigur().setFarbe(stein1.getFarbe());
		stein1.setFarbe(FarbEnum.nichts);
		stein1.setZustand(ZustandEnum.nichts);

		System.out.println("Zug von: " + posxy.getSpielfigur().getFarbe() + ",(" + posxy.getSpielfigur().getZustand() + ") " + stein1.getPosition().getSchachNotation() + " -> " + posxy.getSchachNotation());
		System.out.println(spielbrett);
	}

	public int getSpielerBisher() {
		return spielerBisher;
	}

	public void setSpielerBisher(int spielerBisher) {
		this.spielerBisher = spielerBisher;
	}

	public SpielBrett getSpielbrett() {
		return spielbrett;
	}

	public void setSpielbrett(SpielBrett spielbrett) {
		this.spielbrett = spielbrett;
	}

	public boolean isSiegkondition() {
		return Siegkondition;
	}

	public void setSiegkondition(boolean siegkondition) {
		Siegkondition = siegkondition;
	}
}
