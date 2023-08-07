package org.imdb.tests.BaseClasses;

import io.restassured.RestAssured;
import org.imdb.Driver.DriverController;
import org.imdb.pages.HomePage;
import org.imdb.pages.SearchResultsPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public abstract class BaseAPIUITests {
    protected static Properties properties;
    DriverController driverController;
    private final String APP_PROPERTIES=System.getProperty("user.dir")+"/src/main/resources/application.properties";

    @BeforeSuite
    public void loadProp() throws IOException {
        loadProperties();
        RestAssured.baseURI= properties.getProperty("apiUrl");
    }


    @BeforeMethod
    public void initDriver(){
        driverController=new DriverController();
        WebDriver driver=driverController.createDriver();
        driver.manage().window().maximize();
        driver.get(properties.getProperty("appUrl"));
    }

    @AfterMethod
    public void closeDriver(){
        driverController.getDriver().quit();
        driverController.removeDriver();
    }

    public HomePage getHomePage(){
        return new HomePage(driverController.getDriver());
    }

    public SearchResultsPage getSearchResultsPage() { return new SearchResultsPage(driverController.getDriver());}

    private Properties loadProperties() throws IOException {
        if(properties==null){
            properties=new Properties();
            properties.load(new FileReader(APP_PROPERTIES));}
        return properties;
    }
}
