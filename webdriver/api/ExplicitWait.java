package api;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ExplicitWait {
    WebDriver driver;
    WebDriverWait wait;

    public void sleepInSeconds(long time){
        try {
            Thread.sleep(time*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver,5);
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @Test
    public void TC_02(){

    driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
    driver.findElement(By.xpath("//button[text()='Start']")).click();
    sleepInSeconds(5);

    Assert.assertTrue(driver.findElement(By.xpath("//h4[text()='Hello World!']")).isDisplayed());

    }


    @Test
    public void TC_03(){

    driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
    driver.findElement(By.xpath("//button[text()='Start']")).click();

    Assert.assertTrue(driver.findElement(By.xpath("//h4[text()='Hello World!']")).isDisplayed());
    }

    @Test
    public void TC_04(){

    driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
    driver.findElement(By.xpath("//button[text()='Start']")).click();
    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//img[@src='/img/ajax-loader.gif']")));
    Assert.assertTrue(driver.findElement(By.xpath("//h4[text()='Hello World!']")).isDisplayed());
    }

    @Test
    public void TC_05(){

    driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
    driver.findElement(By.xpath("//button[text()='Start']")).click();
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[text()='Hello World!']")));
    Assert.assertTrue(driver.findElement(By.xpath("//h4[text()='Hello World!']")).isDisplayed());
    }

    @Test
    public void TC_06(){

    driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".calendarContainer")));
    String noDateText = driver.findElement(By.xpath("//div[@id='ctl00_ContentPlaceholder1_ctl00_ContentPlaceholder1_Label1Panel']/span")).getText();
        System.out.println(noDateText);
    }


    @AfterClass
    public void afterClass(){
driver.quit();
    }
}
