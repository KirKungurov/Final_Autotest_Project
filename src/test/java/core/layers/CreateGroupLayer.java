package core.layers;

import core.pages.BasePage;
import core.pages.GroupPage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CreateGroupLayer extends BasePage {
    private static final By NAME_FIELD = By.xpath(".//input[@name='st.layer.name']");
    private static final By CREATE_BUTTON = By.xpath(".//input[@name='button_create']");

    public CreateGroupLayer(WebDriver driver) {
        super(driver);
    }

    public void CrateGroup(String name){
        type(NAME_FIELD, name);
        click(CREATE_BUTTON,"Пропала кнопка Создать");
        new GroupPage(driver);
    }

    @Override
    protected void check() {
        Assert.assertTrue("Не прогрузилось поле ввода имени",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(NAME_FIELD),
                        5,500));
        Assert.assertTrue("Не прогрузилось кнопка Создать",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(CREATE_BUTTON),
                        5,500));
    }
}
