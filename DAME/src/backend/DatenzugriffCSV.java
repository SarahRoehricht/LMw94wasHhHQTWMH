package backend;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class DatenzugriffCSV implements iDatenzugriff {


/**
 * 
 * @param str
 */
	public static void speichernCSV(String str) {

		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("spielstand.txt"));
			bw.write(str);
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
		// TODO Auto-generated method stub
		
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
