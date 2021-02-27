package PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateAccountPage {
    WebDriver driver;
    WebDriverWait wait;

    By submitBtn = By.id("submitAccount");

    public CreateAccountPage(WebDriver dr){
        driver = dr;
        wait = new WebDriverWait(driver,10);
    }

    public void submitNewAccountForm(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(submitBtn)).click();
    }
}
