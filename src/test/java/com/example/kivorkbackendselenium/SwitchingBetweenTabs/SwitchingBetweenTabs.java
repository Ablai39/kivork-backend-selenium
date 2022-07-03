package com.example.kivorkbackendselenium.SwitchingBetweenTabs;

import com.example.kivorkbackendselenium.AuthInYandex.ConfProperties;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class SwitchingBetweenTabs {

    public static WebDriver driver;

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebDriverWait wait = new WebDriverWait(driver, 1000);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            driver.get("https://ipbase.com/");
            Thread.sleep(5000);
            String window1 = driver.getWindowHandle();
            js.executeScript("window.open()");
            Set<String> currentWindows = driver.getWindowHandles();
            String window2 = null;

            for (String window : currentWindows) {
                if (!window.equals(window1)) {
                    window2 = window;
                    break;
                }
            }

            driver.switchTo().window(window2);
            driver.get("https://www.youtube.com/");


        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            driver.quit();
        }
    }
}
