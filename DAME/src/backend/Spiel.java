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
		System.out.println("KI? - y/n");
		String ki1 = scanner.next();
		String kiabfrage = new String("y");
		while (ki1.equals(kiabfrage)) { // Ki funktionsfaehig machen!
			System.out.println("Ki noch nicht funktionsfaehig :(");
			System.out.println("KI? - y/n");
			ki1 = scanner.next();
		}
		Spieler player1 = new Spieler(name1, FarbEnum.weiß);
		add(player1);

		System.out.println("Name Spieler 2:");
		String name2 = scanner.next();
		System.out.println("KI? - y/n");
		String ki2 = scanner.next();
		String kiabfrage2 = "y";
		while (ki2.equals(kiabfrage2)) {
			System.out.println("Ki noch nicht funktionsfaehig :(");// Ki
																															// funktionsfaehig
																															// machen!
			System.out.println("KI? - y/n");
			ki2 = scanner.next();
		}

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
		if (startfeld.getSpielfigur().getZustand() == ZustandEnum.nichts) {
			System.out.println("Keine Spielfigur auf dem Spielfeld " + coorda + "!");// Muss
			// zurueckSpringen
			// zu
			// Eingabe
			// Startfeld.
			// Fehlt die
			// Implementierung.
		} else {

			// Spielfigur startSF = startfeld.getSpielfigur();
			System.out.println("Eingabe Zielfeld:");
			String coordb = scanner.nextLine();
			// Spielfeld Zielfeld =EingabeSpielfeld(coordb) ;

			move(startfeld.getSpielfigur(), EingabeSpielfeld(coordb));

		}
		if (spieler[0] == player1) {
			
			act(spieler[1]);
		} else if (spieler[1] == player1) {
		
			act(spieler[0]);
		}
		

	}

	private Spielfeld EingabeSpielfeld(String coord) {
		char Stelle1 = coord.charAt(0);
		int Stelle2 = coord.charAt(1);
		int Stelle1X = ((int) Stelle1) - 97;
		int Stelle2Y = Stelle2 - 49;
		Spielfeld[][] spielfelder = spielbrett.getBrett();

		Spielfeld spielfeld = spielfelder[Stelle2Y][Stelle1X];
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

	public void move(Spielfigur stein1, Spielfeld posxy) {
		System.out.println(stein1);
		System.out.println(posxy.getSpielfigur());

		// if(regelwerk.legitmove()){
		posxy.getSpielfigur().setZustand(stein1.getZustand());
		posxy.getSpielfigur().setFarbe(stein1.getFarbe());
		stein1.setFarbe(FarbEnum.nichts);
		stein1.setZustand(ZustandEnum.nichts);
		
		// }
	
		System.out.println(posxy.getSpielfigur().getFarbe() + " er" + "Spieler am Zug.");
		System.out.println(spielbrett);
	}

	public int getSpielerBisher() {
		return spielerBisher;
	}

	public void setSpielerBisher(int spielerBisher) {
		this.spielerBisher = spielerBisher;
	}

	public SpielBrett getSpielbrett() {
		return spielbrett;
	}

	public void setSpielbrett(SpielBrett spielbrett) {
		this.spielbrett = spielbrett;
	}
}
