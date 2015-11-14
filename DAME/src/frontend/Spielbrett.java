
package frontend;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Dialog.ModalityType;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class Spielbrett {
	private final JPanel gui = new JPanel(new BorderLayout(3, 3));
	
	public JButton[][] Spielfelder = new JButton[12][12];
	private JPanel Spielbrett;
	EH_Spielbrett eh;
	JDialog pustDialog= new JDialog();
	JPanel pustDialogpanel= new JPanel(new BorderLayout());
	JPanel pustDialogTextPanel= new JPanel(new BorderLayout());
	JLabel pustDialogText1= new JLabel("", SwingConstants.CENTER);
	JLabel pustDialogText2 =  new JLabel("", SwingConstants.CENTER);
JPanel	pustDialogpanelSouth = new JPanel();
	JButton pustDialogButton1= new JButton();
	JButton pustDialogButton2= new JButton();
	private Color schwarzFeldColor= new Color(156, 93, 82);
	private static final String spaltenbuchstaben = "ABCDEFGHIJKL";
	
	
	public Spielbrett(EH_Spielbrett eh){
		this.eh=eh;
		Spielbrett= new JPanel();
		Spielbrett.setLayout(new GridLayout(0,14));
		gui.add(Spielbrett);
		pustDialog.setTitle("Pusten!");
	 pustDialogButton1.addActionListener(eh);
	 pustDialogButton2.addActionListener(eh);
	 pustDialogTextPanel.add(pustDialogText1, BorderLayout.NORTH);
		pustDialogTextPanel.add(pustDialogText2, BorderLayout.CENTER);
		pustDialogpanelSouth.add(pustDialogButton1);
		pustDialogpanelSouth.add(pustDialogButton2);
		pustDialogpanel.add(pustDialogpanelSouth, BorderLayout.SOUTH);
		pustDialogpanel.add(pustDialogTextPanel, BorderLayout.CENTER);
		pustDialog.add(pustDialogpanel);
		
		pustDialog.setModalityType(ModalityType.APPLICATION_MODAL);
		pustDialog.setSize(500,200);
		pustDialog.setMinimumSize(pustDialog.getPreferredSize());
		pustDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		pustDialog.setLocationRelativeTo(null);
		
		
		
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
