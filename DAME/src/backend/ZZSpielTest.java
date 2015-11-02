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
	private Spielfeld[][] brett;
	private SpielBrett spielbrett;

	@Before
	public void vorTest(){
		s2= new Spieler("Harald", FarbEnum.schwarz);
		s1= new Spieler("Egon", FarbEnum.weiß);
		spielbrett= new SpielBrett();
		brett=spielbrett.getBrett();
		
	}
	@Test
	public void testFarbeFeld1(){
		
		FarbEnum farbe= FarbEnum.schwarz;
			FarbEnum farbe2	= brett[0][0].getFarbe();
		assertTrue(farbe.equals(farbe2));
	}
	@Test
	public void testFarbeFeld2(){
		
		FarbEnum farbe= FarbEnum.schwarz;
			FarbEnum farbe2	= brett[6][0].getFarbe();
		assertTrue(farbe.equals(farbe2));
	}
	@Test
	public void testFarbeFeld3(){
		
		FarbEnum farbe= FarbEnum.schwarz;
			FarbEnum farbe2	= brett[0][8].getFarbe();
		assertTrue(farbe.equals(farbe2));
	}
	@Test
	public void testFarbeFeld4(){
		
		FarbEnum farbe= FarbEnum.schwarz;
			FarbEnum farbe2	= brett[4][8].getFarbe();
		assertTrue(farbe.equals(farbe2));
	}
	@Test
	public void testFarbeFeld5(){
		
		FarbEnum farbe= FarbEnum.weiß;
			FarbEnum farbe2	= brett[3][8].getFarbe();
		assertTrue(farbe.equals(farbe2));
	}
	@Test
	public void testFarbeFeld6(){
		
		FarbEnum farbe= FarbEnum.weiß;
			FarbEnum farbe2	= brett[7][10].getFarbe();
		assertTrue(farbe.equals(farbe2));
	}
	@Test
	public void testFarbeFeld7(){
		
		FarbEnum farbe= FarbEnum.weiß;
			FarbEnum farbe2	= brett[9][2].getFarbe();
		assertTrue(farbe.equals(farbe2));
	}
	@Test(expected=java.lang.ArrayIndexOutOfBoundsException.class)
	public void testZugriffOoB1(){
		Spielfeld abc= brett[12][11];
	}
	@Test(expected=java.lang.ArrayIndexOutOfBoundsException.class)
	public void testZugriffOoB2(){
		Spielfeld abc= brett[11][12];
	}
	@Test
	public void testZug1(){
		spiel.doTheMove(s1, spiel.getSpielbrett().getBrett()[4][0], spiel.getSpielbrett().getBrett()[5][1]);
		assertTrue(brett[5][1].getSpielfigur()==null);
	}
	@Test
	public void testZug2(){
		spiel.doTheMove(s1, spiel.getSpielbrett().getBrett()[4][8], spiel.getSpielbrett().getBrett()[5][7]);
		assertTrue(spiel.getSpielbrett().getBrett()[5][7].getSpielfigur().getFarbe()==FarbEnum.weiß);
	}
	@Test
	public void testZug3(){
		spiel.doTheMove(s1, spiel.getSpielbrett().getBrett()[4][2], spiel.getSpielbrett().getBrett()[5][3]);
		assertTrue(spiel.getSpielbrett().getBrett()[5][3].getSpielfigur().getFarbe()==FarbEnum.weiß);
	}
	@Test
	public void testZug4(){
		spiel.doTheMove(s1, spiel.getSpielbrett().getBrett()[3][0], spiel.getSpielbrett().getBrett()[5][1]);
		assertFalse(spiel.getSpielbrett().getBrett()[5][1].getSpielfigur().getFarbe()==FarbEnum.weiß);
	}
	
	
	
	
//	// Testet gültige Spieleranzahl - 2 erlaubt
//	@Test(expected=RuntimeException.class)
//	public void testSpieleranzahl() {
//		spiel= new Spiel();
//		s3=new Spieler("Heinrich", FarbEnum.schwarz);
//		spiel.add(s1);
//		spiel.add(s2);
//	}
//	
//	
//	//Testet Spieler add Funktion
//	@Test
//	public void testSpieleradd1(){
//		spiel=new Spiel();
//		spiel.add(s1);
//		assertTrue(spiel.getSpieler()[0]!=null);
//	}
//	//Testet Spieler add Funktion
//	@Test
//	public void testSpieleradd2(){
//		spiel=new Spiel();
//		spiel.add(s1);
//		spiel.add(s2);
//		assertTrue(spiel.getSpieler()[0]!=null && spiel.getSpieler()[1]!=null);
//	}
//	// Testet Spieler auf unterschiedliche Farbe
//	@Test
//	public void testSpielerFarbe() {
//		spiel= new Spiel();
//		spiel.add(s1);
//		spiel.add(s2);
//			assertTrue(s1.getFarbe()!=s2.getFarbe());
//		
//		}
//		
//	//Testet SpielBrett erstellung, notation ende
//	@Test
//	public void testSpielBrettEnde(){
//		int i;
//		i=12;
//		
//		spiel= new Spiel(i);
//		String erg=""; //!!!Ueberlegen wie man dies umsetzen kann.!!!
//		assertTrue(erg=="l12");
//		}
//
//
//	//Testet SpielBrett erstellung, notation ende
//	@Test
//	public void testSpielBrettAnfang(){
//		int i;
//		i=12;
//		
//		spiel= new Spiel(i);
//		String erg=SpielBrettarr[0][0];
//		assertTrue(erg=="a1");
//		}
////Testet SpielBrett erstellung,Out of Bounds an Stelle 13 im Array
//	@Test(expected=IndexOutOfBoundsException.class)
//	public void testSpielBrettOutOfBounds(){
//		int i;
//		i=12;
//		
//		spiel= new Spiel(i);
//		String erg=SpielBrettarr[12][11];
//		}
//	//Testet Spielfigur initialisierung auf gueltigem Feld
//	@Test
//	public void testSpielfigurInit(){
//		
//		
//		
//	}
//	//Move Befehl testen
//	@Test
//	public void testMove1(){
//		int i =12;
//		spiel=new Spiel();
//		Spielfeld steinpos=new Spielfeld("b1");
//		Spielfigur stein1= new Spielfigur(FarbEnum.schwarz, steinpos);
//		Spielfeld posxy= new Spielfeld("a2");
//		spiel.move(s1, stein1, posxy);
//		assertTrue(stein1.getPosition()==posxy);
//		
//	}
	
	}

