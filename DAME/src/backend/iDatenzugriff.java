package backend;

import java.io.IOException;
import java.util.Properties;

/**
 * 
 * @author A-2
 *
 */
public interface iDatenzugriff {

	public void open(Properties prop) throws IOException;
	
	public void writeObject(Object obj) throws IOException;
	
	public Object readObject() throws IOException;
	
	public void close() throws IOException;
}
