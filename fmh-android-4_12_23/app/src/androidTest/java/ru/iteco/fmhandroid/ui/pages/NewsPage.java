package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.swipeDown;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.hasSibling;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static ru.iteco.fmhandroid.ui.matchers.CustomViewAction.clickOnViewWithId;
import static ru.iteco.fmhandroid.ui.matchers.CustomViewMatcher.childAtPosition;
import static ru.iteco.fmhandroid.ui.matchers.TimeoutEspresso.onViewWithTimeout;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.RootMatchers;


import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.DataGenerator;
import ru.iteco.fmhandroid.ui.matchers.CustomViewAction;
import ru.iteco.fmhandroid.ui.matchers.TimeoutEspresso;
import ru.iteco.fmhandroid.ui.steps.OpenPage;

public class NewsPage {
    private static String newsTitle = "Новости";
    private static String defaultCategory = "Объявление";
    private static String defaultDate = DataGenerator.getCurrentDate();
    private static String defaultTime = DataGenerator.getCurrentTime();

    public static TimeoutEspresso.TimedViewInteraction title =
            onViewWithTimeout(withText(newsTitle));
    public static TimeoutEspresso.TimedViewInteraction newsContainer =
            onViewWithTimeout(withId(R.id.container_list_news_include));

    public static TimeoutEspresso.TimedViewInteraction appBarPanel =
            onViewWithTimeout(withId(R.id.container_custom_app_bar_include_on_fragment_news_list));
    public static TimeoutEspresso.TimedViewInteraction sortButton =
            onViewWithTimeout(withId(R.id.sort_news_material_button));
    public static TimeoutEspresso.TimedViewInteraction filterButton =
            onViewWithTimeout(withId(R.id.filter_news_material_button));
    public static TimeoutEspresso.TimedViewInteraction editButton =
            onViewWithTimeout(withId(R.id.edit_news_material_button));
    public static TimeoutEspresso.TimedViewInteraction newsList =
            onViewWithTimeout(15000, withId(R.id.news_list_recycler_view));

    public static void clickOnFilterButton() {
        Allure.step("Клик по кнопке фильтра");
        filterButton.performWithTimeout(click());
    }

    public static void clickOnEditButton() {
        Allure.step("Клик по кнопке \"Панель управления\"");
        editButton.performWithTimeout(click());
    }

    public static void addNews(String title) {
        addNews(defaultCategory, title, defaultDate, defaultTime, title);
    }

    public static void addNews(String category, String title) {
        addNews(category, title, defaultDate, defaultTime, title);
    }
    public static void addNews(String category, String title, String date, String time, String description) {
        Allure.step("Создание новой новости:");
        OpenPage.newsControlPanel();

        try {
            NewsPage.ControlPanelPage.clickOnAddNewsButton();
            NewsPage.FilterForm.clickOnCategoryField();
            NewsPage.FilterForm.clickOnCategory(category);
            NewsPage.ControlPanelPage.CreateEditForm.insertInTitleField(title);
            NewsPage.ControlPanelPage.CreateEditForm.insertInDateField(date);
            NewsPage.ControlPanelPage.CreateEditForm.insertInTimeField(time);
            NewsPage.ControlPanelPage.CreateEditForm.insertInDescriptionField(description);

            NewsPage.ControlPanelPage.CreateEditForm.clickOnSaveButton();
        }
        catch (Exception e) {
        }
    }
    public static void filterNewsByCategory(String category) {
        NewsPage.clickOnFilterButton();
        NewsPage.FilterForm.clickOnCategoryField();
        NewsPage.FilterForm.clickOnCategory(category);
        NewsPage.FilterForm.clickOnAcceptButton();
    }
    public static void editDescriptionNewsWithTitle(String title, String newDescription) {
        try {
            OpenPage.newsControlPanel();
            NewsPage.ControlPanelPage.ItemNewsControlPanel.clickOnEditButtonNewsWithTitle(title);
            NewsPage.ControlPanelPage.CreateEditForm.insertInDescriptionField(newDescription);
            NewsPage.ControlPanelPage.CreateEditForm.clickOnSaveButton();
        }
        catch (Exception e) {
        }
    }
    public static void changeStatusNewsWithTitle (String title) {
        try {
            OpenPage.newsControlPanel();
            NewsPage.ControlPanelPage.ItemNewsControlPanel.clickOnEditButtonNewsWithTitle(title);
            NewsPage.ControlPanelPage.CreateEditForm.clickOnSwitcher();
            NewsPage.ControlPanelPage.CreateEditForm.clickOnSaveButton();
        }
        catch (Exception e) {
        }
    }

