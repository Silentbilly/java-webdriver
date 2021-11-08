package com.epam.auto.page;

import com.epam.auto.utils.Utils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class GoogleCloudPage extends BasePage {

  private static final String HOMEPAGE_URL = "https://cloud.google.com";

  @FindBy(xpath = "//input[@aria-label='Search']")
  private WebElement searchBar;

  public GoogleCloudPage(WebDriver driver) {
    super(driver);
  }

  public GoogleCloudPage openPage() {
    driver.get(HOMEPAGE_URL);
    return this;
  }

  public GoogleCalculatorPage searchForText(String searchText) {
    Actions action = new Actions(driver);
    Utils.highlightElement(driver, searchBar);
    searchBar.click();
    searchBar.sendKeys(searchText);
    action.sendKeys(searchBar, Keys.RETURN).build().perform();
    return new GoogleCalculatorPage(driver, searchText);
  }
}
