package frontend;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import backend.Spiel;

public class DameStartZielEingabe {

	EventHandler eh;

	Container c;
	JTextField jTF_Start_Ziel;
	DameStartZielEingabe Texthandler;
	String Startziel = "";
	JPanel jp;
	JPanel jpborderl = new JPanel(new BorderLayout());
	JButton jb;
	JLabel jl;
	Font font;
	JLabel spielerSchwarzName = new JLabel();

	public DameStartZielEingabe(EventHandler eh) {
		this.eh = eh;
		JTFStartZiel();
		// this.jTF_Start_Ziel = jTF_Start_Ziel;

		font = new Font("ARIAL", Font.BOLD, 14);
		jTF_Start_Ziel.setFont(font);
	}

	public void JTFStartZiel() {
		String s = "z.B. a5-b6";
		jpborderl.add(spielerSchwarzName, BorderLayout.NORTH);
		jp = new JPanel();
		jpborderl.add(jp, BorderLayout.SOUTH);
		jTF_Start_Ziel = new JTextField(7);
		jTF_Start_Ziel.setText(s);
		jTF_Start_Ziel.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				if (jTF_Start_Ziel.getText().equals("")) {
					jTF_Start_Ziel.setText(s);
				}

			}

			@Override
			public void focusGained(FocusEvent e) {
				jTF_Start_Ziel.setText("");

			}
		});
		jb = new JButton("los");
		jp.add(jTF_Start_Ziel);
		jp.add(jb);

		jb.setForeground(Color.BLUE);
		ImageIcon Icon = new ImageIcon("check.png");
		ImageIcon Icon2 = new ImageIcon("error.png");
		jTF_Start_Ziel.setPreferredSize(new Dimension(90, 25));
		jTF_Start_Ziel.setForeground(Color.LIGHT_GRAY);
		jTF_Start_Ziel.setVisible(true);
		jb.setPreferredSize(new Dimension(70, 25));
		jb.setVisible(true);

		jTF_Start_Ziel.addActionListener(eh);
		jb.addActionListener(eh);

	}

	public Component getJPanel() {
		return jpborderl;
	}

}