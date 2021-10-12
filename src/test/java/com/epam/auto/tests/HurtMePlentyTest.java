package com.epam.auto.tests;

import static com.epam.auto.utils.Utils.selectOptionWithWait;

import com.epam.auto.page.GoogleCalculatorPage;
import com.epam.auto.page.GoogleCloudPage;
import com.epam.auto.utils.Utils;
import org.openqa.selenium.By;
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

    GoogleCloudPage googleCloudPage = new GoogleCloudPage(driver);
    GoogleCalculatorPage googleCalculatorPage = new GoogleCalculatorPage(driver, searchText);

    googleCloudPage.openPage();
    googleCloudPage.searchForText(searchText);

    googleCalculatorPage.clickElement(googleCalculatorPage.pricingCalculator);

    googleCalculatorPage.switchFrame();

    googleCalculatorPage.clickElement(googleCalculatorPage.computerEngineBtn);

    googleCalculatorPage.sendKeys(googleCalculatorPage.numberOfInstances, "4");

    googleCalculatorPage.clickElement(googleCalculatorPage.operatingSystemSoftware);

    googleCalculatorPage.clickElement(googleCalculatorPage.operatingSystemSoftwareOption);

    googleCalculatorPage.clickUnclickableElement(googleCalculatorPage.machineClass);

    googleCalculatorPage.clickElement(googleCalculatorPage.machineType);
    googleCalculatorPage.clickUnclickableElement(googleCalculatorPage.machineTypeOption);

    googleCalculatorPage.clickUnclickableElement(googleCalculatorPage.committedUsage);
    googleCalculatorPage.clickUnclickableElement(googleCalculatorPage.committedUsageOption);

    googleCalculatorPage.clickUnclickableElement(googleCalculatorPage.addToEstimateBtn);

    googleCalculatorPage.sendKeys(googleCalculatorPage.numberOfNodes, "4");

    googleCalculatorPage.clickUnclickableElement(googleCalculatorPage.addGpusCheckBox);
    googleCalculatorPage.clickUnclickableElement(googleCalculatorPage.numberOfGpus);
    googleCalculatorPage.selectGpuWithWait();

    googleCalculatorPage.clickUnclickableElement(googleCalculatorPage.gpuType);
    selectOptionWithWait(driver, gpuTypeOptionLocator);

    googleCalculatorPage.clickUnclickableElement(googleCalculatorPage.localSsd);
    googleCalculatorPage.clickUnclickableElement(googleCalculatorPage.localSsdOption);

    googleCalculatorPage.clickUnclickableElement(googleCalculatorPage.datacenterLocation);
    googleCalculatorPage.selectDatacenterLocationWithWait();

    googleCalculatorPage.clickUnclickableElement(googleCalculatorPage.addToEstimateBtn2);

    final String actualVmClass = driver.findElement(By.xpath("//div[contains(text(), 'VM class')]")).getText();
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
    final String actualLocalSsd = driver.findElement(By.xpath("//div[contains(text(), 'Local SSD')]")).getText();
    final String expectedLocalSsd = "Local SSD: 24x375 GiB";
    final String actualCommitmentTerm = driver.findElement(By.xpath("//div[contains(text(), 'Commitment term')]"))
        .getText();
    final String expectedCommitmentTerm = "Commitment term: 1 Year";
    final String actualEstimatedCost = driver.findElement(By.xpath("//h2/b")).getText();
    final String expectedEstimatedCost = "Total Estimated Cost: USD 19,271.87 per 1 month";

    Assert.assertEquals(actualVmClass, expectedVmClass);
    Assert.assertEquals(actualInstanceType, expectedInstanceType);
    Assert.assertEquals(actualRegion, expectedRegion);
    Assert.assertEquals(actualLocalSsd, expectedLocalSsd);
    Assert.assertEquals(actualCommitmentTerm, expectedCommitmentTerm);
    Assert.assertEquals(actualEstimatedCost, expectedEstimatedCost);
  }
}
