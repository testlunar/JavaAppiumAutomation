package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListPageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListPageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

public class MyListTests extends CoreTestCase {

    private static final String name_folder = "My list";

    @Test
    public void testSaveFirstArticleToMyList() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
        searchPageObject.clickByArticleWithSomeString("Object-oriented programming language");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement();
        String article_title = articlePageObject.getArticleTitle();

        if(Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToNewList(name_folder);
        }else{
            articlePageObject.addArticleToMySaved();

        }
        articlePageObject.openSavedListFromSnackBar();

        MyListPageObject myListPageObject = MyListPageObjectFactory.get(driver);
        myListPageObject.swipeToLeftArticleToDelete(article_title);
        myListPageObject.checkListItemDissapeared(article_title);
    }

    //Ex5: Тест: сохранение двух статей
    @Test
    public void testSaveTwoArticleToMyList() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
        searchPageObject.clickByArticleWithSomeString("Object-oriented programming language");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
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
        MyListPageObject myListPageObject = MyListPageObjectFactory.get(driver);
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
