package api;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_19_UserInteraction {
    WebDriver driver;
    Actions action;
    By tooltip = By.xpath("//div[text()='We ask for your age only for statistical purposes.']");
    By age = By.id("age");
    By kids = By.xpath("//div[@class='desktop-navLink']/a[text()='Kids']");
    By homeAndBath = By.xpath("//a[text()='Home & Bath']");


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
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driver.manage().window().maximize();
    }

    @Test
    public void TC_01() throws InterruptedException {
    driver.get("https://jqueryui.com/resources/demos/tooltip/default.html");

    Assert.assertFalse(driver.findElement(tooltip).isDisplayed());

    action.moveToElement(driver.findElement(age)).perform();
    Thread.sleep(3000);
    Assert.assertTrue(driver.findElement(tooltip).isDisplayed());



    }

    @Test
    public void TC_02(){
        driver.get("http://www.myntra.com/");
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        action.moveToElement(driver.findElement(kids)).perform();
        sleepInSeconds(2);
        driver.findElement(homeAndBath).click();
        Assert.assertEquals(driver.getTitle(),"Kids Home Bath - Buy Kids Home Bath online in India");

    }
////div[@id='lefmenu-mobile']//ul[@class='groupmenu-drop']
    @Test
    public void TC_03(){
        driver.get("https://hn.telio.vn/");
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        List<WebElement> menuItems = driver.findElements(By.xpath("//div[@id='lefmenu-mobile']//ul[@class='groupmenu']/li"));
        Random rnd = new Random();
        int randomMenuItem = rnd.nextInt(menuItems.size());
    }

    @Test
    public void TC_04(){
        driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        List<WebElement> numbers = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
        action.clickAndHold(numbers.get(0)).moveToElement(numbers.get(3)).release().perform();
        sleepInSeconds(3);
        List<WebElement> selectedNumbers = driver.findElements(By.className("ui-selected"));

        Assert.assertEquals(selectedNumbers.size(),4);

    }

    @Test
    public void TC_05(){
        driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        List<WebElement> numbers = driver.findElements(By.xpath("//ol[@id='selectable']/li"));

        action.keyDown(Keys.CONTROL);
        action.click(numbers.get(0))
                .click(numbers.get(2))
                .click(numbers.get(5))
                .click(numbers.get(10))
                .perform();
        action.keyUp(Keys.CONTROL);
        sleepInSeconds(3);
        List<WebElement> selectedNumbers = driver.findElements(By.className("ui-selected"));
        Assert.assertEquals(selectedNumbers.size(),4);
    }

    @Test
    public void TC_06(){
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();

        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Hello Automation Guys!']")).isDisplayed());
    }

    @AfterClass
    public void afterClass(){
driver.quit();
    }
}
