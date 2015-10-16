package backend;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

public class ZZSpielTest {

	private Spieler s1 = new Spieler();
	private Spieler s2 = new Spieler();
	private Spieler s3 = new Spieler();
	private Object spiel;

	// Testet gültige Spieleranzahl - 2 erlaubt
	@Test
	public void testSpieleranzahl() {
		spiel.add(s1);
		spiel.add(s2);
		spiel.add(s3);
	}

	// Testet Spieler auf unterschiedliche Farbe
	@Test
	public void testSpielerFarbe() {
		if (s1.getFarbe() == s2.getFarbe()) {
			assertFalse(true);
		}
	}

}
