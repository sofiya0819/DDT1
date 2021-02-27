package PageFactory;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ForgottenPasswordPageFactory {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(how = How.XPATH, using = "//div[@class='box']")
    WebElement ForgotYourPassBox;
    @FindBy(how = How.XPATH, using = "//div[@class='form-group']/input[@id='email']")
    WebElement emailField;
    @FindBy(how = How.XPATH, using = "//button[@class='btn btn-default button button-medium']")
    WebElement retrievePasswordBtn;
    @FindBy(how = How.XPATH, using = "//div[@class='alert alert-danger']/ol/li[contains(text(),'There is no account registered for this email address.')]")
    WebElement alertMsgNoRegisteredAccount;
    @FindBy(how = How.XPATH, using = "//div[@class='box']/p[contains(text(),'A confirmation email has been sent to your address:')]")
    WebElement confirmationMsgValidAccount;

    public ForgottenPasswordPageFactory(WebDriver driver){
        //ToDo: set implicit wait
        if(driver == null){
            throw new NullPointerException("Forgotten Password Page Factory Driver instance is null");
        }
        this.driver = driver;
        //init all elements defined by the annotation @FindBy
        PageFactory.initElements(driver,this);

    }

    public void RetrievePasswordWithInvalidEmail(String email){
        String expectedText = "There is no account registered for this email address.";
        if(ForgotYourPassBox.isDisplayed()){
           emailField.sendKeys(email);
            retrievePasswordBtn.click();
            Assert.assertEquals(expectedText, alertMsgNoRegisteredAccount.getText());
            System.out.println("Retrieve Password With Invalid Email passed");
        }
    }

    //    ????????????????????????????????????????? --msg with email--better way ??????????????????????????????
    public void RetrievePasswordWithValidEmail(String email){
        String expectedText = "A confirmation email has been sent to your address: " + email;
        if(ForgotYourPassBox.isDisplayed()){
            emailField.sendKeys(email);
            retrievePasswordBtn.click();
            Assert.assertEquals(expectedText, confirmationMsgValidAccount.getText());
            System.out.println("Retrieve Password With Valid Email passed");
        }else{
            //???? necessary or not
            throw new RuntimeException("Not retrieving password (valid email)");
        }
    }
    public void closePage(){
        driver.close();
    }

}
