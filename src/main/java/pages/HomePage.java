package pages;

import abstractPage.AbstractPage;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import libs.ActionsOnElements;

public class HomePage extends AbstractPage {
    @FindBy(xpath = "//nav/div/a[@href='https://www.royalcaribbean.com/cruises']")
    private WebElement findACruiseMenu;


    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }


    public void openPage() {
        try {
            webDriver.get("https://www.royalcaribbean.com/");
        } catch (Exception e) {
            Assert.fail("cannot open the site");

        }
    }

    public void clickOnFindACruise() {
        try {
            actionsOnElements.clickOnElement(findACruiseMenu);
            logger.info("Find a cruise Menu was clicked");
            System.out.println("Find a cruise Menu was clicked");
        } catch (Exception e) {
            logger.error("find A Cruise Menu  not found");
        }
    }


}
