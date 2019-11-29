package abstractPage;

import libs.ActionsOnElements;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class AbstractPage {

    protected WebDriver webDriver;
    protected ActionsOnElements actionsOnElements = new ActionsOnElements();
    public AbstractPage( WebDriver webDriver) {
    }

}
