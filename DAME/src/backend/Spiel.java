package backend;

import java.util.Scanner;

import javax.management.RuntimeErrorException;

public class Spiel implements iBediener {

	private SpielBrett spielbrett;

	private static final int spielerMax = 2;

	private Spieler[] spieler = new Spieler[spielerMax];
	private int spielerBisher = 0;

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
	public void welcome() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Name Spieler 1:");
		String name1 = scanner.next();
		System.out.println("KI? - y/n");
		String ki1 = scanner.next();
		boolean ki1erstellen = false;
		if(ki1.equals("y")){
			ki1erstellen=true;
		}
		
		
		// boolean kidone = false;
		// while (kidone == false) {
		// if (ki1 == kiabfrage) {
		//
		// ki1erstellen = true;
		// kidone = true;
		// }
		// if (ki1 == kiabfrageno) {
		// ki1erstellen = false;
		// kidone = true;
		// }
		// System.out.println("KI? - y/n");
		// ki1 = scanner.next();
		// }

		Spieler player1 = new Spieler(name1, FarbEnum.weiß, ki1erstellen);

		add(player1);
		System.out.println(player1 + " hinzugefuegt!");
		System.out.println("Name Spieler 2:");
		String name2 = scanner.next();
		System.out.println("KI? - y/n");
		String ki2 = scanner.next();
		boolean ki2erstellen = false;
if(ki2.equals("y")){
	ki2erstellen=true;
}
		// while (!ki2.equals(kiabfrage) || !ki2.equals(kiabfrageno)) {
		// if (ki2.equals(kiabfrage)) { // Ki funktionsfaehig machen!
		//
		// ki2erstellen = true;
		//
		// } else {
		//
		// }
		// System.out.println("KI? - y/n");
		// ki2 = scanner.next();
		// }

