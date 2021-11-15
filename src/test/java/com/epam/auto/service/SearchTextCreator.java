package com.epam.auto.service;

public class SearchTextCreator {

  public static final String TESTDATA_SEARCH_TEXT = "testdata.search.text";

  public static String pricingCalculator() {
    return TestDataReader.getTestData(TESTDATA_SEARCH_TEXT);
  }
}
