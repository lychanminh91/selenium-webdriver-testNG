package api;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_17_CustomDropDown {
    WebDriver driver;
    WebDriverWait explicitWait;
    String parentXpath = "//span[@id='number-button']";
    public void selectCustomDropdown(String parentXpath, String allItemXpath, String expectedText){
    driver.findElement(By.xpath(parentXpath)).click();
    sleepInSeconds(2);

    explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));

    List<WebElement> allItems = driver.findElements(By.xpath(allItemXpath));
    for (WebElement item : allItems){
        if(item.getText().equals(expectedText)){
            item.click();
            break;
        }
    }
    Assert.assertEquals(driver.findElement(By.xpath(parentXpath)).getText(),expectedText);
    }

    public void sleepInSeconds (long time){
        try {
            Thread.sleep(time* 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", ".\\browserDriver\\chromedriver.exe");
        driver = new ChromeDriver();
        explicitWait = new WebDriverWait(driver, 30);
        driver.manage().window().maximize();
        driver.get("https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");
    }
    @Test
    public void jQueryTC_01(){
    selectCustomDropdown(parentXpath,"//li[@class='ui-menu-item']","19");
    Assert.assertEquals(driver.findElement(By.xpath(parentXpath)).getText(),"19");
    }

    @Test
    public void angular_TC02(){

    }
    @AfterClass
    public void afterClass(){

    }
}