		Spieler player2 = new Spieler(name2, FarbEnum.weiß, ki2erstellen);

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
		if (player1.getKi() != null) {
			
			Spielfeld kiarray[];
			kiarray=player1.getKi().kiAct(spielbrett.getBrett());
			Spielfeld startfeld=kiarray[0];
			Spielfeld zielfeld=kiarray[1];
			if (doTheMove(player1, startfeld, zielfeld) == false) {
				System.out.println("ungueltiger Zug!");
				throw new RuntimeErrorException( null, "KI BAUT MIST.");
			}
		}else{
		boolean actdone = false;
		while (actdone != true) {// doppelte While-Schleife fuer breaks;
			while (actdone != true) {
				Scanner scanner = new Scanner(System.in);

				System.out.println("Eingabe Startfeld:");
				String coorda = scanner.nextLine();
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

				if (doTheMove(player1, startfeld, zielfeld) == false) {
					System.out.println("ungueltiger Zug!");
					break;
				}

				actdone = true;
			}

		}}
	}

	private boolean doTheMove(Spieler player1, Spielfeld startfeld, Spielfeld zielfeld) {
		if (startfeld.getSpielfigur().isDame() == true) {
			if (moveDameLegit(player1, startfeld, zielfeld) == true) {
				move(startfeld, zielfeld);
				return true;
			}
			if (schlagenDameLegit(player1, startfeld, zielfeld) == true) {
				schlagenDame(player1, startfeld, zielfeld);
				if (schlagMoeglichDame(player1, zielfeld) == true) {
					askSchlagen(player1, zielfeld);
				}
			} else {

				return false;
			}
		} else if (startfeld.getSpielfigur().isDame() == false) {

			if (doMoveStein(player1, startfeld, zielfeld) == false) {

				if (doSchlagStein(player1, startfeld, zielfeld) == true) {
					if (SchlagMoeglich(player1, zielfeld) == true) {
						askSchlagen(player1, zielfeld);

					}
				} else {

					return false;
				}

			}
		}
		return true;
	}

	private boolean schlagMoeglichDame(Spieler player1, Spielfeld startfeld) {
		try {
			for (int i = 1; i < spielbrett.getBrett().length - 2; i++) {
				if (spielbrett.getBrett()[startfeld.getPosY() + i][startfeld.getPosX() + i].getSpielfigur() != null) {
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

	private boolean schlagenDameLegit(Spieler player1, Spielfeld startfeld, Spielfeld zielfeld) {
		if (dameStartZiel(player1, startfeld, zielfeld) == "top-right") {
			for (int i = 1; i < spielbrett.getBrett().length - 2; i++) {
				try {
					if (spielbrett.getBrett()[startfeld.getPosY() + i][startfeld.getPosX() + i].getSpielfigur() != null) {
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
				if (startfeld.getSpielfigur().isDame() == false) {
					if (doSchlagStein(player1, startfeld, zielfeld) == true) {

						if (SchlagMoeglich(player1, zielfeld) == true) {
							askSchlagen(player1, zielfeld);

						} else {

						}
					} else {
						break;
					}
				} else if (startfeld.getSpielfigur().isDame() == true) {
					if (schlagenDameLegit(player1, startfeld, zielfeld) == true) {
						schlagenDame(player1, startfeld, zielfeld);
						if (schlagMoeglichDame(player1, zielfeld) == true) {
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
	private boolean SchlagMoeglich(Spieler player1, Spielfeld startfeld) {
		if (startfeld.getSpielfigur() != null) {
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

					if (moveMoeglich(player1, spielbrett.getBrett()[j][i]) == true || SchlagMoeglich(player1, spielbrett.getBrett()[j][i]) == true) {
						return true;
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
	private boolean 
	moveMoeglich(Spieler player1, Spielfeld startfeld) {
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
	 * Ueberprueft ob auf das gewuenschte Feld gezogen werden kann, und fuert dies
	 * dann auch aus.
	 * 
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
			checkAndGetDame(player1);
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
	 * Schaut ob player1 eine Dame nach seinem Zug bekommen sollte, falls ja wird
	 * wirdDame mit dem passenden Spielfeld aufgerufen.
	 * 
	 * @param player1
	 */
	private void checkAndGetDame(Spieler player1) {
		if (player1.getFarbe() == FarbEnum.weiß) {
			for (int i = 0; i < spielbrett.getBrett().length - 1; i++) {
				if (spielbrett.getBrett()[11][i].getSpielfigur() != null && spielbrett.getBrett()[11][i].getSpielfigur().isDame() == false) {
					if (spielbrett.getBrett()[11][i].getSpielfigur().getFarbe() == FarbEnum.weiß) {
						wirdDame(spielbrett.getBrett()[11][i]);
					}
				}
			}
		}
		if (player1.getFarbe() == FarbEnum.schwarz) {
			for (int i = 0; i < spielbrett.getBrett().length - 1; i++) {
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
		if (nochSteineaufBrett(player) == false) {
			return true;
		}
		if (AnyMovesLeft(player) == false) {
			return true;
		}
		return false;

	}

	/**
	 * Ueberprueft ob sich noch Steine des Spielers auf dem Brett befinden.
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
		// Check Pusten
		if(doCheckPusten(stein1, posxy)==true){
		}else{
			
		
		//
		posxy.setSpielfigur(stein1.getSpielfigur());// Setzt Spielfigur auf
																								// Zielfeld.
		stein1.setSpielfigur(null); // Setzt Spielfigur auf Startfeld auf null.

		System.out.println("Zug von: " + posxy.getSpielfigur().getFarbe() + ",(" + posxy.getSpielfigur() + ") " + stein1.getSchachNotation() + " -> " + posxy.getSchachNotation());
		System.out.println(spielbrett);
		}
	}

	private boolean doCheckPusten(Spielfeld stein1, Spielfeld posxy) {
		for (int i = 0; i < spielbrett.getBrett().length-1; i++) {
			for (int j = 0; j < spielbrett.getBrett()[i].length-1; j++) {
				if(spielbrett.getBrett()[j][i].getSpielfigur()!=null){
					if(spielbrett.getBrett()[j][i].getSpielfigur().getFarbe()==stein1.getSpielfigur().getFarbe()){
						if(SchlagMoeglich(spielbrett.getBrett()[j][i])==true){
							
						}
					}
				}
			}
		}
		return false;
	}

	/**
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

	public Spieler[] getSpieler() {
		return spieler;
	}

	public void setSpieler(Spieler[] spieler) {
		this.spieler = spieler;
	}

}
