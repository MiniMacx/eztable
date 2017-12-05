package org.tustcs.eztable.test;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class ReadSheet {
    static XSSFRow row;
    public static void main(String[] args) throws Exception
    {
        FileInputStream fis = new FileInputStream(
                new File("g:\\WriteSheet.xlsx"));
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet spreadsheet = workbook.getSheetAt(0);
        for (Row aSpreadsheet : spreadsheet) {
            row = (XSSFRow) aSpreadsheet;
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_NUMERIC:
                        System.out.print(
                                cell.getNumericCellValue() + " \t\t ");
                        break;
                    case Cell.CELL_TYPE_STRING:
                        System.out.print(
                                cell.getStringCellValue() + " \t\t ");
                        break;
                }
                if(cell.getStringCellValue().equals("tp01")){
                    System.out.print("gotit");
                }
            }
            System.out.println();
        }
        fis.close();
    }
}
