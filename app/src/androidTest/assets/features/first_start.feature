Feature: First start
  Load app for first time, and go to Store view

  Scenario: Load app, retrieve user information without favourite store, and go to Store view
    Given a user launching the app for first time
    When no favourite store is received
    And the store view is launched
    And app have not location permissions
    And user tap on grant location button
    And user grant location permission
    Then store list is retrieved and shown