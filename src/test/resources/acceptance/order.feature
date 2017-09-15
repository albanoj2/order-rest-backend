Feature: User can view a list of all orders

  Scenario: User gets all orders
  	Given no orders are present
    When the user calls /order
    Then the user receives status code of 200
    And the list of orders is empty
    
  Scenario: User gets a previously created order
  	Given no orders are present
    When the user creates an order
    And the user retrieves the order
    Then the user receives status code of 200
    And client is returned