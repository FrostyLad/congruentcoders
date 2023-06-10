//Georgia Moberly - ID: 22009670
//Adam Pejcic - ID: 22011604
//Braden Johnston - ID: 20005898

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main extends GameEngine {
    public static void main(String[] args) {
        createGame(new Main(), 150);
    }
    //*******************************************************
    // ************************Game**************************
    //*******************************************************
    enum GameState {Menu, Levels, Play, Exit,GameOver,LevelComplete, Instructions, Options}
    GameState state = GameState.Menu;
    enum Difficulty {Easy, Normal, Hard};
    Difficulty difficulty = Difficulty.Normal;
    int menuOption,exitOption,levelMenu,levelExit,gameOver, gameLevel, width, height, levelCompOption, score,difficultyMenu, livesMenu, livesDifficulty, gameLives, lives;
    double gameSpeed;
    Scanner platformRead, enemyRead,  coinRead;
    public void init() {
        levelCompOption = 0;
        menuOption = 0;
        exitOption = 0;
        levelMenu = 1;
        levelExit = 0;
        gameOver = 0;
        gameLevel = 1;
        width = 500;
        height = 500;
        livesDifficulty = 0;
        difficultyMenu = 1; //0 = easy, 1 == normal, 2 == hard
        livesMenu = 1;//0 = 0 lives, 1 == 3 lives, 2 == 5 lives
        gameLives = 2;
        if (difficulty == Difficulty.Easy){
            gameSpeed = 150;
        }else if(difficulty == Difficulty.Normal){
            gameSpeed = 200;
        }else if(difficulty == Difficulty.Hard){
            gameSpeed = 250;
        }
    }

    public void update(double dt) {
        if (state == GameState.Play) {
            updateGame(dt);
        }
    }
    public void paintComponent() {
        changeBackgroundColor(208,245,190);
        clearBackground(width, height);
        if(state == GameState.Menu) {
            drawMenu();
        }else if(state == GameState.Levels) {
            drawLevels();
        }else if(state == GameState.Play) {
            // backgrounds go here
            drawGame();
        }else if(state == GameState.Exit){
            drawExit();
        }else if(state == GameState.GameOver){
            drawGameOver();
        }else if(state == GameState.LevelComplete){
            drawLevelComplete();
        }else if(state == GameState.Instructions){
            drawInstructions();
        }else if(state == GameState.Options){
            drawOptions();
        }
    }
    public void drawGame(){
        if(gameLevel == 1) {
            drawSpikes();
            drawEnemies();
            drawBall();
            drawFlag();
            drawCoins();
            drawPlatforms();
            changeColor(10,77,104);
            drawText(10,30, "Score: " + score,30);
            if(gameLives>=0){
                drawText(10,60, "Lives: " + (lives + 1),30);
            }
        }else{
            changeColor(Color.BLUE);
            drawText(100,150, "Next level");
            drawText(100,200, "Still to Come!");
            drawText(100,250,"Press Enter to go to main menu",20);
        }
    }
    public void drawMenu(){
        changeColor(10,77,104);
        drawText(width/2.0-90,75, "Menu",75);
        if(menuOption == 0) {
            changeColor(5,191,219);
            drawText(width/2.0 -50, 150, "Play");
            changeColor(8, 131, 149);
            drawSolidCircle(100,135, 20);
            drawSolidCircle(400,135, 20);
        } else {
            changeColor(Color.darkGray);
            drawText(width/2.0-50, 150, "Play");
        }
        if(menuOption == 1) {
            changeColor(5,191,219);
            drawText(width/2.0-65, 200, "Levels");
            changeColor(8, 131, 149);
            drawSolidCircle(100,185, 20);
            drawSolidCircle(400,185, 20);
        } else {
            changeColor(Color.darkGray);
            drawText(width/2.0-65, 200, "Levels");
        }
        if(menuOption == 2) {
            changeColor(5,191,219);
            drawText(width/2.0-100, 250, "Instructions");
            changeColor(8, 131, 149);
            drawSolidCircle(100,235, 20);
            drawSolidCircle(400,235, 20);
        } else {
            changeColor(Color.darkGray);
            drawText(width/2.0-100, 250, "Instructions");
        }
        if(menuOption == 3) {
            changeColor(5,191,219);
            drawText(width/2.0-75, 300, "Options");
            changeColor(8, 131, 149);
            drawSolidCircle(100,285, 20);
            drawSolidCircle(400,285, 20);
        } else {
            changeColor(Color.darkGray);
            drawText(width/2.0-75, 300, "Options");
        }
        if(menuOption == 4) {
            changeColor(5,191,219);
            drawText(width/2.0-48, 350, "Exit");
            changeColor(8, 131, 149);
            drawSolidCircle(100,335, 20);
            drawSolidCircle(400,335, 20);
        } else {
            changeColor(Color.darkGray);
            drawText(width/2.0-48, 350, "Exit");
        }
    }
    public void drawLevels(){
        changeColor(10,77,104);
        drawText(width/2.0-95,75, "Levels",75);
        drawText(160, 450,"press Escape to exit",20);
        if (levelMenu == 1 ){
            changeColor(5,191,219);
            drawText(200,300,"1",200);
            changeColor(Color.darkGray);
            drawText(300,300,"2",100);
            drawText(360,300,"3",75);
            drawText(410,300,"4",50);
            drawText(450,300,"5",30);
        }else if(levelMenu == 2){
            changeColor(5,191,219);
            drawText(200,300,"2",200);
            changeColor(Color.darkGray);
            drawText(300,300,"3",100);
            drawText(360,300,"4",75);
            drawText(410,300,"5",50);
            drawText(160,300,"1",100);
        } else if(levelMenu == 3){
            changeColor(5,191,219);
            drawText(200,300,"3",200);
            changeColor(Color.darkGray);
            drawText(300,300,"4",100);
            drawText(360,300,"5",75);
            drawText(160,300,"2",100);
            drawText(120,300,"1",75);
        }else if(levelMenu == 4){
            changeColor(5,191,219);
            drawText(200,300,"4",200);
            changeColor(Color.darkGray);
            drawText(300,300,"5",100);
            drawText(150,300,"3",100);
            drawText(110,300,"2",75);
            drawText(90,300,"1",50);
        }else if(levelMenu == 5){
            changeColor(5,191,219);
            drawText(200,300,"5",200);
            changeColor(Color.darkGray);
            drawText(150,300,"4",100);
            drawText(110,300,"3",75);
            drawText(90,300,"2",50);
            drawText(70,300,"1",30);
        }
        changeColor(Color.darkGray);
        if (levelMenu == 1 ){
            drawText(300,300,"2",100);
            drawText(360,300,"3",75);
            drawText(410,300,"4",50);
            drawText(450,300,"5",30);
        }else if(levelMenu == 2){
            drawText(300,300,"3",100);
            drawText(360,300,"4",75);
            drawText(410,300,"5",50);
            drawText(160,300,"1",100);
        } else if(levelMenu == 3){
            drawText(300,300,"4",100);
            drawText(360,300,"5",75);
            drawText(160,300,"2",100);
            drawText(120,300,"1",75);
        }else if(levelMenu == 4){
            drawText(300,300,"5",100);
            drawText(150,300,"3",100);
            drawText(110,300,"2",75);
            drawText(90,300,"1",50);
        }else if(levelMenu == 5){
            drawText(150,300,"4",100);
            drawText(110,300,"3",75);
            drawText(90,300,"2",50);
            drawText(70,300,"1",30);
        }

    }
    public void drawExit(){
        changeColor(10,77,104);
        drawText(90,150, "Do you want to exit?");
        if(exitOption == 0){
            drawText(100, 300, "No");
            changeColor(8, 131, 149);
            drawSolidCircle(80,285,15);
        } else {
            changeColor(Color.darkGray);
            drawText(100, 300, "No");
            changeColor(Color.blue);
        }
        if (exitOption == 1) {
            changeColor(5,191,219);
            drawText(350, 300, "Yes");
            changeColor(8, 131, 149);
            drawSolidCircle(330,285,15);
        }else{
            changeColor(Color.darkGray);
            drawText(350, 300, "Yes");
        }
    }
    public void drawGameOver(){
        changeColor(10,77,104);
        drawText(50,100, "Game Over",75);
        changeColor(5,191,219);
        drawText(100,180,"Score: " + score,30);
        drawText(100,230,"You can change the game difficulty",15);
        drawText(100,250," and lives amount in options",15);
        if(gameOver == 0){
            changeColor(5,191,219);
            drawText(100, 295, "Restart Level",20);
            changeColor(8, 131, 149);
            drawSolidCircle(80,285,15);
        } else {
            changeColor(Color.darkGray);
            drawText(100, 295, "Restart Level",20);
            changeColor(Color.blue);
        }
        if (gameOver == 1) {
            changeColor(5,191,219);
            drawText(300, 295, "Main Menu",20);
            changeColor(8, 131, 149);
            drawSolidCircle(280,285,15);
        }else{
            changeColor(Color.darkGray);
            drawText(300, 295, "Main Menu",20);
        }
    }
    public void drawLevelComplete(){
        changeColor(10,77,104);
        drawText(50,150, "You Completed Level "+gameLevel+"!");
        drawText(50,200,"Score: " + score);
        if(levelCompOption == 0){
            changeColor(5,191,219);
            drawText(150, 300, "Next Level");
            changeColor(8, 131, 149);
            drawSolidCircle(80,285,15);
        } else {
            changeColor(Color.darkGray);
            drawText(150, 300, "Next Level");
        }
        if (levelCompOption == 1) {
            changeColor(5,191,219);
            drawText(150, 400, "Main Menu");
            changeColor(8, 131, 149);
            drawSolidCircle(80,385,15);
        }else{
            changeColor(Color.darkGray);
            drawText(150, 400, "Main Menu");
        }
    }
    public void drawInstructions(){
        changeColor(10,77,104);
        drawText(125,100, "Instructions",50);
        drawText(175, 450,"press Enter to exit",20);
        changeColor(5,191,219);
        drawText(50,150,"Stay on the platform and reach the finish flag",20);
        drawText(50,175,"Use the up key to jump",20);
        drawText(50,200,"Use the down key to become heavy",20);
        drawText(50,225,"Use the left and right key to move left and right",20);
        drawText(50,250,"Jump over the red spikes",20);
        drawText(50,275,"Roll under or jump over the red balls",20);
        drawText(50,300,"Use the jump pads to jump higher",20);
        drawText(50,325,"Collect coins to increase your score",20);
    }
    public void drawOptions(){
        changeColor(10,77,104);
        drawText(135,100, "Options",75);
        drawText(175, 450,"press Enter to exit",20);
        changeColor(121,224,238);
        drawText(50, 200, "Difficulty:",30);
        drawText(50, 250, "Lives:",30);
        if(difficulty == Difficulty.Easy){
            changeColor(5,191,219);
            drawText(185, 200, "Easy",30);
        }else{
            changeColor(Color.darkGray);
            drawText(185, 200, "Easy",30);
        }
        if(difficulty == Difficulty.Normal){
            changeColor(5,191,219);
            drawText(275, 200, "Normal",30);
        }else{
            changeColor(Color.darkGray);
            drawText(275, 200, "Normal",30);
        }if(difficulty == Difficulty.Hard){
            changeColor(5,191,219);
            drawText(400, 200, "Hard",30);
        }else{
            changeColor(Color.darkGray);
            drawText(400, 200, "Hard",30);
        }
        if(livesMenu == 0){
            changeColor(5,191,219);
            drawText(190, 250, "Zero",30);
        }else{
            changeColor(Color.darkGray);
            drawText(190, 250, "Zero",30);
        }
        if(livesMenu == 1){
            changeColor(5,191,219);
            drawText(280, 250, "Three",30);
        }else{
            changeColor(Color.darkGray);
            drawText(280, 250, "Three",30);
        }if(livesMenu == 2){
            changeColor(5,191,219);
            drawText(405, 250, "Five",30);
        }else{
            changeColor(Color.darkGray);
            drawText(405, 250, "Five",30);
        }
        if(livesDifficulty == 0){
            changeColor(8, 131, 149);
            drawSolidCircle(25,190, 15);
        }else{
            changeColor(8, 131, 149);
            drawSolidCircle(25,240, 15);
        }
    }
    public void initGame(){
        score = 0;
        lives = gameLives;
        initBall();
        initSpkies();
        initPlatforms();
        initEnemies();
        initFlag();
        initCoins();
    }
    public void updateGame(double dt){
        updatePlatforms(dt);
        updateSpikes(dt);
        updateEnemies(dt);
        updateBall(dt);
        updateFlag(dt);
        updateCoins(dt);
    }
    //*******************************************************
    //***********************Key Press***********************
    //*******************************************************

    public void keyPressed(KeyEvent e){
        if (state == GameState.Play) {
            if(gameLevel == 1) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    if (jumpReady) {
                        ballPositionY--;
                        Jump = true;
                        jumpReady = false;
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    ballAngle -= 200 * 0.015;
                    if (ballVelocityX > 50) {
                        ballVelocityX -= (ballVelocityX - 50) / 2;
                    }
                    ballVelocityX -= 15;
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    ballAngle += 200 * 0.015;
                    if (ballVelocityX < -50) {
                        ballVelocityX -= (ballVelocityX + 50) / 2;
                    }
                    ballVelocityX += 15;
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    if (checkBallOnPlatform()[1] == 0) {
                        ballColour = Color.gray;
                        heavy = true;
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    state = GameState.Menu;
                }
            }else {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    state = GameState.Menu;
                }
            }
            } else if (state == GameState.Menu){
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                if(menuOption == 1){
                    menuOption--;
                }else if(menuOption == 2){
                    menuOption--;
                } else if(menuOption == 3){
                    menuOption--;
                } else if(menuOption == 4){
                    menuOption--;
                }else if(menuOption == 0){
                    menuOption +=4;
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                if(menuOption == 0){
                    menuOption++;
                }else if(menuOption == 1){
                    menuOption++;
                }else if(menuOption == 2){
                    menuOption++;
                }else if(menuOption == 3){
                    menuOption++;
                }else if (menuOption == 4){
                    menuOption-=4;
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                if(menuOption == 0){
                    initGame();
                    state = GameState.Play;
                }else if(menuOption == 1){
                    state = GameState.Levels;
                }else if(menuOption == 2){
                    state = GameState.Instructions;
                }else if(menuOption == 3){
                    if(gameLives ==-1) {
                        livesMenu = 0;
                    }else if (gameLives == 2){
                        livesMenu = 1;
                    }else{
                        livesMenu = 2;
                    }
                    state = GameState.Options;
                }else{
                    state = GameState.Exit;
                }
            }
        }else if(state == GameState.Levels) {
            if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
                state = GameState.Menu;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT){
                if (levelMenu < 5){
                    levelMenu++;
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT){
                if (levelMenu > 1){
                    levelMenu--;
                }
            }
            if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                initGame();
                if (levelMenu == 1){
                    gameLevel = 1;
                    state = GameState.Play;
                }else if (levelMenu == 2){
                    gameLevel = 2;
                    state = GameState.Play;
                }else if (levelMenu == 3){
                    gameLevel = 3;
                    state = GameState.Play;
                }else if (levelMenu == 4){
                    gameLevel = 4;
                    state = GameState.Play;
                }else if (levelMenu == 5){
                    gameLevel = 5;
                    state = GameState.Play;
                }
            }
        }else if(state == GameState.Exit){
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                if(exitOption == 0){
                    state = GameState.Menu;
                }else if(exitOption == 1){System.exit(1);}
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                if(exitOption == 0){
                    exitOption++;
                }else if(exitOption == 1){
                    exitOption--;
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                if(exitOption == 0){
                    exitOption++;
                }else if(exitOption == 1){
                    exitOption--;
                }
            }
        }else if (state == GameState.GameOver){
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                if(gameOver == 0){
                    initGame();
                    state = GameState.Play;
                }else if(gameOver == 1){
                    state = GameState.Menu;
                    gameOver=0;
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                if(gameOver == 0){
                    gameOver++;
                }else if(gameOver == 1){
                    gameOver--;
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                if(gameOver == 0){
                    gameOver++;
                }else if(gameOver == 1){
                    gameOver--;
                }
            }
        }else if (state == GameState.LevelComplete){
            if(e.getKeyCode() == KeyEvent.VK_UP){
                if(levelCompOption == 0){
                    levelCompOption++;
                }else if(levelCompOption == 1){
                    levelCompOption--;
                }
            }
            if(e.getKeyCode() == KeyEvent.VK_DOWN){
                if(levelCompOption == 0){
                    levelCompOption++;
                }else if(levelCompOption == 1){
                    levelCompOption--;
                }
            }
            if(e.getKeyCode() == KeyEvent.VK_ENTER){
                if(levelCompOption == 0){
                    gameLevel++;
                    initGame();
                    state = GameState.Play;
                }else if(levelCompOption == 1){
                    gameLevel++;
                    state = GameState.Menu;
                }
            }
        }else if(state == GameState.Instructions && e.getKeyCode() == KeyEvent.VK_ENTER){
            state = GameState.Menu;
        }else if(state == GameState.Options) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                lives = gameLives;
                state = GameState.Menu;
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                if (livesDifficulty == 0) {
                    if (difficultyMenu == 0) {
                        difficultyMenu += 2;
                        difficulty = Difficulty.Hard;
                    } else if (difficultyMenu == 1) {
                        difficultyMenu--;
                        difficulty = Difficulty.Easy;
                    } else if (difficultyMenu == 2) {
                        difficultyMenu--;
                        difficulty = Difficulty.Normal;
                    }
                } else if (livesDifficulty == 1) {
                    if (livesMenu == 0) {
                        livesMenu += 2;
                        gameLives = 4;
                    } else if (livesMenu == 1) {
                        livesMenu--;
                        gameLives = -1;
                    } else if (livesMenu == 2) {
                        livesMenu--;
                        gameLives = 2;
                    }
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                if (livesDifficulty == 0) {
                    if (difficultyMenu == 0) {
                        difficultyMenu++;
                        difficulty = Difficulty.Normal;
                    } else if (difficultyMenu == 1) {
                        difficultyMenu++;
                        difficulty = Difficulty.Hard;
                    } else if (difficultyMenu == 2) {
                        difficultyMenu -= 2;
                        difficulty = Difficulty.Easy;
                    }
                } else if (livesDifficulty == 1) {
                    if (livesMenu == 0) {
                        livesMenu++;
                        gameLives = 2;
                    } else if (livesMenu == 1) {
                        livesMenu++;
                        gameLives = 4;
                    } else if (livesMenu == 2) {
                        livesMenu -= 2;
                        gameLives = -1;
                    }
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                if (livesDifficulty == 1) {
                    livesDifficulty--;
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                if (livesDifficulty == 0) {
                    livesDifficulty++;
                }
            }
        }
    }
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            Jump = false;
            jumpCount = 0;
        }
    }

    //*******************************************************
    //***********************Platforms***********************
    //*******************************************************
    ArrayList<Double> platformXSmallPositionX, platformXSmallPositionY, platformSmallPositionX, platformSmallPositionY,
            platformMedPositionX, platformMedPositionY, platformLargePositionX, platformLargePositionY,
            platformVertPositionX, platformVertPositionY, jumpPadPositionX, jumpPadPositionY;
    ArrayList<ArrayList<Double>> platforms;

    String[] currentLine;
    Image jumpPad;
    public void initPlatforms(){
        try {
            platformRead = new Scanner(new File("platformPositions.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        jumpPad = loadImage("jumppad.png");
        jumpPadPositionX = new ArrayList<>();
        jumpPadPositionY = new ArrayList<>();
        platformXSmallPositionX = new ArrayList<>();
        platformXSmallPositionY = new ArrayList<>();
        platformSmallPositionX = new ArrayList<>();
        platformSmallPositionY = new ArrayList<>();
        platformMedPositionX = new ArrayList<>();
        platformMedPositionY = new ArrayList<>();
        platformLargePositionX = new ArrayList<>();
        platformLargePositionY = new ArrayList<>();
        platformVertPositionX = new ArrayList<>();
        platformVertPositionY = new ArrayList<>();
        if(gameLevel == 1) {
            while (platformRead.hasNext()) {
                currentLine = platformRead.nextLine().split(",");
                switch (currentLine[0]) {
                    case "0" -> {
                        platformXSmallPositionX.add(Double.parseDouble(currentLine[1]));
                        platformXSmallPositionY.add(Double.parseDouble(currentLine[2]));
                    }
                    case "1" -> {
                        platformSmallPositionX.add(Double.parseDouble(currentLine[1]));
                        platformSmallPositionY.add(Double.parseDouble(currentLine[2]));
                    }
                    case "2" -> {
                        platformMedPositionX.add(Double.parseDouble(currentLine[1]));
                        platformMedPositionY.add(Double.parseDouble(currentLine[2]));
                    }
                    case "3" -> {
                        platformLargePositionX.add(Double.parseDouble(currentLine[1]));
                        platformLargePositionY.add(Double.parseDouble(currentLine[2]));
                    }
                    case "4" -> {
                        platformVertPositionX.add(Double.parseDouble(currentLine[1]));
                        platformVertPositionY.add(Double.parseDouble(currentLine[2]));
                    }
                    case "5" -> {
                        jumpPadPositionX.add(Double.parseDouble(currentLine[1]));
                        jumpPadPositionY.add(Double.parseDouble(currentLine[2]));
                    }
                }
            }
        }else if(gameLevel == 2) {
        }
    }
    public void updatePlatforms(double dt) {
        platforms = new ArrayList<>();
        changeColor(white);
        doUpdate(dt, platformVertPositionX, platformVertPositionY, 4, 25,100);
        doUpdate(dt, jumpPadPositionX, jumpPadPositionY, 5, 30,30);
        doUpdate(dt, platformXSmallPositionX, platformXSmallPositionY, 0, 100,25);
        doUpdate(dt, platformSmallPositionX, platformSmallPositionY, 1, 200,25);
        doUpdate(dt, platformMedPositionX, platformMedPositionY, 2, 400,25);
        doUpdate(dt, platformLargePositionX, platformLargePositionY, 3, 800,25);
    }

    private void doUpdate(double dt, ArrayList<Double> platformListX, ArrayList<Double> platformListY, double id, double length, double height) {
        for (int i = 0; i < platformListX.size(); i++) {
            platformListX.set(i, platformListX.get(i)-gameSpeed*dt);
            if (platformListX.get(i) < width) {
                ArrayList<Double> temp = new ArrayList<>();
                temp.add(id);
                temp.add(platformListX.get(i));
                temp.add(platformListY.get(i));
                temp.add(length);
                temp.add(height);
                platforms.add(temp);
            }
        }
    }

    public void drawPlatforms() {
        for(int i=0; i<platformXSmallPositionX.size(); i++) {
            changeColor(white);
            saveCurrentTransform();
            translate(0, 0);
            drawSolidRectangle(platformXSmallPositionX.get(i), platformXSmallPositionY.get(i), 100, 25);
            changeColor(black);
            drawRectangle(platformXSmallPositionX.get(i), platformXSmallPositionY.get(i), 100, 25);
            restoreLastTransform();
        }
        for(int i=0; i<platformSmallPositionX.size(); i++) {
            changeColor(white);
            saveCurrentTransform();
            translate(0, 0);
            drawSolidRectangle(platformSmallPositionX.get(i), platformSmallPositionY.get(i), 200, 25);
            changeColor(black);
            drawRectangle(platformSmallPositionX.get(i), platformSmallPositionY.get(i), 200, 25);
            restoreLastTransform();
        }
        for(int i=0; i<platformMedPositionX.size(); i++) {
            changeColor(white);
            saveCurrentTransform();
            translate(0, 0);
            drawSolidRectangle(platformMedPositionX.get(i), platformMedPositionY.get(i), 400, 25);
            changeColor(black);
            drawRectangle(platformMedPositionX.get(i), platformMedPositionY.get(i), 400, 25);
            restoreLastTransform();
        }
        for (int i = 0; i < platformLargePositionX.size(); i++) {
            changeColor(white);
            saveCurrentTransform();
            translate(0, 0);
            drawSolidRectangle(platformLargePositionX.get(i), platformLargePositionY.get(i), 800, 25);
            changeColor(black);
            drawRectangle(platformLargePositionX.get(i), platformLargePositionY.get(i), 800, 25);
            restoreLastTransform();
        }
        for (int i = 0; i < platformVertPositionX.size(); i++) {
            changeColor(white);
            saveCurrentTransform();
            translate(0, 0);
            drawSolidRectangle(platformVertPositionX.get(i), platformVertPositionY.get(i), 25, 100);
            changeColor(black);
            drawRectangle(platformVertPositionX.get(i), platformVertPositionY.get(i), 25, 100);
            restoreLastTransform();
        }
        for (int i = 0; i < jumpPadPositionX.size(); i++) {
            saveCurrentTransform();
            translate(0, 0);
            drawImage(jumpPad, jumpPadPositionX.get(i),jumpPadPositionY.get(i),50,20);
            restoreLastTransform();
        }
    }

    //*******************************************************
    //***********************Enemies*************************
    //*******************************************************
    ArrayList<Double> spikeEnemyPositionX;
    ArrayList<Double> spikeEnemyPositionY;
    ArrayList<Double> bounceEnemyPositionX;
    ArrayList<Double> bounceEnemyPositionY;
    Image spikeEnemy, bounceEnemy;
    boolean[] bounceEnemyActive;
    ArrayList<ArrayList<Double>> enemies;
    double bounceEnemyVelocityY,bounceHeight;
    public void initEnemies(){
        bounceHeight = 100;
        spikeEnemy = loadImage("spikeenemy.png");
        bounceEnemy = loadImage("bouncingenemy.png");
        spikeEnemyPositionX = new ArrayList<>();
        spikeEnemyPositionY = new ArrayList<>();
        bounceEnemyPositionX = new ArrayList<>();
        bounceEnemyPositionY = new ArrayList<>();
        bounceEnemyActive = new boolean[10];

        if(gameLevel == 1) {
            try {
                enemyRead = new Scanner(new File("enemyPositions.txt"));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            while (enemyRead.hasNext()) {
                currentLine = enemyRead.nextLine().split(",");
                switch (currentLine[0]) {
                    case "0" -> {
                        spikeEnemyPositionX.add(Double.parseDouble(currentLine[1]));
                        spikeEnemyPositionY.add(Double.parseDouble(currentLine[2]));
                    }
                    case "1" -> {
                        bounceEnemyPositionX.add(Double.parseDouble(currentLine[1]));
                        bounceEnemyPositionY.add(Double.parseDouble(currentLine[2]));
                    }
                }
            }
            for (int i = 0; i < bounceEnemyPositionX.size(); i++) {
                bounceEnemyActive[i] = true;
            }
        }else if(gameLevel==2){}
    }
    public void drawEnemies() {
        if(gameLevel==1) {
            for (int i = 0; i < spikeEnemyPositionX.size(); i++) {
                translate(spikeEnemyPositionX.get(i), spikeEnemyPositionY.get(i));
                drawImage(spikeEnemy,-10,-40,40,60);
                restoreLastTransform();
            }
            for (int i = 0; i < bounceEnemyPositionX.size(); i++) {
                if(bounceEnemyActive[i]) {
                    translate(bounceEnemyPositionX.get(i)-ballRadius, bounceEnemyPositionY.get(i)-ballRadius);
                    drawImage(bounceEnemy,-20,-20,ballRadius*2,ballRadius*2);
                    restoreLastTransform();
                }
            }
        }
    }
    ArrayList<Double> temp;
    double[] apex, startY;
    public void updateEnemies(double dt) {
        enemies = new ArrayList<>();
        apex = new double[10];
        startY = new double[10];
        if (gameLevel == 1) {
            for (int i = 0; i < spikeEnemyPositionY.size(); i++) {
                spikeEnemyPositionX.set(i, spikeEnemyPositionX.get(i) - gameSpeed * dt);
                if (spikeEnemyPositionX.get(i) < width){
                    if (spikeEnemyPositionY.get(i) < width) {
                        temp = new ArrayList<>();
                        temp.add(0.0);
                        temp.add(spikeEnemyPositionX.get(i));
                        temp.add(spikeEnemyPositionY.get(i));
                        temp.add(40.0);
                        temp.add(60.0);
                        enemies.add(temp);
                    }
                }
            }
            for (int i = 0; i < bounceEnemyPositionY.size(); i++) {
                bounceEnemyPositionX.set(i, bounceEnemyPositionX.get(i) - gameSpeed * dt);
                for (ArrayList<Double> platform : platforms) {
                    if(platform.get(1)<=bounceEnemyPositionX.get(i)+ballRadius
                            && platform.get(1)+platform.get(3)>=bounceEnemyPositionX.get(i)-ballRadius){
                        apex[i] = platform.get(2)-bounceHeight+ballRadius;
                        startY[i] = platform.get(2);
                        if(bounceEnemyPositionY.get(i)>= startY[i]){
                            bounceEnemyVelocityY=150;
                        }
                        if(bounceEnemyPositionY.get(i)<=apex[i]){
                            bounceEnemyVelocityY =-150;
                        }
                    }
                }
                bounceEnemyPositionY.set(i, bounceEnemyPositionY.get(i) - bounceEnemyVelocityY * dt);
                if (bounceEnemyPositionX.get(i) < width&&bounceEnemyPositionX.get(i)>0) {
                    temp = new ArrayList<>();
                    temp.add(1.0);
                    temp.add(bounceEnemyPositionX.get(i));
                    temp.add(bounceEnemyPositionY.get(i));
                    temp.add(40.0);
                    temp.add(40.0);
                    enemies.add(temp);
                }
            }
        }
    }
    //*******************************************************
    //*************************Ball**************************
    //*******************************************************

    boolean Jump, jumpReady, heavy;
    int jumpCount, ballRadius, canJumpPad;
    double ballPositionX, ballPositionY, ballVelocityX, ballVelocityY, ballAngle;
    Color ballColour;
    Image ballImage,ballHeavyImage,flamesImage;

    public void initBall(){
        ballImage = loadImage("ball.png");
        ballHeavyImage= loadImage("BallHeavy.png");
        flamesImage = loadImage("Flames.png");
        Jump = false;
        jumpReady = true;
        heavy = false;
        jumpCount = 0;
        ballRadius = 20;
        ballPositionX = width/2.0;
        ballPositionY = 400;
        ballVelocityX = 10;
        ballVelocityY = 0;
        ballColour = white;
        canJumpPad = 0;
        ballAngle = 0;

    }
    public void drawBall() {
        saveCurrentTransform();
        translate(ballPositionX,ballPositionY);
        if (heavy) {
            drawImage(flamesImage, -ballRadius-10, -ballRadius-48, 55, 90);
            rotate(ballAngle);
            drawImage(ballHeavyImage, -ballRadius, -ballRadius, ballRadius * 2, ballRadius * 2);
        }else{
            rotate(ballAngle);
            drawImage(ballImage, -ballRadius, -ballRadius, ballRadius * 2, ballRadius * 2);
        }
        restoreLastTransform();
    }
    public void updateBall(double dt){
        ballAngle += 250*dt;
        ballPositionY -= ballVelocityY / 50;
        ballPositionX += ballVelocityX / 50;
        int[] platformcheck = checkBallOnPlatform();

        if (ballVelocityY < 0 && platformcheck[1] == 1) {
            if (!jumpReady) {
                if (ballVelocityY <= -51 && !heavy) {
                    ballVelocityY = -(ballVelocityY) / 3;
                } else {jumpReady = true;}

            }else {
                heavy = false;
                ballColour = white;
                ballVelocityY = 0;
                ballPositionY = platformcheck[0]-ballRadius;
            }
        }
        if (ballPositionX <= ballRadius) {
            ballPositionX = ballRadius;
            ballVelocityX = 10;
        }
        if (ballPositionX + ballRadius > width) {
            ballPositionX = width-ballRadius;
            ballVelocityX = 10;
        }

        switch (platformcheck[1]){
            case 0 -> {
                jumpReady = false;
                ballVelocityY -= 6;
            }
            case 2 -> { // bottom of platform
                jumpReady = false;
                ballPositionY = platformcheck[0] + ballRadius;
                ballVelocityY = ballVelocityY*(-1.0/2);
            }
            case 3 -> { // left of platform
                jumpReady = false;
                if (ballPositionX > ballRadius) {
                    ballPositionX = platformcheck[0] - ballRadius;
                    ballVelocityX = -200;
                }
                else {
                    if (lives > 0) {
                        respawnBall(0.0);
                    } else {
                        state = GameState.GameOver;
                    }
                }
            }
            case 4 -> { // jump pad
                heavy = false;
                ballColour = white;
                if (canJumpPad == 0) {
                    Jump = false;
                    ballPositionY -= 10;
                    jumpReady = false;
                    canJumpPad++;
                    ballVelocityY = 325 + (Math.abs(ballVelocityY/2.5));
                }
                else {
                    canJumpPad++;
                    if (canJumpPad >=20){
                        canJumpPad = 0;
                    }
                }
            }
        }

        if (Jump) {
            jumpCount++;
            ballVelocityY += (dt * 3000) / ((double) jumpCount/1.8);
            if (jumpCount >= 20) {
                Jump = false;
                jumpReady = false;
            }
        }

        if (heavy) {
            Jump = false;
            if (ballVelocityY > 0) {
                ballVelocityY = 0;
            }
            jumpCount = 0;
            if (ballPositionY + ballRadius < 415) {ballVelocityY = -300;}
        }
        checkCollisions();
    }

    public int[] checkBallOnPlatform() {
        if (state == GameState.Play) {
            for (ArrayList<Double> platform : platforms) {
                if (platform.get(0) == 5.0 && platform.get(1) + platform.get(3) >= ballPositionX - ballRadius &&
                        ballPositionX + ballRadius >= platform.get(1) && platform.get(2) + platform.get(4) >= ballPositionY - ballRadius &&
                        ballPositionY + ballRadius >= platform.get(2)-10) {
                    return new int[]{platform.get(2).intValue(), 4};
                }
                // If Ball on top of platform.
                if (ballPositionX >= platform.get(1) && ballPositionX - ballRadius <= platform.get(1) + platform.get(3) &&
                        ballPositionY + ballRadius >= platform.get(2) && ballPositionY < platform.get(2)) {
                    return new int[]{platform.get(2).intValue(), 1};
                }

                if (ballPositionX >= platform.get(1) && ballPositionX <= platform.get(1) + platform.get(3) &&
                        ballPositionY - ballRadius <= platform.get(2) + platform.get(4) && ballPositionY > platform.get(2) + platform.get(4)) {
                    ballPositionY++;
                    return new int[]{platform.get(2).intValue()+ platform.get(4).intValue(), 2}; // bottom of platform hit
                }
                if (ballPositionX + ballRadius >= platform.get(1) && ballPositionX - ballRadius < platform.get(1) &&
                        ballPositionY + ballRadius >= platform.get(2) && ballPositionY - ballRadius <= platform.get(2) + platform.get(4)) {
                    ballPositionX--;
                    return new int[]{platform.get(1).intValue(), 3}; // left of platform hit
                }
            }
        }
        return new int[]{0, 0};
    }
    public void checkCollisions(){
        if(gameLevel == 1){
            checkLevelFinish();
            checkBallonSpikes();
            if (enemies != null) {
                checkEnemyCollision();
            }
            checkCoinCollision();
        }
    }
    public void checkEnemyCollision(){
        int counter = -1;
        for(ArrayList<Double> enemy : enemies) {
            AffineTransform t = new AffineTransform();
            t.translate(enemy.get(1), enemy.get(2));
            double[] pts = {0, -40, -20, 19, 20, 19, -9.5, -10.5, 9.5, -10.5};
            t.transform(pts, 0, pts, 0, 5);
            if (enemy.get(0) == 0) {
                if (distance(ballPositionX, ballPositionY, pts[0], pts[1]) <= ballRadius ||
                        distance(ballPositionX, ballPositionY, pts[2], pts[3]) <= ballRadius ||
                        distance(ballPositionX, ballPositionY, pts[4], pts[5]) <= ballRadius ||
                        distance(ballPositionX, ballPositionY, pts[6], pts[7]) <= ballRadius ||
                        distance(ballPositionX, ballPositionY, pts[8], pts[9]) <= ballRadius) {
                    if (lives > 0) {
                        respawnBall(enemy.get(1));
                    } else {
                        state = GameState.GameOver;
                    }
                }
            } else if (enemy.get(0) == 1) {
                counter++;
                if(distance(ballPositionX,ballPositionY,enemy.get(1),enemy.get(2)) < ballRadius * 2 && bounceEnemyActive[counter]){
                    if(heavy){
                        enemy.set(1, enemy.get(1)*-1);
                        bounceEnemyActive[counter] = false;
                        score++;
                    }else {
                        if (lives > 0) {
                            respawnBall(enemy.get(1));
                        } else {
                            state = GameState.GameOver;
                        }
                    }
                }
            }
        }
    }
    public void checkBallonSpikes(){
        if (ballPositionY>=height()-70){
            if(lives>0){
                respawnBall(0.0);
            }else {
                state = GameState.GameOver;
            }
        }
    }
    public void checkLevelFinish(){
        if(ballPositionX - ballRadius >= flagPositionX){
            state = GameState.LevelComplete;
        }
    }
    public void checkCoinCollision(){
        for(int i = 0; i< coinPositionX.size(); i++){
            if(distance(ballPositionX,ballPositionY, coinPositionX.get(i), coinPositionY.get(i)) <= ballRadius*2){
                coinPositionX.set(i, coinPositionX.get(i)*-1);
                activeCoin[i]= false;
                score++;
            }
        }
    }
    public void respawnBall(Double enemyX){
        boolean breaking = false;
        int spawnX;
        ballVelocityX = 10;
        if (enemyX < 150 || enemyX > 450) {
            spawnX = 200;
        }
        else{
            spawnX = enemyX.intValue() + 80;
        }
        for (ArrayList<Double> platform : platforms) {
            for (int i = spawnX; i<=spawnX+100;i++) {
                if (platform.get(1) <= i && platform.get(1) + platform.get(3) >= i) {
                    ballPositionX = i;
                    heavy = false;
                    ballColour = white;
                    ballVelocityY = 0;
                    ballPositionY = platform.get(2) - ballRadius;
                    jumpReady = true;
                    breaking = true;
                    lives--;
                    break;
                }
                //Need to add if statement for if the ball respawns same place as ememies
                if(breaking) {
                    break;
                }
            }
        }
    }

    //*******************************************************
    //************************Spikes*************************
    //*******************************************************
    double[] spikePositionX;
    double[] spikePositionY;
    Image spike;
    public void initSpkies(){
        spike = loadImage("spike.png");
        if(gameLevel==1) {
            spikePositionY = new double[13];
            spikePositionX = new double[13];
            for (int i = 0; i < 13; i++) {
                spikePositionY[i] = 400;
                spikePositionX[i] = 10 + i * 40;
            }
        }
    }
    public void drawSpikes(){
        if(gameLevel==1) {
            for (int i = 0; i < 13; i++) {
                saveCurrentTransform();
                translate(spikePositionX[i], spikePositionY[i]);
                drawImage(spike,20,-40,40,60);
                restoreLastTransform();
            }
        }
    }
    public void updateSpikes(double dt){
        if (gameLevel == 1) {
            for (int i = 0; i < 13; i++) {
                spikePositionX[i] -= gameSpeed * dt;
                spikePositionY[i] = 490;
                checkOutScreen();
            }
        }
    }
    public void checkOutScreen(){
        for(int i=0;i<13;i++){
            if(spikePositionX[i] < -40) {
                spikePositionX[i] += width+20;
            }
        }
    }
    //*******************************************************
    //*********************Finish Flag***********************
    //*******************************************************
    double flagPositionX, flagPositionY;
    Image flag;
    public void initFlag(){
        flag = loadImage("flag.png");
        if(gameLevel==1) {
            flagPositionX = 15950;
            flagPositionY = 220;
        }
    }
    public void drawFlag(){
        if(gameLevel==1) {
            saveCurrentTransform();
            changeColor(Color.PINK);
            translate(flagPositionX, flagPositionY);
            drawImage(flag,0,0,50,100);
            restoreLastTransform();
        }
    }
    public void updateFlag(double dt){
        if(gameLevel==1) {
            flagPositionX -= gameSpeed * dt;
        }
    }
    //*******************************************************
    //************************Coins**************************
    //*******************************************************
    ArrayList<Double> coinPositionX, coinPositionY;
    boolean[] activeCoin;
    Image coinSpriteSheet;
    ArrayList<Image> coinFrames;
    public void initCoins(){
        coinFrames = new ArrayList<>();
        activeCoin = new boolean[5];
        coinSpriteSheet = loadImage("coinspritesheet.png");
        coinPositionX = new ArrayList<>();
        coinPositionY = new ArrayList<>();
        for(int y=0; y<2; y++){
            for (int x=0;x<3;x++) {
                coinFrames.add(subImage(coinSpriteSheet,x*200,y*200,200,200));
            }
        }
        if(gameLevel == 1) {
            try {
                coinRead = new Scanner(new File("coinPositions.txt"));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            while (coinRead.hasNext()) {
                currentLine = coinRead.nextLine().split(",");
                if (currentLine[0].equals("0")) {
                    coinPositionX.add(Double.parseDouble(currentLine[1]));
                    coinPositionY.add(Double.parseDouble(currentLine[2]));
                }
            }
            for (int i = 0; i < coinPositionX.size(); i++) {
                activeCoin[i] = true;
            }
        }else if(gameLevel==2){}
    }
    int count = 0;
    public void drawCoins() {
        if(gameLevel==1) {
            for (int i = 0; i < coinPositionX.size(); i++) {
                if(activeCoin[i]==true) {
                    translate(coinPositionX.get(i), coinPositionY.get(i));
                    drawImage(coinFrames.get(count/2), 0, 0, 20, 20);
                    restoreLastTransform();
                }
            }
        }
        if(count<11) {
            count++;
        }else{
            count = 0;
        }
    }
    public void updateCoins(double dt) {
        if (gameLevel == 1) {
            for (int i = 0; i < coinPositionX.size(); i++) {
                if(activeCoin[i]==true) {
                    coinPositionX.set(i, coinPositionX.get(i) - gameSpeed * dt);
                }
            }
        }
    }
}
