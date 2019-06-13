package tests;

import configs.Config;
import core.Helper;
import core.pages.*;
import core.BaseTest;
import core.layers.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DeliveredInviteTest extends BaseTest {
    private final String NAME_OF_CREATING_GROUP = "Test of creation group";
    private final String NAME_OF_CREATER = "technopolisBot38 technopolisBot38";
    private final String NAME_OF_CHOOSEN_FRIEND = "technopolisBot2 technopolisBot2";

    private WebDriver secondDriver;

    @Before
    public void sendInvite(){
        new LoginPage(driver).login(Config.loginOfFirstBot, Config.passwordOfFirstBot);
        new MainPage(driver).clickToGroups();
        new GroupsPage(driver).clickToCreateButton();
        new GroupCategoryLayer(driver).selectCategory();
        new CreateGroupLayer(driver).CrateGroup(NAME_OF_CREATING_GROUP);
        GroupPage groupPage = new GroupPage(driver);
        //Открытие окна Пригласить друга
        InviteFriendsLayer inviteFriendsLayer = groupPage.clickInviteButton();

        Assert.assertNotNull("Нет неоходимого друга",
                inviteFriendsLayer.chooseNeededFriend(NAME_OF_CHOOSEN_FRIEND));
        //Выбор друга
        inviteFriendsLayer = inviteFriendsLayer.chooseNeededFriend(NAME_OF_CHOOSEN_FRIEND).chooseMan();
        //Отправка приглашения
        inviteFriendsLayer.sendInvite();

        secondDriver = new ChromeDriver();
        Helper.init(baseUrl, secondDriver);

    }

    @Test
    public void checkInvite() {
        new LoginPage(secondDriver).login(Config.loginOfSecondBut, Config.passwordOfSecondBot);
        new MainPage(secondDriver).clickToAlert();

        NotificationLayer notificationsLayer = new NotificationLayer(secondDriver);
        notificationsLayer.clickToGroups();

        Assert.assertNotNull("Приглашение не дошло",
                notificationsLayer.findNeededInvite(NAME_OF_CREATER, NAME_OF_CREATING_GROUP));

    }

    @After
    public void deleteGroup() {
        secondDriver.quit();
        Helper.deleteGroup(baseUrl, driver, NAME_OF_CREATING_GROUP);
    }
}
