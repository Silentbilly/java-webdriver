package com.epam.auto.object;

public class CalculatorDataFactory {
  public static CalculatorData createDefaultCalculatorData() {
    CalculatorData calculatorData = new CalculatorData();
    calculatorData.setNumberOfInstances("4");
    calculatorData.setNumberOfNodes("4");
    calculatorData.setExpectedVmClass("VM class: preemptible");
    calculatorData.setExpectedInstanceType("Instance type: n1-standard-1");
    calculatorData.setExpectedRegion("Region: Los Angeles");
    calculatorData.setExpectedLocalSsd("Local SSD: 24x375 GiB\nCommitted Use Discount applied");
    calculatorData.setExpectedCommitmentTerm("Commitment term: 1 Year");
    calculatorData.setExpectedEstimatedCost("Total Estimated Cost: USD 18,180.25 per 1 month");
    return calculatorData;
  }
}
