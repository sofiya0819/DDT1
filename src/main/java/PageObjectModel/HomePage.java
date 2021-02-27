package PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;
    //private String url = "http://automationpractice.com/index.php";

    By sighInBtn = By.className("login");
    By contactUsBtn = By.xpath("//div[@id='contact-link']/a[@title='Contact Us']");

    //constructor; must have driver and wait;
    public HomePage(WebDriver dr){
        this.driver = dr;
        wait = new WebDriverWait(dr,10);
    }

    public void navigateTo(String url){
        driver.manage().window().maximize();
        driver.get(url);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }

    public LoginPage openSignInPage(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(sighInBtn)).click();
        return new LoginPage(driver);
    }

//    public ContactUsPage openContactUsPage(){
//        wait.until(ExpectedConditions.visibilityOfElementLocated(contactUsBtn)).click();
//        return new ContactUsPage(driver);
//    }

    public void closePage(){
        driver.close();
    }

}