    public static void deleteNewsWithTitle(String title) {
        try {
            Allure.step("Удаление новости с заголовком: \"" + title + "\"");
            OpenPage.newsControlPanel();
            NewsPage.ControlPanelPage.swipeRefresh();
            NewsPage.ControlPanelPage.ItemNewsControlPanel.clickOnDeleteButtonNewsWithTitle(title);

            // To wait for a selector to appear in the hierarchy
            CustomViewAction.stopExecutionForSeconds(5);
            NewsPage.ControlPanelPage.ItemNewsControlPanel.clickOkDialogButton();
        }
        catch (Exception e) {
        }
    }



    public static class ItemNews {
        public static TimeoutEspresso.TimedViewInteraction dateNewsWithTitle(String title) {
            return onViewWithTimeout(allOf(
                    withId(R.id.news_item_date_text_view),
                    hasSibling(allOf(
                            withId(R.id.news_item_title_text_view),
                            withText(title)))));
        }

        public static TimeoutEspresso.TimedViewInteraction dropButtonNewsWithTitle(String title) {
            return onViewWithTimeout(allOf(
                    withId(R.id.view_news_item_image_view),
                    hasSibling(allOf(
                            withId(R.id.news_item_title_text_view),
                            withText(title)))));
        }

        public static TimeoutEspresso.TimedViewInteraction iconCategoryNewsWithTitle(String title) {
            return onViewWithTimeout(allOf(
                    withId(R.id.category_icon_image_view),
                    hasSibling(allOf(
                            withId(R.id.news_item_title_text_view),
                            withText(title)))));
        }

        public static TimeoutEspresso.TimedViewInteraction descriptionNewsWithTitle(String title) {
            return onViewWithTimeout(allOf(
                    withId(R.id.news_item_description_text_view),
                    hasSibling(allOf(
                            withId(R.id.news_item_title_text_view),
                            withText(title)))));
        }

        public static TimeoutEspresso.TimedViewInteraction newsWithTitle(String title) {
            return onViewWithTimeout(allOf(
                    withId(R.id.news_item_title_text_view),
                    withText(title)));
        }

        public static TimeoutEspresso.TimedViewInteraction descriptionOfNews(int num) {
            int number = num -1;
            return onViewWithTimeout(allOf(
                    withId(R.id.news_item_description_text_view),
                    withParent(
                            withParent(
                                    childAtPosition(withId(R.id.news_list_recycler_view),
                                            number)))));
        }

        public static TimeoutEspresso.TimedViewInteraction iconOfNews(int num) {
            int number = num -1;
            return onViewWithTimeout(allOf(
                    withId(R.id.category_icon_image_view),
                    withParent(
                            withParent(
                                    childAtPosition(withId(R.id.news_list_recycler_view),
                                            number)))));
        }

        public static TimeoutEspresso.TimedViewInteraction titleOfNews(int num) {
            int number = num -1;
            return onViewWithTimeout(allOf(
                    withId(R.id.news_item_title_text_view),
                    withParent(
                            withParent(
                                    childAtPosition(withId(R.id.news_list_recycler_view),
                                            number)))));
        }

