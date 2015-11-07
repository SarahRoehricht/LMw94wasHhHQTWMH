package frontend;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DameBrett extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {

		// http://stackoverflow.com/questions/2712414/how-to-create-an-array-of-jlabels-in-java-to-be-printed-on-a-jframe

		JFrame frame = new JFrame("Damebrett");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 600);
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(14, 14, 0, 0));

		//grün zum Überprüfen verwendet ==> besser sichtbar als weiß ;-)
		// Das Layout wird erst schtbar, wenn die Fenstergröße manuell angepasst wird ==> ???
		//Nochmal dransitzen... 
		JLabel[] labels = new JLabel[196];
		for (int i = 0; i < labels.length; i++) {
			labels[i] = new JLabel();
			labels[i].setOpaque(true);
			panel.add(labels[i]);
			//Noch Methoden für effizenteren Code schreiben
			if (i > 14 && i <= 26) {
				if (i % 2 == 1) {
					labels[i].setBackground(Color.green);
				} else {
					labels[i].setBackground(Color.black);
				}
			}
			if (i > 28 && i <= 40) {
				if (i % 2 == 1) {
					labels[i].setBackground(Color.black);
				} else {
					labels[i].setBackground(Color.green);
				}
			}
			if (i > 42 && i <= 54) {
				if (i % 2 == 1) {
					labels[i].setBackground(Color.green);
				} else {
					labels[i].setBackground(Color.black);
				}
			}
			if (i > 56 && i <= 68) {
				if (i % 2 == 1) {
					labels[i].setBackground(Color.black);
				} else {
					labels[i].setBackground(Color.green);
				}
			}
			if (i > 70 && i <= 82) {
				if (i % 2 == 1) {
					labels[i].setBackground(Color.green);
				} else {
					labels[i].setBackground(Color.black);
				}
			}
			if (i > 84 && i <= 96) {
				if (i % 2 == 1) {
					labels[i].setBackground(Color.black);
				} else {
					labels[i].setBackground(Color.green);
				}
			}
			if (i > 98 && i <= 110) {
				if (i % 2 == 1) {
					labels[i].setBackground(Color.green);
				} else {
					labels[i].setBackground(Color.black);
				}
			}
			if (i > 112 && i <= 124) {
				if (i % 2 == 1) {
					labels[i].setBackground(Color.black);
				} else {
					labels[i].setBackground(Color.green);
				}
			}
			if (i > 126 && i <= 138) {
				if (i % 2 == 1) {
					labels[i].setBackground(Color.green);
				} else {
					labels[i].setBackground(Color.black);
				}
			}
			if (i > 140 && i <= 152) {
				if (i % 2 == 1) {
					labels[i].setBackground(Color.black);
				} else {
					labels[i].setBackground(Color.green);
				}
			}
			if (i > 154 && i <= 166) {
				if (i % 2 == 1) {
					labels[i].setBackground(Color.green);
				} else {
					labels[i].setBackground(Color.black);
				}
			}
			if (i > 168 && i <= 180) {
				if (i % 2 == 1) {
					labels[i].setBackground(Color.black);
				} else {
					labels[i].setBackground(Color.green);
				}
			}
		}

		panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		frame.add(panel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
