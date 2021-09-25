package com.epam.auto.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class SecondDriverTest extends BaseTest {

  protected WebDriver secondDriver;

  @BeforeMethod(alwaysRun = true)
  public void browserSetup() {
    driver = new ChromeDriver();
  }

  @BeforeMethod(alwaysRun = true)
  public void browser2Setup() {
    secondDriver = new ChromeDriver();
  }

  @AfterMethod(alwaysRun = true)
  public void browserTearDown() {
    driver.quit();
    driver = null;
  }

  @AfterMethod(alwaysRun = true)
  public void browser2TearDown() {
    secondDriver.quit();
    secondDriver = null;
  }
}
