package frontend;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.html.HTMLEditorKit;

/**
 * 
 * @author A2
 *
 */
public class MenuLeiste extends JFrame {
	
	private static final long serialVersionUID = 1L;
	GraphicsEnvironment env;
	Rectangle bounds;
	EventHandler eh;
	/********* Fuer JPaneText *************/
	JFrame f;
	JTextPane pane;
	HTMLEditorKit eKit;

	/**********************************/

	/********* Fenster fuer NEUES SPIEL **************/

	JFrame jf;
	JPanel jp;
	JLabel jl_spieler1;
	JLabel jl_spieler2;
	JRadioButton rb_mensch1;
	JRadioButton rb_ki1;
	JRadioButton rb_mensch2;
	JRadioButton rb_ki2;
	JRadioButton rb_PDF;
	JRadioButton rb_SER;
	ButtonGroup bg1;
	ButtonGroup bg2;
	JTextField jt_spieler1;
	JTextField jt_spieler2;
	JButton jb_abbruch;
	JButton jb_bestaetigen;

	Font spieler;
	Font fontEingabe;

	/********************************************/

	JPanel panel;
	JFileChooser jfc_laden;
	JFileChooser jfc_speichern;
	JFileChooser jfc_speichernVorLaden;
	FileNameExtensionFilter filter_csv;
	FileNameExtensionFilter filter_ser;
	FileNameExtensionFilter filter_pdf;
	JMenuBar leiste;
	Container c;

	JPanel jp_laden;
	JPanel jp_neuesSpiel;
	JPanel jp_schliessen;

	JDialog jd_neuesSpiel;
	JDialog jd_speichernVorLaden;
	JDialog jd_emailSenden;
	JDialog jd_schliessen;

	JLabel jl_laden;
	JLabel jl_neuesSpiel;
	JLabel jl_schliessen;
	//Email
	JTextArea jText;
	JTextField tf; 
	JTextField tf2;
	JLabel jlEmailFormat;
	JLabel jlLeer;
	JLabel jlLeer2;
	
	
	JButton vorLadenSpeichern_ja;
	JButton vorLadenSpeichern_nein;
	JButton speichern_ja;
	JButton speichern_nein;
	JButton verlassen_ja;
	JButton verlassen_nein;
	JButton schliessen_ja;
	JButton schliessen_nein;
	JButton jbEmail;

	JMenu datei;
	JMenu bearbeiten;
	JMenu hilfe;

	JMenuItem jmi_laden;
	JMenuItem jmi_speichern;
	JMenuItem jmi_neuesSpiel;
	JMenuItem jmi_email;
	JMenuItem jmi_schliessen;

	JMenuItem hintergrundAendern;
	JMenuItem SteineFarbeAendern;

	JMenuItem spielBeschreibung;

