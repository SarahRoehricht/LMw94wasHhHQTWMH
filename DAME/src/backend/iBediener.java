package backend;

public interface iBediener {

	 void welcome();
	
	 void act(Spieler player); 
	 void askSchlagen(Spieler player, Spielfeld spielfeld);
}
