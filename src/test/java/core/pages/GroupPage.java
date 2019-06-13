package core.pages;

import core.layers.ConfirmDeletingGroupLayer;
import core.layers.InviteFriendsLayer;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GroupPage extends BasePage {
    private static final By LOGO = By.className("toolbar_logo");
    private static final By NAME_OF_GROUP = By.xpath(".//h1[@class='mctc_name_tx']");
    private static final By DESCRIPTION_OF_GROUP = By.xpath(".//*[@class='group-info_block group-info_desc']");
    private static final By INFROM_CARD = By.xpath(".//*[@id='hook_Block_LeftColumnTopCard']");
    private static final By BUTTON_OF_DROPDOWN_MENU = By.xpath(".//span[@class='u-menu_a toggle-dropdown']");
    private static final By BUTTON_DELETE_GROUP = By.xpath(".//a[contains(@hrefattrs,'CardButtonsRemoveAltGroup')]");
    private static final By BUTTON_INVITE = By.xpath(".//a[contains(@hrefattrs,'AltGroupTopCardButtonsInviteFriends')]");


    public GroupPage(WebDriver driver) {
        super(driver);
    }

    public boolean checkGroupName(String expectedName){
        return expectedName.equals(driver.findElement(NAME_OF_GROUP).getText());
    }

    public InviteFriendsLayer clickInviteButton(){
        click(BUTTON_INVITE, "Пропала кнопка Пригласить друзей");
        return new InviteFriendsLayer(driver);
    }

    public void deleteGroup(){
        click(BUTTON_OF_DROPDOWN_MENU, "Пропала кнопка выпадающего меню");
        Assert.assertTrue("Не прогрузилась кнопка Удалить",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(BUTTON_DELETE_GROUP),
                        5,500));
        click(BUTTON_DELETE_GROUP, "Пропала кнопка Удалить");
        new ConfirmDeletingGroupLayer(driver);
    }

    @Override
    protected void check() {
        Assert.assertTrue("Не прогрузился логотип",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(LOGO),
                        5, 500));
        Assert.assertTrue("Не прогрулось название группы",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(NAME_OF_GROUP),
                        5,500));
        Assert.assertTrue("Не прогрузилась кнопка выпадающего меню",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(BUTTON_OF_DROPDOWN_MENU),
                        5, 500));
        Assert.assertTrue("Не прогрузилось информация о группе",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(INFROM_CARD),
                        5, 500));
        Assert.assertTrue("Не прогрузилась кнопка Пригласить друзей",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(BUTTON_INVITE),
                        5, 500));
    }
}
