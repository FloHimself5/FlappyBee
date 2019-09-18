package com.example.firstgame;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class TopPillar {
    private int left, top, right, bot;
    private double vel = 5;

    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    private boolean gotPoint = false;
    private boolean paused = false;

    private int width = 200;
    public TopPillar(int bot, int left){
        this.left = left;
        right = left + width;
        this.bot = bot;
        top = 0;
    }

    public TopPillar(int bot){
        left = screenWidth;
        right = left + width;
        this.bot = bot;
        top = 0;
    }

    public boolean passed(CharacterSprite bird) {
        if(gotPoint == false && bird.getX() + bird.getImageWidth() >= left ){
            //  System.out.println("Check1");
            if((bird.getX()) <= right)
                // System.out.println("Check2");
                gotPoint = true;
                return true;
            }
        return false;
    }

    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Color.rgb(0,255,0));
        paint.setStrokeWidth(10);
        canvas.drawRect(left, top, right, bot,paint);
    }

    public void update() {
        if(!paused) {
            left -= vel;
            right -= vel;
        }
    }

    public boolean check() {
        if(right <= 0){
            return true;
        }
        else return false;
    }

    public int getRight() {
        return right;
    }

    public boolean hit(CharacterSprite bird) {
        //System.out.println(bird.getImageWidth());
        if(bird.getY() < bot){

            if(bird.getX() + bird.getImageWidth() >= left ){
              //  System.out.println("Check1");
                    if((bird.getX()) <= right) {
                       // System.out.println("Check2");
                        return true;
                    }
            }
        }
        return false;
    }

    public void pause() {
        paused = true;
    }

    public void unpause() {
        paused = false;
    }
}
