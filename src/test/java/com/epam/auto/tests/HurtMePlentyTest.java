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
 Автоматизировать следующий сценарий:

 1. Открыть https://cloud.google.com/
 2. Нажав кнопку поиска по порталу вверху страницы, ввести в поле поиска"Google Cloud Platform Pricing Calculator"
 3. Запустить поиск, нажав кнопку поиска.
 4. В результатах поиска кликнуть "Google Cloud Platform Pricing Calculator" и перейти на страницу калькулятора.
 5. Активировать раздел COMPUTE ENGINE вверху страницы
 6. Заполнить форму следующими данными:
 * Number of instances: 4
 * What are these instances for?: оставить пустым
 * Operating System / Software: Free: Debian, CentOS, CoreOS, Ubuntu, or other User Provided OS
 * VM Class: Regular
 * Instance type: n1-standard-8    (vCPUs: 8, RAM: 30 GB)
 * Выбрать Add GPUs
 * Number of GPUs: 1
 * GPU type: NVIDIA Tesla V100
 * Local SSD: 2x375 Gb
 * Datacenter location: Frankfurt (europe-west3)
 * Commited usage: 1 Year
 7. Нажать Add to Estimate
 8. Проверить соответствие данных следующих полей: VM Class, Instance type, Region, local SSD, commitment term
 9. Проверить что сумма аренды в месяц совпадает с суммой получаемой при ручном прохождении теста.
 *
 * @author Alexander Kononov
 */

public class HurtMePlentyTest extends BaseTest {

  @Test(description = "Проверка результатов Hurt Me Plenty")
  public void checkResults() {

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

    final String actualVmClass = driver.findElement(By.xpath("//md-card-content[2]/descendant::md-list-item[4]/div"))
        .getText();
    final String expectedVmClass = "VM class: regular";
    final String actualInstanceType = driver.findElement(By.xpath(
        "//md-content[@id='compute']/descendant::div[@class='md-list-item-text ng-binding cpc-cart-multiline flex']"))
        .getText();
    final String expectedInstanceType = "Instance type: e2-standard-8\n"
        + "Committed Use Discount applied";
    final String actualRegion = driver
        .findElement(By.xpath("//md-card-content[2]/descendant::md-content[2]/md-list/md-list-item[1]/div"))
        .getText();
    final String expectedRegion = "Region: Los Angeles";
    final String actualLocalSsd = driver
        .findElement(By.xpath("//md-card-content[2]/descendant::div[@class='md-list-item-text ng-binding flex']"))
        .getText();
    final String expectedLocalSsd = "Local SSD: 24x375 GiB";
    final String actualCommitmentTerm = driver
        .findElement(By.xpath("//md-card-content[2]/descendant::md-list-item[3]/div"))
        .getText();
    final String expectedCommitmentTerm = "Commitment term: 1 Year";
    final String actualEstimatedCost = driver.findElement(By.xpath("//h2/b")).getText();
    final String expectedEstimatedCost = "Total Estimated Cost: USD 19,284.66 per 1 month";

    Assert.assertEquals(actualVmClass, expectedVmClass);
    Assert.assertEquals(actualInstanceType, expectedInstanceType);
    Assert.assertEquals(actualRegion, expectedRegion);
    Assert.assertEquals(actualLocalSsd, expectedLocalSsd);
    Assert.assertEquals(actualCommitmentTerm, expectedCommitmentTerm);
    Assert.assertEquals(actualEstimatedCost, expectedEstimatedCost);
  }
}
