package backend;

import java.util.Scanner;

/**
 * 
 * @author A2
 *
 */

public class SpielTest implements iBediener{

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		System.out.println("Spiel laden [L] oder neues Spiel spielen [n].\n" + "Der aktuelle Spielstand kann jederzeit über die Eingabe [s] gespeichert werden");
		String eingabe = scanner.nextLine();
		if ("l".equals(eingabe) == true) {
			System.out.println("Dateinamen eingeben.");
			String filename = scanner.nextLine();
			System.out.println("Speichertyp [csv] oder [ser] eingeben.");
			String typ = scanner.nextLine();
			iDatenzugriff idz = new DatenzugriffCSV();
			laden("", filename, typ);

		} else if ("n".equals(eingabe) == true) {

			System.out.println("Name Spieler 1:");
			String name1 = scanner.next();
			System.out.println("KI? - y/n");
			while (!scanner.hasNext("[yn]")) {
				System.out.println("KI? - y/n");
				scanner.next();
			}
			String ki1 = scanner.next();
			boolean ki1erstellen = false;

			if (ki1.equals("y")) {
				ki1erstellen = true;
			}
			
			
//
//			Spieler player1 = new Spieler(name1, FarbEnum.weiss, ki1erstellen);
//
//			add(player1);
			System.out.println(name1 + " hinzugefuegt!");
			System.out.println("Name Spieler 2:");
			String name2 = scanner.next();
			System.out.println("KI? - y/n");
			while (!scanner.hasNext("[yn]")) {
				System.out.println("KI? - y/n");
				scanner.next();
			}
			String ki2 = scanner.next();
			boolean ki2erstellen = false;
			if (ki2.equals("y")) {
				ki2erstellen = true;
			}
//
//			Spieler player2 = new Spieler(name2, FarbEnum.schwarz, ki2erstellen);
//
//			add(player2);
//			System.out.println(player2 + " hinzugefuegt!");
//
//			System.out.println("Moege das Spiel beginnen!");

//			System.out.println(spielbrett);
//			System.out.println(player1.getName() + " - " + "'" + player1.getFarbe() + "'" + " faengt an!");
			scanner.reset();
			//playerRotation(player1, player2);
			
			iBediener unserSpiel = new Spiel(name1, ki1erstellen, name2, ki2erstellen);
		}

		iBediener spiel = new Spiel(eingabe, false, eingabe, false);

	}

	@Override
	public Spielfeld[] eingabeSpielfeldSpieler(Spieler player1) {

		
		return null;
	}

	@Override
	public void askSchlagen(Spieler player, Spielfeld spielfeld) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void speichern(String pfad, String dateiname, String typ) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void laden(String pfad, String dateiname, String typ) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void welcome() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void act(Spieler player1) {
		// TODO Auto-generated method stub
		
	}
}
