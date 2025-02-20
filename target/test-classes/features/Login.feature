@login
Feature: Log in to the website 
  I want to verify login functions of my application

Background:
Given I am in login page

  @sanity @smoke
  Scenario: Login with credentails 
    Given Open "<website>"
    And Enter "<username>" and "<passowrd>"
    When Click login
    And User is logged in to the website 
    Then I validate the credentails 

  
