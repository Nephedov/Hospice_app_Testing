package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;


import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.ui.data.DataGenerator;
import ru.iteco.fmhandroid.ui.matchers.CustomViewAction;
import ru.iteco.fmhandroid.ui.pages.AppBarPanel;
import ru.iteco.fmhandroid.ui.pages.AuthorizationPage;
import ru.iteco.fmhandroid.ui.pages.MainPage;

public class Authorization {
    private AuthorizationPage authorizationPage = new AuthorizationPage();
    private AppBarPanel appBarPanel = new AppBarPanel();

    public void validLogin() {
        Allure.step("Авторизация валидного пользователя:");
        DataGenerator.User validUser = new DataGenerator().getValidUser();
        auth(validUser.getLogin(), validUser.getPassword());
    }

    public void invalidLogin() {
        Allure.step("Авторизация невалидного пользователя:");
        DataGenerator.User invalidUser = new DataGenerator().getInvalidUser();
        auth(invalidUser.getLogin(), invalidUser.getPassword());
    }

    public void tryLogIn() {
    // LogIn method for tests Before/After
        try {
            authorizationPage.title.checkWithTimeout(matches(isDisplayed()));
        }
        catch (Exception e1){
            tryLogOut();
            authorizationPage.title.checkWithTimeout(matches(isDisplayed()));
        }
        validLogin();
        new MainPage().newsContainer.checkWithTimeout(matches(isDisplayed()));
    }

    public void tryLogOut() {
        // LogOut method for tests Before/After
        try {
            authorizationPage.title.checkWithTimeout(matches(isDisplayed()));
        } catch (Exception e) {
            try {
                Allure.step("Выход из аккаунта:");
                try {
                    appBarPanel.clickOnAuthButton();
                } catch (Exception e1) {
                    CustomViewAction.returnBack();
                    appBarPanel.clickOnAuthButton();
                }
                appBarPanel.clickOnLogOutButton();
                authorizationPage.title.checkWithTimeout(matches(isDisplayed()));
            } catch (Exception e1) {
            }
        }
    }

    public void auth(String login, String password) {

        authorizationPage.insertInLoginField(login);
        authorizationPage.insertInPasswordField(password);
        authorizationPage.clickOnEnterButton();
    }
}
