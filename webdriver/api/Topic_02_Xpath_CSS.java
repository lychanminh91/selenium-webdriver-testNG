package api;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_02_Xpath_CSS {
    WebDriver driver;
    @Test
    public void logInWithEmptyEmailAndPassword () throws InterruptedException {
        driver.findElement(By.xpath(".//*[@id='header']/div/div[2]/div/a/span[2]")).click();
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        driver.findElement(By.xpath(".//*[@id='header-account']/div/ul/li[1]/a")).click();
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        Thread.sleep(2000);
        driver.findElement(By.id("send2"));
        Thread.sleep(2000);
        String pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle,"Customer Login");

    }
    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://live.demoguru99.com/");

    }
    @AfterClass
    public void afterClass(){}
}
