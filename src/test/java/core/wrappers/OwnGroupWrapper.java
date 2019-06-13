package core.wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OwnGroupWrapper {
    private WebElement element;

    private static final By NAME_OF_OWN_GROUP = By.xpath(".//*[@class='o two-lines']");

    public OwnGroupWrapper(WebElement element){
        this.element=element;
    }

    public boolean isGroupNeeded(String expectedNameOfGroup){
        return expectedNameOfGroup.equals(element.findElement(NAME_OF_OWN_GROUP).getText());
    }

    public void clickToName(){
        element.findElement(NAME_OF_OWN_GROUP).click();
    }
}
