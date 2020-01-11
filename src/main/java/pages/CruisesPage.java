package pages;

import abstractPage.AbstractPage;
import components.DeparturePortComponent;
import components.ItineraryPanelComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.function.Predicate;

public class CruisesPage extends AbstractPage {

    public CruisesPage(WebDriver webDriver) {
        super(webDriver);
    }


    public void waitPageLoaded() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebDriverWait wait = new WebDriverWait(webDriver, 15);
        wait.until(ExpectedConditions.and(
                ExpectedConditions.visibilityOfAllElements(filterBy6_8Nights),
                ExpectedConditions.or(
                        ExpectedConditions.elementToBeClickable(previousPageButton),
                        ExpectedConditions.elementToBeClickable(nextPageButton)
                )
        ));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void clickOnNightsFilter(String numberOfNight) {
        try {
            actionsOnElements.clickOnElement(webDriver.findElement(By.xpath("//label[@for= 'filter_" + numberOfNight + "_nights-input']")));
            System.out.println(numberOfNight + " nights was selected");
        } catch (Exception e) {
            logger.error("Cannot click on element");
        }
    }

    public void clickOn6_8NightsFilter() {

        try {
            actionsOnElements.clickOnElement(filterBy6_8Nights);
            System.out.println("Filter by 6_8 Nights was selected");
            waitPageLoaded();
            initialize();
        } catch (Exception e) {
            logger.error("Cannot click on element");
        }
    }

    public boolean isNextPageButtonEnabled() {
        return nextPageButton.isEnabled();
    }

    public void clickOnNextPageButton() {
        logger.info("Clicking next page button");
        nextPageButton.click();
        waitPageLoaded();
    }

    public void clickOnDeparturePortButton() {
        logger.info("Clicking Departure Port button");
        departurePortButton.click();
        waitPageLoaded();
    }

    public boolean checkCruiseItemsOnAllPages(Predicate<CruiseItem> predicate, String predicateCriteriaDescription) {
        logger.info("Checking that cruise items on all pages match the following criteria: " + predicateCriteriaDescription);

        int maxPage = 1000;
        int currentPage = 1;

        while (true) {

            if (currentPage > maxPage) {
                stopTestAndThrowError("The number of pages exceeds 1000");
            }

            if (!this.getCruiseItemList().stream().allMatch(it -> {
                        boolean itemIsCorrect = predicate.test(it);
                        logger.info("Cruise " + it.getCruiseName() + " is correct: " + itemIsCorrect);
                        return itemIsCorrect;
                    }
            )
            ) {
                logger.info("Some cruise items on the page do not match the following criteria: " + predicateCriteriaDescription);
                return false;
            }
            if (isNextPageButtonEnabled()) {
                clickOnNextPageButton();
                currentPage++;

                continue;
            }
            logger.info("Cruise items on all pages match the following criteria: " + predicateCriteriaDescription);
            return true;
        }
    }

    public boolean checkSortingByPriceOnAllPages() {
        logger.info("Checking that default sorting is from low to high");
        int priceToCompare = -1;

        while (true) {
            for (CruiseItem item : getCruiseItemList()) {
                boolean isCorrect = priceToCompare <= item.getPrice();
                logger.info("Checking cruise " + item.getCruiseName()
                        + ". Its price is " + item.getPrice()
                        + " (previous cruise price was: " + priceToCompare
                        + "). It is correct: " + isCorrect);
                if (!isCorrect) {
                    return false;
                }
                priceToCompare = item.getPrice();
            }
            if (isNextPageButtonEnabled()) {
                clickOnNextPageButton();
                continue;
            }
            logger.info("Prices for all cruises is sorted from low to high");
            return true;
        }
    }

    public int getNumberOfItems() {
        String paginationLabel = this.paginationLabel.getText().trim();
        int value = Integer.valueOf(paginationLabel.substring(paginationLabel.indexOf("of ") + 3));
        logger.info("Number of items is: " + value);
        return value;
    }

    public void clickOnClearAllButton() {
        try {
            logger.info("Clicking on Clear All button");
            clearAllButton.click();
            waitPageLoaded();
            initialize();
        } catch (Exception e) {
            logger.info("Clear All  button coud not be found");
        }
    }

    public void clickOnCruise(int index) {
        if (index < cruiseItemList.size()) {
            cruiseItemList.get(index).click();
            logger.info("Clicking on " + cruiseItemList.get(index).getName());
        } else {
            stopTestAndThrowError("Index must be between 0 and " + (cruiseItemList.size() - 1));
        }
    }

    public ItineraryPanelComponent getItineraryPanelComponent() {
        return itineraryPanelComponent;
    }

    @FindBy(xpath = "//ul/li[@class='collapsable']")
    protected List<CruiseItem> cruiseItemList;

    @FindBy(xpath = "//label[@for= 'filter_6to8_nights-input']")
    protected WebElement filterBy6_8Nights;

    @FindBy(xpath = "//div[@class='mat-paginator-range-label']")
    protected WebElement paginationLabel;

    @FindBy(xpath = "//button[@data-selector='search-clear-all-button']")
    protected WebElement clearAllButton;

    @FindBy(xpath = "//button[@data-selector='search-previous-page']")
    protected WebElement previousPageButton;

    @FindBy(xpath = "//button[@data-selector='search-next-page']")
    protected WebElement nextPageButton;

    @FindBy(xpath = "//button[@data-selector ='search-any-departure-port']")
    protected WebElement departurePortButton;

    @FindBy(xpath = "//section[@class='basic-filter-dropdown active']")
    protected DeparturePortComponent departurePortComponent;


    @FindBy(xpath = "//md-sidenav[contains(@class,'itinerary-panel-sidenav')]")
    protected ItineraryPanelComponent itineraryPanelComponent;


    public DeparturePortComponent getDeparturePortComponent() {
        return departurePortComponent;
    }

    public List<CruiseItem> getCruiseItemList() {
        return cruiseItemList;
    }

}
