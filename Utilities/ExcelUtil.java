package Utilities;

import java.io.*;
import org.apache.poi.xssf.usermodel.*;

/**
 *
 * @author preston.williamson
 */
public class ExcelUtil {
    protected XSSFWorkbook workbook;
    
    public ExcelUtil () {
        this.workbook = new XSSFWorkbook ();
    }
    
    public XSSFWorkbook createWorkbook (String _filePath) throws Exception {
        File file = new File (_filePath);
        String[] path = _filePath.split(_filePath);
        FileOutputStream out = new FileOutputStream (file);
        this.workbook.write (out);
        
        int count = path.length - 1;
        
        String ext = path [count];
        
        if (!ext.equals(".xlsx"))
            throw new Exception ("Utilty only supports file types: .xlsx");
        
        return this.workbook;
    }
    
    public XSSFSheet createWorksheet (String _sheetName) {
        XSSFSheet sheet = this.workbook.createSheet(_sheetName);
        XSSFRow row = sheet.createRow(0);
        XSSFCell cell = row.createCell(0);
        cell.setCellValue("testing123");
        return sheet;
    }
    
    public long getRowCount (XSSFSheet _sheet) {
        return _sheet.getLastRowNum();
    }
}