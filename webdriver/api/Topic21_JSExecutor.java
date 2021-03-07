package api;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic21_JSExecutor {
    WebDriver driver;
    JavascriptExecutor jsExecutor;
    By mobileButton = By.xpath("//a[text()='Mobile']");
    By samsungCompareButton = By.xpath("//a[@title='Samsung Galaxy']/following-sibling::div//a[text()='Add to Compare']");
    By samsungAddToCartButton = By.xpath("//a[@title='Samsung Galaxy']/following-sibling::div//button[@title='Add to Cart']");

    public String getPageDomainByJS(){
    return jsExecutor.executeScript("return document.domain").toString();
    }

    public String getUrlByJS(){
    return jsExecutor.executeScript("return document.URL").toString();
    }

    public String getTitleByJS(){
    return jsExecutor.executeScript("return document.title").toString();
    }

    public String getInnerTextByJS(){
        return jsExecutor.executeScript("return document.documentElement.innerText;").toString();
    }

    public void clickToElementByJS(By by){
        WebElement element = driver.findElement(by);
        jsExecutor.executeScript("arguments[0].click()",element);
    }

    public void scrollToElementByJS(By by){
        WebElement element = driver.findElement(by);
        jsExecutor.executeScript("arguments[0].scrollIntoView(true)",element);
    }

    public void sendkeyToElementByJS(By by ,String input){
        WebElement element = driver.findElement(by);
        jsExecutor.executeScript("arguments[0].setAttribute('value','"+input+"')",element);
    }

    public String getValidationMsgByJS(By by){
        WebElement element = driver.findElement(by);
        return jsExecutor.executeScript("return arguments[0].validationMessage;",element).toString();
    }
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
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        jsExecutor = (JavascriptExecutor) driver;
    }

    @Test
    public void TC_01(){
        driver.get("http://live.demoguru99.com/");
        System.out.println(getPageDomainByJS());
        System.out.println(getUrlByJS());
        clickToElementByJS(mobileButton);
        String mobilePageUrl = getUrlByJS();
        Assert.assertEquals(mobilePageUrl,"http://live.demoguru99.com/index.php/mobile.html");

        clickToElementByJS(samsungAddToCartButton);
        sleepInSeconds(2);
        String pageInnerText = getInnerTextByJS();
        Assert.assertTrue(pageInnerText.contains("Samsung Galaxy was added to your shopping cart."));

        clickToElementByJS(By.xpath("//a[text()='Customer Service']"));
        sleepInSeconds(1);
        Assert.assertEquals(getTitleByJS(),"Customer Service");
        scrollToElementByJS(By.xpath("//span[text()='Newsletter']"));
        sendkeyToElementByJS(By.id("newsletter"),"af@abc.com");
        sleepInSeconds(2);

        clickToElementByJS(By.xpath("//button[@title='Subscribe']"));
        sleepInSeconds(2);
        Assert.assertTrue(pageInnerText.contains("Thank you for your subscription."));



    }

    @Test
    public void TC_02(){
        driver.get("https://automationfc.github.io/html5/index.html");
        clickToElementByJS(By.xpath("//input[@value='SUBMIT']"));
        String validationMsg=getValidationMsgByJS(By.id("fname"));
        System.out.println(validationMsg);
    }

    @AfterClass
    public void afterClass(){
driver.quit();
    }
}
