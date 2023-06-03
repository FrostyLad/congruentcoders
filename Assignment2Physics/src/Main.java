//Georgia Moberly - ID: 22009670
//Adam Pejcic - ID: 22011604
//Braden Johnston - ID: 20005898

import java.awt.*;
import java.awt.event.*;
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
    enum GameState {Menu, Levels, Play, Exit,GameOver,LevelComplete}

    GameState state = GameState.Menu;
    int menuOption,exitOption,levelMenu,levelExit,gameOver, gameLevel, width, height, levelCompOption;
    final int platformHeight = 25;
    double gameSpeed;
    Scanner platformreader, enemyreader;
    public void init() {
        try {
            platformreader = new Scanner(new File("platformPositions"));
            enemyreader = new Scanner(new File("enemyPositions"));
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
        width = 500;
        height = 500;
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
        }
    }
    public void drawGame(){
        if(gameLevel == 1) {
            drawSpikes();
            drawPlatforms();
            drawEnemies();
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
            drawText(width/2.0-48, 250, "Exit");
            changeColor(Color.blue);
            drawSolidCircle(100,235, 20);
            drawSolidCircle(400,235, 20);
        } else {
            changeColor(Color.darkGray);
            drawText(width/2.0-48, 250, "Exit");
        }
    }
    public void drawLevels(){
        changeColor(Color.BLUE);
        drawText(width/2.0-55,50, "Levels");
        if (levelExit == 0){
            changeColor(Color.darkGray);
            drawText(200, 400, "Main Menu", 30);
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
        }else if(levelExit == 1){
            changeColor(Color.white);
            drawText(200, 400, "Main Menu", 30);
            changeColor(Color.darkGray);
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
        if(gameOver == 0){
            changeColor(Color.WHITE);
            drawText(100, 300, "Main Menu");
            changeColor(Color.blue);
            drawSolidCircle(80,285,15);
        } else {
            changeColor(Color.darkGray);
            drawText(100, 300, "Main Menu");
            changeColor(Color.blue);
        }
        if (gameOver == 1) {
            changeColor(Color.WHITE);
            drawText(400, 300, "Exit");
            changeColor(Color.blue);
            drawSolidCircle(380,285,15);
        }else{
            changeColor(Color.darkGray);
            drawText(400, 300, "Exit");
            changeColor(Color.blue);
        }
    }
    public void drawLevelComplete(){
        changeColor(Color.BLUE);
        drawText(50,150, "You Completed Level "+gameLevel+"!");
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
    public void initGame(){
        initBall();
        initSpkies();
        initPlatforms();
        initEnemies();
        initFlag();
    }
    public void updateGame(double dt){
        updatePlatforms(dt);
        updateSpikes(dt);
        updateEnemies(dt);
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
                    init();
                    state = GameState.Play;
                }else if (levelMenu == 2){
                    gameLevel = 2;
                    init();
                    state = GameState.Play;
                }else if (levelMenu == 3){
                    gameLevel = 3;
                    init();
                    state = GameState.Play;
                }else if (levelMenu == 4){
                    gameLevel = 4;
                    init();
                    state = GameState.Play;
                }else if (levelMenu == 5){
                    gameLevel = 5;
                    init();
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
        }
    }
    public void checkBounceEnemyCollision(){
        for(int i=0;i<bounceEnemyPosistionX.size();i++){
            if(distance(ballPositionX,ballPositionY,bounceEnemyPosistionX.get(i),bounceEnemyPosistionY.get(i)) < ballRadius * 2){
               state=GameState.GameOver;
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
        if(ballPositionX - ballRadius >= flagPosistionX){
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
