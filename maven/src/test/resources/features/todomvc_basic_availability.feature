Feature: Todos Availability

  Background:
    Given Go is on todomvc

  Scenario: todos page title
    Then he should see title of the page as "todos"
    And option to add todos

  Scenario: todos add option
    Then he should see add todos option with placeholder as "What needs to be done?"

  Scenario: todos creation
    When he adds todos as Buy some milk, Walk the dog
    Then todos list should show items as Buy some milk, Walk the dog
