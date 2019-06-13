package core.layers;

import core.pages.BasePage;
import core.pages.GroupsPage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ConfirmDeletingGroupLayer extends BasePage {
    private static final By BUTTON_DELETE_GROUP = By.xpath(".//input[@class='button-pro form-actions_yes']");
    private static final By BUTTON_CANCEL = By.xpath(".//*[@class='modal-new_actions __center']" +
            "//*/a[@class='button-pro __sec']");

    public ConfirmDeletingGroupLayer(WebDriver driver) {
        super(driver);
    }

    public void confirmDeleting(){
        click(BUTTON_DELETE_GROUP, "Пропала кнопка Удалить");
        new GroupsPage(driver);
    }

    @Override
    protected void check() {
        Assert.assertTrue("Не прогрузилась кнопка Удалить",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(BUTTON_DELETE_GROUP),
                        5,500));
        Assert.assertTrue("Не прогрузилась кнопка Отменить",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(BUTTON_CANCEL),
                        5,500));
    }
}
