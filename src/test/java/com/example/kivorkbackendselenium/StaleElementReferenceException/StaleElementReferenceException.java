package com.example.kivorkbackendselenium.StaleElementReferenceException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StaleElementReferenceException {

    public static WebDriver driver;

    public static void main(String[] args) {

        /*
            1) How would you handle a StaleElementReferenceException?
                Please provide a code fragment or fragments,
                if you know about several common ways of handling.
         */

        /*
            Ошибка StaleElementException возникает тогда,
                когда Javascript обновляет страницу между вызовом
                findElement и вызовом click.

            Обычно такое случается, когда запускается старый код,
                так как современные JS фреймворки собираются из компонентов,
                части страниц часто подвергаются изменениям.

             Самое простое решение, как по мне просто поставить таймаут,
                прежде чем искать элемент: Thread.sleep.
         */

        int timeout = 5000;
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.refreshed(
                ExpectedConditions.elementToBeClickable(By.id("checkoutLink"))));
        driver.findElement(By.id("checkoutLink")).click();
    }
}
