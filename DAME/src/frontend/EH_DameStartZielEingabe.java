package frontend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

public class EH_DameStartZielEingabe implements ActionListener{
	DameStartZielEingabe DEF = null;
	public EH_DameStartZielEingabe(DameStartZielEingabe lol) {
		this.DEF = lol;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String regel = "[a-lA-L]{1}[0-9]{1}[0-2]{0,1}-{1}[a-lA-L]{1}[0-9]{1}[0-2]{0,1}";
		if (e.getSource() == DEF.jTF_Start_Ziel ) {
			
			if (Pattern.matches(regel, DEF.jTF_Start_Ziel.getText())) {
				String[] sz = new String[2];
				sz = DEF.jTF_Start_Ziel.getText().split("-", 2);
				System.out.println(sz[0]+","+sz[1]);
				System.out.println(DEF.spiel.getSpieler()[1]);
				if ((DEF.spiel.doTheMove(DEF.spiel.getSpieler()[0], DEF.spiel.EingabeSpielfeld(sz[0]),
						DEF.spiel.EingabeSpielfeld(sz[1])) == false)) {
					System.out.println("Falscher Zug bzw Zug nicht möglich erneute eingabe");

				}
			} else {
				System.out.println("Falsche eingabe neue eingabe");// an unteres
																	// Textfeld
																	// weiterleiten
				// eingabe erneut starten...
			}
		}
	}

	
	
	
	
}
