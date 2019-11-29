package pages;

import abstractPage.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends AbstractPage {
    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = "//nav/div/a[@href='https://www.royalcaribbean.com/cruises']")
    private WebElement findACruiseMenu;




}
