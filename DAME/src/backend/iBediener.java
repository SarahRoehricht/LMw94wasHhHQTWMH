package backend;

public interface iBediener {

	 void add(Spieler s1);
	 void welcome();
	 int getSpielerBisher();
	 void setSpielerBisher(int spielerBisher);
	 void act(Spieler player); 
}
