package org.imdb.tests.ui;


import org.imdb.pages.HomePage;
import org.imdb.pages.SearchResultsPage;
import org.imdb.tests.BaseClasses.BaseAPIUITests;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class HomePageTests extends BaseAPIUITests
{

    @Test
    public void testHomePageLogo()
    {
        Assert.assertTrue(getHomePage().getImdbLogIcon().isDisplayed(),"Homepage not loaded");
    }


    @Test
    public void testSearchMovieByTitle()
    {
        HomePage homePage=getHomePage();
        homePage.getSearchBox().sendKeys("Lord of the rings");
        homePage.getSearchButton().click();
        SearchResultsPage searchResultsPage = getSearchResultsPage();
        searchResultsPage.clickTitleCategory("Movie");
        Assert.assertTrue(searchResultsPage.getSearchHeader().isDisplayed()," Search results page is not visible");
        Assert.assertTrue(searchResultsPage.getResultsSubHeader().isDisplayed(),"Category is not applied");
        List<WebElement> listOfSearchResults = searchResultsPage.getSearchResultsList();
        System.out.println("ALl list values"+listOfSearchResults.size());
        for (WebElement elem: listOfSearchResults) {
            System.out.println(elem.getText());
        }

    }
}
