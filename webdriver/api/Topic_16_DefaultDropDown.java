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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_16_DefaultDropDown {
    WebDriver driver;
    Select select;
    String url="https://automationfc.github.io/basic-form/index.html";
    By job1 = By.id("job1");
    By job2 = By.id("job2");
    @BeforeClass
    public void beforeClass(){
    driver = new FirefoxDriver();
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    driver.manage().window().maximize();
    driver.get(url);
    }
    @Test
    public void htmlDropDown_TC02(){
    select = new Select(driver.findElement(job1));
    select.selectByVisibleText("Mobile Testing");
    Assert.assertEquals(select.getFirstSelectedOption().getText(),"Mobile Testing");

    select.selectByValue("manual");
    Assert.assertEquals(select.getFirstSelectedOption().getText(),"Manual Testing");

    select.selectByIndex(9);
    Assert.assertEquals(select.getFirstSelectedOption().getText(),"Functional UI Testing");

    List<WebElement> jobRole1 = select.getOptions();
        Assert.assertEquals(jobRole1.size(), 10);

    select = new Select(driver.findElement(job2));
    Assert.assertTrue(select.isMultiple());
    select.selectByVisibleText("Automation");
    select.selectByVisibleText("Mobile");
    select.selectByVisibleText("Desktop");
    List<WebElement> jobRole2 = select.getAllSelectedOptions();
        ArrayList<String> selectedItemsInJob2 = new ArrayList<>();
        ArrayList<String> itemToCompare = new ArrayList<>();
        itemToCompare.add("Automation");
        itemToCompare.add("Mobile");
        itemToCompare.add("Desktop");
    for(WebElement jobRoles : jobRole2){
        selectedItemsInJob2.add(jobRoles.getText());
    }
    Assert.assertEquals(selectedItemsInJob2,itemToCompare);

    select.deselectAll();
        Assert.assertEquals(select.getAllSelectedOptions().size(), 0);
    }
    @AfterClass
    public void afterClass(){
driver.quit();
    }
}
