package searchCruisesTest;

import abstractTest.AbstractTest;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.openqa.selenium.support.ui.Sleeper;
import pages.CruiseItem;

import java.time.Duration;

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
    public void checkDefaultSorting(){
        Logger logger = Logger.getLogger(getClass());
        homePage.openPage();
        homePage.clickOnFindACruise();
        cruisesPage.clickOn6_8NightsFilter();
        cruisesPage.checkSortingByPriceOnAllPages();
   }
}
