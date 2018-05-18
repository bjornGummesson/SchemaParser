package model;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Reader {


	private ArrayList<ShiftEntry> entries;

	public static final String FILE_PATH = "schema.xlsx";
	private Workbook wb;

	public Reader() {
	
		entries = new ArrayList<>();

	}

	public void readFile(String filePath) throws EncryptedDocumentException, InvalidFormatException, IOException {
		wb = WorkbookFactory.create(new File(filePath));

	}

	private Sheet getSheet() {

		return wb.getSheetAt(0);
	}
	
	
	
	//Fyller listan med ShiftEntryObjekten som inneh�ller informationen fr�n schemat
	private void fillList() {
		Sheet sheet = getSheet();
		Iterator<Row> rowIterator = sheet.rowIterator();

		Row row = rowIterator.next();

		row = rowIterator.next();
		while (rowIterator.hasNext()) {
			ShiftEntry entry = new ShiftEntry();
			row = rowIterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();
			Cell cell;
			for (int i = 0; i < 4; i++) {
				cell = cellIterator.next();
				if (i == 0) {
					entry.setDate(cell.getStringCellValue());
				}

				if (i == 2) {
					entry.setShiftType(cell.getStringCellValue());
				}
				if (i == 3) {
					entry.setShift(cell.getStringCellValue());
					
				}
			}
			if (!entry.getShiftType().equals("---")) {
				entries.add(entry);
			}

		}

	}
	
	
	// temporary check 

	public void print() {
		fillList();
		System.out.println("---------------------");
		for (ShiftEntry e: entries) {
		
			if(!e.isEmpty()) {

				System.out.println(e.toString());
				
				
			}
			
		}
	}

	private class ShiftEntry {
		String date;
		String shiftType;
		String shift;

		public ShiftEntry(String date, String shiftType, String shift) {
			super();
			this.date = date;
			this.shiftType = shiftType;
			this.shift = shift;
		}

		public ShiftEntry() {

		}

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public String getShiftType() {
			return shiftType;
		}

		public void setShiftType(String shiftType) {
			this.shiftType = shiftType;
		}

		public String getShift() {
			return shift;
		}

		public void setShift(String shift) {
			this.shift = shift;
		}

		public String toString() {
			return "Date: " + date + "| ShiftType: " + shiftType + "| Time: " + shift;
		}
		public boolean isEmpty() {
			if(date.equals("") || shiftType.equals("")) {
				return true;
			}
			return false;
		}
	}
}
