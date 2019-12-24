package libs;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ActionsOnElements {
    Logger logger = Logger.getLogger(getClass());
    WebDriver webDriver;
    protected ActionsOnElements actionsOnElements;

    public ActionsOnElements(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public final String NIGHTS_2_5 = "2to5";
    public final String NIGHTS_6_8 = "6to8";
    public final String NIGHTS_9_11 = "9to11";
    public final String NIGHTS_12_MORE = "12plus";

    public void clickOnElement(WebElement webElement) {
        try {//todo Add waiting
            webElement.click();
        } catch (Exception e) {
            logger.error("Error occurred on clicking on element");
        }
    }

    public void clickOnElementByXpath(String xpath) {
        try {//todo Add waiting

          WebElement webElement = webDriver.findElement(By.xpath(xpath));
          webElement.click();
            System.out.println("element was clicked");
        } catch (Exception e) {
            logger.error("Error occured on clicking on element");
        }
    }

    public void stopTestAndShowMessage() {
        logger.error("Error occured");
        Assert.fail("Fatal error");
    }






}
