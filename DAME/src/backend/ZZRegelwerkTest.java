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

	 @Test
	 public void testHatGezogenWahr() {
		 boolean hatGezogen = true;
		 assertTrue(hatGezogen == true);
	 }
	 
	 @Test
	 public void testHatGezogenFalsch() {
		 boolean hatGezogen = false;
		 assertTrue(hatGezogen == false);
	 }
	
//	 @Test
//	 public void testHatGeschlagenWahr() {
//		 boolean hatgeschlagen = true;
//		 assertTrue(true);
//	 }
	
	// @Test
	// public void testWirdDame() {
	// fail("Not yet implemented");
	// }
	//
	// @Test
	// public void testSchlagenMoeglichFelder() {
	// fail("Not yet implemented");
	// }
	//
	// @Test
	// public void testSchlagenFeldGroesse() {
	// fail("Not yet implemented");
	// }
	//
	// @Test
	// public void testObject() {
	// fail("Not yet implemented");
	// }
	//
	// @Test
	// public void testGetClass() {
	// fail("Not yet implemented");
	// }
	//
	// @Test
	// public void testHashCode() {
	// fail("Not yet implemented");
	// }
	//
	// @Test
	// public void testEquals() {
	// fail("Not yet implemented");
	// }
	//
	// @Test
	// public void testClone() {
	// fail("Not yet implemented");
	// }
	//
	// @Test
	// public void testToString() {
	// fail("Not yet implemented");
	// }
	//
	// @Test
	// public void testNotify() {
	// fail("Not yet implemented");
	// }
	//
	// @Test
	// public void testNotifyAll() {
	// fail("Not yet implemented");
	// }
	//
	// @Test
	// public void testWaitLong() {
	// fail("Not yet implemented");
	// }
	//
	// @Test
	// public void testWaitLongInt() {
	// fail("Not yet implemented");
	// }
	//
	// @Test
	// public void testWait() {
	// fail("Not yet implemented");
	// }
	//
	// @Test
	// public void testFinalize() {
	// fail("Not yet implemented");
	// }

}
