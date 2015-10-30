package backend;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class DatenzugriffCSV implements iDatenzugriff {

	public static void main(String[] args) {

	}
/**
 * 
 * @param str
 */
	public static void speichernCSV(String str) {

		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("spielstand.txt"));
			bw.write(str + "\n");
			bw.close();

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	public static void ladenCSV() {
		
	}
	@Override
	public void open(Properties prop) throws IOException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void writeObject(Object obj) {
		// Wenn @Override, dann in Serialisiert und in CSV write. Keine Differenzierung anhand des Namens. 
		
	}
	@Override
	public Object readObject() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		
	}
	
}
