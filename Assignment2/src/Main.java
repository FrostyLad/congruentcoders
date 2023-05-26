//Georgia Moberly - ID: 22009670
//Adam Pejcic - ID:
//Braden Johnston - ID:

import java.awt.*;
import java.awt.event.*;


public class Main extends GameEngine {
    public static void main(String args[]) {
        createGame(new Main(),144);
    }
    //*******************************************************
    // ************************Game**************************
    //*******************************************************
    enum GameState {Menu, Levels, Play, Exit,GameOver};
    GameState state = GameState.Menu;
    int menuOption = 0;
    int exitOption = 0;
    int levelMenu = 1;
    int levelExit = 0;
    int gameOver =0;
    public void init() {
        initSpkies();
        initPlatfromSmall();
        initPlatfromLarge();
        initBall();
    }
    public void update(double dt) {
        updatePlatfromSmall(dt);
        updateSpikes(dt);
        updatePlatfromLarge(dt);
        updateBall(dt);
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
        drawPlatformSmall();
        drawPlatformLarge();
        drawBall();
    }
    public void drawMenu(){
        changeColor(Color.BLUE);
        drawText(width()/2-55,50, "Menu");
        if(menuOption == 0) {
            changeColor(Color.darkGray);
            drawText(width()/2 -50, 150, "Play");
            changeColor(Color.blue);
            drawSolidCircle(100,135, 20);
            drawSolidCircle(400,135, 20);
        } else {
            changeColor(Color.white);
            drawText(width()/2-50, 150, "Play");
        }
        if(menuOption == 1) {
            changeColor(Color.darkGray);
            drawText(width()/2-65, 200, "Levels");
            changeColor(Color.blue);
            drawSolidCircle(100,185, 20);
            drawSolidCircle(400,185, 20);
        } else {
            changeColor(Color.white);
            drawText(width()/2-65, 200, "Levels");
        }
        if(menuOption == 2) {
            changeColor(Color.darkGray);
            drawText(width()/2-48, 250, "Exit");
            changeColor(Color.blue);
            drawSolidCircle(100,235, 20);
            drawSolidCircle(400,235, 20);
        } else {
            changeColor(Color.WHITE);
            drawText(width()/2-48, 250, "Exit");
        }
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
    //*******************************************************
    //***********************Key Press***********************
    //*******************************************************

    public void keyPressed(KeyEvent e){
        if (state == GameState.Play) {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                tempY = ballPositionY;
                Up = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                state = GameState.Menu;
            }
            // +
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
    //********************Platfrom Small*********************
    //*******************************************************
    double maxPlatfromsSmall;
    double[] platformSmallPositionX;
    double[] platformSmallPositionY;
    double[] platfromSmallVerlocityX;
    public void initPlatfromSmall(){
        maxPlatfromsSmall = 0;
        platformSmallPositionX = new double[100];
        platformSmallPositionY = new double[100];
        platfromSmallVerlocityX = new double[100];
        for(int i=0; i<maxPlatfromsSmall; i++) {
            platformSmallPositionY[i] += 420-i*120;
            platformSmallPositionX[i] += 1100 +i*150;
            platfromSmallVerlocityX[i] = 200;
        }
    }
    public void updatePlatfromSmall(double dt) {
        if (state == GameState.Play){
            for (int i = 0; i < maxPlatfromsSmall; i++) {
                platformSmallPositionX[i] -= platfromSmallVerlocityX[i] * dt;
            }
        }
    }
    public void drawPlatformSmall() {
        for(int i=0; i<maxPlatfromsSmall; i++) {
            changeColor(white);
            saveCurrentTransform();
            translate(0, 0);
            drawSolidRectangle(platformSmallPositionX[i], platformSmallPositionY[i], 100, 25);
            restoreLastTransform();
        }
    }
    //*******************************************************
    //********************Platfrom Large*********************
    //*******************************************************
    double maxPlatfromsLarge;
    double[] platformLargePositionX;
    double platformLargePositionY;
    double[] platfromLargeVerlocityX;
    public void initPlatfromLarge(){
        maxPlatfromsLarge =5;
        platformLargePositionX = new double[100];
        platfromLargeVerlocityX = new double[100];
        platformLargePositionY = 420;
        for(int i=0; i<=maxPlatfromsLarge; i++) {
            platformLargePositionX[i] = 250-i*50;
            platfromLargeVerlocityX[i] = 200;
        }
    }
    public void updatePlatfromLarge(double dt) {
        if (state == GameState.Play){
            for (int i = 0; i < maxPlatfromsLarge; i++) {
                platformLargePositionX[i] -= platfromLargeVerlocityX[i] * dt;
            }
        }
    }
    public void drawPlatformLarge() {
        for(int i=0; i<=maxPlatfromsLarge; i++) {
            changeColor(white);
            saveCurrentTransform();
            translate(0, 0);
            drawSolidRectangle(platformLargePositionX[i], platformLargePositionY, 500, 25);
            restoreLastTransform();
        }
    }
    //*******************************************************
    //*************************Ball**************************
    //*******************************************************
    double ballPositionX, ballPositionY, ballVerlocityX, ballVerlocityY, tempY;
    boolean Up;
    int bounceHeight, ballRadius;
    public void initBall(){
        Up = false;
        ballRadius = 20;
        ballPositionX = width()/2;
        ballPositionY = 400;
        ballVerlocityX = 10;
        ballVerlocityY = 5;
        bounceHeight = 100;
    }
    public void drawBall() {
        changeColor(white);
        saveCurrentTransform();
        translate(0, 0);
        drawSolidCircle(ballPositionX, ballPositionY , ballRadius);
        restoreLastTransform();
    }
    public void updateBall(double dt){
        checkBallOnPlatfrom();
        if (Up) {
            ballPositionY -= ballVerlocityY * dt * 30;
            for(int i=0; i<maxPlatfromsLarge;i++) {
                if (ballPositionY <= platformLargePositionY- bounceHeight) {
                    ballVerlocityY += -1;
                }
            }
        }
        if (ballPositionY>=height()-65){
            state = GameState.GameOver;
        }
    }
    public void checkBallOnPlatfrom(){
        for(int i=0;i<maxPlatfromsLarge;i++){
            if(ballPositionY >= platformLargePositionY-20){
                ballVerlocityY = 5;
            }
        }
    }
    //*******************************************************
    //************************Spikes*************************
    //*******************************************************
    double[] spikePosistionX;
    double[] spikePosistionY;
    double[] spikeVerlocityX;
    public void initSpkies(){
        spikePosistionY = new double[13];
        spikePosistionX = new double[13];
        spikeVerlocityX = new double[13];
        for(int i=0;i<13;i++) {
            spikePosistionY[i] = 490;
            spikeVerlocityX[i] = 200;
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
            spikePosistionX[i] -= spikeVerlocityX[i]*dt;
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
