package lection3.tests;

import lection3.BaseTest;
import lection3.pages.DashboardPage;
import lection3.pages.LoginPage;

import org.openqa.selenium.support.events.EventFiringWebDriver;

public class MyTest extends BaseTest {

    public static void main(String[] args) {

        EventFiringWebDriver driver = getConfiguredDriver();

        LoginPage loginPage = new LoginPage(driver);

        loginPage.open();
        loginPage.fillEmailInput();
        loginPage.fillPasswordInput();
        loginPage.clickLoginButton();

        DashboardPage dashboardPage = new DashboardPage(driver);

        dashboardPage.selectOrdersItem();
        dashboardPage.addNewCategory("Men");
        dashboardPage.filterCategory("Men");

        quitDriver(driver);
    }
}