package backend;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * 
 * @author A-2
 *@version 0.0.2
 */
public class ZZSpielTest {

	private Spieler s1;
	private Spieler s2;
	private Spieler s3;
	private Object spiel;

	@Before
	public void vorTest(){
		s1= new Spieler("Harald", FarbEnum.schwarz);
		s2= new Spieler("Egon", FarbEnum.weiß);
	}
	
	// Testet gültige Spieleranzahl - 2 erlaubt
	@Test(expected=RuntimeException.class)
	public void testSpieleranzahl() {
		
		s3=new Spieler("Heinrich", FarbEnum.schwarz);
		spiel.add(s1);
		spiel.add(s2);
		spiel.add(s3);
	}
	
	

	// Testet Spieler auf unterschiedliche Farbe
	@Test
	public void testSpielerFarbe() {
		
			assertFalse(s1.getFarbe()==s2.getFarbe());
		
		}
		
	//Testet SpielBrett erstellung, notation ende
	@Test
	public void testSpielBrettEnde(){
		int i;
		i=12;
		
		spiel(i);
		String erg=SpielBrettarr[11][11];
		assertTrue(erg=="l12");
		}
//Testet SpielBrett erstellung, notation ende
	@Test
	public void testSpielBrettAnfang(){
		int i;
		i=12;
		
		Spiel(i);
		String erg=SpielBrettarr[0][0];
		assertTrue(erg=="a1");
		}
//Testet SpielBrett erstellung,Out of Bounds an Stelle 13 im Array
	@Test(expected=IndexOutOfBoundsException.class)
	public void testSpielBrettOutOfBounds(){
		int i;
		i=12;
		
		Spiel(i);
		String erg=SpielBrettarr[12][11];
		}
	//Testet Spielfigur initialisierung auf gueltigem Feld
	@Test
	public void testSpielfigurInit(){
		
		
		
	}
	
	}

