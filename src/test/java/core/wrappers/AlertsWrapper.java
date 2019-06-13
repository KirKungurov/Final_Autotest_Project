package core.wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AlertsWrapper {
    private WebElement element;
    private WebDriver driver;

    public static final By NAME_OF_PERSON = By.xpath(".//*[@data-l='nA,LINK_USER,t,user_link']");
    public static final By NAME_OF_GROUP =  By.xpath(".//form[@method='POST']//*[contains(@data-l,'nA,LINK_GROUP')]");
    public static final By TIME = By.xpath(".//*[@class='notif_footer']");

    public AlertsWrapper(WebDriver driver, WebElement element){
        this.driver = driver;
        this.element = element;
    }

    public boolean equals(String expectedNameOfPerson, String expectedNameOfGroup){
        String actualNameOfPerson = element.findElement(NAME_OF_PERSON).getText();
        String actualNameOfGroup = element.findElement(NAME_OF_GROUP).getText();
        if (actualNameOfGroup.equals(expectedNameOfGroup)
                && actualNameOfPerson.equals(expectedNameOfPerson))
            return true;
        else
            return false;
    }
}
