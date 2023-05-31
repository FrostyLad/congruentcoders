//Georgia Moberly - ID: 22009670
//Adam Pejcic - ID:
//Braden Johnston - ID: 20005898

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Main extends GameEngine {
    public static void main(String[] args) {
        createGame(new Main());
    }
    //*******************************************************
    // ************************Game**************************
    //*******************************************************
    enum GameState {Menu, Levels, Play, Exit,GameOver,LevelComplete};
    GameState state = GameState.Menu;
    int menuOption,exitOption,levelMenu,levelExit,gameOver, gameLevel, width,levelCompOption;
    double gameSpeed;
    Scanner platformreader;
    public void init() {
        try {
            platformreader = new Scanner(new File("platformPositions"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        levelCompOption = 0;
        menuOption = 0;
        exitOption = 0;
        levelMenu = 1;
        levelExit = 0;
        gameOver =0;
        gameSpeed = 200;
        gameLevel = 1;
        width = width();
        initGame();
    }
    public void update(double dt) {
        if (state == GameState.Play) {
            updateGame(dt);
        }
    }
    public void paintComponent() {
        changeBackgroundColor(black);
        clearBackground(500, 500);
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
        }
    }
    public void drawGame(){
        if(gameLevel == 1) {
            drawSpikes();
            drawPlatforms();
            drawSpikeEnemy();
            drawBounceEnemy();
            drawJumpPad();
            drawBall();
            drawFlag();
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
            changeColor(Color.darkGray);
            drawText(width/2.0 -50, 150, "Play");
            changeColor(Color.blue);
            drawSolidCircle(100,135, 20);
            drawSolidCircle(400,135, 20);
        } else {
            changeColor(Color.white);
            drawText(width/2.0-50, 150, "Play");
        }
        if(menuOption == 1) {
            changeColor(Color.darkGray);
            drawText(width/2.0-65, 200, "Levels");
            changeColor(Color.blue);
            drawSolidCircle(100,185, 20);
            drawSolidCircle(400,185, 20);
        } else {
            changeColor(Color.white);
            drawText(width/2.0-65, 200, "Levels");
        }
        if(menuOption == 2) {
            changeColor(Color.darkGray);
            drawText(width/2.0-48, 250, "Exit");
            changeColor(Color.blue);
            drawSolidCircle(100,235, 20);
            drawSolidCircle(400,235, 20);
        } else {
            changeColor(Color.WHITE);
            drawText(width/2.0-48, 250, "Exit");
        }
    }
    public void drawLevels(){
        changeColor(Color.BLUE);
        drawText(width/2.0-55,50, "Levels");
        if (levelExit == 0){
            changeColor(Color.white);
            drawText(200, 400, "Main Menu", 20);
            if (levelMenu == 1 ){
                changeColor(Color.darkGray);
                drawText(200,300,"1",200);
                changeColor(Color.white);
                drawText(300,300,"2",100);
                drawText(360,300,"3",75);
                drawText(410,300,"4",50);
                drawText(450,300,"5",30);
            }else if(levelMenu == 2){
                changeColor(Color.darkGray);
                drawText(200,300,"2",200);
                changeColor(Color.white);
                drawText(300,300,"3",100);
                drawText(360,300,"4",75);
                drawText(410,300,"5",50);
                drawText(160,300,"1",100);
            } else if(levelMenu == 3){
                changeColor(Color.darkGray);
                drawText(200,300,"3",200);
                changeColor(Color.white);
                drawText(300,300,"4",100);
                drawText(360,300,"5",75);
                drawText(160,300,"2",100);
                drawText(120,300,"1",75);
            }else if(levelMenu == 4){
                changeColor(Color.darkGray);
                drawText(200,300,"4",200);
                changeColor(Color.white);
                drawText(300,300,"5",100);
                drawText(150,300,"3",100);
                drawText(110,300,"2",75);
                drawText(90,300,"1",50);
            }else if(levelMenu == 5){
                changeColor(Color.darkGray);
                drawText(200,300,"5",200);
                changeColor(Color.white);
                drawText(150,300,"4",100);
                drawText(110,300,"3",75);
                drawText(90,300,"2",50);
                drawText(70,300,"1",30);
            }
        }else if(levelExit == 1){
            changeColor(Color.darkGray);
            drawText(200, 400, "Main Menu", 20);
            changeColor(Color.white);
            if (levelMenu == 1 ){
                drawText(200,300,"1",200);
                drawText(300,300,"2",100);
                drawText(360,300,"3",75);
                drawText(410,300,"4",50);
                drawText(450,300,"5",30);
            }else if(levelMenu == 2){
                drawText(200,300,"2",200);
                drawText(300,300,"3",100);
                drawText(360,300,"4",75);
                drawText(410,300,"5",50);
                drawText(160,300,"1",100);
            } else if(levelMenu == 3){
                drawText(200,300,"3",200);
                drawText(300,300,"4",100);
                drawText(360,300,"5",75);
                drawText(160,300,"2",100);
                drawText(120,300,"1",75);
            }else if(levelMenu == 4){
                drawText(200,300,"4",200);
                drawText(300,300,"5",100);
                drawText(150,300,"3",100);
                drawText(110,300,"2",75);
                drawText(90,300,"1",50);
            }else if(levelMenu == 5){
                drawText(200,300,"5",200);
                drawText(150,300,"4",100);
                drawText(110,300,"3",75);
                drawText(90,300,"2",50);
                drawText(70,300,"1",30);
            }
        }
    }
    public void drawExit(){
        changeColor(Color.BLUE);
        drawText(100,150, "Do you want to exit?");
        if(exitOption == 0){
            changeColor(Color.darkGray);
            drawText(100, 300, "No");
            changeColor(Color.blue);
            drawSolidCircle(80,285,15);
        } else {
            changeColor(Color.white);
            drawText(100, 300, "No");
            changeColor(Color.blue);
        }
        if (exitOption == 1) {
            changeColor(Color.darkGray);
            drawText(400, 300, "Yes");
            changeColor(Color.blue);
            drawSolidCircle(380,285,15);
        }else{
            changeColor(Color.white);
            drawText(400, 300, "Yes");
            changeColor(Color.blue);
        }
    }
    public void drawGameOver(){
        changeColor(Color.BLUE);
        drawText(100,150, "Game Over");
        if(gameOver == 0){
            changeColor(Color.darkGray);
            drawText(100, 300, "Main Menu");
            changeColor(Color.blue);
            drawSolidCircle(80,285,15);
        } else {
            changeColor(Color.white);
            drawText(100, 300, "Main Menu");
            changeColor(Color.blue);
        }
        if (gameOver == 1) {
            changeColor(Color.darkGray);
            drawText(400, 300, "Exit");
            changeColor(Color.blue);
            drawSolidCircle(380,285,15);
        }else{
            changeColor(Color.white);
            drawText(400, 300, "Exit");
            changeColor(Color.blue);
        }
    }
    public void drawLevelComplete(){
        changeColor(Color.BLUE);
        drawText(50,150, "You Completed Level "+gameLevel+"!");
        if(levelCompOption == 0){
            changeColor(Color.darkGray);
            drawText(150, 300, "Next Level");
            changeColor(Color.blue);
            drawSolidCircle(80,285,15);
        } else {
            changeColor(Color.white);
            drawText(150, 300, "Next Level");
        }
        if (levelCompOption == 1) {
            changeColor(Color.darkGray);
            drawText(150, 400, "Main Menu");
            changeColor(Color.blue);
            drawSolidCircle(80,385,15);
        }else{
            changeColor(Color.white);
            drawText(150, 400, "Main Menu");
        }
    }
    public void initGame(){
        initBall();
        initJumpPad();
        initSpkies();
        initPlatforms();
        initBounceEnemy();
        initSpikeEnemy();
        initFlag();
    }
    public void updateGame(double dt){
        updateJumpPad(dt);
        updatePlatforms(dt);
        updateSpikes(dt);
        updateSpikeEnemy(dt);
        updateBounceEnemy(dt);
        updateBall(dt);
        updateFlag(dt);
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
                    ballVelocityX = 50;
                }
                ballVelocityX -= 5;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                if (ballVelocityX < -50) {
                    ballVelocityX = -50;
                }
                ballVelocityX += 5;
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                if (checkBallOnPlatform() == 0) {
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
                } else if(menuOption == 0){
                    menuOption +=2;
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                if(menuOption == 0){
                    menuOption++;
                }else if(menuOption == 1){
                    menuOption++;
                }else if (menuOption == 2){
                    menuOption-=2;
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                if(menuOption == 0){
                    init();
                    state = GameState.Play;
                }else if(menuOption == 1){
                    state = GameState.Levels;
                }else{
                    state = GameState.Exit;
                }
            }
        }else if(state == GameState.Levels) {
            if (e.getKeyCode() == KeyEvent.VK_DOWN){
                levelExit = 1;
            }
            if (e.getKeyCode() == KeyEvent.VK_UP){
                levelExit = 0;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT && levelExit ==0){
                if (levelMenu < 5){
                    levelMenu++;
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT && levelExit ==0){
                if (levelMenu > 1){
                    levelMenu--;
                }
            }
            if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                if(levelExit == 1){
                    state = GameState.Menu;
                    levelExit =0;
                }else if (levelMenu == 1){
                    gameLevel = 1;
                    initGame();
                    state = GameState.Play;
                }else if (levelMenu == 2){
                    gameLevel = 2;
                    initGame();
                    state = GameState.Play;
                }else if (levelMenu == 3){
                    gameLevel = 3;
                    initGame();
                    state = GameState.Play;
                }else if (levelMenu == 4){
                    gameLevel = 4;
                    initGame();
                    state = GameState.Play;
                }else if (levelMenu == 5){
                    gameLevel = 5;
                    initGame();
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
                    state = GameState.Menu;
                }else if(gameOver == 1){
                    state = GameState.Exit;
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
            platformVertPositionX, platformVertPositionY;
    ArrayList<ArrayList<Double>> platforms;

    String[] currentLine;
    public void initPlatforms(){
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
                }
            }
        }else if(gameLevel == 2) {
        }
    }
    public void updatePlatforms(double dt) {
        platforms = new ArrayList<>();
        changeColor(white);
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
    }

    //*******************************************************
    //**********************Spike Enemy**********************
    //*******************************************************
    double[] spikeEnemyPosistionX;
    double[] spikeEnemyPosistionY;
    double maxSpikeEnemy;
    public void initSpikeEnemy(){
        if(gameLevel == 1) {
            maxSpikeEnemy = 10;
            spikeEnemyPosistionX = new double[20];
            spikeEnemyPosistionY = new double[20];
            for (int i = 0; i < maxSpikeEnemy; i++) {
                if (i == 0) {
                    spikeEnemyPosistionX[i] = 1550;
                    spikeEnemyPosistionY[i] = 400;
                } else if (i == 1) {
                    spikeEnemyPosistionX[i] = 12050;
                    spikeEnemyPosistionY[i] = 300;
                } else if (i == 2) {
                    spikeEnemyPosistionX[i] = 12090;
                    spikeEnemyPosistionY[i] = 300;
                } else if (i == 3) {
                    spikeEnemyPosistionX[i] = 13370;
                    spikeEnemyPosistionY[i] = 400;
                } else if (i == 4) {
                    spikeEnemyPosistionX[i] = 13410;
                    spikeEnemyPosistionY[i] = 400;
                } else if (i == 5) {
                    spikeEnemyPosistionX[i] = 13450;
                    spikeEnemyPosistionY[i] = 400;
                } else if (i == 6) {
                    spikeEnemyPosistionX[i] = 13670;
                    spikeEnemyPosistionY[i] = 400;
                } else if (i == 7) {
                    spikeEnemyPosistionX[i] = 13710;
                    spikeEnemyPosistionY[i] = 400;
                } else if (i == 8) {
                    spikeEnemyPosistionX[i] = 14580;
                    spikeEnemyPosistionY[i] = 300;
                } else if (i == 9) {
                    spikeEnemyPosistionX[i] = 14620;
                    spikeEnemyPosistionY[i] = 300;
                }
            }
        }else if(gameLevel==2){}
    }
    public void drawSpikeEnemy() {
        if(gameLevel==1) {
            for (int i = 0; i < maxSpikeEnemy; i++) {
                changeColor(Color.RED);
                translate(spikeEnemyPosistionX[i], spikeEnemyPosistionY[i]);
                drawLine(-20, 20, 20, 20);
                drawLine(20, 20, 0, -40);
                drawLine(0, -40, -20, 20);
                restoreLastTransform();
            }
        }
    }
    public void updateSpikeEnemy(double dt){
        if(gameLevel == 1) {
            for (int i = 0; i < maxSpikeEnemy; i++) {
                spikeEnemyPosistionX[i] -= gameSpeed * dt;
            }
        }
    }
    //*******************************************************
    //*******************Ball Bounce Enemy*******************
    //*******************************************************
    double[] bounceEnemyPosistionX;
    double[] bounceEnemyPosistionY;
    double bounceEnemySpeedY;
    int maxBounceEnemy;
    public void initBounceEnemy(){
        if(gameLevel == 1) {
            maxBounceEnemy = 5;
            bounceEnemySpeedY = 100;
            bounceEnemyPosistionX = new double[10];
            bounceEnemyPosistionY = new double[10];
            for (int i = 0; i < maxBounceEnemy; i++) {
                if (i == 0) {
                    bounceEnemyPosistionX[i] = 6650;
                    bounceEnemyPosistionY[i] = 175;
                } else if (i == 1) {
                    bounceEnemyPosistionX[i] = 12240;
                    bounceEnemyPosistionY[i] = 300;
                } else if (i == 2) {
                    bounceEnemyPosistionX[i] = 15010;
                    bounceEnemyPosistionY[i] = 200;
                } else if (i == 3) {
                    bounceEnemyPosistionX[i] = 15050;
                    bounceEnemyPosistionY[i] = 200;
                } else if (i == 4) {
                    bounceEnemyPosistionX[i] = 15090;
                    bounceEnemyPosistionY[i] = 200;
                }
            }
        }else if(gameLevel == 2) {}
    }
    public void drawBounceEnemy() {
        if(gameLevel==1) {
            for (int i = 0; i < maxBounceEnemy; i++) {
                changeColor(Color.RED);
                translate(bounceEnemyPosistionX[i], bounceEnemyPosistionY[i]);
                drawSolidCircle(0, 0, 20);
                restoreLastTransform();
            }
        }
    }
    public void updateBounceEnemy(double dt){
        if(gameLevel == 1) {
            for (int i = 0; i < maxBounceEnemy; i++) {
                bounceEnemyPosistionX[i] -= gameSpeed * dt;
               bounceEnemyPosistionY[i] += bounceEnemySpeedY * dt;
                if (i == 0) {
                    System.out.println("Bounce PosY: "+bounceEnemyPosistionY[i]);
                    if (bounceEnemyPosistionY[i] >= 75) {
                        bounceEnemySpeedY = bounceEnemySpeedY * -1;
                    }
                    if (bounceEnemyPosistionY[i] <= 175) {
                        bounceEnemySpeedY = bounceEnemySpeedY * -1;
                    }
                } else if (i == 1) {
                        if (bounceEnemyPosistionY[i] <= 200) {
                            bounceEnemySpeedY = bounceEnemySpeedY * -1;
                        }
                        if (bounceEnemyPosistionY[i] >= 300) {
                            bounceEnemySpeedY = bounceEnemySpeedY * -1;
                        }
                    } else if (i == 2) {
                        if (bounceEnemyPosistionY[i] <= 100) {
                            bounceEnemySpeedY = bounceEnemySpeedY * -1;
                        }
                        if (bounceEnemyPosistionY[i] >= 200) {
                            bounceEnemySpeedY = bounceEnemySpeedY * -1;
                        }
                    } else if (i == 3) {
                        if (bounceEnemyPosistionY[i] <= 100) {
                            bounceEnemySpeedY = bounceEnemySpeedY * -1;
                        }
                        if (bounceEnemyPosistionY[i] >= 200) {
                            bounceEnemySpeedY = bounceEnemySpeedY * -1;
                        }
                    } else if (i == 4) {
                        if (bounceEnemyPosistionY[i] <= 100) {
                            bounceEnemySpeedY = bounceEnemySpeedY * -1;
                        }
                        if (bounceEnemyPosistionY[i] >= 200) {
                            bounceEnemySpeedY = bounceEnemySpeedY * -1;
                        }
                    }
            }
        }
    }
    //*******************************************************
    //***********************Jump Pad************************
    //*******************************************************
    double jumpPadPosistionX, jumpPadPosistionY;
    public void initJumpPad(){
        if(gameLevel == 1) {
            jumpPadPosistionX = 7900;
            jumpPadPosistionY = 415;
        }
    }
    public void drawJumpPad() {
        if(gameLevel==1) {
            changeColor(Color.green);
            translate(0, 0);
            drawSolidRectangle(jumpPadPosistionX, jumpPadPosistionY, 30, 2);
            restoreLastTransform();
        }
    }
    public void updateJumpPad(double dt){
        if(gameLevel==1) {
            jumpPadPosistionX -= gameSpeed * dt;
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

        if (ballVelocityY <= 0) {
            int height = checkBallOnPlatform();
            if (height != 0) {doBallOnPlatform(height);}
        }
        if (ballPositionX < ballRadius) {
            ballPositionX = ballRadius;
        }
        if (ballPositionX + ballRadius > width) {
            ballPositionX = width-ballRadius;
        }

        if (checkBallOnPlatform()==0) {
            ballVelocityY -= 10;
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

    public int checkBallOnPlatform() {
        if (state == GameState.Play) {
            for (ArrayList<Double> platform : platforms) {
                if (ballPositionX < platform.get(1) + platform.get(3) && ballPositionX > platform.get(1) && ballPositionY + ballRadius >= platform.get(2)) {
                    return (platform.get(2).intValue());
                }
            }
        }
        return 0;
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
            checkJumpPadCollision();
        }
    }
    public void checkJumpPadCollision(){
        if (distance(ballPositionX,ballPositionY,jumpPadPosistionX,jumpPadPosistionY) <=ballRadius){
            //insert big jump code here
        }
    }
    public void checkBounceEnemyCollision(){
        for(int i=0;i<maxBounceEnemy;i++){
            if(distance(ballPositionX,ballPositionY,bounceEnemyPosistionX[i],bounceEnemyPosistionY[i])<ballRadius){
               state=GameState.GameOver;
            }
        }
    }
    public void checkSpikeEnemyCollision(){
        for(int i=0;i<maxSpikeEnemy;i++){
            if(distance(ballPositionX,ballPositionY,spikeEnemyPosistionX[i],spikeEnemyPosistionY[i])<=ballRadius){
                state = GameState.GameOver;
            }
        }
    }
    public void checkBallonSpikes(){
        if (ballPositionY>=height()-65){
            state = GameState.GameOver;
        }
    }
    public void checkLevelFinish(){
        if(ballPositionX >= flagPosistionX-ballRadius){
            state = GameState.LevelComplete;
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
    public void initFlag(){
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
            drawLine(0, 0, 0, 100);
            restoreLastTransform();
        }
    }
    public void updateFlag(double dt){
        if(gameLevel==1) {
            flagPosistionX -= gameSpeed * dt;
        }
    }
}
