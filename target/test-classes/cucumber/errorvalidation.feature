
@tag
Feature: Error Validation
  I want to use this template for my feature file

  

  @Errorvalidation
  Scenario Outline: Title of your scenario outline
   Given user landed on ecommerce login page
   When user logedin using Username <email> and password <password>
   Then Verify "Incorrect email or password." is displayed

    Examples: 
      | email                     | password         | 
      | anandmuthuvel02@gmail.com | Asdf@1234        |
      