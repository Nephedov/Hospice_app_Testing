package ru.iteco.fmhandroid.ui.tests.functional;

import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

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
import ru.iteco.fmhandroid.ui.data.DataGenerator;
import ru.iteco.fmhandroid.ui.matchers.ToastMatcher;
import ru.iteco.fmhandroid.ui.pages.NewsPage;
import ru.iteco.fmhandroid.ui.pages.NewsPage.ControlPanelPage;
import ru.iteco.fmhandroid.ui.pages.NewsPage.ControlPanelPage.CreateEditForm;
import ru.iteco.fmhandroid.ui.steps.Authorization;
import ru.iteco.fmhandroid.ui.steps.OpenPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class NewsTest {
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    @Rule
    public ScreenshotRule screenshotRuleFailure =
            new ScreenshotRule(ScreenshotRule.Mode.FAILURE, "test_failure");
    private Authorization auth = new Authorization();
    private NewsPage newsPage;
    private ControlPanelPage controlPanelPage;
    private CreateEditForm createEditForm;

    @Before
    public void setUp() {
        auth.tryLogIn();
        newsPage = new OpenPage().news();
        controlPanelPage = newsPage.new ControlPanelPage();
        createEditForm = controlPanelPage.new CreateEditForm();
    }
    @After
    public void tearDown() {
        auth.tryLogOut();
        newsPage = null;
        controlPanelPage = null;
        createEditForm = null;
    }
    @Epic(value = "Функциональное тестирование")
    @Feature(value = "Операции с новостями")
    @Story(value = "Добавление валидной новости")
    @Test
    @Description(value = "Тест проверяет добавление валидно заполненной новости")
    public void shouldCheckAddNewNews() {
        String newsTitle = DataGenerator.RandomString.getRandomRuString(5);

        // Adding valid news
        newsPage.addNews(newsTitle);

        // Checking that the news has been published
        new OpenPage().news();
        newsPage.newsWithTitle(newsTitle).checkWithTimeout(matches(isDisplayed()));

        // Deleting news
        newsPage.deleteNewsWithTitle(newsTitle);
    }

    @Epic(value = "Функциональное тестирование")
    @Feature(value = "Операции с новостями")
    @Story(value = "Добавление валидной новости")
    @Test
    @Description(value = "Тест проверяет добавление валидно заполненной новости на будущую дату")
    public void shouldCheckAddNewFutureNews() {
        String category = "Зарплата";
        String newsTitle = DataGenerator.RandomString.getRandomRuString(5);
        String futureDate = DataGenerator.getCurrentDatePlusDays(3);
        String time = DataGenerator.getCurrentTime();
        String description = newsTitle;

        // Adding valid news
        newsPage.addNews(category, newsTitle, futureDate, time, description);

        // Checking that the news has been added to the list
        controlPanelPage.swipeRefresh();
        controlPanelPage.newsWithTitle(newsTitle).checkWithTimeout(matches(isDisplayed()));

        // Checking that the news has not been published
        new OpenPage().news();
        newsPage.newsWithTitle(newsTitle).checkWithTimeout(doesNotExist());

        // Deleting news
        newsPage.deleteNewsWithTitle(newsTitle);
    }

    @Epic(value = "Функциональное тестирование")
    @Feature(value = "Операции с новостями")
    @Story(value = "Добавление не валидной новости")
    @Test
    @Description(value = "Тест проверяет добавление новости с незаполненными полями")
    public void shouldCheckNotAddNewEmptyNews() {
        String toastMessage = "Заполните пустые поля";

        new OpenPage().newsControlPanel();
        controlPanelPage.clickOnAddNewsButton();
        createEditForm.clickOnSaveButton();

        ToastMatcher.checkToastMessageIsDisplayed(toastMessage);
        createEditForm.categoryFieldAlertIcon.checkWithTimeout(matches(isDisplayed()));
        createEditForm.titleFieldAlertIcon.checkWithTimeout(matches(isDisplayed()));
        createEditForm.publicationDateFieldAlertIcon.checkWithTimeout(matches(isDisplayed()));
        createEditForm.publicationTimeFieldAlertIcon.checkWithTimeout(matches(isDisplayed()));
        createEditForm.descriptionFieldAlertIcon.checkWithTimeout(matches(isDisplayed()));
    }

    @Epic(value = "Функциональное тестирование")
    @Feature(value = "Операции с новостями")
    @Story(value = "Редактирование новости")
    @Test
    @Description(value = "Тест проверяет изменение описания новости")
    public void shouldCheckEditTitleNews() {
        String title = DataGenerator.RandomString.getRandomRuString(5);
        String newDescription = DataGenerator.RandomString.getRandomRuString(5);

        // Adding news
        newsPage.addNews(title);

        // Checking the description of the added news
        new OpenPage().news();
        newsPage.clickOnNews(title);
        newsPage.descriptionNewsWithTitle(title)
                .checkWithTimeout(matches(isDisplayed()))
                .check(matches(withText(title)));

        // Editing the description of the added news
        newsPage.editDescriptionNewsWithTitle(title, newDescription);

        // Checking the change in the description of the added news
        new OpenPage().news();
        newsPage.clickOnNews(title);
        newsPage.descriptionNewsWithTitle(title)
                .checkWithTimeout(matches(isDisplayed()))
                .check(matches(withText(newDescription)));

        // Deleting news after the test
        newsPage.deleteNewsWithTitle(title);
    }

    @Epic(value = "Функциональное тестирование")
    @Feature(value = "Операции с новостями")
    @Story(value = "Редактирование новости")
    @Test
    @Description(value = "Тест проверяет изменение статуса новости")
    public void shouldCheckEditStatusNews() {
        String title = DataGenerator.RandomString.getRandomRuString(5);
        String statusActive = "Активна";
        String statusNotActive = "Не активна";

        // Adding news
        newsPage.addNews(title);

        // Checking the status of the added news
        controlPanelPage.statusNewsWithTitle(title)
                .checkWithTimeout(matches(isDisplayed()))
                .check(matches(withText(statusActive)));

        // Changing the status of an added news item
        newsPage.changeStatusNewsWithTitle(title);

        // Checking the status change of the added news
        controlPanelPage.statusNewsWithTitle(title)
                .checkWithTimeout(matches(isDisplayed()))
                .check(matches(withText(statusNotActive)));

        // Deleting news after the test
        newsPage.deleteNewsWithTitle(title);
    }


    @Epic(value = "Функциональное тестирование")
    @Feature(value = "Операции с новостями")
    @Story(value = "Фильтр новостей")
    @Test
    @Description(value = "Тест проверяет отображение новостей с использованием фильтра")
    public void shouldCheckFilterNews() {
        String categoryAnnouncement = "Объявление";
        String categorySalary = "Зарплата";
        String titleAnnouncement = DataGenerator.RandomString.getRandomRuString(5);
        String titleSalary = DataGenerator.RandomString.getRandomRuString(5);

        // Adding news
        newsPage.addNews(titleAnnouncement);
        newsPage.addNews(categorySalary, titleSalary);

        new OpenPage().news();

        // Category filter
        newsPage.filterNewsByCategory(categoryAnnouncement);

        // Checking the display of news with the "ad" category
        newsPage.newsWithTitle(titleAnnouncement).checkWithTimeout(matches(isDisplayed()));
        // Checking the absence of news with the category "salary"
        newsPage.newsWithTitle(titleSalary).checkWithTimeout(doesNotExist());

        // Changing the category filter
        newsPage.filterNewsByCategory(categorySalary);

        // Checking the display of news with the category "salary"
        newsPage.newsWithTitle(titleSalary).checkWithTimeout(matches(isDisplayed()));
        // Checking the absence of news with the "ad" category
        newsPage.newsWithTitle(titleAnnouncement).checkWithTimeout(doesNotExist());

        // Deleting news after the test
        newsPage.deleteNewsWithTitle(titleAnnouncement);
        newsPage.deleteNewsWithTitle(titleSalary);
    }


    @Epic(value = "Функциональное тестирование")
    @Feature(value = "Операции с новостями")
    @Story(value = "Удаление новости")
    @Test
    @Description(value = "Тест проверяет удаление добавленной новости")
    public void shouldCheckDeleteAddedNews() {
        String newsTitle = DataGenerator.RandomString.getRandomRuString(5);

        // Adding valid news
        newsPage.addNews(newsTitle);

        // Checking that the news has been added to the list
        controlPanelPage.newsWithTitle(newsTitle).checkWithTimeout(matches(isDisplayed()));

        // Deleting news
        newsPage.deleteNewsWithTitle(newsTitle);

        // Checking that the news has been deleted
        controlPanelPage.swipeRefresh();
        controlPanelPage.newsWithTitle(newsTitle).checkWithTimeout(doesNotExist());
    }
}
