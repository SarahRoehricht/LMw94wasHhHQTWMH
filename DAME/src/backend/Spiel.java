package backend;

import java.util.Scanner;

public class Spiel implements iBediener {

	private SpielBrett spielbrett;

	private static final int spielerMax = 2;

	private Spieler[] spieler = new Spieler[spielerMax];
	private int spielerBisher = 0;
/**
 * Konstruktor, ruft SpielBrett Konstruktor auf, und ruft this.welcome()Methode zum Spielanfang auf. Fuer End-User
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
	public void welcome() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Name Spieler 1:");
		String name1 = scanner.next();
		System.out.println("KI? - y/n");
		String ki1 = scanner.next();
		String kiabfrage = new String("y");

		String kiabfrage1no = "n";
		while (!ki1.equals(kiabfrage1no)) {
			if (ki1.equals(kiabfrage)) { // Ki funktionsfaehig machen!
				System.out.println("Ki noch nicht funktionsfaehig :(");
				System.out.println("KI? - y/n");
				ki1 = scanner.next();
			} else {
				System.out.println("KI? - y/n");
				ki1 = scanner.next();
			}
		}
		Spieler player1 = new Spieler(name1, FarbEnum.weiß);
		add(player1);
		System.out.println(player1 + " hinzugefuegt!");
		System.out.println("Name Spieler 2:");
		String name2 = scanner.next();
		System.out.println("KI? - y/n");
		String ki2 = scanner.next();
		String kiabfrage2 = "y";

		String kiabfrage2no = "n";
		while (!ki2.equals(kiabfrage2no)) {
			if (ki2.equals(kiabfrage2)) {
				System.out.println("Ki noch nicht funktionsfaehig :(");// Ki
																																// funktionsfaehig
																																// machen!
				System.out.println("KI? - y/n");
				ki2 = scanner.next();
			} else {
				System.out.println("KI? - y/n");
				ki2 = scanner.next();
			}
		}

		Spieler player2 = new Spieler(name2, FarbEnum.schwarz);
		add(player2);
		System.out.println(player2 + " hinzugefuegt!");

		System.out.println("Moege das Spiel beginnen!");

		System.out.println(spielbrett);
		System.out.println(player1.getName() + " - " + "'" + player1.getFarbe() + "'" + " faengt an!");
		scanner.reset();
		playerRotation(player1, player2);
	}

	/**
	 * Beinhaltet Eingabenbehandlungen und Methodenaufrufe, wie auch vorerst die
	 * Startfeld, Zielfeldsetzung
	 * 
	 */
	@Override
	public void act(Spieler player1) {
		boolean actdone = false;
		while (actdone != true) {
			while (actdone != true) {
				Scanner scanner = new Scanner(System.in);

				System.out.println("Eingabe Startfeld:");
				String coorda = scanner.nextLine();
				if (checkLegitFeld(coorda) == false) {
					System.out.println("Das ist kein Feld, versuche es erneut!");
					break;
				}
				Spielfeld startfeld = EingabeSpielfeld(coorda);
				while (startfeld.getSpielfigur() == null) {
					System.out.println("Keine Spielfigur auf dem Spielfeld " + coorda + "!");
					System.out.println("Eingabe Startfeld:");
					String coorda2 = scanner.nextLine();
					Spielfeld startfeldtemp = EingabeSpielfeld(coorda2);
					startfeld = startfeldtemp;
					if (startfeld.getSpielfigur() != null) {
						while (!startfeld.getSpielfigur().getFarbe().equals(player1.getFarbe())) {
							System.out.println("kein " + player1.getFarbe() + "er Spielstein!");
							System.out.println("Eingabe Startfeld:");
							coorda2 = scanner.nextLine();
							startfeldtemp = EingabeSpielfeld(coorda2);
							startfeld = startfeldtemp;
						}
					}
				}

				System.out.println("Eingabe Zielfeld:");
				String coordb = scanner.nextLine();
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
				if (startfeld.getSpielfigur().isDame() == false) {

					if (doMoveStein(player1, startfeld, zielfeld) == false) {

						if (doSchlagStein(player1, startfeld, zielfeld) == true) {
							if (SchlagMoeglich(player1, zielfeld) == true) {
								askSchlagen(player1, zielfeld);

							} else {

							}
						} else {
							System.out.println("ungueltiger Zug!");
							break;
						}

					}

				}
				actdone = true;
			}

		}
	}

	
	/**
	 * Fragt End-User ob wohin er schlagen moechte nach einem bereits
	 * durchfuehrten schlag
	 * 
	 * @param player1
	 * @param startfeld
	 */
	private void askSchlagen(Spieler player1, Spielfeld startfeld) {

		boolean askdone = false;

		while (askdone == false) {
			while (askdone == false) {
				System.out.println(startfeld.getSchachNotation() + startfeld.getSpielfigur() + " kann noch einmal schlagen");
				Scanner scanner = new Scanner(System.in);
				System.out.println("Eingabe Zielfeld:");
				String coordb = scanner.nextLine();
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

				if (doSchlagStein(player1, startfeld, zielfeld) == true) {

					if (SchlagMoeglich(player1, zielfeld) == true) {
						askSchlagen(player1, zielfeld);

					} else {

					}
				} else {
					break;
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
	private boolean SchlagMoeglich(Spieler player1, Spielfeld startfeld) {
		if(startfeld.getSpielfigur()!=null){
		if (startfeld.getSpielfigur().getFarbe() == FarbEnum.weiß) {

			if (player1.getFarbe() == FarbEnum.weiß && startfeld.getSpielfigur().isDame() == false) {
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

			if (player1.getFarbe() == FarbEnum.schwarz && startfeld.getSpielfigur().isDame() == false) {

				try {
					if (spielbrett.getBrett()[startfeld.getPosY() + 1][startfeld.getPosX() + 1] != null) {
						if (spielbrett.getBrett()[startfeld.getPosY() + 1][startfeld.getPosX() + 1].getSpielfigur() != null && spielbrett.getBrett()[startfeld.getPosY() + 1][startfeld.getPosX() + 1].getSpielfigur().getFarbe() == FarbEnum.weiß) {
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
						if (spielbrett.getBrett()[startfeld.getPosY() - 1][startfeld.getPosX() - 1].getSpielfigur() != null && spielbrett.getBrett()[startfeld.getPosY() - 1][startfeld.getPosX() - 1].getSpielfigur().getFarbe() == FarbEnum.weiß) {
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
						if (spielbrett.getBrett()[startfeld.getPosY() - 1][startfeld.getPosX() + 1].getSpielfigur() != null && spielbrett.getBrett()[startfeld.getPosY() - 1][startfeld.getPosX() + 1].getSpielfigur().getFarbe() == FarbEnum.weiß) {
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
						if (spielbrett.getBrett()[startfeld.getPosY() + 1][startfeld.getPosX() - 1].getSpielfigur() != null && spielbrett.getBrett()[startfeld.getPosY() + 1][startfeld.getPosX() - 1].getSpielfigur().getFarbe() == FarbEnum.weiß) {
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

		}}
		return false;
	}
/**
 * Schaut ob der Spieler noch ziehen kann.
 * @param player1
 * @return
 */
	private boolean AnyMovesLeft(Spieler player1) {

		// Zaehler fuer moeglich uebrige Zuege.
		{
			for (int i = 0; i < spielbrett.getBrett().length - 1; i++) {

				for (int j = 0; j < spielbrett.getBrett()[i].length - 1; j++) {

					if (moveMoeglich(player1, spielbrett.getBrett()[j][i]) == true || SchlagMoeglich(player1, spielbrett.getBrett()[j][i]) == true) {
						return true;
					}
				}
			}
		}

		return false;
	}
/**
 * schaut ob die Spielfigur auf dem Spielfeld, falls vorhanden noch ziehen kann.
 * @param player1
 * @param startfeld
 * @return
 */
	private boolean moveMoeglich(Spieler player1, Spielfeld startfeld) {
		if (startfeld.getSpielfigur() == null) {
			return false;
		}
		if (player1.getFarbe() == FarbEnum.weiß) {
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
 * Ueberprueft ob auf das gewuenschte Feld gezogen werden kann, und fuert dies dann auch aus.
 * @param player1
 * @param startfeld
 * @param zielfeld
 * @return boolean
 */
	private boolean doSchlagStein(Spieler player1, Spielfeld startfeld, Spielfeld zielfeld) {
		if (startfeld.getSpielfigur().getFarbe() == FarbEnum.weiß) {

			if (player1.getFarbe() == FarbEnum.weiß && startfeld.getSpielfigur().isDame() == false) {
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
				if (spielbrett.getBrett()[startfeld.getPosY() + 1][startfeld.getPosX() + 1].getSpielfigur() != null && spielbrett.getBrett()[startfeld.getPosY() + 1][startfeld.getPosX() + 1].getSpielfigur().getFarbe() == FarbEnum.weiß) {
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
				if (spielbrett.getBrett()[startfeld.getPosY() - 1][startfeld.getPosX() - 1].getSpielfigur() != null && spielbrett.getBrett()[startfeld.getPosY() - 1][startfeld.getPosX() - 1].getSpielfigur().getFarbe() == FarbEnum.weiß) {
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
				if (spielbrett.getBrett()[startfeld.getPosY() - 1][startfeld.getPosX() + 1].getSpielfigur() != null && spielbrett.getBrett()[startfeld.getPosY() - 1][startfeld.getPosX() + 1].getSpielfigur().getFarbe() == FarbEnum.weiß) {
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
				if (spielbrett.getBrett()[startfeld.getPosY() + 1][startfeld.getPosX() - 1].getSpielfigur() != null && spielbrett.getBrett()[startfeld.getPosY() + 1][startfeld.getPosX() - 1].getSpielfigur().getFarbe() == FarbEnum.weiß) {
					schlagen(startfeld, zielfeld, spielbrett.getBrett()[startfeld.getPosY() + 1][startfeld.getPosX() - 1]);
					return true;
				} else {

					return false;
				}

			}

		}
		return false;
	}
/**Ueberprueft Zug Moeglichkeiten, und ruft move methode auf, falls auf das gewuenschte Zielfeld gezogen werden kann.
 * 
 * @param player1
 * @param startfeld
 * @param zielfeld
 * @return boolean
 */
	private boolean doMoveStein(Spieler player1, Spielfeld startfeld, Spielfeld zielfeld) {
		if (startfeld.getSpielfigur().getFarbe() == FarbEnum.weiß) {

			if (player1.getFarbe() == FarbEnum.weiß && startfeld.getSpielfigur().isDame() == false) {
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
			System.out.println(player1 + " ist am Zug!");
			act(player1);
			if (checkSiegkondition(player2) == true) {
				i = 2;
			}
			System.out.println(player2 + " ist am Zug!");
			act(player2);
			if (checkSiegkondition(player1) == true) {
				i = 1;
			}

		}
		if (i == 2) {
			System.out.println(player1 + "hat gewonnen");
		} // Schaut ob beim anderen Spieler keine
		// Steine mehr uebrig sind, um zu gewinnen && ob noch Zuege moeglich sind.
		if (i == 1) {
			System.out.println(player2 + "hat gewonnen");

		}
		System.out.println("Spiel zuende!");

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
	 * Setzt Siegkondition auf true, wenn ein Spieler, keine Figuren mehr auf dem
	 * Feld hat.
	 * 
	 * @param player
	 */
	private boolean checkSiegkondition(Spieler player) { // Siegkondition, kann
																												// nicht
		// mehr ziehen fehlt noch.
		if (nochSteineaufBrett(player) == false) {
			return true;
		}
		if (AnyMovesLeft(player) == false) {
			return true;
		}
		return false;

	}
/**Ueberprueft ob sich noch Steine des Spielers auf dem Brett befinden.
 * 
 * @param player
 * @return
 */
	private boolean nochSteineaufBrett(Spieler player) {

		Spielfeld[][] spielfelder = spielbrett.getBrett();
		if (player.getFarbe() == FarbEnum.weiß) {
			for (int i = 0; i < spielfelder.length - 1; i++) {
				for (int j = 0; j < spielfelder[i].length; j++) {
					if (spielfelder[i][j].getSpielfigur() == null) {
					} else if (spielfelder[i][j].getSpielfigur().getFarbe() == FarbEnum.weiß) {
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
		if (spielerBisher == 2) {// überprüft, dass keine zwei identischen Namen
															// vergeben wurden
			if (spieler[0].getName().equals(spieler[1].getName())) {
				throw new RuntimeException("Dieser Name ist bereits vergeben!");
			}
		}
	}

	/**
	 * Bewegt Stein von einem Feld auf das Andere und veraendert deren
	 * Spielfiguren Zustaende
	 * 
	 * @param stein1
	 * @param posxy
	 */
	public void move(Spielfeld stein1, Spielfeld posxy) {

		posxy.setSpielfigur(stein1.getSpielfigur());
		stein1.setSpielfigur(null);

		System.out.println("Zug von: " + posxy.getSpielfigur().getFarbe() + ",(" + posxy.getSpielfigur() + ") " + stein1.getSchachNotation() + " -> " + posxy.getSchachNotation());
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

	private void setSpielbrett(SpielBrett spielbrett) {
		this.spielbrett = spielbrett;
	}

}
