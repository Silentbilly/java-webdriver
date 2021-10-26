package com.epam.auto.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverSingleton {

  private static final String RESOURCES_PATH = "src\\test\\resources\\";
  private static WebDriver driver;

  private DriverSingleton(){}

  public static WebDriver getDriver() {
    if (null == driver){
      if ("chrome".equals(System.getProperty("browser"))) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
      }
      WebDriverManager.firefoxdriver().setup();
      driver = new FirefoxDriver();
      driver.manage().window().maximize();
    }
    return driver;
  }

  public static void closeDriver(){
    driver.quit();
    driver = null;
  }
}
