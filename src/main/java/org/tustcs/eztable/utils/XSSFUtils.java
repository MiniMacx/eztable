package org.tustcs.eztable.utils;

import org.apache.commons.collections4.MapIterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.tustcs.eztable.config.Config;
import org.tustcs.eztable.entity.FileKeyword;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class XSSFUtils {

    public static List<FileKeyword> readKeyword(int rowNo, String fileName) throws IOException {
        FileInputStream fis=new FileInputStream(
                new File(Config.FILE_PATH+fileName));
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);
        XSSFRow row=sheet.getRow(rowNo);
        Iterator<Cell> cellIterator=row.cellIterator();
        List<FileKeyword> keywordList=new ArrayList<FileKeyword>();
        while(cellIterator.hasNext()){
            Cell cell=cellIterator.next();
            FileKeyword fileKeyword=new FileKeyword();
            fileKeyword.setKeywordColumn(cell.getColumnIndex());
            fileKeyword.setKeywordName(cell.getStringCellValue());
            keywordList.add(fileKeyword);
        }
        fis.close();
        return keywordList;
    }

    public static void writeByKeyword(List<String> infoList, List<FileKeyword> fileKeywordList, String fileName) throws IOException {
        FileInputStream fis=new FileInputStream(
                new File(Config.FILE_PATH+fileName));
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet=workbook.getSheetAt(0);
        Map<Integer,String> infoMap=new HashMap<Integer, String>();
        for(int i=0;i<infoList.size();i++){
            infoMap.put(fileKeywordList.get(i).getKeywordColumn(),infoList.get(i));
            System.out.println(fileKeywordList.get(i).getKeywordColumn()+"  "+infoList.get(i));
        }
        XSSFRow row=sheet.createRow(sheet.getLastRowNum()+1);
        for(Integer key:infoMap.keySet()){
            Cell cell=row.createCell(key);
            cell.setCellValue(infoMap.get(key));
        }
        FileOutputStream out = new FileOutputStream(
                new File(Config.FILE_PATH+fileName));
        workbook.write(out);
        out.close();
        fis.close();
    }

//        public static void main(String[] args) throws IOException {
//            List<String> infoList=new ArrayList<String>();
//            infoList.add("a");
//            infoList.add("b");
//
//            List<FileKeyword> keywordList=new ArrayList<FileKeyword>();
//
//            FileKeyword keyword=new FileKeyword();
//            keyword.setFileId(1);
//            keyword.setKeywordId(1);
//            keyword.setKeywordName("name");
//            keyword.setKeywordValue(0);
//            keyword.setKeywordColumn(2);
//
//            FileKeyword keyword1=new FileKeyword();
//            keyword1.setFileId(1);
//            keyword1.setKeywordId(1);
//            keyword1.setKeywordName("value");
//            keyword1.setKeywordValue(0);
//            keyword1.setKeywordColumn(4);
//
//            keywordList.add(keyword);
//            keywordList.add(keyword1);
//
//            writeByKeyword(infoList,keywordList,"mmm");
//        }
}
