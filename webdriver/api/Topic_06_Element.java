package api;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_06_Element {
    WebDriver driver;
    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://automationfc.github.io/basic-form/index.html");

    }

    @Test
    public void verifyIsDisplayed_TC01 (){


        Assert.assertTrue(driver.findElement(By.id("mail")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.id("under_18")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.id("edu")).isDisplayed());

        boolean emailDisplay = driver.findElement(By.id("email")).isDisplayed();
        boolean under18 = driver.findElement(By.id("under_18")).isDisplayed();
        boolean educationLabel = driver.findElement(By.id("edu")).isDisplayed();

        if (emailDisplay && educationLabel){
            driver.findElement(By.id("mail")).sendKeys("Automation Testing");
            driver.findElement(By.id("edu")).sendKeys("Automation Testing");
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

    @AfterClass
    public void afterClass(){
//        driver.quit();
    }
}
