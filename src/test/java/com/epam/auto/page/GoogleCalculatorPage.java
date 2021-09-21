package com.epam.auto.page;

import com.epam.auto.utils.Utils;
import com.epam.auto.utils.WaitingUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleCalculatorPage extends BasePage {

  private final String searchText;
  private final By headFrameLocator = By.xpath("//iframe[contains(@name,'goog_')]");
  @FindBy(xpath = "//b[text()='Google Cloud Platform Pricing Calculator']")
  public WebElement pricingCalculator;
  @FindBy(xpath = "//div[@class='hexagon-in2' and ancestor::div[@title='Compute Engine'] and ancestor::md-tab-item]")
  public WebElement computerEngineBtn;
  @FindBy(xpath = "//md-input-container/child::input[@ng-model='listingCtrl.computeServer.quantity']")
  public WebElement numberOfInstances;
  @FindBy(xpath = "//md-card-content/div/div[1]/form/div[3]/div[1]/md-input-container")
  public WebElement operatingSystemSoftware;
  @FindBy(xpath = "//md-option[@value='free']")
  public WebElement operatingSystemSoftwareOption;
  @FindBy(xpath = "//md-card-content/div/div[1]/form/div[4]/div[1]/md-input-container")
  public WebElement machineClass;
  @FindBy(xpath = "//md-card-content/div/div[1]/form/div[8]/div[1]/md-input-container")
  public WebElement machineType;
  @FindBy(xpath = "//md-option[@value='CP-COMPUTEENGINE-VMIMAGE-E2-STANDARD-8']")
  public WebElement machineTypeOption;
  @FindBy(xpath = "//md-select[@placeholder='Committed usage']")
  public WebElement committedUsage;
  @FindBy(xpath = "//md-option[@ng-value='1']")
  public WebElement committedUsageOption;
  @FindBy(xpath = "//div[1]/form/div[13]/button[@aria-label='Add to Estimate']")
  public WebElement addToEstimateBtn;
  @FindBy(xpath = "//md-input-container/child::input[@ng-model='listingCtrl.soleTenant.nodesCount']")
  public WebElement numberOfNodes;
  @FindBy(xpath = "//md-checkbox[@aria-label='Add GPUs']")
  public WebElement addGpusCheckBox;
  @FindBy(xpath = "//md-select[@placeholder='Number of GPUs']")
  public WebElement numberOfGpus;
  @FindBy(xpath = "//md-select[@placeholder='GPU type']")
  public WebElement gpuType;
  @FindBy(xpath = "//md-select[@placeholder='Local SSD']")
  public WebElement localSsd;
  @FindBy(xpath = "//md-option[@ng-value='24']")
  public WebElement localSsdOption;
  @FindBy(xpath = "//md-select[@placeholder='Datacenter location']")
  public WebElement datacenterLocation;
  @FindBy(xpath = "//div[2]/form/div[13]/button[@aria-label='Add to Estimate']")
  public WebElement addToEstimateBtn2;
  private final By numberOfGpusOptionLocator = By
      .cssSelector("md-option[value='0'][class='ng-scope md-ink-ripple'][ng-disabled]");
  private final By datacenterLocationOptionLocator = By.xpath("//md-option[@value='us-west2']");
  private final By gpuTypeOptionLocator = By.xpath("//md-select[@placeholder='Local SSD']");

  public GoogleCalculatorPage(WebDriver driver, String searchText) {
    super(driver);
    this.searchText = searchText;
  }

  public void clickElement(WebElement element) {
    new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(element));
    element.click();
  }

  public void clickUnclickableElement(WebElement element) {
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
  }

  public void sendKeys(WebElement element, String keys) {
    new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(element));
    element.sendKeys(keys);
  }

  public void selectGpuWithWait() {
    Utils.selectOptionWithWait(driver, numberOfGpusOptionLocator);
  }

  public void selectDatacenterLocationWithWait() {
    Utils.selectOptionWithWait(driver, datacenterLocationOptionLocator);
  }

  public void selectGpuTypeWithWait() {
    Utils.selectOptionWithWait(driver, gpuTypeOptionLocator);
  }

  public void switchFrame() {
    WebElement headFrame = WaitingUtils
        .waitForElementLocatedBy(driver, headFrameLocator);

    driver.switchTo().frame(headFrame)
        .switchTo().frame("myFrame");
  }

  protected BasePage openPage() {
    throw new RuntimeException("You don't need to open any page anymore");
  }
}