        public static TimeoutEspresso.TimedViewInteraction dropButtonOfNews(int num) {
            int number = num -1;
            return onViewWithTimeout(allOf(
                    withId(R.id.view_news_item_image_view),
                    withParent(
                            withParent(
                                    childAtPosition(withId(R.id.news_list_recycler_view),
                                            number)))));
        }
        public static void clickOnNews(String title) {
            Allure.step("Клик по  новости в списке c заголовком \"" + title + "\"");
            ItemNews.newsWithTitle(title).performWithTimeout(click());
        }
        public static void clickOnNews(int num) {
            int number = num -1;
            Allure.step("Клик по " + num + " новости в списке");
            onViewWithTimeout(
                    childAtPosition(withId(R.id.news_list_recycler_view),
                            number))
                    .performWithTimeout(click());
        }
    }



    public static class FilterForm {

        public static TimeoutEspresso.TimedViewInteraction title =
                onViewWithTimeout(withId(R.id.filter_news_title_text_view));
        public static TimeoutEspresso.TimedViewInteraction categoryField =
                onViewWithTimeout(withId(R.id.news_item_category_text_auto_complete_text_view));
        public static TimeoutEspresso.TimedViewInteraction dateStartField =
                onViewWithTimeout(withId(R.id.news_item_publish_date_start_text_input_edit_text));
        public static TimeoutEspresso.TimedViewInteraction dateEndField =
                onViewWithTimeout(withId(R.id.news_item_publish_date_end_text_input_edit_text));
        public static TimeoutEspresso.TimedViewInteraction acceptButton =
                onViewWithTimeout(withId(R.id.filter_button));
        public static TimeoutEspresso.TimedViewInteraction cancelButton =
                onViewWithTimeout(withId(R.id.cancel_button));

        public static TimeoutEspresso.TimedViewInteraction category(String category) {
            return onViewWithTimeout(withText(category))
                    .inRoot(RootMatchers
                            .isPlatformPopup());
        }
        public static void clickOnCategoryField() {
            Allure.step("Клик по полю выбора категории");
            categoryField.performWithTimeout(click(), closeSoftKeyboard());
        }

        public static void clickOnCategory (String category) {
            Allure.step("Клик по категории \"" + category + "\"");
            onViewWithTimeout(withText(category))
                    .inRoot(RootMatchers
                            .isPlatformPopup())
                    .performWithTimeout(click());
        }

        public static void clickOnAcceptButton() {
            Allure.step("Клик по кнопке \"Фильтровать\"");
            acceptButton.performWithTimeout(click());
        }
    }



    public static class ControlPanelPage extends NewsPage{
        private static String ControlPanelTitle = "Панель \n управления";

        public static TimeoutEspresso.TimedViewInteraction title =
                onViewWithTimeout(withText(ControlPanelTitle));

        public static TimeoutEspresso.TimedViewInteraction appBarPanel =
                onViewWithTimeout(withId(R.id.container_custom_app_bar_include_on_fragment_news_control_panel));
        public static TimeoutEspresso.TimedViewInteraction addNewsButton =
                onViewWithTimeout(withId(R.id.add_news_image_view));
        public static TimeoutEspresso.TimedViewInteraction swipeRefresh =
                onViewWithTimeout(withId(R.id.news_control_panel_swipe_to_refresh));
        public static void swipeRefresh() {
            swipeRefresh.performWithTimeout(swipeDown());
        }

        public static void clickOnAddNewsButton() {
            Allure.step("Клик по кнопке добавления новости");
            addNewsButton.performWithTimeout(click());
        }



        public static class ItemNewsControlPanel extends ItemNews{

            public static TimeoutEspresso.TimedViewInteraction statusNewsWithTitle(String title) {
                return onViewWithTimeout(allOf(
                        withId(R.id.news_item_published_text_view),
                        hasSibling(allOf(
                                withId(R.id.news_item_title_text_view),
                                withText(title)))));
            }

            public static TimeoutEspresso.TimedViewInteraction publicationTextField(int num) {
                int number = num -1;
                return onViewWithTimeout(allOf(
                        withId(R.id.news_item_publication_text_view),
                        withParent(withParent(
                                childAtPosition(withId(R.id.news_list_recycler_view),
                                        number)))));
            }

            public static TimeoutEspresso.TimedViewInteraction publicationDateField(int num) {
                int number = num -1;
                return onViewWithTimeout(allOf(
                        withId(R.id.news_item_publication_date_text_view),
                        withParent(
                                withParent(
                                        childAtPosition(withId(R.id.news_list_recycler_view),
                                                number)))));
            }

