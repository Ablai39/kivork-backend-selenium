package com.example.kivorkbackendselenium.SwitchingBetweenTabs;

import com.example.kivorkbackendselenium.AuthInYandex.ConfProperties;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class SwitchingBetweenTabs {

    /*
        Selenium умеет работать только с окнами, но из-за особенности браузера Chrome
            он рассматривает окна как вкладки.
        Другие браузеры открывают новое окно.

        Если в браузере Chrome открыть вкладку, то Selenium будет считать, что мы открыли
            новое окно. Это нам позволит переключаться между ними.
     */

    public static WebDriver driver;

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebDriverWait wait = new WebDriverWait(driver, 1000);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            driver.get("https://ipbase.com/");
            Thread.sleep(5000);

            /*
                Каждое окно (вкладки), которое мы открываем
                    имеет свой дескриптор (тип String).
             */
            String window1 = driver.getWindowHandle(); // Получаем дескриптор текущего окна.

            /*
                В Selenium нет метода открытия окна,
                    поэтому мы используем JavascriptExecutor для открытия нового окна.
             */
            js.executeScript("window.open()");

            /*
                Далее открывается второе окно.
             */
            Set<String> currentWindows = driver.getWindowHandles(); // Получаем набор строк Set<String>.

            /*
                Создаем переменную для записи дескриптора второго окна(вкладки).
             */
            String window2 = null;

            /*
                Циклом перебираем множество.
             */
            for (String window : currentWindows) {
                /*
                    Сравниваем с первым окном,
                        если они не совпадают, то записываем в window2.
                */
                if (!window.equals(window1)) {
                    window2 = window;
                    break;
                }
            }

            /*
                Переключаемся на второе окно(вкладку).
             */
            driver.switchTo().window(window2);
            driver.get("https://freegeoip.io/");

            /*
                Закрываем окно(вкладку).
                Даже если мы закрыли это окно, то это еще не значит,
                    что мы перешли на новую вкладку. Selenium все еще останется на этой вкладке
                    и будут ошибки.
             */
            driver.close();

            /*
                После закрытия окна нужно вернуться на прежнюю явно.
             */
            driver.switchTo().window(window1);

            Thread.sleep(5000);


        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("Test completed successfully");
            driver.quit();
        }
    }
}
