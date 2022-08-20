package io.pragra.framework.data;

import io.pragra.framework.conf.Configuration;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelReader {
    private static ExcelReader instance;
    private Workbook workbook;
    private ExcelReader(){
        // Read File
        Path path = Paths.get(
                Configuration.getProperty("excel.location"),
                Configuration.getProperty("excel.name")

        );
        try {
            InputStream stream = new FileInputStream(path.toFile());
            workbook = new XSSFWorkbook(stream);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized List<Object[]> getDataFromSheet(String sheetName, boolean skipHeader){
        if(instance == null) {
            instance = new ExcelReader();
        }
        Sheet sheet = instance.workbook.getSheet(sheetName);

        Iterator<Row> rowIterator = sheet.rowIterator();
        List<Object[]> data = new ArrayList<>();
        if(skipHeader && rowIterator.hasNext()) {
            rowIterator.next(); // this will skip one row
        }
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            List<Object> cellData = new ArrayList<>();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                if(cell.getCellType()== CellType.STRING) {
                    cellData.add( cell.getStringCellValue() );
                }else if(cell.getCellType()== CellType.NUMERIC) {
                    cellData.add( cell.getNumericCellValue() );
                }else if(cell.getCellType()== CellType.BOOLEAN) {
                    cellData.add( cell.getBooleanCellValue() );
                }

            }
            data.add(cellData.toArray());
        }
        return data;
    }
}
