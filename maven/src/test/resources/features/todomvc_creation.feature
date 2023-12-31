Feature: Todos Creation

  Background:
    Given Go is on todomvc

  Scenario: todos creation
    When he adds todos as Buy some milk, Walk the dog
    Then todos list should show items as Buy some milk, Walk the dog
