package com.epam.auto.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {

  protected WebDriver driver;

  protected abstract BasePage openPage();
  protected final  int WAIT_TIMEOUT_SECONDS = 10;

  protected BasePage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }
}
