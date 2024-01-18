package ru.iteco.fmhandroid.ui.tests.ui;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static ru.iteco.fmhandroid.ui.steps.Authorization.tryLogOut;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

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
import ru.iteco.fmhandroid.ui.pages.AppBarPanel;
import ru.iteco.fmhandroid.ui.pages.AuthorizationPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class AuthorizationPageTest {
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    @Rule
    public ScreenshotRule screenshotRuleFailure =
            new ScreenshotRule(ScreenshotRule.Mode.FAILURE, "test_failure");

    @Before
    public void setUp() {
        tryLogOut();
    }

    @Epic(value = "Тестирование UI")
    @Feature(value = "Панель AppBar")
    @Story(value = "AppBar на странице авторизации")
    @Test
    @Description(value = "Тест проверяет отображение AppBar панели на странице авторизации")
    public void shouldCheckAppBarOnAuthIsDisplayed() {
        AuthorizationPage.appBarPanel.checkWithTimeout(matches(isDisplayed()));
    }

    @Epic(value = "Тестирование UI")
    @Feature(value = "Панель AppBar")
    @Story(value = "Лого AppBar на странице авторизации")
    @Test
    @Description(value = "Тест проверяет отображение главной иконки на панели AppBar на странице авторизации")
    public void shouldCheckAppBarLogoOnAuthIsDisplayed() {
        AppBarPanel.mainImage.checkWithTimeout(matches(isDisplayed()));
    }

    @Epic(value = "Тестирование UI")
    @Feature(value = "Страница Авторизации")
    @Story(value = "Заголовок страницы")
    @Test
    @Description(value = "Тест проверяет отображение заголовка")
    public void shouldCheckAuthTextViewIsDisplayed() {
        AuthorizationPage.title.checkWithTimeout(matches(isDisplayed()));
    }

    @Epic(value = "Тестирование UI")
    @Feature(value = "Страница Авторизации")
    @Story(value = "Поле ввода \"Логин\"")
    @Test
    @Description(value = "Тест проверяет отображение поля ввода логина")
    public void shouldCheckLoginFieldIsDisplayed() {
        AuthorizationPage.loginField.checkWithTimeout(matches(isDisplayed()));
    }

    @Epic(value = "Тестирование UI")
    @Feature(value = "Страница Авторизации")
    @Story(value = "Поле ввода \"Пароль\"")
    @Test
    @Description(value = "Тест проверяет отображение поля ввода пароля")
    public void shouldCheckPasswordFieldIsDisplayed() {
        AuthorizationPage.passwordField.checkWithTimeout(matches(isDisplayed()));
    }

    @Epic(value = "Тестирование UI")
    @Feature(value = "Страница Авторизации")
    @Story(value = "Кнопка \"Войти\"")
    @Test
    @Description(value = "Тест проверяет отображение кнопки входа")
    public void shouldCheckEnterButtonIsDisplayed() {
        String buttonText = "Войти";

        AuthorizationPage.enterButton.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withText(buttonText)));
    }
}
