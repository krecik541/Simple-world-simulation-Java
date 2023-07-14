# Simple-world-simulation-Java

For better view open directly README

Made using Java Swing
Simple world simulation that includes a few animal and plant spicies, breeding and fighting. 
There is not specific goal or target, it's just simulation.

Player, human, has special skill, which adds +5 to his current strength. For the next 5 rounds, strength decreases until it return to earlier state. The cooldown is 10 rounds (the skill can be used 10 rounds after use, not after the skill expire).

Simulation is based on turn-based system. Every organism has its strength, initiative, postion, life duration and skill (not every organism has its own skill). Organism with higher initiative moves faster.
When two organisms are standing in the same field, organism with higher strength wins and weaker organism dies (unless it has special skill). If both organisms have the same strength, the attacker wins.
If two organisms of the same species meet, they will breed.

Pressing free field there is an option to add a new organism to the board.

Game:
First, user is asked about size of the board, then about filling the board. After that, the world is generated and shown to the user. From this moment player can make moves, fight, etc.


Controls:
  q - quit the program
  u - special skill
  s - save the board status; user is asked about the name of file, where save will be hold
  l - load the board status; user is asked about the name of save file
  arrows - move right/left/up/down


Organisms:
  Animals:
  'A' - Antelope (strength 4, initiative 4) -> moves two times every round, when fighting has 50% chance to avoid the fight
  'L' - Fox (strength 3, initiative 7) -> avoids the fight with stronger opponent
  '^' - Human (strength 5, initiative 4) -> [player]
  'O' - Sheep (strength 4, initiative 4)
  'Z' - Turtle (strength 2, initiative 1) -> has 25% chance to make move, when fighting repels the attack of opponets weaker than 5 strength
  'W' - Wolf (strength 9, initiative 5)

  Plants (initiative 0):
  'M' - Dandelion -> has 1/7 chance to spread
  'J' - Deadly nightshade -> has 1/9 chance to spread, kills every organism that eat it
  'T' - Grass -> has 1/7 chance to spread
  'G' - Guarana -> has 1/8 chance to spread, gives any organism that eat it +3 strength
  'B' - Pine Borscht -> has 1/9 chance to spread, kills every organism that eat it or stands right next to it 
