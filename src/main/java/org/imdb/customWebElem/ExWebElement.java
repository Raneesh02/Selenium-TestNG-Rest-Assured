package org.imdb.customWebElem;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * This class is to be used for creating any web element in the page
 * in this way it is better to avoid putting waits anywhere in the program
 */
public class ExWebElement extends RemoteWebElement {
    WebDriver driver;
    WebDriverWait explicitWait;
    WebElement webElement;
    By byLoc;


    public ExWebElement(WebDriver driver, By byLoc){
        this.driver=driver;
        this.byLoc=byLoc;
        explicitWait=new WebDriverWait(driver,20);
    }

    public boolean exists(){
        try{
            explicitWait.until(ExpectedConditions.presenceOfElementLocated(byLoc));
            webElement=driver.findElement(byLoc);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public List<WebElement> findAllElements(){
        return driver.findElements(byLoc);
    }

    public void sendKeys(String keysToSend){
        exists();
        explicitWait.until(ExpectedConditions.elementToBeClickable(webElement));
        webElement.sendKeys(keysToSend);

    }

    public boolean isDisplayed(){
        if(!exists()) return false;
        return webElement.isDisplayed();
    }

    public void click(){
        exists();
        explicitWait.until(ExpectedConditions.elementToBeClickable(webElement));
        webElement.click();

    }


}
