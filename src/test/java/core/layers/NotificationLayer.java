package core.layers;

import core.transformers.NotificationsTransformer;
import core.wrappers.AlertsWrapper;
import core.pages.BasePage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Collections;
import java.util.List;

public class NotificationLayer extends BasePage {

    private static final By LEFT_MENU = By.xpath(".//*[@class='toolbar-layer_menu custom-scrolling usel-off']");
    private static final By CONTENT = By.id("ntf_layer_content_inner");
    private static final By GROUPS = By.xpath(".//*[contains(@class,'nav-side __navigation')]//a[@data-category='Groups']");
    private static final By ALERT = By.xpath(".//*[@id='ntf_layer_content_inner']//*[@class='hookBlock']");

    public NotificationLayer(WebDriver driver) {
        super(driver);
    }

    public void clickToGroups(){
        click(GROUPS, "Пропала кнопка Группы");
        new NotificationLayer(driver);
    }
    public AlertsWrapper findNeededInvite(String expectedNameOfCreater,
                                          String expectedNameofGroup){
        List<AlertsWrapper> alerts = findAllAlerts();

        for (AlertsWrapper alert : alerts) {
            if (alert.equals(expectedNameOfCreater, expectedNameofGroup)) {
                return alert;
            }
        }
        return null;
    }

    private List<AlertsWrapper> findAllAlerts(){
        if (isElementPresent(ALERT)){
            return NotificationsTransformer.wrap(driver.findElements(ALERT), driver);
        }
        return Collections.emptyList();
    }

    @Override
    protected void check() {
        Assert.assertTrue("Не прогрузилось левое меню",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(LEFT_MENU),
                        5,500));
        Assert.assertTrue("Не прогрузился блок контента",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(CONTENT),
                        5,500));
    }
}
