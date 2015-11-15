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

public class DameStartZielEingabe implements FocusListener{

	Spiel spiel;
	EH_DameStartZielEingabe eh = null;

	Container c;
	JTextField jTF_Start_Ziel;
	DameStartZielEingabe Texthandler;
	String Startziel = "";
	JPanel jp;
	JFrame jf;
	JButton jb;
	JLabel jl;
	Font font;

	public DameStartZielEingabe() {
		JTFStartZiel();
		spiel = new Spiel("Jonas", false, "Peter", false);
		// this.jTF_Start_Ziel = jTF_Start_Ziel;
		
		font = new Font("ARIAL", Font.BOLD, 14);
		jTF_Start_Ziel.setFont(font);
	}

	public void JTFStartZiel() {
		String s = "z.B. a5-b6";
		jf = new JFrame();
		jp = new JPanel();
		jf.add(jp, BorderLayout.EAST);
		jTF_Start_Ziel = new JTextField(7);
		jTF_Start_Ziel.setText(s);
		jTF_Start_Ziel.addFocusListener(new FocusListener() {

			public void focusLost(FocusEvent e) {
				if (jTF_Start_Ziel.getText().equals("")) {
					jTF_Start_Ziel.setText(s);
				}

			}

			public void focusGained(FocusEvent e) {
				jTF_Start_Ziel.setText("");

			}
		});
		jb = new JButton("los");
		jl = new JLabel("Zugeingabe: ");
		jp.add(jl);
		jp.add(jTF_Start_Ziel);
		jp.add(jb);
		jf.add(jp);
		jb.setForeground(Color.BLUE);
		ImageIcon Icon = new ImageIcon("check.png");
		ImageIcon Icon2 = new ImageIcon("error.png");
		jTF_Start_Ziel.setPreferredSize(new Dimension(90, 25));
		jTF_Start_Ziel.setForeground(Color.LIGHT_GRAY);
		jTF_Start_Ziel.setVisible(true);
		jb.setPreferredSize(new Dimension(70, 25));
		jb.setVisible(true);
		jl.setSize(100, 50);
		jl.setVisible(true);
		jTF_Start_Ziel.addActionListener(eh);
		jb.addActionListener(eh);

	}

	public Component getJPanel() {
		return jp;
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

}