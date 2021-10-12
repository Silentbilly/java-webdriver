package com.epam.auto.utils;

import java.util.List;
import lombok.experimental.UtilityClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@UtilityClass
public class WaitingUtils {

  public static WebElement waitForElementLocatedBy(WebDriver driver, By by) {
    return new WebDriverWait(driver, 20)
        .until(ExpectedConditions.presenceOfElementLocated(by));
  }

  public static WebElement waitForElementVisibility(WebDriver driver, By by) {
    return new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(driver.findElement(by)));
  }
}
