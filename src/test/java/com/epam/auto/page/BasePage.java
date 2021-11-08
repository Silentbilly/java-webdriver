package com.epam.auto.page;

import com.epam.auto.object.CalculatorData;
import com.epam.auto.object.CalculatorDataFactory;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;

public abstract class BasePage {

  @SneakyThrows
  @DataProvider
  public static Object[][] pageDataMethod() {
    CalculatorData defaultCalculatorData = CalculatorDataFactory.createDefaultCalculatorData();
    return new Object[][]{{defaultCalculatorData.getNumberOfInstances(), defaultCalculatorData.getNumberOfNodes()}};
  }

  protected WebDriver driver;

  protected abstract BasePage openPage();

  protected BasePage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }
}
