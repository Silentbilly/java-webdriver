package com.epam.auto.object;

import lombok.Data;

@Data
public class CalculatorData {

  private String numberOfInstances;
  private String numberOfNodes;
  private String expectedVmClass;
  private String expectedInstanceType;
  private String expectedRegion;
  private String expectedLocalSsd;
  private String expectedCommitmentTerm;
  private String expectedEstimatedCost;
}
