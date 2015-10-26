package backend;

import java.util.Scanner;

public class Spiel implements iBediener {

	private SpielBrett spielbrett;
	public SpielBrett getSpielbrett() {
		return spielbrett;
	}



	public void setSpielbrett(SpielBrett spielbrett) {
		this.spielbrett = spielbrett;
	}



	private static final int spielerMax = 2;
	private Spieler[] spieler = new Spieler[spielerMax];
	private int spielerBisher = 0;
	private Regelwerk regelwerk;
	private Scanner scanner= new Scanner(System.in);



	public Spiel() {
		spielbrett = new SpielBrett();
		this.welcome();
		regelwerk=new Regelwerk(this);
	}

	
	
	/**Spielinitialisierung, fragt die Namen ab und generiert Spieler fuer den Spielbeginn und Spielbrettausgabe
	 *
	 * @param Scanner scanner, String name1, String name2
	 * creates 2 Spieler objects and calls this.add method with each of them one after another.
	 */
	public void welcome(){
		System.out.println("Name Spieler 1:");
		String name1=scanner.next();
//		System.out.println("Mensch oder KI?");				Hum/Ki Abfrage implementieren
//		String ki1=scanner.next();							
//		System.out.println(ki1);									
//		String kiname=new String("ki");
//		while(ki1==kiname){							diese While-Schleife funktioniert noch nicht ki1!=kiname wenn Eingabe ki ist, liegt das an der Enter-Taste beim Scanner?
//			System.out.println("Ki noch nicht funktionsfaehig :(");
//			System.out.println("Mensch oder KI?");
//			name1=scanner.next();
//		}
		Spieler player1= new Spieler(name1, FarbEnum.weiß);
		add(player1);
		
		
		System.out.println("Name Spieler 2:");
		String name2=scanner.next();
		Spieler player2= new Spieler(name2, FarbEnum.schwarz);
		add(player2);
		
		System.out.println("Moege das Spiel beginnen!");
		
		System.out.println(spielbrett);
		System.out.println(player1.getName()+" - "+"'"+player1.getFarbe() +"'"+" faengt an!");
		act(player1);
	}
	
	private void act(Spieler player1) {
		System.out.println(player1 +"ist am Zug!");
		System.out.println("Eingabe Startfeld:");
		String coorda=scanner.nextLine();
		
		System.out.println("Eingabe Zielfeld:");
		String coordb=scanner.nextLine();
		
		
		
		
		
		
		if(spieler[0]==player1){
			act(spieler[1]);
		}
		else if(spieler[1]==player1){
			act(spieler[0]);
		}
		
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
		
		System.out.println(stein1.getFarbe()+"er" + "Spieler am Zug.");
		
	}

}
