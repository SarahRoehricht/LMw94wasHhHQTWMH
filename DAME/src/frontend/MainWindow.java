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
	JLabel spielerWeissName;
	private JFrame jf;
	private Rectangle bounds;
	private Spielbrett spielbrett;
	private ScrollPane sp;
	private DameStartZielEingabe dsze;
	GraphicsEnvironment env;
	JFrame spielStartDialog;

	public MainWindow(String title) {
		EventHandler eh = new EventHandler(this);
		panel = new JPanel(new BorderLayout());
		ml = new MenuLeiste(eh);
		spielbrett = new Spielbrett(eh);
		sp = new ScrollPane();
		dsze = new DameStartZielEingabe(eh);
		panelWest = new JPanel(new BorderLayout());
		spielerWeissName = new JLabel();
		spielerWeissName.setHorizontalAlignment(JLabel.RIGHT);
		spielerWeissName.setVerticalAlignment(JLabel.BOTTOM);
		panelWest.add(spielerWeissName, BorderLayout.SOUTH);
		panelFuerLeiste = new JPanel(new BorderLayout());
		panelFuerLeiste.add(ml.getJPanel(), BorderLayout.WEST);
		env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		bounds = env.getMaximumWindowBounds();
		panel.add(panelFuerLeiste, BorderLayout.NORTH);
		panel.add(spielbrett.getSpielbrett(), BorderLayout.CENTER);
		panel.add(sp.getJPanel(), BorderLayout.SOUTH);
		panel.add(dsze.getJPanel(), BorderLayout.EAST);
		panel.add(spielerWeissName, BorderLayout.WEST);

		jf = new JFrame(title);

		jf.setContentPane(panel);
		jf.setSize(bounds.width, bounds.height);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);

		spielStartDialog = ml.jf;

		eh.setSpielbrett(spielbrett);
		eh.setScrollPane(sp);
		eh.setMenu(ml);
		eh.setDEF(dsze);
		spielStartDialog.setVisible(true);
	}

}
