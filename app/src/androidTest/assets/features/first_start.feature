Feature: First start
  Load app for first time, and go to Store view

  Scenario: Load app, retrieve user information and go to Store view
    Given A user is in MainActivity
    When No favourite store is defined
    Then I should open StoreActivity