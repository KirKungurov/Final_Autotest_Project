package core.layers;

import core.wrappers.FriendWrapper;
import core.transformers.FriendsTransformer;
import core.pages.BasePage;
import core.pages.GroupPage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Collections;
import java.util.List;

public class InviteFriendsLayer extends BasePage {
    private static final By BUTTON_INVITE = By.xpath(".//*[@class='modal-new_actions __center']" +
            "//input[@data-l='t,confirm']");
    private static final By BUTTON_CANCEL = By.xpath(".//*[@class='modal-new_actions __center']" +
            "//a[@data-l='t,cancel']");
    private static final By FRIEND_CARD = By.xpath(".//div[@class='ucard-v']");

    public InviteFriendsLayer(WebDriver driver) {
        super(driver);
    }

    public GroupPage sendInvite(){
        click(BUTTON_INVITE, "Пропала кнопка Пригласить");
        return new GroupPage(driver);
    }

    public List<FriendWrapper> findAllFriends(){
        if (isElementPresent(FRIEND_CARD)){
            return FriendsTransformer.wrap(driver.findElements(FRIEND_CARD), driver);
        }
        return Collections.emptyList();
    }

    public FriendWrapper chooseNeededFriend(String expectedName){
        List<FriendWrapper> friends = findAllFriends();

        for (FriendWrapper friend: friends){
            if (friend.equals(expectedName)){
                return friend;
            }
        }
        return  null;

    }

    @Override
    protected void check() {
        Assert.assertTrue("Не прогрузилась кнопка Пригласить",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(BUTTON_INVITE),
                        5, 500));
        Assert.assertTrue("Не прогрузилась кнопка Отменить",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(BUTTON_CANCEL),
                        5, 500));

    }
}
