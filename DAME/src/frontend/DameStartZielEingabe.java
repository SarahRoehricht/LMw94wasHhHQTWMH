package frontend;

import java.awt.Component;
import java.awt.Container;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import backend.Spiel;

public class DameStartZielEingabe {

	Spiel spiel;

	public DameStartZielEingabe() {// JTextField jTF_Start_Ziel
		JTFStartZiel();
		spiel = new Spiel("Jonas", false, "Peter", false);
		// this.jTF_Start_Ziel = jTF_Start_Ziel;
		Font font;
		font = new Font("ARIAL",Font.BOLD,14);
		jTF_Start_Ziel.setFont(font);
	}

	Container c;
	JTextField jTF_Start_Ziel;
	DameStartZielEingabe Texthandler;
	String Startziel = "";
	JPanel jp;
	JFrame jf;

	public void JTFStartZiel() {
		
		jp = new JPanel();
		jTF_Start_Ziel = new JTextField(7);
		
		jp.add(jTF_Start_Ziel);

		// jTF_Start_Ziel.getText();

		// c.add(jTF_Start_Ziel, BorderLayout.EAST);

		// jTF_Start_Ziel.add(, BorderLayout.EAST);
		// Texthandler = new NeueTestOberfläche();
		// jTF_Start_Ziel.addActionListener((ActionListener) Texthandler);
		jTF_Start_Ziel.setSize(300, 100);
		jTF_Start_Ziel.setVisible(true);
//		jp.setVisible(true);
//		jf.setSize(1920, 1080);
//		jf.setVisible(true);
		jTF_Start_Ziel.addActionListener(new EH_DameStartZielEingabe(this));
//		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
public Component getJPanel(){
	return jp;
}
	

}
