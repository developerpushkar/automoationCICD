


  Feature: Purchase the order from Ecommerce website
  I want to use this template for my feature title
  
  Background:
  Given I landed on Ecommerce page
  

  Scenario Outline: Positive test of submiting the order
    Given Logged in with <username> and <password>
    When I add <productname> to cart
    And checkout <productname> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on confirmation page

    Examples: 
      | username         | password         | productname    |
      | ssol@gmail.com   |   Kishansingh1   | ZARA COAT 3    |
      | kishan@gmail.com |   Kishansingh1   | IPHONE 13 PRO  |
