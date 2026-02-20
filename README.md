📌 Project Overview

This project is a 4-team football league simulation written in Java.
The application allows users to:

Enter team names

View a predefined 6-week fixture (double round-robin)

Randomly generate match scores

Display the league standings with detailed statistics

It runs entirely in the console using a menu-driven system.

🛠 Technologies Used

Java

Scanner (for user input)

Random (for score generation)

📋 Features
1️⃣ Enter Team Names

The user inputs 4 team names.
Other menu options cannot be accessed until team names are entered.

2️⃣ View Fixture

Displays a 6-week fixture:

First 3 weeks → First half of the season

Last 3 weeks → Second half of the season

Each week includes 2 matches

3️⃣ Assign Weekly Scores (Random)

Random scores between 0–4 are generated.

Scores are assigned only once per week.

Results are displayed immediately.

4️⃣ Show Standings

The standings table includes:

Column	Description
Rank	Position in league
Team	Team name
Played	Matches played
Wins	Total wins
Draws	Total draws
Losses	Total losses
Goals For	Goals scored
Goals Against	Goals conceded
Goal Difference	Goals For − Goals Against
Home Points	Points earned at home
Away Points	Points earned away
Points	Total league points
🏆 Ranking Rules

Teams are sorted by:

Total points (descending)

Goal difference (if points are equal)

📂 Project Structure
JavaOdev.java

All logic is implemented inside the main method.

Main data structures used:

String[] takimlar → Stores team names

int[][] fikstur → Predefined fixture

int[][] haftalikSkorlar → Weekly match scores

Multiple statistic arrays (wins, draws, goals, points, etc.)

▶ How to Run
Compile:
javac JavaOdev.java
Run:
java JavaOdev

🎮 Example Menu
------    MAIN MENU    ------
1. Enter Team Names
2. View Fixture
3. Assign Weekly Scores Randomly
4. Show Standings
5. Exit
⚙ How It Works

The fixture is predefined using team indexes.

Scores are stored in a 2D array initialized with -1 (meaning not played).

Statistics are calculated dynamically when showing standings.

A simple bubble sort is used to rank teams.

🚀 Possible Improvements

Manual score entry option

Persistent storage (file/database)

Object-Oriented refactoring (Team class)

More advanced tie-break rules

GUI version
