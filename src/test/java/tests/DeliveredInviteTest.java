package tests;

import configs.Config;
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
    private WebDriver secondDriver;
    private String baseUrl;

    boolean isInviteDelivered = false;
    boolean isFriendFounded = false;

    @Test
    public void checkInvite() throws Exception{
        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        String minTime = "";
        String maxTime = "";


        new LoginPage(driver).login(Config.loginOfFirstBot, Config.passwordOfFirstBot);
        new MainPage(driver).clickToGroups();
        new GroupsPage(driver).clickToCreateButton();
        new GroupCategoryLayer(driver).selectCategory();
        new CreateGroupLayer(driver).CrateGroup(Config.nameOfCreatingGroup);

        GroupPage groupPage = new GroupPage(driver);
        InviteFriendsLayer inviteFriendsLayer = groupPage.clickInviteButton();

        List<FriendWrapper> friends = inviteFriendsLayer.findAllFriends();

        for (FriendWrapper friend: friends){
            if (friend.equals(Config.nameOfChoosenFriend)){
                inviteFriendsLayer = friend.chooseMan();
                isFriendFounded = true;
                minTime = time.minusMinutes(1).format(formatter);
                maxTime = time.plusMinutes(1).format(formatter);
                break;
            }
        }

        Assert.assertTrue("Нет неоходимого друга", isFriendFounded);
        groupPage = inviteFriendsLayer.sendInvite();

        baseUrl = "https://ok.ru";
        secondDriver = new ChromeDriver();
        secondDriver.manage().window().maximize();
        secondDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        secondDriver.get(baseUrl + "/");

        new LoginPage(secondDriver).login(Config.loginOfSecondBut, Config.passwordOfSecondBot);
        new MainPage(secondDriver).clickToAlert();

        AlertsLayer alertsLayer = new AlertsLayer(secondDriver);
        alertsLayer.clickToGroups();

        List<AlertsWrapper> alerts = alertsLayer.findAllAlerts();

        for (AlertsWrapper alert: alerts){
            if (alert.equals(Config.nameOfCreator, Config.nameOfCreatingGroup, maxTime, minTime)){
                isInviteDelivered = true;
                break;
            }
        }
        Assert.assertTrue("Приглашение не дошло",
                isInviteDelivered);

        secondDriver.quit();

        groupPage.deleteGroup();
        new ConfirmDeletingGroupLayer(driver).confirmDeleting();

        driver.quit();
    }

    @After
    public void tearDown() throws Exception{
        driver.quit();
        if (isFriendFounded){
            secondDriver.quit();
        }
    }
}
