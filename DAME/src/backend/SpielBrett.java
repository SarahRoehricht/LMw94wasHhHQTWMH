package backend;

public class SpielBrett {
	private char Spielfelder[][];

	SpielBrett(char Spielfelder[][]) {
		setSpielfelder(Spielfelder);
	}

	public char[][] getSpielfelder() {
		return Spielfelder;
	}

	public void setSpielfelder(char[][] spielfelder) {
		Spielfelder = spielfelder;
	}

}
