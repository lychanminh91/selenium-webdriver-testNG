package api;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_02_Xpath_CSS extends Create_Random_Email {
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
    public void logInWithEmptyEmailAndPasswordTC_01 ()  {

        driver.findElement(By.xpath("//div[@class='footer']/div[4]/ul/li[@class='first']/a[@title='My Account']")).click();
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        sleepInSecond(2);
        driver.findElement(By.id("send2")).click();
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);

        String emailErrorMsg = driver.findElement(By.xpath("//*[@id='advice-required-entry-email']")).getText();
        String passErrorMsg = driver.findElement(By.xpath("//*[@id='advice-required-entry-pass']")).getText();

        Assert.assertEquals(emailErrorMsg,"This is a required field.");
        Assert.assertEquals(passErrorMsg,"This is a required field.");
    }
    @Test
    public void logInWithInvalidEmailTC_02 ()  {
        String email = "1234@1234.1234";
        String passWord = "2314235";
        driver.navigate().refresh();
        driver.findElement(By.xpath("//div[@class='footer']/div[4]/ul/li[@class='first']/a[@title='My Account']")).click();
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        sleepInSecond(2);
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("pass")).sendKeys(passWord);
        driver.findElement(By.id("send2")).click();
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);

        String emailErrorMsg = driver.findElement(By.xpath("//*[@id='advice-validate-email-email']")).getText();
        //String passErrorMsg = driver.findElement(By.xpath(".//*[@id='advice-required-entry-pass']")).getText();

        Assert.assertEquals(emailErrorMsg,"Please enter a valid email address. For example johndoe@domain.com.");
        //Assert.assertEquals(passErrorMsg,"This is a required field.");
    }

    @Test
    public void logInShortPasswordTC_03 ()  {
        String email = "automation@gmail.com";
        String passWord = "123";
        driver.navigate().refresh();
        driver.findElement(By.xpath("//div[@class='footer']/div[4]/ul/li[@class='first']/a[@title='My Account']")).click();
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        sleepInSecond(2);
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("pass")).sendKeys(passWord);
        driver.findElement(By.id("send2")).click();
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);

        //String emailErrorMsg = driver.findElement(By.xpath("//*[@id='advice-validate-email-email']")).getText();
        String passErrorMsg = driver.findElement(By.id("advice-validate-password-pass")).getText();

        Assert.assertEquals(passErrorMsg,"Please enter 6 or more characters without leading or trailing spaces.");
        //Assert.assertEquals(passErrorMsg,"This is a required field.");
    }

    @Test
    public void logInWithCorrectEmailAndWrongPassword_TC04 ()  {
        String email = "automation@gmail.com";
        String passWord = "123123123";
        driver.navigate().refresh();
        driver.findElement(By.xpath("//div[@class='footer']/div[4]/ul/li[@class='first']/a[@title='My Account']")).click();
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        sleepInSecond(2);
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("pass")).sendKeys(passWord);
        driver.findElement(By.id("send2")).click();
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);

        //String emailErrorMsg = driver.findElement(By.xpath("//*[@id='advice-validate-email-email']")).getText();
        String passErrorMsg = driver.findElement(By.xpath("//div[@class='account-login']/ul[1]/li[@class='error-msg']/ul/li")).getText();

        Assert.assertEquals(passErrorMsg,"Invalid login or password.");
        //Assert.assertEquals(passErrorMsg,"This is a required field.");
    }

    @Test
    public void createNewAccount_TC05 ()  {
        String firstName = "Minh_First_Name";
        String lastName = "Minh_Last_Name";
        String email = generateEmail()+"@gmail.com";
        String passWord = "12345678";
        driver.navigate().refresh();
        driver.findElement(By.xpath("//div[@class='footer']/div[4]/ul/li[@class='first']/a[@title='My Account']")).click();
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        sleepInSecond(2);

        driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);

        driver.findElement(By.id("firstname")).sendKeys(firstName);
        driver.findElement(By.id("lastname")).sendKeys(lastName);
        driver.findElement(By.id("email_address")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys(passWord);
        driver.findElement(By.id("confirmation")).sendKeys(passWord);
        driver.findElement(By.id("is_subscribed")).click();
        sleepInSecond(4);
        driver.findElement(By.className("validation-passed")).click();





    }
    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
