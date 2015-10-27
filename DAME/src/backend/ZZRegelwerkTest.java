package backend;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ZZRegelwerkTest {

	@Before
	public void vorMethode() {
		System.out.println("@Before");

		FarbEnum farbe = null;
		farbe = farbe.schwarz;

		Spielfeld s;
		s = new Spielfeld(0, 5, farbe.schwarz);

		Spielfigur sp;
		sp = new Spielfigur(farbe.schwarz, s);

	}

//	@Test
//	public void testMove() {
//		FarbEnum farbe = null;
//		farbe = farbe.schwarz;
//		Spielfeld s = new Spielfeld(0, 5, farbe.schwarz);
//		Spielfigur sp = new Spielfigur(farbe.schwarz, s);
//		assertTrue(s == null);
//	}

	@Test
	public void testIstZugErlaubtMitUngleichNull() {
	FarbEnum farbe = null;
	farbe = farbe.schwarz;
	Spielfeld s = new Spielfeld(0, 5, farbe);
		assertTrue(s != null);
	}
	
	@Test
	public void testIstZugErlaubtMitNull() {
	FarbEnum farbe = null;
	farbe = farbe.schwarz;
	Spielfeld s = new Spielfeld(0, 5, farbe);
		assertFalse(s == null);
	}
	
	
}
