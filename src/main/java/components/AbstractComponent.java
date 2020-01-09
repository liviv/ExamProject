package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import utils.Utils;

import java.util.List;
import java.util.Set;

public class AbstractComponent extends HtmlElement {

    protected WebDriver webDriver = Utils.getWebDriver();


}
