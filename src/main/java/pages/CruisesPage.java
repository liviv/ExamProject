package pages;

import abstractPage.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CruisesPage extends AbstractPage {
    public CruisesPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = "//label[@for= 'filter_6to8_nights-input']")
    private WebElement filterBy6_8Nights;

    @FindBy(xpath="//h4")
    private WebElement nightLabelOnCruise;

    @FindBy(xpath = "")
    private WebElement cruiseElement;



}
