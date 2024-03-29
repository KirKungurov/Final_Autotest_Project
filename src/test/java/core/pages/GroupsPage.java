package core.pages;

import core.layers.GroupCategoryLayer;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GroupsPage extends BasePage {
    private static final By LOGO = By.className("toolbar_logo");
    private static final By BUTTON_CREATE_GROUP = By.xpath(".//a[@class='add-stub al add-stub__hor']");
    private static final By SEARCH_FIELD = By.xpath(".//input[@name='st.query']");
    private static final By OWN_GROUP_BUTTON = By.xpath(".//*[@class='ml']");

    public GroupsPage(WebDriver driver) {
        super(driver);

    }

    public void clickToCreateButton(){
        click(BUTTON_CREATE_GROUP, "Не прогрузилась кнопка создания группы");
        new GroupCategoryLayer(driver);
    }

    public void clickToOwnGroupButton(){
        click(OWN_GROUP_BUTTON, "Не прогрузилась кнопка Мои группы");
    }
    public void clickSearchGroup(String nameOfNeededGroup){
        type(SEARCH_FIELD, nameOfNeededGroup);
        new FoundedGroupsPage(driver);
    }


    @Override
    protected void check() {
        Assert.assertTrue("Не прогрузился логотип",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(LOGO),
                        5, 500));
        Assert.assertTrue("Не прогрузилась кнопка создания группы",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(BUTTON_CREATE_GROUP),
                        5, 500));
        Assert.assertTrue("Не прогрузилось поле для поиска групп",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(SEARCH_FIELD),
                        5, 500));
    }
}
