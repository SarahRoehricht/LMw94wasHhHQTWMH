package frontend;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileFilter;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.html.HTMLEditorKit;

public class MenuLeiste extends JFrame {

	private static final long serialVersionUID = 1L;
	GraphicsEnvironment env;
	Rectangle bounds;

	/*********Für JPaneText*************/
	JFrame f;
	JTextPane pane;
	HTMLEditorKit eKit;
	/**********************************/
	
	JPanel panel;
	JFileChooser jfc_laden;
	JFileChooser jfc_speichern;
	FileNameExtensionFilter filter_csv;
	FileNameExtensionFilter filter_ser;
	FileNameExtensionFilter filter_pdf;
	JMenuBar leiste;
	Container c;

	JPanel jp_laden;
	JPanel jp_neuesSpiel;
	JPanel jp_schliessen;

	JDialog jd_neuesSpiel;
	JDialog jd_laden;
	JDialog jd_schliessen;

	JLabel jl_laden;
	JLabel jl_neuesSpiel;
	JLabel jl_schliessen;
	
	JTextArea jText;

	JButton vorLadenSpeichern_ja;
	JButton vorLadenSpeichern_nein;
	JButton speichern_ja;
	JButton speichern_nein;
	JButton verlassen_ja;
	JButton verlassen_nein;
	JButton schliessen_ja;
	JButton schliessen_nein;

	JMenu datei;
	JMenu bearbeiten;
	JMenu hilfe;

	JMenuItem jmi_laden;
	JMenuItem jmi_speichern;
	JMenuItem jmi_neuesSpiel;
	JMenuItem jmi_schliessen;

	JMenuItem hintergrundAendern;
	JMenuItem SteineFarbeAendern;

	JMenuItem spielBeschreibung;

