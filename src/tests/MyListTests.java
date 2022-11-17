package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListPageObject;
import lib.ui.SearchPageObject;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

public class MyListTests extends CoreTestCase {
    @Test
    public void testSaveFirstArticleToMyList() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
        searchPageObject.clickByArticleWithSomeString("Object-oriented programming language");

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTitleElement();
        String article_title = articlePageObject.getArticleTitle();
        articlePageObject.addArticleToNewList("My list");
        articlePageObject.openSavedListFromSnackBar();

        MyListPageObject myListPageObject = new MyListPageObject(driver);
        myListPageObject.swipeToLeftArticleToDelete(article_title);
        myListPageObject.checkListItemDissapeared(article_title);
    }

    //Ex5: Тест: сохранение двух статей
    @Test
    public void testSaveTwoArticleToMyList() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
        searchPageObject.clickByArticleWithSomeString("Object-oriented programming language");

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTitleElement();
        String listName = "Programming";
        articlePageObject.addArticleToNewList(listName);

        //добавление 2й статьи в существующий лист
        searchPageObject.typeSearchLine("Python");
        searchPageObject.waitForSearchResult("General-purpose programming language");
        searchPageObject.clickByArticleWithSomeString("General-purpose programming language");
        articlePageObject.addArticleToSomeList(listName);

        //Открытие сохраненного листа из всплывающего окна снизу
        articlePageObject.openSavedListFromSnackBar();
        MyListPageObject myListPageObject = new MyListPageObject(driver);
        myListPageObject.checkMyListAppeared();

        //удаление статьи
        myListPageObject.swipeToLeftArticleToDelete("Java (programming language");
        myListPageObject.checkListItemDissapeared("Java (programming language");

        String title_in_list = myListPageObject.getArticleTitle();
        myListPageObject.clickArticleInSavedList();

        articlePageObject.waitForTitleElement();
        String title_whenOpen = articlePageObject.getArticleTitle();

        assertEquals("title is different", title_in_list, title_whenOpen);
    }
}
