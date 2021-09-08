package com.epam.auto.utils;

import lombok.experimental.UtilityClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@UtilityClass
public class Utils {

  public static void createNewPasteBin(WebDriver driver) {
    driver.findElement(By.xpath("//button[text()='Create New Paste']")).click();
  }

  public static void clickUnclickable(WebDriver driver, By by) {
    WebElement element = WaitingUtils.waitForElementLocatedBy(driver, by);
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
  }

  public static void selectOptionWithWait(WebDriver driver, By by) {
    WaitingUtils.waitForElementVisibility(driver, by);
    WebElement element = WaitingUtils.waitForElementLocatedBy(driver, by);
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
  }
}
