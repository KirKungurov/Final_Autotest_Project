package core.wrappers;

import core.layers.InviteFriendsLayer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FriendWrapper {
    private WebDriver driver;
    private WebElement element;


    private static final By BUTTON = By.xpath(".//*[@class='selectable-card_ic']");
    private static final By NAME = By.xpath(".//*[@class='ellip']/a[contains(@hrefattrs,'friendId')]");

    public FriendWrapper(WebDriver driver, WebElement element) {
        this.driver = driver;
        this.element = element;
    }

    public InviteFriendsLayer chooseMan(){
        element.findElement(BUTTON).click();
        return new InviteFriendsLayer(driver);
    }

    public boolean equals(String name) {
        return element.findElement(NAME).getText().equals(name);
    }
}