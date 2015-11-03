package backend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Properties;

/**
 * 
 * @author A-2
 *
 */
public class DatenzugriffCSV implements iDatenzugriff, Serializable  {

	private BufferedReader br;
	private BufferedWriter bw;

	/**
	 * 
	 * @param obj
	 *            -
	 * @throws IOException 
	 */
	@Override
	public void writeObject(Object obj, String name) throws IOException {
		String str = (String) obj;
		bw = new BufferedWriter(new FileWriter(name));
		try {
			bw.write(str + ";");
			bw.close();

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Spielstand wurde gespeichert!");
	}

	/**
	 * @return ausgelesenes Objekt wird zurÃ¼ckgegeben
	 */
	@Override
	public Object readObject(String name) {
		try {
			String line;
			ArrayList<String> feld = new ArrayList<String>();
			// hier wird die .csv Datei ganz eingelesen und in feld Ã¼bergeben
			while ((line = br.readLine()) != null) {
				feld.add(line);
			}
			return feld;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public void close() throws IOException {
		if (bw != null) {
			bw.close();
			bw = null;
		}
		if (br != null) {
			br.close();
			br = null;
		}
	}
}
