package backend;

public interface iBediener {

	 void add(Spieler s1);
	 void welcome();
	 int getSpielerBisher();
	 void setSpielerBisher(int spielerBisher);
	 void move(Spielfigur stein1, Spielfeld posxy); // mit vererbung über spiel ändern kein interface für 2 klassen.
}
