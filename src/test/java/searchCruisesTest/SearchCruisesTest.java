package searchCruisesTest;

import abstractTest.AbstractTest;
import org.apache.log4j.Logger;
import org.junit.Test;

public class SearchCruisesTest extends AbstractTest {

    @Test
    public void searchCruisesByNumberOfNights() {
        Logger logger = Logger.getLogger(getClass());
        homePage.openPage();
        homePage.clickOnFindACruise();

        cruisesPage.clickOn6_8NightsFilter();

        System.out.println("cruisesPage.getCruiseItemList() + " + cruisesPage.getCruiseItemList());
        cruisesPage.checkCruiseItemsOnAllPages(it -> {
            return it.getNumberOfNights() >= 6 && it.getNumberOfNights() <= 8;
        }, "Cruise number of nights are between 6 and 8");
    }

    @Test
    public void checkDefaultSorting() {
        Logger logger = Logger.getLogger(getClass());
        homePage.openPage();
        homePage.clickOnFindACruise();
        cruisesPage.clickOn6_8NightsFilter();
        cruisesPage.checkSortingByPriceOnAllPages();
    }

    @Test
    public void checkDeparturePort() {
        Logger logger = Logger.getLogger(getClass());
        homePage.openPage();
        homePage.clickOnFindACruise();
        cruisesPage.waitPageLoaded();
        cruisesPage.clickOnDeparturePortButton();
        cruisesPage.waitPageLoaded();
        cruisesPage.getDeparturePortComponent().
                clickOnContinentByName("ITALY");
        cruisesPage.getDeparturePortComponent().clickOnCityByName("Venice");
        cruisesPage.getDeparturePortComponent().clickOnApplyButton();
    }

}
