package frontend;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import backend.FarbEnum;
import backend.Spiel;

public class EH_Spielbrett implements ActionListener {

	private Spielbrett spielbrett;
	private Color klickColor = new Color(130, 255, 174);
	private Spiel spiel;

	private int[] buttonkoords = new int[2];

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
					spielbrett.Spielfelder[j][i].setIcon(null);
					if (spiel.getSpielbrett().getBrett()[i][j].getSpielfigur() != null) {

						if (spiel.getSpielbrett().getBrett()[i][j].getSpielfigur().getFarbe() == FarbEnum.schwarz) {
							if (spiel.getSpielbrett().getBrett()[i][j].getSpielfigur().isDame() == false) {
								ImageIcon icon = new ImageIcon("Schwarz.gif");
								spielbrett.Spielfelder[j][i].setIcon(icon);
							} else {
								ImageIcon icon = new ImageIcon("SchwarzDame.png");
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
					}
				}
			}
		}
	}

	/**
	 * JAVAVA JVAJVA VAVJAVAVAVJ DOCCCCCCCCCCCC PLPPLLSLLSLSLSLSSSSSSS!!!!
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

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
									if (spiel.doTheMove(spiel.getActiveSpieler(), spiel.getSpielbrett().getBrett()[buttonkoords[1]][buttonkoords[0]], spiel.getSpielbrett().getBrett()[i][j]) == true) {
										bewegeIcon(buttonkoords[0], buttonkoords[1], j, i);
										spielbrett.Spielfelder[j][i].setBackground(Color.black);
										spielbrett.Spielfelder[buttonkoords[0]][buttonkoords[1]].setBackground(Color.black);
										if (spiel.getActiveSpieler() == spiel.getSpieler()[0]) {
											spiel.setActiveSpieler(spiel.getSpieler()[1]);
										} else {
											spiel.setActiveSpieler(spiel.getSpieler()[0]);
										}
										setzeSteine();
									} else {
										spielbrett.Spielfelder[j][i].setBackground(Color.black);
										spielbrett.Spielfelder[buttonkoords[0]][buttonkoords[1]].setBackground(Color.black);
									}
								}

							}
							spielbrett.Spielfelder[buttonkoords[0]][buttonkoords[1]].setBackground(Color.black);
							spielbrett.Spielfelder[j][i].setBackground(Color.black);
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
								spielbrett.Spielfelder[j][i].setBackground(klickColor);
							}
						}
					}
				}
			}
		}

	}

	/**
	 * JAVAVA JVAJVA VAVJAVAVAVJ DOCCCCCCCCCCCC PLPPLLSLLSLSLSLSSSSSSS!!!!
	 */
	private void bewegeIcon(int i, int j, int j2, int i2) {
		spielbrett.Spielfelder[j2][i2].setIcon(spielbrett.Spielfelder[i][j].getIcon());
		spielbrett.Spielfelder[i][j].setIcon(null);
	}

	/**
	 * JAVAVA JVAJVA VAVJAVAVAVJ DOCCCCCCCCCCCC PLPPLLSLLSLSLSLSSSSSSS!!!!
	 */
	public void setSpiel(Spiel spiel, Spielbrett spielbrett) {
		this.spiel = spiel;
		this.spielbrett = spielbrett;
		setzeSteine();
	}

}
