//Georgia Moberly - ID: 22009670
//Adam Pejcic - ID:
//Braden Johnston - ID:

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main extends GameEngine {
    public static void main(String args[]) {
        createGame(new Main());
    }
    //*******************************************************
    // ************************Game**************************
    //*******************************************************
    enum GameState {Menu, Levels, Play, Exit,GameOver};
    GameState state = GameState.Menu;
    int menuOption,exitOption,levelMenu,levelExit,gameOver, gameLevel;
    double gameSpeed;
    Scanner platformreader;
    public void init() {
        try {
            platformreader = new Scanner(new File("platformPositions"));
            System.out.println(platformreader.nextLine());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        menuOption = 0;
        exitOption = 0;
        levelMenu = 1;
        levelExit = 0;
        gameOver =0;
        gameSpeed = 200;
        gameLevel = 1;
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
        }
    }
    public void drawGame(){
        drawSpikes();
        drawPlatformXSmall();
        drawPlatformSmall();
        drawPlatformMed();
        drawPlatformLarge();
        drawPlatformVert();
        drawSpikeEnemy();
        drawBounceEnemy();
        drawJumpPad();
    }
    public void drawMenu(){                                     // +
        changeColor(Color.BLUE);
        drawText(width()/2-55,50, "Menu");
        if(menuOption == 0) {                                   // +
            changeColor(Color.darkGray);                        // +
            drawText(width()/2 -50, 150, "Play");        // +
            changeColor(Color.blue);                            // +
            drawSolidCircle(100,135, 20);            // +
            drawSolidCircle(400,135, 20);            // +
        } else {                                                // +
            changeColor(Color.white);                           // +
            drawText(width()/2-50, 150, "Play");         // +
        }
        if(menuOption == 1) {                                   // +
            changeColor(Color.darkGray);                        // +
            drawText(width()/2-65, 200, "Levels");     // +
            changeColor(Color.blue);                            // +
            drawSolidCircle(100,185, 20);            // +
            drawSolidCircle(400,185, 20);            // +
        } else {                                                // +
            changeColor(Color.white);                           // +
            drawText(width()/2-65, 200, "Levels");     // +
        }
        if(menuOption == 2) {                                   // +
            changeColor(Color.darkGray);                        // +
            drawText(width()/2-48, 250, "Exit");        // +
            changeColor(Color.blue);                            // +
            drawSolidCircle(100,235, 20);           // +
            drawSolidCircle(400,235, 20);           // +
        } else {                                                // +
            changeColor(Color.WHITE);                           // +
            drawText(width()/2-48, 250, "Exit");        // +
        }                                                       // +
    }
    public void drawLevels(){
        changeColor(Color.BLUE);
        drawText(width()/2-55,50, "Levels");
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
            if (levelMenu == 1 ){
                changeColor(Color.white);
                drawText(200,300,"1",200);
                drawText(300,300,"2",100);
                drawText(360,300,"3",75);
                drawText(410,300,"4",50);
                drawText(450,300,"5",30);
            }else if(levelMenu == 2){
                changeColor(Color.white);
                drawText(200,300,"2",200);
                drawText(300,300,"3",100);
                drawText(360,300,"4",75);
                drawText(410,300,"5",50);
                drawText(160,300,"1",100);
            } else if(levelMenu == 3){
                changeColor(Color.white);
                drawText(200,300,"3",200);
                drawText(300,300,"4",100);
                drawText(360,300,"5",75);
                drawText(160,300,"2",100);
                drawText(120,300,"1",75);
            }else if(levelMenu == 4){
                changeColor(Color.white);
                drawText(200,300,"4",200);
                drawText(300,300,"5",100);
                drawText(150,300,"3",100);
                drawText(110,300,"2",75);
                drawText(90,300,"1",50);
            }else if(levelMenu == 5){
                changeColor(Color.white);
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
    public void initGame(){
        initJumpPad();
        initSpkies();
        initPlatfromXSmall();
        initPlatfromSmall();
        initPlatfromMed();
        initPlatfromLarge();
        initPlatfromVert();
        initBounceEnemy();
        initSpikeEnemy();
    }
    public void updateGame(double dt){
        updateJumpPad(dt);
        updatePlatfromXSmall(dt);
        updatePlatfromSmall(dt);
        updatePlatfromMed(dt);
        updatePlatfromLarge(dt);
        updatePlatfromVert(dt);
        updateSpikes(dt);
        updateSpikeEnemy(dt);
        updateBounceEnemy(dt);
    }
    //*******************************************************
    //***********************Key Press***********************
    //*******************************************************

    public void keyPressed(KeyEvent e){
        if (state == GameState.Play) {
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
                }
                else if (levelMenu == 2){
                    gameLevel = 2;
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
        }
    }

    //*******************************************************
    //********************Platfrom X-Small*******************
    //*******************************************************
    double maxPlatfromsXSmall;
    ArrayList<Double> platformXSmallPositionX;
    ArrayList<Double> platformXSmallPositionY;
    String[] currentLine;
    public void initPlatfromXSmall(){
        if(gameLevel == 1) {
            platformXSmallPositionX = new ArrayList<>();
            platformXSmallPositionY = new ArrayList<>();
            while (platformreader.hasNext()) {
                currentLine = platformreader.nextLine().split(",");
                platformXSmallPositionX.add(Double.parseDouble(currentLine[1]));
                platformXSmallPositionY.add(Double.parseDouble(currentLine[2]));
            }
        }else if(gameLevel == 2) {
        }
    }
    public void updatePlatfromXSmall(double dt) {
        for (int i = 0; i < maxPlatfromsXSmall; i++) {
            platformXSmallPositionX.set(i, (-gameSpeed*dt));
        }
    }
    public void drawPlatformXSmall() {
        for(int i=0; i<maxPlatfromsXSmall; i++) {
                changeColor(white);
                saveCurrentTransform();
                translate(0, 0);
                drawSolidRectangle(platformXSmallPositionX.get(i), platformXSmallPositionY.get(i), 100, 25);
                restoreLastTransform();
        }
    }

    //*******************************************************
    //********************Platfrom Small*********************
    //*******************************************************
    double maxPlatfromsSmall;
    double[] platformSmallPositionX;
    double[] platformSmallPositionY;
    public void initPlatfromSmall(){
        if(gameLevel == 1) {
            maxPlatfromsSmall=20;
            platformSmallPositionX = new double[30];
            platformSmallPositionY = new double[30];
            for (int i = 0; i <= maxPlatfromsSmall; i++) {
                if (i == 0) {
                    platformSmallPositionX[i] = 1950;
                    platformSmallPositionY[i] = 370;
                } else if (i == 1) {
                    platformSmallPositionX[i] = 2250;
                    platformSmallPositionY[i] = 320;
                } else if (i == 2) {
                    platformSmallPositionX[i] = 2550;
                    platformSmallPositionY[i] = 270;
                } else if (i == 3) {
                    platformSmallPositionX[i] = 2850;
                    platformSmallPositionY[i] = 220;
                } else if (i == 4) {
                    platformSmallPositionX[i] = 3150;
                    platformSmallPositionY[i] = 170;
                } else if (i == 5) {
                    platformSmallPositionX[i] = 3950;
                    platformSmallPositionY[i] = 270;
                } else if (i == 6) {
                    platformSmallPositionX[i] = 5250;
                    platformSmallPositionY[i] = 320;
                } else if (i == 7) {
                    platformSmallPositionX[i] = 5950;
                    platformSmallPositionY[i] = 345;
                } else if (i == 8) {
                    platformSmallPositionX[i] = 6150;
                    platformSmallPositionY[i] = 270;
                } else if (i == 9) {
                    platformSmallPositionX[i] = 6850;
                    platformSmallPositionY[i] = 290;
                } else if (i == 10) {
                    platformSmallPositionX[i] = 9100;
                    platformSmallPositionY[i] = 420;
                } else if (i == 11) {
                    platformSmallPositionX[i] = 9400;
                    platformSmallPositionY[i] = 320;
                } else if (i == 12) {
                    platformSmallPositionX[i] = 9700;
                    platformSmallPositionY[i] = 420;
                } else if (i == 13) {
                    platformSmallPositionX[i] = 10050;
                    platformSmallPositionY[i] = 420;
                } else if (i == 14) {
                    platformSmallPositionX[i] = 9800;
                    platformSmallPositionY[i] = 220;
                } else if (i == 15) {
                    platformSmallPositionX[i] = 10150;
                    platformSmallPositionY[i] = 120;
                } else if (i == 16) {
                    platformSmallPositionX[i] = 12850;
                    platformSmallPositionY[i] = 170;
                } else if (i == 17) {
                    platformSmallPositionX[i] = 13150;
                    platformSmallPositionY[i] = 220;
                } else if (i == 18) {
                    platformSmallPositionX[i] = 13750;
                    platformSmallPositionY[i] = 345;
                } else if (i == 19) {
                    platformSmallPositionX[i] = 14550;
                    platformSmallPositionY[i] = 320;
                }
            }
        }else if(gameLevel == 2) {
            maxPlatfromsSmall = 0;
            platformSmallPositionX = new double[30];
            platformSmallPositionY = new double[30];
            for (int i = 0; i <= maxPlatfromsSmall; i++) {
                if (i == 0) {
                    platformSmallPositionX[i] = 100;
                    platformSmallPositionY[i] = 270;
                }
            }
        }
    }
    public void updatePlatfromSmall(double dt) {
        for (int i = 0; i < maxPlatfromsSmall; i++) {
            platformSmallPositionX[i] -= gameSpeed * dt;
        }
    }
    public void drawPlatformSmall() {
        for(int i=0; i<maxPlatfromsSmall; i++) {
                changeColor(white);
                saveCurrentTransform();
                translate(0, 0);
                drawSolidRectangle(platformSmallPositionX[i], platformSmallPositionY[i], 200, 25);
                restoreLastTransform();
            }
    }
    //*******************************************************
    //********************Platfrom Medium*********************
    //*******************************************************
    double maxPlatfromsMed;
    double[] platformMedPositionX;
    double[] platformMedPositionY;
    public void initPlatfromMed(){
        if(gameLevel == 1) {
            maxPlatfromsMed=11;
            platformMedPositionX = new double[20];
            platformMedPositionY = new double[20];
            for (int i = 0; i <= maxPlatfromsMed; i++) {
                if (i == 0) {
                    platformMedPositionX[i] = 3450;
                    platformMedPositionY[i] = 120;
                } else if (i == 1) {
                    platformMedPositionX[i] = 4250;
                    platformMedPositionY[i] = 340;
                } else if (i == 2) {
                    platformMedPositionX[i] = 4750;
                    platformMedPositionY[i] = 420;
                } else if (i == 3) {
                    platformMedPositionX[i] = 5550;
                    platformMedPositionY[i] = 420;
                } else if (i == 4) {
                    platformMedPositionX[i] = 6350;
                    platformMedPositionY[i] = 195;
                } else if (i == 5) {
                    platformMedPositionX[i] = 8100;
                    platformMedPositionY[i] = 100;
                } else if (i == 6) {
                    platformMedPositionX[i] = 8600;
                    platformMedPositionY[i] = 220;
                } else if (i == 7) {
                    platformMedPositionX[i] = 10450;
                    platformMedPositionY[i] = 320;
                } else if (i == 8) {
                    platformMedPositionX[i] = 11950;
                    platformMedPositionY[i] = 320;
                } else if (i == 9) {
                    platformMedPositionX[i] = 13350;
                    platformMedPositionY[i] = 420;
                } else if (i == 10) {
                    platformMedPositionX[i] = 14850;
                    platformMedPositionY[i] = 220;
                }
            }
        }else if(gameLevel == 2) {
            maxPlatfromsMed = 0;
            platformMedPositionX = new double[20];
            platformMedPositionY = new double[20];
            for (int i = 0; i <= maxPlatfromsMed; i++) {
                if (i == 0) {
                    platformMedPositionX[i] =100;
                    platformMedPositionY[i]=320;
                }
            }
        }
    }
    public void updatePlatfromMed(double dt) {
        for (int i = 0; i < maxPlatfromsMed; i++) {
            platformMedPositionX[i] -= gameSpeed * dt;
        }
    }
    public void drawPlatformMed() {
        for(int i=0; i<maxPlatfromsMed; i++) {
            changeColor(white);
            saveCurrentTransform();
            translate(0, 0);
            drawSolidRectangle(platformMedPositionX[i], platformMedPositionY[i], 400, 25);
            restoreLastTransform();
        }
    }
    //*******************************************************
    //********************Platfrom Large*********************
    //*******************************************************
    double maxPlatfromsLarge;
    double[] platformLargePositionX;
    double[] platformLargePositionY;
    public void initPlatfromLarge(){
        if(gameLevel == 1) {
            maxPlatfromsLarge = 4;
            platformLargePositionX = new double[10];
            platformLargePositionY = new double[10];
            for (int i = 0; i <= maxPlatfromsLarge; i++) {
                if (i == 0) {
                    platformLargePositionX[i] = 100;
                    platformLargePositionY[i] = 420;
                } else if (i == 1) {
                    platformLargePositionX[i] = 1050;
                    platformLargePositionY[i] = 420;
                } else if (i == 2) {
                    platformLargePositionX[i] = 7150;
                    platformLargePositionY[i] = 420;
                } else if (i == 3) {
                    platformLargePositionX[i] = 15350;
                    platformLargePositionY[i] = 320;
                }
            }
        }else if(gameLevel == 2) {
            maxPlatfromsLarge = 0;
            platformLargePositionX = new double[10];
            platformLargePositionY = new double[10];
            for (int i = 0; i <= maxPlatfromsLarge; i++) {
                if (i == 0) {
                    platformLargePositionX[i] =100;
                    platformLargePositionY[i] =370;
                }
            }
        }
    }
    public void updatePlatfromLarge(double dt) {
        for (int i = 0; i < maxPlatfromsLarge; i++) {
            platformLargePositionX[i] -= gameSpeed * dt;
        }
    }
    public void drawPlatformLarge() {
            for (int i = 0; i < maxPlatfromsLarge; i++) {
                changeColor(white);
                saveCurrentTransform();
                translate(0, 0);
                drawSolidRectangle(platformLargePositionX[i], platformLargePositionY[i], 800, 25);
                restoreLastTransform();
            }
    }
    //*******************************************************
    //*******************Platfrom Vertical*******************
    //*******************************************************
    double maxPlatfromsVert;
    double[] platformVertPositionX;
    double[] platformVertPositionY;
    public void initPlatfromVert(){
        if(gameLevel == 1) {
            maxPlatfromsVert = 6;
            platformVertPositionX = new double[10];
            platformVertPositionY = new double[10];
            for (int i = 0; i <= maxPlatfromsVert; i++) {
                if (i == 0) {
                    platformVertPositionX[i] = 5950;
                    platformVertPositionY[i] = 345;
                } else if (i == 1) {
                    platformVertPositionX[i] = 6150;
                    platformVertPositionY[i] = 270;
                } else if (i == 2) {
                    platformVertPositionX[i] = 6350;
                    platformVertPositionY[i] = 195;
                } else if (i == 3) {
                    platformVertPositionX[i] = 13750;
                    platformVertPositionY[i] = 345;
                } else if (i == 4) {
                    platformVertPositionX[i] = 13950;
                    platformVertPositionY[i] = 270;
                } else if (i == 5) {
                    platformVertPositionX[i] = 14050;
                    platformVertPositionY[i] = 195;
                }
            }
        }else if(gameLevel == 2) {
            maxPlatfromsVert = 0;
            platformVertPositionX = new double[10];
            platformVertPositionY = new double[10];
            for (int i = 0; i <= maxPlatfromsVert; i++) {
                if(i==0){
                    platformVertPositionX[i] = 400;
                    platformVertPositionY[i] = 420;
                }
            }
        }
    }
    public void updatePlatfromVert(double dt) {
                for (int i = 0; i < maxPlatfromsVert; i++) {
                    platformVertPositionX[i] -= gameSpeed * dt;
                }
    }
    public void drawPlatformVert() {
            for (int i = 0; i < maxPlatfromsVert; i++) {
                changeColor(white);
                saveCurrentTransform();
                translate(0, 0);
                drawSolidRectangle(platformVertPositionX[i], platformVertPositionY[i], 25, 100);
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
        }else if(gameLevel==2){
            maxSpikeEnemy = 0;
            spikeEnemyPosistionX = new double[20];
            spikeEnemyPosistionY = new double[20];
            for (int i = 0; i < maxSpikeEnemy; i++) {
                if (i == 0) {
                    spikeEnemyPosistionX[i] = 250;
                    spikeEnemyPosistionY[i] = 400;
                }
            }
        }
    }
    public void drawSpikeEnemy() {
            for (int i = 0; i < maxSpikeEnemy; i++) {
                changeColor(Color.RED);
                translate(spikeEnemyPosistionX[i], spikeEnemyPosistionY[i]);
                drawLine(-20, 20, 20, 20);
                drawLine(20, 20, 0, -40);
                drawLine(0, -40, -20, 20);
                restoreLastTransform();
            }
    }
    public void updateSpikeEnemy(double dt){
            for (int i = 0; i < maxSpikeEnemy; i++) {
                spikeEnemyPosistionX[i] -= gameSpeed * dt;
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
        }else if(gameLevel == 2) {
            maxBounceEnemy = 0;
            bounceEnemySpeedY = 100;
            bounceEnemyPosistionX = new double[10];
            bounceEnemyPosistionY = new double[10];
            for (int i = 0; i < maxBounceEnemy; i++) {
                if (i == 0) {
                    bounceEnemyPosistionX[i] = 400;
                    bounceEnemyPosistionY[i] = 300;
                }
            }
        }
    }
    public void drawBounceEnemy() {
        for (int i = 0; i < maxBounceEnemy; i++) {
            changeColor(Color.RED);
            translate(0, 0);
            drawSolidCircle(bounceEnemyPosistionX[i], bounceEnemyPosistionY[i], 20);
            restoreLastTransform();
        }
    }
    public void updateBounceEnemy(double dt){
        if(gameLevel == 1) {
            for (int i = 0; i < maxBounceEnemy; i++) {
                bounceEnemyPosistionX[i] -= gameSpeed * dt;
                if (bounceEnemyPosistionX[i] <= width()) {
                    bounceEnemyPosistionY[i] += bounceEnemySpeedY * dt;
                    if (i == 0) {
                        if (bounceEnemyPosistionY[i] <= 75) {
                            bounceEnemySpeedY = bounceEnemySpeedY * -1;
                        }
                        if (bounceEnemyPosistionY[i] >= 175) {
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
        changeColor(Color.green);
        translate(0, 0);
        drawSolidRectangle(jumpPadPosistionX, jumpPadPosistionY, 30, 2);
        restoreLastTransform();
    }
    public void updateJumpPad(double dt){
        jumpPadPosistionX -= gameSpeed * dt;
    }

    //*******************************************************
    //*************************Ball**************************
    //*******************************************************
    public void initBall(){}
    public void drawBall() {}
    public void updateBall(double dt){}
    //*******************************************************
    //************************Spikes*************************
    //*******************************************************
    double[] spikePosistionX;
    double[] spikePosistionY;
    public void initSpkies(){
        spikePosistionY = new double[13];
        spikePosistionX = new double[13];
        for(int i=0;i<13;i++) {
            spikePosistionY[i] = 490;
            spikePosistionX[i] = 10+i*40;
        }
    }
    public void drawSpikes(){
        for (int i=0;i<13;i++) {
            saveCurrentTransform();
            changeColor(white);
            translate(spikePosistionX[i], spikePosistionY[i]);
            drawLine(-20, 20, 20, 20);
            drawLine(20, 20, 0, -40);
            drawLine(0, -40, -20, 20);
            restoreLastTransform();
        }
    }
    public void updateSpikes(double dt){
        for(int i=0;i<13;i++) {
            spikePosistionX[i] -= gameSpeed*dt;
            spikePosistionY[i] = 490;
            checkOutScreen();
        }
    }
    public void checkOutScreen(){
        for(int i=0;i<13;i++){
            if(spikePosistionX[i] < 0) {
                spikePosistionX[i] += width()+20;
            }
        }
    }
}