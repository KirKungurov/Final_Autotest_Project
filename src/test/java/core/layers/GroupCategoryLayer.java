package core.layers;

import core.pages.BasePage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GroupCategoryLayer extends BasePage {
    private static final By PUBlIC_PAGE = By.xpath(".//a[@data-l='t,PAGE']");
    private static final By EVENT = By.xpath(".//a[@data-l='t,HAPPENING']");

    public GroupCategoryLayer(WebDriver driver) {
        super(driver);
    }

    public CreateGroupLayer selectCategory(){
        click(PUBlIC_PAGE, "Пропала кнопка Публичная страница");
        return new CreateGroupLayer(driver);
    }

    @Override
    protected void check() {
        Assert.assertTrue("Не прогрузилась кнопка Публичная страница",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(PUBlIC_PAGE),
                        5,500));
        Assert.assertTrue("Не прогрузилась кнопка Мероприятия",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(EVENT),
                        5,500));
    }
}
