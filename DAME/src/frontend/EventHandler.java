package frontend;

	import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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

	public class EventHandler implements ActionListener {

		private Spielbrett spielbrett;
		private Color klickColor = new Color(130, 255, 174);
		private Color schwarzFeldColor= new Color(156, 93, 82);
		private Spiel spiel;
		private boolean schlagMoeglich;
		private ScrollPane sp;
		private int rundenZaehler=1;
		private DameStartZielEingabe DEF;

		private ImageIcon Icon = new ImageIcon("check.png");
		private ImageIcon Icon2 = new ImageIcon("error.png");

		private MenuLeiste menu;
		private int[] buttonkoords = new int[2];
		private int[] schlagStartKoords = new int[2];

		private int[] pusten1 = new int[2];
		private int[] pusten2 = new int[2];


		public EventHandler() {
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
												spielbrett.Spielfelder[j][i].setBackground(klickColor);
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
												spielbrett.Spielfelder[j][i].setBackground(klickColor);
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
		
			
			
			// ------------------------------------------------------------------------------------#-------------------------------------________SPIELGEWONNEN_________-------========_______ANFANG_=======------
		
			if(e.getSource()==spielbrett.spielGewonnenDialogButton){
				spielbrett.spielGewonnenDialog.setVisible(false);
			}
			
			// ------------------------------------------------------------------------------------#-------------------------------------________SPIELGEWONNEN_________-------========_______ENDE_=======------
		// ------------------------------------------------------------------------------------#-------------------------------------________KIDIALOG_________-------========_______ANFANG_=======------
			if(e.getSource()==spielbrett.kiButton){
				
				
				spiel.act(spiel.getActiveSpieler());
				
				
				sp.addToTextArea(spiel.getActiveSpieler()+"");
				setzeSteine();
				spielerRotation();
				spielbrett.kiDialog.setVisible(false);
				
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
				System.out.println(dateiName + "." + dateiTyp);
				System.out.println(pfad);
				System.out.println(pfadOhneDateiname);
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
				System.out.println(dateiName + "." + dateiTyp);
				System.out.println(pfad);
				System.out.println(pfadOhneDateiname);
				/****************************/

				if (dateiTyp.equals("csv")) {
					spiel.speichern(pfadOhneDateiname, dateiName, dateiTyp);
				}
				if (dateiTyp.equals("ser")) {
					spiel.saveSerialize(dateiName);
				} else {
					// PDF speichern
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
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					} else {
						try {
							spiel.loadSerialize(dateiName);
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
				System.out.println("blub abbruch");
				menu.jf.dispose();
			}

			// Mensch RadioButton als default ausgewählt
			menu.rb_mensch1.setSelected(true);
			menu.rb_mensch2.setSelected(true);

			if (e.getSource() == menu.jb_bestaetigen) {
				boolean dame1 = false;
				boolean dame2 = false;
				if (menu.rb_ki1.isSelected()) {
					dame1 = true;
				} else {
					dame1 = false;
				}
				if (menu.rb_ki2.isSelected()) {
					dame2 = true;
				} else {
					dame2 = false;
				}

				String nameWeiß = menu.jt_spieler1.getText();
				String nameSchwarz = menu.jt_spieler2.getText();
				
				if (e.getSource() == menu.jb_bestaetigen && (nameWeiß.length() < 3)) {
					JOptionPane.showMessageDialog(null,
							"Spieler 1: Ihr Name muss mindestens 3 Zeichen lang sein!",
							"Unvollständige Eingabe!", JOptionPane.WARNING_MESSAGE);
				}
				
				if (e.getSource() == menu.jb_bestaetigen && (nameSchwarz.length() < 3)) {
					JOptionPane.showMessageDialog(null,
							"Spieler 2: Ihr Name muss mindestens 3 Zeichen lang sein!",
							"Unvollständige Eingabe!", JOptionPane.WARNING_MESSAGE);
				}
				if(e.getSource() == menu.jb_bestaetigen && nameWeiß.equals(nameSchwarz)){
					JOptionPane.showMessageDialog(null,
							"Spieler 1 und Spieler 2 dürfen nicht den selben Namen besitzen!",
							"Fehlerhafte Eingabe!", JOptionPane.WARNING_MESSAGE);
				} else {
					this.spiel = new Spiel(nameWeiß, dame1, nameSchwarz, dame2);
					sp.addToTextArea("Zug 1: Weiss");
					setzeSteine();
					
			rundenZaehler=1;
					menu.jf.dispose();
					if(spiel.getActiveSpieler().getKi()!=null){
						spielbrett.kiDialog.setVisible(true);
					}
				}
				
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
				System.out.println(dateiName + "." + dateiTyp);
				System.out.println(pfad);
				System.out.println(pfadOhneDateiname);
				/****************************/

				if (dateiTyp.equals("csv")) {
					spiel.speichern(pfadOhneDateiname, dateiName, dateiTyp);
				}
				if (dateiTyp.equals("ser")) {
					spiel.saveSerialize(dateiName);
				} else {
					// PDF speichern
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
//		------------------------------------------------------------------------------------#-------------------------------------________MENUELEISTE_________-------========_______ENDE_=======------
	
//		------------------------------------------------------------------------------------#-------------------------------------________EASTBEREICH_________-------========_______ANFANG_=======------
		
		
		
			String regel = "[a-lA-L]{1}[0-9]{1}[0-2]{0,1}-{1}[a-lA-L]{1}[0-9]{1}[0-2]{0,1}";
			String ausgabe;
			Font font;
			font = new Font("ARIAL", Font.BOLD, 14);
			DEF.jl.setText("");
			if (e.getSource() == DEF.jTF_Start_Ziel || e.getSource() == DEF.jb) {
				DEF.jTF_Start_Ziel.setForeground(Color.black);
				DEF.jTF_Start_Ziel.setFont(font);
				DEF.jTF_Start_Ziel.setBackground(Color.white);
				if (Pattern.matches(regel, DEF.jTF_Start_Ziel.getText())) {
					String[] sz = new String[2];
					sz = DEF.jTF_Start_Ziel.getText().split("-", 2);
					
					if ((spiel.doTheMove(spiel.getActiveSpieler(), spiel.EingabeSpielfeld(sz[0]), spiel.EingabeSpielfeld(sz[1])) == false)) {
						// System.out.println("Falscher Zug bzw Zug nicht möglich erneute eingabe");
						ausgabe = DEF.jTF_Start_Ziel.getText()+"ist kein gültiger Zug";
						// sp.addToTextArea(ausgabe);
						DEF.jl.setForeground(Color.red);
						DEF.jl.setText(DEF.jTF_Start_Ziel.getText()+" ist kein gültiger Zug");
						DEF.jTF_Start_Ziel.setFont(font);
						DEF.jTF_Start_Ziel.setBackground(Color.red);
						DEF.jTF_Start_Ziel.setForeground(Color.white);
					//	DEF.jb.setIcon(Icon2);

						// DEF.jb.setIcon(Icon);
					} else {
						DEF.jl.setForeground(Color.green);
						DEF.jl.setText(spiel.getActiveSpieler()+" hat von "+sz[0]+" nach "+sz[1]+ " gezogen");
						ausgabe = sz[0] + "-" + sz[1];
						// System.out.println(ausgabe);
						// sp.addToTextArea(ausgabe);
						DEF.jTF_Start_Ziel.setText("");
						//if((DEF.spiel.zugdone)==true)
						//DEF.jb.setIcon(Icon);
					}
				} else {

					ausgabe = "Eingabe Syntax nicht korrekt, Feld nicht auf Spielbrett";
					//DEF.jb.setIcon(Icon2);
					DEF.jl.setForeground(Color.red);
					DEF.jl.setText("Eingabe Syntax nicht korrekt, Feld nicht auf Spielbrett");
					DEF.jTF_Start_Ziel.setFont(font);
					DEF.jTF_Start_Ziel.setBackground(Color.red);
					DEF.jTF_Start_Ziel.setForeground(Color.white);

					// sp.addToTextArea(ausgabe);
					// System.out.println("Falsche eingabe neue eingabe");// an unteres
					// Textfeld
					// weiterleiten
					// eingabe erneut starten...
				}

			}
		
//		------------------------------------------------------------------------------------#-------------------------------------________EASTBEREICH_________-------========_______ENDE_=======------
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
									ImageIcon icon = new ImageIcon("Dame_Dame_Braun.png");
									spielbrett.Spielfelder[j][i].setIcon(icon);
								} else {
									ImageIcon icon = new ImageIcon("Dame_Stein_Braun.png");
									spielbrett.Spielfelder[j][i].setIcon(icon);
								}
							} else {
								if (spiel.getSpielbrett().getBrett()[i][j].getSpielfigur().isDame() == false) {

									ImageIcon icon = new ImageIcon("Dame_Stein_weiss_50.png");
									spielbrett.Spielfelder[j][i].setIcon(icon);
								} else {
									ImageIcon icon = new ImageIcon("Dame_Dame_weiss_50.png");
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
		setzeSteine();
		if (spiel.getActiveSpieler() == spiel.getSpieler()[0]) {
			if(spiel.checkSiegkondition(spiel.getActiveSpieler())){
				spielbrett.spielGewonnenText.setText(spiel.getActiveSpieler()+" hat gewonnen.");
				spielbrett.spielGewonnenDialog.setVisible(true);
			}
			spiel.checkAndGetDame(spiel.getActiveSpieler());
			spiel.setActiveSpieler(spiel.getSpieler()[1]);
			
			sp.addToTextArea("Zug "+rundenZaehler+": "+spiel.getActiveSpieler().getFarbe());
			if(spiel.getActiveSpieler().getKi()!=null){
				spielbrett.kiDialog.setVisible(true);
			}
			rundenZaehler++;
		} else {
			if(spiel.checkSiegkondition(spiel.getActiveSpieler())){
				spielbrett.spielGewonnenText.setText(spiel.getActiveSpieler()+" hat gewonnen.");
				spielbrett.spielGewonnenDialog.setVisible(true);
			}
			spiel.checkAndGetDame(spiel.getActiveSpieler());
			spiel.setActiveSpieler(spiel.getSpieler()[0]);
			
			sp.addToTextArea("Zug "+rundenZaehler+": "+spiel.getActiveSpieler().getFarbe());
			if(spiel.getActiveSpieler().getKi()!=null){
				spielbrett.kiDialog.setVisible(true);
			}
		}
	}
		/**
		 * JAVAVA JVAJVA VAVJAVAVAVJ DOCCCCCCCCCCCC PLPPLLSLLSLSLSLSSSSSSS!!!!
		 */
		public void setSpiel(Spiel spiel) {
			this.spiel = spiel;
		}

		public void setScrollPane(ScrollPane sp) {
			this.sp = sp;

		}

		public void setSpielbrett(Spielbrett spielbrett) {
		this.spielbrett=spielbrett;
			
		}

		public void setMenu(MenuLeiste ml) {
			this.menu=ml;
			
		}
		public void setDEF(DameStartZielEingabe def){
this.DEF=def;
	}

	
	

	
	
	
		



	
	
	

}