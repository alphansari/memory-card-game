#  Memory Card Game (Java / Swing)

A single-player **Memory Card Game** built in **Java** using **Swing** for the graphical user interface.  
Developed as part of the **CSE212: Software Development Methodologies** course at Yeditepe University.

---

##  Overview

The project implements a fully interactive card-matching game featuring three difficulty levels — **Internet & Social Media**, **Cybersecurity**, and **Gaming PC Parts**.  
Each level presents unique themes, randomized card placements, and an adaptive scoring system. The goal is to test the player’s memory while progressively increasing difficulty across levels.

---

##  Features

- **Graphical User Interface:** Fully interactive GUI designed with **Java Swing**, including menus, buttons, and level selection screens.  
- **Three Levels of Difficulty:**  
  - *Level 1:* Internet & Social Media  
  - *Level 2:* Cybersecurity  
  - *Level 3:* Gaming PC Components  
- **Dynamic Scoring System:**  
  - Correct match: +5 (L1), +4 (L2), +3 (L3)  
  - Incorrect match: −1 (L1), −2 (L2), −3 (L3)  
- **Tries Counter:** Limited number of attempts per level (18, 15, and 12 respectively).  
- **Randomized Card Placement:** Cards are shuffled on every new game or level using the `Collections` library.  
- **Menu Bar:**  
  - *Game:* Restart and High Scores  
  - *About:* Game Info and Developer Info  
  - *Exit:* Terminates the application  
- **High Score Tracking:** Stores top 10 scores with player names in a text file.  
- **Threading Bonus:** Level 3 uses **Threads** to reshuffle cards after incorrect matches for an added challenge.  
- **Instruction and About Windows:** Provides gameplay and developer details.

---

## Technical Design

- Built using **Object-Oriented Programming (OOP)** principles.  
- Utilized **Collections** for randomizing card positions.  
- High score data is persisted through file I/O operations.  
- **Multi-threading** implemented in Level 3 for dynamic card reshuffling.  
- Modular code structure for game states, event handling, and GUI rendering.

---

##  Technologies Used

- **Java 17**  
- **Swing** (for GUI design)  
- **Collections Framework**  
- **File I/O** for saving and loading scores  
- **Threads** (for Level 3 reshuffling mechanism)

---

##  Run Instructions

### Prerequisites
- Install Java Development Kit (JDK 17 or later)

### Compile and Run
```bash
javac -d bin src/*.java
java -cp bin Main
