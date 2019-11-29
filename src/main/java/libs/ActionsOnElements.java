package libs;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ActionsOnElements {
    Logger logger = Logger.getLogger(getClass());
    WebDriver webDriver = new ChromeDriver();
    protected ActionsOnElements actionsOnElements;

    public final String   NIGHTS_2_5 = "2to5";
    public final String   NIGHTS_6_8 = "6to8";
    public final String   NIGHTS_9_11 = "9to11";
    public final String   NIGHTS_12_MORE = "12plus";

    public void clickOnElement(WebElement webElement) {
        try {//todo Add waiting
            webElement.click();
        } catch (Exception e) {
            logger.error("Error occured on clicking on element");
        }
    }


    public void clickOnElementByXpath(String xpath) {
        try {//todo Add waiting
          webDriver.findElement(By.xpath(xpath)).click();
        } catch (Exception e) {
            logger.error("Error occured on clicking on element");
        }
    }
    public void stopTestAndShowMessage() {
        logger.error("Error occured");
        Assert.fail("Fatal error");
    }

    public boolean checkCurrentUrl() {
return true; //todo
    }
    public void clickOnNumberOfNights(String numberOfNights){ // todo кликает на фильтр с нужным количеством ночей
        //todo add try and cath
        actionsOnElements.clickOnElementByXpath("//label[@for= 'filter_"+ numberOfNights+"_nights-input']");
    }

}
