package ru.iteco.fmhandroid.ui.tests.functional;
import static android.content.Intent.ACTION_VIEW;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;

import static org.hamcrest.Matchers.allOf;
import static ru.iteco.fmhandroid.ui.steps.Authorization.tryLogIn;

import android.net.Uri;

import androidx.test.espresso.intent.Intents;
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
import ru.iteco.fmhandroid.ui.pages.AboutPage;
import ru.iteco.fmhandroid.ui.steps.OpenPage;
@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class ExternalLinksTest {
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

    @Epic(value = "Функциональное тестирование")
    @Feature(value = "Внешние ссылки")
    @Story(value = "Переход по ссылке о Пользовательском Соглашении")
    @Test
    @Description(value = "Тест проверяет переход по ссылке о Пользовательском Соглашении при клике по ней")
    public void shouldCheckTermOfUseLinkIsActive() {
        String termOfUseData = "https://vhospice.org/#/terms-of-use";

        Intents.init();

        AboutPage.clickOnTermOfUseLink();
        intended(allOf(
                        hasData(Uri.parse(termOfUseData)),
                        hasAction(ACTION_VIEW)));

        Intents.release();
    }

    @Epic(value = "Функциональное тестирование")
    @Feature(value = "Внешние ссылки")
    @Story(value = "Переход по ссылке о Политике Конфиденциальности")
    @Test
    @Description(value = "Тест проверяет переход по ссылке о Политике Конфиденциальности при клике по ней")
    public void shouldCheckPrivacyPolicyLinkIsActive() {
        String termOfUseData = "https://vhospice.org/#/privacy-policy";

        Intents.init();

        AboutPage.clickOnPrivacyPolicyLink();
        intended(allOf(
                hasData(Uri.parse(termOfUseData)),
                hasAction(ACTION_VIEW)));

        Intents.release();
    }
}
