# Battleship
Own version of the popular battleship game

## ESSENTIALS
- [x] Basic UI
- [x] Different Sea States
- [x] Basic Colors to UI
- [x] Menubar with actions
- [x] Show highscores
- [x] Turn based firing
- [x] Victory conditions
- [x] Images in UI
- [x] After victory add highscores
- [ ] Place Ships
- [x] Firing
- [x] Calculate Highscore
- [ ] Configure Ships

## NICE TO HAVE
- [x] Auto deploy fleet
- [ ] Save Game
- [ ] Load Game
- [x] Artificial Intelligence
- [ ] Moving Clouds
- [x] Game sounds
- [ ] Gun firing / explosion animation

## CHALLENGES
- How to organize the gui decently, using nested instance jpanels with the appropiate layoutmanagers ( mostly borderlayout and gridlayout for the grid DUh!)
- How to stack multiple png images on top of each other, should be resolved using a JLayerPane but then layoutmanagers are ignored so this would enforce us to manually build the gui. We solved it by creating the stacked images in photoshop and thus bypassing the issue.
- Pass coordinates and other information to a MouseListener, solved by maken them final.
- sublist returns RandomAccessSubList wich is not serializable, solved by wrapping in a new ArrayList<>
- Make jar executable, solved by adding manifest generation from maven
- resources not accessable when jar is executed, solved by using the getResource method
