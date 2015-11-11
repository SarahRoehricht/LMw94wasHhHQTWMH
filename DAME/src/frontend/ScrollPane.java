package frontend;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ScrollPane {
	
	private JTextArea jta;

	public ScrollPane() {
		jta = new JTextArea();
		JPanel jp = new JPanel();
		jta.add(new JScrollBar());
		jp.add(new JScrollPane(jta));
		jta.setEditable(false);
		jta.setText("Log:");
	}
	
	public void addToTextArea(String s){
		this.getTextArea().setText(getTextArea().getText().concat("\n" + s));
	}
	
	public JTextArea getTextArea() {
		return jta;
	}
	
}
