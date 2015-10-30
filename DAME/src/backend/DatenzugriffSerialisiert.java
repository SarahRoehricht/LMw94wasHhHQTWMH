package backend;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.util.Properties;

public class DatenzugriffSerialisiert implements iDatenzugriff {

	ObjectInputStream is;
	ObjectOutputStream os;

	@Override
	public void open(Properties prop) throws IOException {
		String filename = prop.getProperty("modus");
		if(filename == null){
			throw new IOException("Datei nicht gefunden!");
		}
		else{
			if("s".equals(prop.getProperty("modus"))){
				os = new ObjectOutputStream(new FileOutputStream(filename));
			}
			else if("l".equals(prop.getProperty("modus"))){
				is = new ObjectInputStream(new FileInputStream(filename));
			}
			else{
				throw new IOException("modus nicht vorhanden");
			}
		}
	}
	
	@Override
	public void writeObject(Object obj) {
		try {
			os = new ObjectOutputStream(new FileOutputStream("data.bin"));
			os.writeObject(obj);
			os.close();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println("Serialisierung fehlgeschlagen!");
			System.out.println(e.getMessage());
		}
		System.out.println("Spielstand wurde gespeichert!");	
	}
	
	@Override
	public Object readObject() {
		
		try {
			is = new ObjectInputStream(new FileInputStream("data.bin"));
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
		if(is != null){
			is.close();
			is = null;
		}else if(os != null){
			os.close();
			os =null;
		}
		
	}

}
