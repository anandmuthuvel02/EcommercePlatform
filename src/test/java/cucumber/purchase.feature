
@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file

  Background:
    Given user landed on ecommerce login page

  @Regression
  Scenario Outline: Positive testing of puchase order
    Given user logedin using Username <email> and password <password>
    When user add product <productname> to cart
    And Checkout the <productname> and submit the order
    Then user select country <countryName> 
    And verify "Thankyou for the order." is displayed

    Examples: 
      | email                      | password  | productname     | countryName |
      | anandmuthuvel02@gmail.com  | Asdf@2024 | ADIDAS ORIGINAL |   Ind       |
      | sample02@gmail.com         | Qwer@1234 | IPHONE 13 PRO  |   Ind       |