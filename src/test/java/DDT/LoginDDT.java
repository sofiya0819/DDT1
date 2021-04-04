package DDT;

import AutoFrameWork.MainTestSetUp;
import AutoFrameWork.Utilities.Log;
import AutoFrameWork.Utilities.Screenshot;
import PageObjectModel.HomePage;
import PageObjectModel.LoginPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LoginDDT extends MainTestSetUp {
    @Before
    public void SetUp()  {
        try {
            this.mainSetUp();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @After
    public void tearDown(){
        this.mainTestTearDown();
    }

    @Ignore
    @Test
    public void forTheTest(){
        System.out.println(this.getUsername());
    }

    @Test
    public void simpleLogin() throws IOException {
        try{
            Log.startTestDetails(this.getClass().getSimpleName());
            Log.info("Open new page");
            HomePage homePage = new HomePage(driver,this.getUsername());
            homePage.navigateTo(this.getMainURL());

            LoginPage loginPage = homePage.openSignInPage();
            //compare screenshots
            BufferedImage image1 = Screenshot.takePageScreenshot(this.driver,"Login");
            BufferedImage imageOut = ImageIO.read(new File("D:\\L_Java\\screenshots\\testImg.png"));
            Screenshot.compareImages(image1,imageOut);

            loginPage.login(this.getUsername(),this.getPassword());
            loginPage.checkAccountInfoByText(this.getGetUsernameLoggedInfo());
            loginPage.logOut();

            homePage.closePage();
            Log.endTestDetails(this.getClass().getSimpleName());

        }catch(RuntimeException | IOException ex){
            Log.error(ex.getMessage());
            throw ex;
        }

//        Log.startTestDetails(this.getClass().getSimpleName());
//        Log.info("Open new page");
//        HomePage homePage = new HomePage(driver);
//        homePage.navigateTo(this.getMainURL());
//
//        LoginPage loginPage = homePage.openSignInPage();
//        Log.info("Login with " + this.getUsername());
//        loginPage.login(this.getUsername(),this.getPassword());
//        loginPage.checkAccountInfoByText(this.getGetUsernameLoggedInfo());
//
//        loginPage.logOut();
//        homePage.closePage();
//        Log.endTestDetails(this.getClass().getSimpleName());

    }



}
