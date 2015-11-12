package frontend;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;

public class EH_DameStartZielEingabe implements ActionListener {
	DameStartZielEingabe DEF = null;

	public EH_DameStartZielEingabe(DameStartZielEingabe lol) {
		this.DEF = lol;

	}

	ScrollPane sp;
	ImageIcon Icon = new ImageIcon("check.png");
	ImageIcon Icon2 = new ImageIcon("error.png");

	@Override
	public void actionPerformed(ActionEvent e) {
		String regel = "[a-lA-L]{1}[0-9]{1}[0-2]{0,1}-{1}[a-lA-L]{1}[0-9]{1}[0-2]{0,1}";
		String ausgabe;
		Font font;
		font = new Font("ARIAL", Font.BOLD, 14);
		DEF.jl.setText("");
		if (e.getSource() == DEF.jTF_Start_Ziel || e.getSource() == DEF.jb) {
			DEF.jTF_Start_Ziel.setForeground(Color.black);
			DEF.jTF_Start_Ziel.setFont(font);
			DEF.jTF_Start_Ziel.setBackground(Color.white);
			if (Pattern.matches(regel, DEF.jTF_Start_Ziel.getText())) {
				String[] sz = new String[2];
				sz = DEF.jTF_Start_Ziel.getText().split("-", 2);
				
				if ((DEF.spiel.doTheMove(DEF.spiel.getActiveSpieler(), DEF.spiel.EingabeSpielfeld(sz[0]), DEF.spiel.EingabeSpielfeld(sz[1])) == false)) {
					// System.out.println("Falscher Zug bzw Zug nicht möglich erneute eingabe");
					ausgabe = DEF.jTF_Start_Ziel.getText()+"ist kein gültiger Zug";
					// sp.addToTextArea(ausgabe);
					DEF.jl.setForeground(Color.red);
					DEF.jl.setText(DEF.jTF_Start_Ziel.getText()+" ist kein gültiger Zug");
					DEF.jTF_Start_Ziel.setFont(font);
					DEF.jTF_Start_Ziel.setBackground(Color.red);
					DEF.jTF_Start_Ziel.setForeground(Color.white);
				//	DEF.jb.setIcon(Icon2);

					// DEF.jb.setIcon(Icon);
				} else {
					DEF.jl.setForeground(Color.green);
					DEF.jl.setText(DEF.spiel.getActiveSpieler()+" hat von "+sz[0]+" nach "+sz[1]+ " gezogen");
					ausgabe = sz[0] + "-" + sz[1];
					// System.out.println(ausgabe);
					// sp.addToTextArea(ausgabe);
					DEF.jTF_Start_Ziel.setText("");
					//if((DEF.spiel.zugdone)==true)
					//DEF.jb.setIcon(Icon);
				}
			} else {

				ausgabe = "Eingabe Syntax nicht korrekt, Feld nicht auf Spielbrett";
				//DEF.jb.setIcon(Icon2);
				DEF.jl.setForeground(Color.red);
				DEF.jl.setText("Eingabe Syntax nicht korrekt, Feld nicht auf Spielbrett");
				DEF.jTF_Start_Ziel.setFont(font);
				DEF.jTF_Start_Ziel.setBackground(Color.red);
				DEF.jTF_Start_Ziel.setForeground(Color.white);

				// sp.addToTextArea(ausgabe);
				// System.out.println("Falsche eingabe neue eingabe");// an unteres
				// Textfeld
				// weiterleiten
				// eingabe erneut starten...
			}

		}
	}

}