package tests;

import configs.Config;
import core.Helper;
import core.wrappers.AlertsWrapper;
import core.BaseTest;
import core.wrappers.FriendWrapper;
import core.layers.*;
import core.pages.GroupPage;
import core.pages.GroupsPage;
import core.pages.LoginPage;
import core.pages.MainPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DeliveredInviteTest extends BaseTest {
    private final String NAME_OF_CREATING_GROUP = "Test of creation group";
    private final String NAME_OF_CREATER = "technopolisBot38 technopolisBot38";
    private final String NAME_OF_CHOOSEN_FRIEND = "technopolisBot2 technopolisBot2";

    private WebDriver secondDriver;

    boolean isInviteDelivered = false;
    boolean isFriendFounded = false;

    @Test
    public void checkInvite() {

        new LoginPage(driver).login(Config.loginOfFirstBot, Config.passwordOfFirstBot);
        new MainPage(driver).clickToGroups();
        new GroupsPage(driver).clickToCreateButton();
        new GroupCategoryLayer(driver).selectCategory();
        new CreateGroupLayer(driver).CrateGroup(NAME_OF_CREATING_GROUP);

        GroupPage groupPage = new GroupPage(driver);
        InviteFriendsLayer inviteFriendsLayer = groupPage.clickInviteButton();

        Assert.assertNotNull("Нет неоходимого друга",
                inviteFriendsLayer.chooseNeededFriend(NAME_OF_CHOOSEN_FRIEND));

        inviteFriendsLayer = inviteFriendsLayer.chooseNeededFriend(NAME_OF_CHOOSEN_FRIEND).chooseMan();
        groupPage = inviteFriendsLayer.sendInvite();

        secondDriver = new ChromeDriver();
        Helper.init(baseUrl, secondDriver);

        new LoginPage(secondDriver).login(Config.loginOfSecondBut, Config.passwordOfSecondBot);
        new MainPage(secondDriver).clickToAlert();

        AlertsLayer alertsLayer = new AlertsLayer(secondDriver);
        alertsLayer.clickToGroups();

        Assert.assertNotNull("Приглашение не дошло",
                alertsLayer.findNeededInvite(NAME_OF_CREATER, NAME_OF_CREATING_GROUP));

        secondDriver.quit();

        groupPage.deleteGroup();
        new ConfirmDeletingGroupLayer(driver).confirmDeleting();

        driver.quit();
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        if (isFriendFounded) {
            secondDriver.quit();
        }
    }
}
