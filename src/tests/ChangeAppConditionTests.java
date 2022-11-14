package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Assert;
import org.junit.Test;

public class ChangeAppConditionTests extends CoreTestCase {
    @Test
    public void testChangesScreenOrientationTestResults() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
        searchPageObject.clickByArticleWithSomeString("Object-oriented programming language");

        ArticlePageObject articlePageObject  = new ArticlePageObject(driver);
        String title_before_rotation = articlePageObject.getArticleTitle();
        this.rotateScreenLandscape();
        String title_after_rotation = articlePageObject.getArticleTitle();

        Assert.assertEquals(
                "title is different after rotation", title_before_rotation, title_after_rotation);
        this.rotateScreenPortrait();
        String title_after2rotation = articlePageObject.getArticleTitle();
        Assert.assertEquals(
                "title is different after 2rotation", title_before_rotation, title_after2rotation);
    }

    @Test
    public void testSearchArticleInBackground() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
        this.background(2);
        searchPageObject.waitForSearchResult("Object-oriented programming language");
    }
}
