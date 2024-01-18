package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.ui.matchers.CustomViewAction;
import ru.iteco.fmhandroid.ui.pages.AboutPage;
import ru.iteco.fmhandroid.ui.pages.AppBarPanel;
import ru.iteco.fmhandroid.ui.pages.MainPage;
import ru.iteco.fmhandroid.ui.pages.NewsPage.ControlPanelPage;
import ru.iteco.fmhandroid.ui.pages.NewsPage;
import ru.iteco.fmhandroid.ui.pages.OurMissionPage;

public class OpenPage {
    // Methods for opening application pages
    public static void main() {
        Allure.step("Переход к \"Главной\" странице:");
        try {
            AppBarPanel.clickOnMainMenuButton();
            AppBarPanel.clickOnMainPageButton();
        }
        catch (Exception e){
            try {
                AppBarPanel.clickOnAboutBackButton();
                AppBarPanel.clickOnMainMenuButton();
                AppBarPanel.clickOnMainPageButton();
            }
            catch (Exception e1){
            }
        }
        MainPage.newsContainer.checkWithTimeout(matches(isDisplayed()));
    }

    public static void news() {
        Allure.step("Переход к странице \"Новости\":");
        try {
            AppBarPanel.clickOnMainMenuButton();
            AppBarPanel.clickOnNewsPageButton();
        }
        catch (Exception e){
            try {
                AppBarPanel.clickOnAboutBackButton();
                AppBarPanel.clickOnMainMenuButton();
                AppBarPanel.clickOnNewsPageButton();
            }
            catch (Exception e1){
            }
        }
        NewsPage.newsContainer.checkWithTimeout(matches(isDisplayed()));
    }

    public static void newsControlPanel() {
        try {
            ControlPanelPage.title.check(matches(isDisplayed()));
        }
        catch (Exception e){
            Allure.step("Переход к странице \"Панель управления\":");
            try {
                NewsPage.newsContainer.check(matches(isDisplayed()));
                NewsPage.clickOnEditButton();
            }
            catch (Exception e1) {
                try {
                    OpenPage.news();
                    NewsPage.clickOnEditButton();
                    ControlPanelPage.title.checkWithTimeout(matches(isDisplayed()));
                }
                catch (Exception e2){
                }
            }
        }
    }

    public static void about() {
        Allure.step("Переход к странице \"О приложении\":");
        try{
            NewsPage.newsContainer.checkWithTimeout(matches(isDisplayed()));
            CustomViewAction.returnBack();
        }
        catch (Exception e) {
        }
        AppBarPanel.clickOnMainMenuButton();
        AppBarPanel.clickOnAboutPageButton();
        AboutPage.aboutLabel.checkWithTimeout(matches(isDisplayed()));
    }

    public static void ourMission() {
        Allure.step("Переход к странице тематических цитат:");
        try {
            AppBarPanel.clickOnOurMissionButton();
        }
        catch (Exception e) {
            AppBarPanel.clickOnAboutBackButton();
            AppBarPanel.clickOnOurMissionButton();
        }
        OurMissionPage.title.checkWithTimeout(matches(isDisplayed()));
    }
}
