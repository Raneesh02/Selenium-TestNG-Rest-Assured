package org.imdb.tests.api_ui_compare.moviesearch;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.specification.RequestSpecification;
import org.imdb.pages.HomePage;
import org.imdb.pages.SearchResultsPage;
import org.imdb.testData.MovieSearchData;
import org.imdb.pojo.movie_search.MovieSearchByNameResponseSuccess;
import org.imdb.testData.MovieSearchDataRoot;
import org.imdb.tests.BaseClasses.BaseAPIUITests;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class MovieSearchApiWebTests extends BaseAPIUITests
{

    @Test
    public void testHomePageList()
    {
        HomePage homePage=getHomePage();
        homePage.getSearchBox().sendKeys("Lord of the rings");
        homePage.getSearchButton().click();
        SearchResultsPage searchResultsPage = getSearchResultsPage();
        searchResultsPage.clickTitleCategory("Movie");
        Assert.assertTrue(searchResultsPage.getSearchHeader().isDisplayed()," Search results page is not visible");

    }

    @Test(dataProvider = "moviesearchData")
    public void testMovieSearchApi_UI(MovieSearchData movieSearchData){

        //Get the list of movieTitles from UI
        HomePage homePage=getHomePage();
        homePage.getSearchBox().sendKeys(movieSearchData.getNameToSearch());
        homePage.getSearchButton().click();

        SearchResultsPage searchResultsPage = getSearchResultsPage();
        searchResultsPage.clickTitleCategory("Movie");

        Assert.assertTrue(searchResultsPage.getSearchHeader().isDisplayed()," Search results page is not visible");
        Assert.assertTrue(searchResultsPage.getResultsSubHeader().isDisplayed(),"Category is not applied");
        List<String> movieTitlesUI=searchResultsPage.getSearchResultsList().stream().map(r -> r.getText()).collect(Collectors.toList());

        //Get the list of movieTitles from API
        HashMap<String,String> queryMap=new HashMap<>();
        queryMap.put("apikey",properties.getProperty("apiKey"));
        queryMap.put("type",movieSearchData.getType());
        queryMap.put("s",movieSearchData.getNameToSearch().replaceAll(" ","+"));
        RequestSpecification request = RestAssured.given()
                .queryParams(queryMap);
        MovieSearchByNameResponseSuccess movieSearchResponse = request.request(Method.GET).body().as(MovieSearchByNameResponseSuccess.class);
        List<String> movieTitlesApi=movieSearchResponse.search.stream().map(r -> r.getTitle()).collect(Collectors.toList());

        Assert.assertFalse(movieTitlesApi.retainAll(movieTitlesUI),"Movie List from API: "+movieTitlesApi+"\nMovieList from Web"+movieTitlesUI);
    }


    @DataProvider(name = "moviesearchData")
    public Object[][] movieData() throws FileNotFoundException {
        Gson gson=new Gson();
        MovieSearchDataRoot movieSearchDataRoot=gson.fromJson(JsonParser.parseReader(new FileReader(System.getProperty("user.dir")+"/src/test/resources/testData/movieSearchdata.json")), MovieSearchDataRoot.class);
        MovieSearchData[][] movieSearchDataArr=new MovieSearchData[movieSearchDataRoot.data.size()][1];
        for(int i=0;i<movieSearchDataArr.length;i++){
            movieSearchDataArr[i][0]=movieSearchDataRoot.data.get(i);
        }

        return movieSearchDataArr;
    }
}
