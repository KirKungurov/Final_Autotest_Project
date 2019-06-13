package core.wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FoundedGroupsWrapper {
    private WebDriver driver;
    private WebElement element;

    private static final By NAME_OF_GROUP = By.xpath(".//*[@data-l='t,visit']");
    private static final By DESCRIPTION_OF_GROUP = By.className("textWrap js-expandable-content");

    public FoundedGroupsWrapper(WebDriver driver, WebElement element){
        this.driver = driver;
        this.element = element;
    }

    public boolean isGroupNeeded(String expectedName, String expectedDescription){
        return expectedName.equals(element.findElement(NAME_OF_GROUP).getText())
                && expectedDescription.equals(element.findElement(DESCRIPTION_OF_GROUP));
    }


}
