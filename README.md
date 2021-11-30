[![Actions Status](https://github.com/rastorius/xp-farm-test/actions/workflows/build.yml/badge.svg)](https://github.com/rastorius/xp-farm-test/actions?query=workflow%3build)

[![Sonarcloud Status](https://sonarcloud.io/api/project_badges/measure?project=rastorius_xp-farm-test&metric=alert_status)](https://sonarcloud.io/dashboard?id=rastorius_xp-farm-test)

[![SonarCloud Coverage](https://sonarcloud.io/api/project_badges/measure?project=rastorius_xp-farm-test&metric=coverage)](https://sonarcloud.io/component_measures/metric/coverage/list?id=rastorius_xp-farm-test)

[![SonarCloud Bugs](https://sonarcloud.io/api/project_badges/measure?project=rastorius_xp-farm-test&metric=bugs)](https://sonarcloud.io/component_measures/metric/reliability_rating/list?id=rastorius_xp-farm-test)

[![SonarCloud Code Smells](https://sonarcloud.io/api/project_badges/measure?project=rastorius_xp-farm-test&metric=code_smells)](https://sonarcloud.io/component_measures/metric/code_smells/list?id=rastorius_xp-farm-test)

# Kata and requirements

- Board size: 3 x 3
- Number of mines: 1 to 3 bombs

# User stories

### 📘 #1 Game board creation

As a gamer  
I want to have a new board  
So that I can play mine sweeper

#### 📗 Acceptance test

**GIVEN** the game is not started  
**WHEN** starting the game  
**THEN** should print new board and starting message

#### 📜 #1 Scenario

**GIVEN** new game  
**WHEN** starting the game  
**THEN** game state should be IN_PROGRESS

#### 📜 #2 Scenario

**GIVEN** new game  
**WHEN** starting the game  
**THEN** should print new board and starting message

### 📘 #2 Implement steps

As a gamer  
I want to take a step  
So that I can progress the game

#### 📗 Acceptance test

**GIVEN** no steps were taken and there is a bomb on (1,1)  
**WHEN** stepping on (1,1)   
**THEN** should print board with (1,1) revealed (X) and game over message

#### 📜 #1 Scenario

**GIVEN** a board no steps taken  
**WHEN** stepping  
**THEN** should print board with the square revealed

#### 📜 #2 Scenario

**GIVEN** a board with 1 bomb  
**WHEN** stepping on the bomb  
**THEN** should print game over message and game state should be GAME_OVER

#### 📗 Acceptance test

**GIVEN** no steps were taken and there are bombs on (1,0), (0,1) and (1,1)  
**WHEN** stepping on (0,0)   
**THEN** should print board with (0,0) revealed (3) and mines around you message

#### 📜 #3 Scenario

**GIVEN** a board with 1 bomb  
**WHEN** stepping next to the bomb  
**THEN** should print mind around you message and game state should be IN_PROGRESS

#### 📗 Acceptance test

**GIVEN** (1,0), (0,1) and (1,1) are bombs, (0,0) is 3  
**WHEN** stepping on (0,1), (1,0), (0,2), (2,0) and (2,2)   
**THEN** should print board with  
(0,1), (1,0), (0,2), (2,0) revealed (2)  
(2,2) revealed (1)  
victory message

#### 📜 #4 Scenario

**GIVEN** a board with 1 cleanable square left  
**WHEN** stepping on the cleanable square  
**THEN** should print victory message and game state should be VICTORY

#### 📗 Acceptance test

**GIVEN** no steps were taken and there is a bomb on (2,2)  
**WHEN** stepping on (0,0)   
**THEN** should print board with  
(0,1), (1,0), (0,2), (2,0) revealed (_)  
(0,0) revealed (X)  
(1,1), (2,1), (1,2) revealed (1)  
victory message

#### 📜 #5 Scenario

**GIVEN** a board with massive cleaning possible with victory  
**WHEN** stepping on one of winning squares  
**THEN** should clear every square around it recursively and game state should be VICTORY

### 📘 #3 Implement marks

As a gamer  
I want to mark potential mines  
So that I can progress the game

#### 📗 Acceptance test

**GIVEN** (0,0) is revealed (3) and there are bombs on (1,0), (0,1) and (1,1)  
**WHEN** marking (1,0), (0,1) and (1,1)   
**THEN** should print board with (1,0), (0,1) and (1,1) as marked (+) and square flagged as mine message

#### 📜 #1 Scenario

**GIVEN** a board no steps taken  
**WHEN** marking a square  
**THEN** should print board with the square revealed (+) and square flagged as mine message

### 📘 #4 Setup bot game

As a gamer  
I want to start the game in bot mode   
So that I can check the game's progression end conclusion
