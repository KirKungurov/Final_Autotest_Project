package core.pages;

import core.layers.AlertsLayer;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends BasePage {
    private static final By LOGO = By.className("toolbar_logo");
    private static final By BUTTON_GROUPS = By.xpath(".//*[@id='hook_Block_SideNavigation']" +
            "//*/a[@data-l='t,userAltGroup']");
    private static final By ALERTS = By.xpath(".//*[@class='toolbar_nav_a toolbar_nav_a__notif']");
    private static final By TOOLBAR = By.xpath(".//*[@data-l='t,navigationToolbar']");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public GroupsPage clickToGroups(){
        click(BUTTON_GROUPS, "Пропала кнопка перехода в группы");
        return new GroupsPage(driver);
    }

    public AlertsLayer clickToAlert(){
        click(ALERTS, "Пропала кнопка Оповещения");
        return new AlertsLayer(driver);
    }

    @Override
    protected void check() {
        Assert.assertTrue("Не прогрузился логотип",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(LOGO),
                        4, 500));
        Assert.assertTrue("Не прогрузилась кнопка перехода в группы",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(BUTTON_GROUPS),
                        4, 500));
        Assert.assertTrue("Не прогрузолось верхнее меню",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(TOOLBAR),
                        4, 500));

    }
}
