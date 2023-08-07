package org.imdb.pages;

import org.imdb.BaseClasses.BasePage;
import org.imdb.customWebElem.ExWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class HomePage extends BasePage {

    By cssImdbLogoIcon=By.cssSelector("svg[id='home_img']");
    By cssImdbSearchTextBox=By.cssSelector("input[id='suggestion-search']");
    By cssSearchButton=By.cssSelector("button[id='suggestion-search-button']");


    public HomePage(WebDriver driver) {
        super(driver);
    }

    public ExWebElement getImdbLogIcon(){
        return new ExWebElement(driver,cssImdbLogoIcon);
    }

    public ExWebElement getSearchBox() { return new ExWebElement(driver,cssImdbSearchTextBox);}

    public ExWebElement getSearchButton() {return new ExWebElement(driver,cssSearchButton);}

}
