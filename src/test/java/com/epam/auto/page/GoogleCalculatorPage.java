package com.epam.auto.page;

import com.epam.auto.utils.ElementActions;
import com.epam.auto.utils.Waiting;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleCalculatorPage extends BasePage {

  private final String searchText;
  private final By headFrameLocator = By.xpath("//iframe[contains(@name,'goog_')]");
  private final By gpuType = By.xpath("//md-select[@placeholder='Local SSD']");
  private final By numberOfGpusOptionLocator = By
      .cssSelector("md-option[value='0'][class='ng-scope md-ink-ripple'][ng-disabled]");
  private final By datacenterLocationOptionLocator = By
      .xpath(
          "//md-select-menu[@class='md-overflow']/md-content/md-optgroup/md-option/div[contains(text(), 'Los Angeles (us-west2)')]");
  @FindBy(xpath = "//b[text()='Google Cloud Platform Pricing Calculator']")
  public WebElement pricingCalculator;
  @FindBy(xpath = "//div[@class='hexagon-in2' and ancestor::div[@title='Compute Engine'] and ancestor::md-tab-item]")
  public WebElement computeEngineBtn;
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
  @FindBy(xpath = "/html/body/md-content/md-card/div/md-card-content[1]/div[2]/div/md-card/md-card-content/div/div[2]/form/div[10]/div[1]/md-input-container/md-select/md-select-value/span[1]")
  public WebElement committedUsage;
  @FindBy(xpath = "//md-option[@id='select_option_139']/div[contains(text(), '1 Year')]")
  public WebElement committedUsageOption;
  @FindBy(xpath = "//div[1]/form/div/button[@aria-label='Add to Estimate']")
  public WebElement addToEstimateBtn;
  @FindBy(xpath = "//md-input-container/child::input[@ng-model='listingCtrl.soleTenant.nodesCount']")
  public WebElement numberOfNodes;
  @FindBy(xpath = "//md-checkbox[@aria-label='Add GPUs']")
  public WebElement addGpusCheckBox;
  @FindBy(xpath = "//md-select[@placeholder='Number of GPUs']")
  public WebElement numberOfGpus;
  @FindBy(xpath = "//md-select[@placeholder='Local SSD']")
  public WebElement localSsd;
  @FindBy(xpath = "//md-option[@ng-value='24']")
  public WebElement localSsdOption;
  @FindBy(xpath = "//div[2]/form/div[10]/div[1]/md-input-container/md-select/md-select-value/span[2]")
  public WebElement datacenterLocation;
  @FindBy(xpath = "//div[2]/form/div/button[@aria-label='Add to Estimate']")
  public WebElement addToEstimateBtn2;

  public GoogleCalculatorPage(String searchText) {
    super();
    this.searchText = searchText;
  }

  public GoogleCalculatorPage goToPricingCalculatorPage() {
    clickElement(pricingCalculator);
    return this;
  }

  public GoogleCalculatorPage clickComputeEngineBtn() {
    clickElement(computeEngineBtn);
    return this;
  }

  public GoogleCalculatorPage enterNumberOfInstances(String numOfInst) {
    sendKeys(numberOfInstances, numOfInst);
    return this;
  }

  public GoogleCalculatorPage enterNumberOfNodes(String numOfNodes) {
    sendKeys(numberOfNodes, numOfNodes);
    return this;
  }

  public GoogleCalculatorPage selectOperatingSystemSoftware() {
    clickElement(operatingSystemSoftware);
    clickJsElement(operatingSystemSoftwareOption);
    return this;
  }

  public GoogleCalculatorPage selectMachineClass() {
    clickJsElement(machineClass);
    return this;
  }

  public GoogleCalculatorPage selectMachineType() {
    clickElement(machineType);
    clickJsElement(machineTypeOption);
    return this;
  }

  public GoogleCalculatorPage addNumberOfGpus() {
    clickJsElement(addGpusCheckBox);
    clickJsElement(numberOfGpus);
    return this;
  }

  public GoogleCalculatorPage selectLocalSsd() {
    clickJsElement(localSsd);
    clickJsElement(localSsdOption);
    return this;
  }

  public GoogleCalculatorPage selectDatacenterLocation() {
    clickJsElement(datacenterLocation);
    selectDatacenterLocationOptionWithWait();
    return this;
  }

  public GoogleCalculatorPage selectCommitUsage() {
    clickJsElement(committedUsage);
    clickJsElement(committedUsageOption);
    return this;
  }

  public GoogleCalculatorPage selectGpu() {
    selectGpuWithWait();
    selectGpuTypeWithWait();
    return this;
  }

  public GoogleCalculatorPage addToEstimate() {
    clickJsElement(addToEstimateBtn);
    return this;
  }

  public void addToEstimateSecond() {
    clickJsElement(addToEstimateBtn2);
  }


  public void selectGpuWithWait() {
    ElementActions.selectOptionWithWait(numberOfGpusOptionLocator);
  }

  public void selectGpuTypeWithWait() {
    ElementActions.selectOptionWithWait(gpuType);
  }

  public void selectDatacenterLocationOptionWithWait() {
    ElementActions.selectOptionWithWait(datacenterLocationOptionLocator);
  }

  public GoogleCalculatorPage switchFrame() {
    WebElement headFrame = Waiting
        .waitForElementLocatedBy(headFrameLocator);

    switchTo().frame(headFrame)
        .switchTo().frame("myFrame");
    return this;
  }

  protected BasePage openPage() {
    throw new RuntimeException("You don't need to open any page anymore");
  }
}
