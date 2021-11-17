package com.epam.auto.page;

import com.epam.auto.utils.ElementActions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleCloudPage extends BasePage {

  private static final String HOMEPAGE_URL = "https://cloud.google.com";

  @FindBy(xpath = "//input[@aria-label='Search']")
  private WebElement searchBar;

  public GoogleCloudPage() {
    super();
  }

  public GoogleCloudPage openPage() {
    get(HOMEPAGE_URL);
    return this;
  }

  public GoogleCalculatorPage searchForText(String searchText) {
    ElementActions.highlightElement(searchBar);
    searchBar.click();
    searchBar.sendKeys(searchText);
    searchBar.sendKeys(Keys.RETURN);
    return new GoogleCalculatorPage();
  }
}
