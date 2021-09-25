package com.epam.auto.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

  protected final By searchBarLocator = By.xpath("//input[@aria-label='Search']");
  protected final By pricingCalculatorLocator = By.xpath("//b[text()='Google Cloud Platform Pricing Calculator']");
  protected final By headFrameLocator = By.xpath("//iframe[contains(@name,'goog_')]");
  protected final By computerEngineBtnLocator = By
      .xpath("//div[@class='hexagon-in2' and ancestor::div[@title='Compute Engine'] and ancestor::md-tab-item]");
  protected final By numberOfInstancesLocator = By
      .xpath("//md-input-container/child::input[@ng-model='listingCtrl.computeServer.quantity']");
  protected final By operatingSystemSoftwareLocator = By
      .xpath("//md-card-content/div/div[1]/form/div[3]/div[1]/md-input-container");
  protected final By operatingSystemSoftwareOptionLocator = By.xpath("//md-option[@value='free']");
  protected final By machineClassLocator = By
      .xpath("//md-card-content/div/div[1]/form/div[4]/div[1]/md-input-container");
  protected final By machineClassOptionLocator = By.xpath("//md-option[@value='regular']");
  protected final By machineTypeLocator = By
      .xpath("//md-card-content/div/div[1]/form/div[8]/div[1]/md-input-container");
  protected final By machineTypeOptionLocator = By
      .xpath("//md-option[@value='CP-COMPUTEENGINE-VMIMAGE-E2-STANDARD-8']");
  protected final By addGpusCheckBoxLocator = By.xpath("//md-checkbox[@aria-label='Add GPUs']");
  protected final By numberOfGpusLocator = By.xpath("//md-select[@placeholder='Number of GPUs']");
  protected final By numberOfGpusOptionLocator = By
      .cssSelector("md-option[value='0'][class='ng-scope md-ink-ripple'][ng-disabled]");
  protected final By gpuTypeLocator = By.xpath("//md-select[@placeholder='GPU type']");
  protected final By gpuTypeOptionLocator = By.xpath("//md-option[@value='NVIDIA_TESLA_V100']");
  protected final By localSsdLocator = By.xpath("//md-select[@placeholder='Local SSD']");
  protected final By localSsdOptionLocator = By.xpath("//md-option[@ng-value='24']");
  protected final By datacenterLocationLocator = By.xpath("//md-select[@placeholder='Datacenter location']");
  protected final By datacenterLocationOptionLocator = By.xpath("//md-option[@value='us-west2']");
  protected final By committedUsageLocator = By.xpath("//md-select[@placeholder='Committed usage']");
  protected final By committedUsageOptionLocator = By.xpath("//md-option[@ng-value='1']");
  protected final By numberOfNodesLocator = By
      .xpath("//md-input-container/child::input[@ng-model='listingCtrl.soleTenant.nodesCount']");
  protected final By addToEstimateBtnLocator = By.xpath("//div[1]/form/div[13]/button[@aria-label='Add to Estimate']");
  protected final By addToEstimateBtn2Locator = By.xpath("//div[2]/form/div[13]/button[@aria-label='Add to Estimate']");
  protected WebDriver driver;

  @BeforeMethod(alwaysRun = true)
  public void browserSetup() {
    driver = new ChromeDriver();
  }

  @AfterMethod(alwaysRun = true)
  public void browserTearDown() {
    driver.quit();
    driver = null;
  }
}
