Feature: the list of orders can be retrieved
  Scenario: client gets all orders
    When the client calls /order
    And no orders are present
    Then the client receives status code of 200