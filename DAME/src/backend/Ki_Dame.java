package backend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Ki_Dame extends Ki {
	private FarbEnum farbe;

	private Spielfeld[][] spielbrett;

	/**
	 * Konstruktor ruft Super-Konstruktor auf um Farbe zu setzen.
	 * 
	 * @param farbe
	 */
	Ki_Dame(FarbEnum farbe) {
		super(farbe);

	}

	/**
	 * Wird von Spiel act aufgerufen, und kriegt das 2-D Spielfeld Array
	 * uebergeben, liefert 2Spielfelder im Array zurueck.
	 * 
	 * @return Spielfeld[2] mit Start und Zielfeld
	 */
	public Spielfeld[] kiAct(Spielfeld[][] brett) {
		this.setSpielbrett(brett);
		Spielfeld[] startZiel = new Spielfeld[2];
		ArrayList<Spielfeld[]> schlagMoeglichkeiten = new ArrayList<Spielfeld[]>();
		schlagMoeglichkeiten = schlagMoeglichkeitenSpieler();

		if (schlagMoeglichkeiten.isEmpty()) {
			ArrayList<Spielfeld[]> moveMoeglichkeiten = new ArrayList<Spielfeld[]>();
			moveMoeglichkeiten = moveMoeglichkeitenSpieler();
			Collections.shuffle(moveMoeglichkeiten);
			startZiel = moveMoeglichkeiten.get(0);
		} else {
			Collections.shuffle(schlagMoeglichkeiten);
			startZiel = schlagMoeglichkeiten.get(0);
		}
		System.out.println(startZiel[0].getSchachNotation());
		System.out.println(startZiel[1].getSchachNotation());
		return startZiel;

	}

	/**
	 * Geht ueber das ganze Spielbrett und fuegt die Spielfeld[]'s einer ArrayList
	 * hinzu. Indem es moveMoeglichStartziel aufruft
	 * 
	 * @return ArrayList<Spielfeld[]> mit den MoveMoeglichkeiten in Form von einem
	 *         Spielfeld[]
	 */
	private ArrayList<Spielfeld[]> moveMoeglichkeitenSpieler() {
		ArrayList<Spielfeld[]> startZielListMove = new ArrayList<Spielfeld[]>();
		for (int i = 0; i < this.getSpielbrett().length; i++) {
			for (int j = 0; j < this.getSpielbrett()[i].length; j++) {
				if (this.getSpielbrett()[j][i].getSpielfigur() != null) {
					if (this.getSpielbrett()[j][i].getSpielfigur().getFarbe() == this.getFarbe()) {

						if (this.getSpielbrett()[j][i].getSpielfigur().isDame() == false) {
							if (moveMoeglich(this.getSpielbrett()[j][i]) == true) {
								Spielfeld[] abc = new Spielfeld[2];

								abc = moveMoeglichStartZiel(this.getSpielbrett()[j][i]);
								startZielListMove.add(abc);

							}
						} else {
							if (MoveMoeglichDame(this.getSpielbrett()[j][i]) == true) {
								Spielfeld[] abcd = new Spielfeld[2];
								abcd[0] = this.getSpielbrett()[j][i];
								ArrayList<Spielfeld> movesMoeglichDame = new ArrayList<Spielfeld>();
								movesMoeglichDame = MovesMoeglichDame(this.getSpielbrett()[j][i]);
								for (Spielfeld spielfeld : movesMoeglichDame) {
									abcd[1] = spielfeld;
									startZielListMove.add(abcd);
								}

								System.out.println(startZielListMove.toString());
							}
						}
					}
				}
			}
		}

		return startZielListMove;
	}

	/**
	 * Schaut ob die Dame sich generell noch mind. 1 Feld bewegen kann.
	 * 
	 * @param spielfeld
	 * @return boolean
	 */
	private boolean MoveMoeglichDame(Spielfeld spielfeld) {
		try {
			if (this.getSpielbrett()[spielfeld.getPosY() + 1][spielfeld.getPosX() + 1].getSpielfigur() == null) {
				return true;
			}
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {

		}
		try {
			if (this.getSpielbrett()[spielfeld.getPosY() - 1][spielfeld.getPosX() + 1].getSpielfigur() == null) {
				return true;
			}
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {

		}
		try {
			if (this.getSpielbrett()[spielfeld.getPosY() + 1][spielfeld.getPosX() - 1].getSpielfigur() == null) {
				return true;
			}
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {

		}
		try {
			if (this.getSpielbrett()[spielfeld.getPosY() - 1][spielfeld.getPosX() - 1].getSpielfigur() == null) {
				return true;
			}
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {

		}
		return false;
	}

	/**
	 * Gibt ArrayList einzelner Spielfelder(Zielfelder) zurueck, auf die man
	 * ziehen kann.
	 * 
	 * @param spielfeld
	 * @return
	 */
	private ArrayList<Spielfeld> MovesMoeglichDame(Spielfeld spielfeld) {
		ArrayList<Spielfeld> movesMoeglichDame = new ArrayList<Spielfeld>();
		try {
			for (int i = 0; i < spielbrett.length - 1; i++) {
				if (this.getSpielbrett()[spielfeld.getPosY() + i][spielfeld.getPosX() + i].getSpielfigur() != null) {
					break;
				}
				if (this.getSpielbrett()[spielfeld.getPosY() + i][spielfeld.getPosX() + i].getSpielfigur() == null) {
					movesMoeglichDame.add(this.getSpielbrett()[spielfeld.getPosY() + i][spielfeld.getPosX() + i]);
				}
			}
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {

		}
		try {
			for (int i = 0; i < spielbrett.length - 1; i++) {
				if (this.getSpielbrett()[spielfeld.getPosY() - i][spielfeld.getPosX() + i].getSpielfigur() != null) {
					break;
				}
				if (this.getSpielbrett()[spielfeld.getPosY() - i][spielfeld.getPosX() + i].getSpielfigur() == null) {
					movesMoeglichDame.add(this.getSpielbrett()[spielfeld.getPosY() - i][spielfeld.getPosX() + i]);
				}
			}
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {

		}
		try {
			for (int i = 0; i < spielbrett.length - 1; i++) {
				if (this.getSpielbrett()[spielfeld.getPosY() + i][spielfeld.getPosX() - i].getSpielfigur() != null) {
					break;
				}
				if (this.getSpielbrett()[spielfeld.getPosY() + i][spielfeld.getPosX() - i].getSpielfigur() == null) {
					movesMoeglichDame.add(this.getSpielbrett()[spielfeld.getPosY() + i][spielfeld.getPosX() - i]);
				}
			}
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {

		}
		try {
			for (int i = 0; i < spielbrett.length - 1; i++) {
				if (this.getSpielbrett()[spielfeld.getPosY() - i][spielfeld.getPosX() - i].getSpielfigur() != null) {
					break;
				}
				if (this.getSpielbrett()[spielfeld.getPosY() - i][spielfeld.getPosX() - i].getSpielfigur() == null) {
					movesMoeglichDame.add(this.getSpielbrett()[spielfeld.getPosY() - i][spielfeld.getPosX() - i]);
				}
			}
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {

		}
		return movesMoeglichDame;
	}

	/**
	 * Findet moves die moeglich sind an Stelle Startfeld, und gibt einen
	 * moeglichen Move zurueck oder null.
	 * 
	 * @param startfeld
	 * @return Spielfeld[2]
	 */
	private Spielfeld[] moveMoeglichStartZiel(Spielfeld startfeld) {
		Spielfeld[] moveStartZiel = new Spielfeld[2];

		if (startfeld.getSpielfigur().getFarbe() == FarbEnum.weiß) {
			int w = 0;
			try {
				if (this.getSpielbrett()[startfeld.getPosY() + 1][startfeld.getPosX() - 1] != null) {
					if (this.getSpielbrett()[startfeld.getPosY() + 1][startfeld.getPosX() - 1].getSpielfigur() == null) {
						w += 1;
					}
				}

			} catch (java.lang.ArrayIndexOutOfBoundsException e) {

			}
			try {
				if (this.getSpielbrett()[startfeld.getPosY() + 1][startfeld.getPosX() + 1] != null) {
					if (this.getSpielbrett()[startfeld.getPosY() + 1][startfeld.getPosX() + 1].getSpielfigur() == null) {
						w += 2;
					}
				}
			} catch (java.lang.ArrayIndexOutOfBoundsException e) {

			}
			if (w == 0) {
				return null;
			} else if (w == 1) {
				moveStartZiel[0] = startfeld;
				moveStartZiel[1] = this.getSpielbrett()[startfeld.getPosY() + 1][startfeld.getPosX() - 1];
				return moveStartZiel;
			} else if (w == 2) {
				moveStartZiel[0] = startfeld;
				moveStartZiel[1] = this.getSpielbrett()[startfeld.getPosY() + 1][startfeld.getPosX() + 1];
				return moveStartZiel;
			} else if (w == 3) {
				Random random = new Random();
				int num = random.nextInt(2) + 1;
				if (num == 1) {
					moveStartZiel[0] = startfeld;
					moveStartZiel[1] = this.getSpielbrett()[startfeld.getPosY() + 1][startfeld.getPosX() - 1];
					return moveStartZiel;

				} else if (num == 2) {
					moveStartZiel[0] = startfeld;
					moveStartZiel[1] = this.getSpielbrett()[startfeld.getPosY() + 1][startfeld.getPosX() + 1];
					return moveStartZiel;
				}
			}
			return null;
		}

		if (startfeld.getSpielfigur().getFarbe() == FarbEnum.schwarz) {
			int s = 0;
			try {

				if (this.getSpielbrett()[startfeld.getPosY() - 1][startfeld.getPosX() - 1] != null) {
					if (this.getSpielbrett()[startfeld.getPosY() - 1][startfeld.getPosX() - 1].getSpielfigur() == null) {
						s += 1;
					}
				}
			} catch (java.lang.ArrayIndexOutOfBoundsException e) {

			} finally {

			}

			try {
				if (this.getSpielbrett()[startfeld.getPosY() - 1][startfeld.getPosX() + 1] != null) {
					if (this.getSpielbrett()[startfeld.getPosY() - 1][startfeld.getPosX() + 1].getSpielfigur() == null) {
						s += 2;
					}
				}
			} catch (java.lang.ArrayIndexOutOfBoundsException e) {

			}
			if (s == 0) {
				return null;
			} else if (s == 1) {
				moveStartZiel[0] = startfeld;
				moveStartZiel[1] = this.getSpielbrett()[startfeld.getPosY() - 1][startfeld.getPosX() - 1];
				return moveStartZiel;
			} else if (s == 2) {
				moveStartZiel[0] = startfeld;
				moveStartZiel[1] = this.getSpielbrett()[startfeld.getPosY() - 1][startfeld.getPosX() + 1];
				return moveStartZiel;
			} else if (s == 3) {
				Random random = new Random();
				int num = random.nextInt(2) + 1;
				if (num == 1) {
					moveStartZiel[0] = startfeld;
					moveStartZiel[1] = this.getSpielbrett()[startfeld.getPosY() - 1][startfeld.getPosX() - 1];
					return moveStartZiel;

				} else if (num == 2) {
					moveStartZiel[0] = startfeld;
					moveStartZiel[1] = this.getSpielbrett()[startfeld.getPosY() - 1][startfeld.getPosX() + 1];
					return moveStartZiel;
				}
			}
		}
		return null;
	}

	/**
	 * Findet heraus, ob ein Zug an dem gewissen Feld moeglich ist. Fuer einen
	 * normalen Stein
	 * 
	 * @param startfeld
	 * @return boolean
	 */
	private boolean moveMoeglich(Spielfeld startfeld) {
		if (startfeld.getSpielfigur() == null) {
			return false;
		}
		if (startfeld.getSpielfigur().getFarbe() == FarbEnum.weiß) {
			try {
				if (this.getSpielbrett()[startfeld.getPosY() + 1][startfeld.getPosX() - 1] != null) {
					if (this.getSpielbrett()[startfeld.getPosY() + 1][startfeld.getPosX() - 1].getSpielfigur() == null) {
						return true;
					}
				}

			} catch (java.lang.ArrayIndexOutOfBoundsException e) {

			}
			try {
				if (this.getSpielbrett()[startfeld.getPosY() + 1][startfeld.getPosX() + 1] != null) {
					if (this.getSpielbrett()[startfeld.getPosY() + 1][startfeld.getPosX() + 1].getSpielfigur() == null) {
						return true;
					}
				}
			} catch (java.lang.ArrayIndexOutOfBoundsException e) {

			}
			return false;
		}

		if (startfeld.getSpielfigur().getFarbe() == FarbEnum.schwarz) {
			try {

				if (this.getSpielbrett()[startfeld.getPosY() - 1][startfeld.getPosX() - 1] != null) {
					if (this.getSpielbrett()[startfeld.getPosY() - 1][startfeld.getPosX() - 1].getSpielfigur() == null) {
						return true;
					}
				}
			} catch (java.lang.ArrayIndexOutOfBoundsException e) {

			} finally {

			}

			try {
				if (this.getSpielbrett()[startfeld.getPosY() - 1][startfeld.getPosX() + 1] != null) {
					if (this.getSpielbrett()[startfeld.getPosY() - 1][startfeld.getPosX() + 1].getSpielfigur() == null) {
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
	 * Durchlaeuft das ganze Brett und ruft schlagMoeglichkeitenSpieler auf, falls
	 * true, fuegt es spielfeld[2] der ArrayList hinzu ueber
	 * schlagMoeglichDameStartZiel oder schlagMoeglichStartZiel
	 * 
	 * @return ArrayList in der Spielfeld[2]'s enthalten sind
	 */
	private ArrayList<Spielfeld[]> schlagMoeglichkeitenSpieler() {

		ArrayList<Spielfeld[]> startZielListStein = new ArrayList<Spielfeld[]>();
		ArrayList<Spielfeld[]> startZielListDame = new ArrayList<Spielfeld[]>();
		for (int i = 0; i < this.getSpielbrett().length; i++) {
			for (int j = 0; j < this.getSpielbrett()[i].length; j++) {
				if (this.getSpielbrett()[j][i].getSpielfigur() != null) {
					if (this.getSpielbrett()[j][i].getSpielfigur().getFarbe() == this.getFarbe()) {

						if (this.getSpielbrett()[j][i].getSpielfigur().isDame() == false) {
							if (SchlagMoeglich(this.getSpielbrett()[j][i]) == true) {
								Spielfeld[] abc = new Spielfeld[2];

								abc = schlagMoeglichStartZiel(this.getSpielbrett()[j][i]);
								startZielListStein.add(abc);

							}
						} else if (this.getSpielbrett()[j][i].getSpielfigur().isDame() == true) {
							if ((schlagMoeglichDame(this.getSpielbrett()[j][i]) == true)) {
								Spielfeld[] abcd = new Spielfeld[2];
								abcd = schlagMoeglichDameStartZiel(this.getSpielbrett()[j][i]);
								startZielListDame.add(abcd);
								System.out.println(startZielListDame.toString());
								System.out.println(abcd[0].getSchachNotation());
								System.out.println(abcd[1].getSchachNotation());
							}
						}
					}
				}
			}
		}
		if (startZielListDame.isEmpty()) {
			return startZielListStein;
		} else {
			return startZielListDame;

		}
	}

	/**
	 * Findet eine Schlagmoeglichkeit fuer die Dame, und gibt diese zurueck.
	 * 
	 * @param startfeld
	 * @return Spielfeld[2]
	 */
	private Spielfeld[] schlagMoeglichDameStartZiel(Spielfeld startfeld) {
		Spielfeld[] startZiel = new Spielfeld[2];
		// Random falls mehrere Moeglichkeiten offen stehen.
		try {
			for (int i = 1; i < this.getSpielbrett().length - 2; i++) {
				if (this.getSpielbrett()[startfeld.getPosY() + i][startfeld.getPosX() + i].getSpielfigur() != null) {
					if (this.getSpielbrett()[startfeld.getPosY() + i][startfeld.getPosX() + i].getSpielfigur().getFarbe() == this.getSpielbrett()[startfeld.getPosY()][startfeld.getPosX()].getSpielfigur().getFarbe()) {
						break;
					}
					if (this.getSpielbrett()[startfeld.getPosY() + i + 1][startfeld.getPosX() + i + 1].getSpielfigur() != null) {
						break;
					}
					if (startfeld.getSpielfigur().getFarbe() != this.getSpielbrett()[startfeld.getPosY() + i][startfeld.getPosX() + i].getSpielfigur().getFarbe()) {
						startZiel[0] = startfeld;
						startZiel[1] = this.getSpielbrett()[startfeld.getPosY() + i + 1][startfeld.getPosX() + i + 1];
						return startZiel;

					}
				}
			}

		} catch (java.lang.ArrayIndexOutOfBoundsException e) {

		}
		try {
			for (int i = 1; i < this.getSpielbrett().length - 2; i++) {
				if (this.getSpielbrett()[startfeld.getPosY() - i][startfeld.getPosX() + i].getSpielfigur() != null) {
					if (this.getSpielbrett()[startfeld.getPosY() - i][startfeld.getPosX() + i].getSpielfigur().getFarbe() == this.getSpielbrett()[startfeld.getPosY()][startfeld.getPosX()].getSpielfigur().getFarbe()) {
						break;
					}
					if (this.getSpielbrett()[startfeld.getPosY() - i - 1][startfeld.getPosX() + i + 1].getSpielfigur() != null) {
						break;
					}
					if (startfeld.getSpielfigur().getFarbe() != this.getSpielbrett()[startfeld.getPosY() - i][startfeld.getPosX() + i].getSpielfigur().getFarbe()) {
						startZiel[0] = startfeld;
						startZiel[1] = this.getSpielbrett()[startfeld.getPosY() - i - 1][startfeld.getPosX() + i + 1];
						return startZiel;

					}
				}
			}

		} catch (java.lang.ArrayIndexOutOfBoundsException e) {

		}
		try {
			for (int i = 1; i < this.getSpielbrett().length - 2; i++) {
				if (this.getSpielbrett()[startfeld.getPosY() + i][startfeld.getPosX() - i].getSpielfigur() != null) {
					if (this.getSpielbrett()[startfeld.getPosY() + i][startfeld.getPosX() - i].getSpielfigur().getFarbe() == this.getSpielbrett()[startfeld.getPosY()][startfeld.getPosX()].getSpielfigur().getFarbe()) {
						break;
					}
					if (this.getSpielbrett()[startfeld.getPosY() + i + 1][startfeld.getPosX() - i - 1].getSpielfigur() != null) {
						break;
					}
					if (startfeld.getSpielfigur().getFarbe() != this.getSpielbrett()[startfeld.getPosY() + i][startfeld.getPosX() - i].getSpielfigur().getFarbe()) {
						startZiel[0] = startfeld;
						startZiel[1] = this.getSpielbrett()[startfeld.getPosY() + i + 1][startfeld.getPosX() - i - 1];
						return startZiel;

					}
				}
			}

		} catch (java.lang.ArrayIndexOutOfBoundsException e) {

		}
		try {
			for (int i = 1; i < this.getSpielbrett().length - 2; i++) {
				if (this.getSpielbrett()[startfeld.getPosY() - i][startfeld.getPosX() - i].getSpielfigur() != null) {
					if (this.getSpielbrett()[startfeld.getPosY() - i][startfeld.getPosX() - i].getSpielfigur().getFarbe() == this.getSpielbrett()[startfeld.getPosY()][startfeld.getPosX()].getSpielfigur().getFarbe()) {
						break;
					}
					if (this.getSpielbrett()[startfeld.getPosY() - i - 1][startfeld.getPosX() - i - 1].getSpielfigur() != null) {
						break;
					}
					if (startfeld.getSpielfigur().getFarbe() != this.getSpielbrett()[startfeld.getPosY() - i][startfeld.getPosX() - i].getSpielfigur().getFarbe()) {
						startZiel[0] = startfeld;
						startZiel[1] = this.getSpielbrett()[startfeld.getPosY() - i - 1][startfeld.getPosX() - i - 1];
						return startZiel;

					}
				}
			}

		} catch (java.lang.ArrayIndexOutOfBoundsException e) {

		}
		return null;
	}

	/**
	 * Liefert Boolean zurueck falls an dem Feld eine schlagmoeglichkeit fuer
	 * einen einfachen Stein besteht.
	 * 
	 * @param startfeld
	 * @return boolean
	 */
	private boolean SchlagMoeglich(Spielfeld startfeld) {
		if (startfeld.getSpielfigur() != null) {
			if (startfeld.getSpielfigur().getFarbe() == FarbEnum.weiß) {

				if (startfeld.getSpielfigur().getFarbe() == FarbEnum.weiß && startfeld.getSpielfigur().isDame() == false) {
					try {
						if (this.getSpielbrett()[startfeld.getPosY() + 1][startfeld.getPosX() + 1] != null) {
							if (this.getSpielbrett()[startfeld.getPosY() + 1][startfeld.getPosX() + 1].getSpielfigur() != null && this.getSpielbrett()[startfeld.getPosY() + 1][startfeld.getPosX() + 1].getSpielfigur().getFarbe() == FarbEnum.schwarz) {
								if (this.getSpielbrett()[startfeld.getPosY() + 2][startfeld.getPosX() + 2] != null) {
									if (this.getSpielbrett()[startfeld.getPosY() + 2][startfeld.getPosX() + 2].getSpielfigur() == null) {
										return true;
									}
								}
							}
						}
					} catch (java.lang.ArrayIndexOutOfBoundsException e) {

					}
					try {
						if (this.getSpielbrett()[startfeld.getPosY() - 1][startfeld.getPosX() - 1] != null) {
							if (this.getSpielbrett()[startfeld.getPosY() - 1][startfeld.getPosX() - 1].getSpielfigur() != null && this.getSpielbrett()[startfeld.getPosY() - 1][startfeld.getPosX() - 1].getSpielfigur().getFarbe() == FarbEnum.schwarz) {
								if (this.getSpielbrett()[startfeld.getPosY() - 2][startfeld.getPosX() - 2] != null) {
									if (this.getSpielbrett()[startfeld.getPosY() - 2][startfeld.getPosX() - 2].getSpielfigur() == null) {
										return true;
									}
								}
							}
						}
					} catch (java.lang.ArrayIndexOutOfBoundsException e) {

					}
					try {
						if (this.getSpielbrett()[startfeld.getPosY() - 1][startfeld.getPosX() + 1] != null) {
							if (this.getSpielbrett()[startfeld.getPosY() - 1][startfeld.getPosX() + 1].getSpielfigur() != null && this.getSpielbrett()[startfeld.getPosY() - 1][startfeld.getPosX() + 1].getSpielfigur().getFarbe() == FarbEnum.schwarz) {
								if (this.getSpielbrett()[startfeld.getPosY() - 2][startfeld.getPosX() + 2] != null) {
									if (this.getSpielbrett()[startfeld.getPosY() - 2][startfeld.getPosX() + 2].getSpielfigur() == null) {
										return true;
									}
								}
							}
						}
					} catch (java.lang.ArrayIndexOutOfBoundsException e) {

					}
					try {
						if (this.getSpielbrett()[startfeld.getPosY() + 1][startfeld.getPosX() - 1] != null) {
							if (this.getSpielbrett()[startfeld.getPosY() + 1][startfeld.getPosX() - 1].getSpielfigur() != null && this.getSpielbrett()[startfeld.getPosY() + 1][startfeld.getPosX() - 1].getSpielfigur().getFarbe() == FarbEnum.schwarz) {
								if (this.getSpielbrett()[startfeld.getPosY() + 2][startfeld.getPosX() - 2] != null) {
									if (this.getSpielbrett()[startfeld.getPosY() + 2][startfeld.getPosX() - 2].getSpielfigur() == null) {
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
					if (this.getSpielbrett()[startfeld.getPosY() + 1][startfeld.getPosX() + 1] != null) {
						if (this.getSpielbrett()[startfeld.getPosY() + 1][startfeld.getPosX() + 1].getSpielfigur() != null && this.getSpielbrett()[startfeld.getPosY() + 1][startfeld.getPosX() + 1].getSpielfigur().getFarbe() == FarbEnum.weiß) {
							if (this.getSpielbrett()[startfeld.getPosY() + 2][startfeld.getPosX() + 2] != null) {
								if (this.getSpielbrett()[startfeld.getPosY() + 2][startfeld.getPosX() + 2].getSpielfigur() == null) {
									return true;
								}
							}
						}
					}
				} catch (java.lang.ArrayIndexOutOfBoundsException e) {

				}
				try {
					if (this.getSpielbrett()[startfeld.getPosY() - 1][startfeld.getPosX() - 1] != null) {
						if (this.getSpielbrett()[startfeld.getPosY() - 1][startfeld.getPosX() - 1].getSpielfigur() != null && this.getSpielbrett()[startfeld.getPosY() - 1][startfeld.getPosX() - 1].getSpielfigur().getFarbe() == FarbEnum.weiß) {
							if (this.getSpielbrett()[startfeld.getPosY() - 2][startfeld.getPosX() - 2] != null) {
								if (this.getSpielbrett()[startfeld.getPosY() - 2][startfeld.getPosX() - 2].getSpielfigur() == null) {
									return true;
								}
							}
						}
					}
				} catch (java.lang.ArrayIndexOutOfBoundsException e) {

				}
				try {
					if (this.getSpielbrett()[startfeld.getPosY() - 1][startfeld.getPosX() + 1] != null) {
						if (this.getSpielbrett()[startfeld.getPosY() - 1][startfeld.getPosX() + 1].getSpielfigur() != null && this.getSpielbrett()[startfeld.getPosY() - 1][startfeld.getPosX() + 1].getSpielfigur().getFarbe() == FarbEnum.weiß) {
							if (this.getSpielbrett()[startfeld.getPosY() - 2][startfeld.getPosX() + 2] != null) {
								if (this.getSpielbrett()[startfeld.getPosY() - 2][startfeld.getPosX() + 2].getSpielfigur() == null) {
									return true;
								}
							}
						}
					}
				} catch (java.lang.ArrayIndexOutOfBoundsException e) {

				}
				try {
					if (this.getSpielbrett()[startfeld.getPosY() + 1][startfeld.getPosX() - 1] != null) {
						if (this.getSpielbrett()[startfeld.getPosY() + 1][startfeld.getPosX() - 1].getSpielfigur() != null && this.getSpielbrett()[startfeld.getPosY() + 1][startfeld.getPosX() - 1].getSpielfigur().getFarbe() == FarbEnum.weiß) {
							if (this.getSpielbrett()[startfeld.getPosY() + 2][startfeld.getPosX() - 2] != null) {
								if (this.getSpielbrett()[startfeld.getPosY() + 2][startfeld.getPosX() - 2].getSpielfigur() == null) {
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
	 * Liefert Spielfeld[2] mit start und Zielfeld im Array zurueck, nachdem es
	 * eine schlagmoeglichkeit gefunden hat.
	 * 
	 * @param startfeld
	 * @return Spielfeld[2]
	 */
	private Spielfeld[] schlagMoeglichStartZiel(Spielfeld startfeld) {
		Spielfeld[] startZiel = new Spielfeld[2];
		if (startfeld.getSpielfigur() != null) {
			if (startfeld.getSpielfigur().getFarbe() == FarbEnum.weiß) {

				if (startfeld.getSpielfigur().getFarbe() == FarbEnum.weiß && startfeld.getSpielfigur().isDame() == false) {
					try {
						if (this.getSpielbrett()[startfeld.getPosY() + 1][startfeld.getPosX() + 1] != null) {
							if (this.getSpielbrett()[startfeld.getPosY() + 1][startfeld.getPosX() + 1].getSpielfigur() != null && this.getSpielbrett()[startfeld.getPosY() + 1][startfeld.getPosX() + 1].getSpielfigur().getFarbe() == FarbEnum.schwarz) {
								if (this.getSpielbrett()[startfeld.getPosY() + 2][startfeld.getPosX() + 2] != null) {
									if (this.getSpielbrett()[startfeld.getPosY() + 2][startfeld.getPosX() + 2].getSpielfigur() == null) {
										startZiel[0] = startfeld;
										startZiel[1] = this.getSpielbrett()[startfeld.getPosY() + 2][startfeld.getPosX() + 2];
										return startZiel;
									}
								}
							}
						}
					} catch (java.lang.ArrayIndexOutOfBoundsException e) {

					}
					try {
						if (this.getSpielbrett()[startfeld.getPosY() - 1][startfeld.getPosX() - 1] != null) {
							if (this.getSpielbrett()[startfeld.getPosY() - 1][startfeld.getPosX() - 1].getSpielfigur() != null && this.getSpielbrett()[startfeld.getPosY() - 1][startfeld.getPosX() - 1].getSpielfigur().getFarbe() == FarbEnum.schwarz) {
								if (this.getSpielbrett()[startfeld.getPosY() - 2][startfeld.getPosX() - 2] != null) {
									if (this.getSpielbrett()[startfeld.getPosY() - 2][startfeld.getPosX() - 2].getSpielfigur() == null) {
										startZiel[0] = startfeld;
										startZiel[1] = this.getSpielbrett()[startfeld.getPosY() - 2][startfeld.getPosX() - 2];
										return startZiel;
									}
								}
							}
						}
					} catch (java.lang.ArrayIndexOutOfBoundsException e) {

					}
					try {
						if (this.getSpielbrett()[startfeld.getPosY() - 1][startfeld.getPosX() + 1] != null) {
							if (this.getSpielbrett()[startfeld.getPosY() - 1][startfeld.getPosX() + 1].getSpielfigur() != null && this.getSpielbrett()[startfeld.getPosY() - 1][startfeld.getPosX() + 1].getSpielfigur().getFarbe() == FarbEnum.schwarz) {
								if (this.getSpielbrett()[startfeld.getPosY() - 2][startfeld.getPosX() + 2] != null) {
									if (this.getSpielbrett()[startfeld.getPosY() - 2][startfeld.getPosX() + 2].getSpielfigur() == null) {
										startZiel[0] = startfeld;
										startZiel[1] = this.getSpielbrett()[startfeld.getPosY() - 2][startfeld.getPosX() + 2];
										return startZiel;
									}
								}
							}
						}
					} catch (java.lang.ArrayIndexOutOfBoundsException e) {

					}
					try {
						if (this.getSpielbrett()[startfeld.getPosY() + 1][startfeld.getPosX() - 1] != null) {
							if (this.getSpielbrett()[startfeld.getPosY() + 1][startfeld.getPosX() - 1].getSpielfigur() != null && this.getSpielbrett()[startfeld.getPosY() + 1][startfeld.getPosX() - 1].getSpielfigur().getFarbe() == FarbEnum.schwarz) {
								if (this.getSpielbrett()[startfeld.getPosY() + 2][startfeld.getPosX() - 2] != null) {
									if (this.getSpielbrett()[startfeld.getPosY() + 2][startfeld.getPosX() - 2].getSpielfigur() == null) {
										startZiel[0] = startfeld;
										startZiel[1] = this.getSpielbrett()[startfeld.getPosY() + 2][startfeld.getPosX() - 2];
										return startZiel;
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
					if (this.getSpielbrett()[startfeld.getPosY() + 1][startfeld.getPosX() + 1] != null) {
						if (this.getSpielbrett()[startfeld.getPosY() + 1][startfeld.getPosX() + 1].getSpielfigur() != null && this.getSpielbrett()[startfeld.getPosY() + 1][startfeld.getPosX() + 1].getSpielfigur().getFarbe() == FarbEnum.weiß) {
							if (this.getSpielbrett()[startfeld.getPosY() + 2][startfeld.getPosX() + 2] != null) {
								if (this.getSpielbrett()[startfeld.getPosY() + 2][startfeld.getPosX() + 2].getSpielfigur() == null) {
									startZiel[0] = startfeld;
									startZiel[1] = this.getSpielbrett()[startfeld.getPosY() + 2][startfeld.getPosX() + 2];
									return startZiel;
								}
							}
						}
					}
				} catch (java.lang.ArrayIndexOutOfBoundsException e) {

				}
				try {
					if (this.getSpielbrett()[startfeld.getPosY() - 1][startfeld.getPosX() - 1] != null) {
						if (this.getSpielbrett()[startfeld.getPosY() - 1][startfeld.getPosX() - 1].getSpielfigur() != null && this.getSpielbrett()[startfeld.getPosY() - 1][startfeld.getPosX() - 1].getSpielfigur().getFarbe() == FarbEnum.weiß) {
							if (this.getSpielbrett()[startfeld.getPosY() - 2][startfeld.getPosX() - 2] != null) {
								if (this.getSpielbrett()[startfeld.getPosY() - 2][startfeld.getPosX() - 2].getSpielfigur() == null) {
									startZiel[0] = startfeld;
									startZiel[1] = this.getSpielbrett()[startfeld.getPosY() - 2][startfeld.getPosX() - 2];
									return startZiel;
								}
							}
						}
					}
				} catch (java.lang.ArrayIndexOutOfBoundsException e) {

				}
				try {
					if (this.getSpielbrett()[startfeld.getPosY() - 1][startfeld.getPosX() + 1] != null) {
						if (this.getSpielbrett()[startfeld.getPosY() - 1][startfeld.getPosX() + 1].getSpielfigur() != null && this.getSpielbrett()[startfeld.getPosY() - 1][startfeld.getPosX() + 1].getSpielfigur().getFarbe() == FarbEnum.weiß) {
							if (this.getSpielbrett()[startfeld.getPosY() - 2][startfeld.getPosX() + 2] != null) {
								if (this.getSpielbrett()[startfeld.getPosY() - 2][startfeld.getPosX() + 2].getSpielfigur() == null) {
									startZiel[0] = startfeld;
									startZiel[1] = this.getSpielbrett()[startfeld.getPosY() - 2][startfeld.getPosX() + 2];
									return startZiel;
								}
							}
						}
					}
				} catch (java.lang.ArrayIndexOutOfBoundsException e) {

				}
				try {
					if (this.getSpielbrett()[startfeld.getPosY() + 1][startfeld.getPosX() - 1] != null) {
						if (this.getSpielbrett()[startfeld.getPosY() + 1][startfeld.getPosX() - 1].getSpielfigur() != null && this.getSpielbrett()[startfeld.getPosY() + 1][startfeld.getPosX() - 1].getSpielfigur().getFarbe() == FarbEnum.weiß) {
							if (this.getSpielbrett()[startfeld.getPosY() + 2][startfeld.getPosX() - 2] != null) {
								if (this.getSpielbrett()[startfeld.getPosY() + 2][startfeld.getPosX() - 2].getSpielfigur() == null) {
									startZiel[0] = startfeld;
									startZiel[1] = this.getSpielbrett()[startfeld.getPosY() + 2][startfeld.getPosX() - 2];
									return startZiel;
								}
							}
						}
					}
				} catch (java.lang.ArrayIndexOutOfBoundsException e) {

				}
			}

		}
		return null;
	}

	/**
	 * Findet Heraus, ob Dame an dieser Stelle schlagen kann oder nicht.
	 * 
	 * @param startfeld
	 * @return boolean
	 */
	private boolean schlagMoeglichDame(Spielfeld startfeld) {

		try {
			for (int i = 1; i < this.getSpielbrett().length - 2; i++) {
				if (this.getSpielbrett()[startfeld.getPosY() + i][startfeld.getPosX() + i].getSpielfigur() != null) {
					if (this.getSpielbrett()[startfeld.getPosY() + i][startfeld.getPosX() + i].getSpielfigur().getFarbe() == this.getSpielbrett()[startfeld.getPosY()][startfeld.getPosX()].getSpielfigur().getFarbe()) {
						break;
					}
					if (this.getSpielbrett()[startfeld.getPosY() + i + 1][startfeld.getPosX() + i + 1].getSpielfigur() != null) {
						break;
					}
					if (startfeld.getSpielfigur().getFarbe() != this.getSpielbrett()[startfeld.getPosY() + i][startfeld.getPosX() + i].getSpielfigur().getFarbe()) {
						return true;
					}
				}
			}

		} catch (java.lang.ArrayIndexOutOfBoundsException e) {

		}
		try {
			for (int i = 1; i < this.getSpielbrett().length - 2; i++) {
				if (this.getSpielbrett()[startfeld.getPosY() - i][startfeld.getPosX() + i].getSpielfigur() != null) {
					if (this.getSpielbrett()[startfeld.getPosY() - i][startfeld.getPosX() + i].getSpielfigur().getFarbe() == this.getSpielbrett()[startfeld.getPosY()][startfeld.getPosX()].getSpielfigur().getFarbe()) {
						break;
					}
					if (this.getSpielbrett()[startfeld.getPosY() - i - 1][startfeld.getPosX() + i + 1].getSpielfigur() != null) {
						break;
					}
					if (startfeld.getSpielfigur().getFarbe() != this.getSpielbrett()[startfeld.getPosY() - i][startfeld.getPosX() + i].getSpielfigur().getFarbe()) {
						return true;
					}
				}
			}

		} catch (java.lang.ArrayIndexOutOfBoundsException e) {

		}
		try {
			for (int i = 1; i < this.getSpielbrett().length - 2; i++) {
				if (this.getSpielbrett()[startfeld.getPosY() + i][startfeld.getPosX() - i].getSpielfigur() != null) {
					if (this.getSpielbrett()[startfeld.getPosY() + i][startfeld.getPosX() - i].getSpielfigur().getFarbe() == this.getSpielbrett()[startfeld.getPosY()][startfeld.getPosX()].getSpielfigur().getFarbe()) {
						break;
					}
					if (this.getSpielbrett()[startfeld.getPosY() + i + 1][startfeld.getPosX() - i - 1].getSpielfigur() != null) {
						break;
					}
					if (startfeld.getSpielfigur().getFarbe() != this.getSpielbrett()[startfeld.getPosY() + i][startfeld.getPosX() - i].getSpielfigur().getFarbe()) {
						return true;
					}
				}
			}

		} catch (java.lang.ArrayIndexOutOfBoundsException e) {

		}
		try {
			for (int i = 1; i < this.getSpielbrett().length - 2; i++) {
				if (this.getSpielbrett()[startfeld.getPosY() - i][startfeld.getPosX() - i].getSpielfigur() != null) {
					if (this.getSpielbrett()[startfeld.getPosY() - i][startfeld.getPosX() - i].getSpielfigur().getFarbe() == this.getSpielbrett()[startfeld.getPosY()][startfeld.getPosX()].getSpielfigur().getFarbe()) {
						break;
					}
					if (this.getSpielbrett()[startfeld.getPosY() - i - 1][startfeld.getPosX() - i - 1].getSpielfigur() != null) {
						break;
					}
					if (startfeld.getSpielfigur().getFarbe() != this.getSpielbrett()[startfeld.getPosY() - i][startfeld.getPosX() - i].getSpielfigur().getFarbe()) {
						return true;
					}
				}
			}

		} catch (java.lang.ArrayIndexOutOfBoundsException e) {

		}
		return false;
	}

	/**
	 * Aktualisiert Spielbrett und findet eine schlagmoeglichkeit fuer den Stein,
	 * der noch einmal schlagen kann heraus, und gibt dieses Zielfeld zurueck.
	 */
	public Spielfeld actAgain(Spielfeld startfeld, Spielfeld[][] brett) {
		this.setSpielbrett(brett);
		Spielfeld[] startZiel = new Spielfeld[2];
		if (startfeld.getSpielfigur().isDame() == false) {
			startZiel = schlagMoeglichStartZiel(startfeld);
		} else {
			startZiel = schlagMoeglichDameStartZiel(startfeld);
		}
		System.out.println(startZiel[0].getSchachNotation());
		System.out.println(startZiel[1].getSchachNotation());
		return startZiel[1];

	}

	/**
	 * setter Farbe
	 * 
	 */
	public void setFarbe(FarbEnum farbe) {
		this.farbe = farbe;
	}

	/**
	 * Getter Spielbrett
	 * 
	 * @return Spielfeld[][]
	 */
	public Spielfeld[][] getSpielbrett() {
		return spielbrett;
	}

	/**
	 * getter Farbe
	 * 
	 * @return farbe
	 */
	public FarbEnum getFarbe() {
		return farbe;
	}

	/**
	 * setter Spielbrett
	 * 
	 * @param spielbrett
	 *          set
	 */
	public void setSpielbrett(Spielfeld[][] spielbrett) {
		this.spielbrett = spielbrett;
	}
}
