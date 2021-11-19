package com.epam.auto.page;

import com.epam.auto.utils.ElementActions;
import com.epam.auto.utils.Waiting;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleCalculatorPage extends BasePage {

  private final By headFrameLocator = By.xpath("//iframe[contains(@name,'goog_')]");
  private final By gpuTypeOption = By.xpath("//md-option[@value='NVIDIA_TESLA_P100']");
  private final By numberOfGpusOptionLocator = By
      .cssSelector("md-option[value='0'][class='ng-scope md-ink-ripple'][ng-disabled]");
  private final By datacenterLocationOptionLocator = By
      .xpath(
          "//md-select-menu[@class='md-overflow']/md-content/md-optgroup/md-option/div[contains(text(), 'Los Angeles (us-west2)')]");
  private final By committedUsageOption = By.xpath("//body/div/descendant::div[contains(text(), '1 Year')]");
  @FindBy(xpath = "//a[@data-ctorig='https://cloud.google.com/products/calculator']")
  public WebElement pricingCalculator;
  @FindBy(xpath = "//div[@class='hexagon-in2' and ancestor::div[@title='Compute Engine'] and ancestor::md-tab-item]")
  public WebElement computeEngineBtn;
  @FindBy(xpath = "//md-input-container/child::input[@ng-model='listingCtrl.computeServer.quantity']")
  public WebElement numberOfInstances;
  @FindBy(xpath = "//md-input-container[child::label[contains(text(), 'Operating System / Software')]]")
  public WebElement operatingSystemSoftware;
  @FindBy(xpath = "//md-option[@value='free']")
  public WebElement operatingSystemSoftwareOption;
  @FindBy(xpath = "//md-input-container[child::label[contains(text(), 'Machine Class')]]")
  public WebElement machineClass;
  @FindBy(xpath = "//md-option[@value='preemptible']")
  public WebElement machineClassOption;
  @FindBy(xpath = "//md-input-container[child::label[contains(text(), 'Series')]]")
  public WebElement machineType;
  @FindBy(xpath = "//md-input-container[child::label[contains(text(), 'Machine type')]]")
  public WebElement machineTypeOption;
  @FindBy(xpath = "//form[@name='SoleTenantForm']/descendant::md-input-container[child::label[contains(text(), 'Committed usage')]]/md-select")
  public WebElement committedUsage;
  @FindBy(xpath = "//form[@name='ComputeEngineForm']/div/button[@aria-label='Add to Estimate']")
  public WebElement addToEstimateBtn;
  @FindBy(xpath = "//md-input-container/child::input[@ng-model='listingCtrl.soleTenant.nodesCount']")
  public WebElement numberOfNodes;
  @FindBy(xpath = "//md-checkbox[@ng-model='listingCtrl.soleTenant.addGPUs']/div[1]")
  public WebElement addGpusCheckBox;
  @FindBy(xpath = "//form[@name='SoleTenantForm']/descendant::label[contains(text(), 'GPU type')]/parent::md-input-container/md-select")
  public WebElement gpuType;
  @FindBy(xpath = "//form[@name='SoleTenantForm']/descendant::label[contains(text(), 'Number of GPUs')]/parent::md-input-container/md-select")
  public WebElement numberOfGpus;
  @FindBy(xpath = "//md-select[@placeholder='Local SSD']")
  public WebElement localSsd;
  @FindBy(xpath = "//md-option[@ng-value='24']")
  public WebElement localSsdOption;
  @FindBy(xpath = "//form[@name='SoleTenantForm']/descendant::label[contains(text(), 'Datacenter location')]/parent::md-input-container/md-select")
  public WebElement datacenterLocation;
  @FindBy(xpath = "//form[@name='SoleTenantForm']/div/button[@aria-label='Add to Estimate']")
  public WebElement addToEstimateBtn2;

  public GoogleCalculatorPage() {
    super();
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
    clickJsElement(operatingSystemSoftware);
    clickJsElement(operatingSystemSoftwareOption);
    return this;
  }

  public GoogleCalculatorPage selectMachineClass() {
    clickJsElement(machineClass);
    clickJsElement(machineClassOption);
    return this;
  }

  public GoogleCalculatorPage selectMachineType() {
    clickJsElement(machineType);
    clickJsElement(machineTypeOption);
    return this;
  }

  public GoogleCalculatorPage activateAddGpus() {
    clickJsElement(addGpusCheckBox);
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
    ElementActions.selectOptionWithWait(committedUsageOption);
    return this;
  }

  public GoogleCalculatorPage selectGpuType() {
    clickJsElement(gpuType);
    selectGpuTypeWithWait();
    return this;
  }

  public GoogleCalculatorPage selectGpuNumber() {
    clickJsElement(numberOfGpus);
    selectNumberOfGpuWithWait();
    return this;
  }

  public GoogleCalculatorPage addToEstimate() {
    clickJsElement(addToEstimateBtn);
    return this;
  }

  public void addToEstimateSecond() {
    clickJsElement(addToEstimateBtn2);
  }


  public void selectNumberOfGpuWithWait() {
    ElementActions.selectOptionWithWait(numberOfGpusOptionLocator);
  }

  public void selectGpuTypeWithWait() {
    ElementActions.selectOptionWithWait(gpuTypeOption);
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