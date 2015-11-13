package frontend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ButtonModel;
import javax.swing.JFileChooser;

import backend.Spiel;

public class EH_MenuLeiste implements ActionListener {

	MenuLeiste menu = null;
	Spiel spiel;

	public EH_MenuLeiste(MenuLeiste menu) {
		// Swing Seite 36 Konstruktor
		// MenuLeiste in EH_MenuLeiste
		this.menu = menu;
		spiel = new Spiel();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// LADEN

		if (e.getSource() == menu.jmi_laden) {
			menu.jd_laden.setVisible(true);

		}
		if (e.getSource() == menu.vorLadenSpeichern_ja) {
			menu.jd_laden.dispose();
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
				// spiel.saveSerialize(filename);
			} else {
				// PDF speichern
			}
		}
		if (e.getSource() == menu.vorLadenSpeichern_nein) {
			menu.jd_laden.dispose();
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
						// TODO Auto-generated catch block
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
			// altes Spiel laden
			// neues Spiel laden
		}

		// Neues Spiel starten
		if (e.getSource() == menu.jmi_neuesSpiel) {
			menu.jd_neuesSpiel.setVisible(true);
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
		if (e.getSource() == menu.jb_bestaetigen) {
//			String nameWeiß;
//			String nameSchwarz;
			boolean dame1 = false;
			boolean dame2 = false;
			if (menu.rb_ki1.isSelected()) {
				dame1 = true;
			}else{
				dame1 = false;
			}
			if (menu.rb_ki2.isSelected()) {
				dame2 = true;
			}else{
				dame2 = false;
			}

			String nameWeiß = menu.jt_spieler1.getText();
			String nameSchwarz = menu.jt_spieler2.getText();
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
				// spiel.saveSerialize(filename);
			} else {
				// PDF speichern
			}

		}

		if (e.getSource() == menu.hintergrundAendern) {

		}

		if (e.getSource() == menu.SteineFarbeAendern) {
		}

		if (e.getSource() == menu.spielBeschreibung) {
			menu.f.setVisible(true);
		}
	}

}
