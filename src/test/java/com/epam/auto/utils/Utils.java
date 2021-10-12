package com.epam.auto.utils;

import lombok.experimental.UtilityClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@UtilityClass
public class Utils {

    public static void selectOptionWithWait(WebDriver driver, By by) {
        WaitingUtils.waitForElementVisibility(driver, by);
        WebElement element = WaitingUtils.waitForElementLocatedBy(driver, by);
        highlightElement(driver, element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public static void highlightElement(WebDriver driver, WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].setAttribute('style', 'border:2px solid red; background:yellow');",
                element);
    }
}
