package PageFactory;

import AutoFrameWork.Validation;
import PageObjectModel.RegistrationPage;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPageFactory {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id = "login_form")  //   "//form[@id='login_form']"
    WebElement loginForm;
    @FindBy(id = "email")
    WebElement emailField;
    @FindBy(id = "passwd")
    WebElement passwordField;
    @FindBy(id = "SubmitLogin")
    WebElement submitLoginBtn;
    @FindBy(how = How.XPATH, using = "//div[@class='header_user_info']/a[@class='account']")
    WebElement accountInfo;
    @FindBy(how = How.XPATH, using = "//div[@class='header_user_info']/a[@class='logout']")
    WebElement logOutBtn;
    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Forgot your password?')]")
    WebElement passForgottenBtn;
    @FindBy(id = "create-account_form")
    WebElement createAccountForm;
    @FindBy(id = "email_create")
    WebElement emailCreateField;
    @FindBy(id = "SubmitCreate")
    WebElement submitCreateAccountBtn;

    Validation validation;

    public LoginPageFactory(WebDriver driver){
        //ToDo: set implicit wait
        if(driver == null){
            throw new NullPointerException("Login page Factory Driver instance is null");
        }
        this.driver = driver;
        //init all elements defined by the annotation @FindBy
        PageFactory.initElements(driver,this);
    }

    public void login(String email, String password) {
        if (validateEmail(email) && password.length() > 5 && loginForm.isDisplayed()) {
                emailField.sendKeys(email);
                passwordField.sendKeys(password);
                submitLoginBtn.click();
                System.out.println("Login passed!");
        } else {
            System.out.println("wrong email/password");
        }
    }

    private boolean validateEmail(String em){
        return true;
    }


    public void checkAccountInfoByText(String expectedText){
        Assert.assertEquals(expectedText,accountInfo.getText());
        System.out.println("Checked passed!");
    }

    public void logOut(){
        logOutBtn.click();
        if(loginForm.isDisplayed()){
            System.out.println("Logout passed!");
        }else{
            throw new RuntimeException("Not logout from login page");
        }
    }

    public ForgottenPasswordPageFactory openForgottenPasswordPage(){
        passForgottenBtn.click();
        return new ForgottenPasswordPageFactory(driver);
    }

//    public void createAccount(String email){
//        emailCreateField.sendKeys(email);
//        System.out.println("createAccount passed");
//    }
//
//    public RegistrationPageFactory openRegistrationPage(){
//        if(createAccountForm.isDisplayed()){
//            submitCreateAccountBtn.click();
//            return new RegistrationPageFactory(driver);
//        }else{
//            throw new RuntimeException("Not creating an account from login page");
//        }
//    }

    public RegistrationPageFactory createAccount(String email){
        if(createAccountForm.isDisplayed()){
            emailCreateField.sendKeys(email);
            submitCreateAccountBtn.click();
            return new RegistrationPageFactory(driver);
        }else{
            throw new RuntimeException("Not creating an account from login page");
        }
    }


}

