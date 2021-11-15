package com.epam.auto.test;

import com.epam.auto.page.GoogleCloudPage;
import com.epam.auto.service.SearchTextCreator;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Factory;
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

@Log4j
public class HurtMePlentyTest extends BaseTest {

  private final String numberOfInstances;
  private final String numberOfNodes;
  private final String expectedVmClass;
  private final String expectedInstanceType;
  private final String expectedRegion;
  private final String expectedLocalSsd;
  private final String expectedCommitmentTerm;
  private final String expectedEstimatedCost;

  @Factory(dataProvider = "dataMethod")
  public HurtMePlentyTest(String numberOfInstances, String numberOfNodes, String expectedVmClass,
      String expectedInstanceType, String expectedRegion, String expectedLocalSsd, String expectedCommitmentTerm,
      String expectedEstimatedCost) {
    this.numberOfInstances = numberOfInstances;
    this.numberOfNodes = numberOfNodes;
    this.expectedVmClass = expectedVmClass;
    this.expectedInstanceType = expectedInstanceType;
    this.expectedRegion = expectedRegion;
    this.expectedLocalSsd = expectedLocalSsd;
    this.expectedCommitmentTerm = expectedCommitmentTerm;
    this.expectedEstimatedCost = expectedEstimatedCost;
  }

  @Test(description = "Проверка результатов Hurt Me Plenty")
  public void checkResults() {
    final String searchText = SearchTextCreator.pricingCalculator();

    log.info("Opening main page and searching for text. Making calculations on calculator");
    GoogleCloudPage googleCloudPage = new GoogleCloudPage();
    googleCloudPage
        .openPage()
        .searchForText(searchText)
        .goToPricingCalculatorPage()
        .switchFrame()
        .clickComputeEngineBtn()
        .enterNumberOfInstances(numberOfInstances)
        .selectOperatingSystemSoftware()
        .selectMachineClass()
        .selectMachineType()
        .addToEstimate()
        .enterNumberOfNodes(numberOfNodes)
        .addNumberOfGpus()
        .selectGpu()
        .selectLocalSsd()
        .selectDatacenterLocation()
        .selectCommitUsage()
        .addToEstimateSecond();

    final String actualVmClass = findElement(By.xpath("//div[contains(text(), 'VM class')]")).getText();
    final String actualInstanceType = findElement(By.xpath(
        "//md-content[@id='compute']/descendant::div[contains(text(), 'Instance type')]"))
        .getText();
    final String actualRegion = findElement(
        By.xpath("//md-card-content[2]/descendant::md-content[2]/md-list/md-list-item[1]/div"))
        .getText();
    final String actualLocalSsd = findElement(By.xpath("//div[contains(text(), 'Local SSD')]")).getText();
    final String actualCommitmentTerm = findElement(By.xpath("//div[contains(text(), 'Commitment term')]"))
        .getText();
    final String actualEstimatedCost = findElement(By.xpath("//h2/b")).getText();

    log.info("Checking final calculations");
    Assert.assertEquals(actualVmClass, expectedVmClass);
    Assert.assertEquals(actualInstanceType, expectedInstanceType);
    Assert.assertEquals(actualRegion, expectedRegion);
    Assert.assertEquals(actualLocalSsd, expectedLocalSsd);
    Assert.assertEquals(actualCommitmentTerm, expectedCommitmentTerm);
    Assert.assertEquals(actualEstimatedCost, expectedEstimatedCost);
  }
}
