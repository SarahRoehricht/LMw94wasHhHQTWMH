package frontend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import backend.Spiel;

/**
 * 
 * @author A2
 *
 */
public class EH_MenuLeiste implements ActionListener {

	MenuLeiste menu = null;
	Spiel spiel;

	public EH_MenuLeiste(MenuLeiste menu) {
		this.menu = menu;
		spiel = new Spiel();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// LADEN

		if (e.getSource() == menu.jmi_laden && spiel != null) {
			menu.jd_speichernVorLaden.setVisible(true);

		}
		if (e.getSource() == menu.jmi_laden && spiel == null) {
			menu.jfc_speichernVorLaden.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			String dateiName = menu.jfc_speichernVorLaden.getSelectedFile().getName();
			String dateiTyp = menu.jfc_speichernVorLaden.getFileFilter().getDescription();
			String pfad = menu.jfc_speichernVorLaden.getSelectedFile().getAbsolutePath();
			int pfadLaenge = pfad.length();
			int dateiNameLaenge = dateiName.length();
			String pfadOhneDateiname = pfad.substring(0, pfadLaenge - dateiNameLaenge);

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

			menu.jfc_speichernVorLaden.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			String dateiName = menu.jfc_speichernVorLaden.getSelectedFile().getName();
			String dateiTyp = menu.jfc_speichernVorLaden.getFileFilter().getDescription();
			String pfad = menu.jfc_speichernVorLaden.getSelectedFile().getAbsolutePath();
			int pfadLaenge = pfad.length();
			int dateiNameLaenge = dateiName.length();
			String pfadOhneDateiname = pfad.substring(0, pfadLaenge - dateiNameLaenge);

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
				String dateiName = datei.substring(0, menu.jfc_laden.getSelectedFile().getName().length() - 4);
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
		// Bestätiegn Button nur klickbar, wenn erforderliche Angaben gemacht
		// wurden
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
				JOptionPane.showMessageDialog(null, "Spieler 1: Ihr Name muss mindestens 3 Zeichen lang sein!", "Unvollständige Eingabe!", JOptionPane.WARNING_MESSAGE);
			}

			if (e.getSource() == menu.jb_bestaetigen && (nameSchwarz.length() < 3)) {
				JOptionPane.showMessageDialog(null, "Spieler 2: Ihr Name muss mindestens 3 Zeichen lang sein!", "Unvollständige Eingabe!", JOptionPane.WARNING_MESSAGE);
			}
			if (nameWeiß.equals(nameSchwarz)) {
				JOptionPane.showMessageDialog(null, "Spieler 1 und Spieler 2 dürfen nicht den selben Namen besitzen!", "Fehlerhafte Eingabe!", JOptionPane.WARNING_MESSAGE);
			}

			System.out.println(nameWeiß + ", " + dame1 + ", " + nameSchwarz + ", " + dame2);
			spiel = new Spiel(nameWeiß, dame1, nameSchwarz, dame2);
			menu.jf.dispose();
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

			menu.jfc_speichern.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			String dateiName = menu.jfc_speichern.getSelectedFile().getName();
			String dateiTyp = menu.jfc_speichern.getFileFilter().getDescription();
			String pfad = menu.jfc_speichern.getSelectedFile().getAbsolutePath();
			int pfadLaenge = pfad.length();
			int dateiNameLaenge = dateiName.length();
			String pfadOhneDateiname = pfad.substring(0, pfadLaenge - dateiNameLaenge);

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
	}
}
