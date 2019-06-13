package core.pages;

import core.transformers.OwnGroupTransformer;
import core.wrappers.OwnGroupWrapper;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Collections;
import java.util.List;

public class OwnGroupsPage extends BasePage {
    private static final By LOGO = By.className("toolbar_logo");
    private static final By BUTTON_CREATE_GROUP = By.xpath(".//a[@class='add-stub al add-stub__hor']");
    private static final By SEARCH_FIELD = By.xpath(".//input[@name='st.query']");
    private static final By OWN_GROUPS_LAYER = By.xpath(".//*[@id='hook_Block_DetailedUserGroupsListBlock']");
    private static final By OWN_GROUP_CARD = By.xpath(".//*[@id='listBlockPanelDetailedUserGroupsListBlock']//*[@class='ugrid_i']");

    public OwnGroupsPage(WebDriver driver) {
        super(driver);
    }

    private List<OwnGroupWrapper> findAllOwnGroups(){
        if (isElementPresent(OWN_GROUPS_LAYER)){
            return OwnGroupTransformer.wrap(driver.findElements(OWN_GROUP_CARD),driver);
        }
        return  Collections.emptyList();
    }

    public GroupPage findNeededOwnGroup(String expectedName){
        List<OwnGroupWrapper> ownGroups = findAllOwnGroups();

        for (OwnGroupWrapper group: ownGroups){
            if (group.isGroupNeeded(expectedName)){
                group.clickToName();
                return new GroupPage(driver);
            }
        }
        return null;
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
