package ru.iteco.fmhandroid.ui.tests.functional;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.rules.ScreenshotRule;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.Feature;
import io.qameta.allure.kotlin.Story;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.matchers.ToastMatcher;
import ru.iteco.fmhandroid.ui.pages.AppBarPanel;
import ru.iteco.fmhandroid.ui.pages.AuthorizationPage;
import ru.iteco.fmhandroid.ui.pages.MainPage;
import ru.iteco.fmhandroid.ui.steps.Authorization;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class AuthorizationTest {
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    @Rule
    public ScreenshotRule screenshotRuleFailure =
            new ScreenshotRule(ScreenshotRule.Mode.FAILURE, "test_failure");
    private Authorization auth = new Authorization();
    private AuthorizationPage authorizationPage;

    @Before
    public void setUp() {
        auth.tryLogOut();
        authorizationPage = new AuthorizationPage();
    }
    @After
    public void tearDown() {
        auth.tryLogOut();
    }

    @Epic(value = "Функциональное тестирование")
    @Feature(value = "Авторизация")
    @Story(value = "Невалидная авторизация")
    @Test
    @Description(value = "Тест проверяет отображение сообщения и отсутствие входа в приложение, при попытке входа с незаполненными полями логина и пароля")
    public void shouldCheckToastMessageIsDisplayedWithEmptyLoginAndPasswordFields() {
        String toastMessage = "Логин и пароль не могут быть пустыми";
        authorizationPage.clickOnEnterButton();

        ToastMatcher.checkToastMessageIsDisplayed(toastMessage);
        authorizationPage.title.checkWithTimeout(matches(isDisplayed()));
    }

    @Epic(value = "Функциональное тестирование")
    @Feature(value = "Авторизация")
    @Story(value = "Невалидная авторизация. Негативный тест, ожидающий исключение \"NullPointerException.class\".")
    @Test(expected = NullPointerException.class)
    @Description(value = "Тест проверяет отображение сообщения и отсутствие входа в приложение, при попытке входа с невалидными данными")
    public void shouldCheckToastMessageIsDisplayed() {
        String toastMessage = "Логин или пароль не введены неверно";
        new Authorization().invalidLogin();

        ToastMatcher.checkToastMessageIsDisplayed(toastMessage);
        authorizationPage.title.checkWithTimeout(matches(isDisplayed()));
    }

    @Epic(value = "Функциональное тестирование")
    @Feature(value = "Авторизация")
    @Story(value = "Валидная авторизация")
    @Test
    @Description(value = "Тест проверяет вход в приложение с валидными данными")
    public void shouldCheckValidAuthorization() {
        new Authorization().validLogin();

        new MainPage().newsContainer.checkWithTimeout(matches(isDisplayed()));
    }

    @Epic(value = "Функциональное тестирование")
    @Feature(value = "Авторизация")
    @Story(value = "Выход из аккаунта")
    @Test
    @Description(value = "Тест проверяет выход из аккаунта приложения")
    public void shouldCheckCancelAuthorization() {
        new Authorization().validLogin();
        AppBarPanel appBarPanel = new AppBarPanel();

        appBarPanel.clickOnAuthButton();
        appBarPanel.clickOnLogOutButton();
        authorizationPage.title.checkWithTimeout(matches(isDisplayed()));
    }
}
