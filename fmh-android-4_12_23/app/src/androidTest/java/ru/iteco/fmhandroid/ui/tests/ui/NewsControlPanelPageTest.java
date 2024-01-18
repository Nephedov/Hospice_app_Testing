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
import ru.iteco.fmhandroid.ui.matchers.CustomViewAction;
import ru.iteco.fmhandroid.ui.pages.AppBarPanel;
import ru.iteco.fmhandroid.ui.pages.NewsPage.ControlPanelPage;
import ru.iteco.fmhandroid.ui.pages.NewsPage.ControlPanelPage.*;
import ru.iteco.fmhandroid.ui.steps.OpenPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class NewsControlPanelPageTest {
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    @Rule
    public ScreenshotRule screenshotRuleFailure =
            new ScreenshotRule(ScreenshotRule.Mode.FAILURE, "test_failure");

    @Before
    public void setUp() {
        tryLogIn();
        OpenPage.newsControlPanel();
    }
    @After
    public void tearDown() {
        tryLogOut();
    }


//    @Epic(value = "Тестирование UI")
//    @Feature(value = "Панель AppBar")
//    @Story(value = "AppBar на странице \"Панель управления\"")
//    @Test
//    @Description(value = "Тест проверяет отображение AppBar панели на странице \"Панель управления\"")
//    public void shouldCheckAppBarOnControlPanelIsDisplayed() {
//        ControlPanelPage.appBarPanel.checkWithTimeout(matches(isDisplayed()));
//    }
//
//    @Epic(value = "Тестирование UI")
//    @Feature(value = "Панель AppBar")
//    @Story(value = "Лого AppBar на странице \"Панель управления\"")
//    @Test
//    @Description(value = "Тест проверяет отображение главной иконки на панели AppBar на странице \"Панель управления\"")
//    public void shouldCheckAppBarLogoOnControlPanelIsDisplayed() {
//        AppBarPanel.mainImage.checkWithTimeout(matches(isDisplayed()));
//    }
//
//    @Epic(value = "Тестирование UI")
//    @Feature(value = "Страница \"Панель управления\"")
//    @Story(value = "Заголовок")
//    @Test
//    @Description(value = "Тест проверяет отображение заголовка на странице \"Панель управления\"")
//    public void shouldCheckControlPanelTitleIsDisplayed() {
//        ControlPanelPage.title.checkWithTimeout(matches(isDisplayed()));
//    }
//
//    @Epic(value = "Тестирование UI")
//    @Feature(value = "Страница \"Панель управления\"")
//    @Story(value = "Кнопки управления")
//    @Test
//    @Description(value = "Тест проверяет отображение кнопок управления на странице \"Панель управления\"")
//    public void shouldCheckControlButtonsIsDisplayed() {
//        ControlPanelPage.sortButton.checkWithTimeout(matches(isDisplayed()));
//        ControlPanelPage.filterButton.checkWithTimeout(matches(isDisplayed()));
//        ControlPanelPage.addNewsButton.checkWithTimeout(matches(isDisplayed()));
//    }

    @Epic(value = "Тестирование UI")
    @Feature(value = "Страница \"Панель управления\"")
    @Story(value = "Элементы скрытой новости")
    @Test
    @Description(value = "Тест проверяет отображение элементов первой в списке нераскрытой новости")
    public void shouldCheckHiddenFirstNewsElementsIsDisplayed() {
        int numberOfNews = 1;
        String publicationTextField = "Дата публикации";
        String creationTextField = "Дата создания";
        String authorTextField = "Автор";

        ItemNewsControlPanel.titleOfNews(numberOfNews).checkWithTimeout(matches(isDisplayed()));
        ItemNewsControlPanel.publicationTextField(numberOfNews).checkWithTimeout(matches(isDisplayed())).
                check(matches(withText(publicationTextField)));
        ItemNewsControlPanel.publicationDateField(numberOfNews).checkWithTimeout(matches(isDisplayed()));
        ItemNewsControlPanel.creationTextField(numberOfNews).checkWithTimeout(matches(isDisplayed()))
                .check(matches(withText(creationTextField)));
        ItemNewsControlPanel.creationDateField(numberOfNews).checkWithTimeout(matches(isDisplayed()));
        ItemNewsControlPanel.authorTextField(numberOfNews).checkWithTimeout(matches(isDisplayed()))
                .check(matches(withText(authorTextField)));
        ItemNewsControlPanel.authorNameField(numberOfNews).checkWithTimeout(matches(isDisplayed()));
        ItemNewsControlPanel.publicationStatus(numberOfNews).checkWithTimeout(matches(isDisplayed()));
        ItemNewsControlPanel.deleteNewsButton(numberOfNews).checkWithTimeout(matches(isDisplayed()));
        ItemNewsControlPanel.editNewsButton(numberOfNews).checkWithTimeout(matches(isDisplayed()));
        ItemNewsControlPanel.dropButtonOfNews(numberOfNews).checkWithTimeout(matches(isDisplayed()));
        ItemNewsControlPanel.iconOfNews(numberOfNews).checkWithTimeout(matches(isDisplayed()));

        ItemNewsControlPanel.descriptionOfNews(numberOfNews).checkWithTimeout(matches(not(isDisplayed())));
    }

    @Epic(value = "Тестирование UI")
    @Feature(value = "Страница \"Панель управления\"")
    @Story(value = "Форма фильтра новостей")
    @Test
    @Description(value = "Тест проверяет отображение элементов формы фильтра")
    public void shouldCheckFilterFormElementsOnControlPanelIsDisplayed() {
        String formTitle = "Фильтровать новости";
        String categoryHint = "Категория";
        String dateFormat = "ДД.ММ.ГГГГ";
        String acceptButton = "Фильтровать";
        String cancelButton = "Отмена";
        String checkboxActive = "Активна";
        String checkboxNotActive = "Не активна";

        ControlPanelPage.clickOnFilterButton();

        FilterFormControlPanel.title.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withText(formTitle)));
        FilterFormControlPanel.categoryField.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withHint(categoryHint)));
        FilterFormControlPanel.dateStartField.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withHint(dateFormat)));
        FilterFormControlPanel.dateEndField.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withHint(dateFormat)));
        FilterFormControlPanel.acceptButton.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withText(acceptButton)));
        FilterFormControlPanel.cancelButton.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withText(cancelButton)));
        FilterFormControlPanel.checkboxActive.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withText(checkboxActive)));
        FilterFormControlPanel.checkboxNotActive.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withText(checkboxNotActive)));
    }
    @Epic(value = "Тестирование UI")
    @Feature(value = "Страница \"Панель управления\"")
    @Story(value = "Форма фильтра новостей")
    @Test
    @Description(value = "Тест проверяет отображение категорий формы фильтра")
    public void shouldCheckFilterFormOnControlCategoriesIsDisplayed() {
        String birthday = "День рождения";
        String salary = "Зарплата";
        String tradeUnion = "Профсоюз";
        String holiday = "Праздник";
        String massage = "Массаж";
        String gratitude = "Благодарность";
        String helpIsNeeded = "Нужна помощь";

        ControlPanelPage.clickOnFilterButton();
        FilterFormControlPanel.clickOnCategoryField();

        FilterFormControlPanel.category(birthday).checkWithTimeout(matches(isDisplayed()));
        FilterFormControlPanel.category(salary).checkWithTimeout(matches(isDisplayed()));
        FilterFormControlPanel.category(tradeUnion).checkWithTimeout(matches(isDisplayed()));
        FilterFormControlPanel.category(holiday).checkWithTimeout(matches(isDisplayed()));
        FilterFormControlPanel.category(massage).checkWithTimeout(matches(isDisplayed()));
        FilterFormControlPanel.category(gratitude).checkWithTimeout(matches(isDisplayed()));
        FilterFormControlPanel.category(helpIsNeeded).checkWithTimeout(matches(isDisplayed()));

        CustomViewAction.returnBack();
    }

    @Epic(value = "Тестирование UI")
    @Feature(value = "Страница \"Панель управления\"")
    @Story(value = "Форма редактирования новости")
    @Test
    @Description(value = "Тест проверяет отображение элементов формы редактирования новости")
    public void shouldCheckEditFormElementsOnControlPanelIsDisplayed() {
        String appBarTopLine = "Редактирование";
        String appBarBottomLine = "Новости";

        String categoryField = "Категория";
        String titleField = "Заголовок";
        String publicationDateField = "Дата публикации";
        String publicationTimeField = "Время";
        String descriptionField = "Описание";
        String saveButton = "Сохранить";
        String cancelButton = "Отмена";

        ItemNewsControlPanel.clickOnEditNewsButton(1);

        AppBarPanel.topLineText.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withText(appBarTopLine)));
        AppBarPanel.bottomLineText.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withText(appBarBottomLine)));

        CreateEditForm.categoryField.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withHint(categoryField)));
        CreateEditForm.titleField.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withHint(titleField)));
        CreateEditForm.publicationDateField.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withHint(publicationDateField)));
        CreateEditForm.publicationTimeField.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withHint(publicationTimeField)));
        CreateEditForm.descriptionField.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withHint(descriptionField)));
        CreateEditForm.switcher.checkWithTimeout(matches(isDisplayed()));
        CreateEditForm.saveButton.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withText(saveButton)));
        CreateEditForm.cancelButton.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withText(cancelButton)));
    }

    @Epic(value = "Тестирование UI")
    @Feature(value = "Страница \"Панель управления\"")
    @Story(value = "Форма создания новости")
    @Test
    @Description(value = "Тест проверяет отображение элементов формы создания новости")
    public void shouldCheckCreateFormElementsOnControlPanelIsDisplayed() {
        String appBarTopLine = "Создание";
        String appBarBottomLine = "Новости";

        String categoryField = "Категория";
        String titleField = "Заголовок";
        String publicationDateField = "Дата публикации";
        String publicationTimeField = "Время";
        String descriptionField = "Описание";
        String saveButton = "Сохранить";
        String cancelButton = "Отмена";

        ControlPanelPage.clickOnAddNewsButton();

        AppBarPanel.topLineText.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withText(appBarTopLine)));
        AppBarPanel.bottomLineText.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withText(appBarBottomLine)));

        CreateEditForm.categoryField.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withHint(categoryField)));
        CreateEditForm.titleField.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withHint(titleField)));
        CreateEditForm.publicationDateField.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withHint(publicationDateField)));
        CreateEditForm.publicationTimeField.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withHint(publicationTimeField)));
        CreateEditForm.descriptionField.checkWithTimeout(matches(isDisplayed())).
                check(matches(withHint(descriptionField)));
        CreateEditForm.switcher.checkWithTimeout(matches(isDisplayed()));
        CreateEditForm.saveButton.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withText(saveButton)));
        CreateEditForm.cancelButton.checkWithTimeout(matches(isDisplayed()))
                .check(matches(withText(cancelButton)));
    }
}
