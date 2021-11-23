package com.epam.auto.service;

import com.epam.auto.utils.ElementActions;
import lombok.extern.log4j.Log4j;

@Log4j
public class SearchTextCreator {

  public static final String TESTDATA_SEARCH_TEXT = "testdata.search.text";

  public static String pricingCalculator() {
    try {
      TestDataReader.getTestData(TESTDATA_SEARCH_TEXT);
    }
    catch (ExceptionInInitializerError e) {
      log.error("Can't find property.");
      ElementActions.takeScreenshot();
      e.printStackTrace();
    }
    return TestDataReader.getTestData(TESTDATA_SEARCH_TEXT);
  }
}
