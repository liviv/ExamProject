package components;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;


public class DeparturePortComponent extends AbstractComponent {
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

//    public void clickOnRandomContinent(String continent) {
//
//        departureContinentList.get(new Random().nextInt(), );
//        Optional<WebElement> item = departureContinentList.stream().filter(it -> it.getText().trim().equalsIgnoreCase(continent)).findFirst();
//        if (item.isPresent()) {
//            // waitPageLoadedSelectContinent();
//            item.get().click();
//        } else {
//            logger.info("Continent not found: " + continent);
//        }
//    }




    public void clickOnCityByName(String city) {
        logger.info("cities: " + String.join(", ", departureCityList.stream().map(it -> it.getText().trim()).collect(Collectors.toList())));
        Optional<WebElement> item = departureCityList.stream().filter(it -> it.getText().trim().toLowerCase().contains(city.toLowerCase())).findFirst();
        if (item.isPresent()) {
            item.get().click();

        } else {
            logger.info("City not found: " + city);
        }
    }

    public void clickOnApplyButton() {
        waitDepartureComponentLoaded();
        try {
            logger.info("Clicking on apply button");
            applyButton.click();
        } catch (Exception e) {
            logger.info("Apply button could not be found");
        }

    }


    public void waitDepartureComponentLoaded() {

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebDriverWait wait = new WebDriverWait(webDriver, 15);
        wait.until(ExpectedConditions.and(
                ExpectedConditions.visibilityOfAllElements(departureContinentList),
                ExpectedConditions.elementToBeClickable(departureContinentList.get(0)),
                ExpectedConditions.visibilityOfAllElements(departureCityList),
                ExpectedConditions.elementToBeClickable(departureCityList.get(0)),
                ExpectedConditions.visibilityOfAllElements(applyButton)
                )
        );

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @FindBy(xpath = "//div[@role='tab']")
    List<WebElement> departureContinentList;

    @FindBy(xpath = ".//div[@class='mat-button-toggle-label-content']")
    List<WebElement> departureCityList;

    @FindBy(xpath = "//button[@data-selector='search-apply-button']")
    WebElement applyButton;

}
