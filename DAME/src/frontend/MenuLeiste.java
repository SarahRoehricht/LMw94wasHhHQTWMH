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
import javax.swing.filechooser.FileNameExtensionFilter;

public class MenuLeiste extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
	Rectangle bounds = env.getMaximumWindowBounds();

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

	// soll aktueller Spielstand gespeichert werden, bevor Spiel geladen
	JDialog jd_laden;
	JDialog jd_neuesSpiel;
	JDialog jd_schliessen;

	JLabel jl_laden;
	JLabel jl_neuesSpiel;
	JLabel jl_schliessen;

	JButton laden_ja;
	JButton laden_nein;
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

		filter_csv = new FileNameExtensionFilter("csv", "csv"); 
		filter_ser = new FileNameExtensionFilter("ser", "ser"); 
		filter_pdf = new FileNameExtensionFilter("pdf", "pdf"); 
		panel = new JPanel();
		jfc_laden = new JFileChooser(".");
		jfc_laden.removeChoosableFileFilter(jfc_laden.getChoosableFileFilters()[0]);
		jfc_speichern = new JFileChooser(".");
		jfc_laden.addChoosableFileFilter(filter_csv);
		jfc_laden.addChoosableFileFilter(filter_ser);
		jfc_laden.addChoosableFileFilter(filter_pdf);
		//c = panel.getContentPane();

		jp_laden = new JPanel();
		jp_neuesSpiel = new JPanel();
		jp_schliessen = new JPanel();
	
		jl_laden = new JLabel(
				"Soll der aktuelle Spielstand gespeichert werden?");
		jl_laden.setHorizontalAlignment(JLabel.CENTER);

		jl_schliessen = new JLabel("Möchten Sie das Spiel wirklich verlassen?");
		jl_schliessen.setHorizontalAlignment(JLabel.CENTER);

		// Leiste mit Unterpunkten
		leiste = new JMenuBar();

		datei = new JMenu("Datei");
		bearbeiten = new JMenu("Bearbeiten");
		hilfe = new JMenu("Hilfe");

		jmi_laden = new JMenuItem("Spiel laden");
		jmi_laden.addActionListener(this);

		jmi_speichern = new JMenuItem("Spiel speichern");
		jmi_speichern.addActionListener(this);

		jmi_neuesSpiel = new JMenuItem("neues Spiel starten");
		jmi_neuesSpiel.addActionListener(this);

		jmi_schliessen = new JMenuItem("schließen");
		jmi_schliessen.addActionListener(this);

		hintergrundAendern = new JMenuItem("Hintergrund ändern");
		hintergrundAendern.addActionListener(this);

		SteineFarbeAendern = new JMenuItem("Steinfarbe ändern");
		SteineFarbeAendern.addActionListener(this);

		spielBeschreibung = new JMenuItem("Spielbeschreibung");
		spielBeschreibung.addActionListener(this);

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

		// JButtons
		laden_ja = new JButton("ja");
		laden_nein = new JButton("nein");
		speichern_ja = new JButton("ja");
		speichern_nein = new JButton("nein");
		verlassen_ja = new JButton("ja");
		verlassen_nein = new JButton("nein");
		schliessen_ja = new JButton("ja");
		schliessen_nein = new JButton("nein");

		laden_ja.addActionListener(this);
		laden_nein.addActionListener(this);
		speichern_ja.addActionListener(this);
		speichern_nein.addActionListener(this);
		verlassen_ja.addActionListener(this);
		verlassen_nein.addActionListener(this);
		schliessen_ja.addActionListener(this);
		schliessen_nein.addActionListener(this);

		laden_ja.setPreferredSize(new Dimension(70, 20));
		laden_nein.setPreferredSize(new Dimension(70, 20));
		speichern_ja.setPreferredSize(new Dimension(100, 20));
		speichern_nein.setPreferredSize(new Dimension(100, 20));
		schliessen_ja.setPreferredSize(new Dimension(100, 20));
		schliessen_nein.setPreferredSize(new Dimension(100, 20));

		jp_laden.add(laden_ja);
		jp_laden.add(laden_nein);

		jp_schliessen.add(schliessen_ja);
		jp_schliessen.add(schliessen_nein);

		// Dialogfenster
		jd_laden = new JDialog();
		jd_laden.setTitle("Sicherung des Spielstands");
		jd_laden.setSize(350, 150);
		jd_laden.setModal(true);
		jd_laden.add(jl_laden);
		jd_laden.add(jp_laden, BorderLayout.SOUTH);
		jd_laden.add(jp_laden, BorderLayout.SOUTH);
		jd_laden.setLocationRelativeTo(null);

		jd_schliessen = new JDialog();
		jd_schliessen.setTitle("Spiel verlassen");
		jd_schliessen.setSize(400, 200);
		jd_schliessen.setModal(true);
		jd_schliessen.add(jl_schliessen);
		jd_schliessen.add(jp_schliessen, BorderLayout.SOUTH);
		jd_schliessen.add(jp_schliessen, BorderLayout.SOUTH);
		jd_schliessen.setLocationRelativeTo(null);

