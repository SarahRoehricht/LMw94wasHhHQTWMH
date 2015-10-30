package backend;

import java.io.IOException;
import java.util.Properties;

public interface iDatenzugriff {

	public void open(Properties prop) throws IOException;
	
	public void writeObject(Object obj) throws IOException;
	
	public Object readObject() throws IOException;
	
	public void close() throws IOException;
}
