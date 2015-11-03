package backend;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

/**
 * 
 * @author A-2
 *
 */
public class DatenzugriffSerialisiert implements iDatenzugriff , Serializable{


	ObjectInputStream is;
	ObjectOutputStream os;

	@Override
	public void writeObject(Object obj,String filename) {
		try {
			os = new ObjectOutputStream(new FileOutputStream(filename + ".ser"));
			if(os == null) {
				throw new IOException("OuputStream nicht geÃ¶ffnet.");
			} else {
				os.writeObject(obj);
				os.close();
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println("Serialisierung fehlgeschlagen!");
			System.out.println(e.getMessage());
		}
		System.out.println("Spielstand wurde gespeichert!");
	}

	@Override
	public Object readObject(String name) {
		try {
			is = new ObjectInputStream(new FileInputStream(name +".ser"));
			Object obj = is.readObject();
			is.close();
			return obj;
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return null;

	}

	@Override
	public void close() throws IOException {
		if (is != null) {
			is.close();
			is = null;
		} else if (os != null) {
			os.close();
			os = null;
		}

	}


}
