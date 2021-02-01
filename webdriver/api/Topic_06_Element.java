package api;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_06_Element {
    WebDriver driver;
    By mail = By.id("mail");
    By edu = By.id("edu");
    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://automationfc.github.io/basic-form/index.html");

    }

    public boolean elementDisplay (By by){
        WebElement element= driver.findElement(by);
        return element.isDisplayed();
    }

    public void elementSendKeys(By by, String value){
        WebElement element= driver.findElement(by);
        element.sendKeys(value);
    }
    @Test
    public void verifyIsDisplayed_TC01 (){


        Assert.assertTrue(elementDisplay(mail));
        Assert.assertTrue(driver.findElement(By.id("under_18")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.id("edu")).isDisplayed());

        boolean emailDisplay = driver.findElement(By.id("email")).isDisplayed();
        boolean under18 = driver.findElement(By.id("under_18")).isDisplayed();
        boolean educationLabel = driver.findElement(By.id("edu")).isDisplayed();

        if (emailDisplay && educationLabel){
            elementSendKeys(mail,"send keys");
            elementSendKeys(edu,"send keys");
        }
        else {
            System.out.println("No email found");
        }

        if (under18){
            driver.findElement(By.id("under_18")).click();
        }
        else {
            System.out.println("No button found");
        }
    }

    @Test
    public void verifyEnableDisable_TC02 ()
    {
        Assert.assertTrue((driver.findElement(By.id("mail")).isEnabled()));
        Assert.assertTrue((driver.findElement(By.id("under_18")).isEnabled()));
        Assert.assertTrue((driver.findElement(By.id("edu")).isEnabled()));
        Assert.assertTrue((driver.findElement(By.id("job1")).isEnabled()));
        Assert.assertTrue((driver.findElement(By.id("job2")).isEnabled()));
        Assert.assertTrue((driver.findElement(By.id("development")).isEnabled()));
        Assert.assertTrue((driver.findElement(By.id("slider-1")).isEnabled()));

        Assert.assertFalse((driver.findElement(By.id("password")).isEnabled()));
        Assert.assertFalse((driver.findElement(By.id("radio-disabled")).isEnabled()));
        Assert.assertFalse((driver.findElement(By.id("bio")).isEnabled()));
        Assert.assertFalse((driver.findElement(By.id("job3")).isEnabled()));
        Assert.assertFalse((driver.findElement(By.id("check-disbaled")).isEnabled()));
        Assert.assertFalse((driver.findElement(By.id("slider-2")).isEnabled()));

        if((driver.findElement(By.id("mail")).isEnabled())){
            System.out.println("email is enable");
        }if(!driver.findElement(By.id("password")).isEnabled()){
            System.out.println("email is disable");
        }
    }

    @Test
    public void verifyIsSelected(){
        driver.findElement(By.id("under_18")).click();
        driver.findElement(By.id("java")).click();
        driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);

        Assert.assertTrue( driver.findElement(By.id("under_18")).isSelected());
        Assert.assertTrue(  driver.findElement(By.id("java")).isSelected());
        driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);

        driver.findElement(By.id("java")).click();
        Assert.assertFalse(driver.findElement(By.id("java")).isSelected());
    }

    @Test
    public void verifyMailChimp_TC04(){
        driver.findElement(By.id("email")).sendKeys("AVNCZasda@a.com");
        driver.findElement(By.id("new_username")).sendKeys("Minh");
        driver.findElement(By.id("new_password")).sendKeys("123abcABC");

        Assert.assertFalse(driver.findElement(By.id("create-account")).isEnabled());

        driver.findElement(By.id("marketing_newsletter")).click();
        driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
        Assert.assertTrue(driver.findElement(By.id("marketing_newsletter")).isSelected());


    }
    @AfterClass
    public void afterClass(){
       driver.quit();
    }
}
