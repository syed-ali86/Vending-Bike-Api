@CityBike
Feature: To do api testing on the biker Api

  Scenario: Validate response code
    When I hit the biker Api
    Then I should get valid response code as 200
    Then I should get valid response data

  Scenario Outline: Validate city and corresponding lat long
    Given developers pass network id "<networkId>"
    Then network location corresponded "<city>" in "<country>"
    And corresponded location "<latitude>", "<longitude>" returned
    Examples:
      | networkId      | city      | country | latitude | longitude |
      | visa-frankfurt | Frankfurt | DE      | 50.1072  | 8.66375   |
