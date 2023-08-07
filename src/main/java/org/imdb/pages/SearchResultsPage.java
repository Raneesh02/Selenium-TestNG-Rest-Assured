package org.imdb.pages;

import org.imdb.BaseClasses.BasePage;
import org.imdb.customWebElem.ExWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchResultsPage extends BasePage {

    By cssResultsHeader =By.cssSelector("h1[class='findHeader']");
    By cssResultsSubHeader=By.cssSelector("span[id='findSubHeaderLabel']");
    By xpathResultsList=By.xpath("//table[@class='findList']//td[contains(@class,'result_text')]/a");

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }


    public ExWebElement getSearchHeader() {return new ExWebElement(driver, cssResultsHeader);}

    public ExWebElement getResultsSubHeader() {return new ExWebElement(driver, cssResultsSubHeader);}

    public List<WebElement> getSearchResultsList(){ return new ExWebElement(driver,xpathResultsList).findAllElements(); }

    public void clickTitleCategory(String titleName){
        By xpathTitleCategory=By.xpath("//ul[@class='findTitleSubfilterList']//a[text()='"+titleName+"']");
        ExWebElement titleCategory = new ExWebElement(driver, xpathTitleCategory);
        titleCategory.click();
    }




}
