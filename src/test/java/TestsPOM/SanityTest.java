package TestsPOM;

import AutoFrameWork.ItemDetails;
import AutoFrameWork.MainTestSetUp;
import AutoFrameWork.Utilities.ReadFromXml;
import PageObjectModel.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;
import java.util.Collection;

@RunWith(Parameterized.class)
public class SanityTest extends MainTestSetUp {

    ItemDetails item;
    
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

    public SanityTest(String itemName, String price, String quantity, String size, String color){
        item = new ItemDetails(itemName,price,quantity,size,color);
    }

    /**************************************************************************************************
    Read form excel file the items as for each item there is a separate test
     **************************************************************************************************/
    @Parameterized.Parameters
    public static Collection inputs() throws IOException {
        return ReadFromXml.readXml("C:\\WebDriver\\inits\\Item.xlsx");
    }

    @Test
    public void sanityTest() throws InterruptedException, IOException {
        HomePage homePage = new HomePage(driver,this.getUsername());
        homePage.navigateTo(this.getMainURL());
        LoginPage loginPage = homePage.openSignInPage();
        loginPage.login(this.getUsername(),this.getPassword());
        loginPage.goToHomePage();
        ProductDetailsPage product = homePage.openItem(item.itemName);
        product.addToCard(item);
        product.proceedToCheckOut();
        //TODO: make a separate page SummaryPage
        product.proceedToCheckOutInSummary();
        AddressPage addressPage = new AddressPage(this.driver);
        String nameFromInput = addressPage.getTextFromNameField();
        Assert.assertEquals(this.getGetUsernameLoggedInfo(),nameFromInput);
        addressPage.setOrderAddress("varna, Alaska 12345");
        addressPage.proceedToCheckOut();
        ShippingPage shippingPage = new ShippingPage(driver);
        shippingPage.acceptAndProceed();
        PaymentPage paymentPage = new PaymentPage(driver);
        paymentPage.checkItemName(item.itemName);
        paymentPage.checkItemPrice(item.price);
        paymentPage.checkTotalPrice(item);
        paymentPage.pay();



    }


}
