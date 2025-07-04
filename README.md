
# FootballWorldCup
Solution by: _Ana Serwinowska_
## 📋 Problem Assumptions
- Each match is uniquely identified by the combination of home and away teams (case-insensitive).
- A match cannot be started twice with the same team names.
- Score updates must be non-negative (as in regular footbal matches).
- Matches are sorted by total score (total number of goals by both teams - most to least), and for ties by playing order (recent to older matches).

## ✅ Solution Capabilities
- Start a match (with initial score 0-0)
- Update score for a match (score a goal)
- End a match (remove from scoreboard)
- Get summary of matches sorted by the rules defined above
## 🖥️ Program Execution Plan
- Initial data is generated (and presented on startup) using the steps above
- After this, the user is prompted to do one of multiple actions:
  -- Start a new match
  -- Update the score to an ongoing match
  -- End an ongoing match
  -- See the current status of the scoreboard
  -- End the program
- The actions above are open for execution until the user ends the match

## 🛠️ Technical Description
- Solution written in pure Java
- To run, install the required dependencies (Maven) and after that run the `Main` class
- IDE used for writing the solution: IntelliJ IDEA