package PageObjectModel;

import AutoFrameWork.Utilities.Log;
import AutoFrameWork.Utilities.Screenshot;
import AutoFrameWork.Validation;
import jdk.swing.interop.SwingInterOpUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

public class LoginPage {
    WebDriver driver;
    WebDriverWait wait;
    String email;

    By loginForm = By.id("login_form");  //   "//form[@id='login_form']"
    By emailField = By.id("email");
    By passwordField = By.id("passwd");
    By submitBtn = By.id("SubmitLogin");
    By accountInfo = By.xpath("//div[@class='header_user_info']/a[@class='account']");
    By logOutBtn = By.xpath("//div[@class='header_user_info']/a[@class='logout']");
//    By passForgottenBtn = By.xpath("//form[@id='login_form']/div/p[1]/a[contains(text(),'Forgot your password?')]");
    By passForgottenBtn = By.xpath("//a[contains(text(),'Forgot your password?')]");

    By createAccountForm = By.id("create-account_form");
    By emailCreateField = By.id("email_create");
    By submitCreateAccountBtn = By.id("SubmitCreate");

    By homeLink = By.linkText("Home");

    Validation validate;

    public LoginPage(WebDriver dr, String email){
        driver = dr;
        wait = new WebDriverWait(driver,10);
        this.email = email;
    }

    public void login(String email, String password) throws IOException {
        Log.info("Login with "+ email + " and password "+password);
        validate = new Validation(email,password);
//        ?????????????????????????????????????---sth wrong ---????????????????????????????
        if(validate.validateEmail(email) && validate.validatePassword(password)){
//        if(validateEmail(email) && password.length() > 5){    //this works !!!!!!!
            Screenshot.takeWebElementScreenShot(wait.until(ExpectedConditions.visibilityOfElementLocated(loginForm)),"loginForm");
            wait.until(ExpectedConditions.visibilityOfElementLocated(loginForm));
            wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
            wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
            wait.until(ExpectedConditions.visibilityOfElementLocated(submitBtn)).click();
            System.out.println("Login passed");
            Log.info("Login passed");
        }else{
            System.out.println("Wrong email or password input");
            Log.error("Wrong email or password input");
            throw new IllegalArgumentException("Wrong email or password input");
        }
    }

    public boolean validateEmail(String email){
        return true;
    }

    public void checkAccountInfoByText(String expectedText){
        WebElement check = wait.until(ExpectedConditions.visibilityOfElementLocated(accountInfo));
        Assert.assertEquals(expectedText,check.getText());
        System.out.println("Check Account Info passed");
    }

    public ForgottenPasswordPage openForgottenPasswordPage(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(passForgottenBtn)).click();
        return new ForgottenPasswordPage(driver);

    }

    public void logOut(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(logOutBtn)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginForm));
        System.out.println("Logout passed");
    }

//    public void createAccount(String email){
//        wait.until(ExpectedConditions.visibilityOfElementLocated(createAccountForm));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(emailCreateField)).sendKeys(email);
//        System.out.println("CreateAccount passed");
//    }
//
//    public RegistrationPage openRegistrationPage(String email){
//        wait.until(ExpectedConditions.visibilityOfElementLocated(submitCreateAccountBtn)).click();
//        return new RegistrationPage(driver,email);
//    }

    public RegistrationPage createAccount(String email){
        wait.until(ExpectedConditions.visibilityOfElementLocated(createAccountForm));
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailCreateField)).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOfElementLocated(submitCreateAccountBtn)).click();
        return new RegistrationPage(driver,email);
    }

    public void goToHomePage(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(homeLink)).click();
    }

    public void closePage(){
        driver.close();
    }

}
