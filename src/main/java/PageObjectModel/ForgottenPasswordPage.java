package PageObjectModel;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ForgottenPasswordPage {
    WebDriver driver;
    WebDriverWait wait;

    By ForgotYourPassBox = By.xpath("//div[@class='box']");
//    By emailField = By.id("email");

    By emailField = By.xpath("//div[@class='form-group']/input[@id='email']");
    By retrievePasswordBtn = By.xpath("//button[@class='btn btn-default button button-medium']");
    By alertMsgNoRegisteredAccount = By.xpath("//div[@class='alert alert-danger']/ol/li[contains(text(),'There is no account registered for this email address.')]");
    By confirmationMsgValidAccount = By.xpath("//div[@class='box']/p[contains(text(),'A confirmation email has been sent to your address:')]");


    public ForgottenPasswordPage(WebDriver dr){
        driver = dr;
        wait = new WebDriverWait(driver,10);
    }


    public void RetrievePasswordWithInvalidEmail(String email){
        String expectedText = "There is no account registered for this email address.";
        wait.until(ExpectedConditions.visibilityOfElementLocated(ForgotYourPassBox));
        System.out.println("ForgotYourPassBox passed");
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOfElementLocated(retrievePasswordBtn)).click();
        WebElement alertMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(alertMsgNoRegisteredAccount));
        Assert.assertEquals(expectedText, alertMsg.getText());
        System.out.println("Retrieve Password With Invalid Email passed");
    }

//    ????????????????????????????????????????? --msg with email--better way ??????????????????????????????
    public void RetrievePasswordWithValidEmail(String email){
        String expectedText = "A confirmation email has been sent to your address: " + email;
        wait.until(ExpectedConditions.visibilityOfElementLocated(ForgotYourPassBox));
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOfElementLocated(retrievePasswordBtn)).click();
        WebElement confirmationMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationMsgValidAccount));
        Assert.assertEquals(expectedText, confirmationMsg.getText());
        System.out.println("Retrieve Password With Valid Email passed");
    }

    public void closePage(){
        driver.close();
    }
}
