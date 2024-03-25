package ru.iteco.fmhandroid.ui.tests.functional;

import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.not;

import androidx.test.espresso.ViewAssertion;
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
import ru.iteco.fmhandroid.ui.pages.ControlPanelPage;
import ru.iteco.fmhandroid.ui.pages.NewsPage;
import ru.iteco.fmhandroid.ui.steps.Authorization;
import ru.iteco.fmhandroid.ui.steps.NewsActions;
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
    private ControlPanelPage.CreateEditForm createEditForm;
    private NewsActions newsActions;

    @Before
    public void setUp() {
        auth.tryLogIn();
        newsPage = new OpenPage().news();
        controlPanelPage = new ControlPanelPage();
        createEditForm = controlPanelPage.new CreateEditForm();
        newsActions = new NewsActions();
    }
    @After
    public void tearDown() {
        auth.tryLogOut();
        newsPage = null;
        controlPanelPage = null;
        createEditForm = null;
        newsActions = null;
    }
    @Epic(value = "Функциональное тестирование")
    @Feature(value = "Операции с новостями")
    @Story(value = "Добавление валидной новости")
    @Test
    @Description(value = "Тест проверяет добавление валидно заполненной новости")
    public void shouldCheckAddNewNews() {
        String newsTitle = DataGenerator.RandomString.getRandomRuString(5);

        // Adding valid news
        newsActions.addNews(newsTitle);

        // Checking that the news has been published
        new OpenPage().news();
        newsPage.scrollToNewsWithTitle(newsTitle);
        newsPage.newsWithTitle(newsTitle).checkWithTimeout(matches(isDisplayed()));

        // Deleting news
        newsActions.deleteNewsWithTitle(newsTitle);
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
        newsActions.addNews(category, newsTitle, futureDate, time, description);

        // Checking that the news has been added to the list
        controlPanelPage.scrollToNewsWithTitle(newsTitle);
        controlPanelPage.newsWithTitle(newsTitle).checkWithTimeout(matches(isDisplayed()));

        // Checking that the news has not been published
        new OpenPage().news();
        newsPage.newsWithTitle(newsTitle).checkWithTimeout(doesNotExist());

        // Deleting news
        newsActions.deleteNewsWithTitle(newsTitle);
    }

    @Epic(value = "Функциональное тестирование")
    @Feature(value = "Операции с новостями")
    @Story(value = "Добавление не валидной новости")
    @Test
    @Description(value = "Тест проверяет добавление новости с незаполненными полями")
    public void shouldCheckNotAddNewEmptyNews() {
        String toastMessage = "Заполните пустые поля";

        new OpenPage().controlPanel();
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
        newsActions.addNews(title);

        // Checking the description of the added news
        new OpenPage().news();
        newsPage.clickOnNews(title);
        newsPage.descriptionNewsWithTitle(title)
                .checkWithTimeout(matches(isDisplayed()))
                .check(matches(withText(title)));

        // Editing the description of the added news
        newsActions.editDescriptionNewsWithTitle(title, newDescription);

        // Checking the change in the description of the added news
        new OpenPage().news();
        newsPage.clickOnNews(title);
        newsPage.descriptionNewsWithTitle(title)
                .checkWithTimeout(matches(isDisplayed()))
                .check(matches(withText(newDescription)));

        // Deleting news after the test
        newsActions.deleteNewsWithTitle(title);
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
        newsActions.addNews(title);

       // Checking the status of the added news
        controlPanelPage.scrollToNewsWithTitle(title);
        controlPanelPage.statusNewsWithTitle(title, statusActive)
                .checkWithTimeout(matches(isDisplayed()));

        // Changing the status of an added news item
        newsActions.changeStatusNewsWithTitle(title);

        // Checking the status change of the added news
        controlPanelPage.scrollToNewsWithTitle(title);
        controlPanelPage.statusNewsWithTitle(title, statusNotActive)
                .checkWithTimeout(matches(isDisplayed()));

        // Deleting news after the test
        newsActions.deleteNewsWithTitle(title);
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
        newsActions.addNews(titleAnnouncement);
        newsActions.addNews(categorySalary, titleSalary);

        new OpenPage().news();

        // Category filter
        newsPage.filterNewsByCategory(categoryAnnouncement);

        // Checking the display of news with the "ad" category
        newsPage.scrollToNewsWithTitle(titleAnnouncement);
        newsPage.newsWithTitle(titleAnnouncement).checkWithTimeout(matches(isDisplayed()));
        // Checking the absence of news with the category "salary"
        newsPage.newsWithTitle(titleSalary).checkWithTimeout(doesNotExist());

        // Changing the category filter
        newsPage.filterNewsByCategory(categorySalary);

        // Checking the display of news with the category "salary"
        newsPage.scrollToNewsWithTitle(titleSalary);
        newsPage.newsWithTitle(titleSalary).checkWithTimeout(matches(isDisplayed()));
        // Checking the absence of news with the "ad" category
        newsPage.newsWithTitle(titleAnnouncement).checkWithTimeout(doesNotExist());

        // Deleting news after the test
        newsActions.deleteNewsWithTitle(titleAnnouncement);
        newsActions.deleteNewsWithTitle(titleSalary);
    }


    @Epic(value = "Функциональное тестирование")
    @Feature(value = "Операции с новостями")
    @Story(value = "Удаление новости")
    @Test
    @Description(value = "Тест проверяет удаление добавленной новости")
    public void shouldCheckDeleteAddedNews() {
        String newsTitle = DataGenerator.RandomString.getRandomRuString(5);

        // Adding valid news
        newsActions.addNews(newsTitle);

        // Checking that the news has been added to the list
        controlPanelPage.scrollToNewsWithTitle(newsTitle);
        controlPanelPage.newsWithTitle(newsTitle).checkWithTimeout(matches(isDisplayed()));

        // Deleting news
        newsActions.deleteNewsWithTitle(newsTitle);

        // Checking that the news has been deleted
        controlPanelPage.swipeRefresh();
        controlPanelPage.newsWithTitle(newsTitle).checkWithTimeout(doesNotExist());
    }
}
