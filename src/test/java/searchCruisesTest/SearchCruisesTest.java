package searchCruisesTest;

import abstractTest.AbstractTest;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import pages.CruiseItem;

import java.util.function.Predicate;

public class SearchCruisesTest extends AbstractTest {

    public static Predicate<CruiseItem> moreOrEqual6Nights = new Predicate<CruiseItem>() {
        @Override
        public boolean test(CruiseItem t) {
            return t.getNumberOfNights() >= 6;
        }
    };


    public static Predicate<CruiseItem> lessOrEqual8Nights = new Predicate<CruiseItem>() {
        @Override
        public boolean test(CruiseItem t) {
            return t.getNumberOfNights() <= 8;
        }
    };

    @Test
    public void searchCruisesByNumberOfNights() {
        Logger logger = Logger.getLogger(getClass());
        homePage.openPage();
        homePage.clickOnFindACruise();

        cruisesPage.clickOn6_8NightsFilter();

        System.out.println("cruisesPage.getCruiseItemList() + " + cruisesPage.getCruiseItemList());
        Assert.assertTrue(cruisesPage.checkCruiseItemsOnAllPages(it -> {
            return it.getNumberOfNights() >= 6 && it.getNumberOfNights() <= 8;
        }, "Cruise number of nights are between 6 and 8"));

        Assert.assertTrue(cruisesPage.checkCruiseItemsOnAllPages(
                moreOrEqual6Nights.and(lessOrEqual8Nights)
                , "Cruise number of nights are between 6 and 8"));
    }

    @Test
    public void checkDefaultSorting() {
        Logger logger = Logger.getLogger(getClass());
        homePage.openPage();
        homePage.clickOnFindACruise();
        cruisesPage.clickOn6_8NightsFilter();
        Assert.assertTrue(
                cruisesPage.checkSortingByPriceOnAllPages());
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
        cruisesPage.waitPageLoaded();
        cruisesPage.getDeparturePortComponent().clickOnCityByName("Rome");
        cruisesPage.initialize();
        cruisesPage.getDeparturePortComponent().clickOnApplyButton();
        cruisesPage.waitPageLoaded();
        cruisesPage.initialize();
        Assert.assertTrue(
                cruisesPage.checkCruiseItemsOnAllPages(it -> {
                    return it.getDeparturePort().toLowerCase().contains("rome");
                }, "Departure port is Rome"));
    }

    @Test
    public void checkClearAllButton() {
        Logger logger = Logger.getLogger(getClass());
        homePage.openPage();
        homePage.clickOnFindACruise();
        cruisesPage.waitPageLoaded();

        int initialNumberOfCruises = cruisesPage.getNumberOfItems();
        //  cruisesPage.getDeparturePortComponent().waitDepartureComponentLoaded();
        cruisesPage.clickOnDeparturePortButton();
        cruisesPage.getDeparturePortComponent().
                clickOnContinentByName("ITALY");
        cruisesPage.waitPageLoaded();
        cruisesPage.getDeparturePortComponent().clickOnCityByName("Venice");
        cruisesPage.getDeparturePortComponent().clickOnApplyButton();
        cruisesPage.waitPageLoaded();
        cruisesPage.initialize();
        Assert.assertTrue("Number of Cruises is incorrect", initialNumberOfCruises > cruisesPage.getNumberOfItems());

        cruisesPage.clickOnClearAllButton();
        Assert.assertEquals("Number of Cruises is incorrect", initialNumberOfCruises, cruisesPage.getNumberOfItems());

        cruisesPage.clickOn6_8NightsFilter();
        Assert.assertTrue("Number of Cruises is incorrect", initialNumberOfCruises > cruisesPage.getNumberOfItems());

        cruisesPage.clickOnClearAllButton();
        Assert.assertEquals("Number of Cruises is incorrect", initialNumberOfCruises, cruisesPage.getNumberOfItems());


    }


}
