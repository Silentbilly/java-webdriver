package com.epam.auto.driver;

import java.util.List;
import java.util.Set;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

@Log4j
public class CustomDriverDecorator implements WebDriver, JavascriptExecutor, TakesScreenshot {

  protected WebDriver driver;

  public CustomDriverDecorator(WebDriver driver) {
    this.driver = driver;
  }

  @Override
  public void get(String s) {
    log.info(String.format("Opening page: '%s'", s));
    driver.get(s);
  }

  @Override
  public String getCurrentUrl() {
    return driver.getCurrentUrl();
  }

  @Override
  public String getTitle() {
    return driver.getTitle();
  }

  @Override
  public List<WebElement> findElements(By by) {
    return driver.findElements(by);
  }

  @Override
  public WebElement findElement(By by) {
    log.info(String.format("Finding element: %s, current URL: '%s'", by.toString(), driver.getCurrentUrl()));
    return driver.findElement(by);
  }

  @Override
  public String getPageSource() {
    return driver.getPageSource();
  }

  @Override
  public void close() {
    driver.close();
  }

  @Override
  public void quit() {
    log.info("Closing browser");
    driver.quit();
  }

  @Override
  public Set<String> getWindowHandles() {
    return driver.getWindowHandles();
  }

  @Override
  public String getWindowHandle() {
    return driver.getWindowHandle();
  }

  @Override
  public TargetLocator switchTo() {
    return driver.switchTo();
  }

  @Override
  public Navigation navigate() {
    return driver.navigate();
  }

  @Override
  public Options manage() {
    return driver.manage();
  }

  @Override
  public Object executeScript(String s, Object... objects) {
    log.debug("Implementing JsExecutor");
    return ((JavascriptExecutor)driver).executeScript(s, objects);
  }

  @Override
  public Object executeAsyncScript(String s, Object... objects) {
    return null;
  }

  @Override
  public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
    return  ((TakesScreenshot) driver).getScreenshotAs(target);
  }
}
