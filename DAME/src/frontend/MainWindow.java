package frontend;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import backend.Spiel;

public class MainWindow {
	private MenuLeiste ml;
	private JPanel panel;
	private JPanel panelFuerLeiste;
	private JPanel panelWest;
	private JLabel labelWestSpielerName;
	private JFrame jf;
	private Rectangle bounds;
	private Spielbrett spielbrett;
	private ScrollPane sp;
	private DameStartZielEingabe dsze;
	GraphicsEnvironment env;

	public MainWindow(String title) {
		EH_Spielbrett eh = new EH_Spielbrett();
		panel = new JPanel(new BorderLayout());
		ml = new MenuLeiste();
		spielbrett = new Spielbrett(eh);
		sp = new ScrollPane();
		dsze = new DameStartZielEingabe();
		panelWest = new JPanel(new BorderLayout());
		labelWestSpielerName = new JLabel();
		labelWestSpielerName.setHorizontalAlignment(JLabel.RIGHT);
		labelWestSpielerName.setVerticalAlignment(JLabel.BOTTOM);
		panelWest.add(labelWestSpielerName, BorderLayout.SOUTH);
		panelFuerLeiste = new JPanel(new BorderLayout());
		panelFuerLeiste.add(ml.getJPanel(), BorderLayout.WEST);
		env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		bounds = env.getMaximumWindowBounds();
		panel.add(panelFuerLeiste, BorderLayout.NORTH);
		panel.add(spielbrett.getSpielbrett(), BorderLayout.CENTER);
		panel.add(sp.getJPanel(), BorderLayout.SOUTH);
		panel.add(dsze.getJPanel(), BorderLayout.EAST);
		panel.add(labelWestSpielerName, BorderLayout.WEST);
		Spiel spiel = new Spiel("Harald", false, "Johannes", true);
		eh.setSpiel(spiel, spielbrett);
		eh.setScrollPane(sp);
		jf = new JFrame(title);

		jf.setContentPane(panel);
		jf.setSize(bounds.width, bounds.height);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);

	}

}
