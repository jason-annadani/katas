Feature: Display the game board
  Scenario: Starting the game should display an empty 3x3 board
    Given a new game
    Then the displayed board should have:
      | l | m | r |
      |   |   |   |
      |   |   |   |
      |   |   |   |
      