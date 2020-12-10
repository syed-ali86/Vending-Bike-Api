Feature: To do api testing on the biker Api



  Scenario: Validate response code
    When I hit the biker Api
    Then I should get valid response code as 200
