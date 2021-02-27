package PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateAccountPageFactory {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(className = "submitAccount")
    WebElement submitBtn;

    public CreateAccountPageFactory(WebDriver dr){
        //ToDo: set implicit wait
        if(driver == null){
            throw new NullPointerException("Create Account Page Factory Driver instance is null");
        }
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void submitNewAccountForm(){
        submitBtn.click();
    }
}
