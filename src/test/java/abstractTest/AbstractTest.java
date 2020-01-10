package abstractTest;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.CruiseItem;
import pages.CruisesPage;
import pages.HomePage;
import utils.Utils;

import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class AbstractTest {
    protected HomePage homePage;
    protected CruisesPage cruisesPage;
    protected WebDriver webDriver;

    @Before
    public void setUp() {
        File file = new File("./src/drivers/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());


        Logger logger = Logger.getLogger(getClass());
        webDriver = Utils.getWebDriver();
        homePage = new HomePage(webDriver);
        cruisesPage = new CruisesPage(webDriver);

        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @After
    public void closeDriver() {
        webDriver.quit();
    }
}
