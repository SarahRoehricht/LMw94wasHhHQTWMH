package frontend;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import backend.FarbEnum;
import backend.Spiel;

public class EH_Spielbrett implements ActionListener {

	private Spielbrett spielbrett;
	private Color klickColor = new Color(130, 255, 174);
	private Color schwarzFeldColor= new Color(156, 93, 82);
	private Spiel spiel;
	private boolean schlagMoeglich;
	private ScrollPane sp;
	private int rundenZaehler=1;

	private int[] buttonkoords = new int[2];
	private int[] schlagStartKoords = new int[2];

	private int[] pusten1 = new int[2];
	private int[] pusten2 = new int[2];

	public EH_Spielbrett(Spielbrett spielbrett) {
		this.spielbrett = spielbrett;
	}

	public EH_Spielbrett() {

	}

	/**
	 * JAVAVA JVAJVA VAVJAVAVAVJ DOCCCCCCCCCCCC PLPPLLSLLSLSLSLSSSSSSS!!!!
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
	 * JAVAVA JVAJVA VAVJAVAVAVJ DOCCCCCCCCCCCC PLPPLLSLLSLSLSLSSSSSSS!!!!
	 */
	public void setzeSteine() {
		
		for (int i = spielbrett.Spielfelder.length - 1; i >= 0; i--) {
			for (int j = 0; j < spielbrett.Spielfelder[1].length; j++) {
				if (spiel.getSpielbrett().getBrett()[i][j].getFarbe() == FarbEnum.schwarz) {

					if (spiel.getSpielbrett().getBrett()[i][j].getSpielfigur() != null) {

						if (spiel.getSpielbrett().getBrett()[i][j].getSpielfigur().getFarbe() == FarbEnum.schwarz) {
							if (spiel.getSpielbrett().getBrett()[i][j].getSpielfigur().isDame() == false) {
								ImageIcon icon = new ImageIcon("Schwarz.gif");
								spielbrett.Spielfelder[j][i].setIcon(icon);
							} else {
								ImageIcon icon = new ImageIcon("Dame.png");
								spielbrett.Spielfelder[j][i].setIcon(icon);
							}
						} else {
							if (spiel.getSpielbrett().getBrett()[i][j].getSpielfigur().isDame() == false) {

								ImageIcon icon = new ImageIcon("Weiss.png");
								spielbrett.Spielfelder[j][i].setIcon(icon);
							} else {
								ImageIcon icon = new ImageIcon("WeissDame.png");
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
public void spielerRotation(){
	if (spiel.getActiveSpieler() == spiel.getSpieler()[0]) {
		spiel.checkAndGetDame(spiel.getActiveSpieler());
		spiel.setActiveSpieler(spiel.getSpieler()[1]);
		sp.addToTextArea("Zug "+rundenZaehler+": "+spiel.getActiveSpieler().getFarbe());
		rundenZaehler++;
	} else {
		spiel.checkAndGetDame(spiel.getActiveSpieler());
		spiel.setActiveSpieler(spiel.getSpieler()[0]);
		sp.addToTextArea("Zug "+rundenZaehler+": "+spiel.getActiveSpieler().getFarbe());
		
	}
}
	/**
	 * JAVAVA JVAJVA VAVJAVAVAVJ DOCCCCCCCCCCCC PLPPLLSLLSLSLSLSSSSSSS!!!!
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// ------------------------------------------------------------------------------------#-------------------------------------________SPIELBRETT_________-------========_______ANFANG_=======------
		if (schlagMoeglich == false) {
			if (schonGeklickt(spielbrett.Spielfelder) == true) {
				for (int i = 0; i < spielbrett.Spielfelder.length; i++) {
					for (int j = 0; j < spielbrett.Spielfelder[i].length; j++) {
						if (spielbrett.Spielfelder[j][i] == e.getSource()) {
							if (spielbrett.Spielfelder[j][i].getBackground() == klickColor || spielbrett.Spielfelder[j][i].getBackground() == Color.black) {
								if (spielbrett.Spielfelder[j][i].getBackground() == klickColor) {
									spielbrett.Spielfelder[j][i].setBackground(Color.black);
								} else {
									spielbrett.Spielfelder[j][i].setBackground(klickColor);
									if (spiel.getSpielbrett().getBrett()[i][j].getSpielfigur() == null) {
											if(spiel.getActiveSpieler().getFarbe()!=spiel.getSpielbrett().getBrett()[buttonkoords[1]][buttonkoords[0]].getSpielfigur().getFarbe()){
												spielbrett.Spielfelder[j][i].setBackground(Color.black);
												spielbrett.Spielfelder[buttonkoords[0]][buttonkoords[1]].setBackground(Color.black);
											}else{
										if (spiel.getSpielbrett().getBrett()[buttonkoords[1]][buttonkoords[0]].getSpielfigur().isDame() == true) {

											if (spiel.moveDameLegit(spiel.getActiveSpieler(), spiel.getSpielbrett().getBrett()[buttonkoords[1]][buttonkoords[0]], spiel.getSpielbrett().getBrett()[i][j]) == true) {
												
													ArrayList<int[]> pusten = spiel.doCheckPusten(spiel.getSpielbrett().getBrett()[buttonkoords[1]][buttonkoords[0]]);
													if (pusten.isEmpty()) {

														spiel.move(spiel.getSpielbrett().getBrett()[buttonkoords[1]][buttonkoords[0]], spiel.getSpielbrett().getBrett()[i][j]);
														spielerRotation();
														sp.addToTextArea( spiel.getSpielbrett().getBrett()[buttonkoords[1]][buttonkoords[0]].getSchachNotation() + " auf " + spiel.getSpielbrett().getBrett()[i][j].getSchachNotation() + ".");
													} else if (pusten.size() == 1) {
														int[] abc = new int[2];
														abc = pusten.get(0);
														spiel.removeSpielfigur(spiel.getSpielbrett().getBrett()[abc[0]][abc[1]]);
														sp.addToTextArea("Schlag war moeglich an Stelle " + spiel.getSpielbrett().getBrett()[abc[0]][abc[1]].getSchachNotation() + ". Stein wird entfernt.");
														if (spiel.getSpielbrett().getBrett()[abc[0]][abc[1]] == spiel.getSpielbrett().getBrett()[buttonkoords[1]][buttonkoords[0]]) {

														} else {
															spiel.move(spiel.getSpielbrett().getBrett()[buttonkoords[1]][buttonkoords[0]], spiel.getSpielbrett().getBrett()[i][j]);
															spielerRotation();
															spielbrett.Spielfelder[j][i].setBackground(Color.black);
															spielbrett.Spielfelder[buttonkoords[0]][buttonkoords[1]].setBackground(Color.black);
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
														spielbrett.Spielfelder[j][i].setBackground(Color.black);
														spielbrett.Spielfelder[buttonkoords[0]][buttonkoords[1]].setBackground(Color.black);
														spielbrett.Spielfelder[pusten1[1]][pusten1[0]].setBackground(Color.red);
														spielbrett.Spielfelder[pusten2[1]][pusten2[0]].setBackground(Color.red);
														spielbrett.pustDialogText1.setText("Es wurde gezogen obwohl man haette schlagen koennen.");
														spielbrett.pustDialogText2.setText(spiel.getActiveSpieler() + ": Trenne dich von einem Stein.");
														spielbrett.pustDialogButton1.setText(spiel.getSpielbrett().getBrett()[abc[0]][abc[1]].getSpielfigur() + " auf " + spiel.getSpielbrett().getBrett()[abc[0]][abc[1]].getSchachNotation() + ".");
														spielbrett.pustDialogButton2.setText(spiel.getSpielbrett().getBrett()[abc2[0]][abc2[1]].getSpielfigur() + " auf " + spiel.getSpielbrett().getBrett()[abc2[0]][abc2[1]].getSchachNotation() + ".");
														spielbrett.pustDialog.setVisible(true);
														spiel.move(spiel.getSpielbrett().getBrett()[buttonkoords[1]][buttonkoords[0]], spiel.getSpielbrett().getBrett()[i][j]);
														
														spielerRotation();
													}
													spielbrett.Spielfelder[j][i].setBackground(Color.black);
													spielbrett.Spielfelder[buttonkoords[0]][buttonkoords[1]].setBackground(Color.black);
												
											}
											if (spiel.schlagenDameLegit(spiel.getActiveSpieler(), spiel.getSpielbrett().getBrett()[buttonkoords[1]][buttonkoords[0]], spiel.getSpielbrett().getBrett()[i][j]) == true) {
												spiel.schlagenDame(spiel.getActiveSpieler(), spiel.getSpielbrett().getBrett()[buttonkoords[1]][buttonkoords[0]], spiel.getSpielbrett().getBrett()[i][j]);
												sp.addToTextArea("von "+spiel.getSpielbrett().getBrett()[buttonkoords[1]][buttonkoords[0]].getSchachNotation()+" auf " +spiel.getSpielbrett().getBrett()[i][j].getSchachNotation()+", dabei Stein geschlagen.");
												if (spiel.schlagMoeglichDame(spiel.getSpielbrett().getBrett()[i][j]) == true) {
													schlagMoeglich = true;
													spielbrett.Spielfelder[buttonkoords[0]][buttonkoords[1]].setBackground(Color.black);
													schlagStartKoords[0] = i;
													schlagStartKoords[1] = j;
													sp.addToTextArea(spiel.getSpielbrett().getBrett()[i][j].getSpielfigur() + " auf "+spiel.getSpielbrett().getBrett()[i][j].getSchachNotation()+" kann noch einmal schlagen.");
												} else {
													schlagMoeglich = false;
													spielbrett.Spielfelder[j][i].setBackground(Color.black);
													spielbrett.Spielfelder[buttonkoords[0]][buttonkoords[1]].setBackground(Color.black);
													spielerRotation();
												}
											} else {
												spielbrett.Spielfelder[j][i].setBackground(Color.black);
											}
											} else if (spiel.getSpielbrett().getBrett()[buttonkoords[1]][buttonkoords[0]].getSpielfigur().isDame() == false) {

											if (spiel.doMoveStein(spiel.getActiveSpieler(), spiel.getSpielbrett().getBrett()[buttonkoords[1]][buttonkoords[0]], spiel.getSpielbrett().getBrett()[i][j]) == false) {

												if (spiel.doSchlagStein(spiel.getActiveSpieler(), spiel.getSpielbrett().getBrett()[buttonkoords[1]][buttonkoords[0]], spiel.getSpielbrett().getBrett()[i][j]) == true) {
													sp.addToTextArea("von "+spiel.getSpielbrett().getBrett()[buttonkoords[1]][buttonkoords[0]].getSchachNotation()+" auf " +spiel.getSpielbrett().getBrett()[i][j].getSchachNotation()+", dabei Stein geschlagen.");
													if (spiel.SchlagMoeglich(spiel.getSpielbrett().getBrett()[i][j]) == true) {
														schlagMoeglich = true;	
														sp.addToTextArea(spiel.getSpielbrett().getBrett()[i][j].getSpielfigur() + " auf "+spiel.getSpielbrett().getBrett()[i][j].getSchachNotation()+" kann noch einmal schlagen.");
														spielbrett.Spielfelder[buttonkoords[0]][buttonkoords[1]].setBackground(Color.black);
														schlagStartKoords[0] = i;
														schlagStartKoords[1] = j;

													} else {
														schlagMoeglich = false;
														spielbrett.Spielfelder[j][i].setBackground(Color.black);
														spielbrett.Spielfelder[buttonkoords[0]][buttonkoords[1]].setBackground(Color.black);
														spielerRotation();
													}
												} else {
													spielbrett.Spielfelder[j][i].setBackground(Color.black);
												}

											} else {
												ArrayList<int[]> pusten = spiel.doCheckPusten(spiel.getSpielbrett().getBrett()[buttonkoords[1]][buttonkoords[0]]);
												if (pusten.isEmpty()) {

													spiel.move(spiel.getSpielbrett().getBrett()[buttonkoords[1]][buttonkoords[0]], spiel.getSpielbrett().getBrett()[i][j]);
													sp.addToTextArea(spiel.getSpielbrett().getBrett()[buttonkoords[1]][buttonkoords[0]].getSchachNotation() + " auf " + spiel.getSpielbrett().getBrett()[i][j].getSchachNotation() + ".");
												} else if (pusten.size() == 1) {
													int[] abc = new int[2];
													abc = pusten.get(0);
													spiel.removeSpielfigur(spiel.getSpielbrett().getBrett()[abc[0]][abc[1]]);
													sp.addToTextArea("Schlag war moeglich an Stelle " + spiel.getSpielbrett().getBrett()[abc[0]][abc[1]].getSchachNotation() + ". Stein wird entfernt.");
													if (spiel.getSpielbrett().getBrett()[abc[0]][abc[1]] == spiel.getSpielbrett().getBrett()[buttonkoords[1]][buttonkoords[0]]) {

													} else {
														spiel.move(spiel.getSpielbrett().getBrett()[buttonkoords[1]][buttonkoords[0]], spiel.getSpielbrett().getBrett()[i][j]);
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
													spielbrett.Spielfelder[j][i].setBackground(Color.black);
													spielbrett.Spielfelder[buttonkoords[0]][buttonkoords[1]].setBackground(Color.black);
													spielbrett.Spielfelder[pusten1[1]][pusten1[0]].setBackground(Color.red);
													spielbrett.Spielfelder[pusten2[1]][pusten2[0]].setBackground(Color.red);
													spielbrett.pustDialogText1.setText("Es wurde gezogen obwohl man haette schlagen koennen.");
													spielbrett.pustDialogText2.setText(spiel.getActiveSpieler() + ": Trenne dich von einem Stein.");
													spielbrett.pustDialogButton1.setText(spiel.getSpielbrett().getBrett()[abc[0]][abc[1]].getSpielfigur() + " auf " + spiel.getSpielbrett().getBrett()[abc[0]][abc[1]].getSchachNotation() + ".");
													spielbrett.pustDialogButton2.setText(spiel.getSpielbrett().getBrett()[abc2[0]][abc2[1]].getSpielfigur() + " auf " + spiel.getSpielbrett().getBrett()[abc2[0]][abc2[1]].getSchachNotation() + ".");
													spielbrett.pustDialog.setVisible(true);
													spiel.move(spiel.getSpielbrett().getBrett()[buttonkoords[1]][buttonkoords[0]], spiel.getSpielbrett().getBrett()[i][j]);

												}
												spielbrett.Spielfelder[j][i].setBackground(Color.black);
												spielbrett.Spielfelder[buttonkoords[0]][buttonkoords[1]].setBackground(Color.black);
												spielerRotation();
											}
										}

									
									
										setzeSteine();
									}} else {
										spielbrett.Spielfelder[buttonkoords[0]][buttonkoords[1]].setBackground(Color.black);
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
									
									spielbrett.Spielfelder[j][i].setBackground(klickColor);}
								
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
								if (spiel.getSpielbrett().getBrett()[schlagStartKoords[0]][schlagStartKoords[1]].getSpielfigur().isDame() == true) {
									if (spiel.schlagenDameLegit(spiel.getActiveSpieler(), spiel.getSpielbrett().getBrett()[schlagStartKoords[0]][schlagStartKoords[1]], spiel.getSpielbrett().getBrett()[i][j]) == true) {
										spiel.schlagenDame(spiel.getActiveSpieler(), spiel.getSpielbrett().getBrett()[schlagStartKoords[0]][schlagStartKoords[1]], spiel.getSpielbrett().getBrett()[i][j]);
										spielbrett.Spielfelder[schlagStartKoords[1]][schlagStartKoords[0]].setBackground(Color.black);
										sp.addToTextArea("von "+spiel.getSpielbrett().getBrett()[schlagStartKoords[0]][schlagStartKoords[1]].getSchachNotation()+" auf " +spiel.getSpielbrett().getBrett()[i][j].getSchachNotation()+", dabei Stein geschlagen.");
										if (spiel.schlagMoeglichDame(spiel.getSpielbrett().getBrett()[i][j]) == true) {
											schlagMoeglich = true;
											
											schlagStartKoords[0] = i;
											schlagStartKoords[1] = j;
											spielbrett.Spielfelder[schlagStartKoords[1]][schlagStartKoords[0]].setBackground(klickColor);
											sp.addToTextArea(spiel.getSpielbrett().getBrett()[schlagStartKoords[0]][schlagStartKoords[1]]+" auf " +spiel.getSpielbrett().getBrett()[schlagStartKoords[0]][schlagStartKoords[1]].getSchachNotation() +" kann noch einmal schlagen.");
										} else {
											schlagMoeglich = false;
											spielbrett.Spielfelder[schlagStartKoords[1]][schlagStartKoords[0]].setBackground(Color.black);
											spielbrett.Spielfelder[i][j].setBackground(Color.black);
											
										}
									}

								} else if (spiel.getSpielbrett().getBrett()[schlagStartKoords[0]][schlagStartKoords[1]].getSpielfigur().isDame() == false) {
									if (spiel.doSchlagStein(spiel.getActiveSpieler(), spiel.getSpielbrett().getBrett()[schlagStartKoords[0]][schlagStartKoords[1]], spiel.getSpielbrett().getBrett()[i][j]) == true) {
										sp.addToTextArea("von "+spiel.getSpielbrett().getBrett()[schlagStartKoords[0]][schlagStartKoords[1]].getSchachNotation()+" auf " +spiel.getSpielbrett().getBrett()[i][j].getSchachNotation()+", dabei Stein geschlagen.");
										spielbrett.Spielfelder[schlagStartKoords[0]][schlagStartKoords[1]].setBackground(Color.black);
										if (spiel.SchlagMoeglich(spiel.getSpielbrett().getBrett()[i][j]) == true) {
											schlagMoeglich = true;
											spielbrett.Spielfelder[schlagStartKoords[1]][schlagStartKoords[0]].setBackground(Color.black);
											schlagStartKoords[0] = i;
											schlagStartKoords[1] = j;
											spielbrett.Spielfelder[i][j].setBackground(klickColor);
											sp.addToTextArea(spiel.getSpielbrett().getBrett()[schlagStartKoords[0]][schlagStartKoords[1]]+" auf " +spiel.getSpielbrett().getBrett()[schlagStartKoords[0]][schlagStartKoords[1]].getSchachNotation() +" kann noch einmal schlagen.");
										} else {
											schlagMoeglich = false;
											spielbrett.Spielfelder[schlagStartKoords[1]][schlagStartKoords[0]].setBackground(Color.black);
											spielbrett.Spielfelder[i][j].setBackground(Color.black);
										}
									} else {

									}
								}

								if (schlagMoeglich == false) {
									spielbrett.Spielfelder[i][j].setBackground(Color.black);
									spielbrett.Spielfelder[schlagStartKoords[1]][schlagStartKoords[0]].setBackground(Color.black);
									spielerRotation();
								} else {

									spielbrett.Spielfelder[schlagStartKoords[1]][schlagStartKoords[0]].setBackground(Color.black);
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

		if (e.getSource() == spielbrett.pustDialogButton1 || e.getSource() == spielbrett.pustDialogButton2) {
			if (e.getSource() == spielbrett.pustDialogButton1) {
				sp.addToTextArea(spiel.getSpielbrett().getBrett()[pusten1[0]][pusten1[1]].getSpielfigur() + " an Stelle: " + spiel.getSpielbrett().getBrett()[pusten1[0]][pusten1[1]].getSchachNotation() + " entfernt.");
				spiel.removeSpielfigur(spiel.getSpielbrett().getBrett()[pusten1[0]][pusten1[1]]);

			}
			if (e.getSource() == spielbrett.pustDialogButton2) {
				sp.addToTextArea(spiel.getSpielbrett().getBrett()[pusten2[0]][pusten2[1]].getSpielfigur() + " an Stelle: " + spiel.getSpielbrett().getBrett()[pusten2[0]][pusten2[1]].getSchachNotation() + " entfernt.");
				spiel.removeSpielfigur(spiel.getSpielbrett().getBrett()[pusten2[0]][pusten2[1]]);

			}
			spielbrett.Spielfelder[pusten1[1]][pusten1[0]].setBackground(Color.black);
			spielbrett.Spielfelder[pusten2[1]][pusten2[0]].setBackground(Color.black);
			spielbrett.pustDialog.setVisible(false);
			setzeSteine();
		}
		// ------------------------------------------------------------------------------------#-------------------------------------________PUSTENDIALOG_________-------========_______ENDE_=======------

	}


	/**
	 * JAVAVA JVAJVA VAVJAVAVAVJ DOCCCCCCCCCCCC PLPPLLSLLSLSLSLSSSSSSS!!!!
	 */
	public void setSpiel(Spiel spiel, Spielbrett spielbrett) {
		this.spiel = spiel;
		this.spielbrett = spielbrett;
		setzeSteine();
	}

	public void setScrollPane(ScrollPane sp) {
		this.sp = sp;

	}

}
