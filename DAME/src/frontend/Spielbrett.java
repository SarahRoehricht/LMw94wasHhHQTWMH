
package frontend;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class Spielbrett {
	private final JPanel gui = new JPanel(new BorderLayout(3, 3));
	
	private JButton[][] Spielfelder = new JButton[12][12];
	private JPanel Spielbrett;
	
	private static final String spaltenbuchstaben = "ABCDEFGHIJKL";
	public Spielbrett(){
		
		Spielbrett= new JPanel();
		Spielbrett.setLayout(new GridLayout(0,14));
		gui.add(Spielbrett);
		Spielbrett.setBorder(new LineBorder(Color.black));
		Insets buttonMargin = new Insets(0,0,0,0);
		
		for (int i = 0; i < Spielfelder.length; i++) {
			for (int j = 0; j < Spielfelder[i].length; j++) {
				JButton b=new JButton();
				b.setMargin(buttonMargin);
				
			if((j%2==1&& i%2==1)||(j%2==0 && i%2==0)){
				b.setBackground(Color.white);
			}
			else{
				if(i>6){
					ImageIcon icon= new ImageIcon("Weiss.png");
					b.setIcon(icon);
				}
				if(i<5){
					ImageIcon icon= new ImageIcon("schwarzkeks.gif");
					b.setIcon(icon);
				}
				
				b.setBackground(Color.black);
			}
			Spielfelder[j][i]=b;
			}
		}
		Spielbrett.add(new JLabel(""));
		for (int i = 0; i < 12; i++) {
			Spielbrett.add(new JLabel(spaltenbuchstaben.substring(i,i+ 1),SwingConstants.CENTER));
		}
		Spielbrett.add(new JLabel(""));
		int c=12;
		for (int i = 0; i < 12; i++) {
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
