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
    private AppBarPanel appBarPanel = new AppBarPanel();
    private NewsPage newsPage = new NewsPage();
    private ControlPanelPage controlPanelPage = new NewsPage().new ControlPanelPage();
    // Methods for opening application pages
    public void main() {
        Allure.step("Переход к \"Главной\" странице:");
        try {
            appBarPanel.clickOnMainMenuButton();
            appBarPanel.clickOnMainPageButton();
        }
        catch (Exception e){
            try {
                appBarPanel.clickOnAboutBackButton();
                appBarPanel.clickOnMainMenuButton();
                appBarPanel.clickOnMainPageButton();
            }
            catch (Exception e1){
            }
        }
        new MainPage().newsContainer.checkWithTimeout(matches(isDisplayed()));
    }

    public void news() {
        Allure.step("Переход к странице \"Новости\":");
        try {
            appBarPanel.clickOnMainMenuButton();
            appBarPanel.clickOnNewsPageButton();
        }
        catch (Exception e){
            try {
                appBarPanel.clickOnAboutBackButton();
                appBarPanel.clickOnMainMenuButton();
                appBarPanel.clickOnNewsPageButton();
            }
            catch (Exception e1){
            }
        }
        newsPage.newsContainer.checkWithTimeout(matches(isDisplayed()));
    }

    public void newsControlPanel() {
        try {
            controlPanelPage.title.check(matches(isDisplayed()));
        }
        catch (Exception e){
            Allure.step("Переход к странице \"Панель управления\":");
            try {
                newsPage.newsContainer.check(matches(isDisplayed()));
                newsPage.clickOnEditButton();
            }
            catch (Exception e1) {
                try {
                    news();
                    newsPage.clickOnEditButton();
                    controlPanelPage.title.checkWithTimeout(matches(isDisplayed()));
                }
                catch (Exception e2){
                }
            }
        }
    }

    public void about() {
        Allure.step("Переход к странице \"О приложении\":");
        try{
            newsPage.newsContainer.checkWithTimeout(matches(isDisplayed()));
            CustomViewAction.returnBack();
        }
        catch (Exception e) {
        }
        appBarPanel.clickOnMainMenuButton();
        appBarPanel.clickOnAboutPageButton();
        new AboutPage().aboutLabel.checkWithTimeout(matches(isDisplayed()));
    }

    public void ourMission() {
        Allure.step("Переход к странице тематических цитат:");
        try {
            appBarPanel.clickOnOurMissionButton();
        }
        catch (Exception e) {
            appBarPanel.clickOnAboutBackButton();
            appBarPanel.clickOnOurMissionButton();
        }
        new OurMissionPage().title.checkWithTimeout(matches(isDisplayed()));
    }
}
