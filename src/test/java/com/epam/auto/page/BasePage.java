package com.epam.auto.page;

import com.epam.auto.driver.DriverSingleton;
import com.epam.auto.utils.ElementActions;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage implements WebDriver {

  protected WebDriver driver;

  protected BasePage() {
    driver = DriverSingleton.getDriver();
    PageFactory.initElements(driver, this);
  }

  protected abstract BasePage openPage();

  public void clickElement(WebElement element) {
    new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(element));
    ElementActions.highlightElement(element);
    element.click();
  }

  public void clickJsElement(WebElement element) {
    ElementActions.highlightElement(element);
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
  }

  public void sendKeys(WebElement element, String keys) {
    ElementActions.highlightElement(element);
    new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(element));
    element.sendKeys(keys);
  }

  @Override
  public void get(String url) {
    driver.get(url);
  }

  @Override
  public String getCurrentUrl() {
    return null;
  }

  @Override
  public String getTitle() {
    return null;
  }

  @Override
  public List<WebElement> findElements(By by) {
    return null;
  }

  @Override
  public WebElement findElement(By by) {

    return by.findElement(this);
  }

  @Override
  public String getPageSource() {
    return null;
  }

  @Override
  public void close() {

  }

  @Override
  public void quit() {

  }

  @Override
  public Set<String> getWindowHandles() {
    return null;
  }

  @Override
  public String getWindowHandle() {
    return null;
  }

  @Override
  public TargetLocator switchTo() {
    return driver.switchTo();
  }

  @Override
  public Navigation navigate() {
    return null;
  }

  @Override
  public Options manage() {
    return null;
  }
}
