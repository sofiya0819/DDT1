package DDT;

import AutoFrameWork.ItemDetails;
import AutoFrameWork.MainTestSetUp;
import AutoFrameWork.Utilities.ReadFromXml;
import PageObjectModel.HomePage;
import PageObjectModel.LoginPage;
import PageObjectModel.ProductDetailsPage;
import PageObjectModel.RegistrationPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.Collection;

public class RegistrationDDT extends MainTestSetUp {

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


    @Test
    public void registrationTest() throws InterruptedException, IOException {
        // proverka na ве4е registriran email ?????????????????????????
        HomePage homePage = new HomePage(driver,this.getUsername());
        homePage.navigateTo(this.getMainURL());
        LoginPage loginPage = homePage.openSignInPage();
        RegistrationPage registrationPage = loginPage.createAccount(this.getUsername());
        registrationPage.register();


    }
}
