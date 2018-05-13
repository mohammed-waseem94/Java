package controller;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import application.Bill;
import javafx.collections.ObservableList;
import model.SalesAccess;
import res.Values;



public class GenerateInvoice{

	private static final String FILE_NAME = "/AWAF_ERP/data/rsc/textpositioning.xlsx";
	private static XSSFWorkbook workbook;
	private static XSSFSheet sheet;
	
	public  static void generateInvoice(String name, String address, String contact, String email,
			ObservableList<BillProduct> prod,double discount,double totamnt, boolean quot) {
		
		totamnt-=discount;
		LocalDateTime dt=LocalDateTime.now();
		String date = dt.getDayOfMonth()+"-"+dt.getMonthValue()+"-"+dt.getYear();
		double rate=Values.goldrate22;
		
		String invoice= ""+dt.toEpochSecond(ZoneOffset.UTC);
		String FILE_NAME2 = "/AWAF_ERP/users/bills/quotation.xlsx";
		if(!quot) {
			FILE_NAME2 = "/AWAF_ERP/users/bills/"+invoice+".xlsx";
			Bill b=new Bill(invoice, name, contact, email, totamnt);
			SalesAccess.addToSales(b);
		}
		FileInputStream fis;
		try {
			fis = new FileInputStream(new File(FILE_NAME));
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);
			fis.close();
		} catch (Exception ex) {

		}
		Cell namecell,addresscell,contcell,invcell,datecell,ratecell;
		namecell = sheet.getRow(11).getCell(2);
		namecell.setCellValue("\t"+name);
		addresscell = sheet.getRow(12).getCell(2);
		addresscell.setCellValue("  "+address);
		contcell = sheet.getRow(13).getCell(2);
		contcell.setCellValue("  "+contact);
		invcell = sheet.getRow(11).getCell(8);
		invcell.setCellValue("          "+invoice);
		datecell = sheet.getRow(12).getCell(8);
		datecell.setCellValue("          "+date);
		ratecell = sheet.getRow(13).getCell(8);
		ratecell.setCellValue("          "+rate);
		
		Cell snocell,desccell,pcscell,puritycell,gwtcell,nwtcell,swtcell,wstgcell,amntcell;

			for(int i=0;i<prod.size();i++) {
			BillProduct p = prod.get(i);
			snocell=sheet.getRow(16+i).getCell(1);
			snocell.setCellValue(i+1);
			desccell=sheet.getRow(16+i).getCell(2);
			desccell.setCellValue(p.getName());
			pcscell=sheet.getRow(16+i).getCell(3);
			pcscell.setCellValue(p.getPcs());
			puritycell=sheet.getRow(16+i).getCell(4);
			puritycell.setCellValue(p.getCrt());
			gwtcell=sheet.getRow(16+i).getCell(5);
			gwtcell.setCellValue(3);
			nwtcell=sheet.getRow(16+i).getCell(6);
			nwtcell.setCellValue(p.getMwt());
			swtcell=sheet.getRow(16+i).getCell(7);
			swtcell.setCellValue(p.getSwt());
			wstgcell=sheet.getRow(16+i).getCell(8);
			wstgcell.setCellValue(p.getWstg());
			amntcell=sheet.getRow(16+i).getCell(9);
			amntcell.setCellValue(p.getPrc());	
		}
		Cell cgstcell,sgstcell,totamntcell;
		cgstcell=sheet.getRow(31).getCell(9);
		cgstcell.setCellValue(totamnt*0.15);
		sgstcell=sheet.getRow(32).getCell(9);
		sgstcell.setCellValue(totamnt*0.15);
		totamntcell=sheet.getRow(33).getCell(9);
		totamntcell.setCellValue(totamnt);

		try {
			FileOutputStream fos = new FileOutputStream(FILE_NAME2);
			workbook.write(fos);
			workbook.close();
			fos.close();
			System.out.println("Done");
			File myFile = new File(FILE_NAME2);
//			 Desktop.getDesktop().open(myFile);	
			 Desktop.getDesktop().print(myFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}