            public static TimeoutEspresso.TimedViewInteraction creationTextField(int num) {
                int number = num -1;
                return onViewWithTimeout(allOf(
                        withId(R.id.news_item_creation_text_view),
                        withParent(
                                withParent(
                                        childAtPosition(withId(R.id.news_list_recycler_view),
                                                number)))));
            }

            public static TimeoutEspresso.TimedViewInteraction creationDateField(int num) {
                int number = num -1;
                return onViewWithTimeout(allOf(
                        withId(R.id.news_item_create_date_text_view),
                        withParent(
                                withParent(
                                        childAtPosition(withId(R.id.news_list_recycler_view),
                                                number)))));
            }

            public static TimeoutEspresso.TimedViewInteraction authorTextField(int num) {
                int number = num -1;
                return onViewWithTimeout(allOf(
                        withId(R.id.news_item_author_text_view),
                        withParent(
                                withParent(
                                        childAtPosition(withId(R.id.news_list_recycler_view),
                                                number)))));
            }

            public static TimeoutEspresso.TimedViewInteraction authorNameField(int num) {
                int number = num -1;
                return onViewWithTimeout(allOf(
                        withId(R.id.news_item_author_name_text_view),
                        withParent(
                                withParent(
                                        childAtPosition(withId(R.id.news_list_recycler_view),
                                                number)))));
            }

            public static TimeoutEspresso.TimedViewInteraction publicationStatus(int num) {
                int number = num -1;
                return onViewWithTimeout(allOf(
                        withId(R.id.news_item_published_text_view),
                        withParent(
                                withParent(
                                        childAtPosition(withId(R.id.news_list_recycler_view),
                                                number)))));
            }

            public static TimeoutEspresso.TimedViewInteraction deleteNewsButton(int num) {
                int number = num -1;
                return onViewWithTimeout(allOf(
                        withId(R.id.delete_news_item_image_view),
                        withParent(
                                withParent(
                                        childAtPosition(withId(R.id.news_list_recycler_view),
                                                number)))));
            }

            public static TimeoutEspresso.TimedViewInteraction editNewsButton(int num) {
                int number = num -1;
                return onViewWithTimeout(allOf(
                        withId(R.id.edit_news_item_image_view),
                        withParent(
                                withParent(
                                        childAtPosition(withId(R.id.news_list_recycler_view),
                                                number)))));
            }

            public static void clickOnEditNewsButton(int num) {
                Allure.step("Клик по кнопке редактирования " + num + " новости");
                editNewsButton(num).performWithTimeout(click());
            }
            public static void clickOnDeleteButtonNewsWithTitle(String title) {
                Allure.step("Клик по кнопке удаления новости с заголовком: \"" + title + "\"");
                NewsPage.ControlPanelPage.newsList.perform(RecyclerViewActions.actionOnItem(
                        hasDescendant(withText(title)),
                        clickOnViewWithId(R.id.delete_news_item_image_view)));
            }
            public static void clickOnEditButtonNewsWithTitle (String title) {
                Allure.step("Клик по кнопке редактирования новости с заголовком: \"" + title + "\"");
                NewsPage.ControlPanelPage.newsList.perform(RecyclerViewActions.actionOnItem(
                        hasDescendant(withText(title)),
                        clickOnViewWithId(R.id.edit_news_item_image_view)));
            }
            public static void clickOkDialogButton() {
                onViewWithTimeout(allOf(withId(android.R.id.button1), withText("OK"), isDisplayed()))
                        .inRoot(isDialog())
                        .performWithTimeout(scrollTo(), click());
                Allure.step("Клик по кнопке \"ОК\" окна подтверждения");
            }
        }



        public static class FilterFormControlPanel extends FilterForm {
            public static TimeoutEspresso.TimedViewInteraction checkboxActive =
                    onViewWithTimeout(withId(R.id.filter_news_active_material_check_box));
            public static TimeoutEspresso.TimedViewInteraction checkboxNotActive =
                    onViewWithTimeout(withId(R.id.filter_news_inactive_material_check_box));
        }




