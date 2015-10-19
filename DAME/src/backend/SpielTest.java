package backend;

/**
 * 
 * @author A2
 *
 */

public class SpielTest implements iBediener{

	public static void main(String[] args) {
		
	Spiel spiel= new Spiel();
	
	//Anlegen von x-vielen Spielern möglich
	Spieler s1 = new Spieler();
	Spieler s2 = new Spieler();
	Spieler s3 = new Spieler();
	
	//Namen vergeben
	//Möglichkeit für JUnit-Test
	s1.setName("Gustav");
	s2.setName("Max");
	s3.setName("Max");
	
	System.out.println(s2.getName());
	
	//erwartete RuntimeException geworfen bei mehr als zwei Spielern
	//Möglichkeit für JUnit-Test
	spiel.add(s3);
	spiel.add(s2);
//	spiel.add(s3);
	
	//
	System.out.println(spiel.getSpielerBisher());

	}

}