//		panel.setSize(bounds.width, bounds.height);
//		panel.add(leiste, BorderLayout.NORTH);
//		panel.setVisible(true);

	}

	public Component getJPanel(){
		panel.add(leiste);
		return panel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		// nicht verschachtelt mit e.getSource() arbeiten!!!!!!!
		// funktioniert nicht ???

		// LADEN
		if (e.getSource() == jmi_laden) {
			//jd_laden.setVisible(true);
			jfc_laden.setVisible(true);
			jfc_laden.showOpenDialog(this);
			
		}
//		if (e.getSource() == laden_ja) {
//			System.out
//					.println("JDialog nur anzeigen, wenn bereits ein Spiel läuft");
//			System.out
//					.println("Aktuelles Spiel wird abgebrochen und gespeichert");
//			System.out
//					.println("Eingabe des Titels und Art der Speicherung in neuem Fenster");
//			System.out.println("Älteres Spiel soll geladen werden");
//			System.out
//					.println("Öffnen eines neuen JDialogs mit Auswahl aller gespeicherter Spiele");
//			jd_laden.dispose();
//		}
//		if (e.getSource() == laden_nein) {
//			System.out
//					.println("Der aktuelle Spielstand wird nicht gespeichert");
//			System.out
//					.println("Öffnen eines neuen JDialogs mit Auswahl aller gespeicherter Spiele");
//			jd_laden.dispose();
//		}

		
		// Neues Spiel starten
		if (e.getSource() == jmi_neuesSpiel) {
			jd_laden.setVisible(true);
		}
		if (e.getSource() == verlassen_ja) {
			System.out
					.println("JDialog nur anzeigen, wenn bereits ein Spiel läuft");
			System.out
					.println("Aktuelles Spiel wird abgebrochen und gespeichert");
			System.out
					.println("Eingabe des Titels und Art der Speicherung in neuem Fenster");
			System.out.println("Älteres Spiel soll geladen werden");
			System.out
					.println("Öffnen eines neuen JDialogs mit Auswahl aller gespeicherter Spiele");
			jd_laden.dispose();
		}
		if (e.getSource() == verlassen_nein) {
			jd_laden.dispose();
		}

		// Schliessen
		if (e.getSource() == jmi_schliessen) {
			jd_schliessen.setVisible(true);
		}
		if (e.getSource() == schliessen_ja) {
			System.out
					.println("JDialog nur anzeigen, wenn bereits ein Spiel läuft");
			System.out
					.println("Aktuelles Spiel wird abgebrochen und gespeichert");
			System.out
					.println("Eingabe des Titels und Art der Speicherung in neuem Fenster");
			System.out.println("Älteres Spiel soll geladen werden");
			System.out
					.println("Öffnen eines neuen JDialogs mit Auswahl aller gespeicherter Spiele");
			jd_schliessen.dispose();
		}
		if (e.getSource() == schliessen_nein) {
			jd_schliessen.dispose();
		}
		
		
		//Speichern
		if(e.getSource() == jmi_speichern){
			//System.out.println("Fenster zur Eingabe von Dateiname und Speicherformat wird geöffnet");
			jfc_speichern.setVisible(true);
			jfc_speichern.showSaveDialog(this);
		}
		
		if (e.getSource() == hintergrundAendern) {

		}

		if (e.getSource() == SteineFarbeAendern) {

		}

		if (e.getSource() == spielBeschreibung) {

		}
	}

	public static void main(String[] args) {
		MenuLeiste so = new MenuLeiste();
	}
}