        public static class CreateEditForm {
            public static TimeoutEspresso.TimedViewInteraction categoryField =
                    onViewWithTimeout(withId(R.id.news_item_category_text_auto_complete_text_view));
            public static TimeoutEspresso.TimedViewInteraction categoryFieldAlertIcon =
                    onViewWithTimeout(allOf(
                            withId(R.id.text_input_start_icon),
                            withParent(
                                    hasSibling(withId(R.id.news_item_category_text_auto_complete_text_view)))));

            public static TimeoutEspresso.TimedViewInteraction titleField =
                    onViewWithTimeout(withId(R.id.news_item_title_text_input_edit_text));
            public static TimeoutEspresso.TimedViewInteraction titleFieldAlertIcon =
                    onViewWithTimeout(allOf(
                            withId(R.id.text_input_end_icon),
                            withParent(
                                    withParent(
                                            hasSibling(withId(R.id.news_item_title_text_input_edit_text))))));

            public static TimeoutEspresso.TimedViewInteraction publicationDateField =
                    onViewWithTimeout(withId(R.id.news_item_publish_date_text_input_edit_text));
            public static TimeoutEspresso.TimedViewInteraction publicationDateFieldAlertIcon =
                    onViewWithTimeout(allOf(
                            withId(R.id.text_input_end_icon),
                            withParent(
                                    withParent(
                                            hasSibling(withId(R.id.news_item_publish_date_text_input_edit_text))))));

            public static TimeoutEspresso.TimedViewInteraction publicationTimeField =
                    onViewWithTimeout(withId(R.id.news_item_publish_time_text_input_edit_text));
            public static TimeoutEspresso.TimedViewInteraction publicationTimeFieldAlertIcon =
                    onViewWithTimeout(allOf(
                            withId(R.id.text_input_end_icon),
                            withParent(
                                    withParent(
                                            hasSibling(withId(R.id.news_item_publish_time_text_input_edit_text))))));
            public static TimeoutEspresso.TimedViewInteraction descriptionField =
                    onViewWithTimeout(withId(R.id.news_item_description_text_input_edit_text));
            public static TimeoutEspresso.TimedViewInteraction descriptionFieldAlertIcon =
                    onViewWithTimeout(allOf(
                            withId(R.id.text_input_end_icon),
                            withParent(
                                    withParent(
                                            hasSibling(withId(R.id.news_item_description_text_input_edit_text))))));
            public static TimeoutEspresso.TimedViewInteraction switcher =
                    onViewWithTimeout(withId(R.id.switcher));
            public static TimeoutEspresso.TimedViewInteraction saveButton =
                    onViewWithTimeout(withId(R.id.save_button));

            public static TimeoutEspresso.TimedViewInteraction cancelButton =
                    onViewWithTimeout(withId(R.id.cancel_button));

            public static void insertInTitleField (String title) {
                Allure.step("Ввод в поле \"Заголовок\" значения \"" + title + "\"");
                titleField.performWithTimeout(replaceText(title));
            }

            public static void insertInDescriptionField (String description) {
                Allure.step("Ввод в поле \"Описание\" значения \"" + description + "\"");
                descriptionField.performWithTimeout(replaceText(description));
            }

            public static void insertInDateField (String date) {
                Allure.step("Ввод в поле \"Дата\" значения \"" + date + "\"");
                publicationDateField.performWithTimeout(replaceText(date));
            }

            public static void insertInTimeField (String time) {
                Allure.step("Ввод в поле \"Время\" значения \"" + time + "\"");
                publicationTimeField.performWithTimeout(replaceText(time));
            }

            public static void clickOnSwitcher() {
                Allure.step("Клик по переключателю статуса новости");
                switcher.performWithTimeout(click());
            }

            public static void clickOnSaveButton() {
                Allure.step("Клик по кнопке \"Сохранить\"");
                saveButton.performWithTimeout(click());
            }
        }
    }
}