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
	private Spiel spiel;

	@Before
	public void vorTest(){
		s1= new Spieler("Harald", FarbEnum.schwarz);
		s2= new Spieler("Egon", FarbEnum.weiß);
		
	}
	
	// Testet gültige Spieleranzahl - 2 erlaubt
	@Test(expected=RuntimeException.class)
	public void testSpieleranzahl() {
		spiel= new Spiel();
		s3=new Spieler("Heinrich", FarbEnum.schwarz);
		spiel.add(s1);
		spiel.add(s2);
		spiel.add(s3);
	}
	
	
	//Testet Spieler add Funktion
	@Test
	public void testSpieleradd1(){
		spiel=new Spiel();
		spiel.add(s1);
		assertTrue(spiel.getSpielerBisher()==1);
	}
	//Testet Spieler add Funktion
	@Test
	public void testSpieleradd2(){
		spiel=new Spiel();
		spiel.add(s1);
		spiel.add(s2);
		assertTrue(spiel.getSpielerBisher()==2);
	}
	// Testet Spieler auf unterschiedliche Farbe
	@Test
	public void testSpielerFarbe() {
		spiel= new Spiel();
		spiel.add(s1);
		spiel.add(s2);
			assertTrue(s1.getFarbe()!=s2.getFarbe());
		
		}
		
	//Testet SpielBrett erstellung, notation ende
	@Test
	public void testSpielBrettEnde(){
		int i;
		i=12;
		
		spiel= new Spiel(i);
		String erg=""; //!!!Ueberlegen wie man dies umsetzen kann.!!!
		assertTrue(erg=="l12");
		}


	//Testet SpielBrett erstellung, notation ende
	@Test
	public void testSpielBrettAnfang(){
		int i;
		i=12;
		
		spiel= new Spiel(i);
		String erg=SpielBrettarr[0][0];
		assertTrue(erg=="a1");
		}
//Testet SpielBrett erstellung,Out of Bounds an Stelle 13 im Array
	@Test(expected=IndexOutOfBoundsException.class)
	public void testSpielBrettOutOfBounds(){
		int i;
		i=12;
		
		spiel= new Spiel(i);
		String erg=SpielBrettarr[12][11];
		}
	//Testet Spielfigur initialisierung auf gueltigem Feld
	@Test
	public void testSpielfigurInit(){
		
		
		
	}
	//Move Befehl testen
	@Test
	public void testMove1(){
		int i =12;
		spiel=new Spiel(i);
		Spielfeld steinpos=new Spielfeld("b1");
		Spielfigur stein1= new Spielfigur(FarbEnum.schwarz, steinpos);
		Spielfeld posxy= new Spielfeld("a2");
		spiel.move(s1, stein1, posxy);
		assertTrue(stein1.getPosition()==posxy);
		
	}
	
	}

