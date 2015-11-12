
package frontend;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class Spielbrett {
	private final JPanel gui = new JPanel(new BorderLayout(3, 3));
	
	public JButton[][] Spielfelder = new JButton[12][12];
	private JPanel Spielbrett;
	EH_Spielbrett eh;
	
	private static final String spaltenbuchstaben = "ABCDEFGHIJKL";
	
	
	public Spielbrett(EH_Spielbrett eh){
		this.eh=eh;
		Spielbrett= new JPanel();
		Spielbrett.setLayout(new GridLayout(0,14));
		gui.add(Spielbrett);
		Spielbrett.setBorder(new LineBorder(Color.black));
		Insets buttonMargin = new Insets(1,1,1,1);
		
		for (int i = Spielfelder.length-1; i>=0; i--) {
		for (int j = 0; j < Spielfelder[i].length; j++) {
				JButton b=new JButton();
				b.setMargin(buttonMargin);
				
			if((i % 2 == j % 2)){
				b.setBackground(Color.black);
			}
			else{
			
				
				b.setBackground(Color.white);
			}
			b.setText(""+i+j);
			Spielfelder[j][i]=b;
			b.addActionListener(eh);
			}
		}
		Spielbrett.add(new JLabel(""));
		for (int i = 0; i < 12; i++) {
			Spielbrett.add(new JLabel(spaltenbuchstaben.substring(i,i+ 1),SwingConstants.CENTER));
		}
		Spielbrett.add(new JLabel(""));
		
		
		
		int c=12;
		for (int i = 11; i>=0; i--) {
			for (int j = 0; j < 12; j++) {
				
			
			
			if(j==0){
				Spielbrett.add(new JLabel(""+ (c), SwingConstants.CENTER));
			
	
			}
			
			
			Spielbrett.add(this.Spielfelder[j][i]);
if(j==11){
				
				
				Spielbrett.add(new JLabel(""+c, SwingConstants.CENTER));
			c--;
		}}
			}
		Spielbrett.add(new JLabel(""));
		for (int i = 0; i < 12; i++) {
			Spielbrett.add(new JLabel(spaltenbuchstaben.substring(i,i+1),SwingConstants.CENTER));
		}
		Spielbrett.add(new JLabel(""));
	
		
	}
	public Component getSpielbrett() {
		
		return Spielbrett;
	}
}
