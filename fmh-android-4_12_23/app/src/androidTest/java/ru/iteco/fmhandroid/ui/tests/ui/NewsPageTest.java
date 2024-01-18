package ru.iteco.fmhandroid.ui.tests.ui;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.not;

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
import ru.iteco.fmhandroid.ui.data.DataGenerator;
import ru.iteco.fmhandroid.ui.matchers.CustomViewAction;
import ru.iteco.fmhandroid.ui.pages.AppBarPanel;
import ru.iteco.fmhandroid.ui.pages.NewsPage;
import ru.iteco.fmhandroid.ui.pages.NewsPage.FilterForm;
import ru.iteco.fmhandroid.ui.steps.OpenPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class NewsPageTest {
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    @Rule
    public ScreenshotRule screenshotRuleFailure =
            new ScreenshotRule(ScreenshotRule.Mode.FAILURE, "test_failure");

    @Before
    public void setUp() {
        tryLogIn();
        OpenPage.news();
    }
    @After
    public void tearDown() {
        tryLogOut();
    }

    @Epic(value = "Тестирование UI")
    @Feature(value = "Панель AppBar")
    @Story(value = "AppBar на странице \"Новости\"")
    @Test
    @Description(value = "Тест проверяет отображение AppBar панели на странице \"Новости\"")
    public void shouldCheckAppBarOnNewsIsDisplayed() {
        NewsPage.appBarPanel.checkWithTimeout(matches(isDisplayed()));
    }

    @Epic(value = "Тестирование UI")
    @Feature(value = "Панель AppBar")
    @Story(value = "Лого AppBar на странице \"Новости\"")
    @Test
    @Description(value = "Тест проверяет отображение главной иконки на панели AppBar на странице \"Новости\"")
    public void shouldCheckAppBarLogoOnNewsIsDisplayed() {
        AppBarPanel.mainImage.checkWithTimeout(matches(isDisplayed()));
    }

    @Epic(value = "Тестирование UI")
    @Feature(value = "Страница \"Новости\"")
    @Story(value = "Заголовок")
    @Test
    @Description(value = "Тест проверяет отображение заголовка страницы новостей")
    public void shouldCheckNewsTitleIsDisplayed() {
        NewsPage.title.checkWithTimeout(matches(isDisplayed()));
    }


    @Epic(value = "Тестирование UI")
    @Feature(value = "Страница \"Новости\"")
    @Story(value = "Список новостей")
    @Test
    @Description(value = "Тест проверяет отображение списка новостей страницы новости")
    public void shouldCheckNewsListIsDisplayed() {
        NewsPage.newsList.checkWithTimeout(matches(isDisplayed()));
    }

    @Epic(value = "Тестирование UI")
    @Feature(value = "Страница \"Новости\"")
    @Story(value = "Кнопки управления")
    @Test
    @Description(value = "Тест проверяет отображение кнопок управления новостями")
    public void shouldCheckControlButtonsIsDisplayed() {
        NewsPage.sortButton.checkWithTimeout(matches(isDisplayed()));
        NewsPage.filterButton.checkWithTimeout(matches(isDisplayed()));
        NewsPage.editButton.checkWithTimeout(matches(isDisplayed()));
    }

    @Epic(value = "Тестирование UI")
    @Feature(value = "Страница \"Новости\"")
    @Story(value = "Отображение элементов новости")
    @Test()
    @Description(value = "Тест проверяет отображение элементов новости")
    public void shouldCheckNewNewsElementsIsDisplayed() {
        String category = "Зарплата";
        String title = DataGenerator.RandomString.getRandomRuString(5);
        String date = DataGenerator.getCurrentDate();
        String time = DataGenerator.getCurrentTime();
        String description = DataGenerator.RandomString.getRandomRuString(10);

        NewsPage.addNews(category, title, date, time, description);

        OpenPage.news();

        NewsPage.ItemNews.newsWithTitle(title).checkWithTimeout(matches(isDisplayed()));
        NewsPage.ItemNews.dateNewsWithTitle(title).checkWithTimeout(matches(isDisplayed()))
                .check(matches(withText(date)));
        NewsPage.ItemNews.iconCategoryNewsWithTitle(title).checkWithTimeout(matches(isDisplayed()));
        NewsPage.ItemNews.dropButtonNewsWithTitle(title).checkWithTimeout(matches(isDisplayed()));
        NewsPage.ItemNews.descriptionNewsWithTitle(title).checkWithTimeout(matches(not(isDisplayed())));

        NewsPage.ItemNews.clickOnNews(title);
        NewsPage.ItemNews.descriptionNewsWithTitle(title).checkWithTimeout(matches(isDisplayed()))
                .check(matches(withText(description)));

        NewsPage.deleteNewsWithTitle(title);
    }

    @Epic(value = "Тестирование UI")
    @Feature(value = "Страница \"Новости\"")
    @Story(value = "Форма фильтра новостей")
    @Test
    @Description(value = "Тест проверяет отображение элементов формы фильтра")
    public void shouldCheckFilterFormElementsIsDisplayed() {
        String formTitle = "Фильтровать новости";
        String categoryHint = "Категория";
        String dateFormat = "ДД.ММ.ГГГГ";
        String acceptButton = "Фильтровать";
        String cancelButton = "Отмена";

        NewsPage.clickOnFilterButton();

        FilterForm.title.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withText(formTitle)));
        FilterForm.categoryField.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withHint(categoryHint)));
        FilterForm.dateStartField.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withHint(dateFormat)));
        FilterForm.dateEndField.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withHint(dateFormat)));
        FilterForm.acceptButton.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withText(acceptButton)));
        FilterForm.cancelButton.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withText(cancelButton)));
    }
    @Epic(value = "Тестирование UI")
    @Feature(value = "Страница \"Новости\"")
    @Story(value = "Форма фильтра новостей")
    @Test
    @Description(value = "Тест проверяет отображение категорий формы фильтра")
    public void shouldCheckFilterFormCategoriesIsDisplayed() {
        String birthday = "День рождения";
        String salary = "Зарплата";
        String tradeUnion = "Профсоюз";
        String holiday = "Праздник";
        String massage = "Массаж";
        String gratitude = "Благодарность";
        String helpIsNeeded = "Нужна помощь";

        NewsPage.clickOnFilterButton();
        NewsPage.FilterForm.clickOnCategoryField();

        NewsPage.FilterForm.category(birthday).checkWithTimeout(matches(isDisplayed()));
        NewsPage.FilterForm.category(salary).checkWithTimeout(matches(isDisplayed()));
        NewsPage.FilterForm.category(tradeUnion).checkWithTimeout(matches(isDisplayed()));
        NewsPage.FilterForm.category(holiday).checkWithTimeout(matches(isDisplayed()));
        NewsPage.FilterForm.category(massage).checkWithTimeout(matches(isDisplayed()));
        NewsPage.FilterForm.category(gratitude).checkWithTimeout(matches(isDisplayed()));
        NewsPage.FilterForm.category(helpIsNeeded).checkWithTimeout(matches(isDisplayed()));

        CustomViewAction.returnBack();
    }
}
