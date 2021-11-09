package com.epam.auto.test;

import com.epam.auto.driver.DriverSingleton;
import com.epam.auto.object.CalculatorData;
import com.epam.auto.object.CalculatorDataFactory;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

public class BaseTest {

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
}
