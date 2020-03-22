package frontend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import backend.Spiel;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import backend.FarbEnum;

/**
 * 
 * @author A-2
 *
 */
public class EventHandler implements ActionListener {

	private Spielbrett spielbrett;
	private Color klickColor = new Color(130, 255, 174);
	private Color schwarzFeldColor = new Color(156, 93, 82);
	private Spiel spiel;
	private boolean schlagMoeglich;
	private ScrollPane sp;
	private int rundenZaehler = 1;
	private DameStartZielEingabe DEF;

	private ImageIcon Icon = new ImageIcon("check.png");
	private ImageIcon Icon2 = new ImageIcon("error.png");
	private MainWindow mw;

	private MenuLeiste menu;
	private int[] buttonkoords = new int[2];
	private int[] schlagStartKoords = new int[2];

	private int[] pusten1 = new int[2];
	private int[] pusten2 = new int[2];
	private String regel = "[a-lA-L]{1}[0-9]{1}[0-2]{0,1}-{1}[a-lA-L]{1}[0-9]{1}[0-2]{0,1}";
	private String ausgabe;
	private Font font = new Font("ARIAL", Font.BOLD, 14);

	public EventHandler(MainWindow mw) {
		this.mw = mw;
	}

	/**
	 * Findet zuerst heraus, ob ein JButton des Spielbretts JButton[][]
	 * gedrueckt wurde, falls bereits einer zuvor gedrueckt wurde falls ja, geht
	 * die einzelnen Spielzugoptionen durch und veraendert die einzelnen JBUtton
	 * Hintergruende, fuer die Steinauswahl, fuer eine weitere
	 * Schlagmoeglichkeit(gruen), und fuer pusten(rot). Menueleiste listened die
	 * einzelnen Menueobjekte ab, die diesen EH als ActionListener implementiert
	 * haben. Sie sollen die vom Benutzer erwarteten Aktionen durchfuehren. So
	 * sind diese Abfragen auch zu stellen. Der PustDialog wird bei einer
	 * Pustmoeglichkeit von 2 oder Mehr steinen aufgerufen, und gibt eine
	 * Auswahl von den 2 Pustmoeglichkeiten, die der Benutzer durch Anklicken
	 * der JButton im Dialog auswaehlen kann. Der KIDialog wird aufgerufen, wenn
	 * eine KI am ZUG ist, sie ruft die Ki.act Methode auf, und gibt ebenfalls
	 * wie bei einem normalen Zug die Zugfelder auf dem Scrollpane
	 * aus(sp.addTextToArea(s)) Der EastBereich uebernimmt die Abhandlung der
	 * Spielfeld Start-Ziel Eingabe per String, und reagiert auf ENTER oder
	 * JButton klick. Er ueberprueft die Eingabe ueber einen regulaeren Ausdruck
	 * der nur ein gueltiges Startfeld und Zielfeld erlaubt, getrennt mit einem
	 * '-' Der Spielgewonnen Dialog actionperformed Teil dient lediglich dazu
	 * das Fenster zu schliessen.
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		// ------------------------------------------------------------------------------------#-------------------------------------________SPIELBRETT_________-------========_______ANFANG_=======------
		if (schlagMoeglich == false) {
			if (schonGeklickt(spielbrett.Spielfelder) == true) {
				for (int i = 0; i < spielbrett.Spielfelder.length; i++) {
					for (int j = 0; j < spielbrett.Spielfelder[i].length; j++) {
						if (spielbrett.Spielfelder[j][i] == e.getSource()) {
							if (spielbrett.Spielfelder[j][i].getBackground() == klickColor
									|| spielbrett.Spielfelder[j][i]
											.getBackground() == Color.black) {
								if (spielbrett.Spielfelder[j][i]
										.getBackground() == klickColor) {
									spielbrett.Spielfelder[j][i]
											.setBackground(Color.black);
								} else {
									spielbrett.Spielfelder[j][i]
											.setBackground(klickColor);
									if (spiel.getSpielbrett().getBrett()[i][j]
											.getSpielfigur() == null) {
										if (spiel.getActiveSpieler().getFarbe() != spiel
												.getSpielbrett().getBrett()[buttonkoords[1]][buttonkoords[0]]
												.getSpielfigur().getFarbe()) {
											spielbrett.Spielfelder[j][i]
													.setBackground(Color.black);
											spielbrett.Spielfelder[buttonkoords[0]][buttonkoords[1]]
													.setBackground(Color.black);
										} else {
											if (spiel.getSpielbrett()
													.getBrett()[buttonkoords[1]][buttonkoords[0]]
													.getSpielfigur().isDame() == true) {

												if (spiel
														.moveDameLegit(
																spiel.getActiveSpieler(),
																spiel.getSpielbrett()
																		.getBrett()[buttonkoords[1]][buttonkoords[0]],
																spiel.getSpielbrett()
																		.getBrett()[i][j]) == true) {

													ArrayList<int[]> pusten = spiel
															.doCheckPusten(spiel
																	.getSpielbrett()
																	.getBrett()[buttonkoords[1]][buttonkoords[0]]);
													if (pusten.isEmpty()) {

														spiel.move(
																spiel.getSpielbrett()
																		.getBrett()[buttonkoords[1]][buttonkoords[0]],
																spiel.getSpielbrett()
																		.getBrett()[i][j]);
														spielerRotation();
														sp.addToTextArea(spiel
																.getSpielbrett()
																.getBrett()[buttonkoords[1]][buttonkoords[0]]
																.getSchachNotation()
																+ " auf "
																+ spiel.getSpielbrett()
																		.getBrett()[i][j]
																		.getSchachNotation()
																+ ".");
													} else if (pusten.size() == 1) {
														int[] abc = new int[2];
														abc = pusten.get(0);
														spiel.removeSpielfigur(spiel
																.getSpielbrett()
																.getBrett()[abc[0]][abc[1]]);
														sp.addToTextArea("Schlag war moeglich an Stelle "
																+ spiel.getSpielbrett()
																		.getBrett()[abc[0]][abc[1]]
																		.getSchachNotation()
																+ ". Stein wird entfernt.");
														if (spiel
																.getSpielbrett()
																.getBrett()[abc[0]][abc[1]] == spiel
																.getSpielbrett()
																.getBrett()[buttonkoords[1]][buttonkoords[0]]) {

														} else {
															spiel.move(
																	spiel.getSpielbrett()
																			.getBrett()[buttonkoords[1]][buttonkoords[0]],
																	spiel.getSpielbrett()
																			.getBrett()[i][j]);
															spielerRotation();
															spielbrett.Spielfelder[j][i]
																	.setBackground(Color.black);
															spielbrett.Spielfelder[buttonkoords[0]][buttonkoords[1]]
																	.setBackground(Color.black);
														}
													} else if (pusten.size() == 2) {
														int[] abc = new int[2];
														abc = pusten.get(0);
														int[] abc2 = new int[2];
														abc2 = pusten.get(1);
														pusten1[0] = abc[0];
														pusten1[1] = abc[1];
														pusten2[0] = abc2[0];
														pusten2[1] = abc2[1];
														spielbrett.Spielfelder[j][i]
																.setBackground(Color.black);
														spielbrett.Spielfelder[buttonkoords[0]][buttonkoords[1]]
																.setBackground(Color.black);
														spielbrett.Spielfelder[pusten1[1]][pusten1[0]]
																.setBackground(Color.red);
														spielbrett.Spielfelder[pusten2[1]][pusten2[0]]
																.setBackground(Color.red);
														spielbrett.pustDialogText1
																.setText("Es wurde gezogen obwohl man haette schlagen koennen.");
														spielbrett.pustDialogText2
																.setText(spiel
																		.getActiveSpieler()
																		+ ": Trenne dich von einem Stein.");
														spielbrett.pustDialogButton1
																.setText(spiel
																		.getSpielbrett()
																		.getBrett()[abc[0]][abc[1]]
																		.getSpielfigur()
																		+ " auf "
																		+ spiel.getSpielbrett()
																				.getBrett()[abc[0]][abc[1]]
																				.getSchachNotation()
																		+ ".");
														spielbrett.pustDialogButton2
																.setText(spiel
																		.getSpielbrett()
																		.getBrett()[abc2[0]][abc2[1]]
																		.getSpielfigur()
																		+ " auf "
																		+ spiel.getSpielbrett()
																				.getBrett()[abc2[0]][abc2[1]]
																				.getSchachNotation()
																		+ ".");
														spielbrett.pustDialog
																.setVisible(true);
														spiel.move(
																spiel.getSpielbrett()
																		.getBrett()[buttonkoords[1]][buttonkoords[0]],
																spiel.getSpielbrett()
																		.getBrett()[i][j]);

														spielerRotation();
													}
													spielbrett.Spielfelder[j][i]
															.setBackground(Color.black);
													spielbrett.Spielfelder[buttonkoords[0]][buttonkoords[1]]
															.setBackground(Color.black);

												}
												if (spiel
														.schlagenDameLegit(
																spiel.getActiveSpieler(),
																spiel.getSpielbrett()
																		.getBrett()[buttonkoords[1]][buttonkoords[0]],
																spiel.getSpielbrett()
																		.getBrett()[i][j]) == true) {
													spiel.schlagenDame(
															spiel.getActiveSpieler(),
															spiel.getSpielbrett()
																	.getBrett()[buttonkoords[1]][buttonkoords[0]],
															spiel.getSpielbrett()
																	.getBrett()[i][j]);
													sp.addToTextArea("von "
															+ spiel.getSpielbrett()
																	.getBrett()[buttonkoords[1]][buttonkoords[0]]
																	.getSchachNotation()
															+ " auf "
															+ spiel.getSpielbrett()
																	.getBrett()[i][j]
																	.getSchachNotation()
															+ ", dabei Stein geschlagen.");
													if (spiel
															.schlagMoeglichDame(spiel
																	.getSpielbrett()
																	.getBrett()[i][j]) == true) {
														schlagMoeglich = true;
														spielbrett.Spielfelder[buttonkoords[0]][buttonkoords[1]]
																.setBackground(Color.black);
														schlagStartKoords[0] = i;
														schlagStartKoords[1] = j;
														sp.addToTextArea(spiel
																.getSpielbrett()
																.getBrett()[i][j]
																.getSpielfigur()
																+ " auf "
																+ spiel.getSpielbrett()
																		.getBrett()[i][j]
																		.getSchachNotation()
																+ " kann noch einmal schlagen.");
													} else {
														schlagMoeglich = false;
														spielbrett.Spielfelder[j][i]
																.setBackground(Color.black);
														spielbrett.Spielfelder[buttonkoords[0]][buttonkoords[1]]
																.setBackground(Color.black);
														spielerRotation();
													}
												} else {
													spielbrett.Spielfelder[j][i]
															.setBackground(Color.black);
												}
											} else if (spiel.getSpielbrett()
													.getBrett()[buttonkoords[1]][buttonkoords[0]]
													.getSpielfigur().isDame() == false) {

												if (spiel
														.doMoveStein(
																spiel.getActiveSpieler(),
																spiel.getSpielbrett()
																		.getBrett()[buttonkoords[1]][buttonkoords[0]],
																spiel.getSpielbrett()
																		.getBrett()[i][j]) == false) {

													if (spiel
															.doSchlagStein(
																	spiel.getActiveSpieler(),
																	spiel.getSpielbrett()
																			.getBrett()[buttonkoords[1]][buttonkoords[0]],
																	spiel.getSpielbrett()
																			.getBrett()[i][j]) == true) {
														sp.addToTextArea("von "
																+ spiel.getSpielbrett()
																		.getBrett()[buttonkoords[1]][buttonkoords[0]]
																		.getSchachNotation()
																+ " auf "
																+ spiel.getSpielbrett()
																		.getBrett()[i][j]
																		.getSchachNotation()
																+ ", dabei Stein geschlagen.");
														if (spiel
																.SchlagMoeglich(spiel
																		.getSpielbrett()
																		.getBrett()[i][j]) == true) {
															schlagMoeglich = true;
															sp.addToTextArea(spiel
																	.getSpielbrett()
																	.getBrett()[i][j]
																	.getSpielfigur()
																	+ " auf "
																	+ spiel.getSpielbrett()
																			.getBrett()[i][j]
																			.getSchachNotation()
																	+ " kann noch einmal schlagen.");
															spielbrett.Spielfelder[buttonkoords[0]][buttonkoords[1]]
																	.setBackground(Color.black);
															schlagStartKoords[0] = i;
															schlagStartKoords[1] = j;

														} else {
															schlagMoeglich = false;
															spielbrett.Spielfelder[j][i]
																	.setBackground(Color.black);
															spielbrett.Spielfelder[buttonkoords[0]][buttonkoords[1]]
																	.setBackground(Color.black);
															spielerRotation();
														}
													} else {
														spielbrett.Spielfelder[j][i]
																.setBackground(Color.black);
													}

												} else {
													ArrayList<int[]> pusten = spiel
															.doCheckPusten(spiel
																	.getSpielbrett()
																	.getBrett()[buttonkoords[1]][buttonkoords[0]]);
													if (pusten.isEmpty()) {

														spiel.move(
																spiel.getSpielbrett()
																		.getBrett()[buttonkoords[1]][buttonkoords[0]],
																spiel.getSpielbrett()
																		.getBrett()[i][j]);
														sp.addToTextArea(spiel
																.getSpielbrett()
																.getBrett()[buttonkoords[1]][buttonkoords[0]]
																.getSchachNotation()
																+ " auf "
																+ spiel.getSpielbrett()
																		.getBrett()[i][j]
																		.getSchachNotation()
																+ ".");
													} else if (pusten.size() == 1) {
														int[] abc = new int[2];
														abc = pusten.get(0);
														spiel.removeSpielfigur(spiel
																.getSpielbrett()
																.getBrett()[abc[0]][abc[1]]);
														sp.addToTextArea("Schlag war moeglich an Stelle "
																+ spiel.getSpielbrett()
																		.getBrett()[abc[0]][abc[1]]
																		.getSchachNotation()
																+ ". Stein wird entfernt.");
														if (spiel
																.getSpielbrett()
																.getBrett()[abc[0]][abc[1]] == spiel
																.getSpielbrett()
																.getBrett()[buttonkoords[1]][buttonkoords[0]]) {

														} else {
															spiel.move(
																	spiel.getSpielbrett()
																			.getBrett()[buttonkoords[1]][buttonkoords[0]],
																	spiel.getSpielbrett()
																			.getBrett()[i][j]);
														}
													} else if (pusten.size() == 2) {
														int[] abc = new int[2];
														abc = pusten.get(0);
														int[] abc2 = new int[2];
														abc2 = pusten.get(1);
														pusten1[0] = abc[0];
														pusten1[1] = abc[1];
														pusten2[0] = abc2[0];
														pusten2[1] = abc2[1];
														spielbrett.Spielfelder[j][i]
																.setBackground(Color.black);
														spielbrett.Spielfelder[buttonkoords[0]][buttonkoords[1]]
																.setBackground(Color.black);
														spielbrett.Spielfelder[pusten1[1]][pusten1[0]]
																.setBackground(Color.red);
														spielbrett.Spielfelder[pusten2[1]][pusten2[0]]
																.setBackground(Color.red);
														spielbrett.pustDialogText1
																.setText("Es wurde gezogen obwohl man haette schlagen koennen.");
														spielbrett.pustDialogText2
																.setText(spiel
																		.getActiveSpieler()
																		+ ": Trenne dich von einem Stein.");
														spielbrett.pustDialogButton1
																.setText(spiel
																		.getSpielbrett()
																		.getBrett()[abc[0]][abc[1]]
																		.getSpielfigur()
																		+ " auf "
																		+ spiel.getSpielbrett()
																				.getBrett()[abc[0]][abc[1]]
																				.getSchachNotation()
																		+ ".");
														spielbrett.pustDialogButton2
																.setText(spiel
																		.getSpielbrett()
																		.getBrett()[abc2[0]][abc2[1]]
																		.getSpielfigur()
																		+ " auf "
																		+ spiel.getSpielbrett()
																				.getBrett()[abc2[0]][abc2[1]]
																				.getSchachNotation()
																		+ ".");
														spielbrett.pustDialog
																.setVisible(true);
														spiel.move(
																spiel.getSpielbrett()
																		.getBrett()[buttonkoords[1]][buttonkoords[0]],
																spiel.getSpielbrett()
																		.getBrett()[i][j]);

													}
													spielbrett.Spielfelder[j][i]
															.setBackground(Color.black);
													spielbrett.Spielfelder[buttonkoords[0]][buttonkoords[1]]
															.setBackground(Color.black);
													spielerRotation();
												}
											}

											setzeSteine();
										}
									} else {
										spielbrett.Spielfelder[buttonkoords[0]][buttonkoords[1]]
												.setBackground(Color.black);
									}

								}

							}

						}
					}
				}
			} else {
				for (int i = 0; i < spielbrett.Spielfelder.length; i++) {
					for (int j = 0; j < spielbrett.Spielfelder[i].length; j++) {
						if (spielbrett.Spielfelder[j][i] == e.getSource()) {

							if (spielbrett.Spielfelder[j][i].getBackground() == Color.black) {
								if (spielbrett.Spielfelder[j][i].getIcon() != null) {

									spielbrett.Spielfelder[j][i]
											.setBackground(klickColor);
								}

							}
						}
					}
				}
			}

		} else {
			for (int i = 0; i < spielbrett.Spielfelder.length; i++) {
				for (int j = 0; j < spielbrett.Spielfelder[i].length; j++) {
					if (spielbrett.Spielfelder[j][i] == e.getSource()) {
						if (spielbrett.Spielfelder[j][i].getBackground() == Color.black) {
							if (spielbrett.Spielfelder[j][i].getIcon() == null) {
								if (spiel.getSpielbrett().getBrett()[schlagStartKoords[0]][schlagStartKoords[1]]
										.getSpielfigur().isDame() == true) {
									if (spiel
											.schlagenDameLegit(
													spiel.getActiveSpieler(),
													spiel.getSpielbrett()
															.getBrett()[schlagStartKoords[0]][schlagStartKoords[1]],
													spiel.getSpielbrett()
															.getBrett()[i][j]) == true) {
										spiel.schlagenDame(
												spiel.getActiveSpieler(),
												spiel.getSpielbrett()
														.getBrett()[schlagStartKoords[0]][schlagStartKoords[1]],
												spiel.getSpielbrett()
														.getBrett()[i][j]);
										spielbrett.Spielfelder[schlagStartKoords[1]][schlagStartKoords[0]]
												.setBackground(Color.black);
										sp.addToTextArea("von "
												+ spiel.getSpielbrett()
														.getBrett()[schlagStartKoords[0]][schlagStartKoords[1]]
														.getSchachNotation()
												+ " auf "
												+ spiel.getSpielbrett()
														.getBrett()[i][j]
														.getSchachNotation()
												+ ", dabei Stein geschlagen.");
										if (spiel
												.schlagMoeglichDame(spiel
														.getSpielbrett()
														.getBrett()[i][j]) == true) {
											schlagMoeglich = true;

											schlagStartKoords[0] = i;
											schlagStartKoords[1] = j;
											spielbrett.Spielfelder[j][i]
													.setBackground(klickColor);
											sp.addToTextArea(spiel
													.getSpielbrett().getBrett()[schlagStartKoords[0]][schlagStartKoords[1]]
													+ " auf "
													+ spiel.getSpielbrett()
															.getBrett()[schlagStartKoords[0]][schlagStartKoords[1]]
															.getSchachNotation()
													+ " kann noch einmal schlagen.");
										} else {
											schlagMoeglich = false;
											spielbrett.Spielfelder[schlagStartKoords[1]][schlagStartKoords[0]]
													.setBackground(Color.black);
											spielbrett.Spielfelder[i][j]
													.setBackground(Color.black);

										}
									}

								} else if (spiel.getSpielbrett().getBrett()[schlagStartKoords[0]][schlagStartKoords[1]]
										.getSpielfigur().isDame() == false) {
									if (spiel
											.doSchlagStein(
													spiel.getActiveSpieler(),
													spiel.getSpielbrett()
															.getBrett()[schlagStartKoords[0]][schlagStartKoords[1]],
													spiel.getSpielbrett()
															.getBrett()[i][j]) == true) {
										sp.addToTextArea("von "
												+ spiel.getSpielbrett()
														.getBrett()[schlagStartKoords[0]][schlagStartKoords[1]]
														.getSchachNotation()
												+ " auf "
												+ spiel.getSpielbrett()
														.getBrett()[i][j]
														.getSchachNotation()
												+ ", dabei Stein geschlagen.");
										spielbrett.Spielfelder[schlagStartKoords[0]][schlagStartKoords[1]]
												.setBackground(Color.black);
										if (spiel
												.SchlagMoeglich(spiel
														.getSpielbrett()
														.getBrett()[i][j]) == true) {
											schlagMoeglich = true;
											spielbrett.Spielfelder[schlagStartKoords[1]][schlagStartKoords[0]]
													.setBackground(Color.black);
											schlagStartKoords[0] = i;
											schlagStartKoords[1] = j;
											spielbrett.Spielfelder[j][i]
													.setBackground(klickColor);
											sp.addToTextArea(spiel
													.getSpielbrett().getBrett()[schlagStartKoords[0]][schlagStartKoords[1]]
													+ " auf "
													+ spiel.getSpielbrett()
															.getBrett()[schlagStartKoords[0]][schlagStartKoords[1]]
															.getSchachNotation()
													+ " kann noch einmal schlagen.");
										} else {
											schlagMoeglich = false;
											spielbrett.Spielfelder[schlagStartKoords[1]][schlagStartKoords[0]]
													.setBackground(Color.black);
											spielbrett.Spielfelder[i][j]
													.setBackground(Color.black);
										}
									} else {

									}
								}

								if (schlagMoeglich == false) {
									spielbrett.Spielfelder[i][j]
											.setBackground(Color.black);
									spielbrett.Spielfelder[schlagStartKoords[1]][schlagStartKoords[0]]
											.setBackground(Color.black);
									spielerRotation();
								} else {

								}
								setzeSteine();
							}
						}
					}
				}
			}

		}

		// ------------------------------------------------------------------------------------#-------------------------------------________SPIELBRETT_________-------========_______ENDE_____=======------

		// ------------------------------------------------------------------------------------#-------------------------------------________PUSTENDIALOG_________-------========_______ANFANG_=======------

		if (e.getSource() == spielbrett.pustDialogButton1
				|| e.getSource() == spielbrett.pustDialogButton2) {
			if (e.getSource() == spielbrett.pustDialogButton1) {
				sp.addToTextArea(spiel.getSpielbrett().getBrett()[pusten1[0]][pusten1[1]]
						.getSpielfigur()
						+ " an Stelle: "
						+ spiel.getSpielbrett().getBrett()[pusten1[0]][pusten1[1]]
								.getSchachNotation() + " entfernt.");
				spiel.removeSpielfigur(spiel.getSpielbrett().getBrett()[pusten1[0]][pusten1[1]]);

			}
			if (e.getSource() == spielbrett.pustDialogButton2) {
				sp.addToTextArea(spiel.getSpielbrett().getBrett()[pusten2[0]][pusten2[1]]
						.getSpielfigur()
						+ " an Stelle: "
						+ spiel.getSpielbrett().getBrett()[pusten2[0]][pusten2[1]]
								.getSchachNotation() + " entfernt.");
				spiel.removeSpielfigur(spiel.getSpielbrett().getBrett()[pusten2[0]][pusten2[1]]);

			}
			spielbrett.Spielfelder[pusten1[1]][pusten1[0]]
					.setBackground(Color.black);
			spielbrett.Spielfelder[pusten2[1]][pusten2[0]]
					.setBackground(Color.black);
			spielbrett.pustDialog.setVisible(false);
			setzeSteine();
		}
		// ------------------------------------------------------------------------------------#-------------------------------------________PUSTENDIALOG_________-------========_______ENDE_=======------

		// ------------------------------------------------------------------------------------#-------------------------------------________SPIELGEWONNEN_________-------========_______ANFANG_=======------

		if (e.getSource() == spielbrett.spielGewonnenDialogButton) {
			spielbrett.spielGewonnenDialog.setVisible(false);
		}

		// ------------------------------------------------------------------------------------#-------------------------------------________SPIELGEWONNEN_________-------========_______ENDE_=======------
		// ------------------------------------------------------------------------------------#-------------------------------------________KIDIALOG_________-------========_______ANFANG_=======------
		if (e.getSource() == spielbrett.kiButton) {
			int[] kikoords = new int[4];

			kikoords = spiel.act(spiel.getActiveSpieler());
			// //System.out.println(kikoords);
			if (spiel.getSpielbrett().getBrett()[kikoords[0]][kikoords[1]]
					.getSpielfigur().isDame() == true) {

				if (spiel
						.moveDameLegit(
								spiel.getActiveSpieler(),
								spiel.getSpielbrett().getBrett()[kikoords[0]][kikoords[1]],
								spiel.getSpielbrett().getBrett()[kikoords[2]][kikoords[3]]) == true) {

					spiel.move(
							spiel.getSpielbrett().getBrett()[kikoords[0]][kikoords[1]],
							spiel.getSpielbrett().getBrett()[kikoords[2]][kikoords[3]]);
					spielerRotation();
					sp.addToTextArea(spiel.getSpielbrett().getBrett()[kikoords[0]][kikoords[1]]
							.getSchachNotation()
							+ " auf "
							+ spiel.getSpielbrett().getBrett()[kikoords[2]][kikoords[3]]
									.getSchachNotation() + ".");

				}

				if (spiel
						.schlagenDameLegit(
								spiel.getActiveSpieler(),
								spiel.getSpielbrett().getBrett()[kikoords[0]][kikoords[1]],
								spiel.getSpielbrett().getBrett()[kikoords[2]][kikoords[3]]) == true) {
					spiel.schlagenDame(
							spiel.getActiveSpieler(),
							spiel.getSpielbrett().getBrett()[kikoords[0]][kikoords[1]],
							spiel.getSpielbrett().getBrett()[kikoords[2]][kikoords[3]]);
					sp.addToTextArea("von "
							+ spiel.getSpielbrett().getBrett()[kikoords[0]][kikoords[1]]
									.getSchachNotation()
							+ " auf "
							+ spiel.getSpielbrett().getBrett()[kikoords[2]][kikoords[3]]
									.getSchachNotation()
							+ ", dabei Stein geschlagen.");
					while (spiel.schlagMoeglichDame(spiel.getSpielbrett()
							.getBrett()[kikoords[2]][kikoords[3]]) == true) {
						sp.addToTextArea(spiel.getSpielbrett().getBrett()[kikoords[2]][kikoords[3]]
								.getSpielfigur()
								+ " auf "
								+ spiel.getSpielbrett().getBrett()[kikoords[2]][kikoords[3]]
										.getSchachNotation()
								+ " kann noch einmal schlagen.");

						int[] zielkoords = new int[2];
						zielkoords = spiel
								.getActiveSpieler()
								.getKi()
								.actAgain(
										spiel.getSpielbrett().getBrett()[kikoords[2]][kikoords[3]],
										spiel.getSpielbrett().getBrett());

						spiel.schlagenDame(
								spiel.getActiveSpieler(),
								spiel.getSpielbrett().getBrett()[kikoords[2]][kikoords[3]],
								spiel.getSpielbrett().getBrett()[zielkoords[0]][zielkoords[1]]);
						sp.addToTextArea("von "
								+ spiel.getSpielbrett().getBrett()[kikoords[2]][kikoords[3]]
										.getSchachNotation()
								+ " auf "
								+ spiel.getSpielbrett().getBrett()[zielkoords[0]][zielkoords[1]]
										.getSchachNotation()
								+ ", dabei Stein geschlagen.");
						kikoords[2] = spiel.getSpielbrett().getBrett()[zielkoords[0]][zielkoords[1]]
								.getPosY();
						kikoords[3] = spiel.getSpielbrett().getBrett()[zielkoords[0]][zielkoords[1]]
								.getPosX();

					}
					if (spiel.schlagMoeglichDame(spiel.getSpielbrett()
							.getBrett()[kikoords[2]][kikoords[3]]) == false) {
						spielerRotation();
					}
				} else {

				}
			} else if (spiel.getSpielbrett().getBrett()[kikoords[0]][kikoords[1]]
					.getSpielfigur().isDame() == false) {

				if (spiel
						.doMoveStein(
								spiel.getActiveSpieler(),
								spiel.getSpielbrett().getBrett()[kikoords[0]][kikoords[1]],
								spiel.getSpielbrett().getBrett()[kikoords[2]][kikoords[3]]) == false) {

					if (spiel
							.doSchlagStein(
									spiel.getActiveSpieler(),
									spiel.getSpielbrett().getBrett()[kikoords[0]][kikoords[1]],
									spiel.getSpielbrett().getBrett()[kikoords[2]][kikoords[3]]) == true) {
						sp.addToTextArea("von "
								+ spiel.getSpielbrett().getBrett()[kikoords[0]][kikoords[1]]
										.getSchachNotation()
								+ " auf "
								+ spiel.getSpielbrett().getBrett()[kikoords[2]][kikoords[3]]
										.getSchachNotation()
								+ ", dabei Stein geschlagen.");
						while (spiel.schlagMoeglichDame(spiel.getSpielbrett()
								.getBrett()[kikoords[2]][kikoords[3]]) == true) {
							sp.addToTextArea(spiel.getSpielbrett().getBrett()[kikoords[2]][kikoords[3]]
									.getSpielfigur()
									+ " auf "
									+ spiel.getSpielbrett().getBrett()[kikoords[2]][kikoords[3]]
											.getSchachNotation()
									+ " kann noch einmal schlagen.");

							int[] zielkoords = new int[2];
							zielkoords = spiel
									.getActiveSpieler()
									.getKi()
									.actAgain(
											spiel.getSpielbrett().getBrett()[kikoords[2]][kikoords[3]],
											spiel.getSpielbrett().getBrett());

							spiel.doSchlagStein(
									spiel.getActiveSpieler(),
									spiel.getSpielbrett().getBrett()[kikoords[2]][kikoords[3]],
									spiel.getSpielbrett().getBrett()[zielkoords[0]][zielkoords[1]]);
							sp.addToTextArea("von "
									+ spiel.getSpielbrett().getBrett()[kikoords[2]][kikoords[3]]
											.getSchachNotation()
									+ " auf "
									+ spiel.getSpielbrett().getBrett()[zielkoords[0]][zielkoords[1]]
											.getSchachNotation()
									+ ", dabei Stein geschlagen.");
							kikoords[2] = spiel.getSpielbrett().getBrett()[zielkoords[0]][zielkoords[1]]
									.getPosY();
							kikoords[3] = spiel.getSpielbrett().getBrett()[zielkoords[0]][zielkoords[1]]
									.getPosX();

						}
						if (spiel.schlagMoeglichDame(spiel.getSpielbrett()
								.getBrett()[kikoords[2]][kikoords[3]]) == false) {
							spielerRotation();
						}
					} else {

					}

				} else {

					spiel.move(
							spiel.getSpielbrett().getBrett()[kikoords[0]][kikoords[1]],
							spiel.getSpielbrett().getBrett()[kikoords[2]][kikoords[3]]);
					sp.addToTextArea(spiel.getSpielbrett().getBrett()[kikoords[0]][kikoords[1]]
							.getSchachNotation()
							+ " auf "
							+ spiel.getSpielbrett().getBrett()[kikoords[2]][kikoords[3]]
									.getSchachNotation() + ".");
					spielerRotation();
				}
			}

			setzeSteine();

			if (spiel.getSpieler()[0].getKi() != null
					&& spiel.getSpieler()[1].getKi() != null) {

			} else {
				spielbrett.kiDialog.setVisible(false);
			}

		}

		// ------------------------------------------------------------------------------------#-------------------------------------________KIDIALOG_________-------========_______ENDE_=======------

		// ------------------------------------------------------------------------------------#-------------------------------------________MENUELEISTE_________-------========_______ANFANG_=======------

		// LADEN

		if (e.getSource() == menu.jmi_laden && spiel != null) {
			menu.jd_speichernVorLaden.setVisible(true);

		}

		if (e.getSource() == menu.jmi_laden && spiel == null) {
			menu.jfc_speichernVorLaden
					.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			String dateiName = menu.jfc_speichernVorLaden.getSelectedFile()
					.getName();
			String dateiTyp = menu.jfc_speichernVorLaden.getFileFilter()
					.getDescription();
			String pfad = menu.jfc_speichernVorLaden.getSelectedFile()
					.getAbsolutePath();
			int pfadLaenge = pfad.length();
			int dateiNameLaenge = dateiName.length();
			String pfadOhneDateiname = pfad.substring(0, pfadLaenge
					- dateiNameLaenge);

			/******** Testausgabe **********/
			// //System.out.println(dateiName + "." + dateiTyp);
			// System.out.println(pfad);
			// System.out.println(pfadOhneDateiname);
			/****************************/

		}
		if (e.getSource() == menu.vorLadenSpeichern_ja) {
			menu.jd_speichernVorLaden.dispose();
			menu.jfc_speichernVorLaden.setVisible(true);
			menu.jfc_speichernVorLaden.showSaveDialog(menu);

			menu.jfc_speichernVorLaden
					.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			String dateiName = menu.jfc_speichernVorLaden.getSelectedFile()
					.getName();
			String dateiTyp = menu.jfc_speichernVorLaden.getFileFilter()
					.getDescription();
			String pfad = menu.jfc_speichernVorLaden.getSelectedFile()
					.getAbsolutePath();
			int pfadLaenge = pfad.length();
			int dateiNameLaenge = dateiName.length();
			String pfadOhneDateiname = pfad.substring(0, pfadLaenge
					- dateiNameLaenge);

			/******** Testausgabe **********/
			// System.out.println(dateiName + "." + dateiTyp);
			// System.out.println(pfad);
			// System.out.println(pfadOhneDateiname);
			/****************************/

			if (dateiTyp.equals("csv")) {
				spiel.saveCSV(dateiName);
				sp.addToTextArea("Spielstand wurde als .csv gespeichert");
			}
			if (dateiTyp.equals("ser")) {
				spiel.saveSerialize(dateiName);
				sp.addToTextArea("Spielstand wurde als .ser gespeichert");
			} else if (dateiTyp.equals("pdf")) {
				createPicture(dateiName);
				spiel.savePDF(dateiName);
				sp.addToTextArea("Spielstand wurde als .pdf gespeichert Laden von .pdf nicht moeglich");

			}
		}

		if (e.getSource() == menu.vorLadenSpeichern_nein) {
			menu.jd_speichernVorLaden.dispose();
			menu.jfc_laden.setVisible(true);
			int rueckgabeWert = menu.jfc_laden.showOpenDialog(menu);
			if (rueckgabeWert == JFileChooser.APPROVE_OPTION) {
				String datei = menu.jfc_laden.getSelectedFile().getName();
				String dateiName = datei.substring(0, menu.jfc_laden
						.getSelectedFile().getName().length() - 4);
				String dateiEndung = datei.substring(datei.length() - 3);
				if (dateiEndung.equals("csv")) {
					try {
						spiel.laden(dateiName, "csv");
						setzeSteine();
						sp.addToTextArea("Spielstand wurde geladen");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} else {
					try {
						spiel.loadSerialize(dateiName);
						sp.addToTextArea("Spielstand wurde geladen");
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}
		}

		// Neues Spiel starten mÃ¶glich , nur wenn Spiel != null
		// ansonsten wurde ja noch kein Spiel gespielt, also muss auch keines
		// geladen werden.
		if (e.getSource() == menu.jmi_neuesSpiel && spiel != null) {
			menu.jd_neuesSpiel.setVisible(true);
		}

		if (e.getSource() == menu.jmi_neuesSpiel && spiel == null) {
			menu.jf.setVisible(true);
		}
		if (e.getSource() == menu.verlassen_ja) {
			menu.jd_neuesSpiel.dispose();
			// neuesSpielFenster
			menu.jf.setVisible(true);
		}
		if (e.getSource() == menu.verlassen_nein) {
			menu.jd_neuesSpiel.dispose();
		}
		if (e.getSource() == menu.jb_abbruch) {
			// System.out.println("blub abbruch");
			menu.jf.dispose();
		}

		// Mensch RadioButton als default ausgewählt

		if (e.getSource() == menu.jb_bestaetigen) {

			boolean dame1;
			boolean dame2;
			if (menu.rb_ki1.isSelected()) {
				// menu.rb_ki1.setSelected(true);
				// menu.rb_mensch1.setSelected(false);
				dame1 = true;

			} else {
				// menu.rb_ki1.setSelected(false);
				// menu.rb_mensch1.setSelected(true);
				dame1 = false;
			}
			if (menu.rb_ki2.isSelected()) {
				// menu.rb_mensch2.setSelected(false);
				// menu.rb_ki2.setSelected(true);
				dame2 = true;
			} else {
				// menu.rb_mensch2.setSelected(true);
				// menu.rb_ki2.setSelected(false);
				dame2 = false;
			}

			String nameWeiß = menu.jt_spieler1.getText();
			String nameSchwarz = menu.jt_spieler2.getText();

			if (e.getSource() == menu.jb_bestaetigen
					&& (nameWeiß.length() < 3)) {
				JOptionPane
						.showMessageDialog(
								null,
								"Spieler 1: Ihr Name muss mindestens 3 Zeichen lang sein!",
								"Unvollständige Eingabe!",
								JOptionPane.WARNING_MESSAGE);
			}
			if (e.getSource() == menu.jb_bestaetigen
					&& (nameWeiß.length() > 15)) {
				JOptionPane
						.showMessageDialog(
								null,
								"Spieler 1: Ihr Name darf maximal 15 Zeichen lang sein!",
								"Unvollständige Eingabe!",
								JOptionPane.WARNING_MESSAGE);
			}
			if (e.getSource() == menu.jb_bestaetigen
					&& (nameSchwarz.length() > 15)) {
				JOptionPane
						.showMessageDialog(
								null,
								"Spieler 2: Ihr Name darf maximal 15 Zeichen lang sein!",
								"Unvollständige Eingabe!",
								JOptionPane.WARNING_MESSAGE);
			}
			if (e.getSource() == menu.jb_bestaetigen
					&& (nameSchwarz.length() < 3)) {
				JOptionPane
						.showMessageDialog(
								null,
								"Spieler 2: Ihr Name muss mindestens 3 Zeichen lang sein!",
								"Unvollständige Eingabe!",
								JOptionPane.WARNING_MESSAGE);
			}
			if (e.getSource() == menu.jb_bestaetigen
					&& nameWeiß.equals(nameSchwarz)) {
				JOptionPane
						.showMessageDialog(
								null,
								"Spieler 1 und Spieler 2 dürfen nicht den selben Namen besitzen!",
								"Fehlerhafte Eingabe!",
								JOptionPane.WARNING_MESSAGE);
			} else {
				this.spiel = new Spiel(nameWeiß, dame1, nameSchwarz, dame2);

				sp.addToTextArea("Willkommen zu einem neuen Spiel der internationalen Dame");
				sp.addToTextArea("Spielregeln sind ueber das Hilfe Menu zu erreichen");
				sp.addToTextArea("Viel Spass.");
				sp.addToTextArea("Zug 1: Weiss");
				setzeSteine();
				rundenZaehler = 1;
				menu.jf.dispose();
				DEF.spielerSchwarzName.setText(spiel.getSpieler()[1] + "");
				mw.spielerWeissName.setText(spiel.getSpieler()[0] + "");
				if (spiel.getActiveSpieler().getKi() != null) {
					spielbrett.kiDialog.setVisible(true);
				}
			}

			menu.jt_spieler1.setText("");
			menu.jt_spieler2.setText("");
			menu.rb_mensch1.setSelected(true);
			menu.rb_mensch2.setSelected(true);
		}
		//email
		if(e.getSource() == menu.jmi_email){
			menu.jd_emailSenden.setVisible(true);
		}
		if(e.getSource()== menu.jbEmail){
			String s = menu.tf.getText();
			String a= menu.tf2.getText();
			String t = "Lieber User,\n\nvielen Dank, dass Sie unser Spiel gespielt haben. Ihren Spielstand zum Zeitpunkt des Speicherns finden Sie direkt unterhalb dieses Textes in Form eines Bildes wieder. Wir hoffen dass Ihnen unser Spiel Freude bereiten konnte. \nBei R�ckfragen oder f�r Feedback stehen wir gerne f�r Sie bereit.\n\nMit freundlichen Gr��en, \nTeam -A2-";
			
			if(menu.rb_SER.isSelected()){
			//new Mail(s,"Spielstand", t , a+".ser",a+".ser",null, null);
			}
			else if(menu.rb_PDF.isSelected()){
				//new Mail(s ,"Spielstand", t , a +".pdf", a+".pdf", null, null);
				
			}
			menu.jd_emailSenden.dispose();
			sp.addToTextArea("Spielstand wurde in gew�nschtem Format per Email gesendet");
			
		}
		// Schliessen
		if (e.getSource() == menu.jmi_schliessen) {
			menu.jd_schliessen.setVisible(true);
		}
		if (e.getSource() == menu.schliessen_ja) {
			menu.jd_schliessen.dispose();
			System.exit(0);
		}
		if (e.getSource() == menu.schliessen_nein) {
			menu.jd_schliessen.dispose();
		}

		// Speichern
		if (e.getSource() == menu.jmi_speichern) {
			menu.jfc_speichern.setVisible(true);
			menu.jfc_speichern.showSaveDialog(menu);

			menu.jfc_speichern
					.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			String dateiName = menu.jfc_speichern.getSelectedFile().getName();
			String dateiTyp = menu.jfc_speichern.getFileFilter()
					.getDescription();
			String pfad = menu.jfc_speichern.getSelectedFile()
					.getAbsolutePath();
			int pfadLaenge = pfad.length();
			int dateiNameLaenge = dateiName.length();
			String pfadOhneDateiname = pfad.substring(0, pfadLaenge
					- dateiNameLaenge);

			/******** Testausgabe **********/
			// System.out.println(dateiName + "." + dateiTyp);
			// System.out.println(pfad);
			// System.out.println(pfadOhneDateiname);
			/****************************/

			if (dateiTyp.equals("csv")) {
				spiel.saveCSV(dateiName);
			}
			if (dateiTyp.equals("ser")) {
				spiel.saveSerialize(dateiName);
			} else if (dateiTyp.equals("pdf")) {
				createPicture(dateiName);
				spiel.savePDF(dateiName);
				sp.addToTextArea("Spielstand wurde als .pdf gespeichert Laden von .pdf nicht moeglich");
			}

		}

		if (e.getSource() == menu.hintergrundAendern) {
			// muss noch erstellt werden
		}

		if (e.getSource() == menu.SteineFarbeAendern) {
			// muss noch erstellt werden
		}

		if (e.getSource() == menu.spielBeschreibung) {
			menu.f.setVisible(true);
		}
		// ------------------------------------------------------------------------------------#-------------------------------------________MENUELEISTE_________-------========_______ENDE_=======------

		// ------------------------------------------------------------------------------------#-------------------------------------________EASTBEREICH_________-------========_______ANFANG_=======------

		if (e.getSource() == DEF.jTF_Start_Ziel || e.getSource() == DEF.jb) {
			DEF.jTF_Start_Ziel.setForeground(Color.black);
			DEF.jTF_Start_Ziel.setFont(font);
			DEF.jTF_Start_Ziel.setBackground(Color.white);
			if (Pattern.matches(regel, DEF.jTF_Start_Ziel.getText())) {
				String[] sz = new String[2];
				sz = DEF.jTF_Start_Ziel.getText().split("-", 2);

				if (spiel.EingabeSpielfeld(sz[0]).getSpielfigur().isDame() == true) {

					if (spiel.moveDameLegit(spiel.getActiveSpieler(),
							spiel.EingabeSpielfeld(sz[0]),
							spiel.EingabeSpielfeld(sz[1])) == true) {

						ArrayList<int[]> pusten = spiel.doCheckPusten(spiel
								.EingabeSpielfeld(sz[0]));
						if (pusten.isEmpty()) {

							spiel.move(spiel.EingabeSpielfeld(sz[0]),
									spiel.EingabeSpielfeld(sz[1]));
							spielerRotation();
							sp.addToTextArea(spiel.EingabeSpielfeld(sz[0])
									.getSchachNotation()
									+ " auf "
									+ spiel.EingabeSpielfeld(sz[1])
											.getSchachNotation() + ".");
						} else if (pusten.size() == 1) {
							int[] abc = new int[2];
							abc = pusten.get(0);
							spiel.removeSpielfigur(spiel.getSpielbrett()
									.getBrett()[abc[0]][abc[1]]);
							sp.addToTextArea("Schlag war moeglich an Stelle "
									+ spiel.getSpielbrett().getBrett()[abc[0]][abc[1]]
											.getSchachNotation()
									+ ". Stein wird entfernt.");
							if (spiel.getSpielbrett().getBrett()[abc[0]][abc[1]] == spiel
									.EingabeSpielfeld(sz[0])) {

							} else {
								spiel.move(spiel.EingabeSpielfeld(sz[0]),
										spiel.EingabeSpielfeld(sz[1]));
								spielerRotation();
							}
						} else if (pusten.size() == 2) {
							int[] abc = new int[2];
							abc = pusten.get(0);
							int[] abc2 = new int[2];
							abc2 = pusten.get(1);
							pusten1[0] = abc[0];
							pusten1[1] = abc[1];
							pusten2[0] = abc2[0];
							pusten2[1] = abc2[1];
							spielbrett.Spielfelder[pusten1[1]][pusten1[0]]
									.setBackground(Color.red);
							spielbrett.Spielfelder[pusten2[1]][pusten2[0]]
									.setBackground(Color.red);
							spielbrett.pustDialogText1
									.setText("Es wurde gezogen obwohl man haette schlagen koennen.");
							spielbrett.pustDialogText2.setText(spiel
									.getActiveSpieler()
									+ ": Trenne dich von einem Stein.");
							spielbrett.pustDialogButton1
									.setText(spiel.getSpielbrett().getBrett()[abc[0]][abc[1]]
											.getSpielfigur()
											+ " auf "
											+ spiel.getSpielbrett().getBrett()[abc[0]][abc[1]]
													.getSchachNotation() + ".");
							spielbrett.pustDialogButton2
									.setText(spiel.getSpielbrett().getBrett()[abc2[0]][abc2[1]]
											.getSpielfigur()
											+ " auf "
											+ spiel.getSpielbrett().getBrett()[abc2[0]][abc2[1]]
													.getSchachNotation() + ".");
							spielbrett.pustDialog.setVisible(true);
							spiel.move(spiel.EingabeSpielfeld(sz[0]),
									spiel.EingabeSpielfeld(sz[1]));

							spielerRotation();
						}

					}
					if (spiel.schlagenDameLegit(spiel.getActiveSpieler(),
							spiel.EingabeSpielfeld(sz[0]),
							spiel.EingabeSpielfeld(sz[1])) == true) {
						spiel.schlagenDame(spiel.getActiveSpieler(),
								spiel.EingabeSpielfeld(sz[0]),
								spiel.EingabeSpielfeld(sz[1]));
						sp.addToTextArea("von "
								+ spiel.EingabeSpielfeld(sz[0])
										.getSchachNotation()
								+ " auf "
								+ spiel.EingabeSpielfeld(sz[1])
										.getSchachNotation()
								+ ", dabei Stein geschlagen.");
						if (spiel.schlagMoeglichDame(spiel
								.EingabeSpielfeld(sz[1])) == true) {
							schlagMoeglich = true;
							schlagStartKoords[0] = spiel
									.EingabeSpielfeld(sz[1]).getPosY();
							schlagStartKoords[1] = spiel
									.EingabeSpielfeld(sz[1]).getPosX();
							sp.addToTextArea(spiel.EingabeSpielfeld(sz[1])
									.getSpielfigur()
									+ " auf "
									+ spiel.EingabeSpielfeld(sz[1])
											.getSchachNotation()
									+ " kann noch einmal schlagen.");
						} else {
							schlagMoeglich = false;
							spielerRotation();
						}
					} else {
						sp.addToTextArea(DEF.jTF_Start_Ziel.getText()
								+ " ist kein gültiger Zug");
					}
				} else if (spiel.EingabeSpielfeld(sz[0]).getSpielfigur()
						.isDame() == false) {

					if (spiel.doMoveStein(spiel.getActiveSpieler(),
							spiel.EingabeSpielfeld(sz[0]),
							spiel.EingabeSpielfeld(sz[1])) == false) {

						if (spiel.doSchlagStein(spiel.getActiveSpieler(),
								spiel.EingabeSpielfeld(sz[0]),
								spiel.EingabeSpielfeld(sz[1])) == true) {
							sp.addToTextArea("von "
									+ spiel.EingabeSpielfeld(sz[0])
											.getSchachNotation()
									+ " auf "
									+ spiel.EingabeSpielfeld(sz[1])
											.getSchachNotation()
									+ ", dabei Stein geschlagen.");
							if (spiel.SchlagMoeglich(spiel
									.EingabeSpielfeld(sz[1])) == true) {
								schlagMoeglich = true;
								sp.addToTextArea(spiel.EingabeSpielfeld(sz[1])
										.getSpielfigur()
										+ " auf "
										+ spiel.EingabeSpielfeld(sz[1])
												.getSchachNotation()
										+ " kann noch einmal schlagen.");
								schlagStartKoords[0] = spiel.EingabeSpielfeld(
										sz[1]).getPosY();
								schlagStartKoords[1] = spiel.EingabeSpielfeld(
										sz[1]).getPosX();

							} else {
								schlagMoeglich = false;
								spielerRotation();
							}
						} else {
							sp.addToTextArea(DEF.jTF_Start_Ziel.getText()
									+ " ist kein gültiger Zug");
						}

					} else {
						ArrayList<int[]> pusten = spiel.doCheckPusten(spiel
								.EingabeSpielfeld(sz[0]));
						if (pusten.isEmpty()) {

							spiel.move(spiel.EingabeSpielfeld(sz[0]),
									spiel.EingabeSpielfeld(sz[1]));
							sp.addToTextArea(spiel.EingabeSpielfeld(sz[0])
									.getSchachNotation()
									+ " auf "
									+ spiel.EingabeSpielfeld(sz[1])
											.getSchachNotation() + ".");
						} else if (pusten.size() == 1) {
							int[] abc = new int[2];
							abc = pusten.get(0);
							spiel.removeSpielfigur(spiel.getSpielbrett()
									.getBrett()[abc[0]][abc[1]]);
							sp.addToTextArea("Schlag war moeglich an Stelle "
									+ spiel.getSpielbrett().getBrett()[abc[0]][abc[1]]
											.getSchachNotation()
									+ ". Stein wird entfernt.");
							if (spiel.getSpielbrett().getBrett()[abc[0]][abc[1]] == spiel
									.getSpielbrett().getBrett()[buttonkoords[1]][buttonkoords[0]]) {

							} else {
								spiel.move(spiel.EingabeSpielfeld(sz[0]),
										spiel.EingabeSpielfeld(sz[1]));
							}
						} else if (pusten.size() == 2) {
							int[] abc = new int[2];
							abc = pusten.get(0);
							int[] abc2 = new int[2];
							abc2 = pusten.get(1);
							pusten1[0] = abc[0];
							pusten1[1] = abc[1];
							pusten2[0] = abc2[0];
							pusten2[1] = abc2[1];
							spielbrett.Spielfelder[pusten1[1]][pusten1[0]]
									.setBackground(Color.red);
							spielbrett.Spielfelder[pusten2[1]][pusten2[0]]
									.setBackground(Color.red);
							spielbrett.pustDialogText1
									.setText("Es wurde gezogen obwohl man haette schlagen koennen.");
							spielbrett.pustDialogText2.setText(spiel
									.getActiveSpieler()
									+ ": Trenne dich von einem Stein.");
							spielbrett.pustDialogButton1
									.setText(spiel.getSpielbrett().getBrett()[abc[0]][abc[1]]
											.getSpielfigur()
											+ " auf "
											+ spiel.getSpielbrett().getBrett()[abc[0]][abc[1]]
													.getSchachNotation() + ".");
							spielbrett.pustDialogButton2
									.setText(spiel.getSpielbrett().getBrett()[abc2[0]][abc2[1]]
											.getSpielfigur()
											+ " auf "
											+ spiel.getSpielbrett().getBrett()[abc2[0]][abc2[1]]
													.getSchachNotation() + ".");
							spielbrett.pustDialog.setVisible(true);
							spiel.move(spiel.EingabeSpielfeld(sz[0]),
									spiel.EingabeSpielfeld(sz[1]));

						}
						spielerRotation();
					}
				}

				setzeSteine();
			} else {
				sp.addToTextArea("Eingabe Syntax nicht korrekt, Feld nicht auf Spielbrett");

				DEF.jTF_Start_Ziel.setFont(font);
				DEF.jTF_Start_Ziel.setBackground(Color.red);
				DEF.jTF_Start_Ziel.setForeground(Color.white);

			}
			DEF.jTF_Start_Ziel.setText("");
		}

		// ------------------------------------------------------------------------------------#-------------------------------------________EASTBEREICH_________-------========_______ENDE_=======------
	}

	/**
	 * 
	 * erstellt ein Bild des Spielbrettes mit der aktuellen Steinbelegung und
	 * speichert es als png datei ab
	 * 
	 * @param dateiName
	 */
	public void createPicture(String dateiName) {
		String filename = dateiName + ".png";
		BufferedImage image = new BufferedImage(Spielbrett.getSpielbrett()
				.getSize().width, Spielbrett.getSpielbrett().getSize().height,
				BufferedImage.TYPE_INT_RGB);
		Spielbrett.getSpielbrett().paintAll(image.getGraphics());
		try {
			ImageIO.write(image, "png", new File(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Ueberprueft ob in dem JButton[][] von Spielbrett bereits ein Stein
	 * geklickt wurde
	 * 
	 * @return boolean ob bereits geklickt, oder nicht.
	 */
	public boolean schonGeklickt(JButton[][] spielfelder) {
		for (int i = 0; i < spielfelder.length; i++) {
			for (int j = 0; j < spielfelder[i].length; j++) {
				if (spielfelder[j][i].getBackground() == klickColor) {
					buttonkoords[0] = j;
					buttonkoords[1] = i;
					return true;
				}
			}
		}
		return false;

	}

	/**
	 * Setzt Icons im JButton[][], an den Stellen bei denen bereits im Spiel ein
	 * Stein ist.
	 */
	public void setzeSteine() {

		for (int i = spielbrett.Spielfelder.length - 1; i >= 0; i--) {
			for (int j = 0; j < spielbrett.Spielfelder[1].length; j++) {
				if (spiel.getSpielbrett().getBrett()[i][j].getFarbe() == FarbEnum.schwarz) {

					if (spiel.getSpielbrett().getBrett()[i][j].getSpielfigur() != null) {

						if (spiel.getSpielbrett().getBrett()[i][j]
								.getSpielfigur().getFarbe() == FarbEnum.schwarz) {
							if (spiel.getSpielbrett().getBrett()[i][j]
									.getSpielfigur().isDame() == false) {
								ImageIcon icon = new ImageIcon(
										"Dame_Stein_braun_50w.png");
								spielbrett.Spielfelder[j][i].setIcon(icon);
							} else {
								ImageIcon icon = new ImageIcon(
										"Dame_Dame_braun_50w.png");
								spielbrett.Spielfelder[j][i].setIcon(icon);
							}
						} else {
							if (spiel.getSpielbrett().getBrett()[i][j]
									.getSpielfigur().isDame() == false) {

								ImageIcon icon = new ImageIcon(
										"Dame_Stein_weiss_50.png");
								spielbrett.Spielfelder[j][i].setIcon(icon);
							} else {
								ImageIcon icon = new ImageIcon(
										"Dame_Dame_weiss_50.png");
								spielbrett.Spielfelder[j][i].setIcon(icon);
							}
						}
					} else {
						spielbrett.Spielfelder[j][i].setIcon(null);
					}
				}
			}
		}
	}

	/**
	 * Setzt den active Spieler auf den anderen im Spieler[] Array im Spiel, und
	 * ueberprueft davor ob Siegkonditionen erfuellt wurden. wenn 2 KIs
	 * gegeneinanderspielen, wird der Dialog nicht geschlossen.
	 */
	public void spielerRotation() {
		setzeSteine();
		if (spiel.getSpieler()[0].getKi() == null
				|| spiel.getSpieler()[1].getKi() == null) {
			if (spiel.getActiveSpieler() == spiel.getSpieler()[0]) {
				if (spiel.checkSiegkondition(spiel.getActiveSpieler())) {
					spielbrett.spielGewonnenText.setText(spiel
							.getActiveSpieler() + " hat gewonnen.");
					spielbrett.spielGewonnenDialog.setVisible(true);
				}

				spiel.checkAndGetDame(spiel.getActiveSpieler());
				spiel.setActiveSpieler(spiel.getSpieler()[1]);

				sp.addToTextArea("Zug " + rundenZaehler + ": "
						+ spiel.getActiveSpieler().getFarbe());

				rundenZaehler++;
				if (spiel.getActiveSpieler().getKi() != null) {
					spielbrett.kiDialog.setVisible(true);
				}
			} else {
				if (spiel.checkSiegkondition(spiel.getActiveSpieler())) {
					spielbrett.spielGewonnenText.setText(spiel
							.getActiveSpieler() + " hat gewonnen.");
					spielbrett.spielGewonnenDialog.setVisible(true);
				}
				spiel.checkAndGetDame(spiel.getActiveSpieler());
				spiel.setActiveSpieler(spiel.getSpieler()[0]);

				sp.addToTextArea("Zug " + rundenZaehler + ": "
						+ spiel.getActiveSpieler().getFarbe());
				if (spiel.getActiveSpieler().getKi() != null) {
					spielbrett.kiDialog.setVisible(true);
				}
			}
		}
		if (spiel.getSpieler()[0].getKi() != null
				&& spiel.getSpieler()[1].getKi() != null) {

			if (spiel.getActiveSpieler() == spiel.getSpieler()[0]) {
				if (spiel.checkSiegkondition(spiel.getActiveSpieler())) {
					spielbrett.spielGewonnenText.setText(spiel
							.getActiveSpieler() + " hat gewonnen.");
					spielbrett.kiDialog.setVisible(false);
					spielbrett.spielGewonnenDialog.setVisible(true);
				}

				spiel.checkAndGetDame(spiel.getActiveSpieler());
				spiel.setActiveSpieler(spiel.getSpieler()[1]);

				sp.addToTextArea("Zug " + rundenZaehler + ": "
						+ spiel.getActiveSpieler().getFarbe());

				rundenZaehler++;

			} else {
				if (spiel.checkSiegkondition(spiel.getActiveSpieler())) {
					spielbrett.spielGewonnenText.setText(spiel
							.getActiveSpieler() + " hat gewonnen.");
					spielbrett.kiDialog.setVisible(false);
					spielbrett.spielGewonnenDialog.setVisible(true);
				}
				spiel.checkAndGetDame(spiel.getActiveSpieler());
				spiel.setActiveSpieler(spiel.getSpieler()[0]);

				sp.addToTextArea("Zug " + rundenZaehler + ": "
						+ spiel.getActiveSpieler().getFarbe());

			}
		}
	}

	/**
	 * setter fuer Mainwindow aufrufe, damit EH die GUI Objekte kennenlernt.
	 */
	public void setSpiel(Spiel spiel) {
		this.spiel = spiel;
	}

	public void setScrollPane(ScrollPane sp) {
		this.sp = sp;

	}

	public void setSpielbrett(Spielbrett spielbrett) {
		this.spielbrett = spielbrett;

	}

	public void setMenu(MenuLeiste ml) {
		this.menu = ml;

	}

	public void setDEF(DameStartZielEingabe def) {
		this.DEF = def;
	}

}
