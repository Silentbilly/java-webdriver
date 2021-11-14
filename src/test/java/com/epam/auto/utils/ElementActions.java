package com.epam.auto.utils;

import com.epam.auto.driver.CustomDriverDecorator;
import com.epam.auto.driver.DriverSingleton;
import com.epam.auto.page.GoogleCalculatorPage;
import lombok.experimental.UtilityClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@UtilityClass
public class ElementActions {
    private final WebDriver driver = DriverSingleton.getDriver();

    public static void selectOptionWithWait(By by) {
        Waiting.waitForElementVisibility(by);
        WebElement element = Waiting.waitForElementLocatedBy(by);
        highlightElement(element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public static void highlightElement(WebElement element) {
        JavascriptExecutor jsExecutor = (CustomDriverDecorator) driver;
        jsExecutor.executeScript("arguments[0].setAttribute('style', 'border:2px solid red; background:yellow');",
                element);
    }
}
