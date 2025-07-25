Feature:determinant
  Scenario:
    Given I have the following squared matrix
      | 1 | 3 | 4 | 5 |
      | 2 | 4 | 6 | 5 |
      | 4 | 5 | 5 | 5 |
      | 4 | 5 | 6 | 7 |
    When I calculate the determinant
    Then I should have the result 21