package HomeWork;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;


public class HomeWork12final {

    /**
     * Testcase 1: Verify the feels-like temp value is between low and high temp values at any zipcode
     *
     * Step:
     * 1. Go to https://www.darksky.net
     * 2. Enter zipcode in searchbar
     * 3. Click on search button
     * 4. Collect temparature data for feels like, low and high
     * 5. Verify condition that feels like temperature is between low and high temperature
     */

    @Test
    public void verificationOfTemp(){

        System.setProperty("webdriver.chrome.driver", "./DriverExe/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.darksky.net");

        WebElement searchBar = driver.findElement(By.xpath("//input[@type='text']"));
        searchBar.clear();
        searchBar.sendKeys("10467");

        WebElement searchButton = driver.findElement(By.xpath("//img[@alt='Search Button']"));
        searchButton.click();

        WebElement temp = driver.findElement(By.xpath("//span//span[@class='feels-like-text']"));
        String tempText = temp.getText();
        System.out.println(tempText);
        String[] strings = tempText.split("˚");
        System.out.println(Arrays.toString(strings));
        int flT= Integer.parseInt(strings[0]);
        System.out.println(flT);



        WebElement lowTemperature = driver.findElement(By.xpath("//span[@class='high-low-label']/following-sibling::span[@class='low-temp-text']"));
        String[] lowTemp = lowTemperature.getText().split("˚");
        int lTemp = Integer.parseInt(lowTemp[0]);
        System.out.println(lTemp);

        WebElement highTemperature = driver.findElement(By.xpath("//span[@class='high-low-label']/following-sibling::span[@class='high-temp-text']"));
        String[] highTemp = highTemperature.getText().split("˚");
        int hTemp = Integer.parseInt(highTemp[0]);
        System.out.println(hTemp);

        boolean actualComparingTemp;

        if (flT > lTemp && flT < hTemp) {
            actualComparingTemp = true;
        } else {
            actualComparingTemp = false;

        }

        Assert.assertTrue(actualComparingTemp, "actual result and expected result is not same.");

        driver.quit();

    }

    /**
     *
     * Testcase 2: Verify the dates on the Blog Page page appears in reverse chronological order
     *Steps:
     * 1. Go to Blog.darksky.net
     * 2. Find element for the dates
     * 3. Extract the dates from the blog
     * 4. Convert it to the date format
     * 5. Then verify the reverse chronological order
     *
     *
     *
     */

    @Test

    public void verifyDateOrder(){


        System.setProperty("webdriver.chrome.driver", "./DriverExe/chromedriver");
        WebDriver driver = new ChromeDriver();


        driver.get("https://blog.darksky.net");

        WebElement firstDate = driver.findElement(By.xpath("//time[text()='August 1, 2020']"));

        String firstDateText = firstDate.getText();
        System.out.println(firstDateText);
        Date date1 = new Date();

        try {
            date1 = new SimpleDateFormat("MMMM d, yyyy").parse(firstDateText);
        } catch (ParseException p){
            p.printStackTrace();
        }




        WebElement secondDate = driver.findElement(By.xpath("//time[text()='July 1, 2020']"));

        String secondDateText = secondDate.getText();
        System.out.println(secondDateText);

        Date date2 = new Date();
        try {
            date2 =  new SimpleDateFormat("MMMM d, yyyy").parse(secondDateText);
        } catch (ParseException p){
            p.printStackTrace();
        }



        WebElement thirdDate = driver.findElement(By.xpath("//time[text()='March 31, 2020']"));

        String thirdDateText = thirdDate.getText();
        System.out.println(thirdDateText);
        Date date3 = new Date();
        try {
            date3 =  new SimpleDateFormat("MMMM d, yyyy").parse(thirdDateText);
        } catch (ParseException p){
            p.printStackTrace();
        }

        boolean isDateInReverseChronologicalOrder =  date1.after(date2) && date2.after(date3);

        Assert.assertTrue(isDateInReverseChronologicalOrder,"the dates are not in reverse chronological order");

    }



    /**
     *  Testcase 3: Verify the temperature value converts as expected as the the unit selected
     *  1. Go to www.darksky.net
     *  2.find searchBar
     *  3.Clear the searchbar
     *  4. Writing zipcode on searchbar
     *  5. find searchButton and Click on search button
     *  5. Get current temperature on default unit
     *
     *  6. Change default unit to celcius
     *  7. Get current celcius value
     *  8. Define expected celcius value using current temperature on default unit
     *  9. Verify current celcius value is equal to expexted celcius value
     */


    @Test

    public void verifyConvertedTemperature(){

        System.setProperty("webdriver.chrome.driver", "./DriverExe/chromedriver");
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.darksky.net");

        // driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

        WebElement searchBar = driver.findElement(By.xpath("//input[@type='text']"));
        searchBar.clear();
        searchBar.sendKeys("10467");

        WebElement searchButton = driver.findElement(By.xpath("//img[@alt='Search Button']"));
        searchButton.click();

        WebElement currentFarTemp = driver.findElement(By.xpath("//span[@class='summary swap']"));
        String farTempTextTemp = currentFarTemp.getText();
        System.out.println(farTempTextTemp);

        String[] strings = farTempTextTemp.split("˚");
        int fTemp = Integer.parseInt(strings[0]);
        System.out.println(fTemp);

        driver.findElement(By.xpath("//div[@class='selectric-wrapper selectric-units']/descendant::b[@class='button']")).click();


        driver.findElement(By.xpath("//div[@style='width: 77.4062px;']/descendant::li[@data-index='1']")).click();

        WebElement celciusSummary = driver.findElement(By.xpath("//span[@class='summary swap']"));
        String celciusText = celciusSummary.getText();
        String[] celciusStrings = celciusText.split("˚");
        int celciusTemp = Integer.parseInt(celciusStrings[0]);


        /**
         * T(°C) = (T(°F) - 32) × 5/9
         */

        int expectedCelcius = (fTemp-32)*5/9;

        Assert.assertEquals(celciusTemp,expectedCelcius, "Expected and actual converted temperature are not equal");


    }
}
