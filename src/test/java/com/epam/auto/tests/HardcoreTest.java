package com.epam.auto.tests;

import static com.epam.auto.utils.Utils.clickUnclickable;
import static com.epam.auto.utils.Utils.selectOptionWithWait;

import com.epam.auto.utils.WaitingUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Автоматизировать следующий сценарий:
 * <p>
 * 1. Открыть https://cloud.google.com/ 2. Нажав кнопку поиска по порталу вверху страницы, ввести в поле поиска"Google
 * Cloud Platform Pricing Calculator" 3. Запустить поиск, нажав кнопку поиска. 4. В результатах поиска кликнуть "Google
 * Cloud Platform Pricing Calculator" и перейти на страницу калькулятора. 5. Активировать раздел COMPUTE ENGINE вверху
 * страницы 6. Заполнить форму следующими данными: Number of instances: 4 What are these instances for?: оставить пустым
 * Operating System / Software: Free: Debian, CentOS, CoreOS, Ubuntu, or other User Provided OS VM Class: Regular
 * Instance type: n1-standard-8    (vCPUs: 8, RAM: 30 GB) Выбрать Add GPUs Number of GPUs: 1 GPU type: NVIDIA Tesla V100
 * Local SSD: 2x375 Gb Datacenter location: Frankfurt (europe-west3) Commited usage: 1 Year 7. Нажать Add to Estimate 8.
 * Проверить соответствие данных следующих полей: VM Class, Instance type, Region, local SSD, commitment term 9.
 * Проверить что сумма аренды в месяц совпадает с суммой получаемой при ручном прохождении теста.
 *
 * @author Alexander Kononov
 */

public class HardcoreTest extends SecondDriverTest {

  private final By emailEstimateBtnLocator = By.xpath("//button[@aria-label='Email Estimate']");
  private final By newEmailBtnLocator = By.xpath("//div[2]/button[1]");
  private final By copyToClipboardBtnLocator = By.id("cprnd");
  private final By emailFieldLocator = By.xpath("//input[@ng-model='emailQuote.user.email']");
  private final By sendMailBtnLocator = By.xpath("//button[@aria-label='Send Email']");
  private final By checkEmail = By.xpath("//button[@onclick='egengo();']");
  private final By totalCosts = By.xpath("//h2/b");
  private final By emailTotalCosts = By.xpath("//tr[2]/td/table/tbody/tr[3]/td[2]/h3");

  @Test(description = "Проверка результатов Hardcore")
  public void checkResults() throws InterruptedException {

    final String searchText = "Google Cloud Platform Pricing Calculator";

    driver.get("https://cloud.google.com");

    WebElement searchBar = WaitingUtils.waitForElementLocatedBy(driver, searchBarLocator);
    searchBar.click();

    searchBar.sendKeys(searchText);

    searchBar.sendKeys(Keys.RETURN);

    WebElement pricingCalculator = WaitingUtils
        .waitForElementLocatedBy(driver, pricingCalculatorLocator);
    pricingCalculator.click();

    WebElement headFrame = WaitingUtils
        .waitForElementLocatedBy(driver, headFrameLocator);

    driver.switchTo().frame(headFrame)
        .switchTo().frame("myFrame");

    WebElement computerEngineBtn = WaitingUtils
        .waitForElementLocatedBy(driver, computerEngineBtnLocator);
    computerEngineBtn.click();

    WebElement numberOfInstances = WaitingUtils.waitForElementLocatedBy(driver, numberOfInstancesLocator);
    numberOfInstances.sendKeys("4");

    WebElement operatingSystemSoftware = WaitingUtils.waitForElementLocatedBy(driver, operatingSystemSoftwareLocator);
    // //label[@for='select_1972']/parent::md-input-container
    operatingSystemSoftware.click();
    WebElement operatingSystemSoftwareOption = WaitingUtils
        .waitForElementLocatedBy(driver, operatingSystemSoftwareOptionLocator);
    operatingSystemSoftwareOption.click();

    WebElement machineClass = WaitingUtils.waitForElementLocatedBy(driver, machineClassLocator);
    machineClass.click();
    clickUnclickable(driver, machineClassOptionLocator);

    WebElement machineType = WaitingUtils.waitForElementLocatedBy(driver, machineTypeLocator);
    machineType.click();
    clickUnclickable(driver, machineTypeOptionLocator);

    clickUnclickable(driver, committedUsageLocator);
    clickUnclickable(driver, committedUsageOptionLocator);

    clickUnclickable(driver, addToEstimateBtnLocator);

    WebElement numberOfNodes = WaitingUtils.waitForElementLocatedBy(driver, numberOfNodesLocator);
    numberOfNodes.sendKeys("4");

    clickUnclickable(driver, addGpusCheckBoxLocator);
    clickUnclickable(driver, numberOfGpusLocator);
    selectOptionWithWait(driver, numberOfGpusOptionLocator);
    clickUnclickable(driver, gpuTypeLocator);
    selectOptionWithWait(driver, gpuTypeOptionLocator);

    clickUnclickable(driver, localSsdLocator);
    clickUnclickable(driver, localSsdOptionLocator);

    clickUnclickable(driver, datacenterLocationLocator);

    selectOptionWithWait(driver, datacenterLocationOptionLocator);

    clickUnclickable(driver, addToEstimateBtn2Locator);

    clickUnclickable(driver, emailEstimateBtnLocator);

    secondDriver.get("https://yopmail.com/ru/email-generator");
    secondDriver.findElement(newEmailBtnLocator).click();
    secondDriver.findElement(copyToClipboardBtnLocator).click();

    driver.findElement(emailFieldLocator).sendKeys(Keys.CONTROL, "v");

    clickUnclickable(driver, sendMailBtnLocator);

    Thread.sleep(1000);

    secondDriver.findElement(checkEmail).click();

    secondDriver.switchTo().frame(2);
    final String actualEmailTotalCosts = secondDriver.findElement(emailTotalCosts).getText();
    final String actualTotalCosts = driver.findElement(totalCosts).getText().substring(22, 35);

    Assert.assertEquals(actualTotalCosts, actualEmailTotalCosts);
  }
}
