Feature: I want to pick some pizza in the basket

@test1 @test2
  Scenario: I want to go to the blank basket
    Given I go to the main page
    When I click on the basket logo
    Then I should see text about the blanked basket

  @test1 @test2
Scenario Outline: I want to add item in the basket
  Given I go to the main page
  When I write "<itemName>" into fast search float
  Then I chose the first item in the list
  And I save name of item
  And I click on the button To Basket
  When I click on the button In Basket
  Given I save name of the object
  Then I compare "<signOfItem>" name of Item and name of Object
  And I see button for a pay
  Examples:
    | itemName | signOfItem |
  | Пепперони |   Пицца     |

  @test1 @test2
  Scenario Outline: I want to delete item from the basket
    Given I go to the main page
    When I write "<itemName>" into fast search float
    Then I chose the first item in the list
    And I click on the button To Basket
    When I click on the button In Basket
    Then I delete object from the basket
    And I refresh site
    And I should see text about the blanked basket
    Examples:
    | itemName|
    | Пепперони |

  @test1 @test2
    Scenario Outline: I  want to return object to the basket
      Given I go to the main page
      When I write "<itemName>" into fast search float
      Then I chose the first item in the list
      And I click on the button To Basket
      When I click on the button In Basket
      Given I save name of the object
      Then I delete object from the basket
      And I click on the Return button
      And I see the same object in the basket
      Examples:
        | itemName|
        | Пепперони |

  @test1 @test2
  Scenario Outline: I  want to add object to the basket
    Given I go to the main page
    When I write "<itemName>" into fast search float
    Then I chose the first item in the list
    And I click on the button To Basket
    When I click on the button In Basket
    Then I add the second object
    Then I see an inscription of the two objects
    Examples:
      | itemName|
      | Пепперони |