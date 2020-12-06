package HomeWork14;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class TestHomeWork14 {


    /**
     * Homework 14
     * Due: Dec 3rd
     *
     * darksky.net
     * Testcase-1: Verify low/high temp on Today timeline
     *
     * hotels.com
     * Testcase-2: Verify the number of nights on black briefcase
     * checkin: tomorrow (Dec-2)
     * checkout: 7 days from checkin date (Dec-9)
     *

     */

    @Test

    public void verificationOfLowHighTemp(){
        System.setProperty("webdriver.chrome.driver", "./DriverExe/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.darksky.net");



        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("scrollBy(0,700);");

        Wait fWait = new FluentWait(driver);
                ((FluentWait) fWait).withTimeout(Duration.ofSeconds(30));
                ((FluentWait) fWait).pollingEvery(Duration.ofSeconds(1));
                ((FluentWait) fWait).ignoring(ElementClickInterceptedException.class);
                ((FluentWait) fWait).ignoring(NoSuchElementException.class);
                 ((FluentWait) fWait).withMessage("Element is not found after 30 seconds of wait");


          WebElement element = (WebElement) fWait.until(new Function<WebDriver, WebElement>() {
              public WebElement apply(WebDriver driver){
                  return driver.findElement(By.xpath("//a[@data-day='0']//span[@class='toggle']//span[@class='open']"));
              }

          }) ;



       fWait.until(ExpectedConditions.elementToBeClickable(By.xpath(
               "//a[@data-day='0']//span[@class='toggle']//span[@class='open']")));

        driver.findElement(By.xpath("//a[@data-day='0']//span[@class='toggle']//span[@class='open']")).click();

        WebElement minActualTemp = driver.findElement(By.xpath("//a[@data-day='0']//span[@class='minTemp']"));
        String minTemp = minActualTemp.getText();
        System.out.println(minTemp);
        String[] minString = minTemp.split("˚");
        int actualMinTemp = Integer.parseInt(minString[0]);
        System.out.println(actualMinTemp);

        WebElement maxActualTemp = driver.findElement(By.xpath("//a[@data-day='0']//span[@class='maxTemp']"));
        String maxTemp = maxActualTemp.getText();
        System.out.println(maxTemp);
        String[] maxString = maxTemp.split("˚");
        int actualMaxTemp = Integer.parseInt(maxString[0]);
        System.out.println(actualMaxTemp);



        js.executeScript("scrollBy(0,-700);");

        WebElement exMinTemp = driver.findElement(By.className("low-temp-text"));
        String xMinTemp = exMinTemp.getText();
        String[] strings = xMinTemp.split("˚");
        int expecteMinTemp = Integer.parseInt(strings [0]);
        System.out.println(expecteMinTemp);

        WebElement exMaxTemp = driver.findElement(By.className("high-temp-text"));
        String xMaxTemp = exMaxTemp.getText();
        String[] xStrings = xMaxTemp.split("˚");
        int expecteMaxTemp = Integer.parseInt(xStrings [0]);
        System.out.println(expecteMaxTemp);

        Assert.assertEquals(actualMinTemp, expecteMinTemp, "Actual and expected values are not same.");

        Assert.assertEquals(actualMaxTemp, expecteMaxTemp, "Actual and expected values are not same.");

        driver.quit();



    }

    /**
     * * hotels.com
     *      * Testcase-2: Verify the number of nights on black briefcase
     *      * checkin: tomorrow (Dec-2)
     *      * checkout: 7 days from checkin date (Dec-9)
     */


    @Test

    public void verifyNumberOfNigts(){

        System.setProperty("webdriver.chrome.driver", "./DriverExe/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.hotels.com");

        driver.findElement(By.id("qf-0q-localised-check-in")).click();// checkIn bar click

        List<WebElement> dates = driver.findElements(By.xpath("//td[starts-with(@data-date,'2020-11')]"));

        String checkIn;
        Date date;
        Format formatter;
        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.DATE,1);
        date= calendar.getTime();
        formatter= new SimpleDateFormat("d");
        checkIn = formatter.format(date);
        System.out.println(checkIn); // check in date tommorrow


        for (WebElement element: dates){
            if (element.getText().equalsIgnoreCase(checkIn)){
                element.click();
                break;
            }
        }

        String checkOut;


        calendar.add(Calendar.DATE,7);
        date= calendar.getTime();
        formatter= new SimpleDateFormat("d");
        checkOut = formatter.format(date);
        System.out.println(checkOut); // check out date 7 days after checkIn


        driver.findElement(By.id("qf-0q-localised-check-out")).click(); // click on check out bar
        dates = driver.findElements(By.xpath("//td[starts-with(@data-date,'2020-11')]"));

        for (WebElement element : dates){
            if (element.getText().equalsIgnoreCase(checkOut)){
                element.click();
                break;
            }
        }


        WebElement actualNight = driver.findElement(By.className("widget-query-num-nights"));
        int actualNightInBriefcase = Integer.parseInt(actualNight.getText());

        int checkIngDate = Integer.parseInt(checkIn);
        int checkOutDate = Integer.parseInt(checkOut);

        int expectedNightStay = checkOutDate - checkIngDate;

        Assert.assertEquals(actualNightInBriefcase, expectedNightStay, "Actual and ExpectedValue are not same");



    }

    /**
     *  * Testcase-3: Enter the user details as mentioned
     *      * Rooms - 1
     *      * Adult - 1
     *      * Children - 2 (Ages: 1, 2)
     *      * Verify user details on Search page as entered/selected on Homepage
     */

    @Test
    public void verifyUserDetails(){

        System.setProperty("webdriver.chrome.driver", "./DriverExe/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.hotels.com");

        WebElement addressBar = driver.findElement(By.id("qf-0q-destination"));
        addressBar.clear();
        addressBar.sendKeys("Times Square");

        Wait fWait = new FluentWait(driver);
        ((FluentWait) fWait).withTimeout(Duration.ofSeconds(30));
        ((FluentWait) fWait).pollingEvery(Duration.ofSeconds(1));
        ((FluentWait) fWait).ignoring(ElementClickInterceptedException.class);
        ((FluentWait) fWait).ignoring(NoSuchElementException.class);
        ((FluentWait) fWait).withMessage("Element is not found after 30 seconds of wait");


        WebElement element = (WebElement) fWait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver){
                return driver.findElement(By.xpath("//div[@class='autosuggest-category-result']"));
            }

        }) ;



        fWait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "//div[@class='autosuggest-category-result']")));

        List<WebElement> elements = driver.findElements(By.xpath("//div[@class='autosuggest-category-result']"));

        String toBeSelected = "Times Square, New York, New York, United States of America";


        for (WebElement suggestion : elements){
           if (toBeSelected.equalsIgnoreCase(suggestion.getText())){
               suggestion.click();
               break;
           }
        }

        driver.findElement(By.id("qf-0q-localised-check-in")).click();// checkIn bar click

        List<WebElement> dates = driver.findElements(By.xpath("//td[starts-with(@data-date,'2020-11')]"));

        String checkIn;
        Date date;
        Format formatter;
        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.DATE,1);
        date= calendar.getTime();
        formatter= new SimpleDateFormat("d");
        checkIn = formatter.format(date);
        System.out.println(checkIn); // check in date tommorrow


        for (WebElement element1: dates){
            if (element1.getText().equalsIgnoreCase(checkIn)){
                element1.click();
                break;
            }
        }

        String checkOut;


        calendar.add(Calendar.DATE,7);
        date= calendar.getTime();
        formatter= new SimpleDateFormat("d");
        checkOut = formatter.format(date);
        System.out.println(checkOut); // check out date 7 days after checkIn


        driver.findElement(By.id("qf-0q-localised-check-out")).click(); // click on check out bar
        dates = driver.findElements(By.xpath("//td[starts-with(@data-date,'2020-11')]"));

        for (WebElement element2 : dates){
            if (element2.getText().equalsIgnoreCase(checkOut)){
                element2.click();
                break;
            }
        }

        WebElement numberOfRooms = driver.findElement(By.id("qf-0q-rooms"));
        Select select = new Select(numberOfRooms);
        select.selectByVisibleText("1");


        WebElement numberOfAdults = driver.findElement(By.id("qf-0q-room-0-adults"));
        select = new Select(numberOfAdults);
        select.selectByVisibleText("1");


        WebElement numberOfChildren = driver.findElement(By.id("qf-0q-room-0-children"));
        select = new Select(numberOfChildren);
        select.selectByVisibleText("2");


        WebElement age1 = driver.findElement(By.id("qf-0q-room-0-child-0-age"));
        select = new Select(age1);
        select.selectByVisibleText("1");


        WebElement age2 = driver.findElement(By.id("qf-0q-room-0-children"));
        select = new Select(age2);
        select.selectByVisibleText("2");


        driver.findElement(By.xpath("//button[@type='submit']")).click();


         fWait = new FluentWait(driver);
        ((FluentWait) fWait).withTimeout(Duration.ofSeconds(30));
        ((FluentWait) fWait).pollingEvery(Duration.ofSeconds(1));
        ((FluentWait) fWait).ignoring(ElementClickInterceptedException.class);
        ((FluentWait) fWait).ignoring(NoSuchElementException.class);
        ((FluentWait) fWait).withMessage("Element is not found after 30 seconds of wait");


         element = (WebElement) fWait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver){
                return driver.findElement(By.id("q-rooms"));
            }

        }) ;



        fWait.until(ExpectedConditions.elementToBeClickable(By.id(
                "q-rooms")));

        WebElement Room = driver.findElement(By.id("q-rooms"));
        select = new Select(Room);

        int actualNumberOfRooms = Integer.parseInt(select.getFirstSelectedOption().getText());
        System.out.println(actualNumberOfRooms);

        select = new Select(driver.findElement(By.id("q-room-0-adults")));
        int actualNumberOfAdults = Integer.parseInt(select.getFirstSelectedOption().getText());
        System.out.println(actualNumberOfAdults);

        select = new Select(driver.findElement(By.id("q-room-0-children")));
        int actualNumberOfChildren = Integer.parseInt(select.getFirstSelectedOption().getText());
        System.out.println(actualNumberOfChildren);

        select = new Select(driver.findElement(By.id("q-room-0-child-0-age")));
        int actualAgeOfChild1 = Integer.parseInt(select.getFirstSelectedOption().getText());
        System.out.println(actualAgeOfChild1);

        select = new Select(driver.findElement(By.id("q-room-0-child-1-age")));
        int actualAgeOfChild2 = Integer.parseInt(select.getFirstSelectedOption().getText());
        System.out.println(actualAgeOfChild2);

        Assert.assertEquals(actualNumberOfRooms,1, "Actual and Expected Values are not same");
        Assert.assertEquals(actualNumberOfAdults,1, "Actual and Expected values are not same");
        Assert.assertEquals(actualNumberOfChildren, 2, "Actual and Expected values are not same");
        Assert.assertEquals(actualAgeOfChild1, 1, "Actual and Expected values are not same");
        Assert.assertEquals(actualAgeOfChild2, 2, "Actual and Expected Values are not same");











    }





}
