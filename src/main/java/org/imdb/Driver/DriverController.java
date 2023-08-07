package org.imdb.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class DriverController {

    ThreadLocal<WebDriver> driverManager=new ThreadLocal<>();

    public WebDriver createDriver(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driverManager.set(driver);
        return driverManager.get();
    }

    public WebDriver getDriver(){
        return driverManager.get();
    }

    public void removeDriver(){
        driverManager.remove();
    }

}
