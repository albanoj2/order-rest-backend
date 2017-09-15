Feature: User can view a list of all orders

  Scenario: User gets a created order
    When the user creates an order
    And the order is successfully created
    And the user retrieves the created order
    Then the user receives status code of 200
    And the retrieved order is correct