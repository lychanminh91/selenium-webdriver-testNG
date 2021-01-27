package api;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_06_WebCommands {
    WebDriver driver;
    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://live.demoguru99.com/");

    }

    @Test
    public void verifyCurrentUrl_TC01 (){
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        Assert.assertEquals(driver.getCurrentUrl(),"http://live.demoguru99.com/index.php/customer/account/login/");

        driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        Assert.assertEquals(driver.getCurrentUrl(),"http://live.demoguru99.com/index.php/customer/account/create/");
    }

    @Test(priority = 1)
    public void verifyTitle_TC02 (){
        driver.navigate().refresh();
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        Assert.assertEquals(driver.getTitle(),"Customer Login");

        driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        Assert.assertEquals(driver.getTitle(),"Create New Customer Account");
    }

    @Test(priority = 2)
    public void navigateBrowser_TC03 (){

        driver.navigate().refresh();
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        Assert.assertEquals(driver.getCurrentUrl(),"http://live.demoguru99.com/index.php/customer/account/create/");

        driver.navigate().back();
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        Assert.assertEquals(driver.getCurrentUrl(),"http://live.demoguru99.com/index.php/customer/account/login/");

        driver.navigate().forward();
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        Assert.assertEquals(driver.getTitle(),"Create New Customer Account");
    }

    @Test(priority = 4)
    public void verifyPageSourceCode_TC04(){

        driver.navigate().refresh();
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        Assert.assertTrue(driver.getPageSource().contains("Customer Login"));

        driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        Assert.assertTrue(driver.getPageSource().contains("Create New Customer Account"));

    }
    @AfterClass
    public void afterClass(){
    driver.quit();
    }
}
