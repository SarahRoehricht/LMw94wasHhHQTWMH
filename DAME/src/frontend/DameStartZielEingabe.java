package frontend;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import backend.Spiel;
//
//import frontend.SpielOberflaeche.StartZielHandler;

public class DameStartZielEingabe implements ActionListener {
	Spiel spiel = new Spiel();

	@Override
	public void actionPerformed(ActionEvent e) {
//		if(e.getSource()!= null)
		String regel = "[a-lA-L]{1}[0-9]{1}[0-2]{0,1}-{1}[a-lA-L]{1}[0-9]{1}[0-2]{0,1}";
		
		if (Pattern.matches(jTF_Start_Ziel.getText(), regel)){
			String[] sz = new String[2];
			sz = jTF_Start_Ziel.getText().split("-", 2);
			//weitergeben an Backend..?.
		}
				
				
			}

	Container c;
	JTextField jTF_Start_Ziel;
	DameStartZielEingabe Texthandler;
	String Startziel = "";

	public void JTFStartZiel() {
		jTF_Start_Ziel = new JTextField(5);
		c.add(jTF_Start_Ziel, BorderLayout.EAST);
		// jTF_Start_Ziel.add(, BorderLayout.EAST);
		Texthandler = new DameStartZielEingabe();
		jTF_Start_Ziel.addActionListener(Texthandler);
		jTF_Start_Ziel.setSize(300, 100);
		jTF_Start_Ziel.setVisible(true);

	}

	public static void main(String[] args) {
		DameStartZielEingabe DEF = new DameStartZielEingabe();

	}

}
