Feature: ajouter un livre au panier
  Scenario: ajouter un livre au panier
    Given I am in the page of "https://www.ebay.com/"
    When I select category "267"
    And I enter "python in easy steps" in the search bar and submit
    And I select the book "Python in Easy Steps by McGrath, Mike"
    And I switch the tab
    And I add click on add to cart
    Then the URL of the page should contains "cart"
    And the price on the page should be "US $4.58"
    And the number of objects in the cart should be "1"



