package com.example.firstgame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;


public class CharacterSprite {
    private Bitmap image;
    private int y = 0;
    private int x = 50;
    private double vel = 5;
    private double acc = 0;
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    private int lift = -20;
    private double grav = 0.7;
    private boolean alive = true;
    private int score = 0;

    public CharacterSprite(Bitmap bmp) {
        image = bmp;
        y = 50;
        vel = 0;
        acc = 0;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(image,x, (int)y, null);
    }

    public void update() {
        vel += grav;
        y += vel;

        if (y > screenHeight + image.getHeight()) {
            y = screenHeight;
            vel = 0;
        }
        if(y < 0){
            y = 0;
            vel = 0;
        }
    }

    public int getImageWidth(){
        return image.getWidth();
    }

    public int getImageHeight(){
        return image.getHeight();
    }

    public int getY(){
        return y;
    }

    public int getX(){
        return x;
    }



    public void press() {
        if(alive){
            vel += lift;
        }
    }

    public void kill() {
        alive = false;
        vel += grav;
    }

    public void addScore(int point) {
        score += point;
    }

    public int getScore() {
        return score;
    }

    public boolean fell() {
        if(y >= screenHeight){

            return true;
        }
        return false;
    }

    public boolean isAlive(){
        return alive;
    }
}