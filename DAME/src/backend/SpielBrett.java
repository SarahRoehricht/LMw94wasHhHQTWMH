package backend;

public class SpielBrett {
	private char[][] Spielfelder = new char [12][12];
	// private String Spielfelder

	SpielBrett(char Spielfelder[][]) {
		setSpielfelder(Spielfelder);
	}

	public char[][] getSpielfelder() {
		return Spielfelder;
	}

	public void setSpielfelder(char[][] spielfelder) {
	
	for (int i = 0; i < spielfelder[i].length; i++) {
		Spielfelder[i]=A(i++);
		for (int j = 0; j < spielfelder.length; j++) {
			Spielfelder[i][j]=B(j++);
		}
		}
		Spielfelder = spielfelder;
		System.out.println(Spielfelder);
	}

	private char B(int i) {
		// TODO Auto-generated method stub
		return 0;
	}

	private char[] A(int i) {
		// TODO Auto-generated method stub
		return null;
	}

}
