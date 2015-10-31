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
import java.util.ArrayList;
import java.util.Properties;

/**
 * 
 * @author A-2
 *
 */
public class DatenzugriffCSV implements iDatenzugriff {

	private BufferedReader br;
	private BufferedWriter bw;

	/**
	 * 
	 * @param obj
	 *          -
	 */
	@Override
	public void writeObject(Object obj) {
		String str = (String) obj;
		try {
			bw.write(str + ";");
			bw.close();

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * @return ausgelesenes Objekt wird zurückgegeben
	 */
	@Override
	public Object readObject() {
		try {
			String line;
			ArrayList<String> feld = new ArrayList<String>();
			// hier wird die .txt Datei ganz eingelesen und in feld übergeben
			while ((line = br.readLine()) != null) {
				feld.add(line);
				String[] klasse;
				// feld splitten bei ";" um die einzelnen Klassen zu erhalten
				for (String s : feld) {
					klasse = line.split(";");
					// Klasse splitten bei ":" um Name der Klasse von Attributen zu
					// trennen
					for (int i = 0; i < klasse.length; i++) {
						klasse[i].split(":");
						// Switch Case für Klassennamen

						// Attribute müssen noch gesplittet und dann zugeordnet werden
						// entsprechende Objekte müssen noch zurückgegeben werden
					}
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void open(Properties prop) throws IOException {
		bw = new BufferedWriter(new FileWriter("spielstand.txt"));
		String filename = prop.getProperty("modus");
		if (filename == null) {
			throw new IOException("Datei nicht gefunden!");
		} else {
			if ("s".equals(prop.getProperty("modus"))) {
				bw = new BufferedWriter(new FileWriter(filename));
				// Alfreds Code zur CSV - nochmal checken welche Kombi besser für unsere
				// Zwecke ist
				// bw = new BufferedWriter(new OutputStreamWriter(new
				// FileOutputStream(fileName)));
			} else if ("l".equals(prop.getProperty("modus"))) {
				br = new BufferedReader(new FileReader(filename));
			} else {
				throw new IOException("modus nicht vorhanden");
			}
		}
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
