//Georgia Moberly - ID: 22009670
//Adam Pejcic - ID:
//Braden Johnston - ID:

import java.awt.event.*;

public class Main extends GameEngine {
    public static void main(String args[]) {
        createGame(new Main());
    }
    //*******************************************************
    // ************************Game**************************
    //*******************************************************
    double ballPositionX, ballPositionY, ballVerlocityX, ballVerlocityY, tempY;
    double maxPlatfroms, activePlatfroms;
    double[] distanceX;
    double[] distanceY;
    double[] platformPositionX;
    double[] platformPositionY;
    double[] platfromSpeed;
    boolean Up;
    int bounceHeight, ballRadius;
    public void init() {
        Up = false;
        ballRadius = 20;
        maxPlatfroms = 5;
        ballPositionX = width()/2;
        ballPositionY = height()/2/*-(ballRadius)*/;
        ballVerlocityX = 10;
        ballVerlocityY = 5;
        activePlatfroms = 0;
        bounceHeight = 100;
        distanceX = new double[100];
        distanceY = new double[100];
        platformPositionX = new double[100];
        platformPositionY = new double[100];
        platfromSpeed = new double[100];
        for(int i=0; i<maxPlatfroms; i++) {
            platformPositionX[i] = width()-100;
            platformPositionY[i] = rand(height()- 50);
            platfromSpeed[i] = 250;
            distanceX[i] = 0;
            distanceY[i] = 0;
        }
    }
    public void update(double dt) {
        updatePlatfrom(dt);
        if (Up){
            updateBall(dt);
        }
    }
    public void paintComponent() {
        changeBackgroundColor(black);
        clearBackground(500, 500);
        drawPlatform();
        drawBall();
    }
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_UP){
            tempY = ballPositionY;
            Up = true;
        }
    }

    //*******************************************************
    //***********************Platfrom************************
    //*******************************************************
    public void updatePlatfrom(double dt) {
        for(int i=0; i<maxPlatfroms; i++) {
            platformPositionX[i] -= platfromSpeed[i] * dt;
            if (platformPositionX[i] <= 0){
                activePlatfroms -=1;
            }
        }
    }
    public void drawPlatform() {
        for(int i=0; i<maxPlatfroms; i++) {
            changeColor(white);
            saveCurrentTransform();
            translate(0, 0);
            drawSolidRectangle(platformPositionX[i], platformPositionY[i], 100, 25);
            restoreLastTransform();
        }
    }
    //*******************************************************
    //*************************Ball**************************
    //*******************************************************
    public void drawBall() {
        changeColor(white);
        saveCurrentTransform();
        translate(0, 0);
        drawSolidCircle(ballPositionX, ballPositionY , ballRadius);
        restoreLastTransform();
    }
    public void updateBall(double dt){
        if (Up) {
            ballPositionY -= ballVerlocityY * dt * 30;
            if (ballPositionY <= tempY -bounceHeight) {
                ballVerlocityY += -1;
            }
            if (ballPositionY >= height()-ballRadius) {
                ballVerlocityY = 5;
                ballPositionY = height()-ballRadius;
                Up = false;
            }
        }
    }
}