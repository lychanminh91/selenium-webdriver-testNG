package api;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_20_FrameWindowTab {
    WebDriver driver;
    Actions action;
    WebDriverWait explicitWait;

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
        action = new Actions(driver);
        explicitWait = new WebDriverWait(driver,10);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01(){
    driver.get("https://www.zingpoll.com/");
    driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
    driver.findElement(By.xpath("//a[@id='Loginform']")).click();
    Assert.assertTrue(driver.findElement(By.xpath("//form[@id='loginForm']")).isDisplayed());
    explicitWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[@class='close']"))));

    driver.findElement(By.xpath("//button[@class='close']")).click();
    explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//form[@id='loginForm']")));
    Assert.assertFalse(driver.findElement(By.xpath("//form[@id='loginForm']")).isDisplayed());

    }

    @Test
    public void TC_02(){
        driver.get("https://bni.vn/");
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='sgpb-popup-dialog-main-div']")));
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='sgpb-popup-dialog-main-div']")).isDisplayed());
        driver.findElement(By.xpath("//div[@id='sgpb-popup-dialog-main-div']/preceding-sibling::img")).click();

    }

    @Test
    public void TC_03(){
        String input = "Selenium";
        driver.get("https://blog.testproject.io/");
        WebElement popup = driver.findElement(By.xpath("//div[@class='mailch-wrap']"));
        sleepInSeconds(10);
        if(popup.isDisplayed()){
            driver.findElement(By.xpath("//div[@id='close-mailch']")).click();
            sleepInSeconds(2);
        }
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section//input[@class='search-field']")));
        driver.findElement(By.xpath("//section//input[@class='search-field']")).sendKeys(input);

        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//section//span[@class='glass']")));
        driver.findElement(By.xpath("//section//span[@class='glass']")).click();
    explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section//h2/span[contains(text(),"+"'"+input+"')]")));
    Assert.assertTrue(driver.findElement(By.xpath("//section//h2/span[contains(text(),"+"'"+input+"')]")).isDisplayed());

    }

    @Test
    public void TC_05(){
       driver.get("https://kyna.vn/");

//       Assert.assertTrue(driver.findElement(By.xpath("//div[@class='face-content']")).isDisplayed());
//
//       driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='face-content']/iframe")));
//
//       String likes = driver.findElement(By.xpath("//div[contains(text(),'likes')]")).getText();
//       Assert.assertEquals(likes,"169K likes");
//
//       driver.switchTo().defaultContent();
//
       driver.findElement(By.id("cs-live-chat")).click();
       explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cs_chat_iframe")));
       driver.switchTo().frame(driver.findElement(By.id("cs_chat_iframe")));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Tin nhắn']/parent::label/following-sibling::textarea")));
//
//
        driver.findElement(By.xpath("//label[text()='Tin nhắn']/parent::label/following-sibling::textarea")).sendKeys("automation");
        sleepInSeconds(2);
       driver.findElement(By.xpath("//input[@value='Bắt đầu chat']")).click();
//
       Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Nhập thông tin của bạn']//following-sibling::div[contains(text(),'Tên của bạn chưa được nhập')]")).isDisplayed());
       sleepInSeconds(5);
    }

    @AfterClass
    public void afterClass(){
driver.quit();
    }
}
