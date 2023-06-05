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
        createGame(new Main(), 30);
    }
    //*******************************************************
    // ************************Game**************************
    //*******************************************************
    enum GameState {Menu, Levels, Play, Exit,GameOver,LevelComplete, Instructions, Options}
    GameState state = GameState.Menu;
    enum Difficulty {Easy, Normal, Hard};
    Difficulty difficulty = Difficulty.Normal;
    int menuOption,exitOption,levelMenu,levelExit,gameOver, gameLevel, width, height, levelCompOption, score,difficultyMenu, livesMenu,livesDifficulity;
    int lives = -1;
    final int platformHeight = 25;
    double gameSpeed;
    Scanner platformreader, enemyreader, coinreader;
    public void init() {
        try {
            platformreader = new Scanner(new File("platformPositions.txt"));
            enemyreader = new Scanner(new File("enemyPositions.txt"));
            coinreader = new Scanner(new File("coinPositions.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        levelCompOption = 0;
        menuOption = 0;
        exitOption = 0;
        levelMenu = 1;
        levelExit = 0;
        gameOver = 0;
        gameLevel = 1;
        width = 500;
        height = 500;
        score = 0;
        livesDifficulity = 0;
        difficultyMenu = 1; //0 = easy, 1 == normal, 2 == hard
        livesMenu = 0;//0 = 0 lives, 1 == 3 lives, 2 == 5 lives
        if (difficulty == Difficulty.Easy){
            gameSpeed = 150;
        }else if(difficulty == Difficulty.Normal){
            gameSpeed = 200;
        }else if(difficulty == Difficulty.Hard){
            gameSpeed = 250;
        }
        initGame();
    }
    public void update(double dt) {
        if (state == GameState.Play) {
            updateGame(dt);
        }
    }
    public void paintComponent() {
        changeBackgroundColor(black);
        clearBackground(width, height);
        if(state == GameState.Menu) {
            drawMenu();
        }else if(state == GameState.Levels) {
            drawLevels();
        }else if(state == GameState.Play) {
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
            drawPlatforms();
            drawEnemies();
            drawBall();
            drawFlag();
            drawCoins();
            changeColor(Color.BLUE);
            drawText(10,30, "Score: " + score,30);
            if(lives>=0){
                drawText(10,60, "Lives: " + lives,30);
            }
        }else{
            changeColor(Color.BLUE);
            drawText(100,150, "Next level");
            drawText(100,200, "Still to Come!");
            drawText(100,250,"Press ESC to go to main menu",20);
        }
    }
    public void drawMenu(){
        changeColor(Color.BLUE);
        drawText(width/2.0-55,50, "Menu");
        if(menuOption == 0) {
            changeColor(Color.WHITE);
            drawText(width/2.0 -50, 150, "Play");
            changeColor(Color.blue);
            drawSolidCircle(100,135, 20);
            drawSolidCircle(400,135, 20);
        } else {
            changeColor(Color.darkGray);
            drawText(width/2.0-50, 150, "Play");
        }
        if(menuOption == 1) {
            changeColor(Color.WHITE);
            drawText(width/2.0-65, 200, "Levels");
            changeColor(Color.blue);
            drawSolidCircle(100,185, 20);
            drawSolidCircle(400,185, 20);
        } else {
            changeColor(Color.darkGray);
            drawText(width/2.0-65, 200, "Levels");
        }
        if(menuOption == 2) {
            changeColor(Color.WHITE);
            drawText(width/2.0-100, 250, "Instructions");
            changeColor(Color.blue);
            drawSolidCircle(100,235, 20);
            drawSolidCircle(400,235, 20);
        } else {
            changeColor(Color.darkGray);
            drawText(width/2.0-100, 250, "Instructions");
        }
        if(menuOption == 3) {
            changeColor(Color.WHITE);
            drawText(width/2.0-75, 300, "Options");
            changeColor(Color.blue);
            drawSolidCircle(100,285, 20);
            drawSolidCircle(400,285, 20);
        } else {
            changeColor(Color.darkGray);
            drawText(width/2.0-75, 300, "Options");
        }
        if(menuOption == 4) {
            changeColor(Color.WHITE);
            drawText(width/2.0-48, 350, "Exit");
            changeColor(Color.blue);
            drawSolidCircle(100,335, 20);
            drawSolidCircle(400,335, 20);
        } else {
            changeColor(Color.darkGray);
            drawText(width/2.0-48, 350, "Exit");
        }
    }
    public void drawLevels(){
        changeColor(Color.BLUE);
        drawText(width/2.0-55,50, "Levels");
        drawText(160, 450,"press esc to exit",20);
            if (levelMenu == 1 ){
                changeColor(Color.WHITE);
                drawText(200,300,"1",200);
                changeColor(Color.darkGray);
                drawText(300,300,"2",100);
                drawText(360,300,"3",75);
                drawText(410,300,"4",50);
                drawText(450,300,"5",30);
            }else if(levelMenu == 2){
                changeColor(Color.WHITE);
                drawText(200,300,"2",200);
                changeColor(Color.darkGray);
                drawText(300,300,"3",100);
                drawText(360,300,"4",75);
                drawText(410,300,"5",50);
                drawText(160,300,"1",100);
            } else if(levelMenu == 3){
                changeColor(Color.WHITE);
                drawText(200,300,"3",200);
                changeColor(Color.darkGray);
                drawText(300,300,"4",100);
                drawText(360,300,"5",75);
                drawText(160,300,"2",100);
                drawText(120,300,"1",75);
            }else if(levelMenu == 4){
                changeColor(Color.WHITE);
                drawText(200,300,"4",200);
                changeColor(Color.darkGray);
                drawText(300,300,"5",100);
                drawText(150,300,"3",100);
                drawText(110,300,"2",75);
                drawText(90,300,"1",50);
            }else if(levelMenu == 5){
                changeColor(Color.WHITE);
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
        changeColor(Color.BLUE);
        drawText(100,150, "Do you want to exit?");
        if(exitOption == 0){
            changeColor(Color.WHITE);
            drawText(100, 300, "No");
            changeColor(Color.blue);
            drawSolidCircle(80,285,15);
        } else {
            changeColor(Color.darkGray);
            drawText(100, 300, "No");
            changeColor(Color.blue);
        }
        if (exitOption == 1) {
            changeColor(Color.WHITE);
            drawText(400, 300, "Yes");
            changeColor(Color.blue);
            drawSolidCircle(380,285,15);
        }else{
            changeColor(Color.darkGray);
            drawText(400, 300, "Yes");
            changeColor(Color.blue);
        }
    }
    public void drawGameOver(){
        changeColor(Color.BLUE);
        drawText(100,150, "Game Over");
        drawText(100,200,"Score: " + score,30);
        if(gameOver == 0){
            changeColor(Color.WHITE);
            drawText(100, 295, "Restart Level",20);
            changeColor(Color.blue);
            drawSolidCircle(80,285,15);
        } else {
            changeColor(Color.darkGray);
            drawText(100, 295, "Restart Level",20);
            changeColor(Color.blue);
        }
        if (gameOver == 1) {
            changeColor(Color.WHITE);
            drawText(300, 295, "Main Menu",20);
            changeColor(Color.blue);
            drawSolidCircle(280,285,15);
        }else{
            changeColor(Color.darkGray);
            drawText(300, 295, "Main Menu",20);
            changeColor(Color.blue);
        }
    }
    public void drawLevelComplete(){
        changeColor(Color.BLUE);
        drawText(50,150, "You Completed Level "+gameLevel+"!");
        drawText(50,200,"Score: " + score);
        if(levelCompOption == 0){
            changeColor(Color.WHITE);
            drawText(150, 300, "Next Level");
            changeColor(Color.blue);
            drawSolidCircle(80,285,15);
        } else {
            changeColor(Color.darkGray);
            drawText(150, 300, "Next Level");
        }
        if (levelCompOption == 1) {
            changeColor(Color.WHITE);
            drawText(150, 400, "Main Menu");
            changeColor(Color.blue);
            drawSolidCircle(80,385,15);
        }else{
            changeColor(Color.darkGray);
            drawText(150, 400, "Main Menu");
        }
    }
    public void drawInstructions(){
        changeColor(Color.BLUE);
        drawText(125,100, "Instructions",50);
        drawText(175, 450,"press esc to exit",20);
        changeColor(Color.WHITE);
        drawText(50,150,"Stay on the platform and reach the finish flag",20);
        drawText(50,175,"Use the up key to jump",20);
        drawText(50,200,"Use the down key to become heavy",20);
        drawText(50,225,"Use the left and right key to move left and right",20);
        drawText(50,250,"Jump over the red spikes",20);
        drawText(50,275,"Roll under or jump over the red balls",20);
        drawText(50,300,"Use the green jump pads to jump higher",20);
        drawText(50,325,"Collect coins to increase your score",20);
    }
    public void drawOptions(){
        changeColor(Color.BLUE);
        drawText(160,100, "Options",50);
        drawText(175, 450,"press esc to exit",20);
        changeColor(Color.green);
        drawText(50, 200, "Difficulty:",30);
        drawText(50, 250, "Lives:",30);
        if(difficulty == Difficulty.Easy){
            changeColor(white);
            drawText(185, 200, "Easy",30);
        }else{
            changeColor(Color.darkGray);
            drawText(185, 200, "Easy",30);
        }
        if(difficulty == Difficulty.Normal){
            changeColor(white);
            drawText(275, 200, "Normal",30);
        }else{
            changeColor(Color.darkGray);
            drawText(275, 200, "Normal",30);
        }if(difficulty == Difficulty.Hard){
            changeColor(white);
            drawText(400, 200, "Hard",30);
        }else{
            changeColor(Color.darkGray);
            drawText(400, 200, "Hard",30);
        }
        if(livesMenu == 0){
            changeColor(white);
            drawText(190, 250, "Zero",30);
        }else{
            changeColor(Color.darkGray);
            drawText(190, 250, "Zero",30);
        }
        if(livesMenu == 1){
            changeColor(white);
            drawText(280, 250, "Three",30);
        }else{
            changeColor(Color.darkGray);
            drawText(280, 250, "Three",30);
        }if(livesMenu == 2){
            changeColor(white);
            drawText(405, 250, "Five",30);
        }else{
            changeColor(Color.darkGray);
            drawText(405, 250, "Five",30);
        }
        if(livesDifficulity == 0){
            changeColor(Color.blue);
            drawSolidCircle(25,190, 15);
        }else{
            changeColor(Color.blue);
            drawSolidCircle(25,240, 15);
        }
    }
    public void initGame(){
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
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                if (jumpReady){ballPositionY--; Jump = true; jumpReady=false;}
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                if (ballVelocityX > 50) {
                    ballVelocityX -= (ballVelocityX-50)/2;
                }
                ballVelocityX -= 15;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                if (ballVelocityX < -50) {
                    ballVelocityX -= (ballVelocityX+50)/2;
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
        }else if (state == GameState.Menu){
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
                    menuOption +=2;
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
                    init();
                    state = GameState.Play;
                }else if(menuOption == 1){
                    state = GameState.Levels;
                }else if(menuOption == 2){
                    state = GameState.Instructions;
                }else if(menuOption == 3){
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
                    init();
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
        }else if(state == GameState.Instructions && e.getKeyCode() == KeyEvent.VK_ESCAPE){
            state = GameState.Menu;
        }else if(state == GameState.Options){
            if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                state = GameState.Menu;
            }
            if(e.getKeyCode() == KeyEvent.VK_LEFT){
                if(livesDifficulity == 0) {
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
                }else if(livesDifficulity == 1){
                    if(livesMenu == 0){
                        livesMenu+=2;
                        lives = 5;
                    }else if(livesMenu == 1){
                        livesMenu--;
                        lives = -1;
                    }else if(livesMenu == 2){
                        livesMenu--;
                        lives = 3;
                    }
                }
            }
            if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                if(livesDifficulity == 0) {
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
                }else if(livesDifficulity == 1){
                    if(livesMenu == 0){
                        livesMenu++;
                        lives = 3;
                    }else if(livesMenu == 1){
                        livesMenu++;
                        lives = 5;
                    }else if(livesMenu == 2){
                        livesMenu-=2;
                        lives = -1;
                    }
                }
            }
            if(e.getKeyCode() == KeyEvent.VK_UP){
                if(livesDifficulity == 1){
                    livesDifficulity--;
                }
            }
            if(e.getKeyCode() == KeyEvent.VK_DOWN){
                if(livesDifficulity == 0){
                    livesDifficulity++;
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
    public void initPlatforms(){
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
            while (platformreader.hasNext()) {
                currentLine = platformreader.nextLine().split(",");
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
        doUpdate(dt, jumpPadPositionX, jumpPadPositionY, 5, 30);
        doUpdate(dt, platformXSmallPositionX, platformXSmallPositionY, 0, 100);
        doUpdate(dt, platformSmallPositionX, platformSmallPositionY, 1, 200);
        doUpdate(dt, platformMedPositionX, platformMedPositionY, 2, 400);
        doUpdate(dt, platformLargePositionX, platformLargePositionY, 3, 800);
        doUpdate(dt, platformVertPositionX, platformVertPositionY, 4, 25);
    }

    private void doUpdate(double dt, ArrayList<Double> platformListX, ArrayList<Double> platformListY, double id, double length) {
        for (int i = 0; i < platformListX.size(); i++) {
            platformListX.set(i, platformListX.get(i)-gameSpeed*dt);
            if (platformListX.get(i) < width) {
                ArrayList<Double> temp = new ArrayList<>();
                temp.add(id);
                temp.add(platformListX.get(i));
                temp.add(platformListY.get(i));
                temp.add(length);
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
            restoreLastTransform();
        }
        for(int i=0; i<platformSmallPositionX.size(); i++) {
            changeColor(white);
            saveCurrentTransform();
            translate(0, 0);
            drawSolidRectangle(platformSmallPositionX.get(i), platformSmallPositionY.get(i), 200, 25);
            restoreLastTransform();
        }
        for(int i=0; i<platformMedPositionX.size(); i++) {
            changeColor(white);
            saveCurrentTransform();
            translate(0, 0);
            drawSolidRectangle(platformMedPositionX.get(i), platformMedPositionY.get(i), 400, 25);
            restoreLastTransform();
        }
        for (int i = 0; i < platformLargePositionX.size(); i++) {
            changeColor(white);
            saveCurrentTransform();
            translate(0, 0);
            drawSolidRectangle(platformLargePositionX.get(i), platformLargePositionY.get(i), 800, 25);
            restoreLastTransform();
        }
        for (int i = 0; i < platformVertPositionX.size(); i++) {
            changeColor(white);
            saveCurrentTransform();
            translate(0, 0);
            drawSolidRectangle(platformVertPositionX.get(i), platformVertPositionY.get(i), 25, 100);
            restoreLastTransform();
        }
        for (int i = 0; i < jumpPadPositionX.size(); i++) {
            changeColor(green);
            saveCurrentTransform();
            translate(0, 0);
            drawSolidRectangle(jumpPadPositionX.get(i), jumpPadPositionY.get(i), 30, 5);
            restoreLastTransform();
        }
    }

    //*******************************************************
    //***********************Enemies*************************
    //*******************************************************
    ArrayList<Double> spikeEnemyPosistionX, spikeEnemyPosistionY, bounceEnemyPosistionX, bounceEnemyPosistionY;
    public void initEnemies(){
        spikeEnemyPosistionX = new ArrayList<>();
        spikeEnemyPosistionY = new ArrayList<>();
        bounceEnemyPosistionX = new ArrayList<>();
        bounceEnemyPosistionY = new ArrayList<>();

        if(gameLevel == 1) {
            while (enemyreader.hasNext()) {
                currentLine = enemyreader.nextLine().split(",");
                switch (currentLine[0]) {
                    case "0" -> {
                        spikeEnemyPosistionX.add(Double.parseDouble(currentLine[1]));
                        spikeEnemyPosistionY.add(Double.parseDouble(currentLine[2]));
                    }
                    case "1" -> {
                        bounceEnemyPosistionX.add(Double.parseDouble(currentLine[1]));
                        bounceEnemyPosistionY.add(Double.parseDouble(currentLine[2]));
                    }
                }
            }
        }else if(gameLevel==2){}
    }
    public void drawEnemies() {
        if(gameLevel==1) {
            for (int i = 0; i < spikeEnemyPosistionX.size(); i++) {
                changeColor(Color.RED);
                translate(spikeEnemyPosistionX.get(i), spikeEnemyPosistionY.get(i));
                drawLine(-20, 19, 20, 19);
                drawLine(20, 19, 0, -40);
                drawLine(0, -40, -20, 19);
                restoreLastTransform();
            }
            for (int i = 0; i < bounceEnemyPosistionX.size(); i++) {
                changeColor(Color.RED);
                translate(bounceEnemyPosistionX.get(i), bounceEnemyPosistionY.get(i));
                drawSolidCircle(0, 0, 20);
                restoreLastTransform();
            }
        }
    }
    double bounceEnemySpeedY;
    public void updateEnemies(double dt) {
        if (gameLevel == 1) {
            for (int i = 0; i < spikeEnemyPosistionX.size(); i++) {
                spikeEnemyPosistionX.set(i, spikeEnemyPosistionX.get(i) - gameSpeed * dt);
            }
            for (int i = 0; i < bounceEnemyPosistionX.size(); i++) {
                bounceEnemyPosistionX.set(i, bounceEnemyPosistionX.get(i) - gameSpeed * dt);
                bounceEnemyPosistionX.set(i, bounceEnemyPosistionX.get(i) + bounceEnemySpeedY * dt);
                if (i == 0) {
                    if (bounceEnemyPosistionY.get(i) >= 75) {
                        bounceEnemySpeedY = bounceEnemySpeedY * -1;
                    }
                    if (bounceEnemyPosistionY.get(i) <= 175) {
                        bounceEnemySpeedY = bounceEnemySpeedY * -1;
                    }
                } else if (i == 1) {
                    if (bounceEnemyPosistionY.get(i) <= 200) {
                        bounceEnemySpeedY = bounceEnemySpeedY * -1;
                    }
                    if (bounceEnemyPosistionY.get(i) >= 300) {
                        bounceEnemySpeedY = bounceEnemySpeedY * -1;
                    }
                } else if (i == 2) {
                    if (bounceEnemyPosistionY.get(i) <= 100) {
                        bounceEnemySpeedY = bounceEnemySpeedY * -1;
                    }
                    if (bounceEnemyPosistionY.get(i) >= 200) {
                        bounceEnemySpeedY = bounceEnemySpeedY * -1;
                    }
                } else if (i == 3) {
                    if (bounceEnemyPosistionY.get(i) <= 100) {
                        bounceEnemySpeedY = bounceEnemySpeedY * -1;
                    }
                    if (bounceEnemyPosistionY.get(i) >= 200) {
                        bounceEnemySpeedY = bounceEnemySpeedY * -1;
                    }
                } else if (i == 4) {
                    if (bounceEnemyPosistionY.get(i) <= 100) {
                        bounceEnemySpeedY = bounceEnemySpeedY * -1;
                    }
                    if (bounceEnemyPosistionY.get(i) >= 200) {
                        bounceEnemySpeedY = bounceEnemySpeedY * -1;
                    }
                }
            }
        }
    }

    //*******************************************************
    //*************************Ball**************************
    //*******************************************************

    boolean Jump, jumpReady, heavy;
    int jumpCount, ballRadius;
    double ballPositionX, ballPositionY, ballVelocityX, ballVelocityY;
    java.awt.Color ballColour;

    public void initBall(){
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

    }
    public void drawBall() {
        changeColor(ballColour);
        saveCurrentTransform();
        translate(0, 0);
        drawSolidCircle(ballPositionX, ballPositionY , ballRadius);
        restoreLastTransform();
    }
    public void updateBall(double dt){
        ballPositionY -= ballVelocityY / 50;
        ballPositionX += ballVelocityX / 50;
        int[] platformcheck = checkBallOnPlatform();

        if (ballVelocityY < 0 && platformcheck[1] == 1) {
            doBallOnPlatform(platformcheck[0]);
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
            case 0 -> {jumpReady = false; ballVelocityY -= 10;}
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
                    state = GameState.GameOver;
                }
            }
            case 4 -> { // jump pad
                heavy = false;
                ballColour = white;
                ballVelocityY = 450 + (Math.abs(ballVelocityY)*1.5/4);
            }
        }

        if (Jump) {
            jumpCount++;
            ballVelocityY += (dt * 2500) / ((double) jumpCount);
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
                        ballPositionX + ballRadius >= platform.get(1) && platform.get(2) + platform.get(3) >= ballPositionY - ballRadius &&
                        ballPositionY + ballRadius >= platform.get(2)) {
                    return new int[]{platform.get(2).intValue(), 4};
                }
                // If Ball on top of platform.
                else if (ballPositionX >= platform.get(1) && ballPositionX - ballRadius <= platform.get(1) + platform.get(3) &&
                        ballPositionY + ballRadius >= platform.get(2) && ballPositionY < platform.get(2)) {
                    return new int[]{platform.get(2).intValue(), 1};
                }
                else if (ballPositionX >= platform.get(1) && ballPositionX <= platform.get(1) + platform.get(3) &&
                        ballPositionY - ballRadius <= platform.get(2) + platformHeight && ballPositionY > platform.get(2) + platformHeight) {
                    ballPositionY++;
                    return new int[]{platform.get(2).intValue()+platformHeight, 2}; // bottom of platform hit
                }
                else if (ballPositionX + ballRadius >= platform.get(1) && ballPositionX < platform.get(1) &&
                        ballPositionY + ballRadius >= platform.get(2) && ballPositionY - ballRadius <= platform.get(2) + platformHeight) {
                    ballPositionX--;
                    return new int[]{platform.get(1).intValue(), 3}; // left of platform hit
                }
            }
        }
        return new int[]{0, 0};
    }
    public void doBallOnPlatform(int platformHeight) {
        if (!jumpReady) {
            if (ballVelocityY <= -51 && !heavy) {
                ballVelocityY = -(ballVelocityY) / 3;
            } else {jumpReady = true;}

        }else {
            heavy = false;
            ballColour = white;
            ballVelocityY = 0;
            ballPositionY = platformHeight-ballRadius;
        }
    }
    public void checkCollisions(){
        if(gameLevel == 1){
            checkLevelFinish();
            checkBallonSpikes();
            checkBounceEnemyCollision();
            checkSpikeEnemyCollision();
            checkCoinCollision();
        }
    }
    public void checkBounceEnemyCollision(){
        for(int i=0;i<bounceEnemyPosistionX.size();i++){
            if(distance(ballPositionX,ballPositionY,bounceEnemyPosistionX.get(i),bounceEnemyPosistionY.get(i)) < ballRadius * 2){
                if(lives>0){
                    ballPositionY = 100;
                    ballPositionX = 250;
                    lives--;
                }else if(lives <= 0) {
                    state = GameState.GameOver;
                }
            }
        }
    }
    public void checkSpikeEnemyCollision(){
        for(int i=0;i<spikeEnemyPosistionX.size();i++){
            AffineTransform t = new AffineTransform();
            t.translate(spikeEnemyPosistionX.get(i), spikeEnemyPosistionY.get(i));
            double[] pts = {0, -40, -20, 19, 20, 19, -9.5, -10.5, 9.5, -10.5};
            t.transform(pts, 0, pts, 0, 5);
            if (distance(ballPositionX, ballPositionY, pts[0], pts[1]) <= ballRadius ||
                    distance(ballPositionX, ballPositionY, pts[2], pts[3]) <= ballRadius ||
                    distance(ballPositionX, ballPositionY, pts[4], pts[5]) <= ballRadius ||
                    distance(ballPositionX, ballPositionY, pts[6], pts[7]) <= ballRadius ||
                    distance(ballPositionX, ballPositionY, pts[8], pts[9]) <= ballRadius) {
                if(lives>0){
                    ballPositionY = 100;
                    ballPositionX = 250;
                    lives--;
                }else if(lives <= 0) {
                    state = GameState.GameOver;
                }
            }
        }
    }
    public void checkBallonSpikes(){
        if (ballPositionY>=height()-65){
            if(lives>0){
                ballPositionY = 100;
                ballPositionX = 250;
                lives--;
            }else if(lives <= 0) {
                state = GameState.GameOver;
            }
        }
    }
    public void checkLevelFinish(){
        if(ballPositionX - ballRadius >= flagPosistionX){
            state = GameState.LevelComplete;
        }
    }
    public void checkCoinCollision(){
        for(int i=0;i<coinPosistionX.size();i++){
            if(distance(ballPositionX,ballPositionY,coinPosistionX.get(i),coinPosistionY.get(i)) <= ballRadius*2){
                coinPosistionX.set(i, coinPosistionX.get(i)*-1);
                activeCoin[i]= false;
                score++;
            }
        }
    }

    //*******************************************************
    //************************Spikes*************************
    //*******************************************************
    double[] spikePosistionX;
    double[] spikePosistionY;
    public void initSpkies(){
        if(gameLevel==1) {
            spikePosistionY = new double[13];
            spikePosistionX = new double[13];
            for (int i = 0; i < 13; i++) {
                spikePosistionY[i] = 490;
                spikePosistionX[i] = 10 + i * 40;
            }
        }
    }
    public void drawSpikes(){
        if(gameLevel==1) {
            for (int i = 0; i < 13; i++) {
                saveCurrentTransform();
                changeColor(white);
                translate(spikePosistionX[i], spikePosistionY[i]);
                drawLine(-20, 20, 20, 20);
                drawLine(20, 20, 0, -40);
                drawLine(0, -40, -20, 20);
                restoreLastTransform();
            }
        }
    }
    public void updateSpikes(double dt){
        if (gameLevel == 1) {
            for (int i = 0; i < 13; i++) {
                spikePosistionX[i] -= gameSpeed * dt;
                spikePosistionY[i] = 490;
                checkOutScreen();
            }
        }
    }
    public void checkOutScreen(){
        for(int i=0;i<13;i++){
            if(spikePosistionX[i] < 0) {
                spikePosistionX[i] += width+20;
            }
        }
    }
    //*******************************************************
    //*********************Finish Flag***********************
    //*******************************************************
    double flagPosistionX, flagPosistionY;
    Image flag;
    public void initFlag(){
        flag = loadImage("flag.png");
        if(gameLevel==1) {
            flagPosistionX = 15950;
            flagPosistionY = 220;
        }
    }
    public void drawFlag(){
        if(gameLevel==1) {
            saveCurrentTransform();
            changeColor(Color.PINK);
            translate(flagPosistionX, flagPosistionY);
            drawImage(flag,0,0,50,100);
            restoreLastTransform();
        }
    }
    public void updateFlag(double dt){
        if(gameLevel==1) {
            flagPosistionX -= gameSpeed * dt;
        }
    }
    //*******************************************************
    //************************Coins**************************
    //*******************************************************
    ArrayList<Double> coinPosistionX, coinPosistionY;
    boolean[] activeCoin;

    Image coin;
    public void initCoins(){

        activeCoin = new boolean[5];
        coin = loadImage("coin.png");
        coinPosistionX = new ArrayList<>();
        coinPosistionY = new ArrayList<>();

        if(gameLevel == 1) {
            while (coinreader.hasNext()) {
                currentLine = coinreader.nextLine().split(",");
                switch (currentLine[0]) {
                    case "0" -> {
                        coinPosistionX.add(Double.parseDouble(currentLine[1]));
                        coinPosistionY.add(Double.parseDouble(currentLine[2]));
                    }
                }
            }
            for (int i = 0; i < coinPosistionX.size(); i++) {
                activeCoin[i] = true;
            }
        }else if(gameLevel==2){}
    }
    public void drawCoins() {
        if(gameLevel==1) {
            for (int i = 0; i < coinPosistionX.size(); i++) {
                if(activeCoin[i]==true) {
                    translate(coinPosistionX.get(i), coinPosistionY.get(i));
                    drawImage(coin, 0, 0, 20, 20);
                    restoreLastTransform();
                }
            }
        }
    }
    public void updateCoins(double dt) {
        if (gameLevel == 1) {
            for (int i = 0; i < coinPosistionX.size(); i++) {
                if(activeCoin[i]==true) {
                    coinPosistionX.set(i, coinPosistionX.get(i) - gameSpeed * dt);
                }
            }
        }
    }
}

