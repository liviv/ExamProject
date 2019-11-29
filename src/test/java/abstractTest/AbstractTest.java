package abstractTest;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.CruisesPage;
import pages.HomePage;

import java.io.File;
import java.util.List;
import java.util.Set;

public class AbstractTest {
    HomePage homePage;
    CruisesPage cruisesPage;
    WebDriver webDriver;

    @Before
    public void setUp() {
        File file = new File("./src/drivers/chromedriver.exe");
        Logger logger = Logger.getLogger(getClass());
        WebDriver webDriver = new ChromeDriver();
        homePage = new HomePage(webDriver);
        cruisesPage = new CruisesPage(webDriver);
    }

    @After
    public void closeDriver() {
        webDriver.quit();
    }
}
