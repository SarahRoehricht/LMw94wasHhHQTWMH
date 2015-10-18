package backend;
/**
 * 
 * @author A-2
 * @version 0.0.3
 */
public class SpielBrett {
	private Spielfeld[][] Brett;
	public static int groesse = 12; // Setze nach Aufgabenstellung Größe auf 12
	
	
	/**
	 * 
	 * @param groesse = 12
	 */
	SpielBrett(int groesse) {
		Brett= new Spielfeld[groesse][groesse]; 
		
		this.fillBrett(); //unötige übergabe entfernt
	}
/**sets Spielfeld into Spielfeld array
 * 
 * @param groesse
 */
	private void fillBrett() { //benötigt keine Übergabe da Brett schon die groesse beinhaltet
	
	for (int i = Brett.length; i > -1; i--) {
		
		for (int j = 0; j < Brett[i].length; j++) {
			
			if(i%2==j%2)
			{
				Brett[i][j]=new Spielfeld(i, j);
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

	

}
