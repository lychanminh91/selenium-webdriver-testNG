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
    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://live.demoguru99.com/");

    }
    public void sleepInSecond(long time){
        try {
            Thread.sleep(time*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void logInWithEmptyEmailAndPassword ()  {

        driver.findElement(By.xpath("//div[@class='footer']/div[4]/ul/li[@class='first']/a[@title='My Account']")).click();
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        sleepInSecond(2);
        driver.findElement(By.id("send2")).click();
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);

        String emailErrorMsg = driver.findElement(By.xpath(".//*[@id='advice-required-entry-email']")).getText();
        String passErrorMsg = driver.findElement(By.xpath(".//*[@id='advice-required-entry-pass']")).getText();

        Assert.assertEquals(emailErrorMsg,"This is a required field.");
        Assert.assertEquals(passErrorMsg,"This is a required field.");


    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
