package backend;

import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
/**
 * 
 * @author A-2
 *
 */
public class DatenzugriffPDF implements iDatenzugriff {

	@Override
	public void speichern(Object obj, String name) throws IOException {
		Document document = new Document(PageSize.A4, 20, 20, 20, 20);

		FileOutputStream fos = new FileOutputStream(name + ".pdf");
		PdfWriter writer;
		try {
			writer = PdfWriter.getInstance(document, fos);
			writer.open();
			document.open();

			document.add(new Paragraph(
					"Lieber User,\n\nvielen Dank, dass Sie unser Spiel gespielt haben. Ihren Spielstand zum Zeitpunkt des Speicherns finden Sie direkt unterhalb dieses Textes in Form eines Bildes wieder. Wir hoffen dass Ihnen unser Spiel Freude bereiten konnte. \nBei Rückfragen oder für Feedback stehen wir gerne für Sie bereit.\n\nMit freundlichen Grüßen, \nTeam -A2-"));

			Image img;
			img = Image.getInstance(name + ".png");
			float documentWidth = document.getPageSize().getWidth()
					- document.leftMargin() - document.rightMargin();
			float documentHeight = document.getPageSize().getHeight()
					- document.topMargin() - document.bottomMargin();
			img.scaleToFit(documentWidth, documentHeight);
			document.add(img);
			document.close();
			writer.close();

			
		} catch (DocumentException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Object laden(String name, String typ) throws IOException {
		return null;
	}

	@Override
	public void close() throws IOException {

	}

}
