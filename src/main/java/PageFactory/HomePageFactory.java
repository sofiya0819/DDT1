package PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class HomePageFactory {
    //we do not use implicit wait, but only explicit; The factory is taking care when the site is loaded
    private WebDriver driver;
    private String url ="http://automationpractice.com/index.php";

    @FindBy(className = "login")
    WebElement signInBtn;

    @FindBy(how = How.XPATH, using = "//div[@id='contact-link']/a[@title='Contact Us']")
    WebElement contactUsBtn;


    public HomePageFactory(WebDriver driver){
        //ToDo: set implicit wait
        if(driver == null){
            throw new NullPointerException("Home page Factory Driver instance is null");
        }
        this.driver = driver;
        //init all elements defined by the annotation @FindBy
        PageFactory.initElements(driver,this);
    }

    public void navigateTo(){
        driver.manage().window().maximize();
        driver.get(url);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }

    public LoginPageFactory openSignInPage(){
        signInBtn.click();
        return new LoginPageFactory(driver);
    }

    public void closePage(){
        driver.close();
    }
}
