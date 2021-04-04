package TestsPOM;

import PageObjectModel.ContactUsPage;
import PageObjectModel.HomePage;
import PageObjectModel.LoginPage;
import PageObjectModel.RegistrationPage;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class TC_ContactUsPOM {
    WebDriver driver;
    String url = "http://automationpractice.com/index.php";

    @Before
    public void SetUp(){
        String driverExecutablePath = "C://WebDriver//bin//chromedriver.exe";
        System.getProperty("webdriver.chrome.driver",driverExecutablePath);
        driver = new ChromeDriver();
    }

    @After
    public void tearDown(){
        driver.quit();
    }


    @Test
    public void SendUsMessageTest(){
        String contactEmail = "s2@gmail.com";
        String orderId = "15894";
        String userMessage = "This is a test. I will send a test to the custom services.";
        String subject = "Customer service";
        HomePage homePage = new HomePage(driver,"");
        homePage.navigateTo(url);
        ContactUsPage contactPage = homePage.openContactUsPage();
        contactPage.contactUs(contactEmail,orderId,userMessage,subject);
        contactPage.closePage();
    }


}
