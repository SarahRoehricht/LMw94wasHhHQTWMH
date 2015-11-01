package backend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Ki_Carsten extends Ki {
	private FarbEnum farbe;

	private Spielfeld[][] spielbrett;

	public Spielfeld[][] getSpielbrett() {
		return spielbrett;
	}

	public void setSpielbrett(Spielfeld[][] spielbrett) {
		this.spielbrett = spielbrett;
	}

	public FarbEnum getFarbe() {
		return farbe;
	}

	Ki_Carsten(FarbEnum farbe) {
		super(farbe);
		// this.setFarbe(farbe);
	}

	public void setFarbe(FarbEnum farbe) {
		this.farbe = farbe;
	}

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
		System.out.println(startZiel[0]);
		System.out.println(startZiel[1]);
		return startZiel;

	}

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
						}
						// else {
						// if (MoveMoeglichDame(this.getSpielbrett()[j][i]) == true) {
						// Spielfeld[] abcd= new Spielfeld[2];
						// abcd=schlagMoeglichDameStartZiel(this.getSpielbrett()[j][i]);
						// startZielListMove.add(abcd);
						// System.out.println(startZielListMove.toString());
						// }
						// }
					}
				}
			}
		}

		return startZielListMove;
	}

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
								System.out.println(startZielListStein.toString());
							}
						} else {
							if (schlagMoeglichDame(this.getSpielbrett()[j][i]) == true) {
								Spielfeld[] abcd = new Spielfeld[2];
								abcd = schlagMoeglichDameStartZiel(this.getSpielbrett()[j][i]);
								startZielListDame.add(abcd);
								System.out.println(startZielListDame.toString());
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

	private Spielfeld[] schlagMoeglichDameStartZiel(Spielfeld startfeld) {
		Spielfeld[] startZiel = new Spielfeld[2];

		try {
			for (int i = 1; i < this.getSpielbrett().length - 2; i++) {
				if (this.getSpielbrett()[startfeld.getPosY() + i][startfeld.getPosX() + i].getSpielfigur() != null) {
					if (this.getSpielbrett()[startfeld.getPosY() + i + 1][startfeld.getPosX() + i + 1].getSpielfigur() == null) {
						if (startfeld.getSpielfigur().getFarbe() != this.getSpielbrett()[startfeld.getPosY() + i][startfeld.getPosX() + i].getSpielfigur().getFarbe()) {
							startZiel[0] = this.getSpielbrett()[startfeld.getPosY() + i][startfeld.getPosX() + i];
							startZiel[1] = this.getSpielbrett()[startfeld.getPosY() + i + 1][startfeld.getPosX() + i + 1];
							return startZiel;

						}
					}
				}
			}
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {

		}
		try {
			for (int i = 1; i < this.getSpielbrett().length - 2; i++) {
				if (this.getSpielbrett()[startfeld.getPosY() - i][startfeld.getPosX() + i].getSpielfigur() != null) {
					if (this.getSpielbrett()[startfeld.getPosY() - i - 1][startfeld.getPosX() + i + 1].getSpielfigur() == null) {
						if (startfeld.getSpielfigur().getFarbe() != this.getSpielbrett()[startfeld.getPosY() - i][startfeld.getPosX() + i].getSpielfigur().getFarbe()) {
							startZiel[0] = this.getSpielbrett()[startfeld.getPosY() - i][startfeld.getPosX() + i];
							startZiel[1] = this.getSpielbrett()[startfeld.getPosY() - i - 1][startfeld.getPosX() + i + 1];
							return startZiel;

						}
					}
				}
			}
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {

		}
		try {
			for (int i = 1; i < this.getSpielbrett().length - 2; i++) {
				if (this.getSpielbrett()[startfeld.getPosY() + i][startfeld.getPosX() - i].getSpielfigur() != null) {
					if (this.getSpielbrett()[startfeld.getPosY() + i + 1][startfeld.getPosX() - i - 1].getSpielfigur() == null) {
						if (startfeld.getSpielfigur().getFarbe() != this.getSpielbrett()[startfeld.getPosY() + i][startfeld.getPosX() - i].getSpielfigur().getFarbe()) {
							startZiel[0] = this.getSpielbrett()[startfeld.getPosY() + i][startfeld.getPosX() - i];
							startZiel[1] = this.getSpielbrett()[startfeld.getPosY() + i + 1][startfeld.getPosX() - i - 1];
							return startZiel;

						}
					}
				}
			}
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {

		}
		try {
			for (int i = 1; i < this.getSpielbrett().length - 2; i++) {
				if (this.getSpielbrett()[startfeld.getPosY() - i][startfeld.getPosX() - i].getSpielfigur() != null) {
					if (this.getSpielbrett()[startfeld.getPosY() - i - 1][startfeld.getPosX() - i - 1].getSpielfigur() == null) {
						if (startfeld.getSpielfigur().getFarbe() != this.getSpielbrett()[startfeld.getPosY() - i][startfeld.getPosX() - i].getSpielfigur().getFarbe()) {
							startZiel[0] = this.getSpielbrett()[startfeld.getPosY() - i][startfeld.getPosX() - i];
							startZiel[1] = this.getSpielbrett()[startfeld.getPosY() - i - 1][startfeld.getPosX() - i - 1];
							return startZiel;

						}
					}
				}
			}
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {

		}
		return null;
	}

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

	private boolean schlagMoeglichDame(Spielfeld startfeld) {
		try {
			for (int i = 1; i < this.getSpielbrett().length - 2; i++) {
				if (this.getSpielbrett()[startfeld.getPosY() + i][startfeld.getPosX() + i].getSpielfigur() != null) {
					if (this.getSpielbrett()[startfeld.getPosY() + i + 1][startfeld.getPosX() + i + 1].getSpielfigur() == null) {
						if (startfeld.getSpielfigur().getFarbe() != this.getSpielbrett()[startfeld.getPosY() + i][startfeld.getPosX() + i].getSpielfigur().getFarbe()) {
							return true;
						}
					}
				}
			}
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {

		}
		try {
			for (int i = 1; i < this.getSpielbrett().length - 2; i++) {
				if (this.getSpielbrett()[startfeld.getPosY() - i][startfeld.getPosX() + i].getSpielfigur() != null) {
					if (this.getSpielbrett()[startfeld.getPosY() - i - 1][startfeld.getPosX() + i + 1].getSpielfigur() == null) {
						if (startfeld.getSpielfigur().getFarbe() != this.getSpielbrett()[startfeld.getPosY() - i][startfeld.getPosX() + i].getSpielfigur().getFarbe()) {
							return true;
						}
					}
				}
			}
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {

		}
		try {
			for (int i = 1; i < this.getSpielbrett().length - 2; i++) {
				if (this.getSpielbrett()[startfeld.getPosY() + i][startfeld.getPosX() - i].getSpielfigur() != null) {
					if (this.getSpielbrett()[startfeld.getPosY() + i + 1][startfeld.getPosX() - i - 1].getSpielfigur() == null) {
						if (startfeld.getSpielfigur().getFarbe() != this.getSpielbrett()[startfeld.getPosY() + i][startfeld.getPosX() - i].getSpielfigur().getFarbe()) {
							return true;
						}
					}
				}
			}
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {

		}
		try {
			for (int i = 1; i < this.getSpielbrett().length - 2; i++) {
				if (this.getSpielbrett()[startfeld.getPosY() - i][startfeld.getPosX() - i].getSpielfigur() != null) {
					if (this.getSpielbrett()[startfeld.getPosY() - i - 1][startfeld.getPosX() - i - 1].getSpielfigur() == null) {
						if (startfeld.getSpielfigur().getFarbe() != this.getSpielbrett()[startfeld.getPosY() - i][startfeld.getPosX() - i].getSpielfigur().getFarbe()) {
							return true;
						}
					}
				}
			}
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {

		}
		return false;
	}

	public Spielfeld actAgain(Spielfeld startfeld, Spielfeld[][] brett) {
		this.setSpielbrett(brett);
		Spielfeld[] startZiel = new Spielfeld[2];
startZiel=schlagMoeglichStartZiel(startfeld);
		return startZiel[1];

	}

}
