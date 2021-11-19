package com.epam.auto.stepdefs;

import com.epam.auto.object.CalculatorDataFactory;
import com.epam.auto.page.GoogleCalculatorPage;
import com.epam.auto.page.GoogleCloudPage;
import com.epam.auto.test.BaseTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.testng.Assert;

public class CalculatorStepDefs extends BaseTest {

  private final String expectedEstimatedCost = CalculatorDataFactory.createDefaultCalculatorData()
      .getExpectedEstimatedCost();

  GoogleCloudPage googleCloudPage = new GoogleCloudPage();
  GoogleCalculatorPage googleCalculatorPage = new GoogleCalculatorPage();

  @Before
  public void SetUp() {
    browserSetup();
  }

  @After
  public void TearDown() {
    browserTearDown();
  }

  @Given("I opened Google Search")
  public void iOpenedGoogleSearch() {
    googleCloudPage.openPage();
  }

  @When("^I search ([\\w ]+)$")
  public void iSearch(String searchText) {
    googleCloudPage.searchForText(searchText)
        .goToPricingCalculatorPage();
  }

  @And("^I calculate price for ([0-9]) instances and ([0-9]) nodes$")
  public void iCalculatePriceForAnd(String numberOfInstances, String numberOfNodes) {
    googleCalculatorPage
        .switchFrame()
        .clickComputeEngineBtn()
        .enterNumberOfInstances(numberOfInstances)
        .selectOperatingSystemSoftware()
        .selectMachineClass()
        .selectMachineType()
        .addToEstimate()
        .enterNumberOfNodes(numberOfNodes)
        .activateAddGpus()
        .selectGpuType()
        .selectGpuNumber()
        .selectLocalSsd()
        .selectDatacenterLocation()
        .selectCommitUsage()
        .addToEstimateSecond();
  }

  @Then("Total Estimated Cost equals expected cost")
  public void totalEstimatedCostEqualsExpectedCost() {
    final String actualEstimatedCost = findElement(By.xpath("//h2/b")).getText();
    Assert.assertEquals(actualEstimatedCost, expectedEstimatedCost);
  }
}
