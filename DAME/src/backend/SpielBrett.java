package backend;

/**
 * 
 * @author A-2
 * @version 0.0.3
 */
public class SpielBrett {
	private Spielfeld[][] Brett;
	public static final int groesse = 12; // Setze nach Aufgabenstellung Größe auf
																				// 12

	/**
	 * Default Konstruktor
	 * 
	 * 
	 */
	SpielBrett() {
		Brett = new Spielfeld[groesse][groesse];

		this.fillBrett(); 
	}

	/**
	 * sets Spielfeld into Spielfeld array
	 * 
	 * @param groesse
	 */
	private void fillBrett() { // benötigt keine Übergabe da Brett schon die
															// groesse beinhaltet

		for (int i = Brett.length - 1; i >=0; i--) {

			for (int j = 0; j < Brett[i].length; j++) {

				if (i % 2 == j % 2) {
				
					Brett[i][j] = new Spielfeld(j, i , FarbEnum.schwarz);
					
				}
				else{
					Brett[i][j] = new Spielfeld(j, i, FarbEnum.weiß);
					
				}

			}
			
		}

	}

	/**
	 * 
	 * @return Brett
	 */
	public Spielfeld[][] getBrett() {
		return Brett;
	}

	/**
	 * 
	 * @param brett
	 */
	public void setBrett(Spielfeld[][] brett) {
		Brett = brett;
	}
/** goes through every Element of 2-D Spielfeld Brett Array and uses toString()
 * 
 * @return Brett[i][j].toString();
 */
	public String printBrett() {
		char cd= 97;
		System.out.print("\t");
		for (int i = 0; i < Brett.length; i++) {
			System.out.print(cd+"\t");
			cd++;
		}
		System.out.println();
		int d=12;
		for (int i = Brett.length - 1; i >= 0; i--) {
System.out.print(d+"\t");
			for (int j = 0; j < Brett[i].length; j++) {

				//if (i % 2 == j % 2) {
					System.out.print( Brett[i][j]);
				//}
				
			}
			System.out.print(d+"\t");
			System.out.println();
			
			d--;
		}
		char c= 97;
		System.out.print("\t");
		for (int i = 0; i < Brett.length; i++) {
			System.out.print(c+"\t");
			c++;
		}
		return "" ;
	}
/**Funktion printBrett wird aufgerufen
 * @return this.printBrett 
 */
	@Override
	public String toString() {
		
		
		return this.printBrett();
	}
}
