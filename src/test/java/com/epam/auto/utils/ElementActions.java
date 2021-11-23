package com.epam.auto.utils;

import com.epam.auto.driver.CustomDriverDecorator;
import com.epam.auto.driver.DriverSingleton;
import com.epam.auto.reporting.MyLogger;
import com.epam.reportportal.message.ReportPortalMessage;
import java.io.File;
import java.io.IOException;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@UtilityClass
@Log4j
public class ElementActions {

  private final WebDriver driver = DriverSingleton.getDriver();
  private static final String SCREENSHOTS_NAME_TPL = "screenshots/scr";

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

  public static void takeScreenshot() {
    File screenshot = ((CustomDriverDecorator) driver).getScreenshotAs(OutputType.FILE);
    ReportPortalMessage message = null;
    try {
      String screenshotName = SCREENSHOTS_NAME_TPL + System.nanoTime();
      String scrPath = screenshotName + ".jpg";
      File copy = new File(scrPath);
      FileUtils.copyFile(screenshot, copy);
      String rp_message = "Screenshot:";
      message = new ReportPortalMessage(copy, rp_message);
    } catch (IOException e) {
      log.error("Failed to make screenshot");
    }
    log.info(message);
  }
}
