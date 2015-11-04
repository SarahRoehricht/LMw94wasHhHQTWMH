package backend;

import java.io.IOException;
import java.util.Properties;

/**
 * 
 * @author A-2
 *
 */
public interface iDatenzugriff {
	
	public void speichern(Object obj, String name) throws IOException;
	
	public Object laden(String name) throws IOException;
	
	public void close() throws IOException;
}