	public MenuLeiste(EventHandler eh) {
		this.eh = eh;
		env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		bounds = env.getMaximumWindowBounds();

		/************** Fuer JPaneText ************/
		final String text = "<h1>Die internationale Damevariante</h1> "
				+ "<p>Diese wird auf einem quadratischen Brett mit abwechselnd weissen und "
				+ "schwarzen Feldern gespielt.Die Groesse des Spielbretts betraegt 12x12 Felder, "
				+ "wobei jeder Spieler 30 Steine besitzt.Gespielt wird nur auf den dunklen Feldern.</p>"

				+ "<p>Die Steine ziehen ein Feld in diagonaler Richtung, aber nur vorwaerts. Gegnerische "
				+ "Steine muessen uebersprungen und dadurch geschlagen werden, sofern das direkt angrenzende "
				+ "dahinter liegende Feld frei ist, indem auf dieses freie Feld gezogen wird. "
				+ "Wenn das Zielfeld eines Sprungs auf ein Feld fuehrt, von dem aus ein weiterer "
				+ "Stein uebersprungen werden kann, so wird der Sprung fortgesetzt. Alle uebersprungenen "
				+ "Steine werden vom Brett genommen.Erreicht ein Spielstein die gegnerische Grundlinie, "
				+ "wird er zur Dame befoerdert. Dies wird kenntlich gemacht, indem ein zweiter Stein "
				+ "obenauf gesetzt wird.</p>"

				+ "<p>Eine Dame darf beliebig weit vorwaerts oder rueckwaerts ziehen und springen. Beim "
				+ "ueberspringen eines gegnerischen Steines muss die Dame allerdings auf dem unmittelbar "
				+ "dahinterliegen Diagonalfeld aufsetzen. Falls sie von dem neuen Feld aus ueber andere "
				+ "Steine springen kann, muss sie das auch tun. Es gelten also die Sprungregeln fuer einfache "
				+ "Steine, mit der zusaetzlichen Regel, dass die Dame ueber mehrere "
				+ "Felder vorwaerts und eben auch rueckwaerts springen kann.</p>"
				+ "<p>Da eine Dame auf dem Feld hinter dem geschlagenen Stein aufsetzen muss, ist es moeglich,"
				+ " ein Endspiel von zwei Damen gegen eine einzelne gegnerische Dame zu gewinnen. Beim "
				+ "Erreichen der gegnerischen Grundlinie eines Spielsteins durch Schlagen gegnerischer Figuren "
				+ "geschieht ebenfalls eine Umwandlung zur Dame. Der Zug endet damit; es ist nicht moeglich, "
				+ "mit der Umwandlung zur Dame sofort weitere gegnerische Spielsteine zu schlagen."
				+ "Die Spielsteine koennen diagonal gezogen werden und auch andere Steine schlagen.</p>"

				+ "<p>Ziel des Spieles ist es, dem Gegner alle Zugmoeglichkeiten "
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

		/************************* Fenster fuer NEUES SPIEL *******************************/

		jf = new JFrame();
		jp = new JPanel();
		jp.setLayout(null);
		jl_spieler1 = new JLabel();
		jl_spieler2 = new JLabel();
		rb_mensch1 = new JRadioButton();
		rb_ki1 = new JRadioButton();
		rb_mensch2 = new JRadioButton();
		rb_ki2 = new JRadioButton();
		bg1 = new ButtonGroup();
		bg2 = new ButtonGroup();
		jt_spieler1 = new JTextField();
		jt_spieler2 = new JTextField();
		jb_abbruch = new JButton();
		jb_bestaetigen = new JButton();

		// spieler = new Font("Courier NEW", Font.BOLD, 22);
		spieler = new Font("", Font.BOLD, 20);
		fontEingabe = new Font("", Font.BOLD, 20);

		bg1.add(rb_mensch1);
		bg1.add(rb_ki1);
		bg2.add(rb_mensch2);
		bg2.add(rb_ki2);
		jp.add(jl_spieler1);
		jp.add(jl_spieler2);
		jp.add(rb_mensch1);
		jp.add(rb_mensch2);
		jp.add(rb_ki1);
		jp.add(rb_ki2);
		jp.add(jt_spieler1);
		jp.add(jt_spieler2);
		jp.add(jb_abbruch);
		jp.add(jb_bestaetigen);

		// Spieler 1 (links)
		jl_spieler1.setBounds(30, 15, 120, 25);
		jl_spieler1.setFont(spieler);
		// jl_spieler1.setText("Spieler 1");
		jl_spieler1.setText("Weiss");
		rb_mensch1.setBounds(15, 55, 100, 25);
		rb_mensch1.setText("Mensch");
		rb_mensch1.setSelected(true);
		rb_ki1.setBounds(15, 80, 100, 25);
		rb_ki1.setText("KI");

		jt_spieler1.setBounds(15, 120, 100, 25);
		jt_spieler1.setSize(140, 30);
		jt_spieler1.setFont(fontEingabe);
		jt_spieler1.setHorizontalAlignment(JTextField.CENTER);
		jb_abbruch.setBounds(25, 160, 120, 25);
		jb_abbruch.setText("abbrechen");

		// Spieler 2 (rechts)
		jl_spieler2.setBounds(215, 15, 120, 25);
		jl_spieler2.setFont(spieler);
		// jl_spieler2.setText("Spieler 2");
		jl_spieler2.setText("Schwarz");
		rb_mensch2.setBounds(200, 55, 100, 25);
		rb_mensch2.setText("Mensch");
		rb_mensch2.setSelected(true);
		rb_ki2.setBounds(200, 80, 100, 25);
		rb_ki2.setText("KI");
		jt_spieler2.setBounds(200, 120, 100, 25);
		jt_spieler2.setSize(140, 30);
		jt_spieler2.setFont(fontEingabe);
		jt_spieler2.setHorizontalAlignment(JTextField.CENTER);
		jb_bestaetigen.setBounds(210, 160, 120, 25);
		jb_bestaetigen.setText("bestaetigen");

		jb_abbruch.addActionListener(eh);
		jb_bestaetigen.addActionListener(eh);

		jf.add(jp);

		jf.setSize(380, 250);
		jf.setLocationRelativeTo(null);

		// // Spieler 1 (links)
		// jl_spieler1.setBounds(30, 15, 100, 25);
		// jl_spieler1.setFont(spieler);
		// jl_spieler1.setText("Spieler 1");
		// rb_mensch1.setBounds(15, 55, 100, 25);
		// rb_mensch1.setText("Mensch");
		// rb_ki1.setBounds(15, 80, 100, 25);
		// rb_ki1.setText("KI");
		// jt_spieler1.setBounds(15, 120, 100, 25);
		// jt_spieler1.setSize(120, 30);
		// jt_spieler1.setFont(fontEingabe);
		// jt_spieler1.setHorizontalAlignment(JTextField.CENTER);
		// jb_abbruch.setBounds(25, 160, 100, 25);
		// jb_abbruch.setText("abbrechen");
		//
		// // Spieler 2 (rechts)
		// jl_spieler2.setBounds(185, 15, 100, 25);
		// jl_spieler2.setFont(spieler);
		// jl_spieler2.setText("Spieler 2");
		// rb_mensch2.setBounds(170, 55, 100, 25);
		// rb_mensch2.setText("Mensch");
		// rb_ki2.setBounds(170, 80, 100, 25);
		// rb_ki2.setText("KI");
		// jt_spieler2.setBounds(170, 120, 100, 25);
		// jt_spieler2.setSize(120, 30);
		// jt_spieler2.setFont(fontEingabe);
		// jt_spieler2.setHorizontalAlignment(JTextField.CENTER);
		// jb_bestaetigen.setBounds(180, 160, 100, 25);
		// jb_bestaetigen.setText("bestaetigen");
		//
		// add(jp);
		//
		// setSize(320, 250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/*********************************************************************************/

		filter_csv = new FileNameExtensionFilter("csv", "csv");
		filter_ser = new FileNameExtensionFilter("ser", "ser");
		filter_pdf = new FileNameExtensionFilter("pdf", "pdf");
		panel = new JPanel();
		jfc_laden = new JFileChooser(".");
		jfc_laden
				.removeChoosableFileFilter(jfc_laden.getChoosableFileFilters()[0]);
		jfc_laden.addChoosableFileFilter(filter_csv);
		jfc_laden.addChoosableFileFilter(filter_ser);

		jfc_speichern = new JFileChooser(".");
		jfc_speichern.removeChoosableFileFilter(jfc_speichern
				.getChoosableFileFilters()[0]);
		jfc_speichern.addChoosableFileFilter(filter_csv);
		jfc_speichern.addChoosableFileFilter(filter_ser);
		jfc_speichern.addChoosableFileFilter(filter_pdf);

		jfc_speichernVorLaden = new JFileChooser(".");
		jfc_speichernVorLaden.removeChoosableFileFilter(jfc_speichernVorLaden
				.getChoosableFileFilters()[0]);
		jfc_speichernVorLaden.addChoosableFileFilter(filter_csv);
		jfc_speichernVorLaden.addChoosableFileFilter(filter_ser);
		jfc_speichernVorLaden.addChoosableFileFilter(filter_pdf);

		jp_laden = new JPanel();
		jp_neuesSpiel = new JPanel();
		jp_schliessen = new JPanel();

		jl_laden = new JLabel(
				"Soll der aktuelle Spielstand gespeichert werden?");
		jl_laden.setHorizontalAlignment(JLabel.CENTER);

		jl_neuesSpiel = new JLabel(
				"Moechten Sie das aktuelle Spiel wirklich beenden?");
		jl_neuesSpiel.setHorizontalAlignment(JLabel.CENTER);

		jl_schliessen = new JLabel("Moechten Sie das Spiel wirklich verlassen?");
		jl_schliessen.setHorizontalAlignment(JLabel.CENTER);

		// Leiste mit Unterpunkten
		leiste = new JMenuBar();

		datei = new JMenu("Datei");
		bearbeiten = new JMenu("Bearbeiten");
		hilfe = new JMenu("Hilfe");

		jmi_laden = new JMenuItem("Spiel laden");

		jmi_speichern = new JMenuItem("Spiel speichern");

		jmi_neuesSpiel = new JMenuItem("neues Spiel starten");
		jmi_email = new JMenuItem("email");

		jmi_schliessen = new JMenuItem("schliessen");

		hintergrundAendern = new JMenuItem("Hintergrund aendern");

		SteineFarbeAendern = new JMenuItem("Steinfarbe aendern");

		spielBeschreibung = new JMenuItem("Spielbeschreibung");

		leiste.add(datei);
		leiste.add(bearbeiten);
		leiste.add(hilfe);

		datei.add(jmi_laden);
		datei.add(jmi_speichern);
		datei.add(jmi_neuesSpiel);
		datei.add(jmi_email);
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

		/******************************** JDialog-Fenster LADEN **************************************/
		jd_speichernVorLaden = new JDialog();
		jd_speichernVorLaden.setTitle("Aktuelles Spiel speichern?");
		jd_speichernVorLaden.setSize(400, 130);
		jd_speichernVorLaden.setModal(true);
		jd_speichernVorLaden.add(jl_laden);
		jd_speichernVorLaden.add(jp_laden, BorderLayout.SOUTH);
		jd_speichernVorLaden.add(jp_laden, BorderLayout.SOUTH);
		jd_speichernVorLaden.setLocationRelativeTo(null);

		/******************************** JDialog-Fenster NEUES SPIEL **************************************/
		jd_neuesSpiel = new JDialog();
		jd_neuesSpiel.setTitle("Neues Spiel");
		jd_neuesSpiel.setSize(400, 130);
		jd_neuesSpiel.setModal(true);
		jd_neuesSpiel.add(jl_neuesSpiel);
		jd_neuesSpiel.add(jp_neuesSpiel, BorderLayout.SOUTH);
		jd_neuesSpiel.add(jp_neuesSpiel, BorderLayout.SOUTH);
		jd_neuesSpiel.setLocationRelativeTo(null);
		/******************************** JDialog-Fenster EMAIL **************************************/
		jd_emailSenden = new JDialog();
		jd_emailSenden.setTitle("Spielstand per Email senden");
		jd_emailSenden.setSize(445, 235);
		jd_emailSenden.setResizable(false);
//		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		//System.out.println(screenSize.width+","+screenSize.height);
		jd_emailSenden.setLocationRelativeTo(null);
		jd_emailSenden.setModal(true);
		Image image = Toolkit.getDefaultToolkit().getImage("Emailb.png");
		jd_emailSenden.setIconImage(image);
		tf = new JTextField();
		tf.setOpaque(true);
		tf.setBackground(Color.WHITE);
		tf2 = new JTextField();
		tf2.setOpaque(true);
		tf2.setBackground(Color.WHITE);
		//jlEmailFormat= new JLabel("Wählen Sie bitte aus welches Format");
		jlEmailFormat= new JLabel("Bitte wählen Sie aus welches Format");
		jlEmailFormat.setHorizontalAlignment(SwingConstants.RIGHT);
		
		
		jlEmailFormat.setOpaque(true);
		jlEmailFormat.setBackground(Color.WHITE);
		jlEmailFormat.setForeground(Color.BLACK);
		jlLeer = new JLabel(", Sie per Email senden möchten");
		jlLeer.setHorizontalAlignment(SwingConstants.LEFT);
		
		
		jlLeer2 = new JLabel("");
		jlLeer.setOpaque(true);
		jlLeer.setBackground(Color.WHITE);
		jlLeer.setForeground(Color.BLACK);
		jlLeer2.setOpaque(true);
		jlLeer2.setBackground(Color.WHITE);
		jlLeer2.setIcon(new ImageIcon("Emailb2.png"));
		jlLeer2.setHorizontalAlignment(SwingConstants.RIGHT);
		
		rb_PDF = new JRadioButton("PDF als Anhang",true);
		rb_SER = new JRadioButton("SER als Anhang");
		rb_PDF.setBackground(Color.WHITE);
		rb_SER.setBackground(Color.WHITE);
		rb_PDF.setForeground(Color.BLACK);
		rb_SER.setForeground(Color.BLACK);
		rb_SER.setHorizontalAlignment(SwingConstants.LEFT);
		rb_PDF.setHorizontalAlignment(SwingConstants.RIGHT);;
		ButtonGroup bg = new ButtonGroup();
		GridBagConstraints gbcEmail = new GridBagConstraints();
		bg.add(rb_PDF);
		bg.add(rb_SER);
		tf.setPreferredSize(new Dimension(90,25));
		tf2.setPreferredSize(new Dimension(90,25));
		jd_emailSenden.add(tf);
		
		jbEmail = new JButton();
		jbEmail.setIcon(new ImageIcon("Emailsenden.png"));
		//jbEmail.setText("Senden");
		//jbEmail.setPreferredSize(new Dimension(20,25));
		jbEmail.setBackground(Color.WHITE);
		

		JPanel jpEmail = new JPanel(new GridBagLayout());
		

		JLabel BackgroundImage = new JLabel(); 
		BackgroundImage.setIcon(new ImageIcon("Emailb.png"));
		BackgroundImage.setHorizontalAlignment(SwingConstants.CENTER);
		jpEmail.add(BackgroundImage,gbcEmail,0);
		JLabel jlEmail = new JLabel("An: ");
		jlEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		
		jlEmail.setOpaque(true);
		jlEmail.setBackground(Color.WHITE);
		jlEmail.setForeground(Color.BLACK);
		
		jpEmail.setLayout(new GridLayout(5,2,2,2));
		jpEmail.add(jlEmail,gbcEmail,0);
		jpEmail.add(tf,gbcEmail,1);	
		jpEmail.add(jlEmailFormat,gbcEmail,2);
		jpEmail.add(jlLeer,gbcEmail,3);
		jpEmail.add(rb_PDF,gbcEmail,4);
		jpEmail.add(rb_SER,gbcEmail,5);	
		JLabel jl2Email = new JLabel("Dateiname: ");
		jl2Email.setOpaque(true);
		jl2Email.setForeground(Color.BLACK);
		jl2Email.setBackground(Color.WHITE);
		jl2Email.setHorizontalAlignment(SwingConstants.RIGHT);
		jpEmail.add(jl2Email,gbcEmail,6);
		jpEmail.add(tf2,gbcEmail,7);
		jpEmail.add(jlLeer2, 8);
		jpEmail.add(jbEmail,gbcEmail,9);
		jd_emailSenden.add(BackgroundImage);

		jd_emailSenden.add(jpEmail);
				
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
		jmi_email.addActionListener(eh);
		jbEmail.addActionListener(eh);
		jmi_laden.addActionListener(eh);
		jmi_speichern.addActionListener(eh);
		jmi_neuesSpiel.addActionListener(eh);
		jmi_schliessen.addActionListener(eh);
		hintergrundAendern.addActionListener(eh);
		SteineFarbeAendern.addActionListener(eh);
		spielBeschreibung.addActionListener(eh);
		vorLadenSpeichern_ja.addActionListener(eh);
		vorLadenSpeichern_nein.addActionListener(eh);
		speichern_ja.addActionListener(eh);
		speichern_nein.addActionListener(eh);
		verlassen_ja.addActionListener(eh);
		verlassen_nein.addActionListener(eh);
		schliessen_ja.addActionListener(eh);
		schliessen_nein.addActionListener(eh);

	}
	

	public Component getJPanel() {
		panel.add(leiste);
		return panel;
	}
}