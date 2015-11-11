package frontend;

import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ScrollPane {
	private JPanel jp;
	private JTextArea jta;

	public ScrollPane() {
		jta = new JTextArea();
		jp = new JPanel();
		jta.add(new JScrollBar());
		
		jta.setEditable(false);
		jta.setText("Log:");
		jp.add(new JScrollPane(jta));
	}
	
	public void addToTextArea(String s){
		this.getTextArea().setText(getTextArea().getText().concat("\n" + s));
	}
	
	public JTextArea getTextArea() {
		return jta;
	}

	public Component getJPanel() {
		return jp;
	}
	
}
