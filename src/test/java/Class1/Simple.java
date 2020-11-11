package Class1;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Simple {

    @Test

    public void launchPage (){

        System.setProperty("webdriver.chrome.driver","./DriverExe/chromedriver");

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.facebook.com");

    }






}