	public MenuLeiste() {
		
		env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		bounds = env.getMaximumWindowBounds();
		
		/**************Für JPaneText************/
		final String text = "<h1>Die internationale Damevariante</h1> "
				+ "<p>Diese wird auf einem quadratischen Brett mit abwechselnd weißen und "
				+ "schwarzen Feldern gespielt.Die Größe des Spielbretts beträgt 12x12 Felder, "
				+ "wobei jeder Spieler 30 Steine besitzt.Gespielt wird nur auf den dunklen Feldern.</p>"

				+ "<p>Die Steine ziehen ein Feld in diagonaler Richtung, aber nur vorwärts. Gegnerische "
				+ "Steine müssen übersprungen und dadurch geschlagen werden, sofern das direkt angrenzende "
				+ "dahinter liegende Feld frei ist, indem auf dieses freie Feld gezogen wird. "
				+ "Wenn das Zielfeld eines Sprungs auf ein Feld führt, von dem aus ein weiterer "
				+ "Stein übersprungen werden kann, so wird der Sprung fortgesetzt. Alle übersprungenen "
				+ "Steine werden vom Brett genommen.Erreicht ein Spielstein die gegnerische Grundlinie, "
				+ "wird er zur Dame befördert. Dies wird kenntlich gemacht, indem ein zweiter Stein "
				+ "obenauf gesetzt wird.</p>"

				+ "<p>Eine Dame darf beliebig weit vorwärts oder rückwärts ziehen und springen. Beim "
				+ "Überspringen eines gegnerischen Steines muss die Dame allerdings auf dem unmittelbar "
				+ "dahinterliegen Diagonalfeld aufsetzen. Falls sie von dem neuen Feld aus über andere "
				+ "Steine springen kann, muss sie das auch tun. Es gelten also die Sprungregeln für einfache "
				+ "Steine, mit der zusätzlichen Regel, dass die Dame über mehrere "
				+ "Felder vorwärts und eben auch rückwärts springen kann.</p>"
				+ "<p>Da eine Dame auf dem Feld hinter dem geschlagenen Stein aufsetzen muss, ist es möglich,"
				+ " ein Endspiel von zwei Damen gegen eine einzelne gegnerische Dame zu gewinnen. Beim "
				+ "Erreichen der gegnerischen Grundlinie eines Spielsteins durch Schlagen gegnerischer Figuren "
				+ "geschieht ebenfalls eine Umwandlung zur Dame. Der Zug endet damit; es ist nicht möglich, "
				+ "mit der Umwandlung zur Dame sofort weitere gegnerische Spielsteine zu schlagen."
				+ "Die Spielsteine können diagonal gezogen werden und auch andere Steine schlagen.</p>"

				+ "<p>Ziel des Spieles ist es, dem Gegner alle Zugmöglichkeiten "
				+ "zu nehmen, also alle gegnerischen Steine zu schlagen oder zu blockieren.</p>";
		
		f = new JFrame("Spielbeschreibung");
		pane = new JTextPane();
		eKit = new javax.swing.text.html.HTMLEditorKit();
		pane.setEditorKit(eKit);
		pane.setText(text);
		f.getContentPane().add(new JScrollPane(pane));
		f.setSize(500, 400);
		f.setLocationRelativeTo(null);
		/**************************************/


		filter_csv = new FileNameExtensionFilter("csv", "csv");
		filter_ser = new FileNameExtensionFilter("ser", "ser");
		filter_pdf = new FileNameExtensionFilter("pdf", "pdf");
		panel = new JPanel();
		jfc_laden = new JFileChooser(".");
		jfc_laden.removeChoosableFileFilter(jfc_laden.getChoosableFileFilters()[0]);
		jfc_laden.addChoosableFileFilter(filter_csv);
		jfc_laden.addChoosableFileFilter(filter_ser);

		jfc_speichern = new JFileChooser(".");
		jfc_speichern.removeChoosableFileFilter(jfc_speichern.getChoosableFileFilters()[0]);
		jfc_speichern.addChoosableFileFilter(filter_csv);
		jfc_speichern.addChoosableFileFilter(filter_ser);
		jfc_speichern.addChoosableFileFilter(filter_pdf);
		
		jp_laden = new JPanel();
		jp_neuesSpiel = new JPanel();
		jp_schliessen = new JPanel();

		jl_laden = new JLabel("Soll der aktuelle Spielstand gespeichert werden?");
		jl_laden.setHorizontalAlignment(JLabel.CENTER);

		jl_neuesSpiel = new JLabel("Möchte Sie das aktuelle Spiel wirklich beenden?");
		jl_neuesSpiel.setHorizontalAlignment(JLabel.CENTER);
		
		jl_schliessen = new JLabel("Möchten Sie das Spiel wirklich verlassen?");
		jl_schliessen.setHorizontalAlignment(JLabel.CENTER);

		// Leiste mit Unterpunkten
		leiste = new JMenuBar();

		datei = new JMenu("Datei");
		bearbeiten = new JMenu("Bearbeiten");
		hilfe = new JMenu("Hilfe");

		jmi_laden = new JMenuItem("Spiel laden");

		jmi_speichern = new JMenuItem("Spiel speichern");

		jmi_neuesSpiel = new JMenuItem("neues Spiel starten");

		jmi_schliessen = new JMenuItem("schließen");

		hintergrundAendern = new JMenuItem("Hintergrund ändern");

		SteineFarbeAendern = new JMenuItem("Steinfarbe ändern");

		spielBeschreibung = new JMenuItem("Spielbeschreibung");

		leiste.add(datei);
		leiste.add(bearbeiten);
		leiste.add(hilfe);

		datei.add(jmi_laden);
		datei.add(jmi_speichern);
		datei.add(jmi_neuesSpiel);
		datei.add(jmi_schliessen);

		bearbeiten.add(hintergrundAendern);
		bearbeiten.add(SteineFarbeAendern);

		hilfe.add(spielBeschreibung);

		/******************************** ALLE Buttons ... **************************************/
		vorLadenSpeichern_ja = new JButton("ja");
		vorLadenSpeichern_nein = new JButton("nein");
		speichern_ja = new JButton("ja");
		speichern_nein = new JButton("nein");
		verlassen_ja = new JButton("ja");
		verlassen_nein = new JButton("nein");
		schliessen_ja = new JButton("ja");
		schliessen_nein = new JButton("nein");

		/******************************** ... und ihre EIGENSCHAFTEN **************************************/
		vorLadenSpeichern_ja.setPreferredSize(new Dimension(70, 20));
		vorLadenSpeichern_nein.setPreferredSize(new Dimension(70, 20));
		verlassen_ja.setPreferredSize(new Dimension(70, 20));
		verlassen_nein.setPreferredSize(new Dimension(70, 20));
		speichern_ja.setPreferredSize(new Dimension(100, 20));
		speichern_nein.setPreferredSize(new Dimension(100, 20));
		schliessen_ja.setPreferredSize(new Dimension(100, 20));
		schliessen_nein.setPreferredSize(new Dimension(100, 20));

		jp_laden.add(vorLadenSpeichern_ja);
		jp_laden.add(vorLadenSpeichern_nein);
		
		jp_neuesSpiel.add(verlassen_ja);
		jp_neuesSpiel.add(verlassen_nein);

		jp_schliessen.add(schliessen_ja);
		jp_schliessen.add(schliessen_nein);


		
		/******************************** JDialog-Fenster LADEN**************************************/
		jd_laden = new JDialog();
		jd_laden.setTitle("Aktuelles Spiel speichern?");
		jd_laden.setSize(400, 130);
		jd_laden.setModal(true);
		jd_laden.add(jl_laden);
		jd_laden.add(jp_laden, BorderLayout.SOUTH);
		jd_laden.add(jp_laden, BorderLayout.SOUTH);
		jd_laden.setLocationRelativeTo(null);
		

		/******************************** JDialog-Fenster NEUES SPIEL **************************************/
		jd_neuesSpiel = new JDialog();
		jd_neuesSpiel.setTitle("Neues Spiel");
		jd_neuesSpiel.setSize(400, 130);
		jd_neuesSpiel.setModal(true);
		jd_neuesSpiel.add(jl_neuesSpiel);
		jd_neuesSpiel.add(jp_neuesSpiel, BorderLayout.SOUTH);
		jd_neuesSpiel.add(jp_neuesSpiel, BorderLayout.SOUTH);
		jd_neuesSpiel.setLocationRelativeTo(null);
		
		
		/******************************** JDialog-Fenster SCHLIESSEN **************************************/
		jd_schliessen = new JDialog();
		jd_schliessen.setTitle("Spiel verlassen");
		jd_schliessen.setSize(320, 120);
		jd_schliessen.setModal(true);
		jd_schliessen.add(jl_schliessen);
		jd_schliessen.add(jp_schliessen, BorderLayout.SOUTH);
		jd_schliessen.add(jp_schliessen, BorderLayout.SOUTH);
		jd_schliessen.setLocationRelativeTo(null);

		/******************************** ALLE ActionListener **************************************/
		jmi_laden.addActionListener(new EH_MenuLeiste(this));
		jmi_speichern.addActionListener(new EH_MenuLeiste(this));
		jmi_neuesSpiel.addActionListener(new EH_MenuLeiste(this));
		jmi_schliessen.addActionListener(new EH_MenuLeiste(this));
		hintergrundAendern.addActionListener(new EH_MenuLeiste(this));
		SteineFarbeAendern.addActionListener(new EH_MenuLeiste(this));
		spielBeschreibung.addActionListener(new EH_MenuLeiste(this));
		vorLadenSpeichern_ja.addActionListener(new EH_MenuLeiste(this));
		vorLadenSpeichern_nein.addActionListener(new EH_MenuLeiste(this));
		speichern_ja.addActionListener(new EH_MenuLeiste(this));
		speichern_nein.addActionListener(new EH_MenuLeiste(this));
		verlassen_ja.addActionListener(new EH_MenuLeiste(this));
		verlassen_nein.addActionListener(new EH_MenuLeiste(this));
		schliessen_ja.addActionListener(new EH_MenuLeiste(this));
		schliessen_nein.addActionListener(new EH_MenuLeiste(this));

	}

	public Component getJPanel() {
		panel.add(leiste);
		return panel;
	}
}