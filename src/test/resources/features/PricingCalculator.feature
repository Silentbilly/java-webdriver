Feature: Calculate price

  Scenario Outline: Calculating price
    Given I opened Google Search
    When I search <text>
    And I calculate price for <instances> instances and <nodes> nodes
    Then Total Estimated Cost equals expected cost
    Examples:
      | text                                     | instances | nodes |
      | Google Cloud Platform Pricing Calculator | 4         | 4     |