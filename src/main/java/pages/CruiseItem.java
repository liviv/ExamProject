package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

public class CruiseItem extends HtmlElement {

    Logger logger = Logger.getLogger(getClass());

    public String getCruiseName() {
        String value = cruiseName.getText().trim();
        logger.info("Cruise name  is: " + value);
        return value;
    }

    public String getPriceLabelStr() {
        String value = price.getText().trim();
        logger.info("Price is: " + value);
        return value;
    }

    public int getPrice() {
        return Integer.parseInt(getPriceLabelStr());
    }

    public String getNightLabelOnCruiseStr() {
        String value = nightLabelOnCruise.getText().trim();
        logger.info("Number of nights label is: " + value);
        return value;
    }

    public int getNumberOfNights() {
        int numberOfNights = Integer.parseInt(getNightLabelOnCruiseStr().charAt(0) + "");
        logger.info("Number of nights is: " + numberOfNights);
        return numberOfNights;
    }

    @FindBy(xpath = ".//h4")
    protected WebElement nightLabelOnCruise;

    @FindBy(xpath = ".//h3")
    protected WebElement cruiseName;

    @FindBy(xpath = ".//span[@class='price-value']")
    protected WebElement price;

}