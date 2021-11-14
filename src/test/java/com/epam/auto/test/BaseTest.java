package com.epam.auto.test;

import com.epam.auto.driver.DriverSingleton;
import com.epam.auto.object.CalculatorData;
import com.epam.auto.object.CalculatorDataFactory;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

public class BaseTest implements WebDriver{

  protected WebDriver driver;

  @DataProvider
  public static Object[][] dataMethod() {
    CalculatorData defaultCalculatorData = CalculatorDataFactory.createDefaultCalculatorData();
    return new Object[][]{{defaultCalculatorData.getNumberOfInstances(), defaultCalculatorData.getNumberOfNodes(),
        defaultCalculatorData.getExpectedVmClass(), defaultCalculatorData.getExpectedInstanceType(),
        defaultCalculatorData.getExpectedRegion(), defaultCalculatorData.getExpectedLocalSsd(),
        defaultCalculatorData.getExpectedCommitmentTerm(), defaultCalculatorData.getExpectedEstimatedCost()}};
  }

  @BeforeMethod()
  public void browserSetup() {
    driver = DriverSingleton.getDriver();
  }

  @AfterMethod(alwaysRun = true)
  public void browserTearDown() {
    DriverSingleton.closeDriver();
  }

  @Override
  public void get(String url) {

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
    return driver.findElement(by);
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
    return null;
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
