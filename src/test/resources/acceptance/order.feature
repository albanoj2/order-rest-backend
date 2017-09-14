Feature: the list of orders can be retrieved
  Scenario: client gets all orders
  	Given no orders are present
    When the client calls /order
    Then the client receives status code of 200
    And the list of clients is empty