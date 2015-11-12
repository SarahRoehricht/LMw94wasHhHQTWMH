package frontend;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	JButton jb;
	JLabel jl;

	public void JTFStartZiel() {
		jf = new JFrame();
		jp = new JPanel();
		jTF_Start_Ziel = new JTextField(7);
		jb = new JButton();
		jl = new JLabel();
		jf.add(jp);
		jp.add(jTF_Start_Ziel);
		jp.add(jb);
		jp.add(jl);
//		jf.setResizable(false);
		jb.setForeground(Color.BLUE);
			ImageIcon Icon = new ImageIcon("check.png");
			ImageIcon Icon2 = new ImageIcon("error.png");
			
			//jb.setIcon(Icon);
			
//			jb.setSize(50,50);
		
		// jTF_Start_Ziel.getText();

		// c.add(jTF_Start_Ziel, BorderLayout.EAST);

		// jTF_Start_Ziel.add(, BorderLayout.EAST);
		// Texthandler = new NeueTestOberfläche();
		// jTF_Start_Ziel.addActionListener((ActionListener) Texthandler);
		jTF_Start_Ziel.setSize(300, 100);
		jTF_Start_Ziel.setVisible(true);
		jTF_Start_Ziel.setSize(300, 100);
		jTF_Start_Ziel.setVisible(true);
//		jp.setVisible(true);
//		jf.setSize(1920, 1080);
//		jf.setVisible(true);
		jb.setSize(100,50);
		jb.setVisible(true);
		jl.setSize(100,50);;
		jl.setVisible(true);
		jTF_Start_Ziel.addActionListener(new EH_DameStartZielEingabe(this));
		jb.addActionListener(new EH_DameStartZielEingabe(this));
		
//		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

//	public static void main(String[] args) {
//		DameStartZielEingabe DEF = new DameStartZielEingabe();
//
//	}

	
	
public Component getJPanel(){
	return jp;
}
	

}
