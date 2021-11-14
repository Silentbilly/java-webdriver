package com.epam.auto.utils;

import com.epam.auto.driver.DriverSingleton;
import lombok.experimental.UtilityClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@UtilityClass
public class Waiting {
  private final WebDriver driver = DriverSingleton.getDriver();

  public static WebElement waitForElementLocatedBy(By by) {
    return new WebDriverWait(driver, 20)
        .until(ExpectedConditions.presenceOfElementLocated(by));
  }

  public static WebElement waitForElementVisibility(By by) {
    return new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(driver.findElement(by)));
  }
}
