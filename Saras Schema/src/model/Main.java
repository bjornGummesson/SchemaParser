package model;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class Main {

	public static void main(String[] args) {
		Reader r = new Reader();

		try {
			r.readFile("schema.xlsx");
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		r.print();
	}

}
