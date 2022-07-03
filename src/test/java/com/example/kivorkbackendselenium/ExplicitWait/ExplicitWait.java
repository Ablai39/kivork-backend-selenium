package com.example.kivorkbackendselenium.ExplicitWait;

import com.example.kivorkbackendselenium.AuthInYandex.ConfProperties;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class ExplicitWait {

    public static WebDriver driver;

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get(ConfProperties.getProperty("loginpage"));

        /*
            implicitlyWait - Неявное ожидание.
                Чтобы установить Implicit Wait, необходимо написать всего одну строку после установки драйвера,
                и таким образом мы установим лимит ожидания 10 секунд
         */
        driver.manage().timeouts().implicitlyWait(5000,
                TimeUnit.MILLISECONDS);

        WebElement element = driver.findElement(By.id("passp:exp-register"));
        Assert.assertTrue(element.isDisplayed());

        element.click();

        /*
            Явное ожидание — это код, которым вы определяете
                какое необходимое условие должно произойти для того,
                чтобы дальнейший код исполнился.
            Использую для этого WebDriverWait.
         */
        WebDriverWait wait = new WebDriverWait(driver, 10);

        /*
            Жду явно, пока не появится кнопка "Зарегистрироваться".
         */
        wait.until(ExpectedConditions.invisibilityOfElementLocated
                (By.cssSelector("span.Button2-Text")));

        System.out.println("Test completed successfully");

        driver.quit();
    }
}
