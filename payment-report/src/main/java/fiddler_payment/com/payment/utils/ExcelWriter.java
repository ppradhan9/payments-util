/**
 * 
 */
package fiddler_payment.com.payment.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import fiddler_payment.com.payment.utils.model.PaymentModel;

/**
 * @author ppradhan
 *
 */
public class ExcelWriter {
	
	void writeExcel(String excelFilePath,List<PaymentModel> paymentRows) {
		final String[] header= {"ID", "Description", "Seller Message", "Created At","Amount","Amount Refunded","Currency",
				"Converted Amount","Fee","Tax","Converted Currency","Status","Customer ID","Customer Email","Card ID","Invoice ID",
				"Payment Source Type","SubscriptionType","Subscription Start","Subscription End"};
		Workbook workbook = null;
	    // Excel with .xslx extension
	    workbook = new XSSFWorkbook();
	    // For .xls extension HSSF workbook can be created
	    //workbook = new HSSFWorkbook();

	    // Creating sheet with in the workbook
	    org.apache.poi.ss.usermodel.Sheet sheet = workbook.createSheet("Payment");
//	    /*Font and style For Header*/
//	    Font font = (Font) workbook.createFont();
//	    font.setFontName("VERDANA");
//	    font.setColor(IndexedColors.CORNFLOWER_BLUE.getIndex());
//	    font.setBold(true);
	         
	    CellStyle style = workbook.createCellStyle();
//	    style.setFont(font);
	    style.setWrapText(true);
	    style.setAlignment(HorizontalAlignment.CENTER);
//	    style.setVerticalAlignment(VerticalAlignment.MIDDLE);
	    style.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
	    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	    style.setBorderRight(BorderStyle.THIN);
	    style.setRightBorderColor(IndexedColors.BLACK.getIndex());
	    style.setBorderLeft(BorderStyle.THIN);
	    style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
	    style.setBorderTop(BorderStyle.THIN);
	    style.setTopBorderColor(IndexedColors.BLACK.getIndex());
	    style.setBorderBottom(BorderStyle.THIN);
	    style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
	         
	    Row row =  sheet.createRow(0);
	    // Writing header to excel
	    for(int i = 0; i < header.length; i++) {
	       // each column 20 characters wide
	       sheet.setColumnWidth(i, 20*256);
	       Cell cell = row.createCell(i);
	       cell.setCellValue(header[i]);
	       cell.setCellStyle(style);
	    }   
	    // Header styling ends
	    //Preparing column data for each row
	    CellStyle dateStyle = workbook.createCellStyle();
	    // Setting format For the date column
	    dateStyle.setDataFormat(workbook.getCreationHelper()
	                                    .createDataFormat().getFormat("dd/MM/yyyy"));
	    int rowNum = 1;
	    for(PaymentModel eachModel : paymentRows) {
	      // create new row
	      row = sheet.createRow(rowNum++);
	      row.createCell(0).setCellValue(eachModel.getPaymentId());
	      row.createCell(1).setCellValue(eachModel.getDescription());
	      row.createCell(2).setCellValue(eachModel.getSellerMessage());
	      row.createCell(3).setCellValue(eachModel.getCreatedAt());
	      row.createCell(4).setCellValue(eachModel.getAmount());
	      row.createCell(5).setCellValue(eachModel.getAmountRefunded());
	      row.createCell(6).setCellValue(eachModel.getCurrency());
	      row.createCell(7).setCellValue(eachModel.getConvertedAmount());
	      row.createCell(8).setCellValue(eachModel.getFee());
	      row.createCell(9).setCellValue(eachModel.getTax());
	      row.createCell(10).setCellValue(eachModel.getConvertedCurrency());
	      row.createCell(11).setCellValue(eachModel.getStatus());
	      row.createCell(12).setCellValue(eachModel.getCustomerId());
	      row.createCell(13).setCellValue(eachModel.getCustomerEmail());
	      row.createCell(14).setCellValue(eachModel.getCardId());
	      row.createCell(15).setCellValue(eachModel.getInvoiceId());
	      row.createCell(16).setCellValue(eachModel.getPaymentSourceType());
	      row.createCell(17).setCellValue(eachModel.getSubscriptionType());
	      row.createCell(18).setCellValue(eachModel.getSubscriptionStart());
	      row.createCell(19).setCellValue(eachModel.getSubscriptionEnd());
//	      Cell cell = row.createCell(3);
//	      cell.setCellValue(emp.getDob());
//	      cell.setCellStyle(dateStyle);
	    }
	        
	    FileOutputStream outputStream = null;
	    try {
	     outputStream = new FileOutputStream(excelFilePath);
	     // Writing to excel sheet
	     workbook.write(outputStream);
	    } catch (IOException exp) {
	     // TODO Auto-generated catch block
	     exp.printStackTrace();
	    }finally {
	      if(outputStream != null) {
	        try {
	          outputStream.close();
	          workbook.close();
	        } catch (IOException e) {
	          // TODO Auto-generated catch block
	          e.printStackTrace();
	        }
	      }
	    }
	}

}
