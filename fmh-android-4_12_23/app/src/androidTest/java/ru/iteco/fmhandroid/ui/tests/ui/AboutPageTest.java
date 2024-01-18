package ru.iteco.fmhandroid.ui.tests.ui;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static ru.iteco.fmhandroid.ui.steps.Authorization.tryLogIn;
import static ru.iteco.fmhandroid.ui.steps.Authorization.tryLogOut;

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
import ru.iteco.fmhandroid.ui.pages.AboutPage;
import ru.iteco.fmhandroid.ui.pages.AppBarPanel;
import ru.iteco.fmhandroid.ui.steps.OpenPage;
@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class AboutPageTest {
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    @Rule
    public ScreenshotRule screenshotRuleFailure =
            new ScreenshotRule(ScreenshotRule.Mode.FAILURE, "test_failure");

    @Before
    public void setUp() {
        tryLogIn();
        OpenPage.about();
    }
    @After
    public void tearDown() {
        tryLogOut();
    }

    @Epic(value = "Тестирование UI")
    @Feature(value = "Панель AppBar")
    @Story(value = "AppBar на странице \"О приложении\"")
    @Test
    @Description(value = "Тест проверяет отображение AppBar панели на странице \"О приложении\"")
    public void shouldCheckAppBarOnAboutIsDisplayed() {
        AboutPage.appBarPanel.checkWithTimeout(matches(isDisplayed()));
    }

    @Epic(value = "Тестирование UI")
    @Feature(value = "Панель AppBar")
    @Story(value = "Лого AppBar на странице \"О приложении\"")
    @Test
    @Description(value = "Тест проверяет отображение главной иконки на панели AppBar на странице \"О приложении\"")
    public void shouldCheckAppBarLogoOnAboutIsDisplayed() {
        AppBarPanel.mainImage.checkWithTimeout(matches(isDisplayed()));
    }

    @Epic(value = "Тестирование UI")
    @Feature(value = "Страница \"О приложении\"")
    @Story(value = "Версия приложения")
    @Test
    @Description(value = "Тест проверяет отображение версии приложения")
    public void shouldCheckVersionIsDisplayed() {
        String versionText = "Версия:";
        String versionNumber = "1.0.0";

        AboutPage.versionText.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withText(versionText)));
        AboutPage.versionNumber.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withText(versionNumber)));
    }

    @Epic(value = "Тестирование UI")
    @Feature(value = "Страница \"О приложении\"")
    @Story(value = "Политика конфиденциальности")
    @Test
    @Description(value = "Тест проверяет отображение элементов о Политике Конфиденциальности")
    public void shouldCheckPrivacyPolicyElementsIsDisplayed() {
        String privacyPolicyText = "Политика конфиденциальности:";
        String privacyPolicyLink = "https://vhospice.org/#/privacy-policy";

        AboutPage.privacyPolicyText.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withText(privacyPolicyText)));
        AboutPage.privacyPolicyLink.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withText(privacyPolicyLink)));
    }
    @Epic(value = "Тестирование UI")
    @Feature(value = "Страница \"О приложении\"")
    @Story(value = "Пользовательское соглашение")
    @Test
    @Description(value = "Тест проверяет отображение элементов о Пользовательском Соглашении")
    public void shouldCheckTermOfUseTextIsDisplayed() {
        String privacyPolicyText = "Пользовательское соглашение:";
        String privacyPolicyLink = "https://vhospice.org/#/terms-of-use";

        AboutPage.termOfUseText.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withText(privacyPolicyText)));
        AboutPage.termOfUseLink.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withText(privacyPolicyLink)));
    }

    @Epic(value = "Тестирование UI")
    @Feature(value = "Страница \"О приложении\"")
    @Story(value = "Информация о компании")
    @Test
    @Description(value = "Тест проверяет отображение информации о компании")
    public void shouldCheckAboutLabelIsDisplayed() {
        String aboutLabel = "© Айтеко, 2022";

        AboutPage.aboutLabel.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withText(aboutLabel)));
    }
}
