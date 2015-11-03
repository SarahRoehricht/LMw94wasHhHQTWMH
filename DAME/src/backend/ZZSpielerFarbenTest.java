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
	
		FarbEnum farbe = null;
		farbe=farbe.schwarz;
	
		name="egon";
		
		s=new Spieler(name, farbe);
		
		
	}
	
	@Test
	public void SpielerCreation(){
		Spieler s2=new Spieler(new String ("Ebru"));
		assertFalse(s.equals(s2));
	}
	
	
	@Test
	public void SpielerFarbe() {
	FarbEnum erg=s.getFarbe();
		assertTrue(erg==FarbEnum.schwarz);
	}
	@Test
	public void SpielerFarbe2() {
	FarbEnum erg=s.getFarbe();
		assertTrue(erg!=FarbEnum.weiss);
	}

}
