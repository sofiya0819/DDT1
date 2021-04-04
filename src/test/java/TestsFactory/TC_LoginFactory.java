package TestsFactory;

import PageFactory.ForgottenPasswordPageFactory;
import PageFactory.HomePageFactory;
import PageFactory.LoginPageFactory;
import PageFactory.RegistrationPageFactory;
import PageObjectModel.RegistrationPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TC_LoginFactory {
    WebDriver driver;
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


    public void login(String email, String pass) {
        //init elements here or in the constructor
//        PageFactory.initElements(driver, HomePageFactory.class);
//        PageFactory.initElements(driver, LoginPageFactory.class);
        HomePageFactory homePageFactory = new HomePageFactory(driver);
        homePageFactory.navigateTo();
        LoginPageFactory loginPageFactory = homePageFactory.openSignInPage();
        loginPageFactory.login(email,pass);
        loginPageFactory.checkAccountInfoByText("Test Test");
        loginPageFactory.logOut();
        homePageFactory.closePage();
    }


    public void createNewAccount(String email){
        HomePageFactory homePageFactory = new HomePageFactory(driver);
        homePageFactory.navigateTo();
        LoginPageFactory loginPageFactory = homePageFactory.openSignInPage();
//        loginPageFactory.createAccount(email);
//        RegistrationPageFactory registrationPage = loginPageFactory.openRegistrationPage();
        RegistrationPageFactory registrationPage = loginPageFactory.createAccount(email);
        registrationPage.closePage();

    }

    @Test
    public void SuccessfulLoginAndLogOut(){
        login("test123@abv.bg","123456");
    }

    @Test
    public void SuccessfulGoToCreateNewAccount(){
        createNewAccount("s12345@abv.bg");
    }

    @Test
    public void forgottenPasswordWithNonExistingEmail(){
        HomePageFactory homePageFactory = new HomePageFactory(driver);
        homePageFactory.navigateTo();
        LoginPageFactory loginPageFactory = homePageFactory.openSignInPage();
        ForgottenPasswordPageFactory forgottenPasswordPage = loginPageFactory.openForgottenPasswordPage();
        forgottenPasswordPage.RetrievePasswordWithInvalidEmail("s1234@gmail.com");
        homePageFactory.closePage();
    }


    @Test
    public void forgottenPasswordWithAnExistingEmail(){
        HomePageFactory homePageFactory = new HomePageFactory(driver);
        homePageFactory.navigateTo();
        LoginPageFactory loginPageFactory = homePageFactory.openSignInPage();
        ForgottenPasswordPageFactory forgottenPasswordPage = loginPageFactory.openForgottenPasswordPage();
        forgottenPasswordPage.RetrievePasswordWithValidEmail("test123@abv.bg");
        forgottenPasswordPage.closePage();
    }

}
