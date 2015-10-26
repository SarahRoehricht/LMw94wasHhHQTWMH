package backend;

import java.util.Scanner;

public class Spiel implements iBediener {

	private SpielBrett spielbrett;

	private static final int spielerMax = 2;
	private Spieler[] spieler = new Spieler[spielerMax];
	private int spielerBisher = 0;
	private Regelwerk regelwerk;

	public Spiel() {
		spielbrett = new SpielBrett();
		this.welcome();
		regelwerk = new Regelwerk(this);
	}

	/**
	 * Spielinitialisierung, fragt die Namen ab und generiert Spieler fuer den
	 * Spielbeginn und Spielbrettausgabe
	 *
	 * @param Scanner
	 *          scanner, String name1, String name2 creates 2 Spieler objects and
	 *          calls this.add method with each of them one after another.
	 */
	public void welcome() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Name Spieler 1:");
		String name1 = scanner.next();
		// System.out.println("Mensch oder KI?"); Hum/Ki Abfrage implementieren
		// String ki1=scanner.next();
		// System.out.println(ki1);
		// String kiname=new String("ki");
		// while(ki1==kiname){ diese While-Schleife funktioniert noch nicht
		// ki1!=kiname wenn Eingabe ki ist, liegt das an der Enter-Taste beim
		// Scanner?
		// System.out.println("Ki noch nicht funktionsfaehig :(");
		// System.out.println("Mensch oder KI?");
		// name1=scanner.next();
		// }
		Spieler player1 = new Spieler(name1, FarbEnum.weiß);
		add(player1);

		System.out.println("Name Spieler 2:");
		String name2 = scanner.next();
		Spieler player2 = new Spieler(name2, FarbEnum.schwarz);
		add(player2);

		System.out.println("Moege das Spiel beginnen!");

		System.out.println(spielbrett);
		System.out.println(player1.getName() + " - " + "'" + player1.getFarbe() + "'" + " faengt an!");
		act(player1);
		scanner.reset();
		
	}

	public void act(Spieler player1) {
		Scanner scanner = new Scanner(System.in);
		System.out.println(player1 + " ist am Zug!");
		System.out.println("Eingabe Startfeld:");
		String coorda = scanner.nextLine();
		Spielfeld startfeld = EingabeSpielfeld(coorda);
		if (startfeld.getSpielfigur() == null) {
			System.out.println("Keine Spielfigur auf dem Spielfeld " + coorda+ "!");// Muss
																																		// zurueckSpringen
																																		// zu
																																		// Eingabe
																																		// Startfeld.
																																		// Fehlt die
																																		// Implementierung.
		} else if (startfeld.getSpielfigur() != null) {

			Spielfigur startSF = startfeld.getSpielfigur();
			System.out.println("Eingabe Zielfeld:");
			String coordb = scanner.nextLine();
			Spielfeld Zielfeld = EingabeSpielfeld(coordb);

			move(startSF, Zielfeld);

	}
		if (spieler[0] == player1) {
			act(spieler[1]);
		} else if (spieler[1] == player1) {
			act(spieler[0]);
		}
		scanner.close();

	}

	private Spielfeld EingabeSpielfeld(String coord) {
		char Stelle1 = coord.charAt(0);
		int Stelle2 = coord.charAt(1);
		int Stelle1X = ((int) Stelle1 )- 97;
		int Stelle2Y = Stelle2 - 48;
		Spielfeld[][] spielfelder= spielbrett.getBrett();
		
		
		Spielfeld spielfeld=spielfelder[Stelle1X][Stelle2Y];
		return spielfeld;
	}

	/**
	 * Fuegt einen Spieler dem Spieler Array hinzu. Max 2 Spieler
	 * 
	 * @param Spieler
	 *          s1
	 */
	public void add(Spieler s1) {
		if (spielerBisher >= 2) {
			throw new RuntimeException("Maximale Spieleranzahl erreicht!");
		} else if (spielerBisher == 0) {
			spieler[0] = s1;
			this.setSpielerBisher(++spielerBisher);
		} else {
			spieler[1] = s1;
			this.setSpielerBisher(++spielerBisher);
		}
		if (spielerBisher == 2) {// überprüft, dass kine zwei identischen Namen
															// vergeben wurden
			if (spieler[0].getName().equals(spieler[1].getName())) {
				throw new RuntimeException("Dieser Name ist bereits vergeben!");
			}
		}
	}

	public int getSpielerBisher() {
		return spielerBisher;
	}

	public void setSpielerBisher(int spielerBisher) {
		this.spielerBisher = spielerBisher;
	}

	public void move(Spielfigur stein1, Spielfeld posxy) {

		regelwerk.move(stein1, posxy);

		System.out.println(stein1.getFarbe() + "er" + "Spieler am Zug.");

	}

	public SpielBrett getSpielbrett() {
		return spielbrett;
	}

	public void setSpielbrett(SpielBrett spielbrett) {
		this.spielbrett = spielbrett;
	}
}
