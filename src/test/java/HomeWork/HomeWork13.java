package HomeWork;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class HomeWork13 {

    /**
     *
     * Homework: 13
     * Due Date: Nov 28th, 2020
     *
     * Testcase-1: User should be able to click on the yahoo notification
     * 1. Launch yahoo.com
     * 2. Move mouse to bell icon
     * 3. user should be able to click the first notification
     */

    @Test

    public void clickOnNotification(){

        System.setProperty("webdriver.chrome.driver", "./DriverExe/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.yahoo.com");

        driver.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);

        WebElement bellIcon = driver.findElement(By.id("ybarNotificationMenu"));

        Actions act = new Actions(driver);
        act.moveToElement(bellIcon).build().perform();

        WebElement firstNotification = driver.findElement(By.xpath("//button[text()='Notify Me']"));
        firstNotification.click();
        /**
         *        ......or....
         *        act.moveToElement(firstNotification).build().perform();
         *        act.click(firstNotification).build().perform();
         *
         */

    }





    /**
     *
     * Testcase-2: User should get error on invalid date of birth
     * 1. Launch facebook.com
     * 2. Click 'Create new Account' button
     * 3. Enter fname as Firstname
     * 4. Enter lname as Lastname
     * 5. "abcd@test.com" as email address
     * 6. "abcd@1234" as New Password
     * 7. Enter your "Jan 4 1998" as birth date
     * 8. Click the 'Sign Up' button
     * 9. Verify user see the error msg for gender. (Please choose a gender. You can change who can see this later.)
     *
     */

    @Test

    public void verificationOfErrorMsgForGender(){

        System.setProperty("webdriver.chrome.driver", "./DriverExe/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.facebook.com");

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();

        driver.findElement(By.name("firstname")).sendKeys("fname");

        driver.findElement(By.name("lastname")).sendKeys("lname");

        driver.findElement(By.name("reg_email__")).sendKeys("abcd@test.com");

        driver.findElement(By.id("password_step_input")).sendKeys("abcd@1234");


        driver.findElement(By.name("reg_email_confirmation__")).sendKeys("abcd@test.com");

        Select month = new Select(driver.findElement(By.id("month")));
        month.selectByVisibleText("Jan");

        Select day = new Select(driver.findElement(By.id("day")));
        day.selectByVisibleText("4");

        Select year = new Select(driver.findElement(By.id("year")));
        year.selectByVisibleText("1998");

        driver.findElement(By.xpath("//button[@name='websubmit']")).click();

        WebElement errorMsg = driver.findElement(By.xpath("//div[text()='Please choose a gender. You can change who can see this later.']"));

        Assert.assertTrue(errorMsg.isDisplayed(),"The error message is not displayed");






    }




}
