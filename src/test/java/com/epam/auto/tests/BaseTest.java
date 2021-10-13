package com.epam.auto.tests;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseTest {

  protected final By gpuTypeOptionLocator = By.xpath("//md-option[@value='NVIDIA_TESLA_V100']");
  protected WebDriver driver;

  @Parameters({"Port"})
  @BeforeMethod(alwaysRun = true)
  public void browserSetup(String Port) throws MalformedURLException {
    String nodeUrl = "http://localhost:4444/wd/hub";

    if (Port.equalsIgnoreCase("5557")) {
      DesiredCapabilities dc = DesiredCapabilities.chrome();
      dc.setBrowserName("chrome");
      dc.setPlatform(Platform.WINDOWS);
      System.setProperty("webdriver.chrome.driver", "C://drivers//chromedriver.exe");
      driver = new RemoteWebDriver(new URL(nodeUrl), dc);
    } else if (Port.equalsIgnoreCase("5558")) {
      DesiredCapabilities dc = DesiredCapabilities.firefox();
      dc.setBrowserName("firefox");
      dc.setPlatform(Platform.WINDOWS);
      System.setProperty("webdriver.gecko.driver", "C://drivers//geckodriver.exe");
      driver = new RemoteWebDriver(new URL(nodeUrl), dc);
    }
  }

  @AfterMethod(alwaysRun = true)
  public void browserTearDown() {
    driver.quit();
    driver = null;
  }
}
