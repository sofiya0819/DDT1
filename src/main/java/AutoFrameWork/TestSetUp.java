package AutoFrameWork;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class TestSetUp {
    private static final String CHROME_DRIVER_PATH = "C://WebDriver//bin//chromedriver.exe";
    private static final String INIT_PATH = "C://WebDriver//Book3.xlsx";
//    private static final String INIT_PATH = "C://WebDriver//testSetUp.xls";

    private static final String CHROME = "CHROME";
    private static final String FIREFOX = "FIREFOX";

    public WebDriver driver;
    private String username;
    private String password;
    private String browserName;
    private String mainURL;
    private String getUsernameLoggedInfo;

    public TestSetUp(){
        System.setProperty("webdriver.chrome.driver",CHROME_DRIVER_PATH);
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getBrowserName() {
        return browserName;
    }

    public String getMainURL() {
        return mainURL;
    }

    public String getGetUsernameLoggedInfo() {
        return getUsernameLoggedInfo;
    }

    private void readDataFromExcelFile() throws IOException {
        String className = this.getClass().getSimpleName();
        File file = new File(INIT_PATH);
        FileInputStream inputStream = new FileInputStream(file);
//2003 HSSF .xls ; 2007 XSSF .xls .xlsx
        XSSFWorkbook wb = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = wb.getSheetAt(0);
//        HSSFWorkbook wb = new HSSFWorkbook(inputStream);
//        HSSFSheet sheet = wb.getSheetAt(0);
        Iterator<Row> it = sheet.iterator();
        while (it.hasNext()) {
            Row row = it.next();
            String cel = row.getCell(0).getStringCellValue();
            if (row.getCell(0).getStringCellValue().equals(className)) {
                this.username = row.getCell(1).getStringCellValue();
                this.password = row.getCell(2).getStringCellValue();
                this.browserName = row.getCell(3).getStringCellValue();
                this.mainURL = row.getCell(4).getStringCellValue();
//                this.usernameLoggedInInfo = row.getCell(4).getStringCellValue();
            }
        }
        if(username == null || password == null || mainURL == null){
            throw new InvalidArgumentException("Pass,Username or Url is null");
        }
    }

    private void setUpWebDriver(){
//        if(this.browserName.toUpperCase().equals(CHROME)){
        this.driver = new ChromeDriver();
//        }else if(this.browserName.toUpperCase().equals(CHROME)){
//            this.driver = new FirefoxDriver();
//        }else{
//            this.driver = new ChromeDriver();
//        }
    }

    public void mainSetUp() throws IOException{
        readDataFromExcelFile();
        setUpWebDriver();
        this.driver.manage().window().maximize();
    }


    public void mainTestTearDown(){
        this.driver.quit();
    }

}
