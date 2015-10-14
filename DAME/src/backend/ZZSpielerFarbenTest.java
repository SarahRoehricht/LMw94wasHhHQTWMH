package backend;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ZZSpielerFarbenTest {
	protected Spieler s;

	@Before
	public void vorMethode(){
		System.out.println("@Before");
		String name;
		Ki humorki;
		FarbEnum farbe;
		farbe=schwarz;
		humorki="ki";
		name="egon";
		
		s=new Spieler(name, farbe, humorki);
		
	}
	
	@Test
	public void SpielerCreation(){
		
	}
	
	
	@Test
	public void SpielerFarbe() {
	
		assertTrue(erg=="schwarz");
	}

}
