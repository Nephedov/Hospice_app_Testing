package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static ru.iteco.fmhandroid.ui.matchers.TimeoutEspresso.onViewWithTimeout;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.matchers.TimeoutEspresso;
import ru.iteco.fmhandroid.ui.matchers.ToastMatcher;

public class AuthorizationPage {
    private static String authTitle = "Авторизация";
    private static String loginHint = "Логин";
    private static String passwordHint = "Пароль";

    public static TimeoutEspresso.TimedViewInteraction appBarPanel =
            onViewWithTimeout(withId(R.id.container_custom_app_bar_include_on_fragment_main));
    public static TimeoutEspresso.TimedViewInteraction title =
            onViewWithTimeout(withText(authTitle));
    public static TimeoutEspresso.TimedViewInteraction loginField =
            onViewWithTimeout(allOf(withHint(loginHint),
                    withParent(withParent(withId(R.id.login_text_input_layout)))));
    public static TimeoutEspresso.TimedViewInteraction passwordField =
            onViewWithTimeout(allOf(withHint(passwordHint),
                    withParent(withParent(withId(R.id.password_text_input_layout)))));
    public static TimeoutEspresso.TimedViewInteraction enterButton =
            onViewWithTimeout(withId(R.id.enter_button));

    public static void insertInLoginField(String login) {
        Allure.step("Ввод \"" + login + "\" в поле ввода \"Логин\"");
        loginField.performWithTimeout(replaceText(login), closeSoftKeyboard());
    }

    public static void insertInPasswordField(String password) {
        Allure.step("Ввод \"" + password + "\" в поле ввода \"Пароль\"");
        passwordField.performWithTimeout(replaceText(password), closeSoftKeyboard());
    }

    public static void clickOnEnterButton() {
        Allure.step("Клик по кнопке \"Войти\"");
        enterButton.performWithTimeout(click());
    }
}