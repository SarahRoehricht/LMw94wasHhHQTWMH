package backend;

import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

public class SpielTestSpeichern implements Serializable, iBediener, iDatenzugriff {

	public static void main(String[] args) {

		SpielBrett sb = new SpielBrett();
		Scanner scanner = new Scanner(System.in);

		System.out.println("Der aktuelle Spielstand kann jederzeit während dem Spiel über die Eingabe von [s] gespeichert werden");
//		String eingabe = scanner.nextLine();
//		if ("l".equals(eingabe) == true) {
//			System.out.println("Dateinamen eingeben.");
//			String filename = scanner.nextLine();
//			System.out.println("Speichertyp [csv] oder [ser] eingeben.");
//			String typ = scanner.nextLine();
//			if (typ.equals("csv")) {
//				try {
//					Spiel s = new Spiel();
//					s.laden(filename, typ);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			} else if (typ.equals("ser")) {
//				try {
//					Spiel s = new Spiel();
//					s.laden(filename, typ);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
		System.out.println("Geben Sie `n` ein für ein Test Speichern Spiel das 3 Züge durchführt und danach unter Test4.csv/.ser das Spiel speichert");
		
		iBediener unserSpiel = new Spiel("TesterName1", false, "TesterName2", false);
		
	}

	@Override
	public void speichern(Object obj, String name) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public Object laden(String name, String typ) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public Spielfeld[] eingabeSpielfeldSpieler(Spieler player1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void askSchlagen(Spieler player, Spielfeld spielfeld) {
		// TODO Auto-generated method stub

	}

}
