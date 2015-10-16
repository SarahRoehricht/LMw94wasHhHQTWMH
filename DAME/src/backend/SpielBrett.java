package backend;

public class SpielBrett {
	private String Spielfelder;

	SpielBrett(String Spielfelder) {
		setSpielfelder(Spielfelder);
	}

	public String getSpielfelder() {
		return Spielfelder;
	}

	public void setSpielfelder(String spielfelder) {
		Spielfelder = spielfelder;
	}

}
