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

public class Topic_18_RadioCheckboxAlert {
    WebDriver driver;
    JavascriptExecutor jsExecutor;
    By registerSection = By.cssSelector(".popup-login-tab-login");
    By registerButton = By.cssSelector(".fhs-btn-login");
    By userName = By.id("login_username");
    By password = By.id("login_password");

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        jsExecutor = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();


    }
    public void removeDisabledAttributeByJS(By by){
        WebElement element = driver.findElement(by);
        jsExecutor.executeScript("arguments[0].removeAttribute('disabled')",element);
    }

    public void checkToCheckBoxOrRadio(WebElement element){
        if(!element.isSelected()){
            element.click();
        }
    }
    public void uncheckCheckBox(WebElement element){
        if(element.isSelected()){
            element.click();
        }
    }

    public boolean isSelected(By by){
        WebElement element = driver.findElement(by);
        return element.isSelected();
    }

    public void sleepInSeconds(long time) {
        try {
            Thread.sleep(time*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void TC01(){
    driver.findElement(registerSection).click();
    Assert.assertFalse(driver.findElement(registerButton).isEnabled());

    driver.findElement(userName).sendKeys("a@gmail.com");
    driver.findElement(password).sendKeys("123456");
    Assert.assertTrue(driver.findElement(registerButton).isEnabled());

    driver.navigate().refresh();
    driver.findElement(registerSection).click();
    removeDisabledAttributeByJS(registerButton);
    driver.findElement(registerButton).click();
    sleepInSeconds(2);

    Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Số điện thoại/Email']/following-sibling::div[@class='fhs-input-alert']")).getText(),"Thông tin này không thể để trống");
    Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Mật khẩu']/following-sibling::div[@class='fhs-input-alert']")).getText(),"Thông tin này không thể để trống");
    }

    @Test
    public void TC02(){
        driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.id("onetrust-accept-btn-handler")).click();

        //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).click();

        Assert.assertTrue(isSelected(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")));

        uncheckCheckBox(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")));
        Assert.assertFalse(isSelected(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")));

        driver.navigate().refresh();
        driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        checkToCheckBoxOrRadio(driver.findElement(By.id("engine3")));
        Assert.assertTrue(isSelected(By.id("engine3")));

    }
    @AfterClass
    public void afterClass(){

    }
}
