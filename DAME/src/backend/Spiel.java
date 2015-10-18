package backend;

public class Spiel //implements iBediener
{

	
	private SpielBrett spielbrett;
	private static final int spielerMax = 2;
	private Spieler[] spieler = new Spieler[spielerMax];
	private int spielerBisher =0;
	
	
	public Spiel(int i){

	}


	public Spiel() {
	
	}

/**Fuegt einen Spieler dem Spieler Array hinzu. Max 2 Spieler
 * 
 * @param Spieler s1
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
	}


	public void move(Spieler s1, Spielfigur stein1, Spielfeld posxy) {
	
		
	}


	public int getSpielerBisher() {
		return spielerBisher;
	}


	public void setSpielerBisher(int spielerBisher) {
		this.spielerBisher = spielerBisher;
	}


	
}