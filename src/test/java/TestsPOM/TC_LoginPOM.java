package TestsPOM;

import PageObjectModel.CreateAccountPage;
import PageObjectModel.ForgottenPasswordPage;
import PageObjectModel.HomePage;
import PageObjectModel.LoginPage;
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


    public void login(String email, String password) throws IOException {
        HomePage homePage = new HomePage(driver);
        homePage.navigateTo(url);
        LoginPage loginPage = homePage.openSignInPage();
        loginPage.login(email,password);
        loginPage.checkAccountInfoByText("Test Test");
        loginPage.logOut();
        homePage.closePage();
    }

    public void createNewAccount(String email){
        HomePage homePage = new HomePage(driver);
        homePage.navigateTo(url);
        LoginPage loginPage = homePage.openSignInPage();
        loginPage.createAccount(email);
        CreateAccountPage createAccountPage = loginPage.openCreateAccountPage();

    }

    @Test
    public void SuccessfulLoginAndLogOut() {
        try {
            login("test123@abv.bg","123456");
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
        HomePage homePage = new HomePage(driver);
        homePage.navigateTo(url);
        LoginPage loginPage = homePage.openSignInPage();
        ForgottenPasswordPage forgottenPasswordPage = loginPage.openForgottenPasswordPage();
        forgottenPasswordPage.RetrievePasswordWithInvalidEmail("s1234@gmail.com");
        forgottenPasswordPage.closePage();
    }


    @Test
    public void forgottenPasswordWithAnExistingEmail(){
        HomePage homePage = new HomePage(driver);
        homePage.navigateTo(url);
        LoginPage loginPage = homePage.openSignInPage();
        ForgottenPasswordPage forgottenPasswordPage = loginPage.openForgottenPasswordPage();
        forgottenPasswordPage.RetrievePasswordWithValidEmail("test123@abv.bg");
        forgottenPasswordPage.closePage();
    }





}
