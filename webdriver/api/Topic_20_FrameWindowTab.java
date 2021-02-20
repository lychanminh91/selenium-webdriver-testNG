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

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Topic_20_FrameWindowTab {
    WebDriver driver;
    Actions action;
    WebDriverWait explicitWait;
    By facebookIcon = By.xpath("//div[@class='hotline']//div[@class='social']/a[@href='https://www.facebook.com/kyna.vn']");
    By youTubeIcon = By.xpath("//div[@class='hotline']//div[@class='social']/a[@href='https://www.youtube.com/user/kynavn']");
    By zaloIcon = By.xpath("//div[@class='hotline']//div[@class='social']/a[@href='https://zalo.me/1985686830006307471']");
    By kynaFanpage = By.xpath("//div[@class='face-content']");
    By blueCongThuongIcon = By.xpath("//a[@href='http://online.gov.vn/HomePage/CustomWebsiteDisplay.aspx?DocId=61482']");
    By redCongThuongIcon = By.xpath("//a[@href='http://online.gov.vn/Home/WebDetails/60140']");
    String facebookIconTitle = "Kyna.vn - Trang chủ | Facebook";
    String youtubeIconTitle = "Kyna.vn - YouTube";
    String zaloIconTitle = "Zalo Official Account";
    String redIconTitle = "Thông tin website thương mại điện tử - Online.Gov.VN";
    String blueIconUrl = "http://online.gov.vn/Home/WebDetails/61473";
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
    public void switchToChildWindowById(String parentID){
        Set<String> allWindows = driver.getWindowHandles();
        for(String window : allWindows){
            if(!window.equals(parentID)){
                driver.switchTo().window(window);
                break;
            }
        }
    }

    public void swithToChildWindowByTitle(String title){
        Set<String> allWindows = driver.getWindowHandles();
        for(String runWindow : allWindows){
            driver.switchTo().window(runWindow);
            String currentWin = driver.getTitle();
            if(currentWin.equals(title)){
                break;
            }
        }
    }

    public void swithToChildWindowByUrl(String url){
        Set<String> allWindows = driver.getWindowHandles();
        for(String runWindow : allWindows){
            driver.switchTo().window(runWindow);
            String currentWin = driver.getCurrentUrl();
            if(currentWin.equals(url)){
                break;
            }
        }
    }

    public boolean closeAllSubWindowById(String parentId){
    Set<String> allWindows = driver.getWindowHandles();
    for(String runWindow : allWindows){
        if(!runWindow.equals(parentId)){
            driver.switchTo().window(runWindow);
            driver.close();
        }
        driver.switchTo().window(parentId);
    }
        return driver.getWindowHandles().size() == 1;
    }

    public void clickToElement(By by){
        driver.findElement(by).click();
    }

    public boolean verifyStayInCorrectWindow(String title){
        return driver.getTitle().equals(title);
    }
    @Test
    public void TC_06_Window(){
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        String parentWindowId = driver.getWindowHandle();
        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
        sleepInSeconds(2);
        swithToChildWindowByTitle("Google");

        Assert.assertEquals(driver.getTitle(),"Google");
//      driver.close();
        driver.switchTo().window(parentWindowId);
//        sleepInSeconds(2);

        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
//        sleepInSeconds(2);

        swithToChildWindowByTitle("Facebook - Đăng nhập hoặc đăng ký");
//        sleepInSeconds(2);
        Assert.assertEquals(driver.getTitle(),"Facebook - Đăng nhập hoặc đăng ký");
        driver.switchTo().window(parentWindowId);

        driver.findElement(By.xpath("//a[text()='TIKI']")).click();
//        sleepInSeconds(2);
        swithToChildWindowByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
        Assert.assertEquals(driver.getTitle(),"Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
//        sleepInSeconds(2);

        closeAllSubWindowById(parentWindowId);



    }

    @Test
    public void TC_07(){
        driver.get("https://kyna.vn/");
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        String parentWindowId = driver.getWindowHandle();
        clickToElement(facebookIcon);
        clickToElement(youTubeIcon);
        clickToElement(zaloIcon);
        clickToElement(blueCongThuongIcon);
        clickToElement(redCongThuongIcon);

        swithToChildWindowByTitle(facebookIconTitle);
        verifyStayInCorrectWindow(facebookIconTitle);
        sleepInSeconds(2);

        swithToChildWindowByTitle(youtubeIconTitle);
        verifyStayInCorrectWindow(youtubeIconTitle);
        sleepInSeconds(2);

        swithToChildWindowByTitle(zaloIconTitle);
        verifyStayInCorrectWindow(zaloIconTitle);
        sleepInSeconds(2);

        swithToChildWindowByTitle(redIconTitle);
        verifyStayInCorrectWindow(redIconTitle);
        sleepInSeconds(2);

        swithToChildWindowByUrl(blueIconUrl);
        Assert.assertEquals(driver.getCurrentUrl(),blueIconUrl);
        sleepInSeconds(2);

        closeAllSubWindowById(parentWindowId);
    }

    @AfterClass
    public void afterClass(){
driver.quit();
    }
}
