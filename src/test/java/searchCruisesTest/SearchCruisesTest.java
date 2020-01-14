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
        homePage.openPage();
        homePage.clickOnFindACruise();

        cruisesPage.clickOn6_8NightsFilter();

        //System.out.println("cruisesPage.getCruiseItemList() + " + cruisesPage.getCruiseItemList());

        Assert.assertTrue(cruisesPage.checkCruiseItemsOnAllPages(
                moreOrEqual6Nights.and(lessOrEqual8Nights)
                , "Cruise number of nights are between 6 and 8"));
    }

    @Test
    public void checkDefaultSorting() {
        homePage.openPage();
        homePage.clickOnFindACruise();
        cruisesPage.clickOn6_8NightsFilter();
        Assert.assertTrue(
                cruisesPage.checkSortingByPriceOnAllPages());
    }

    @Test
    public void checkDeparturePort() {
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

    @Test
    public void checkItineraryPanel() {
        homePage.openPage();
        homePage.clickOnFindACruise();
        cruisesPage.waitPageLoaded();
        cruisesPage.getCruiseItemList().get(1).clickOnNightLabel();
        cruisesPage.getItineraryPanelComponent().waitComponentLoaded();

        Assert.assertTrue("Cruise name on cruise item and itinerary panel is not equal ",
                cruisesPage.cruiseNameCorrespondsToItineraryPanel(1));
        Assert.assertTrue("Number of nights on cruise item and itinerary panel is not equal",
                cruisesPage.nbOfNightsOnCruiseCorrespondsToItineraryPanel(1));
        Assert.assertTrue("Departure port on cruise item and itinerary panel is not equal ",
                cruisesPage.departurePortOnCruiseCorrespondsToItineraryPanel(1));

    }

}
