# Battleship
Own version of the popular battleship game

## ESSENTIALS
- [x] Basic UI
- [x] Different Sea States
- [x] Basic Colors to UI
- [ ] Menubar with actions
- [ ] Show highscores
- [x] Turn based firing
- [x] Victory conditions
- [x] Images in UI
- [ ] After victory add highscores
- [ ] Place Ships
- [x] Firing
- [ ] Calculate Highscore
- [ ] Configure Ships

## NICE TO HAVE
- [ ] Auto deploy fleet
- [ ] Save Game
- [ ] Load Game
- [ ] Artificial Intelligence
- [ ] Moving Clouds
- [ ] Game sounds
- [ ] Gun firing / explosion animation

## CHALLENGES
- How to organize the gui decently, using nested instance jpanels with the appropiate layoutmanagers ( mostly borderlayout and gridlayout for the grid DUh!)
- How to stack multiple png images on top of each other, should be resolved using a JLayerPane but then layoutmanagers are ignored so this would enforce us to manually build the gui. We solved it by creating the stacked images in photoshop and thus bypassing the issue.
- Pass coordinates and other information to a MouseListener, solved by maken them final.
 