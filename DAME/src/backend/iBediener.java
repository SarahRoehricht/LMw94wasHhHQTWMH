package backend;

public interface iBediener {

	 void welcome();
	void act(Spieler player1);
	Spielfeld[] eingabeSpielfeldSpieler(Spieler player1);
	 void askSchlagen(Spieler player, Spielfeld spielfeld);
}
