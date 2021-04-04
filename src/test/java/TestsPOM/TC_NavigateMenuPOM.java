package TestsPOM;

import PageObjectModel.NavigationMenuPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;




public class TC_NavigateMenuPOM {
    WebDriver driver;
    String url = "http://automationpractice.com/index.php";
    String urlEveningDress = "http://automationpractice.com/index.php?id_category=10&controller=category";;
    String urlCasualDress = "http://automationpractice.com/index.php?id_category=9&controller=category";
    @Before
    public void SetUp() {
        String driverExecutablePath = "C://WebDriver//bin//chromedriver.exe";
        System.getProperty("webdriver.chrome.driver", driverExecutablePath);
        driver = new ChromeDriver();
    }

    @After
    public void tearDown() {
        driver.quit();
    }


    @Test
    public void NavigationWomanEveningDress() {
        driver.get(urlEveningDress);
        try{
            NavigationMenuPage np = new NavigationMenuPage(driver);
            np.NavigationToWomanMenuToEveningDresses();
            Assert.assertEquals(urlEveningDress, driver.getCurrentUrl());
            np.closePage();
            System.out.println("Navigated to correct webpage");
        }
        catch(Throwable pageNavigationError){
            System.out.println("Didn't navigate to correct webpage");
        }
    }

    @Test
    public void NavigationWomanCasualDress() {
        driver.get(urlCasualDress);
        try{
            NavigationMenuPage np = new NavigationMenuPage(driver);
            np.NavigationToWomanMenuToCasualDresses();
            Assert.assertEquals(urlCasualDress, driver.getCurrentUrl());
            np.closePage();
            System.out.println("Navigated to correct webpage");
        }
        catch(Throwable pageNavigationError){
            System.out.println("Didn't navigate to correct webpage");
        }
    }

    @Test
    public void NavigationDressesToEveningDresses() {
        driver.get(urlCasualDress);
        try{
            NavigationMenuPage np = new NavigationMenuPage(driver);
            np.NavigationDressesToEveningDresses();
            Assert.assertEquals(urlCasualDress, driver.getCurrentUrl());
            np.closePage();
            System.out.println("Navigated to correct webpage");
        }
        catch(Throwable pageNavigationError){
            System.out.println("Didn't navigate to correct webpage");
        }
    }


}
