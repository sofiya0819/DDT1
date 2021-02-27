package DDT;

import AutoFrameWork.TestSetUp;


import PageObjectModel.HomePage;
import PageObjectModel.LoginPage;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.IOException;
public class Login extends TestSetUp {
        @Before
    public void setUp()  {
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
    public void simpleLogin() throws IOException {
//        Log.startTestDetails(this.getClass().getSimpleName());
//        Log.info("Open new page");
        HomePage hp = new HomePage(driver);
        hp.navigateTo(this.getMainURL());
        LoginPage lp = hp.openSignInPage();
//        Log.info("Login with" + this.getUsername());
        lp.login(this.getUsername(), this.getPassword());
        lp.checkAccountInfoByText("S G");
        lp.logOut();
        lp.closePage();
//        Log.endTestDetails();
    }
}
