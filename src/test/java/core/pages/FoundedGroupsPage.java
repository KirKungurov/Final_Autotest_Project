package core.pages;

import core.transformers.FoundedGroupsTransformer;
import core.wrappers.FoundedGroupsWrapper;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Collections;
import java.util.List;

public class FoundedGroupsPage extends BasePage {
    private static final By LOGO = By.className("toolbar_logo");
    private static final By SEARCH_FIELD = By.xpath(".//input[@name='st.query']");
    private static final By RESULT_LIST = By.xpath(".//*[@id='userGroupsSearchResultList']");
    private static final By CHECK_RESULT_LIST = By.xpath(".//*[@id='userGroupsSearchResultList' or" +
            "@id='hook_Block_UserGroupsSearchPortal']");
    private static final By GROUP_CARD = By.xpath(".//*[@class='ucard-v __h groups-portlet']");

    public FoundedGroupsPage(WebDriver driver) {
        super(driver);
    }

    public List<FoundedGroupsWrapper> findAllFoundedGroups(){
        if(isElementPresent(RESULT_LIST)){
            return FoundedGroupsTransformer.wrap(driver.findElements(GROUP_CARD),driver);
        }
        return Collections.emptyList();
    }

    @Override
    protected void check() {
        Assert.assertTrue("Не прогрузился логотип",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(LOGO),
                        5, 500));
        Assert.assertTrue("Не прогрузилось поле для поиска групп",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(SEARCH_FIELD),
                        5, 500));
        Assert.assertTrue("Не прогрузились результаты поиска",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(CHECK_RESULT_LIST),
                        5,500));
    }
}
