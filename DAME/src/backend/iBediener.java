package backend;

public interface iBediener {

	//void act(Spieler player1);

	Spielfeld[] eingabeSpielfeldSpieler(Spieler player1);

	void askSchlagen(Spieler player, Spielfeld spielfeld);

	public void speichern(String pfad, String dateiname, String typ);

	public void laden(String pfad, String dateiname, String typ);

}
