package components;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ItineraryPanelComponent extends AbstractComponent {
    Logger logger = Logger.getLogger(getClass());

    public void waitComponentLoaded() {

        WebDriverWait wait = new WebDriverWait(webDriver, 15);
        wait.until(ExpectedConditions.visibilityOf(nightLabelItineraryPanel));

    }

    public String getCruiseNameItineraryPanel() {
        String value = cruiseNameItineraryPanel.getText().trim();
        logger.info("Cruise name  on itinerary panel is: " + value);
        return value;
    }


    public String getNightLabelItineraryPanelStr() {
        String value = nightLabelItineraryPanel.getText().trim();
        logger.info("Number of nights on itinerary panel is: " + value);
        return value;
    }

    public int getNumberOfNightsItineraryPanel() {
        int numberOfNights = Integer.parseInt(getNightLabelItineraryPanelStr().charAt(0) + "");
        logger.info("Number of nights on itinerary panel is: " + numberOfNights);
        return numberOfNights;
    }


    public String getDeparturePortItineraryPanel() {
        String departurePort = departurePortItineraryPanel.getText().trim();
        logger.info("Departure port on  itinerary panel is: "+ departurePort);
        return departurePort;
    }


    @FindBy(xpath = ".//h2[@class ='itinerary-panel-title']")
    protected WebElement nightLabelItineraryPanel;

    @FindBy(xpath = ".//h3[@class ='itinerary-panel-subtitle']")
    protected WebElement cruiseNameItineraryPanel;

    @FindBy(xpath = ".//div[1]/span[2]/b[1]")
    protected WebElement departurePortItineraryPanel;

}
