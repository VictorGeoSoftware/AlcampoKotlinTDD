Feature: app launched for first time

    Scenario: app launched, user without favourite store has to select one
      Given the store view launched
      When app have not location permissions
      And user tap on grant location button
      And user grant location permission
      Then store list is retrieved and shown