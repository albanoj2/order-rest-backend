Feature: User can successfully get, create, delete, and update orders

  Scenario: User gets a created order
    When the user creates an order
     And the order is successfully created
     And the user gets the created order
    Then the user receives status code of 200
     And the retrieved order is correct
  
  Scenario: User gets an existing order
   Given an order exists
    When the user gets the created order
    Then the user receives status code of 200
     And the retrieved order is correct
  
  Scenario: User deletes a created order
   Given an order exists
     And the user deletes the created order
     And the user receives status code of 204
    When the user gets the created order
    Then the user receives status code of 404 
    