package com.epam.auto.test;

import com.epam.auto.driver.DriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

  protected final By gpuTypeOptionLocator = By.xpath("//md-option[@value='NVIDIA_TESLA_V100']");
  protected WebDriver driver;

  @BeforeMethod()
  public void browserSetup() {
    driver = DriverSingleton.getDriver();
  }

  @AfterMethod(alwaysRun = true)
  public void browserTearDown() {
    DriverSingleton.closeDriver();
  }
}
