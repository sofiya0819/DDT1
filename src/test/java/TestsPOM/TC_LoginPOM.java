package TestsPOM;

import PageObjectModel.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;

public class TC_LoginPOM {
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


    public void login(String email, String password, String userInfo) throws IOException {
        HomePage homePage = new HomePage(driver,"");
        homePage.navigateTo(url);
        LoginPage loginPage = homePage.openSignInPage();
        loginPage.login(email,password);
        loginPage.checkAccountInfoByText(userInfo);
        loginPage.logOut();
        homePage.closePage();
    }

    public void createNewAccount(String email){
        HomePage homePage = new HomePage(driver,"");
        homePage.navigateTo(url);
        LoginPage loginPage = homePage.openSignInPage();
//        loginPage.createAccount(email);
//        RegistrationPage registrationPage = loginPage.openRegistrationPage(email);
        RegistrationPage registrationPage = loginPage.createAccount(email);
        homePage.closePage();
    }

    @Test
    public void SuccessfulLoginAndLogOut() {
        String email = "test123@abv.bg";
        String pass = "123456";
        String userInfo = "Test Test";
        try {
            login(email,pass,userInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void SuccessfulGoToCreateNewAccount(){
        createNewAccount("s1234@abv.bg");
    }

    @Test
    public void forgottenPasswordWithNonExistingEmail(){
        String email = "s1234@gmail.com";
        HomePage homePage = new HomePage(driver,"");
        homePage.navigateTo(url);
        LoginPage loginPage = homePage.openSignInPage();
        ForgottenPasswordPage forgottenPasswordPage = loginPage.openForgottenPasswordPage();
        forgottenPasswordPage.RetrievePasswordWithInvalidEmail(email);
        forgottenPasswordPage.closePage();

    }


    @Test
    public void forgottenPasswordWithAnExistingEmail(){
        String email = "test123@abv.bg";
        HomePage homePage = new HomePage(driver,"");
        homePage.navigateTo(url);
        LoginPage loginPage = homePage.openSignInPage();
        ForgottenPasswordPage forgottenPasswordPage = loginPage.openForgottenPasswordPage();
        forgottenPasswordPage.RetrievePasswordWithValidEmail(email);
        forgottenPasswordPage.closePage();
    }





}
