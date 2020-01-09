package abstractPage;

import libs.ActionsOnElements;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

public class AbstractPage {

    protected WebDriver webDriver;
    protected ActionsOnElements actionsOnElements;
    protected Logger logger = Logger.getLogger(getClass());

    public AbstractPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        //PageFactory.initElements(webDriver, this);

        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(webDriver)), this);
        actionsOnElements = new ActionsOnElements(webDriver);

    }

    public void initialize() {
        PageFactory.initElements(
                new HtmlElementDecorator(
                        new HtmlElementLocatorFactory(webDriver))
                , this);
    }

    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor)webDriver).executeScript("arguments[0].scrollIntoView();", element);

    }

    public void stopTestAndThrowError(String message){
        logger.error(message);
        throw new RuntimeException(message);
    }
}
