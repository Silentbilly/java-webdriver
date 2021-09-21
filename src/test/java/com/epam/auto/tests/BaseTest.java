package com.epam.auto.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

  protected final By gpuTypeOptionLocator = By.xpath("//md-option[@value='NVIDIA_TESLA_V100']");
  protected WebDriver driver;

  @BeforeMethod(alwaysRun = true)
  public void browserSetup() {
    driver = new ChromeDriver();
  }

  @AfterMethod(alwaysRun = true)
  public void browserTearDown() {
    driver.quit();
    driver = null;
  }
}
