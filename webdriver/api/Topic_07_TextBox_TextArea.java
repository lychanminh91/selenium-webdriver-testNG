package api;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_07_TextBox_TextArea extends Create_Random_Email {

    By userIdInput = By.name("uid");
    By userPassword = By.name("password");
    By newCustomerLink = By.xpath("//a[text()='New Customer']");
    String name;
    String dob;
//mngr306233
//bYnajuv
    WebDriver driver;
    String email = generateEmail();
    Select selectAcion;
    public void sendKey(By by,String value){
        WebElement element = driver.findElement(by);


//        if (!element.getText().isBlank()) {
//            element.clear();
//            element.sendKeys(value);
//        }
        element.sendKeys(value);

    }
    public void clickElement(By by){
        WebElement element = driver.findElement(by);
        element.click();
    }

    public void wait5Seconds (){
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
    }
    public String getText(By by){
        WebElement element = driver.findElement(by);
        return element.getText();
    }
    @BeforeClass
    public void beforeClass(){
    driver = new FirefoxDriver();
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    driver.manage().window().maximize();
    driver.get("https://automationfc.github.io/basic-form/index.html");

    name = "Selenium Online";
    dob ="";
    dob ="";
    dob ="";
    }

    @Test
    public void verifyHomePage_TC01 (){
        sendKey(userIdInput,"mngr306233");
        sendKey(userPassword,"bYnajuv");
        clickElement(By.name("btnLogin"));
        wait5Seconds();
        clickElement(newCustomerLink);
        wait5Seconds();

        sendKey((By.name("name")),name);
        sendKey(By.name("dob"),"10/01/2000");
        sendKey(By.name("addr"),"123 Address");
        sendKey(By.name("city"),"Ho Chi Minh");
        sendKey(By.name("state"),"Thu Duc");
        sendKey(By.name("pinno"),"123456");
        sendKey(By.name("telephoneno"),"123456789");
        sendKey(By.name("emailid"),email);
        sendKey(By.name("password"),"12345678");
        clickElement(By.name("sub"));

        String customerId = getText(By.xpath("//td[text()='Customer ID']/following-sibling::td"));

        Assert.assertTrue(driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).isDisplayed());
        Assert.assertEquals(getText((By.xpath("//td[text()='Customer Name']/following-sibling::td"))),"Selenium Online");
        Assert.assertEquals(getText((By.xpath("//td[text()='Gender']/following-sibling::td"))),"male");
        Assert.assertEquals(getText((By.xpath("//td[text()='Birthdate']/following-sibling::td"))),"2000-10-01");
        Assert.assertEquals(getText((By.xpath("//td[text()='Address']/following-sibling::td"))),"123 Address");
        Assert.assertEquals(getText((By.xpath("//td[text()='City']/following-sibling::td"))),"Ho Chi Minh");
        Assert.assertEquals(getText((By.xpath("//td[text()='State']/following-sibling::td"))),"Thu Duc");
        Assert.assertEquals(getText((By.xpath("//td[text()='Mobile No.']/following-sibling::td"))),"123456789");
        Assert.assertEquals(getText((By.xpath("//td[text()='Email']/following-sibling::td"))),email);

        clickElement(By.xpath("//a[text()='Edit Customer']"));
        wait5Seconds();
        sendKey(By.name("cusid"),customerId);
        clickElement(By.name("AccSubmit"));
        wait5Seconds();

        Assert.assertEquals(driver.findElement(By.name("name")).getAttribute("value"),"Selenium Online");
        Assert.assertEquals(getText(By.name("addr")),"123 Address");
        driver.findElement(By.name("city")).clear();
        sendKey(By.name("city"),"Edit Ho Chi Minh");
        driver.findElement(By.name("state")).clear();
        sendKey(By.name("state"),"Edit "+getText(By.name("state")));
        sendKey(By.name("pinno"),"654321");
        sendKey(By.name("telephoneno"),"987654321");
        sendKey(By.name("emailid"),"selenium@gmail.com");
        //clickElement(By.name("sub"));

    }

    @Test
    public void verifyDropDownBoxTC_02(){

        Select selectJob = new Select(driver.findElement(By.id("job1")));
        Assert.assertFalse(selectJob.isMultiple());
        //driver.findElement(By.id("job1")).click();
        selectJob.selectByVisibleText("Mobile Testing");
        Assert.assertEquals(driver.findElement(By.id("job1")).getText(),"Mobile Testing");




    }

    @AfterClass
    public void afterClass(){

    }
}
