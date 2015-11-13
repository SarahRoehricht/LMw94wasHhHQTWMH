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

		jp = new JPanel();	
		jta = new JTextArea(10 ,150);
		jta.setEditable(false);	
		jta.add(new JScrollBar());
		jp.add( new JScrollPane(jta));
		
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
