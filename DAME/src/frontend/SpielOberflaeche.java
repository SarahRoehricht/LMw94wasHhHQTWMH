package frontend;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class SpielOberflaeche extends JFrame implements ActionListener{

	GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
	Rectangle bounds = env.getMaximumWindowBounds();

	JFrame jf;
	JMenuBar leiste;
	Container c;
	
	JPanel jp_laden;
	JPanel jp_neuesSpiel;
	JPanel jp_schliessen;
	
	
	//soll aktueller Spielstand gespeichert werden, bevor Spiel geladen
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

	public SpielOberflaeche() {

		jf = new JFrame("Dame International");
		c = jf.getContentPane();

		jp_laden = new JPanel();
		jp_neuesSpiel = new JPanel();
		jp_schliessen = new JPanel();
		
		
		
		jl_laden = new JLabel("Soll der aktuelle Spielstand gespeichert werden?");
		jl_laden.setHorizontalAlignment(JLabel.CENTER);
		
		jl_schliessen = new JLabel("Möchten Sie das Spiel wirklich verlassen?");
		jl_schliessen.setHorizontalAlignment(JLabel.CENTER);
		
		//Leiste mit Unterpunkten
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
		
		//JButtons
		laden_ja = new JButton("ja");
		laden_nein = new JButton("nein");
		speichern_ja = new JButton("ja");
		speichern_nein = new JButton("nein");
		verlassen_ja = new  JButton("ja");
		verlassen_nein = new JButton("nein");
		schliessen_ja = new  JButton("ja");
		schliessen_nein = new JButton("nein");
		
		laden_ja.addActionListener(this);
		laden_nein.addActionListener(this);
		speichern_ja.addActionListener(this);
		speichern_nein.addActionListener(this);
		verlassen_ja.addActionListener(this);
		verlassen_nein.addActionListener(this);
		schliessen_ja.addActionListener(this);
		schliessen_nein.addActionListener(this);
		
		laden_ja.setPreferredSize(new Dimension(100,20));
		laden_nein.setPreferredSize(new Dimension(100,20));
		speichern_ja.setPreferredSize(new Dimension(100,20));
		speichern_nein.setPreferredSize(new Dimension(100,20));
		schliessen_ja.setPreferredSize(new Dimension(100,20));
		schliessen_nein.setPreferredSize(new Dimension(100,20));
		
		jp_laden.add(laden_ja);
		jp_laden.add(laden_nein);
		
		jp_schliessen.add(schliessen_ja);
		jp_schliessen.add(schliessen_nein);
		
		//Dialogfenster
		jd_laden = new JDialog();
		jd_laden.setTitle("Sicherung des Spielstands");
		jd_laden.setSize(400, 200);
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
		

		jf.setSize(bounds.width, bounds.height);
		jf.add(leiste, BorderLayout.NORTH);
		jf.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent o) {
		if (o.getSource() == jmi_laden) {
			jd_laden.setVisible(true);
		}

		if (o.getSource() == jmi_speichern) {

		}

		if (o.getSource() == jmi_neuesSpiel) {

		}

		if (o.getSource() == jmi_schliessen) {
			jd_schliessen.setVisible(true);
			if(o.getSource() == schliessen_ja){
				schliessen_ja.addActionListener(this);
				jd_schliessen.dispose();
				jf.dispose();	
			}
		}

		if (o.getSource() == hintergrundAendern) {

		}

		if (o.getSource() == SteineFarbeAendern) {

		}

		if (o.getSource() == spielBeschreibung) {

		}
	}

	public static void main(String[] args) {
		SpielOberflaeche ml = new SpielOberflaeche();
	}
}