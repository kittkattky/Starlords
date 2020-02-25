package Utilities;

/**
 * ExcelUtil class for facilitating data entry methods between the UI client and Excel.
 * Authors: Preston Williamson
 * Last Updated Date: 22-FEB-2020
 */

import java.io.*;
import java.security.GeneralSecurityException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.crypt.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.*;

/**
 *
 * @author preston.williamson
 */
public class ExcelUtil {
    protected XSSFWorkbook workbook;
    protected String filePath;
    protected XSSFSheet activeSheet;
    protected boolean secure;
    protected final int FIRSTROW = 0;
    protected File fileRef;
    protected final String FILE_EXT = "xlsx";
    private final String PASSWORD = "pass";

    public ExcelUtil () {
        this.workbook = new XSSFWorkbook ();
    }

    /**
     * createWorkbook: a method dedicated to creating a new Excel workbook.
     * @param _filePath: String representation of the file path in which the workbook will be stored.
     * @param _setSecure: boolean value, indicating whether the workbook will be equipped with password protection (set to true), or as-is (set to false).
     * @return boolean: true if workbook was successfully created;otherwise, return false.
     * @throws Exception if the provided file path is not for a file of extension type "xlsx".
     */
    public boolean createWorkbook (String _filePath, boolean _setSecure) throws Exception {
        this.secure = _setSecure;
        this.filePath = _filePath;

        //verify file extension
        String[] path = this.filePath.split("\\.");
        String ext = path [path.length - 1];
        if (ext.compareToIgnoreCase(this.FILE_EXT) != 0)
            throw new Exception ( "[" + ExcelUtil.class.getName() + "] only supports file type [" + this.FILE_EXT + "]");

        File file = new File (this.filePath);
        boolean ret = file.exists();

        //do not proceed if the file already exists.
        if (!ret) {
            //save workbook to file system.
            this.saveWorkbook();
            return file.exists();
        }
        else {
            //open the workbook if found to exist.
            this.openWorkbook(_filePath, _setSecure);
        }

        //return value indicates if the file exists.
        return ret;
    }

    /**
     * openWorkbook: a method dedicated to opening an existing Excel workbook.
     * @param _filePath: String representation of the file path in which the workbook will be stored.
     * @param _setSecure: boolean value, indicating whether the workbook is expected to be equipped with password protection (set to true), or as-is (set to false).
     * @throws IOException
     * @throws InvalidFormatException
     */
    public void openWorkbook (String _filePath, boolean _setSecure) throws IOException, InvalidFormatException {
        this.fileRef = new File (_filePath);

        //create an input stream to record modifications performed on the workbook.
        FileInputStream inStream = new FileInputStream (this.fileRef);
        XSSFWorkbook book;
        if (_setSecure)
            book = (XSSFWorkbook) XSSFWorkbookFactory.create(inStream, this.PASSWORD);
        else
            book = (XSSFWorkbook) XSSFWorkbookFactory.create(inStream);

        this.workbook = book;
    }

    /**
     * createWorksheet: a method dedicated to creating a new worksheet within an existing Excel workbook.
     * @param _sheetName: String representation of the worksheet's name.
     * @param _headers: Preliminary headers that will be created within the worksheet.
     * @return boolean: true if the worksheet exists, otherwise false.
     * @throws IOException
     * @throws FileNotFoundException
     * @throws GeneralSecurityException
     */
    public boolean createWorksheet (String _sheetName, String [] _headers) throws IOException, FileNotFoundException, GeneralSecurityException {
        //verify worksheet does not exist.
        int index = this.workbook.getSheetIndex(_sheetName);
        if (index == -1) {
            //create worksheet and create the first row
            this.activeSheet = this.workbook.createSheet(_sheetName);
            XSSFRow row = this.activeSheet.createRow(this.FIRSTROW);
            
            //set header cell values.
            for (int i = 0; i < _headers.length;i ++) {
                XSSFCell cell = row.createCell(i);
                cell.setCellValue(_headers [i]);
            }
            
            //save workbook.
            this.saveWorkbook();
        }
        else
            this.setActiveSheet(_sheetName);
        
        //return end result.
        return (this.activeSheet != null);
    }

    /**
     * saveWorkbook: a method dedicated to preserving changes made in an Excel workbook.
     * @throws FileNotFoundException
     * @throws IOException
     * @throws GeneralSecurityException 
     */
    public void saveWorkbook () throws FileNotFoundException, IOException, GeneralSecurityException {

        //special logic required for setting password protection on workbook.
        if (this.secure) {
            //define encryption
            POIFSFileSystem fileSystem = new POIFSFileSystem();
            EncryptionInfo info = new EncryptionInfo(EncryptionMode.standard);
            Encryptor crypt = info.getEncryptor();
            crypt.confirmPassword(this.PASSWORD);
            
            //set encryption
            OutputStream outStream = crypt.getDataStream(fileSystem);
            this.workbook.write(outStream);
            outStream.close();

            //save encryption to file system.
            FileOutputStream outFileStream = new FileOutputStream(this.filePath);
            fileSystem.writeFilesystem(outFileStream);
            outFileStream.close();
        }
        else {
            //write output stream to excel file.
            FileOutputStream outFileStream = new FileOutputStream(this.filePath);
            this.workbook.write(outFileStream);
            outFileStream.close();
        }
    }
    
