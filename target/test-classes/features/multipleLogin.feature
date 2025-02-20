@multiplelogin
Feature: Log in to the website 
Login using different data

Background:
Given I am in login page 

@regression @smoke
  Scenario Outline: Login with different sets of data
    Given Open "<website>"
    And Enter "<username>" and "<passowrd>"
    When Click login
    And User is logged in to the website 
    Then I validate the credentails 

    Examples: 
      | username              | passowrd  | status  |
      | reka@gmail.com        | admin@1   | Fail    |
      | rekaharisri@gmail.com | admin@123 | success |