package backend;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import javax.management.RuntimeErrorException;

public class Spiel implements iBediener, Serializable {

	private SpielBrett spielbrett;

	private static final int spielerMax = 2;

	private Spieler[] spieler = new Spieler[spielerMax];

	private Spieler activeSpieler;

	/**
	 * Konstruktor, ruft SpielBrett Konstruktor auf, und ruft
	 * this.welcome()Methode zum Spielanfang auf. Fuer End-User
	 */
	public Spiel() {
		this.setSpielbrett(new SpielBrett());
		this.welcome();

	}

	/**
	 * Spielinitialisierung, fragt die Namen ab und generiert Spieler fuer den
	 * Spielbeginn und Spielbrettausgabe+ Fuegt Spieler in den Spieler Array
	 * hinzu.
	 *
	 * @param Scanner
	 *          scanner, String name1, String name2 creates 2 Spieler objects and
	 *          calls this.add method with each of them one after another.
	 */
	@Override
	public void welcome() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Spiel laden [L] oder neues Spiel spielen [n].\n" + "Der aktuelle Spielstand kann jederzeit über die Eingabe [s] gespeichert werden");
		String eingabe = scanner.nextLine();
		if ("l".equals(eingabe) == true) {
			System.out.println("Dateinamen eingeben.");
			String filename = scanner.nextLine();
			System.out.println("Speichertyp [csv] oder [ser] eingeben.");
			String typ = scanner.nextLine();
			this.laden("", filename, typ);

		} else if ("n".equals(eingabe) == true) {

			System.out.println("Name Spieler 1:");
			String name1 = scanner.next();
			System.out.println("KI? - y/n");
			while (!scanner.hasNext("[yn]")) {
				System.out.println("KI? - y/n");
				scanner.next();
			}
			String ki1 = scanner.next();
			boolean ki1erstellen = false;

			if (ki1.equals("y")) {
				ki1erstellen = true;
			}

			Spieler player1 = new Spieler(name1, FarbEnum.weiss, ki1erstellen);

			add(player1);
			System.out.println(player1 + " hinzugefuegt!");
			System.out.println("Name Spieler 2:");
			String name2 = scanner.next();
			System.out.println("KI? - y/n");
			while (!scanner.hasNext("[yn]")) {
				System.out.println("KI? - y/n");
				scanner.next();
			}
			String ki2 = scanner.next();
			boolean ki2erstellen = false;
			if (ki2.equals("y")) {
				ki2erstellen = true;
			}

			Spieler player2 = new Spieler(name2, FarbEnum.schwarz, ki2erstellen);

			add(player2);
			System.out.println(player2 + " hinzugefuegt!");

			System.out.println("Moege das Spiel beginnen!");

			System.out.println(spielbrett);
			System.out.println(player1.getName() + " - " + "'" + player1.getFarbe() + "'" + " faengt an!");
			scanner.reset();
			playerRotation(player1, player2);
		}
	}

	/**
	 * Beinhaltet Eingabenbehandlungen und Methodenaufrufe, wie auch vorerst die
	 * Startfeld, Zielfeldsetzung
	 * 
	 */
	@Override
	public void act(Spieler player1) {
		if(AnyMovesLeft(player1)==true){
		if (player1.getKi() != null) {
			continueEnter("Ki am Zug druecke Enter");
			Spielfeld kiarray[] = new Spielfeld[2];
			kiarray = player1.getKi().kiAct(spielbrett.getBrett());
			Spielfeld startfeld = kiarray[0];
			Spielfeld zielfeld = kiarray[1];
			if (doTheMove(player1, startfeld, zielfeld) == false) {
				System.out.println("ungueltiger Zug!");
				throw new RuntimeErrorException(null, "KI BAUT MIST.");
			}
		} else {
			Spielfeld spielerFeldArray[] = new Spielfeld[2];
			spielerFeldArray = eingabeSpielfeldSpieler(player1);
			Spielfeld startfeld = spielerFeldArray[0];
			Spielfeld zielfeld = spielerFeldArray[1];
			boolean zugdone = false;
			while (zugdone == false) {
				if (doTheMove(player1, startfeld, zielfeld) == false) {
					System.out.println("ungueltiger Zug!");

				}
				zugdone = true;
			}

		}

		}if (player1.equals(this.getSpieler()[0])) {
			announceWinner(this.getSpieler()[0]);
		} // Schaut ob beim anderen Spieler keine
		//  noch Zuege moeglich sind.
		else{
			announceWinner(this.getSpieler()[1]);}}

	/**
	 * Will "Enter" zum fortfahren.
	 * 
	 * @param message
	 */
	private void continueEnter(String message) {
		System.out.println(message);
		Scanner keyboard = new Scanner(System.in);
		keyboard.nextLine();
	}

	/**
	 * Fragt Spieler nach dem Start und Zielfeld ab
	 * 
	 * @param player1
	 * @return Spielfeld[2] Start/Zielfeld
	 */
	@Override
	public Spielfeld[] eingabeSpielfeldSpieler(Spieler player1) {
		boolean actdone = false;
		while (actdone != true) {// doppelte While-Schleife fuer breaks;
			while (actdone != true) {
				Spielfeld spielerFeldArray[] = new Spielfeld[2];

				Scanner scanner = new Scanner(System.in);

				System.out.println("Eingabe Start, Ziel getrennt mit '-'");
				String szeingabe = scanner.nextLine();
				
				if ("s".equals(szeingabe) == true) {
					System.out.println("Dateinamen eingeben");
					String filename = scanner.nextLine();
					System.out.println("Speichertyp [csv] oder [ser] eingeben.");
					String typ = scanner.nextLine();
					this.speichern("", filename, typ);

				}

				else {
				
					
				String[] startZiel = new String[2];
				startZiel = szeingabe.split("-", 2);
				if (startZiel[0] == null) {
					System.out.println("Gebe Bitte Start und Zielfeld ein, getrennt mit einem '-'\t z.B. g5-h6");
					break;
				}
				if (startZiel.length == 1) {
					System.out.println("Gebe Bitte Start und Zielfeld ein, getrennt mit einem '-'\t z.B. g5-h6");
					break;
				}
				System.out.println(startZiel[0] + startZiel[1]);
				String coorda = startZiel[0];
				if (checkLegitFeld(coorda) == false) {
					System.out.println("Das ist kein Feld, versuche es erneut!");
					break;
				}
				Spielfeld startfeld = EingabeSpielfeld(coorda);
				if (startfeld.getSpielfigur() == null) {
					System.out.println("Keine Spielfigur auf dem Spielfeld " + coorda + "!");
					break;
				}

				if (startfeld.getSpielfigur() != null) {
					while (!startfeld.getSpielfigur().getFarbe().equals(player1.getFarbe())) {
						System.out.println("kein " + player1.getFarbe() + "er Spielstein!");
						break;
					}
				}
				String coordb = startZiel[1];

				if (checkLegitFeld(coordb) == false) {
					System.out.println("Das ist kein Feld, versuche es erneut!");
					break;
				}
				Spielfeld zielfeld = EingabeSpielfeld(coordb);
				if (startfeld.equals(zielfeld)) {
					System.out.println("Startfeld = Zielfeld, ungueltiger Zug");
					break;

				}

				if (zielfeld.getSpielfigur() != null) {
					System.out.println("Auf dem Zielfeld befindet sich bereits eine Spielfigur! Zug ungueltig.");
					break;
				}
				spielerFeldArray[0] = startfeld;
				spielerFeldArray[1] = zielfeld;
				actdone = true;
				return spielerFeldArray;
				}
			}
		}
		return null;
	}

	/**
	 * bekommt Spieler/Startfeld Zielfeld und ruft entsprechende move/schlag
	 * Methode auf.
	 * 
	 * @param player1
	 * @param startfeld
	 * @param zielfeld
	 * @return
	 */
	public boolean doTheMove(Spieler player1, Spielfeld startfeld, Spielfeld zielfeld) {
		if (startfeld.getSpielfigur().isDame() == true) {
			if (moveDameLegit(player1, startfeld, zielfeld) == true) {
				move(startfeld, zielfeld);
				return true;
			}
			if (schlagenDameLegit(player1, startfeld, zielfeld) == true) {
				schlagenDame(player1, startfeld, zielfeld);
				if (schlagMoeglichDame(zielfeld) == true) {
					askSchlagen(player1, zielfeld);
				}
			} else {

				return false;
			}
		} else if (startfeld.getSpielfigur().isDame() == false) {

			if (doMoveStein(player1, startfeld, zielfeld) == false) {

				if (doSchlagStein(player1, startfeld, zielfeld) == true) {
					if (SchlagMoeglich(zielfeld) == true) {
						askSchlagen(player1, zielfeld);

					}
				} else {

					return false;
				}

			}
		}
		return true;
	}

	/**
	 * Untersucht Spielfigur mit dame=true auf startfeld, ob diese ein weiteres
	 * mal schlagen kann
	 * 
	 * @param startfeld
	 * @return
	 */
	private boolean schlagMoeglichDame(Spielfeld startfeld) {

		if (nochSteineaufBrettandererSpieler(this.getSpieler()[0]) == false) {
			announceWinner(this.getSpieler()[0]);
		}
		if (nochSteineaufBrettandererSpieler(this.getSpieler()[1]) == false) {
			announceWinner(this.getSpieler()[1]);
		}
		try {
			for (int i = 1; i < spielbrett.getBrett().length - 2; i++) {
				if (spielbrett.getBrett()[startfeld.getPosY() + i][startfeld.getPosX() + i].getSpielfigur() != null) {
					if (spielbrett.getBrett()[startfeld.getPosY() + i][startfeld.getPosX() + i].getSpielfigur().getFarbe() == spielbrett.getBrett()[startfeld.getPosY()][startfeld.getPosX()].getSpielfigur().getFarbe()) {
						break;
					}
					if (spielbrett.getBrett()[startfeld.getPosY() + i + 1][startfeld.getPosX() + i + 1].getSpielfigur() != null) {
						break;
					}
					if (spielbrett.getBrett()[startfeld.getPosY() + i + 1][startfeld.getPosX() + i + 1].getSpielfigur() == null) {
						if (startfeld.getSpielfigur().getFarbe() != spielbrett.getBrett()[startfeld.getPosY() + i][startfeld.getPosX() + i].getSpielfigur().getFarbe()) {
							return true;
						}
					}
				}
			}
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {

		}
		try {
			for (int i = 1; i < spielbrett.getBrett().length - 2; i++) {
				if (spielbrett.getBrett()[startfeld.getPosY() - i][startfeld.getPosX() + i].getSpielfigur() != null) {
					if (spielbrett.getBrett()[startfeld.getPosY() - i][startfeld.getPosX() + i].getSpielfigur().getFarbe() == spielbrett.getBrett()[startfeld.getPosY()][startfeld.getPosX()].getSpielfigur().getFarbe()) {
						break;
					}
					if (spielbrett.getBrett()[startfeld.getPosY() - i - 1][startfeld.getPosX() + i + 1].getSpielfigur() != null) {
						break;
					}
					if (spielbrett.getBrett()[startfeld.getPosY() - i - 1][startfeld.getPosX() + i + 1].getSpielfigur() == null) {
						if (startfeld.getSpielfigur().getFarbe() != spielbrett.getBrett()[startfeld.getPosY() - i][startfeld.getPosX() + i].getSpielfigur().getFarbe()) {
							return true;
						}
					}
				}
			}
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {

		}
		try {
			for (int i = 1; i < spielbrett.getBrett().length - 2; i++) {
				if (spielbrett.getBrett()[startfeld.getPosY() + i][startfeld.getPosX() - i].getSpielfigur() != null) {
					if (spielbrett.getBrett()[startfeld.getPosY() + i][startfeld.getPosX() - i].getSpielfigur().getFarbe() == spielbrett.getBrett()[startfeld.getPosY()][startfeld.getPosX()].getSpielfigur().getFarbe()) {
						break;
					}
					if (spielbrett.getBrett()[startfeld.getPosY() + i + 1][startfeld.getPosX() - i - 1].getSpielfigur() != null) {
						break;
					}
					if (spielbrett.getBrett()[startfeld.getPosY() + i + 1][startfeld.getPosX() - i - 1].getSpielfigur() == null) {
						if (startfeld.getSpielfigur().getFarbe() != spielbrett.getBrett()[startfeld.getPosY() + i][startfeld.getPosX() - i].getSpielfigur().getFarbe()) {
							return true;
						}
					}
				}
			}
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {

		}
		try {
			for (int i = 1; i < spielbrett.getBrett().length - 2; i++) {
				if (spielbrett.getBrett()[startfeld.getPosY() - i][startfeld.getPosX() - i].getSpielfigur() != null) {
					if (spielbrett.getBrett()[startfeld.getPosY() -i][startfeld.getPosX() - i].getSpielfigur().getFarbe() == spielbrett.getBrett()[startfeld.getPosY()][startfeld.getPosX()].getSpielfigur().getFarbe()) {
						break;
					}
					if (spielbrett.getBrett()[startfeld.getPosY() - i - 1][startfeld.getPosX() - i - 1].getSpielfigur() != null) {
						break;
					}
					if (spielbrett.getBrett()[startfeld.getPosY() - i - 1][startfeld.getPosX() - i - 1].getSpielfigur() == null) {
						if (startfeld.getSpielfigur().getFarbe() != spielbrett.getBrett()[startfeld.getPosY() - i][startfeld.getPosX() - i].getSpielfigur().getFarbe()) {
							return true;
						}
					}
				}
			}
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {

		}
		return false;
	}

	/**
	 * ruft dameStartziel auf,die die Richtung in welche die Dame schlagen
	 * moechte, zurueck gibt. ruft dann schlagen auf.
	 * 
	 * @param player1
	 * @param startfeld
	 * @param zielfeld
	 */
	private void schlagenDame(Spieler player1, Spielfeld startfeld, Spielfeld zielfeld) {
		// Flying Kings implementieren?
		if (dameStartZiel(player1, startfeld, zielfeld) == "top-right") {
			schlagen(startfeld, zielfeld, spielbrett.getBrett()[zielfeld.getPosY() - 1][zielfeld.getPosX() - 1]);
		}
		if (dameStartZiel(player1, startfeld, zielfeld) == "top-left") {
			schlagen(startfeld, zielfeld, spielbrett.getBrett()[zielfeld.getPosY() - 1][zielfeld.getPosX() + 1]);
		}
		if (dameStartZiel(player1, startfeld, zielfeld) == "bottom-right") {
			schlagen(startfeld, zielfeld, spielbrett.getBrett()[zielfeld.getPosY() + 1][zielfeld.getPosX() - 1]);
		}
		if (dameStartZiel(player1, startfeld, zielfeld) == "bottom-left") {
			schlagen(startfeld, zielfeld, spielbrett.getBrett()[zielfeld.getPosY() + 1][zielfeld.getPosX() + 1]);
		}

	}

	/**
	 * Ueberprueft ob die Dameschlagen kann
	 * 
	 * @param player1
	 * @param startfeld
	 * @param zielfeld
	 * @return boolean
	 */
	private boolean schlagenDameLegit(Spieler player1, Spielfeld startfeld, Spielfeld zielfeld) {
		if (dameStartZiel(player1, startfeld, zielfeld) == "top-right") {
			for (int i = 1; i < spielbrett.getBrett().length - 2; i++) {
				try {
					if (spielbrett.getBrett()[startfeld.getPosY() + i][startfeld.getPosX() + i].getSpielfigur() != null) {
						if (spielbrett.getBrett()[startfeld.getPosY() + i][startfeld.getPosX() + i].getSpielfigur().getFarbe() == spielbrett.getBrett()[startfeld.getPosY()][startfeld.getPosX()].getSpielfigur().getFarbe()) {
							break;
						}
						if (spielbrett.getBrett()[startfeld.getPosY() + i + 1][startfeld.getPosX() + i + 1].getSpielfigur() != null) {
							break;
						}
						if (spielbrett.getBrett()[startfeld.getPosY() + i + 1][startfeld.getPosX() + i + 1].getSpielfigur() == null && spielbrett.getBrett()[startfeld.getPosY() + i + 1][startfeld.getPosX() + i + 1] == zielfeld) {
							if (startfeld.getSpielfigur().getFarbe() != spielbrett.getBrett()[startfeld.getPosY() + i][startfeld.getPosX() + i].getSpielfigur().getFarbe()) {
								return true;
							}
						} else {
							return false;
						}
					}
				} catch (java.lang.ArrayIndexOutOfBoundsException e) {
					return false;
				}
			}
		}
		if (dameStartZiel(player1, startfeld, zielfeld) == "top-left") {
			for (int i = 1; i < spielbrett.getBrett().length - 2; i++) {
				try {
					if (spielbrett.getBrett()[startfeld.getPosY() + i][startfeld.getPosX() - i].getSpielfigur() != null) {
						if (spielbrett.getBrett()[startfeld.getPosY() + i][startfeld.getPosX() - i].getSpielfigur().getFarbe() == spielbrett.getBrett()[startfeld.getPosY()][startfeld.getPosX()].getSpielfigur().getFarbe()) {
							break;
						}
						if (spielbrett.getBrett()[startfeld.getPosY() + i + 1][startfeld.getPosX() - i - 1].getSpielfigur() != null) {
							break;
						}
						if (spielbrett.getBrett()[startfeld.getPosY() + i + 1][startfeld.getPosX() - i - 1].getSpielfigur() == null && spielbrett.getBrett()[startfeld.getPosY() + i + 1][startfeld.getPosX() - i - 1] == zielfeld) {
							if (startfeld.getSpielfigur().getFarbe() != spielbrett.getBrett()[startfeld.getPosY() + i][startfeld.getPosX() - i].getSpielfigur().getFarbe()) {
								return true;
							}
						} else {
							return false;
						}
					}
				} catch (java.lang.ArrayIndexOutOfBoundsException e) {
					return false;
				}
			}
		}
		if (dameStartZiel(player1, startfeld, zielfeld) == "bottom-right") {
			for (int i = 1; i < spielbrett.getBrett().length - 2; i++) {
				try {
					if (spielbrett.getBrett()[startfeld.getPosY() - i][startfeld.getPosX() + i].getSpielfigur() != null) {
						if (spielbrett.getBrett()[startfeld.getPosY() - i][startfeld.getPosX() + i].getSpielfigur().getFarbe() == spielbrett.getBrett()[startfeld.getPosY()][startfeld.getPosX()].getSpielfigur().getFarbe()) {
							break;
						}
						if (spielbrett.getBrett()[startfeld.getPosY() - i - 1][startfeld.getPosX() + i + 1].getSpielfigur() != null) {
							break;
						}
						if (spielbrett.getBrett()[startfeld.getPosY() - i - 1][startfeld.getPosX() + i + 1].getSpielfigur() == null && spielbrett.getBrett()[startfeld.getPosY() - i - 1][startfeld.getPosX() + i + 1] == zielfeld) {
							if (startfeld.getSpielfigur().getFarbe() != spielbrett.getBrett()[startfeld.getPosY() - i][startfeld.getPosX() + i].getSpielfigur().getFarbe()) {
								return true;
							}
						} else {
							return false;
						}
					}
				} catch (java.lang.ArrayIndexOutOfBoundsException e) {
					return false;
				}
			}
		}
		if (dameStartZiel(player1, startfeld, zielfeld) == "bottom-left") {
			for (int i = 1; i < spielbrett.getBrett().length - 2; i++) {
				try {
					if (spielbrett.getBrett()[startfeld.getPosY() - i][startfeld.getPosX() - i].getSpielfigur() != null) {
						if (spielbrett.getBrett()[startfeld.getPosY() - i][startfeld.getPosX() - i].getSpielfigur().getFarbe() == spielbrett.getBrett()[startfeld.getPosY()][startfeld.getPosX()].getSpielfigur().getFarbe()) {
							break;
						}
						if (spielbrett.getBrett()[startfeld.getPosY() - i - 1][startfeld.getPosX() - i - 1].getSpielfigur() != null) {
							break;
						}
						if (spielbrett.getBrett()[startfeld.getPosY() - i - 1][startfeld.getPosX() - i - 1].getSpielfigur() == null && spielbrett.getBrett()[startfeld.getPosY() - i - 1][startfeld.getPosX() - i - 1] == zielfeld) {
							if (startfeld.getSpielfigur().getFarbe() != spielbrett.getBrett()[startfeld.getPosY() - i][startfeld.getPosX() - i].getSpielfigur().getFarbe()) {
								return true;
							}
						} else {
							return false;
						}
					}
				} catch (java.lang.ArrayIndexOutOfBoundsException e) {
					return false;
				}
			}
		}
		return false;
	}

	/**
	 * Ueberprueft ob Dame sich bewegen kann von startfeld auf zielfeld.
	 * 
	 * @param player1
	 * @param startfeld
	 * @param zielfeld
	 * @return boolean
	 */
	private boolean moveDameLegit(Spieler player1, Spielfeld startfeld, Spielfeld zielfeld) {
		if (dameStartZiel(player1, startfeld, zielfeld) == "nope") {
			return false;
		}

		if (dameStartZiel(player1, startfeld, zielfeld) == "top-right") {

			for (int i = 1; i < spielbrett.getBrett().length - 1; i++) {
				if (spielbrett.getBrett()[startfeld.getPosY() + i][startfeld.getPosX() + i].getSpielfigur() == null) {
					if (spielbrett.getBrett()[startfeld.getPosY() + i][startfeld.getPosX() + i] == zielfeld) {
						return true;
					}
				} else {
					return false;
				}
			}
		}
		if (dameStartZiel(player1, startfeld, zielfeld) == "top-left") {
			for (int i = 1; i < spielbrett.getBrett().length - 1; i++) {
				if (spielbrett.getBrett()[startfeld.getPosY() + i][startfeld.getPosX() - i].getSpielfigur() == null) {
					if (spielbrett.getBrett()[startfeld.getPosY() + i][startfeld.getPosX() - i] == zielfeld) {
						return true;
					}
				} else {
					return false;
				}
			}
		}
		if (dameStartZiel(player1, startfeld, zielfeld) == "bottom-right") {
			for (int i = 1; i < spielbrett.getBrett().length - 1; i++) {
				if (spielbrett.getBrett()[startfeld.getPosY() - i][startfeld.getPosX() + i].getSpielfigur() == null) {
					if (spielbrett.getBrett()[startfeld.getPosY() - i][startfeld.getPosX() + i] == zielfeld) {
						return true;
					}
				} else {
					return false;
				}
			}
		}
		if (dameStartZiel(player1, startfeld, zielfeld) == "bottom-left") {
			for (int i = 1; i < spielbrett.getBrett().length - 1; i++) {
				if (spielbrett.getBrett()[startfeld.getPosY() - i][startfeld.getPosX() - i].getSpielfigur() == null) {
					if (spielbrett.getBrett()[startfeld.getPosY() - i][startfeld.getPosX() - i] == zielfeld) {
						return true;
					}
				} else {
					return false;
				}
			}
		}
		return false;
	}

	/**
	 * Findet heraus in welche Richtung der Spieler mit der Dame ziehen moechte.
	 * 
	 * @param player1
	 * @param startfeld
	 * @param zielfeld
	 * @return String top-right, top-left, bottom-right, bottom-left
	 */
	private String dameStartZiel(Spieler player1, Spielfeld startfeld, Spielfeld zielfeld) {
		for (int i = 1; i < spielbrett.getBrett().length - 1; i++) {
			try {

				if (startfeld.getPosX() + i == zielfeld.getPosX() && startfeld.getPosY() + i == zielfeld.getPosY()) {
					return "top-right";
				}
			} catch (java.lang.ArrayIndexOutOfBoundsException e) {
			}
			try {

				if (startfeld.getPosX() - i == zielfeld.getPosX() && startfeld.getPosY() + i == zielfeld.getPosY()) {
					return "top-left";
				}
			} catch (java.lang.ArrayIndexOutOfBoundsException e) {
			}
			try {

				if (startfeld.getPosX() + i == zielfeld.getPosX() && startfeld.getPosY() - i == zielfeld.getPosY()) {
					return "bottom-right";
				}
			} catch (java.lang.ArrayIndexOutOfBoundsException e) {
			}
			try {

				if (startfeld.getPosX() - i == zielfeld.getPosX() && startfeld.getPosY() - i == zielfeld.getPosY()) {
					return "bottom-left";
				}
			} catch (java.lang.ArrayIndexOutOfBoundsException e) {
			}
		}

		return "nope";
	}

	/**
	 * Fragt End-User ob wohin er schlagen moechte nach einem bereits
	 * durchfuehrten schlag
	 * 
	 * @param player1
	 * @param startfeld
	 */
	@Override
	public void askSchlagen(Spieler player1, Spielfeld startfeld) {

		boolean askdone = false;
		while (askdone == false) {
			while (askdone == false) {
				Spielfeld zielfeld;
				if (player1.getKi() == null) {
					System.out.println(startfeld.getSchachNotation() + startfeld.getSpielfigur() + " kann noch einmal schlagen");
					Scanner scanner = new Scanner(System.in);
					System.out.println("Eingabe Zielfeld:");
					String coordb = scanner.nextLine();
					if (checkLegitFeld(coordb) == false) {
						System.out.println("Das ist kein Feld, versuche es erneut!");
						break;
					}
					zielfeld = EingabeSpielfeld(coordb);
					if (startfeld.equals(zielfeld)) {
						System.out.println("Startfeld = Zielfeld, ungueltiger Zug");
						break;

					}

					if (zielfeld.getSpielfigur() != null) {
						System.out.println("Auf dem Zielfeld befindet sich bereits eine Spielfigur! Zug ungueltig.");
						break;
					}

				} else {
					zielfeld = player1.getKi().actAgain(startfeld, spielbrett.getBrett()); // KI

				}
				if (startfeld.getSpielfigur().isDame() == false) {
					if (doSchlagStein(player1, startfeld, zielfeld) == true) {

						if (SchlagMoeglich(zielfeld) == true) {
							askSchlagen(player1, zielfeld);

						} else {

						}
					} else {
						break;
					}
				} else if (startfeld.getSpielfigur().isDame() == true) {
					if (schlagenDameLegit(player1, startfeld, zielfeld) == true) {
						schlagenDame(player1, startfeld, zielfeld);
						if (schlagMoeglichDame(zielfeld) == true) {
							askSchlagen(player1, zielfeld);
						}
					} else {

						break;
					}
				}
				askdone = true;
			}
		}
	}

	/**
	 * Untersucht, ob der gegebene Stein auf Startfeld Schlagmoeglichkeiten
	 * besitzt.
	 * 
	 * @param player1
	 * @param startfeld
	 * @return
	 */
	private boolean SchlagMoeglich(Spielfeld startfeld) {
		if (startfeld.getSpielfigur() != null) {
			if (startfeld.getSpielfigur().getFarbe() == FarbEnum.weiss) {

				if (startfeld.getSpielfigur().getFarbe() == FarbEnum.weiss && startfeld.getSpielfigur().isDame() == false) {
					try {
						if (spielbrett.getBrett()[startfeld.getPosY() + 1][startfeld.getPosX() + 1] != null) {
							if (spielbrett.getBrett()[startfeld.getPosY() + 1][startfeld.getPosX() + 1].getSpielfigur() != null && spielbrett.getBrett()[startfeld.getPosY() + 1][startfeld.getPosX() + 1].getSpielfigur().getFarbe() == FarbEnum.schwarz) {
								if (spielbrett.getBrett()[startfeld.getPosY() + 2][startfeld.getPosX() + 2] != null) {
									if (spielbrett.getBrett()[startfeld.getPosY() + 2][startfeld.getPosX() + 2].getSpielfigur() == null) {
										return true;
									}
								}
							}
						}
					} catch (java.lang.ArrayIndexOutOfBoundsException e) {

					}
					try {
						if (spielbrett.getBrett()[startfeld.getPosY() - 1][startfeld.getPosX() - 1] != null) {
							if (spielbrett.getBrett()[startfeld.getPosY() - 1][startfeld.getPosX() - 1].getSpielfigur() != null && spielbrett.getBrett()[startfeld.getPosY() - 1][startfeld.getPosX() - 1].getSpielfigur().getFarbe() == FarbEnum.schwarz) {
								if (spielbrett.getBrett()[startfeld.getPosY() - 2][startfeld.getPosX() - 2] != null) {
									if (spielbrett.getBrett()[startfeld.getPosY() - 2][startfeld.getPosX() - 2].getSpielfigur() == null) {
										return true;
									}
								}
							}
						}
					} catch (java.lang.ArrayIndexOutOfBoundsException e) {

					}
					try {
						if (spielbrett.getBrett()[startfeld.getPosY() - 1][startfeld.getPosX() + 1] != null) {
							if (spielbrett.getBrett()[startfeld.getPosY() - 1][startfeld.getPosX() + 1].getSpielfigur() != null && spielbrett.getBrett()[startfeld.getPosY() - 1][startfeld.getPosX() + 1].getSpielfigur().getFarbe() == FarbEnum.schwarz) {
								if (spielbrett.getBrett()[startfeld.getPosY() - 2][startfeld.getPosX() + 2] != null) {
									if (spielbrett.getBrett()[startfeld.getPosY() - 2][startfeld.getPosX() + 2].getSpielfigur() == null) {
										return true;
									}
								}
							}
						}
					} catch (java.lang.ArrayIndexOutOfBoundsException e) {

					}
					try {
						if (spielbrett.getBrett()[startfeld.getPosY() + 1][startfeld.getPosX() - 1] != null) {
							if (spielbrett.getBrett()[startfeld.getPosY() + 1][startfeld.getPosX() - 1].getSpielfigur() != null && spielbrett.getBrett()[startfeld.getPosY() + 1][startfeld.getPosX() - 1].getSpielfigur().getFarbe() == FarbEnum.schwarz) {
								if (spielbrett.getBrett()[startfeld.getPosY() + 2][startfeld.getPosX() - 2] != null) {
									if (spielbrett.getBrett()[startfeld.getPosY() + 2][startfeld.getPosX() - 2].getSpielfigur() == null) {
										return true;
									}
								}
							}

						}
					} catch (java.lang.ArrayIndexOutOfBoundsException e) {

					}
				}
			}

			if (startfeld.getSpielfigur().getFarbe() == FarbEnum.schwarz && startfeld.getSpielfigur().isDame() == false) {

				try {
					if (spielbrett.getBrett()[startfeld.getPosY() + 1][startfeld.getPosX() + 1] != null) {
						if (spielbrett.getBrett()[startfeld.getPosY() + 1][startfeld.getPosX() + 1].getSpielfigur() != null && spielbrett.getBrett()[startfeld.getPosY() + 1][startfeld.getPosX() + 1].getSpielfigur().getFarbe() == FarbEnum.weiss) {
							if (spielbrett.getBrett()[startfeld.getPosY() + 2][startfeld.getPosX() + 2] != null) {
								if (spielbrett.getBrett()[startfeld.getPosY() + 2][startfeld.getPosX() + 2].getSpielfigur() == null) {
									return true;
								}
							}
						}
					}
				} catch (java.lang.ArrayIndexOutOfBoundsException e) {

				}
				try {
					if (spielbrett.getBrett()[startfeld.getPosY() - 1][startfeld.getPosX() - 1] != null) {
						if (spielbrett.getBrett()[startfeld.getPosY() - 1][startfeld.getPosX() - 1].getSpielfigur() != null && spielbrett.getBrett()[startfeld.getPosY() - 1][startfeld.getPosX() - 1].getSpielfigur().getFarbe() == FarbEnum.weiss) {
							if (spielbrett.getBrett()[startfeld.getPosY() - 2][startfeld.getPosX() - 2] != null) {
								if (spielbrett.getBrett()[startfeld.getPosY() - 2][startfeld.getPosX() - 2].getSpielfigur() == null) {
									return true;
								}
							}
						}
					}
				} catch (java.lang.ArrayIndexOutOfBoundsException e) {

				}
				try {
					if (spielbrett.getBrett()[startfeld.getPosY() - 1][startfeld.getPosX() + 1] != null) {
						if (spielbrett.getBrett()[startfeld.getPosY() - 1][startfeld.getPosX() + 1].getSpielfigur() != null && spielbrett.getBrett()[startfeld.getPosY() - 1][startfeld.getPosX() + 1].getSpielfigur().getFarbe() == FarbEnum.weiss) {
							if (spielbrett.getBrett()[startfeld.getPosY() - 2][startfeld.getPosX() + 2] != null) {
								if (spielbrett.getBrett()[startfeld.getPosY() - 2][startfeld.getPosX() + 2].getSpielfigur() == null) {
									return true;
								}
							}
						}
					}
				} catch (java.lang.ArrayIndexOutOfBoundsException e) {

				}
				try {
					if (spielbrett.getBrett()[startfeld.getPosY() + 1][startfeld.getPosX() - 1] != null) {
						if (spielbrett.getBrett()[startfeld.getPosY() + 1][startfeld.getPosX() - 1].getSpielfigur() != null && spielbrett.getBrett()[startfeld.getPosY() + 1][startfeld.getPosX() - 1].getSpielfigur().getFarbe() == FarbEnum.weiss) {
							if (spielbrett.getBrett()[startfeld.getPosY() + 2][startfeld.getPosX() - 2] != null) {
								if (spielbrett.getBrett()[startfeld.getPosY() + 2][startfeld.getPosX() - 2].getSpielfigur() == null) {
									return true;
								}
							}
						}
					}
				} catch (java.lang.ArrayIndexOutOfBoundsException e) {

				}
			}

		}
		return false;
	}

	/**
	 * Schaut ob der Spieler noch ziehen kann.
	 * 
	 * @param player1
	 * @return
	 */
	private boolean AnyMovesLeft(Spieler player1) {

		// Zaehler fuer moeglich uebrige Zuege.
		{
			for (int i = 0; i < spielbrett.getBrett().length - 1; i++) {

				for (int j = 0; j < spielbrett.getBrett()[i].length - 1; j++) {
					if (spielbrett.getBrett()[j][i].getSpielfigur() != null) {
						if (spielbrett.getBrett()[j][i].getSpielfigur().isDame() == false) {
							if (moveMoeglich(player1, spielbrett.getBrett()[j][i]) == true || SchlagMoeglich(spielbrett.getBrett()[j][i]) == true) {
								return true;
							}
						} else if (spielbrett.getBrett()[j][i].getSpielfigur().isDame() == true) {
							if ((moveMoeglich(player1, spielbrett.getBrett()[j][i]) == true || schlagMoeglichDame(spielbrett.getBrett()[j][i]) == true)) {
								return true;
							}
						}
					}
				}
			}
		}

		return false;
	}

	/**
	 * schaut ob die Spielfigur auf dem Spielfeld, falls vorhanden noch ziehen
	 * kann.
	 * 
	 * @param player1
	 * @param startfeld
	 * @return
	 */
	private boolean moveMoeglich(Spieler player1, Spielfeld startfeld) {
		if (nochSteineaufBrettandererSpieler(this.getSpieler()[0]) == false) {
			announceWinner(this.getSpieler()[1]);
		}
		if (nochSteineaufBrettandererSpieler(this.getSpieler()[1]) == false) {
			announceWinner(this.getSpieler()[0]);
		}

		if (startfeld.getSpielfigur() == null) {
			return false;
		}
		if (player1.getFarbe() == FarbEnum.weiss) {
			try {
				if (spielbrett.getBrett()[startfeld.getPosY() + 1][startfeld.getPosX() - 1] != null) {
					if (spielbrett.getBrett()[startfeld.getPosY() + 1][startfeld.getPosX() - 1].getSpielfigur() == null) {
						return true;
					}
				}

			} catch (java.lang.ArrayIndexOutOfBoundsException e) {

			}
			try {
				if (spielbrett.getBrett()[startfeld.getPosY() + 1][startfeld.getPosX() + 1] != null) {
					if (spielbrett.getBrett()[startfeld.getPosY() + 1][startfeld.getPosX() + 1].getSpielfigur() == null) {
						return true;
					}
				}
			} catch (java.lang.ArrayIndexOutOfBoundsException e) {

			}
			return false;
		}

		if (player1.getFarbe() == FarbEnum.schwarz) {
			try {

				if (spielbrett.getBrett()[startfeld.getPosY() - 1][startfeld.getPosX() - 1] != null) {
					if (spielbrett.getBrett()[startfeld.getPosY() - 1][startfeld.getPosX() - 1].getSpielfigur() == null) {
						return true;
					}
				}
			} catch (java.lang.ArrayIndexOutOfBoundsException e) {

			} finally {

			}

			try {
				if (spielbrett.getBrett()[startfeld.getPosY() - 1][startfeld.getPosX() + 1] != null) {
					if (spielbrett.getBrett()[startfeld.getPosY() - 1][startfeld.getPosX() + 1].getSpielfigur() == null) {
						return true;
					}
				}
			} catch (java.lang.ArrayIndexOutOfBoundsException e) {

			}

			return false;
		}
		return false;

	}

	/**
	 * Ueberprueft ob auf das gewuenschte Feld gezogen werden kann, und fuert dies
	 * dann auch aus.
	 * 
	 * @param player1
	 * @param startfeld
	 * @param zielfeld
	 * @return boolean
	 */
	private boolean doSchlagStein(Spieler player1, Spielfeld startfeld, Spielfeld zielfeld) {
		if (startfeld.getSpielfigur().getFarbe() == FarbEnum.weiss) {

			if (player1.getFarbe() == FarbEnum.weiss && startfeld.getSpielfigur().isDame() == false) {
				if (startfeld.getPosX() + 2 == zielfeld.getPosX() && startfeld.getPosY() + 2 == zielfeld.getPosY()) { // Zielfeld:Top
					// right
					// 1
					// Figur
					// ueberspringen
					if (spielbrett.getBrett()[startfeld.getPosY() + 1][startfeld.getPosX() + 1].getSpielfigur() != null && spielbrett.getBrett()[startfeld.getPosY() + 1][startfeld.getPosX() + 1].getSpielfigur().getFarbe() == FarbEnum.schwarz) {
						schlagen(startfeld, zielfeld, spielbrett.getBrett()[startfeld.getPosY() + 1][startfeld.getPosX() + 1]);
						return true;
					} else {

						return false;
					}
				} else if (startfeld.getPosX() - 2 == zielfeld.getPosX() && startfeld.getPosY() - 2 == zielfeld.getPosY()) { // Zielfeld:bottom
					// left
					// 1
					// Figur
					// ueberspringen
					if (spielbrett.getBrett()[startfeld.getPosY() - 1][startfeld.getPosX() - 1].getSpielfigur() != null && spielbrett.getBrett()[startfeld.getPosY() - 1][startfeld.getPosX() - 1].getSpielfigur().getFarbe() == FarbEnum.schwarz) {
						schlagen(startfeld, zielfeld, spielbrett.getBrett()[startfeld.getPosY() - 1][startfeld.getPosX() - 1]);
						return true;
					} else {

						return false;
					}
				} else if (startfeld.getPosX() + 2 == zielfeld.getPosX() && startfeld.getPosY() - 2 == zielfeld.getPosY()) { // Zielfeld:bottom
					// right
					// 1
					// Figur
					// ueberspringen
					if (spielbrett.getBrett()[startfeld.getPosY() - 1][startfeld.getPosX() + 1].getSpielfigur() != null && spielbrett.getBrett()[startfeld.getPosY() - 1][startfeld.getPosX() + 1].getSpielfigur().getFarbe() == FarbEnum.schwarz) {
						schlagen(startfeld, zielfeld, spielbrett.getBrett()[startfeld.getPosY() - 1][startfeld.getPosX() + 1]);
						return true;
					} else {

						return false;
					}
				} else if (startfeld.getPosX() - 2 == zielfeld.getPosX() && startfeld.getPosY() + 2 == zielfeld.getPosY()) { // Zielfeld:Top
					// left
					// 1
					// Figur
					// ueberspringen
					if (spielbrett.getBrett()[startfeld.getPosY() + 1][startfeld.getPosX() - 1].getSpielfigur() != null && spielbrett.getBrett()[startfeld.getPosY() + 1][startfeld.getPosX() - 1].getSpielfigur().getFarbe() == FarbEnum.schwarz) {
						schlagen(startfeld, zielfeld, spielbrett.getBrett()[startfeld.getPosY() + 1][startfeld.getPosX() - 1]);
						return true;
					} else {

						return false;
					}
				}
			}
		}
		if (player1.getFarbe() == FarbEnum.schwarz && startfeld.getSpielfigur().isDame() == false) {
			if (startfeld.getPosX() + 2 == zielfeld.getPosX() && startfeld.getPosY() + 2 == zielfeld.getPosY()) { // Zielfeld:Top
				// right
				// 1
				// Figur
				// ueberspringen
				if (spielbrett.getBrett()[startfeld.getPosY() + 1][startfeld.getPosX() + 1].getSpielfigur() != null && spielbrett.getBrett()[startfeld.getPosY() + 1][startfeld.getPosX() + 1].getSpielfigur().getFarbe() == FarbEnum.weiss) {
					schlagen(startfeld, zielfeld, spielbrett.getBrett()[startfeld.getPosY() + 1][startfeld.getPosX() + 1]);
					return true;
				} else {

					return false;
				}
			} else if (startfeld.getPosX() - 2 == zielfeld.getPosX() && startfeld.getPosY() - 2 == zielfeld.getPosY()) { // Zielfeld:bottom
				// left
				// 1
				// Figur
				// ueberspringen
				if (spielbrett.getBrett()[startfeld.getPosY() - 1][startfeld.getPosX() - 1].getSpielfigur() != null && spielbrett.getBrett()[startfeld.getPosY() - 1][startfeld.getPosX() - 1].getSpielfigur().getFarbe() == FarbEnum.weiss) {
					schlagen(startfeld, zielfeld, spielbrett.getBrett()[startfeld.getPosY() - 1][startfeld.getPosX() - 1]);
					return true;
				} else {

					return false;
				}
			} else if (startfeld.getPosX() + 2 == zielfeld.getPosX() && startfeld.getPosY() - 2 == zielfeld.getPosY()) { // Zielfeld:bottom
				// right
				// 1
				// Figur
				// ueberspringen
				if (spielbrett.getBrett()[startfeld.getPosY() - 1][startfeld.getPosX() + 1].getSpielfigur() != null && spielbrett.getBrett()[startfeld.getPosY() - 1][startfeld.getPosX() + 1].getSpielfigur().getFarbe() == FarbEnum.weiss) {
					schlagen(startfeld, zielfeld, spielbrett.getBrett()[startfeld.getPosY() - 1][startfeld.getPosX() + 1]);
					return true;
				} else {

					return false;
				}
			} else if (startfeld.getPosX() - 2 == zielfeld.getPosX() && startfeld.getPosY() + 2 == zielfeld.getPosY()) { // Zielfeld:Top
				// left
				// 1
				// Figur
				// ueberspringen
				if (spielbrett.getBrett()[startfeld.getPosY() + 1][startfeld.getPosX() - 1].getSpielfigur() != null && spielbrett.getBrett()[startfeld.getPosY() + 1][startfeld.getPosX() - 1].getSpielfigur().getFarbe() == FarbEnum.weiss) {
					schlagen(startfeld, zielfeld, spielbrett.getBrett()[startfeld.getPosY() + 1][startfeld.getPosX() - 1]);
					return true;
				} else {

					return false;
				}

			}

		}
		return false;
	}

	/**
	 * Ueberprueft Zug Moeglichkeiten, und ruft move methode auf, falls auf das
	 * gewuenschte Zielfeld gezogen werden kann.
	 * 
	 * @param player1
	 * @param startfeld
	 * @param zielfeld
	 * @return boolean
	 */
	private boolean doMoveStein(Spieler player1, Spielfeld startfeld, Spielfeld zielfeld) {
		if (startfeld.getSpielfigur().getFarbe() == FarbEnum.weiss) {

			if (player1.getFarbe() == FarbEnum.weiss && startfeld.getSpielfigur().isDame() == false) {
				if (startfeld.getPosX() + 1 == zielfeld.getPosX() && startfeld.getPosY() + 1 == zielfeld.getPosY()) {// Zielfeld:
					// move
					// right
					// up
					// legit

					move(startfeld, zielfeld);
					return true;

				} else if (startfeld.getPosX() - 1 == zielfeld.getPosX() && startfeld.getPosY() + 1 == zielfeld.getPosY()) {// Zielfeld:
					// move
					// left
					// up
					// legit

					move(startfeld, zielfeld);
					return true;

				} else if (startfeld.getPosX() - 1 == zielfeld.getPosX() && startfeld.getPosY() - 1 == zielfeld.getPosY()) { // Zielfeld:
																																																											// move
																																																											// left
																																																											// down
																																																											// not
																																																											// legit

					return false;
				} else if (startfeld.getPosX() + 1 == zielfeld.getPosX() && startfeld.getPosY() - 1 == zielfeld.getPosY()) {// Zielfeld:
																																																										// move
																																																										// right
																																																										// down
																																																										// not
																																																										// legit

					return false;
				} else if (startfeld.getPosX() == zielfeld.getPosX() || startfeld.getPosY() == zielfeld.getPosY()) {

					return false;
				}

			} else {

				return false;
			}
		}

		if (player1.getFarbe() == FarbEnum.schwarz && startfeld.getSpielfigur().isDame() == false) {
			if (startfeld.getPosX() - 1 == zielfeld.getPosX() && startfeld.getPosY() - 1 == zielfeld.getPosY()) {// Zielfeld:
																																																						// move
																																																						// left
																																																						// down
																																																						// legit

				move(startfeld, zielfeld);
				return true;

			} else if (startfeld.getPosX() + 1 == zielfeld.getPosX() && startfeld.getPosY() - 1 == zielfeld.getPosY()) {// Zielfeld:
				// move
				// right
				// down
				// legit

				move(startfeld, zielfeld);
				return true;

			} else if (startfeld.getPosX() + 1 == zielfeld.getPosX() && startfeld.getPosY() + 1 == zielfeld.getPosY()) {// Zielfeld:
																																																									// move
																																																									// right
																																																									// up
																																																									// not
																																																									// legit

				return false;
			} else if (startfeld.getPosX() - 1 == zielfeld.getPosX() && startfeld.getPosY() + 1 == zielfeld.getPosY()) {// Zielfeld:
																																																									// move
																																																									// left
				// up
				return false; // not
				// legit
			} else if (startfeld.getPosX() == zielfeld.getPosX() || startfeld.getPosY() == zielfeld.getPosY()) {

				return false;

			}

		}
		return false;
	}

	/**
	 * Ueberprueft die Eingabe, ob das Feld im Brett liegt. ueber die Zerteilung
	 * des Strings und der Stellenweisen Ueberpruefung
	 * 
	 * @param feld
	 * @return boolean
	 */
	private boolean checkLegitFeld(String feld) {
		if (feld.isEmpty() || feld.charAt(0) < 97 || feld.charAt(0) > 108) {// Nur
																																				// a->l
																																				// gueltig
			return false;
		} else if (feld.length() == 1) {// ungueltig, wenn nur ein Buchstabe
																		// eingegeben wird.
			return false;
		}
		if (feld.length() == 3) {
			if (feld.charAt(1) == 49) {
				if (feld.charAt(2) == 48 || feld.charAt(2) == 49 || feld.charAt(2) == 50) { // Nur
																																										// 10/11/12
																																										// gueltig
																																										// 1/2/3
																																										// an
																																										// 3.
																																										// Stelle
					return true;
				}
				if (feld.charAt(2) < 48 || feld.charAt(2) > 50) {
					return false;
				}
			}
		}
		if (feld.length() > 3) {// Ungueltig wenn String laenger als 3
			return false;
		}
		if (feld.charAt(1) < 49 || feld.charAt(1) > 57) { // Nur gueltig bei 0-9 in
																											// char Wert
			return false;
		} else {
			return true;
		}
	}

	/**
	 * bewegt Spielfigur auf Startfeld auf Zielfeld und setzt Spielfigur auf
	 * Startfeld und LoeschSteinAufFeld auf ZustandEnum.nichts.
	 * 
	 * @param startfeld
	 * @param zielfeld
	 * @param LoeschSteinAufFeld
	 */
	private void schlagen(Spielfeld startfeld, Spielfeld zielfeld, Spielfeld LoeschSteinAufFeld) {

		System.out.println("Zug von: " + startfeld.getSpielfigur().getFarbe() + ",(" + startfeld.getSpielfigur() + ") " + startfeld.getSchachNotation() + " -> " + zielfeld.getSchachNotation() + "   (" + LoeschSteinAufFeld.getSpielfigur() + ") auf Feld: " + LoeschSteinAufFeld.getSchachNotation()
				+ " geschlagen");

		zielfeld.setSpielfigur(startfeld.getSpielfigur());
		startfeld.setSpielfigur(null);
		LoeschSteinAufFeld.setSpielfigur(null);

		System.out.println(spielbrett);
		if (nochSteineaufBrettandererSpieler(this.getSpieler()[0]) == false) {
			announceWinner(this.getSpieler()[0]);
		}
		if (nochSteineaufBrettandererSpieler(this.getSpieler()[1]) == false) {
			announceWinner(this.getSpieler()[1]);
		}

	}

	/**
	 * Solange die Siegkondition false ist, wird abwechselnd gespielt(act) Nach
	 * jedem Act wird Siegkondition in eigener Methode geprueft. Wenn
	 * Siegkondition true wird der Sieger ernannt und nach einem neuen Spiel
	 * gefragt
	 * 
	 * 
	 * @param Spieler
	 *          player1, player2
	 */
	private void playerRotation(Spieler player1, Spieler player2) {

		int i = 0;
		while (i == 0) {
			this.setActiveSpieler(player1);
			System.out.println(player1 + " ist am Zug!");
			act(player1);
			checkAndGetDame(player1);
			if (checkSiegkondition(player2) == true) {
				i = 2;
			}
			System.out.println(player2 + " ist am Zug!");
			this.setActiveSpieler(player2);
			act(player2);
			checkAndGetDame(player2);
			if (checkSiegkondition(player1) == true) {
				i = 1;
			}

		}
		if (i == 2) {
			announceWinner(player1);
		} // Schaut ob beim anderen Spieler keine
		// Steine mehr uebrig sind, um zu gewinnen && ob noch Zuege moeglich sind.
		if (i == 1) {
			announceWinner(player2);

		}

	}

	/**
	 * Kuendigt den Gewinner an und fragt den Benutzer, ob ein neues Spiel
	 * gestartet werden soll.
	 * 
	 * @param player1
	 */
	private void announceWinner(Spieler player1) {
		System.out.println("Spiel zuende!");
		System.out.println(player1 + " hat gewonnen.");
		System.out.println("Neues Spiel? y/n?");
		Scanner c = new Scanner(System.in);
		String yesNo = c.nextLine();
		String no = "n";
		String yes = "y";
		boolean checkanswer = false;
		while (checkanswer != true) {
			if (yesNo.equals(no)) {
				System.out.println("Auf Wiedersehen!");
				System.exit(0);
			}
			if (yesNo.equals(yes)) {
				checkanswer = true;
				new Spiel();
			}
			System.out.println("Neues Spiel? y/n?");
			String tempc = c.nextLine();
			yesNo = tempc;

		}
	}

	/**
	 * Schaut ob player eine Dame nach seinem Zug bekommen sollte, falls ja wird
	 * wirdDame mit dem passenden Spielfeld aufgerufen.
	 * 
	 * @param player1
	 */
	private void checkAndGetDame(Spieler player1) {
		if (player1.getFarbe() == FarbEnum.weiss) {
			for (int i = 0; i < spielbrett.getBrett().length; i++) {
				if (spielbrett.getBrett()[11][i].getSpielfigur() != null && spielbrett.getBrett()[11][i].getSpielfigur().isDame() == false) {
					if (spielbrett.getBrett()[11][i].getSpielfigur().getFarbe() == FarbEnum.weiss) {
						wirdDame(spielbrett.getBrett()[11][i]);
					}
				}
			}
		}
		if (player1.getFarbe() == FarbEnum.schwarz) {
			for (int i = 0; i < spielbrett.getBrett().length; i++) {
				if (spielbrett.getBrett()[0][i].getSpielfigur() != null && spielbrett.getBrett()[0][i].getSpielfigur().isDame() == false) {
					if (spielbrett.getBrett()[0][i].getSpielfigur().getFarbe() == FarbEnum.schwarz) {
						wirdDame(spielbrett.getBrett()[0][i]);
					}
				}
			}
		}

	}

	/**
	 * Setzt Siegkondition auf true, wenn ein Spieler, keine Figuren mehr auf dem
	 * Feld hat.
	 * 
	 * @param player
	 */
	private boolean checkSiegkondition(Spieler player) { // Siegkondition, kann
																												// nicht
		// mehr ziehen fehlt noch.
		if (nochSteineaufBrettandererSpieler(player) == false) {
			return true;
		}
		if (AnyMovesLeft(player) == false) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Ueberprueft ob sich noch Steine des Spielers auf dem Brett befinden.
	 * 
	 * @param player
	 * @return
	 */
	private boolean nochSteineaufBrettandererSpieler(Spieler player) {
		if (player.equals(this.getSpieler()[0])) {
			player = this.getSpieler()[1];
		} else if (player.equals(this.getSpieler()[1])) {
			player = this.getSpieler()[0];
		}
		Spielfeld[][] spielfelder = spielbrett.getBrett();
		if (player.getFarbe() == FarbEnum.weiss) {
			for (int i = 0; i < spielfelder.length - 1; i++) {
				for (int j = 0; j < spielfelder[i].length; j++) {
					if (spielfelder[i][j].getSpielfigur() == null) {
					} else if (spielfelder[i][j].getSpielfigur().getFarbe() == FarbEnum.weiss) {
						return true;
					}
				}
			}
		}

		if (player.getFarbe() == FarbEnum.schwarz) {
			for (int i = 0; i < spielfelder.length - 1; i++) {
				for (int j = 0; j < spielfelder[i].length; j++) {
					if (spielfelder[i][j].getSpielfigur() == null) {
					}

					else if (spielfelder[i][j].getSpielfigur().getFarbe() == FarbEnum.schwarz) {
						return true;
					}
				}
			}
		}
		return false;

	}

	/**
	 * Gibt Spielfeld an Position zurueck die Benutzer eingegeben hat,
	 * Ueberpruefungen werden vorab in checklegitFeld vorgenommen.
	 * 
	 * @param coord
	 * @return Spielfeld spielfeld = spielfelder[Stelle2Y][Stelle1X]
	 */
	private Spielfeld EingabeSpielfeld(String coord) {
		char Stelle1 = coord.charAt(0);
		int Stelle1X;
		int Stelle2Y = -1;
		if (coord.length() == 3) {
			if (coord.charAt(2) == 48) {
				Stelle2Y = 9;
			}

			else if (coord.charAt(2) == 49) {
				Stelle2Y = 10;
			} else if (coord.charAt(2) == 50) {
				Stelle2Y = 11;
			}
		}
		if (coord.length() == 2) {
			int Stelle2 = coord.charAt(1);
			Stelle2Y = Stelle2 - 49;
		}
		Stelle1X = ((int) Stelle1) - 97;
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
	private void add(Spieler s1) {
		if (spieler[0] == null) {
			spieler[0] = s1;
		} else {
			spieler[1] = s1;
		}
	}

	/**
	 * Bewegt Stein von einem Feld auf das Andere und veraendert deren
	 * Spielfiguren Zustaende
	 * 
	 * @param stein1
	 * @param posxy
	 */
	private void move(Spielfeld stein1, Spielfeld posxy) {
		// Check Pusten
		doCheckPusten(stein1);

		//
		posxy.setSpielfigur(stein1.getSpielfigur());// Setzt Spielfigur auf
																								// Zielfeld.
		// Setzt Spielfigur auf Startfeld auf null.

		System.out.println("Zug von: " + posxy.getSpielfigur().getFarbe() + ",(" + posxy.getSpielfigur() + ") " + stein1.getSchachNotation() + " -> " + posxy.getSchachNotation());
		stein1.setSpielfigur(null);
		System.out.println(spielbrett);

	}

	/**
	 * Ueberprueft ob gepustet werden muss, und fuehrt dass auch aus.
	 * 
	 * @param stein1
	 * @return boolean
	 */
	private boolean doCheckPusten(Spielfeld stein1) {
		Spielfeld[] pustenarr = new Spielfeld[3];
		int z = 0;
		for (int i = 0; i < spielbrett.getBrett().length; i++) {
			for (int j = 0; j < spielbrett.getBrett()[i].length; j++) {
				if (spielbrett.getBrett()[j][i].getSpielfigur() != null) {
					if (spielbrett.getBrett()[j][i].getSpielfigur().getFarbe() == stein1.getSpielfigur().getFarbe()) {

						if (spielbrett.getBrett()[j][i].getSpielfigur().isDame() == false) {
							if (SchlagMoeglich(spielbrett.getBrett()[j][i]) == true) {
								pustenarr[z] = spielbrett.getBrett()[j][i];
								z++;
							}
						} else {
							if (schlagMoeglichDame(spielbrett.getBrett()[j][i]) == true) {
								pustenarr[z] = spielbrett.getBrett()[j][i];
								z++;
							}
						}
					}
				}
			}
		}
		if (z > 0) {
			askRemove(pustenarr);

			return true;
		} else {
			return false;
		}
	}

	/**
	 * Fragt den Benutzer, welchen Stein er von 2 entfernen moechte.
	 * 
	 * @param pustenarr
	 */
	private void askRemove(Spielfeld[] pustenarr) {
		Spielfeld moeglichkeit1 = pustenarr[0];

		System.out.println("Es wurde gezogen, obwohl man haette schlagen koennen!!!");
		if (pustenarr[1] == null) {
			System.out.println("Spielstein an Stelle " + moeglichkeit1.getSchachNotation() + "(" + moeglichkeit1.getSpielfigur() + ") wird geloescht.");
			removeSpielfigur(moeglichkeit1);
		} else if (pustenarr[2] == null) {
			Spielfeld moeglichkeit2 = pustenarr[1];
			boolean askdone = false;
			while (askdone == false) {
				while (askdone == false) {
					Scanner scanner = new Scanner(System.in);
					System.out.println("Von Welchem Stein moechtest du dich trennen? :)");
					System.out.println(moeglichkeit1.getSpielfigur() + " auf Feld: " + moeglichkeit1.getSchachNotation() + " oder " + moeglichkeit2.getSpielfigur() + " auf Feld: " + moeglichkeit2.getSchachNotation() + "?");
					String feld = scanner.nextLine();
					if (checkLegitFeld(feld) == false) {
						System.out.println("Das ist kein Feld, versuche es erneut!");
						break;
					}
					if (moeglichkeit1.getSchachNotation().equals(feld) || moeglichkeit2.getSchachNotation().equals(feld)) {
						Spielfeld zielfeld = EingabeSpielfeld(feld);
						if (moeglichkeit1.equals(zielfeld)) {
							removeSpielfigur(moeglichkeit1);
							askdone = true;
						} else if (moeglichkeit2.equals(zielfeld)) {
							removeSpielfigur(moeglichkeit2);
							askdone = true;
						} else {
							System.out.println("Dieser Stein steht nicht zur Auswahl");
							break;
						}
					} else {
						break;
					}

					askdone = true;
				}
			}
		} else {
			boolean askdone = false;
			while (askdone == false) {
				while (askdone == false) {
					Spielfeld moeglichkeit2 = pustenarr[1];
					Spielfeld moeglichkeit3 = pustenarr[2];
					Scanner scanner = new Scanner(System.in);
					System.out.println("Von Welchem Stein moechtest du dich trennen? :)");
					System.out.println(moeglichkeit1.getSpielfigur() + " auf Feld: " + moeglichkeit1.getSchachNotation() + " , " + moeglichkeit2.getSpielfigur() + " auf Feld: " + moeglichkeit2.getSchachNotation() + " oder , " + moeglichkeit3.getSpielfigur() + " auf Feld: " + moeglichkeit3.getSchachNotation()
							+ "?");
					String feld = scanner.nextLine();
					if (checkLegitFeld(feld) == false) {
						System.out.println("Das ist kein Feld, versuche es erneut!");
						break;
					}
					if (moeglichkeit1.getSchachNotation().equals(feld) || moeglichkeit2.getSchachNotation().equals(feld) || moeglichkeit3.getSchachNotation().equals(feld)) {
						Spielfeld zielfeld = EingabeSpielfeld(feld);
						if (moeglichkeit1.equals(zielfeld)) {
							removeSpielfigur(moeglichkeit1);
							askdone = true;
						} else if (moeglichkeit2.equals(zielfeld)) {
							removeSpielfigur(moeglichkeit2);
							askdone = true;
						} else if (moeglichkeit3.equals(zielfeld)) {
						} else {

							System.out.println("Dieser Stein steht nicht zur Auswahl");
							break;
						}
					} else {
						break;
					}

					askdone = true;
				}
			}
		}

	}

	/**
	 * entfernt Spielfigur auf Spielbrett
	 * 
	 * @param removeSpielfigur
	 */
	private void removeSpielfigur(Spielfeld removeSpielfigur) {
		System.out.println("Adieu " + removeSpielfigur.getSpielfigur() + " auf Feld: " + removeSpielfigur.getSchachNotation());
		removeSpielfigur.setSpielfigur(null);

	}

	/**
	 * Promoted Spielfigur welche noch keine Dame ist, zur Dame
	 * 
	 * @param spielfeld
	 */
	private void wirdDame(Spielfeld spielfeld) {
		if (spielfeld.getSpielfigur() != null) {
			spielfeld.getSpielfigur().setDame(true);
			System.out.println("Spielfigur auf Feld " + spielfeld.getSchachNotation() + " wurde zu einer Dame! TETEREETEEE");
			System.out.println(spielbrett);
		}
	}

	public SpielBrett getSpielbrett() {
		return spielbrett;
	}

	private void setSpielbrett(SpielBrett spielbrett) {
		this.spielbrett = spielbrett;
	}

	public Spieler[] getSpieler() {
		return spieler;
	}

	public void setSpieler(Spieler[] spieler) {
		this.spieler = spieler;
	}

	public Spieler getActiveSpieler() {
		return activeSpieler;
	}

	public void setActiveSpieler(Spieler activeSpieler) {
		this.activeSpieler = activeSpieler;
	}

	@Override
	public void speichern(String pfad, String dateiname, String typ) {
		typ = typ.toLowerCase();
		switch (typ) {
		case ("csv"):
			saveCSV(pfad + dateiname + "." + typ);
		break;
		case ("ser"):
			saveSerialize(pfad + dateiname + "." + typ);
		break;
		default:
			throw new RuntimeException("Dateityp " + typ + " nicht existent");
		}
	}

	private void saveCSV(String filename) {
		iDatenzugriff csv = new DatenzugriffCSV();
		String s = "";
		s += spieler[0].generiereCSV() + "\n";
		s += spieler[1].generiereCSV() + "\n";
		// Aktiver Spieler boolean
		s += "true\n";
		s += spielbrett.generiereCSV();
		try {
			csv.writeObject(s, filename);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void laden(String pfad, String dateiname, String typ) {
		typ = typ.toLowerCase();
		switch (typ) {
		case ("csv"):
			ladenCSV(pfad + dateiname + "." + typ);
			break;
		case ("ser"):
			loadSerialize(pfad + dateiname + "." + typ);
			break;
		default:
			throw new RuntimeException("Dateityp nicht existent");
		}
	}

	private void ladenCSV(String filename) {
		iDatenzugriff laden = new DatenzugriffCSV();
		try {
			ArrayList<String> s = (ArrayList<String>) laden.readObject(filename);
			for (int i = 0; i < s.size(); i++) {
				String[] args = s.get(i).split(";");
				if (i == 0 || i == 1) {
					boolean istki = args[2].equals("1");
					FarbEnum farbe;
					if (args[1].equals("schwarz")) {
						farbe = FarbEnum.schwarz;
					} else {
						farbe = FarbEnum.weiss;

					}
					Spieler s1 = new Spieler(args[0], farbe, istki);
					this.add(s1);
					;
				} else if (i == 2) {
					// Boolean aktiver Spieler
				} else {
					if (args[1].equals("null")) {
						spielbrett.getFeldById(args[0]).setSpielfigur(null);
					} else {
						FarbEnum farbe;
						boolean b = args[2].equals("true");
						if (args[1].equals("schwarz")) {
							farbe = FarbEnum.schwarz;
						} else {
							farbe = FarbEnum.weiss;

						}
						Spielfigur f = new Spielfigur(farbe, b);

						spielbrett.getFeldById(args[0]).setSpielfigur(f);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void saveSerialize(String filename) {
		iDatenzugriff serial = new DatenzugriffSerialisiert();
		try {
			serial.writeObject(this, filename);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				serial.close();
			} catch (IOException fehler) {
				System.err.println(fehler.getMessage());
			}
		}
	}

	// Änderungen im Code - Änderungen im Code - Änderungen im Code - Änderungen
	// im Code - Änderungen im Code - Änderungen im Code
	public void loadSerialize(String filename) {
		iDatenzugriff serial = new DatenzugriffSerialisiert();
		try {
			Spiel spiel = (Spiel) serial.readObject(filename);
			System.out.println("Spiel wurde geladen!");
			System.out.println(spiel.getSpielbrett());
			if(spiel.spieler[0]==spiel.getActiveSpieler()){
				spiel.playerRotation(spiel.getActiveSpieler(),spiel.spieler[1]);
			}
			else{
				spiel.playerRotation(spiel.getActiveSpieler(),spiel.spieler[0]);
			}
			
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			try {
				serial.close();
			} catch (IOException fehler) {
				System.out.println(fehler.getMessage());
			}
		}
	}
	
}
