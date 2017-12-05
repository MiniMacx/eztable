package org.tustcs.eztable.test;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;

public class CreateWorkbook {
    public static void main(String[] args)throws Exception
    {
        //Create Blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook();
        //Create file system using specific name
        FileOutputStream out = new FileOutputStream(
                new File("g:\\createworkbook.xlsx"));

        //write operation workbook using file out object
        workbook.write(out);
        out.close();
        XSSFSheet sheet=workbook.createSheet("sheet1");
        XSSFRow row = sheet.createRow((short)1);
        System.out.println("createworkbook.xlsx written successfully");
    }
}
