Feature: Opencart
  I want to use this template for my Opencart application

 
  Scenario Outline: Opencart Application
    Given User should open link
    Then User Register account should fail "<SheetName>" and "<RowNumber>"
    And Click on submit
    
 
Examples:
   |SheetName|RowNumber|
   |Data|0|
   
    Scenario Outline: Login to application and edit lastname
    Given User should login to application
    Then User enter credentials "<SheetName>" and "<RowNumber>"
    And Click on login
    Then User able edit the Existing login account
    
 Examples:
   |SheetName|RowNumber|
   |Login|0|
   
   
    Scenario Outline: Login to application and entry coupon code
    Given User should login to application
    Then User enter credentials "<SheetName>" and "<RowNumber>"
    And Click on login
    Then User apply empty coupon code
    
    Examples:
   |SheetName|RowNumber|
   |Login|0|
    
 
   
