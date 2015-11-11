package frontend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

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
			menu.jfc_speichern.setVisible(true);
			menu.jfc_speichern.showSaveDialog(menu);
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
			System.out.println("JDialog nur anzeigen, wenn bereits ein Spiel läuft");
			System.out.println("Aktuelles Spiel wird abgebrochen und gespeichert");
			System.out.println("Eingabe des Titels und Art der Speicherung in neuem Fenster");
			System.out.println("Älteres Spiel soll geladen werden");
			System.out.println("Öffnen eines neuen JDialogs mit Auswahl aller gespeicherter Spiele");

		}
		if (e.getSource() == menu.verlassen_nein) {
			menu.jd_neuesSpiel.dispose();
		}

		// Schliessen
		if (e.getSource() == menu.jmi_schliessen) {
			menu.jd_schliessen.setVisible(true);
		}
		if (e.getSource() == menu.schliessen_ja) {
			System.out.println("JDialog nur anzeigen, wenn bereits ein Spiel läuft");
			System.out.println("Aktuelles Spiel wird abgebrochen und gespeichert");
			System.out.println("Eingabe des Titels und Art der Speicherung in neuem Fenster");
			System.out.println("Älteres Spiel soll geladen werden");
			System.out.println("Öffnen eines neuen JDialogs mit Auswahl aller gespeicherter Spiele");
			// menu.jd_schliessen.dispose();
			System.exit(0);
		}
		if (e.getSource() == menu.schliessen_nein) {
			menu.jd_schliessen.dispose();
		}

		// Speichern
		if (e.getSource() == menu.jmi_speichern) {
			// System.out.println("Fenster zur Eingabe von Dateiname und Speicherformat wird geöffnet");
			menu.jfc_speichern.setVisible(true);
			menu.jfc_speichern.showSaveDialog(menu);
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
