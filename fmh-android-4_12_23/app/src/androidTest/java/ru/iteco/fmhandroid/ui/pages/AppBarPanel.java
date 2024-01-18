package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.iteco.fmhandroid.ui.matchers.TimeoutEspresso.onViewWithTimeout;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.matchers.TimeoutEspresso;

public class AppBarPanel {
    private static String logOutButtonText = "Выйти";
    private static String aboutPageButtonText = "О приложении";
    private static String mainPageButtonText = "Главная";
    private static String newsPageButtonText = "Новости";

    public static TimeoutEspresso.TimedViewInteraction authorizationButton =
            onViewWithTimeout(withId(R.id.authorization_image_button));
    public static TimeoutEspresso.TimedViewInteraction logOutButton =
            onViewWithTimeout(withText(logOutButtonText));
    public static TimeoutEspresso.TimedViewInteraction ourMissionButton =
            onViewWithTimeout(withId(R.id.our_mission_image_button));
    public static TimeoutEspresso.TimedViewInteraction mainImage =
            onViewWithTimeout(withId(R.id.trademark_image_view));
    public static TimeoutEspresso.TimedViewInteraction mainMenuButton =
            onViewWithTimeout(withId(R.id.main_menu_image_button));
    public static TimeoutEspresso.TimedViewInteraction mainPageButton =
            onViewWithTimeout(withText(mainPageButtonText));
    public static TimeoutEspresso.TimedViewInteraction newsPageButton =
            onViewWithTimeout(withText(newsPageButtonText));
    public static TimeoutEspresso.TimedViewInteraction aboutPageButton =
            onViewWithTimeout(withText(aboutPageButtonText));
    public static TimeoutEspresso.TimedViewInteraction aboutBackButton =
            onViewWithTimeout(withId(R.id.about_back_image_button));
    public static TimeoutEspresso.TimedViewInteraction topLineText =
            onViewWithTimeout(withId(R.id.custom_app_bar_title_text_view));
    public static TimeoutEspresso.TimedViewInteraction bottomLineText =
            onViewWithTimeout(withId(R.id.custom_app_bar_sub_title_text_view));


    public static void clickOnMainMenuButton() {
        mainMenuButton.performWithTimeout(click());
    }

    public static void clickOnAuthButton() {
        authorizationButton.performWithTimeout(click());
        Allure.step("Клик по иконке \"Авторизация\" панели AppBar");
    }

    public static void clickOnLogOutButton() {
        Allure.step("Клик по всплывающей кнопке \"Выйти\"");
        logOutButton.performWithTimeout(click());
    }

    public static void clickOnMainPageButton() {
        Allure.step("Клик по кнопке \"Главная\" бургерного меню");
        mainPageButton.performWithTimeout(click());
    }

    public static void clickOnNewsPageButton() {
        Allure.step("Клик по кнопке \"Новости\" бургерного меню");
        newsPageButton.performWithTimeout(click());
    }

    public static void clickOnAboutPageButton() {
        Allure.step("Клик по кнопке \"О приложении\" бургерного меню");
        aboutPageButton.performWithTimeout(click());
    }

    public static void clickOnOurMissionButton() {
        Allure.step("Клик по иконке тематических цитат AppBar панели");
        ourMissionButton.performWithTimeout(click());
    }

    public static void clickOnAboutBackButton() {
        Allure.step("Клик по иконке \"Назад\" AppBar панели");
        aboutBackButton.performWithTimeout(click());
    }
}