    /**
     * closeWorkbook: a method dedicated to closing an existing instance of an opened Excel workbook.
     * @throws IOException
     * @throws FileNotFoundException
     * @throws GeneralSecurityException 
     */
    public void closeWorkbook () throws IOException, FileNotFoundException, GeneralSecurityException {
        this.saveWorkbook();
        this.activeSheet = null;
        this.workbook.close();
    }
    
    //================= GETTERS ===============    
    public int getColumnIndexByText (String _columnName) {
        XSSFRow row = this.activeSheet.getRow(this.FIRSTROW);
        int columnCount = row.getLastCellNum();
        int ret = -1;
        
        for (int i = 0; i < columnCount; i++) {
            String value = row.getCell(i).getStringCellValue();
            if (value.compareToIgnoreCase(_columnName) == 0)
                ret = i;
        }
        
        return ret;
    }    
    
    /**
     * getCellData: a helper method designed to retrieve a String representation of the cell value within a row-column name pair.
     * @param _sheetName: name of the excel worksheet in which the extraction will occur.
     * @param _row: numerical representation of the row number.
     * @param _columnName: String representation of the desired column.
     * @return String: string value contained within the row-value pair.
     * @throws Exception 
     */
    public String getCellData (String _sheetName, int _row, String _columnName) throws Exception {
        this.setActiveSheet(_sheetName);
        int column = this.getColumnIndexByText(_columnName);        
        return this.getCellData (_row, column);
    }    
    
    /**
     * getCellData: a helper method designed to retrieve a String representation of the cell value within a row-column pair.
     * @param _sheetName: name of the excel worksheet in which the extraction will occur.
     * @param _row: numerical representation of the row number.
     * @param _column: numerical representation of the column number.
     * @return String: string value contained within the row-value pair.
     * @throws Exception 
     */
    public String getCellData (String _sheetName, int _row, int _column) throws Exception {
        this.setActiveSheet(_sheetName);
        return this.getCellData (_row, _column);

    }

    /**
     * getCellData: a helper method designed to retrieve a String representation of the cell value within a row-column name pair from the active sheet.
     * @param _row: numerical representation of the row number.
     * @param _columnName: String representation of the column.
     * @return String: string value contained within the row-value pair.
     * @throws Exception 
     */
    public String getCellData (int _row, String _columnName) throws Exception {
        int column = this.getColumnIndexByText(_columnName);
        return this.getCellData(_row, column);
    }
    
    /**
     * getCellData: a helper method designed to retrieve a String representation of the cell value within a row-column pair from the active sheet.
     * @param _row: numerical representation of the row number.
     * @param _column: numerical representation of the row number.
     * @return String: string value contained within the row-value pair.
     * @throws Exception 
     */
    public String getCellData (int _row, int _column) throws Exception {
        XSSFRow row;

        //throw exception if the intended row is higher than the last populated row.
        if (_row > this.activeSheet.getLastRowNum())
            throw new Exception ("");

        row = this.activeSheet.getRow(_row);

        //throw exception if the intended column is higher than the last populated column.
        if (_column > row.getLastCellNum())
           throw new Exception ("");

        //return value within cell.
        return row.getCell(_column).getStringCellValue();
    }

    //================= SETTERS ===============        
    
    /**
     * setActiveSheet: a method dedicated to storing active sheet field for intuitive 
     * @param _sheetName 
     */
    public void setActiveSheet (String _sheetName) {
        //verify intended active sheet is not already set.
        if (this.activeSheet == null || this.activeSheet.getSheetName().compareToIgnoreCase(_sheetName) != 0)
            this.activeSheet = this.workbook.getSheet (_sheetName);
    }
    
    /**
     * setCellData: a helper method designed to set the cell value within a row-column pair to specified String.
     * @param _sheetName: name of the excel worksheet in which the entry will occur.
     * @param _row: numerical representation of the row number.
     * @param _column: numerical representation of the column number.
     * @param _val: String representation of the desired value.
     */
    public void setCellData (String _sheetName, int _row, int _column, String _val) {
        this.setActiveSheet(_sheetName);
        this.setCellData(_row, _column, _val);
    }
    
    /**
     * setCellData: a helper method designed to set the cell value within a row-column name pair to specified String.
     * @param _sheetName: name of the excel worksheet in which the entry will occur.
     * @param _row: numerical representation of the row number.
     * @param _columnName: String representation of the column name.
     * @param _val: String representation of the desired value.
     */
    public void setCellData (String _sheetName, int _row, String _columnName, String _val) {
        this.setActiveSheet(_sheetName);
        int column = this.getColumnIndexByText(_columnName);
        this.setCellData(_row,  column, _val);
    }    

    /**
     * setCellData: a helper method designed to set the cell value within a row-column pair to specified String in the active sheet.
     * @param _row: numerical representation of the row number.
     * @param _column: numerical representation of the row number.
     * @param _val: String representation of the desired value.
     */    
    public void setCellData (int _row, int _column, String _val) {
        XSSFCell cell;
        XSSFRow row;

        //create new row if specified row is higher than last populated row.
        if (_row > this.activeSheet.getLastRowNum()) {
            row = this.activeSheet.createRow(_row);
            cell = row.createCell(_column);
        }
        else {
            //create new cell if specified column is higher than last populated column.
            row = this.activeSheet.getRow(_row);
            if (_column > row.getLastCellNum())
                cell = row.createCell(_column);
            else
                cell = row.getCell(_column);
        }
        
        //perform data entry
        cell.setCellValue(_val);
    }
}