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
    public static void validLogin() {
        Allure.step("Авторизация валидного пользователя:");
        DataGenerator.User validUser = new DataGenerator().getValidUser();
        auth(validUser.getLogin(), validUser.getPassword());
    }

    public static void invalidLogin() {
        Allure.step("Авторизация невалидного пользователя:");
        DataGenerator.User invalidUser = new DataGenerator().getInvalidUser();
        auth(invalidUser.getLogin(), invalidUser.getPassword());
    }

    public static void tryLogIn() {
    // LogIn method for tests Before/After
        try {
            AuthorizationPage.title.checkWithTimeout(matches(isDisplayed()));
        }
        catch (Exception e1){
            tryLogOut();
            AuthorizationPage.title.checkWithTimeout(matches(isDisplayed()));
        }
        validLogin();
        MainPage.newsContainer.checkWithTimeout(matches(isDisplayed()));
    }

    public static void tryLogOut() {
        // LogOut method for tests Before/After
        try {
            AuthorizationPage.title.checkWithTimeout(matches(isDisplayed()));
        } catch (Exception e) {
            try {
                Allure.step("Выход из аккаунта:");
                try {
                    AppBarPanel.clickOnAuthButton();
                } catch (Exception e1) {
                    CustomViewAction.returnBack();
                    AppBarPanel.clickOnAuthButton();
                }
                AppBarPanel.clickOnLogOutButton();
                AuthorizationPage.title.checkWithTimeout(matches(isDisplayed()));
            } catch (Exception e1) {
            }
        }
    }

    public static void auth(String login, String password) {
        AuthorizationPage.insertInLoginField(login);
        AuthorizationPage.insertInPasswordField(password);
        AuthorizationPage.clickOnEnterButton();
    }
}
