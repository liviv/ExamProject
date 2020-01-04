package components;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.List;
import java.util.Optional;

public class DeparturePortComponent extends HtmlElement {
    Logger logger = Logger.getLogger(getClass());

    public void clickOnContinentByName(String continent) {
        Optional<WebElement> item = departureContinentList.stream().filter(it -> it.getText().trim().equalsIgnoreCase(continent)).findFirst();
        if (item.isPresent()) {
           // waitPageLoadedSelectContinent();
            item.get().click();
        } else {
            logger.info("Continent not found: " + continent);
        }
    }

    public void clickOnCityByName(String city) {
        Optional<WebElement> item = departureCityList.stream().filter(it -> it.getText().trim().toLowerCase().contains(city.toLowerCase())).findFirst();
        if (item.isPresent()) {
            item.get().click();

        } else {
            logger.info("City not found: " + city);
        }
    }

    public void clickOnApplyButton() {
        try {
            applyButton.click();
        } catch (Exception e) {
            logger.info("Apply button could not be found");
        }
    }


//    public void waitPageLoadedSelectContinent() {
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        WebDriverWait wait = new WebDriverWait(15);
//        wait.until(ExpectedConditions.and(
//                ExpectedConditions.visibilityOfAllElements(departureContinentList),
//                ExpectedConditions.elementToBeClickable((WebElement) departureContinentList)
//
//        ));
//
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }


    @FindBy(xpath = "//div[@role='tab']")
    List<WebElement> departureContinentList;

    @FindBy(xpath = "./div[@class='mat-button-toggle-label-content']")
    List<WebElement> departureCityList;

    @FindBy(xpath = "//button[@data-selector='search-apply-button']")
    WebElement applyButton;

}
