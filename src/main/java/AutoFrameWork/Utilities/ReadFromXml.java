package AutoFrameWork.Utilities;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.InvalidArgumentException;

import java.io.*;
import java.util.*;

public class ReadFromXml {
    public static List<String> readFromXmlFile(String path, String email) throws IOException {
        List<String> result = new ArrayList<>();
        File file = new File(path);
        FileInputStream inputStream = new FileInputStream(file);

        XSSFWorkbook wb = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = wb.getSheetAt(0); //take the first sheet

        int rows = sheet.getPhysicalNumberOfRows();
        int cols = sheet.getRow(0).getPhysicalNumberOfCells();

        Iterator<Row> iterator = sheet.iterator();
        while(iterator.hasNext()){
            Row row = iterator.next();
            if(row.getCell(0).getStringCellValue().equals(email)){
                for(int i = 1; i <= cols; i++){
                    try {
                        result.add(row.getCell(i).getStringCellValue());
                    }catch(RuntimeException e){
                        int num = (int)row.getCell(i).getNumericCellValue();
                        result.add(String.valueOf(num));
                    }
                }
            }
        }
        if(result.size()==0){
            throw new InvalidObjectException("Personal details are null");
        }
        return result;
    }

/*
Need to solve the issue - returns only one array; how to concatenate arrays from String and Double?
https://tutorial.techaltum.com/read-excel-data-with-Apache-POI.html
 */
    public static Collection readXml(String path) throws IOException {
        File file = new File(path);
        FileInputStream inputStream = new FileInputStream(file);

        XSSFWorkbook wb = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = wb.getSheetAt(0); //take the first sheet

        int rows = sheet.getPhysicalNumberOfRows();
        int cols = sheet.getRow(0).getPhysicalNumberOfCells();

        String[][] totalObj = new String[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                try {
                    totalObj[i][j] = sheet.getRow(i).getCell(j).getStringCellValue();
                }catch(RuntimeException e){
                    int num = (int)sheet.getRow(i).getCell(j).getNumericCellValue();
                    totalObj[i][j] = Integer.toString(num);
                }
            }
        }
        return Arrays.asList(totalObj.clone());
    }


//    //Section 4: This method will convert object of type cell into String
//    public String cellToString(XSSFCell cell){//Declare a method "cellToString()" which will convert an object of type cell into string
//        CellType type = cell.getCellType();
//        // This method "getCellType()" will return an integer which is 0-5 and on the basis of this integer value we will use SWITCH
//
//        Object result; // Its an temporary object which will hold value in switch statement
//
//        switch(type){
//            case XSSFCell.CELL_TYPE_NUMERIC: // If a cell contain numeric value then it will return 0
//                result = cell.getNumericCellValue();
//                System.out.println("Value of type is : " +type);
//                break;
//
//            case XSSFCell.CELL_TYPE_STRING: // If a cell string value then it will return 1
//                result = cell.getStringCellValue();
//                break;
//
//            case XSSFCell.CELL_TYPE_FORMULA:  // If a cell contain formula then it will return 2
//                System.out.println("Can not eveulate formila in JAVA");
//                throw new RuntimeException("Can not eveulate formila in JAVA");
//
//            case XSSFCell.CELL_TYPE_BLANK: // If a cell contain blank value then it will return 3
//                result = "";
//                break;
//
//            case XSSFCell.CELL_TYPE_BOOLEAN: // If a cell contain boolean value then it will return 4
//                result = cell.getBooleanCellValue();
//                break;
//
//            case XSSFCell.CELL_TYPE_ERROR:// If a cell contain error then it will return 5
//                System.out.println("Can not eveulate formila in JAVA");
//                throw new RuntimeException("This cell has an error");
//
//            default:
//                throw new RuntimeException("We dont support this cell type : " +type);
//        }
//        return result.toString();   //Here "toString()" method will convert result object to string; Here question may arise i.e why convert value into string...? This is so because we already have an array[xData] in which we will put this value and that is declared as STRING.
//
//    }
//}
}
